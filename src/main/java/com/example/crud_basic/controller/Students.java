package com.example.crud_basic.controller;

import com.example.crud_basic.Dao.StudentDao;
import com.example.crud_basic.Dao.StudentDaoHbnt;
import com.example.crud_basic.model.Student;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Students extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private StudentDao studentDao;
    private StudentDaoHbnt studentDaoHbnt;
    public void init() {
        String jdbcURL =  getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
        studentDao = new StudentDao(jdbcURL, jdbcUsername, jdbcPassword);
        studentDaoHbnt = new StudentDaoHbnt();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertStudent(request, response);
                    break;
                case "/delete":
                    deleteStudent(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateStudent(request, response);
                    break;
                case "/profile":
                    getMe(request,response);
                    break;
                default:
                    listStudent(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    private void listStudent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Student> listStudent = studentDao.listAllStudents();
        request.setAttribute("listStudent", listStudent);
        RequestDispatcher dispatcher = request.getRequestDispatcher("students.jsp");
        dispatcher.forward(request, response);
    }
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("add_student.jsp");
        dispatcher.forward(request, response);
    }
    private void getMe(HttpServletRequest request, HttpServletResponse response) throws  SQLException, IOException, ServletException{
        int id = Integer.parseInt(request.getParameter("id"));
        Student student = new Student((long) id);
        Student single = studentDao.getStudent(student);
        request.setAttribute("single",single);
        System.out.println(single.getFirstName());
        RequestDispatcher dispatcher = request.getRequestDispatcher("profile.jsp");
        dispatcher.forward(request,response);
    }
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Student std=new Student((long) id);
        Student existingStudent = studentDao.getStudent(std);
        RequestDispatcher dispatcher = request.getRequestDispatcher("add_student.jsp");
        request.setAttribute("student", existingStudent);
        dispatcher.forward(request, response);
    }
    private void insertStudent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String gender = request.getParameter("gender");
        Student newStudent = new Student(firstName, lastName, gender);
        studentDaoHbnt.saveStudent(newStudent);
        response.sendRedirect("list");
    }
    private void updateStudent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("firstName");
        String author = request.getParameter("lastName");
        String gender = request.getParameter("gender");
        Student book = new Student(Long.valueOf(id), title, author, gender);
        studentDao.updateStudent(book);
        response.sendRedirect("list");
    }
    private void deleteStudent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Student book = new Student(Long.valueOf(id));
        studentDao.deleteStudent(book);
        response.sendRedirect("list");
    }
}

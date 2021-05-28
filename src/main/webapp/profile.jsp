<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 5/13/2021
  Time: 8:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <style>
        .details{
        width: 16rem;
        height: 10rem;
        background: white;
        margin: 3rem auto;
        padding: 1rem 2rem 1rem 4rem;
        font-size: 16px;
        box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
        }
    </style>
</head>
<body>
<div class="details">
                       <h4>Student  Details</h4>
    <div>
            <span><b>Id         : </b> <c:out value="${single.id}" /></span>
    </div>
    <div>
            <span><b>First Name : </b> <c:out value="${single.firstName}" /> </span>
    </div>
    <div>
            <span><b>Last Name  : </b>  <c:out value="${single.lastName}" /></span>
    </div>
    <div>
            <span><b>Gender     : </b> <c:out value="${single.gender}" /></span>
    </div>
    <div>
            <span><a href="/crud_basic_war_exploded/new">Add New Student</a>
        &nbsp;&nbsp;&nbsp;
        <a href="/crud_basic_war_exploded/list">List All Students</a></span>
    </div>
             </div>
    <div>

    </div>
</div>

</body>
</html>

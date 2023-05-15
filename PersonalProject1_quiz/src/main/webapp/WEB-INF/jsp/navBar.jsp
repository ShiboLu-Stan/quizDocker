<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<style>
    ul {
        list-style-type: none;
        margin: 0;
        padding: 0;
    }

    li {
        display: inline;
    }
</style>

<ul>
    <li><a href="home">Home</a></li>

    <%

        if (session != null && session.getAttribute("user") != null) {
            out.print("<li><a href=\"logout\">Logout</a></li>");
        } else {

            out.print("<li><a href=\"login\">Login</a></li>");
        }
    %>

    <li><a href="register">Register</a></li>
    <li><a href="feedback">Feedback</a></li>
    <li><a href="contact">Contact</a></li>
</ul>

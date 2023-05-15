<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Register</title>
    <jsp:include page="navBar.jsp" />
    <SCRIPT>
        function onSubmit() {
            alert("Contact us Success!");
        }
    </SCRIPT>
</head>

<body>
<%-- div is for grouping items --%>
<div>
    <form method="post" action="/contact">
        <div>
            <label>Say something to admin</label>
            <input type="text" name="contactText">
        </div>
        <button type="submit" onclick="onSubmit()">Submit</button>
    </form>
</div>
</body>

</html>

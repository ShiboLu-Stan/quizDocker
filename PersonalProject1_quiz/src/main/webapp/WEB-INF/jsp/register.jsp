<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Register</title>
    <jsp:include page="navBar.jsp" />
    <SCRIPT>

        function onSubmit() {
            alert("Register Success! Please go to login.");
        }
    </SCRIPT>
</head>

<body>
<%-- div is for grouping items --%>
<div>
    <c:if test="${sessionScope.register eq 'username'}"> <p style="color: red"> Register failed</p></c:if>

    <form method="post" action="/register">
        <div>
            <label>FirstName</label>
            <input type="text" name="firstName">
        </div>
        <div>
            <label>LastName</label>
            <input type="text" name="lastName">
        </div>
        <div>
            <label>Address</label>
            <input type="text" name="address">
        </div>
        <div>
            <label>Email</label>
            <input type="text" name="email">
        </div>
        <div>
            <label>PhoneNumber</label>
            <input type="text" name="phoneNumber">
        </div>
        <div>
            <label>Username</label>
            <input type="text" name="username">
        </div>
        <div>
            <label>Password</label>
            <input type="password" name="password">
        </div>
        <button type="submit">Submit</button>
    </form>
</div>
</body>

</html>

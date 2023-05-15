
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Admin</title>
    <jsp:include page="navBar.jsp" />

</head>

<body>
<div>
    <p>First Name</p>
    <strong>${certainUserProfile.firstName}</strong>
    <p>Last Name</p>
    <strong>${certainUserProfile.lastName}</strong>
    <p>Primary Address</p>
    <strong>${certainUserProfile.address}</strong>
    <p>Primary Email</p>
    <strong>${certainUserProfile.email}</strong>
    <p>Primary Phone number</p>
    <strong>${certainUserProfile.phoneNumber}</strong>
    <p>Status</p>
    <strong>${certainUserProfile.status}</strong>

    <p></p>
    <a href="/admin"><button>Go back home</button></a>

</div>
</body>
</html>
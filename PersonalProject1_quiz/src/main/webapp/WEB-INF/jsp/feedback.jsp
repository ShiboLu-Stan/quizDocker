<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Register</title>
    <jsp:include page="navBar.jsp" />
    <SCRIPT>
        function onSubmit() {
            alert("Feedback Success!");
        }
    </SCRIPT>
</head>

<body>
<%-- div is for grouping items --%>
<div>
    <form method="post" action="/feedback">
        <div>
            <label>Star rating</label>
            <select name="curRate"  >
                <option value="0" >0 star</option>
                <option value="1" >1 star</option>
                <option value="2" >2 stars</option>
                <option value="3" >3 stars</option>
                <option value="4" >4 stars</option>
                <option value="5" >5 stars</option>
            </select>
        </div>
        <div>
            <label>Feedback</label>
            <input type="text" name="feedbackText">
        </div>
        <button type="submit" onclick="onSubmit()">Submit</button>
    </form>
</div>
</body>

</html>

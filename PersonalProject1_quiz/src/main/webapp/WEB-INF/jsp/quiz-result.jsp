<%@ page import="com.personalproject1.quiz.domain.User" %>
<%@ page import="com.personalproject1.quiz.domain.enums.Role" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Quiz Result</title>
    <jsp:include page="navBar.jsp" />
    <script>
        function takeNewQuiz(){
            var url="/home";
            var tempForm = document.createElement("form");
            tempForm.id = "takeNewQuiz";
            tempForm.method = "get";
            tempForm.action = url;
            tempForm.target=""; //打开新页面

            //将tempForm 表单添加到 documentbody之后
            document.body.appendChild(tempForm);
            tempForm.submit();
            document.body.removeChild(tempForm);
        }
    </script>
</head>

<body>
<div>
<%--    ${sessionScope.user.username}--%>
        <table style="border:1px solid black">
            <tbody>
                <tr >
                    <td style="border:1px solid black">Quiz Name</td>
                    <td style="border:1px solid black">${result.quizName}</td>
                </tr>
                <tr >
                    <td style="border:1px solid black">User Name</td>
                    <td style="border:1px solid black">${sessionScope.user.firstName} ${sessionScope.user.lastName}</td>
                </tr>
                <tr >
                    <td style="border:1px solid black">Starting time</td>
                    <td style="border:1px solid black">${result.dateTaken}</td>
                </tr>
                <tr >
                    <td style="border:1px solid black">Ending time</td>
                    <td style="border:1px solid black">${result.dateFinish}</td>
                </tr>
            </tbody>
        </table>

    <%



    %>

        <c:forEach items="${usedQuestionList}" var="usedQuestion" begin = "0" varStatus="loopQuestion">

            <p>${usedQuestion.question}</p>
            <%-- Dynamically render the choices --%>
            <c:forEach items="${usedQuestion.options}" var="option" begin = "0" varStatus="loopOption">
                <div style="">
                        ${option}
                    <c:if test="${fn:contains(usedQuestion.answer, loopOption.index)}"> answer</c:if>
                    <c:if test="${fn:contains(usedQuestion.userAnswer, loopOption.index)}"> selected</c:if>
                </div>
            </c:forEach>
        </c:forEach>

        <table style="border:1px solid black">
            <tbody>
            <tr >
                <td style="border:1px solid black">Quiz result</td>
                <td style="border:1px solid black">
                    <c:choose>
                        <c:when test="${result.score > 3.00}">Passes</c:when>
                        <c:otherwise>fails</c:otherwise>
                    </c:choose>
                </td>
            </tr>
            </tbody>
        </table>
        <button type="submit" onclick="takeNewQuiz()" ><%
            User curUser = (User)session.getAttribute("user");
            if(curUser.getRole().equals(Role.manager.toString())){
                out.print("go back to home");
            } else {
                out.print("Take Another Quiz");
            }
        %></button>

</div>
</body>

</html>
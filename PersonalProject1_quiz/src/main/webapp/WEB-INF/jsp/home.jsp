<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Main</title>
    <jsp:include page="navBar.jsp" />
    <script>
        function chooseCategory(id){
            var url="/quiz";
            var tempForm = document.createElement("form");
            tempForm.id = "chooseCategory";
            tempForm.method = "post";
            tempForm.action = url;
            tempForm.target=""; //打开新页面
            var hideInput1 = document.createElement("input");
            hideInput1.type = "hidden";
            hideInput1.name="categoryID"; //后台要接受这个参数来取值
            hideInput1.value = id; //后台实际取到的值
            tempForm.appendChild(hideInput1);

            //将tempForm 表单添加到 documentbody之后
            document.body.appendChild(tempForm);
            tempForm.submit();
            document.body.removeChild(tempForm);
        }
        function chooseQuiz(id){
            var url="/quiz-result";
            var tempForm = document.createElement("form");
            tempForm.id = "showQuizResult";
            tempForm.method = "post";
            tempForm.action = url;
            tempForm.target=""; //打开新页面
            var hideInput1 = document.createElement("input");
            hideInput1.type = "hidden";
            hideInput1.name="quizID"; //后台要接受这个参数来取值
            hideInput1.value = id; //后台实际取到的值
            tempForm.appendChild(hideInput1);

            //将tempForm 表单添加到 documentbody之后
            document.body.appendChild(tempForm);
            tempForm.submit();
            document.body.removeChild(tempForm);
        }
    </script>
</head>

<body>
<div>
    <p>Hello, ${user.username}</p>


    <strong>Click bellow to choose a Category and start a Quiz</strong>
    <table style="border:1px solid black">
        <thead>
        <tr style="border:1px solid black">
            <c:forEach items="${category}" var="category" varStatus="loop">
                <th style="border:1px solid black" onclick="chooseCategory(${category.id})">${category.description}</th>
            </c:forEach>
        </tr>
        </thead>
    </table>


    <strong>All quiz done before</strong>
    <table style="border:1px solid black">
        <thead>
        <tr>
            <th style="border:1px solid black">Name</th>
            <th style="border:1px solid black">Date taken</th>
            <th style="border:1px solid black">Result</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${quiz}" var="eachquiz" varStatus="loop">
            <tr >
                <td style="border:1px solid black">${eachquiz.quizName}</td>
                <td style="border:1px solid black">${eachquiz.dateTaken}</td>
                <td style="border:1px solid black" onclick="chooseQuiz(${eachquiz.id})">
                    Click me to view result
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>
</body>
</html>

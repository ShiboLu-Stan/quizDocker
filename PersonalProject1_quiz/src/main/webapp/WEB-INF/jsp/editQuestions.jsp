<%@ page import="java.util.List" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="com.personalproject1.quiz.domain.enums.Status" %>
<%@ page import="com.personalproject1.quiz.domain.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Admin</title>
    <jsp:include page="navBar.jsp" />
    <script>
        function changeQuestionStatus(id){
            var url="/changeQuestionStatus";
            var tempForm = document.createElement("form");
            tempForm.id = "changeQuestionStatus";
            tempForm.method = "post";
            tempForm.action = url;
            tempForm.target=""; //打开新页面
            var hideInput1 = document.createElement("input");
            hideInput1.type = "hidden";
            hideInput1.name="questionID"; //后台要接受这个参数来取值
            hideInput1.value = id; //后台实际取到的值
            tempForm.appendChild(hideInput1);

            //将tempForm 表单添加到 documentbody之后
            document.body.appendChild(tempForm);
            tempForm.submit();
            document.body.removeChild(tempForm);
        }
        window.onload=function() {

            <%
                if (!session.getAttribute("newQuestionValid").equals("valid"))   {
                    out.print("alert(\"Please input all required information to create a new Question!\")");
                }
                session.setAttribute("newQuestionValid", "valid");
            %>

        }

    </script>
</head>

<body>
<div>


    <strong>All Questions</strong>

        <table style="border:1px solid black">
            <thead>
            <tr>
                <th style="border:1px solid black">Question</th>
                <th style="border:1px solid black">Answer</th>
                <th style="border:1px solid black">Options</th>
                <th style="border:1px solid black">Create Time</th>
                <th style="border:1px solid black">Last Update Time</th>
                <th style="border:1px solid black">authorID</th>
                <th style="border:1px solid black">categoryID</th>
                <th style="border:1px solid black">status</th>
                <th style="border:1px solid black">Change Status</th>
<%--                <th style="border:1px solid black">Edit</th>--%>
            </tr>
            </thead>
            <tbody>

            <%

                if (session != null && session.getAttribute("user") != null) {
                    List<Question> allQuestions = (List<Question>) session.getAttribute("allQuestions");

                    System.out.println("allQuestions:\n" + allQuestions);
                    for (int i = 0; i < allQuestions.size(); i++){
                        Question eachQuestion = allQuestions.get(i);
//                        System.out.println("allQuestions get question" + i);
                        String clickChangeStatus = "onclick=\"changeQuestionStatus(" + eachQuestion.getId() + ")\"";
//                        System.out.println("allQuestions get clickChangeStatus" + i);
                        String buttonText = eachQuestion.getStatus().equals(Status.Active.toString()) ? Status.Suspend.toString().toUpperCase(): Status.Active.toString().toUpperCase();

//                        System.out.println("allQuestions Initing" + i);

                        StringBuilder stringBuilderOptions = new StringBuilder("");
                        for (int j = 0; j < eachQuestion.getOptions().size(); j++){
                            stringBuilderOptions.append("\n<p>" + eachQuestion.getOptions().get(j) + "</p>\n");
                        }
                        String radio = "<input type=\"radio\" name=\"QuestionEditID\" value=\"" + eachQuestion.getId() + "\"/>";

//                        System.out.println("allQuestions Inited" + i);

                        out.println("<tr >");
                        out.println("<th style=\"border:1px solid black\">"+ eachQuestion.getQuestion() + "</th>");
                        out.println("<th style=\"border:1px solid black\">"+ eachQuestion.getAnswer() + "</th>");
                        out.println("<th style=\"border:1px solid black\">"+ stringBuilderOptions + "</th>");
                        out.println("<th style=\"border:1px solid black\">"+ eachQuestion.getCreateTime() + "</th>");
                        out.println("<th style=\"border:1px solid black\">"+ eachQuestion.getLastUpdateTime() + "</th>");
                        out.println("<th style=\"border:1px solid black\">"+ eachQuestion.getAuthorID() + "</th>");
                        out.println("<th style=\"border:1px solid black\">"+ eachQuestion.getCategoryID() + "</th>");
                        out.println("<th style=\"border:1px solid black\">"+ eachQuestion.getStatus() + "</th>");
                        out.println("<th style=\"border:1px solid black\">" + "<button "+ clickChangeStatus +" >" + buttonText + "</button>" + "</th>");
//                        out.println("<th style=\"border:1px solid black\">" + radio + "</th>");
                        out.println("<tr>");
                    }
                } else {

                    out.print("");
                }
            %>
            </tbody>
        </table>

    <form method="post" action="/editQuestion">
        <select name="questionEditID" >
            <c:forEach items="${sessionScope.allQuestions}" var="question" begin = "0" varStatus="loopQuestion">
                <option value="${question.id}" >${question.question}</option>
            </c:forEach>
        </select>
        <div>
            <label>Question</label>
            <input type="text" name="question">
        </div>

        <div>
            <label>answer</label>
            <div style="display: inline">
                <input type="checkbox"
                       name="selectedAnswer"
                       value="0"
                       style="display: inline"/>
                option 1
            </div>
            <div style="display: inline">
                <input type="checkbox"
                       name="selectedAnswer"
                       value="1"
                       style="display: inline"/>
                option 2
            </div>
            <div style="display: inline">
                <input type="checkbox"
                       name="selectedAnswer"
                       value="2"
                       style="display: inline"/>
                option 3
            </div>
            <div style="display: inline">
                <input type="checkbox"
                       name="selectedAnswer"
                       value="3"
                       style="display: inline"/>
                option 4
            </div>
        </div>
        <div>
            <label>option1</label>
            <input type="text" name="option1">
        </div>
        <div>
            <label>option2</label>
            <input type="text" name="option2">
        </div>
        <div>
            <label>option3</label>
            <input type="text" name="option3">
        </div>
        <div>
            <label>option4</label>
            <input type="text" name="option4">
        </div>
        <button type="submit" > Edit question</button>
    </form>


    <strong>Create New Question</strong>
    <form method="post" action="/createNewQuestion">
        <div>
            <label>Question</label>
            <input type="text" name="question">
        </div>
        <div>
            <label>answer</label>
            <div style="display: inline">
                <input type="checkbox"
                       name="selectedAnswer"
                       value="0"
                       style="display: inline"/>
                option 1
            </div>
            <div style="display: inline">
                <input type="checkbox"
                       name="selectedAnswer"
                       value="1"
                       style="display: inline"/>
                option 2
            </div>
            <div style="display: inline">
                <input type="checkbox"
                       name="selectedAnswer"
                       value="2"
                       style="display: inline"/>
                option 3
            </div>
            <div style="display: inline">
                <input type="checkbox"
                       name="selectedAnswer"
                       value="3"
                       style="display: inline"/>
                option 4
            </div>
        </div>
        <div>
            <label>option1</label>
            <input type="text" name="option1">
        </div>
        <div>
            <label>option2</label>
            <input type="text" name="option2">
        </div>
        <div>
            <label>option3</label>
            <input type="text" name="option3">
        </div>
        <div>
            <label>option4</label>
            <input type="text" name="option4">
        </div>
        <div>
            <label>Category</label>
            <select name="categoryID" >
                <c:forEach items="${allCategories}" var="category" begin = "0" varStatus="loopCategory">
                    <option value="${category.id}" >${category.description}</option>
                </c:forEach>

            </select>
        </div>

        <button type="submit" > Create question</button>
    </form>
</div>
</body>
</html>
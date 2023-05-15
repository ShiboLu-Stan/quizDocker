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
        function chooseCategory(id){
            var url="/adminCategoryID";
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
        function chooseUser(id){
            var url="/adminUserID";
            var tempForm = document.createElement("form");
            tempForm.id = "chooseUser";
            tempForm.method = "post";
            tempForm.action = url;
            tempForm.target=""; //打开新页面
            var hideInput1 = document.createElement("input");
            hideInput1.type = "hidden";
            hideInput1.name="userID"; //后台要接受这个参数来取值
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

        function changeUserStatus(id){
            var url="/changeUserStatus";
            var tempForm = document.createElement("form");
            tempForm.id = "changeUserStatus";
            tempForm.method = "post";
            tempForm.action = url;
            tempForm.target=""; //打开新页面
            var hideInput1 = document.createElement("input");
            hideInput1.type = "hidden";
            hideInput1.name="userID"; //后台要接受这个参数来取值
            hideInput1.value = id; //后台实际取到的值
            tempForm.appendChild(hideInput1);

            //将tempForm 表单添加到 documentbody之后
            document.body.appendChild(tempForm);
            tempForm.submit();
            document.body.removeChild(tempForm);
        }
        function userProfile(id){
            var url="/userProfile";
            var tempForm = document.createElement("form");
            tempForm.id = "userProfile";
            tempForm.method = "post";
            tempForm.action = url;
            tempForm.target=""; //打开新页面
            var hideInput1 = document.createElement("input");
            hideInput1.type = "hidden";
            hideInput1.name="userID"; //后台要接受这个参数来取值
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


    <strong>All quiz results</strong>
    <table style="border:1px solid black">
        <thead>
        <tr>
            <th style="border:1px solid black">Taken_Date</th>
            <th style="border:1px solid black">Category</th>
            <th style="border:1px solid black">User Full Name</th>
            <th style="border:1px solid black"> No. of question</th>
            <th style="border:1px solid black">Score</th>
        </tr>
        </thead>
        <tbody>

        <%

            if (session != null && session.getAttribute("user") != null) {
                List<Quiz> quizzes = (List<Quiz>) session.getAttribute("allQuizzes");
                List<Category> quizCategories = (List<Category>) session.getAttribute("quizCategories");
                List<User> quizUsers = (List<User>) session.getAttribute("quizUsers");
                List<int[]> quizQuestions = (List<int[]>) session.getAttribute("quizQuestions");

                System.out.println(quizzes);
                System.out.println(quizCategories);
                System.out.println(quizUsers);
                System.out.println(quizQuestions);
                for (int i = 0; i < quizzes.size(); i++){
                    out.println("<tr>");
                    out.println("<th style=\"border:1px solid black\" onclick=\"chooseQuiz(" + quizzes.get(i).getId() + ")\">" + quizzes.get(i).getDateTaken() + "</th>");
                    out.println("<th style=\"border:1px solid black\" onclick=\"chooseCategory(" + quizCategories.get(i).getId() + ")\">" + quizCategories.get(i).getDescription() + "</th>");
                    out.println("<th style=\"border:1px solid black\" onclick=\"chooseUser(" + quizUsers.get(i).getId() + ")\">" + quizUsers.get(i).getFirstName() + " " + quizUsers.get(i).getLastName() + "</th>");
                    out.println("<th style=\"border:1px solid black\" onclick=\"chooseQuiz(" + quizzes.get(i).getId() + ")\">" + Arrays.toString(quizQuestions.get(i)) + "</th>");
                    out.println("<th style=\"border:1px solid black\" onclick=\"chooseQuiz(" + quizzes.get(i).getId() + ")\">" + quizzes.get(i).getScore() + "</th>");
                    out.println("<tr>");
                }
            } else {

                out.print("");
            }
        %>
        </tbody>
    </table>

    <strong>All users</strong>
    <table style="border:1px solid black">
        <thead>
        <tr>
            <th style="border:1px solid black">First Name</th>
            <th style="border:1px solid black">Last Name</th>
            <th style="border:1px solid black">Primary Address</th>
            <th style="border:1px solid black">Primary Email</th>
            <th style="border:1px solid black">Primary Phone number</th>
            <th style="border:1px solid black">Status</th>
        </tr>
        </thead>
        <tbody>
        <%

            if (session != null && session.getAttribute("user") != null) {
                List<User> allUsers = (List<User>) session.getAttribute("allUsers");

                System.out.println("allUsers:\n" + allUsers);
                for (int i = 0; i < allUsers.size(); i++){
                    User eachUser = allUsers.get(i);
                    String clickView = eachUser.getStatus().equals(Status.Active.toString()) ? "onclick=\"userProfile(" + allUsers.get(i).getId() + ")\"":"";
                    String clickChangeStatus = "onclick=\"changeUserStatus(" + allUsers.get(i).getId() + ")\"";
                    String buttonText = eachUser.getStatus().equals(Status.Active.toString()) ? Status.Suspend.toString().toUpperCase(): Status.Active.toString().toUpperCase();
                    out.println("<tr >");
                    out.println("<th style=\"border:1px solid black\"" + clickView +">"+ eachUser.getFirstName() + "</th>");
                    out.println("<th style=\"border:1px solid black\"" + clickView +">" + eachUser.getLastName() + "</th>");
                    out.println("<th style=\"border:1px solid black\"" + clickView +">" + eachUser.getAddress() + "</th>");
                    out.println("<th style=\"border:1px solid black\"" + clickView +">" + eachUser.getEmail() + "</th>");
                    out.println("<th style=\"border:1px solid black\"" + clickView +">" + eachUser.getPhoneNumber() + "</th>");
                    out.println("<th style=\"border:1px solid black\"" + clickView +">" + eachUser.getStatus() + "</th>");
                    out.println("<th style=\"border:1px solid black\">" + "<button "+ clickChangeStatus +" >" + buttonText + "</button>" + "</th>");
                    out.println("<tr>");
                }
            } else {

                out.print("");
            }
        %>
        </tbody>
    </table>

    <a href="/editQuestions" style="display: block"><button style="margin: 20px 0;"> Edit questions</button></a>

    <strong>All Feedback</strong>
    <table style="border:1px solid black">
        <thead>
        <tr>
            <th style="border:1px solid black">FeedbackDate</th>
            <th style="border:1px solid black">Star</th>
            <th style="border:1px solid black">Feedback</th>
        </tr>
        </thead>
        <tbody>
        <%
            if (session != null && session.getAttribute("user") != null) {
                List<Feedback> allFeedbacks = (List<Feedback>) session.getAttribute("allFeedbacks");

                System.out.println("allFeedbacks:\n" + allFeedbacks);
                for (int i = 0; i < allFeedbacks.size(); i++){
                    Feedback eachFeedback = allFeedbacks.get(i);
                    out.println("<tr >");
                    out.println("<th style=\"border:1px solid black\">"+ eachFeedback.getFeedBackDate() + "</th>");
                    out.println("<th style=\"border:1px solid black\">"+ eachFeedback.getStar() + "</th>");
                    out.println("<th style=\"border:1px solid black\">"+ eachFeedback.getText() + "</th>");
                    out.println("<tr>");
                }
            } else {

                out.print("");
            }
        %>
        </tbody>
    </table>

    <strong>All Contact</strong>
    <table style="border:1px solid black">
        <thead>
        <tr>
            <th style="border:1px solid black">FeedbackDate</th>
            <th style="border:1px solid black">UserID</th>
            <th style="border:1px solid black">Feedback</th>
        </tr>
        </thead>
        <tbody>
        <%
            if (session != null && session.getAttribute("user") != null) {
                List<Contact> allContacts = (List<Contact>) session.getAttribute("allContacts");

                System.out.println("allContacts:\n" + allContacts);
                for (int i = 0; i < allContacts.size(); i++){
                    Contact eachContact = allContacts.get(i);
                    out.println("<tr >");
                    out.println("<th style=\"border:1px solid black\">"+ eachContact.getContactDate() + "</th>");
                    out.println("<th style=\"border:1px solid black\">"+ eachContact.getUserID() + "</th>");
                    out.println("<th style=\"border:1px solid black\">"+ eachContact.getText() + "</th>");
                    out.println("<tr>");
                }
            } else {

                out.print("");
            }
        %>
        </tbody>
    </table>

</div>
</body>
</html>
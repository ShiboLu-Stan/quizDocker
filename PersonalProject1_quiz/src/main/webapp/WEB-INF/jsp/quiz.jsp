<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Quiz</title>
    <jsp:include page="navBar.jsp" />

    <script>
        let quizCategory = ${quizCategory.timeNeed}
        window.setInterval(function(){

            quizCategory --;

            let seconds = quizCategory;
            let mins = Math.floor(seconds/60);
            seconds = seconds%60;
            mins += '';
            seconds += '';
            mins = (mins.length==1)?'0'+mins:mins;
            seconds = (seconds.length==1)?'0'+seconds:seconds;
            // time set
            let timeP = document.getElementById("timeLimit");
            timeP.innerText = mins+':'+seconds;
            if(quizCategory == 0){
                alert("Time is up, Submitted automatically.");
                let quizSubmitButton = document.getElementById('quizSubmitButton');
                quizSubmitButton.click();
            }
            document.getElementById("showTimes").innerHTML = toDays ();
        }, 1000);
    </script>
</head>

<body>
<div>
    <div >Time Limit: <span id="timeLimit"></span></div>

    <form method="post" action="/quizSubmit">

        <%-- Dynamically render the questions --%>
        <c:forEach items="${usedQuestionList}" var="usedQuestion" begin = "0" varStatus="loopQuestion">

            <p>${usedQuestion.question}</p>
            <%-- Dynamically render the choices --%>
            <c:forEach items="${usedQuestion.options}" var="option" begin = "0" varStatus="loopOption">
                <div>
                    <input type="checkbox"
                           name="selectedChoiceId${loopQuestion.index}"
                           value="${loopOption.index}"/>
                        ${option}
                </div>
            </c:forEach>
        </c:forEach>

        <%-- Question description --%>


        <%-- Button to submit quiz --%>
        <button type="submit" id = "quizSubmitButton">submit</button>

    </form>
</div>
</body>
</html>

package com.personalproject1.quiz.service;

import com.personalproject1.quiz.dao.CategoryDao;
import com.personalproject1.quiz.dao.QuestionDao;
import com.personalproject1.quiz.dao.QuizDao;
import com.personalproject1.quiz.dao.UsedQuestionDao;
import com.personalproject1.quiz.domain.Question;
import com.personalproject1.quiz.domain.Quiz;
import com.personalproject1.quiz.domain.UsedQuestion;
import com.personalproject1.quiz.domain.User;
import com.personalproject1.quiz.domain.enums.Role;
import com.personalproject1.quiz.domain.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuizService {
    private final QuizDao quizDao;
    private final QuestionDao questionDao;
    private final UsedQuestionDao usedQuestionDao;

    @Autowired
    public QuizService(QuizDao quizDao, QuestionDao questionDao, UsedQuestionDao usedQuestionDao) {
        this.quizDao = quizDao;
        this.questionDao = questionDao;
        this.usedQuestionDao = usedQuestionDao;
    }

    public Quiz createQuizByCategory(int categoryID, int userID) {
        // only contain active Question
        List<Question> listQuestion =questionDao.getQuestionbyCategory(categoryID);

        if (listQuestion == null || listQuestion.size() == 0){
            System.out.println("!!!!!!!!!!!!!!!!!No enough question!");
            return null;
        }
        List<Integer> listUsedQuestion = new ArrayList<>();
        // create UsedQuestion by each question
        for (Question question : listQuestion){
            System.out.println(question.getOptions());
            int primaryID = usedQuestionDao.createNewUsedQuestion(
                    new UsedQuestion(question.getQuestion(), question.getAnswer(), "",
                            question.getOptions(), question.getAuthorID(), question.getCategoryID(), question.getId())
            );
            listUsedQuestion.add(primaryID);
        }

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Quiz quiz = new Quiz(0, listUsedQuestion, "Category" + categoryID + " " + formatter.format(new Date()), new Date(), new Date(), userID, 0, categoryID);

        // didn't update to database
        //        int quizID = quizDao.createNewQuiz(quiz);
//        quiz.setId(quizID);
        return quiz;
    }

    public void submitQuiz(Quiz quiz, List<String> listSelected){
        int score = 0;

        for (int i = 0; i < listSelected.size(); i++){
            String userAnswer = listSelected.get(i);
            System.out.println(quiz);
            System.out.println(listSelected);
            if(userAnswer.equals(usedQuestionDao.getAndUpdateUsedQuestion(quiz.getUsedQuestions().get(i), userAnswer)) ){
                score++;
            }
        }

        quiz.setScore(score);
        quiz.setDateFinish(new Date());

        quizDao.createNewQuiz(quiz);
    }

    public Optional<Quiz> getQuizById(Integer quizID){
        return quizDao.getAllQuizzes().stream()
                .filter(q->quizID.equals(q.getId())).findAny();
    }

    public List<Quiz> getAllQuizzes(){
        return quizDao.getAllQuizzes();
    }

    public List<Quiz> getAllQuizzesByCategoryID(int categoryID){
        return quizDao.getAllQuizzes().stream()
                .filter(quiz -> quiz.getCategoryID() == categoryID)
                .collect(Collectors.toList());
    }

    public List<Quiz> getAllQuizzesByUserID(int userID){
        return quizDao.getAllQuizzes().stream()
                .filter(quiz -> quiz.getUserID() == userID)
                .collect(Collectors.toList());
    }


}

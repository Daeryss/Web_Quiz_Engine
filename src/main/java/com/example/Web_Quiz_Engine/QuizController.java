package com.example.Web_Quiz_Engine;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.*;

@RestController
public class QuizController {
    private List<Quiz> quizzes = new ArrayList<>();
    private List<Answer> answers = new ArrayList<>();
    private List<GetQuiz> getQuizs = new ArrayList<>();
    private int num = 1;

    public QuizController() {
        answers.add(new Answer(false,"Wrong answer! Try again."));
        answers.add(new Answer(true,"Congratulations, you are right!"));
    }


    @GetMapping(path = "/api/quizzes/{id}")
    public GetQuiz getQuizzes(@PathVariable int id, HttpServletResponse response){
        try {
            return getQuizs.get(id - 1);
        } catch (Exception exc) {
            throw new NotFoundRequest();
        }
    }

    @GetMapping(path = "/id")
    public List<Quiz> quizzes(){
        return quizzes;
    }

    @GetMapping(path = "/api/quizzes")
    public List<GetQuiz> getAllQuizzes(){
        return getQuizs;
    }

    @PostMapping(path = "api/quizzes")
    public GetQuiz addQuiz(@RequestBody @Valid  Quiz quiz) {
        quizzes.add(quiz);
        GetQuiz temp = new GetQuiz(num, quiz.getTitle(), quiz.getText(), quiz.getOptions());
        getQuizs.add(temp);
        num++;
        return temp;
    }
    /*
    @PostMapping(path = "api/quizzes")
    public GetQuiz setQuizzes(@RequestBody Quiz quiz) {
        if (quiz.getText().equals("") || quiz.getTitle().equals("") || quiz.getOptions().length < 2){
            throw new BadRequestResponse();
        } else {
            quizzes.add(quiz);
            GetQuiz temp = new GetQuiz(num, quiz.getTitle(), quiz.getText(), quiz.getOptions());
            getQuizs.add(temp);
            num++;
            return temp;
        }
    }

     */

    @PostMapping(path = "/api/quizzes/{id}/solve")
    public Answer getQuiz(@PathVariable int id, @RequestBody @Valid Solving solve){
        String str = null;
        for(int i = quizzes.get(id - 1).getAnswer().length-1 ; i > 0 ; i--){
            for(int j = 0 ; j < i ; j++){
                if( quizzes.get(id - 1).getAnswer()[j] > quizzes.get(id - 1).getAnswer()[j+1] ){
                    int tmp = quizzes.get(id - 1).getAnswer()[j];
                    quizzes.get(id - 1).getAnswer()[j] = quizzes.get(id - 1).getAnswer()[j+1];
                    quizzes.get(id - 1).getAnswer()[j+1] = tmp;
                }
            }
        }
        for(int i = solve.getAnswer().length-1 ; i > 0 ; i--){
            for(int j = 0 ; j < i ; j++){
                if( solve.getAnswer()[j] > solve.getAnswer()[j+1] ){
                    int tmp = solve.getAnswer()[j];
                    solve.getAnswer()[j] = solve.getAnswer()[j+1];
                    solve.getAnswer()[j+1] = tmp;
                }
            }
        }

        if (quizzes.get(id - 1).getAnswer().length != solve.getAnswer().length) {
            return answers.get(0);
        }

        for (int i = 0; i < solve.getAnswer().length; i++){
            if (quizzes.get(id - 1).getAnswer()[i] != solve.getAnswer()[i]) {
                return answers.get(0);
            }
        }
        return answers.get(1);
        /*
        if (solve.getAnswer() == quizzes.get(id - 1).getAnswer() && solve.getAnswer().length > 0) {
            System.out.println(quizzes.get(id - 1).getAnswer());
            return answers.get(1);
        } else {
            return answers.get(0);
        }

         */
    }
}

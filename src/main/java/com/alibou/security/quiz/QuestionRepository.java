
package com.alibou.security.quiz;

import com.alibou.security.quiz.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

    List<Question> findByQuiz(Quiz quiz);
}

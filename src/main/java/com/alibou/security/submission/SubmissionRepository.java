
package com.alibou.security.submission;

import com.alibou.security.quiz.Quiz;
import com.alibou.security.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubmissionRepository extends JpaRepository<Submission, Integer> {

    List<Submission> findByStudent(User student);

    List<Submission> findByQuiz(Quiz quiz);

    Optional<Submission> findByQuizAndStudent(Quiz quiz, User student);
}

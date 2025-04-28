
package com.alibou.security.quiz;

import com.alibou.security.classroom.SchoolClass;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QuizRepository extends JpaRepository<Quiz, Integer> {

    List<Quiz> findBySchoolClass(SchoolClass clazz);

    @EntityGraph(attributePaths = { "questions", "questions.options" })
    Optional<Quiz> findWithQuestionsById(Integer id);
}

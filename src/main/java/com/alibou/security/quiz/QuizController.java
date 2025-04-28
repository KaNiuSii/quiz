
package com.alibou.security.quiz;

import com.alibou.security.user.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/quizzes")
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;

    @PostMapping
    @PreAuthorize("hasRole('TEACHER') or hasRole('ADMIN')")
    public ResponseEntity<?> createQuiz(
            @AuthenticationPrincipal User teacher,
            @Valid @RequestBody CreateQuizRequest request
    ) {
        return ResponseEntity.ok(quizService.createQuiz(teacher, request));
    }
    @GetMapping("/{quizId}")
    @PreAuthorize("hasRole('STUDENT') or hasRole('TEACHER') or hasRole('ADMIN')")
    public ResponseEntity<QuizResponse> getQuiz(
            @AuthenticationPrincipal User caller,
            @PathVariable Integer quizId) throws Exception {

        return ResponseEntity.ok(quizService.getQuizById(caller, quizId));
    }
}

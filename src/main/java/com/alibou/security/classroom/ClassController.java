
package com.alibou.security.classroom;

import com.alibou.security.user.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/classes")
@RequiredArgsConstructor
public class ClassController {

    private final ClassService classService;

    @PostMapping
    @PreAuthorize("hasRole('TEACHER') or hasRole('ADMIN')")
    public ResponseEntity<?> createClass(
            @AuthenticationPrincipal User teacher,
            @Valid @RequestBody CreateClassRequest request
    ) {
        return ResponseEntity.ok(classService.createClass(teacher, request));
    }

    @PostMapping("/join")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<?> joinClass(
            @AuthenticationPrincipal User student,
            @Valid @RequestBody JoinClassRequest request
    ) {
        classService.joinClass(student, request);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<?> myClasses(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(classService.myClasses(user));
    }
}

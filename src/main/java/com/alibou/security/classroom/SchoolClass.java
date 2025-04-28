
package com.alibou.security.classroom;

import com.alibou.security.common.Auditable;
import com.alibou.security.quiz.Quiz;
import com.alibou.security.user.User;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "classes")
public class SchoolClass extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(unique = true)
    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private User teacher;

    @OneToMany(mappedBy = "schoolClass", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<ClassMember> members = new HashSet<>();

    @OneToMany(mappedBy = "schoolClass")
    @JsonManagedReference
    @Builder.Default
    private List<Quiz> quizzes = new ArrayList<>();
}


package com.alibou.security.quiz;

import com.alibou.security.classroom.SchoolClass;
import com.alibou.security.common.Auditable;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "quizzes")
public class Quiz extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id")
    @JsonBackReference
    private SchoolClass schoolClass;

    @OneToMany(mappedBy = "quiz",
           cascade = CascadeType.ALL,
           orphanRemoval = true)
    @Builder.Default
    private List<Question> questions = new ArrayList<>();
  
    private String title;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Integer duration; // minutes
}

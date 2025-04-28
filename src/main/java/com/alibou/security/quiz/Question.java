
package com.alibou.security.quiz;

import java.util.ArrayList;
import java.util.List;

import com.alibou.security.common.Auditable;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "questions")
public class Question extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;
    
    @Column(columnDefinition = "text")
    private String content;

    @OneToMany(mappedBy = "question",
           cascade = CascadeType.ALL,
           orphanRemoval = true)
    @Builder.Default
    @JsonManagedReference
    private List<QuestionOption> options = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private QuestionType questionType;

    private Integer points;
}

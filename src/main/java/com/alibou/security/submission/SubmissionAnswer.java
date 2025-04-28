
package com.alibou.security.submission;

import com.alibou.security.common.Auditable;
import com.alibou.security.quiz.Question;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "submission_answers")
public class SubmissionAnswer extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "submission_id")
    private Submission submission;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    private Integer awardedPoints;

    @Column(columnDefinition = "text")
    private String teacherComment;

    @Column(columnDefinition = "text")
    private String openEndedAnswer;
}

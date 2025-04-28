
package com.alibou.security.submission;

import com.alibou.security.common.Auditable;
import com.alibou.security.quiz.QuestionOption;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "submission_answer_options")
public class SubmissionAnswerOption extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "submission_answer_id")
    private SubmissionAnswer submissionAnswer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_option_id")
    private QuestionOption questionOption;
}

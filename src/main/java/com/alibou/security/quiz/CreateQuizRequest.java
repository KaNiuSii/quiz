
package com.alibou.security.quiz;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CreateQuizRequest {

    @NotNull  private Integer classId;
    @NotBlank private String title;

    @Future   private LocalDateTime startTime;
    @Future   private LocalDateTime endTime;

    @Min(1)   private Integer duration;   // minutes

    @NotNull
    @Size(min = 1)
    private List<QuestionRequest> questions;   // NEW
}

@Data
class QuestionRequest {

    @NotBlank private String content;
    @NotNull  private QuestionType questionType;
    @Min(0)   private Integer points;

    @NotNull
    @Size(min = 1)
    private List<QuestionOptionRequest> options;   // NEW
}

@Data
class QuestionOptionRequest {

    @NotBlank private String content;
    private boolean isCorrect;
}
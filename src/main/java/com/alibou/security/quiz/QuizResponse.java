package com.alibou.security.quiz;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class QuizResponse {
    private Integer id;
    private Integer classId;
    private String  title;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer duration;          // minutes
    private List<QuestionDto> questions;

    @Data
    public static class QuestionDto {
        private Integer id;
        private String  content;
        private QuestionType questionType;
        private Integer points;
        private List<OptionDto> options;
    }

    @Data
    public static class OptionDto {
        private Integer id;
        private String  content;
        private boolean correct;        // expose only to teachers / admins
    }
}

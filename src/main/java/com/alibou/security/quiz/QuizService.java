
package com.alibou.security.quiz;

import com.alibou.security.classroom.SchoolClassRepository;
import com.alibou.security.user.Role;
import com.alibou.security.user.User;
import lombok.RequiredArgsConstructor;

import java.nio.file.AccessDeniedException;
import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuizRepository quizRepository;
    private final SchoolClassRepository classRepository;

    @Transactional
    public QuizResponse createQuiz(User teacher, CreateQuizRequest dto) {
        var clazz = classRepository.findById(dto.getClassId())
                     .orElseThrow(() -> new IllegalArgumentException("Class not found"));
        if (!clazz.getTeacher().getId().equals(teacher.getId()))
            throw new IllegalArgumentException("You are not the teacher of this class");

        Quiz quiz = Quiz.builder()
                .schoolClass(clazz)
                .title(dto.getTitle())
                .startTime(dto.getStartTime())
                .endTime(dto.getEndTime())
                .duration(dto.getDuration())
                .build();

        dto.getQuestions().forEach(qDto -> {
            Question q = Question.builder()
                    .quiz(quiz)
                    .content(qDto.getContent())
                    .questionType(qDto.getQuestionType())
                    .points(qDto.getPoints())
                    .build();

            qDto.getOptions().forEach(oDto -> {
                QuestionOption opt = QuestionOption.builder()
                        .question(q)
                        .content(oDto.getContent())
                        .isCorrect(oDto.isCorrect())
                        .build();
                q.getOptions().add(opt);
            });

            quiz.getQuestions().add(q);
        });
        
        quizRepository.saveAndFlush(quiz);
        
        return mapToDto(quiz, Role.TEACHER);
    }

    @Transactional(readOnly = true)
    public QuizResponse getQuizById(User caller, Integer quizId) throws Exception {

        Quiz quiz = quizRepository.findWithQuestionsById(quizId)
                .orElseThrow(() -> new NotFoundException());

        if (caller.getRole() == Role.STUDENT &&
            !quiz.getSchoolClass().getMembers().contains(caller)) {
            throw new AccessDeniedException("You do not belong to this class");
        }

        return mapToDto(quiz, caller.getRole());
    }

    /* private mapper */
    private QuizResponse mapToDto(Quiz quiz, Role role) {
        QuizResponse dto = new QuizResponse();
        dto.setId(quiz.getId());
        dto.setClassId(quiz.getSchoolClass().getId());
        dto.setTitle(quiz.getTitle());
        dto.setStartTime(quiz.getStartTime());
        dto.setEndTime(quiz.getEndTime());
        dto.setDuration(quiz.getDuration());

        List<QuizResponse.QuestionDto> qDtos = quiz.getQuestions().stream().map(q -> {
            QuizResponse.QuestionDto qd = new QuizResponse.QuestionDto();
            qd.setId(q.getId());
            qd.setContent(q.getContent());
            qd.setQuestionType(q.getQuestionType());
            qd.setPoints(q.getPoints());

            List<QuizResponse.OptionDto> oDtos = q.getOptions().stream().map(o -> {
                QuizResponse.OptionDto od = new QuizResponse.OptionDto();
                od.setId(o.getId());
                od.setContent(o.getContent());
                // expose correctness only for teachers / admins
                if (role == Role.TEACHER || role == Role.ADMIN) {
                    od.setCorrect(o.isCorrect());
                }
                return od;
            }).toList();

            qd.setOptions(oDtos);
            return qd;
        }).toList();

        dto.setQuestions(qDtos);
        return dto;
    }
}

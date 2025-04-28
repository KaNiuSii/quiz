
package com.alibou.security.classroom;

import com.alibou.security.user.Role;
import com.alibou.security.user.User;
import com.alibou.security.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassService {

    private final SchoolClassRepository classRepository;
    private final ClassMemberRepository memberRepository;
    private final UserRepository userRepository;

    @Transactional
    public SchoolClass createClass(User teacher, CreateClassRequest request) {
        if (!teacher.getRole().equals(Role.TEACHER) && !teacher.getRole().equals(Role.ADMIN)) {
            throw new IllegalArgumentException("Only teachers can create classes");
        }
        var clazz = SchoolClass.builder()
                .name(request.getName())
                .code(generateInviteCode())
                .teacher(teacher)
                .build();
        return classRepository.save(clazz);
    }

    @Transactional
    public void joinClass(User student, JoinClassRequest request) {
        var clazz = classRepository.findByCode(request.getCode())
                .orElseThrow(() -> new IllegalArgumentException("Invalid class code"));
        if (memberRepository.findByUserAndSchoolClass(student, clazz).isPresent()) {
            return;
        }
        var member = ClassMember.builder()
                .schoolClass(clazz)
                .user(student)
                .build();
        memberRepository.save(member);
    }

    public List<SchoolClass> myClasses(User user) {
        if (user.getRole().equals(Role.TEACHER)) {
            return classRepository.findAll()
                    .stream()
                    .filter(c -> c.getTeacher().getId().equals(user.getId()))
                    .toList();
        }
        var memberships = memberRepository.findByUser(user);
        return memberships.stream()
                .map(ClassMember::getSchoolClass)
                .toList();
    }

    private String generateInviteCode() {
        return java.util.UUID.randomUUID().toString().substring(0, 8);
    }
}

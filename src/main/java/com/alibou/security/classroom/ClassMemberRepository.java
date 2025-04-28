
package com.alibou.security.classroom;

import com.alibou.security.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClassMemberRepository extends JpaRepository<ClassMember, Integer> {

    List<ClassMember> findByUser(User user);

    Optional<ClassMember> findByUserAndSchoolClass(User user, SchoolClass clazz);
}

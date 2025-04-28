
package com.alibou.security.classroom;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SchoolClassRepository extends JpaRepository<SchoolClass, Integer> {

    Optional<SchoolClass> findByCode(String code);
}

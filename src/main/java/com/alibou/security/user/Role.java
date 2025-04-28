package com.alibou.security.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.alibou.security.user.Permission.*;

@RequiredArgsConstructor
public enum Role {

    ADMIN(
            Set.of(
                    ADMIN_READ,   ADMIN_UPDATE, ADMIN_DELETE, ADMIN_CREATE,
                    MANAGER_READ, MANAGER_UPDATE, MANAGER_DELETE, MANAGER_CREATE,
                    TEACHER_READ, TEACHER_UPDATE, TEACHER_DELETE, TEACHER_CREATE
            )
    ),
    MANAGER(
            Set.of(
                    MANAGER_READ, MANAGER_UPDATE, MANAGER_DELETE, MANAGER_CREATE
            )
    ),
    TEACHER(
            Set.of(
                    TEACHER_READ, TEACHER_UPDATE, TEACHER_DELETE, TEACHER_CREATE
            )
    ),
    STUDENT(
            Set.of(
                    STUDENT_READ, STUDENT_UPDATE
            )
    );

    @Getter
    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = permissions.stream()
                .map(p -> new SimpleGrantedAuthority(p.getPermission()))
                .collect(Collectors.toList());

        authorities.add(new SimpleGrantedAuthority("ROLE_" + name()));
        return authorities;
    }
}

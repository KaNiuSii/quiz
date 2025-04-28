
package com.alibou.security.classroom;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class JoinClassRequest {
    @NotBlank
    private String code;
}

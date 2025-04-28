
package com.alibou.security.classroom;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateClassRequest {
    @NotBlank
    private String name;
}

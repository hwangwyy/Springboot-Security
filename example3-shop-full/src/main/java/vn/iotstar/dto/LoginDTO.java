package vn.iotstar.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/** Documentation DTO: the real form login is processed by Spring Security (fields: username, password). */
@Data
public class LoginDTO {
    @NotBlank
    private String username;

    @NotBlank
    private String password;
}

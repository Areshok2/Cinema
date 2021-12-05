package lpnu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lpnu.entity.enumeration.UserRole;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    @NotBlank
    private String firstname;
    @NotBlank
    private String lastname;
    @Email
    private String email;
    @NotBlank
    private String number;
    @NotBlank
    private UserRole role;
}

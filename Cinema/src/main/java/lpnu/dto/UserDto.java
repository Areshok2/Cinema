package lpnu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lpnu.entity.enumeration.UserRole;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data /*Geter Seter equals hashcode tostring*/
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
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

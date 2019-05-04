package ademsalih.softwarearch.frontend.viewmodel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class SignUpUser {

    @NotBlank(message = "What is your name?")
    @Size(max = 50, message = "Max 50 characters")
    private String name;

    @NotBlank(message = "Email required")
    @Email(message = "That's not a valid email")
    private String email;

    @NotBlank(message = "Username required")
    @Size(max = 15, message = "Max 15 characters")
    private String userName;

    @Size(min = 6, message = "Too short")
    private String password;

    public SignUpUser(String name, String email, String userName, String password) {
        this.name = name;
        this.email = email;
        this.userName = userName;
        this.password = password;
    }
}

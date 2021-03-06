package ademsalih.softwarearch.frontend.viewmodel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class AccountEditUser {

    @NotBlank(message = "What is your name?")
    @Size(max = 50, message = "Max 50 characters")
    private String name;

    @NotBlank(message = "Email required")
    @Email
    private String email;

    @NotBlank(message = "Username required")
    @Size(max = 15, message = "Max 15 characters")
    private String userName;

    public AccountEditUser(String name, String email,String userName) {
        this.name = name;
        this.email = email;
        this.userName = userName;
    }
}

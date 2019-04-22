package ademsalih.softwarearch.frontend.viewmodel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class PasswordEditUser {

    @Size(min = 6, message = "Too short")
    private String password;

    public PasswordEditUser(String password) {
        this.password = password;
    }
}

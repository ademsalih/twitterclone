package ademsalih.softwarearch.frontend.viewmodel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class OtherEditUser {

    private String phone;

    @Size(max = 160, message = "Max 160 characters!")
    private String bio;

    private String link;

    private String location;

    public OtherEditUser(String phone,String bio, String link, String location) {
        this.phone = phone;
        this.bio = bio;
        this.link = link;
        this.location = location;
    }
}

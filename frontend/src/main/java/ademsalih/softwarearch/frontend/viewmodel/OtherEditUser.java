package ademsalih.softwarearch.frontend.viewmodel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class OtherEditUser {

    @Pattern(regexp = "|\\d+|[+]?\\d+",message = "That's not a phone number")
    private String phone;

    @Size(max = 160, message = "Max 160 characters")
    private String bio;


    @Pattern(
            regexp = "|https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)",
    message = "That's not a valid URL")
    private String link;

    private String location;

    public OtherEditUser(String phone,String bio, String link, String location) {
        this.phone = phone;
        this.bio = bio;
        this.link = link;
        this.location = location;
    }
}

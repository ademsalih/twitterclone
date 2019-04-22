package ademsalih.softwarearch.frontend.viewmodel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
public class PictureEditUser {

    private MultipartFile profileImageName;

    private MultipartFile bannerImageName;

    public PictureEditUser(MultipartFile profileImageName, MultipartFile bannerImageName) {
        this.profileImageName = profileImageName;
        this.bannerImageName = bannerImageName;
    }
}

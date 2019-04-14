package ademsalih.softwarearch.frontend.service;

import ademsalih.softwarearch.frontend.model.FollowStatus;
import ademsalih.softwarearch.frontend.model.Tweet;
import ademsalih.softwarearch.frontend.model.User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    String BASE_URL = "http://localhost:9092/users";
    private RestTemplate restTemplate = new RestTemplate();

    public List<User> getUsers(){
        return  Arrays.stream(
                restTemplate.getForObject(BASE_URL, User[].class)
        ).collect(Collectors.toList());
    }

    public User getUserById(long id){
        return restTemplate.getForObject(BASE_URL + "/" + id, User.class);
    }
}

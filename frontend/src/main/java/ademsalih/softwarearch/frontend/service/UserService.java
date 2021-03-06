package ademsalih.softwarearch.frontend.service;

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

    public User registerUser(User user) {
        return restTemplate.postForObject(BASE_URL, user, User.class);
    }

    public void updateUser(User user) {
        restTemplate.put(BASE_URL + "/" + user.getUser_id(), user);
    }

    public User getUserByUsername(String username){
        return restTemplate.getForObject(BASE_URL + "/username/" + username, User.class);
    }

    public User getUserByEmail(String email){
        return restTemplate.getForObject(BASE_URL + "/email/" + email, User.class);
    }

    public void deleteUser(long user_id) {
        restTemplate.delete(BASE_URL + "/" + user_id);
    }

    public List<User> getFollowSuggestions(long id) {
        return  Arrays.stream(
                restTemplate.getForObject(BASE_URL + "/" + id + "/" + "followsuggestions", User[].class)
        ).collect(Collectors.toList());
    }
}

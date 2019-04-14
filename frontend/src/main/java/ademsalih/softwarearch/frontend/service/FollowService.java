package ademsalih.softwarearch.frontend.service;

import ademsalih.softwarearch.frontend.model.Follow;
import ademsalih.softwarearch.frontend.model.FollowStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FollowService {

    String BASE_URL = "http://localhost:9092/";
    private RestTemplate restTemplate = new RestTemplate();

    public List<Follow> getFollowersForUserById(long id){
        return  Arrays.stream(
                restTemplate.getForObject(BASE_URL + "followers" + "/" + id, Follow[].class)
        ).collect(Collectors.toList());
    }

    public List<Follow> getFollowingsForUserById(long id){
        return  Arrays.stream(
                restTemplate.getForObject(BASE_URL + "followings" + "/" + id, Follow[].class)
        ).collect(Collectors.toList());
    }

    public FollowStatus getFollowingStatus(long user_id, long following_id) {
        return restTemplate.getForObject(BASE_URL + "following" + "/" + user_id + "/" + following_id, FollowStatus.class);
    }

    public Follow registerFollow(Follow follow) {
        return restTemplate.postForObject(BASE_URL + "/" + "following", follow, Follow.class);
    }

    public void deleteFollow(long user_id, long following_id) {
        restTemplate.delete(BASE_URL + "/following/" + user_id + "/" + following_id);
    }
}

package ademsalih.softwarearch.frontend.service;

import ademsalih.softwarearch.frontend.model.Follow;
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
}

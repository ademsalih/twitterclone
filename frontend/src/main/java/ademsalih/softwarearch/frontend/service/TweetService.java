package ademsalih.softwarearch.frontend.service;

import ademsalih.softwarearch.frontend.model.Tweet;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TweetService {

    String BASE_URL = "http://localhost:9090/";
    private RestTemplate restTemplate = new RestTemplate();

    public List<Tweet> getFeed(){
        return  Arrays.stream(
                restTemplate.getForObject(BASE_URL + "feed", Tweet[].class)
        ).collect(Collectors.toList());
    }
}

package ademsalih.softwarearch.tweetservice.service;

import ademsalih.softwarearch.tweetservice.model.Retweet;

import ademsalih.softwarearch.tweetservice.repository.RetweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RetweetServiceImplementation implements RetweetService {

    @Autowired
    RetweetRepository retweetRepository;

    @Override
    public List<Retweet> getAllRetweets() {
        return retweetRepository.findAll();
    }

    @Override
    public List<Retweet> getRetweetsForUser(long id) {
        List<Retweet> allRetweets = retweetRepository.findAll();
        List<Retweet> userRetweets = new ArrayList<>();

        for (Retweet r : allRetweets) {
            if (r.getUser_id() == id) userRetweets.add(r);
        }
        return userRetweets;
    }
}

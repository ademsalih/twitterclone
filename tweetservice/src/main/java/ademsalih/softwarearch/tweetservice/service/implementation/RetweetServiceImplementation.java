package ademsalih.softwarearch.tweetservice.service.implementation;

import ademsalih.softwarearch.tweetservice.model.Retweet;

import ademsalih.softwarearch.tweetservice.repository.RetweetRepository;
import ademsalih.softwarearch.tweetservice.service.RetweetService;
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

    @Override
    public Retweet getRetweet(long id) {
        return retweetRepository.findById(id).get();
    }

    @Override
    public void deleteRetweet(long id) {
        retweetRepository.deleteById(id);
    }
}

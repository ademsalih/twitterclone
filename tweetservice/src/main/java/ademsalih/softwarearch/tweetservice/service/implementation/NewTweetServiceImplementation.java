package ademsalih.softwarearch.tweetservice.service.implementation;

import ademsalih.softwarearch.tweetservice.model.NewTweet;
import ademsalih.softwarearch.tweetservice.model.Tweet;
import ademsalih.softwarearch.tweetservice.repository.NewTweetRepository;
import ademsalih.softwarearch.tweetservice.service.NewTweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NewTweetServiceImplementation implements NewTweetService {

    @Autowired
    NewTweetRepository newTweetRepository;

    @Override
    public List<NewTweet> getAllNewTweets() {
        return newTweetRepository.findAll();
    }

    @Override
    public List<NewTweet> getNewTweetsForUser(long id) {
        List<NewTweet> allNewTweets = newTweetRepository.findAll();
        List<NewTweet> userNewTweets = new ArrayList<>();

        for (NewTweet t : allNewTweets) {
            if (t.getUser_id() == id) userNewTweets.add(t);
        }

        return userNewTweets;
    }

    @Override
    public NewTweet getNewTweet(long id) {
        return newTweetRepository.findById(id).get();
    }

    @Override
    public NewTweet saveNewTweet(NewTweet newTweet) {
        return newTweetRepository.save(newTweet);
    }

    @Override
    public void deleteNewTweet(long id) {
        newTweetRepository.deleteById(id);
    }

    @Override
    public List<NewTweet> getTweetForSearch(String query) {




        return null;
    }


}

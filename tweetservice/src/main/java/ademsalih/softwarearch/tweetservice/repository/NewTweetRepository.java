package ademsalih.softwarearch.tweetservice.repository;

import ademsalih.softwarearch.tweetservice.model.NewTweet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewTweetRepository extends JpaRepository<NewTweet, Long> {

    List<NewTweet> findNewTweetsByUser(long id);

}

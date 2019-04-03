package ademsalih.softwarearch.tweetservice.repository;

import ademsalih.softwarearch.tweetservice.model.NewTweet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewTweetRepository extends JpaRepository<NewTweet, Long> {
}

package ademsalih.softwarearch.tweetservice.repository;

import ademsalih.softwarearch.tweetservice.model.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TweetRepository extends JpaRepository<Tweet, Long> {
}

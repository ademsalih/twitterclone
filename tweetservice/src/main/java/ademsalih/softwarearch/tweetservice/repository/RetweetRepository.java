package ademsalih.softwarearch.tweetservice.repository;

import ademsalih.softwarearch.tweetservice.model.Retweet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RetweetRepository extends JpaRepository<Retweet, Long> {
}

package ademsalih.softwarearch.userservice.service;

import ademsalih.softwarearch.userservice.model.Follow;
import ademsalih.softwarearch.userservice.repository.FollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowServiceImplementation implements FollowService {

    @Autowired
    FollowRepository followRepository;

    @Override
    public void saveFollowing(Follow follow) {
        followRepository.save(follow);
    }
}

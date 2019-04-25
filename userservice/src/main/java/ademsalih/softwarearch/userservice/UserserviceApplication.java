package ademsalih.softwarearch.userservice;

import ademsalih.softwarearch.userservice.model.Follow;
import ademsalih.softwarearch.userservice.model.User;
import ademsalih.softwarearch.userservice.model.UserRoles;
import ademsalih.softwarearch.userservice.repository.FollowRepository;
import ademsalih.softwarearch.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Calendar;

@SpringBootApplication
public class UserserviceApplication implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    FollowRepository followRepository;

    public static void main(String[] args) {
        SpringApplication.run(UserserviceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        userRepository.deleteAllInBatch();
        followRepository.deleteAllInBatch();

        User adem = new User(
                "Adem Salih",
                "adem@gmail.com",
                "93689146",
                "adem",
                "123456",
                Calendar.getInstance(),
                "profile1.png",
                UserRoles.USER.name(),
                "My name is Adem and I'm 21 years old. Follow 4 follow. Retweeting anything and everything.",
                "adem-banner.jpg",
                "http://www.google.com",
                "Berlin, Germany"
        );

        User pinar = new User(
                "Pinar Salih",
                "pinar@gmail.com",
                "93689144",
                "pinarsalihgs",
                "123456",
                Calendar.getInstance(),
                "profile2.png",
                UserRoles.USER.name(),
                "My name is Pinar and I'm 22 years old.",
                "pinar-banner.jpg",
                "http://www.google.com",
                "Oslo, Norway"
        );

        User esra = new User(
                "Esra Salih",
                "pinar@gmail.com",
                "93897123",
                "esra123",
                "123456",
                Calendar.getInstance(),
                "profile3.png",
                UserRoles.USER.name(),
                "My name is Esra and I'm 17 years old. I love munching on pancakes.",
                "esra-banner.jpg",
                "http://www.google.com",
                "London, UK"
        );

        userRepository.save(adem);
        userRepository.save(pinar);
        userRepository.save(esra);

        followRepository.save(new Follow(esra, adem));
        followRepository.save(new Follow(adem, esra));
        followRepository.save(new Follow(esra, pinar));




    }

}
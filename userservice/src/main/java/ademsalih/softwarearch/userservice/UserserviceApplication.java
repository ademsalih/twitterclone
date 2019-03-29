package ademsalih.softwarearch.userservice;

import ademsalih.softwarearch.userservice.model.User;
import ademsalih.softwarearch.userservice.model.UserRoles;
import ademsalih.softwarearch.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserserviceApplication implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(UserserviceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        userRepository.deleteAllInBatch();

        User adem = new User(
                "Adem",
                "Salih",
                "adem@gmail.com",
                "93689146",
                "adem123",
                "test",
                "26.03.2019",
                "imageName.jpg",
                UserRoles.USER.name());

        User pinar = new User(
                "Pinar",
                "Salih",
                "pinar@gmail.com",
                "93689144",
                "pinarsalihgs",
                "test",
                "26.03.2019",
                "imageName2.jpg",
                UserRoles.USER.name());


        pinar.getFollowers().add(adem);
        adem.getFollowers().add(pinar);


        adem.getFollowing().add(pinar);
        pinar.getFollowing().add(adem);

        userRepository.save(pinar);
        userRepository.save(adem);


    }

}
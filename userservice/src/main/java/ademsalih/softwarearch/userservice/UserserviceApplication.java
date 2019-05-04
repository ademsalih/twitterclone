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

        User admin = new User(
                "Admin",
                "admin@gmail.com",
                "",
                "admin",
                "$2a$10$JtTL/NrpgT57V7.BmmvIzOWxEmM8FAOeUgIRBPsIz/qxQh6QMA8gO",
                Calendar.getInstance(),
                "admin.png",
                UserRoles.ADMIN.name(),
                "",
                "admin-banner.jpg",
                "",
                ""
        );

        User adem = new User(
                "Adem Salih",
                "adem@gmail.com",
                "93689146",
                "adem",
                "$2a$10$.BFA1vQy.nwyE.dDhAdVZ.VWNVZV1jiEFo0V.dp1JBQXJDldvzE.e",
                Calendar.getInstance(),
                "adem.png",
                UserRoles.USER.name(),
                "My name is Adem and I'm 21 years old. I made this site.",
                "adem-banner.jpg",
                "http://www.google.com",
                "Berlin, Germany"
        );


        User jensStoltenberg = new User(
                "Jens Stoltenberg",
                "jens@nato.com",
                "98234233",
                "jensstoltenberg",
                "$2a$10$KdEEwACKxjWWD0wnt1ZuDeh0OTHOzcjLNC2x9RusSFaLwfu/E5..q",
                Calendar.getInstance(),
                "jensstoltenberg.jpg",
                UserRoles.USER.name(),
                "",
                "jensstoltenberg-banner.jpeg",
                "http://www.nato.int",
                "Berlin, Germany"
        );


        User ernaSolberg = new User(
                "Erna Solberg",
                "erna@outlook.com",
                "93248223",
                "erna_solberg",
                "$2a$10$B0lSL6krJNf7YjhyQNb82u/JivDMIt90FmGgc6z/kTQP.XHSNS06u",
                Calendar.getInstance(),
                "ernasolberg.jpg",
                UserRoles.USER.name(),
                "Statsminister i Norge. Jobber for et samfunn med muligheter for alle. For saker til forvaltningen, henvend deg til rette myndighet for saksbehandling.",
                "ernasolberg-banner.jpg",
                "http://www.erna.no",
                "Hordaland, Bergen"
        );

        User donaldTrump = new User(
                "Donald J. Trump",
                "don@outlook.com",
                "392342345",
                "realDonaldTrump",
                "$2a$10$T0w6GsEl813GwfUAx4Q8Uuo1VIsZhVEzea4FgwS0.Pa6KPPIDkN1y",
                Calendar.getInstance(),
                "donaldtrump.jpg",
                UserRoles.USER.name(),
                "45th President of the United States of America\uD83C\uDDFA\uD83C\uDDF8",
                "donaldtrump-banner.jpeg",
                "https://www.instagram.com/realDonaldTrump/",
                "Washington, DC"
        );

        User obama = new User(
                "Barack Obama",
                "obama@outlook.com",
                "02391244",
                "BarackObama",
                "$2a$10$ygM3YSnGk.MmXAoxKhPsaevzk6K08IPYZDOVN2tkcNIna1HnKkP9W",
                Calendar.getInstance(),
                "obama.jpg",
                UserRoles.USER.name(),
                "Dad, husband, President, citizen.",
                "obama-banner.jpeg",
                "https://www.obama.org",
                "Washington, DC"
        );



        userRepository.save(admin);
        userRepository.save(adem);
        userRepository.save(jensStoltenberg);
        userRepository.save(ernaSolberg);
        userRepository.save(donaldTrump);
        userRepository.save(obama);



        followRepository.save(new Follow(adem, jensStoltenberg));
        followRepository.save(new Follow(adem, ernaSolberg));
        followRepository.save(new Follow(adem, donaldTrump));

        followRepository.save(new Follow(jensStoltenberg, donaldTrump));
        followRepository.save(new Follow(jensStoltenberg, adem));
        followRepository.save(new Follow(jensStoltenberg, ernaSolberg));

        followRepository.save(new Follow(ernaSolberg, donaldTrump));









    }

}
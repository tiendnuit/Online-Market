package edu.miu.cs545.group5.onlinemarket.config;

import edu.miu.cs545.group5.onlinemarket.domain.Address;
import edu.miu.cs545.group5.onlinemarket.domain.User;
import edu.miu.cs545.group5.onlinemarket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class DataLoader implements ApplicationRunner {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    @Autowired
    public DataLoader(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void run(ApplicationArguments args) {
        Address address = new Address("407 N 6 Street", "Fairfield", "IO", "52556");
        User admin = new User("Tien", "Doan",
                "admin@gmail.com", "111-222-3333",
                LocalDate.parse("1987-10-27"), passwordEncoder.encode("123456"),
                address, Constants.ROLE_ADMIN, 1);

        User seller1 = new User("Walmart", "Walmart",
                "walmart@gmail.com", "222-333-4444",
                LocalDate.parse("1950-10-27"), passwordEncoder.encode("123456"),
                address, Constants.ROLE_SELLER, 1);
        User seller2 = new User("Amazon", "Amazon",
                "amazon@gmail.com", "333-444-5555",
                LocalDate.parse("1995-10-27"), passwordEncoder.encode("123456"),
                address, Constants.ROLE_SELLER, 1);
        User seller3 = new User("Best Buy", "Best Buy",
                "bestbuy@gmail.com", "333-444-5555",
                LocalDate.parse("1995-10-27"), passwordEncoder.encode("123456"),
                address, Constants.ROLE_SELLER, 1);
        User seller4 = new User("Apple", "Apple",
                "apple@gmail.com", "333-444-5555",
                LocalDate.parse("1976-10-27"), passwordEncoder.encode("123456"),
                address, Constants.ROLE_SELLER, 1);

        userRepository.save(admin);
        userRepository.save(seller1);
        userRepository.save(seller2);
        userRepository.save(seller3);
        userRepository.save(seller4);
    }
}
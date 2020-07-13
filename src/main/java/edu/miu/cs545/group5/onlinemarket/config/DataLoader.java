package edu.miu.cs545.group5.onlinemarket.config;

import edu.miu.cs545.group5.onlinemarket.domain.Address;
import edu.miu.cs545.group5.onlinemarket.domain.Category;
import edu.miu.cs545.group5.onlinemarket.domain.Seller;
import edu.miu.cs545.group5.onlinemarket.domain.User;
import edu.miu.cs545.group5.onlinemarket.repository.CategoryRepository;
import edu.miu.cs545.group5.onlinemarket.repository.UserRepository;
import edu.miu.cs545.group5.onlinemarket.service.CategoryService;
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
    private CategoryRepository categoryRepository;
    @Autowired
    public DataLoader(UserRepository userRepository, PasswordEncoder passwordEncoder, CategoryRepository categoryRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.categoryRepository = categoryRepository;
    }

    public void run(ApplicationArguments args) {
        Address address = new Address("407 N 6 Street", "Fairfield", "IO", "52556");
        User admin = new User("Tien", "Doan",
                "admin@gmail.com", "111-222-3333",
                LocalDate.parse("1987-10-27"), passwordEncoder.encode("123456"),
                address, Constants.ROLE_ADMIN, 1);

        User seller1 = new Seller("Walmart", "Walmart",
                "walmart@gmail.com", "222-333-4444",
                LocalDate.parse("1950-10-27"), passwordEncoder.encode("123456"),
                address, Constants.ROLE_SELLER, 1, true);
        User seller2 = new Seller("Amazon", "Amazon",
                "amazon@gmail.com", "333-444-5555",
                LocalDate.parse("1995-10-27"), passwordEncoder.encode("123456"),
                address, Constants.ROLE_SELLER, 1, false);
        User seller3 = new Seller("Best Buy", "Best Buy",
                "bestbuy@gmail.com", "333-444-5555",
                LocalDate.parse("1995-10-27"), passwordEncoder.encode("123456"),
                address, Constants.ROLE_SELLER, 1, false);
        User seller4 = new Seller("Apple", "Apple",
                "apple@gmail.com", "333-444-5555",
                LocalDate.parse("1976-10-27"), passwordEncoder.encode("123456"),
                address, Constants.ROLE_SELLER, 1, false);

        //Category
        Category computer = new Category("Computer", "This is Computer category");
        Category food = new Category("Food", "This is Food category");
        Category book = new Category("Book", "This is Book category");
        Category shoe = new Category("Shoe", "This is Shoe category");
        categoryRepository.save(computer);
        categoryRepository.save(food);
        categoryRepository.save(book);
        categoryRepository.save(shoe);

        userRepository.save(admin);
        userRepository.save(seller1);
        userRepository.save(seller2);
        userRepository.save(seller3);
        userRepository.save(seller4);
    }
}
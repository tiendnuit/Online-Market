package edu.miu.cs545.group5.onlinemarket.config;

import edu.miu.cs545.group5.onlinemarket.domain.*;
import edu.miu.cs545.group5.onlinemarket.repository.CategoryRepository;
import edu.miu.cs545.group5.onlinemarket.repository.ProductRepository;
import edu.miu.cs545.group5.onlinemarket.repository.UserRepository;
import edu.miu.cs545.group5.onlinemarket.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Component
public class DataLoader implements ApplicationRunner {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;

    @Autowired
    public DataLoader(UserRepository userRepository,
                      PasswordEncoder passwordEncoder,
                      CategoryRepository categoryRepository,
                      ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    public void run(ApplicationArguments args) {
        Address address = new Address("407 N 6 Street", "Fairfield", "IO", "52556");
        User admin = new User("Tien", "Doan",
                "admin@gmail.com", "111-222-3333",
                LocalDate.parse("1987-10-27"), passwordEncoder.encode("123456"),
                address, Constants.ROLE_ADMIN, 1);

        Seller seller1 = new Seller("Walmart", "Walmart",
                "walmart@gmail.com", "222-333-4444",
                LocalDate.parse("1950-10-27"), passwordEncoder.encode("123456"),
                address, Constants.ROLE_SELLER, 1, false);
        Seller seller2 = new Seller("Amazon", "Amazon",
                "amazon@gmail.com", "333-444-5555",
                LocalDate.parse("1995-10-27"), passwordEncoder.encode("123456"),
                address, Constants.ROLE_SELLER, 1, false);
        Seller seller3 = new Seller("Best Buy", "Best Buy",
                "bestbuy@gmail.com", "333-444-5555",
                LocalDate.parse("1995-10-27"), passwordEncoder.encode("123456"),
                address, Constants.ROLE_SELLER, 1, true);
        Seller seller4 = new Seller("Apple", "Apple",
                "apple@gmail.com", "333-444-5555",
                LocalDate.parse("1976-10-27"), passwordEncoder.encode("123456"),
                address, Constants.ROLE_SELLER, 1, true);

        Buyer buyer1 = new Buyer("Tien", "Doan",
                "buyer@gmail.com", "333-444-5555",
                LocalDate.parse("1976-10-27"), passwordEncoder.encode("123456"),
                address, Constants.ROLE_BUYER, 1, 100, Arrays.asList(seller1, seller3));

        Buyer buyer2 = new Buyer("Robel", "Teferi",
                "RobelBuyer@gmail.com", "123-444-5234",
                LocalDate.parse("1976-10-27"), passwordEncoder.encode("123456"),
                address, Constants.ROLE_BUYER, 0, 0, Arrays.asList(seller1, seller3));

        Buyer buyer3 = new Buyer("Wondyefraw", "Abby",
                "WondyBbuyer@gmail.com", "124-444-5555",
                LocalDate.parse("2013-10-27"), passwordEncoder.encode("123456"),
                address, Constants.ROLE_BUYER, 1, 0, Arrays.asList(seller1, seller3));

        Buyer buyer4 = new Buyer("The Yen Nougen", "Doan",
                "ThyenByer@gmail.com", "333-444-5555",
                LocalDate.parse("1976-10-27"), passwordEncoder.encode("123456"),
                address, Constants.ROLE_BUYER, 0, 0, Arrays.asList(seller1, seller3));

        Buyer buyer5 = new Buyer("Phan Anh", "Nguyen",
                "anhnguyenict@gmail.com", "333-444-5555",
                LocalDate.parse("1976-10-27"), passwordEncoder.encode("123456"),
                address, Constants.ROLE_BUYER, 1, 300, Arrays.asList(seller1, seller3));

        Buyer buyer6 = new Buyer("Tien", "Nguyen",
                "tiendnuit@gmail.com", "333-444-5555",
                LocalDate.parse("1976-10-27"), passwordEncoder.encode("123456"),
                address, Constants.ROLE_BUYER, 1, 300, Arrays.asList(seller1, seller3));

        userRepository.saveAll(Arrays.asList(admin, seller1, seller2, seller3, seller4));
        userRepository.saveAll(Arrays.asList(buyer1, buyer2, buyer3, buyer5, buyer6));

        //Category
        Category computer = new Category("Computer", "This is Computer category");
        Category food = new Category("Food", "This is Food category");
        Category book = new Category("Book", "This is Book category");
        Category shoe = new Category("Shoe", "This is Shoe category");
        categoryRepository.saveAll(Arrays.asList(computer, food, book, shoe));

        //Product
        Product product1 = new Product("HP", 100.0,
                100, "HP computer",
                "hp.png", computer, seller1);

        Product product2 = new Product("HP 2", 100.0,
                100, "HP computer",
                "hp2.jpg", computer, seller2);

        Product product3 = new Product("Java 1", 100.0,
                100, "Java Programming",
                "java.jpg", book, seller3);

        Product product4 = new Product("Java 2", 100.0,
                100, "Java Programming 2",
                "java2.jpg", book, seller4);

        //productRepository.save(product1);
        productRepository.saveAll(Arrays.asList(product1, product2, product3, product4));


    }
}
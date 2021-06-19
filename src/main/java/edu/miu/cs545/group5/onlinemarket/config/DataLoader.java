package edu.miu.cs545.group5.onlinemarket.config;

import edu.miu.cs545.group5.onlinemarket.domain.*;
import edu.miu.cs545.group5.onlinemarket.repository.CategoryRepository;

import edu.miu.cs545.group5.onlinemarket.repository.ProductRepository;
import edu.miu.cs545.group5.onlinemarket.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


import java.time.LocalDate;
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
        Product product1 = new Product("Apple 13\" MacBook Air Core i5 CPU, 8GB RAM (2017 Model 128GB)", 955.67,
                100, "1.8 GHz dual-core Intel Core i5 Processor\n" +
                "Intel HD Graphics 6000\n" +
                "Fast SSD Storage\n" +
                "8GB memory\n",
                "macbook-air-13.jpg", computer, seller4);

        Product product2 = new Product("New Apple MacBook Pro (16-Inch, 16GB RAM, 512GB Storage) - Space Gray", 2399.00,
                100, "Ninth-generation 6-Core Intel Core i7 Processor\n" +
                "Stunning 16-inch Retina Display with True Tone technology\n" +
                "Touch Bar and Touch ID\n" +
                "Amd Radeon Pro 5300M Graphics with GDDR6 memory\n",
                "macbook-pro-16.jpg", computer, seller4);

        Product product3 = new Product("HP 21.5-Inch All-in-One Computer, AMD A4-9125, ", 421.58,
                100, "The essential home computer: With an AMD processor and 4 GB of RAM, your family can seamlessly go from sending work emails to uploading vacation photos with ease\n" +
                "A centerpiece for ",
                "hp-21.jpg", computer, seller1);

        Product product4 = new Product("Java 2", 10.0,
                100, "Java Programming 1, The fact is your brain craves novelty. It's constantly searching, scanning, waiting for something unusual to happen.",
                "java2.jpg", book, seller2);

        Product product5 = new Product("Head First Java", 20.0,
                100, "Learning a complex new language is no easy task especially when it's an object-oriented computer programming language like java. You might think the problo take in the dry, ",
                "hf-java.jpg", book, seller3);

        Product product6 = new Product("Frito-Lay Flavor Mix, 28 Count", 9.98,
                100, "Frito-Lay Fun Times Mix Variety Pack provides the perfect portion size and variety to keep your entire family happy. From the pantry to the lunch box, all you have to do is grab a pack and go!  .",
                "frito.jpg", food, seller1);

        Product product7 = new Product("Fiber One 70 Calories Brownies, Chocolate Fudge, 18 Ct Mega Pack, 16 Oz", 6.76,
                100, "New recipe! Don't compromise on great taste. Fiber One Chocolate Fudge Brownies have 70 calories, 5 net carbs and 2 grams of sugar.\n" +
                "\n" +
                "TASTY INDULGENCES: Guilt-free 70 calorie chocolate fudge brownie\n" +
                "FUDGE BROWNIE: a tasty combination of rich",
                "fiber.jpg", food, seller1);

        //productRepository.save(product1);
        productRepository.saveAll(Arrays.asList(product1, product2, product3, product4, product5, product6, product7));

    }
}
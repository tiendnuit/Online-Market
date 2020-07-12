package edu.miu.cs545.group5.onlinemarket;

import edu.miu.cs545.group5.onlinemarket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OnlineMarketApplication {
	public static void main(String[] args) {
		SpringApplication.run(OnlineMarketApplication.class, args);
	}
}

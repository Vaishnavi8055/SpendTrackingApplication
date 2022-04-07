package com.incs.spendtracking;

import com.incs.spendtracking.repository.LoginDao;
//import com.incs.spendtracking.repository.ProductPurchaseRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
public class SpendtrackingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpendtrackingApplication.class, args);
	}

}

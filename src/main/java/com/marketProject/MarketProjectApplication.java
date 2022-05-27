package com.marketProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 *
 */
@ServletComponentScan
@SpringBootApplication
public class MarketProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarketProjectApplication.class, args);
	}

}

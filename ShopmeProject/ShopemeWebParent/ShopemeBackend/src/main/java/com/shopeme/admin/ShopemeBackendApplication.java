package com.shopeme.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.shopme.common.entity" ,"com.shopeme.admin.user"})
public class ShopemeBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopemeBackendApplication.class, args);
	}

}

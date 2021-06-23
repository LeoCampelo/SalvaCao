package com.ufscar.salvacao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SalvacaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalvacaoApplication.class, args);
		System.out.println(new BCryptPasswordEncoder().encode("admin"));
	}

}

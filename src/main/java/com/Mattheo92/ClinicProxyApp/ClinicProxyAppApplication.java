package com.Mattheo92.ClinicProxyApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ClinicProxyAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClinicProxyAppApplication.class, args);
	}

}

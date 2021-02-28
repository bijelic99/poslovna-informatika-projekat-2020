package org.ftn.poslovnainformatika.podsistemprodajeprojekat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class PodsistemProdajeProjekatApplication {

	public static void main(String[] args) {
		SpringApplication.run(PodsistemProdajeProjekatApplication.class, args);
	}

}

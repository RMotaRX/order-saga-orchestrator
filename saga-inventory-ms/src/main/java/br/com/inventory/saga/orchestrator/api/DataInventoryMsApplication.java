package br.com.inventory.saga.orchestrator.api;

import br.com.inventory.saga.orchestrator.api.application.utils.PropsUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(PropsUtil.class)
public class DataInventoryMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataInventoryMsApplication.class, args);
	}
}

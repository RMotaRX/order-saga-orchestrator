package br.com.product.saga.orchestrator.api;

import br.com.product.saga.orchestrator.api.application.utils.PropsUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(PropsUtil.class)
public class SagaProductMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SagaProductMsApplication.class, args);
	}
}

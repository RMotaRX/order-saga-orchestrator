package br.com.order.saga.orchestrator.api;

import br.com.order.saga.orchestrator.api.application.utils.PropsUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(PropsUtil.class)
public class SagaOrderMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SagaOrderMsApplication.class, args);
	}
}

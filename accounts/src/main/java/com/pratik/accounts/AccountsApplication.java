package com.pratik.accounts;

import com.pratik.accounts.dto.AccountsContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableFeignClients
@EnableConfigurationProperties(value = {AccountsContactInfoDto.class})
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Accounts MicroServices REST Api Documentation",
				description  = "Accounts MicroServices RESTApi Documentation",
				version = "v1",
				contact = @Contact(
						name  = "Pratik Raj",
						email = "av.pratikraj@gmail.com",
						url = ""
				),
				license = @License(
						name = "Apach 2.0",
				url =""
				)
		),
		externalDocs = @ExternalDocumentation(
				description =  "Accounts MicroServices RESTApi Documentation",
				url = ""
		))

public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}

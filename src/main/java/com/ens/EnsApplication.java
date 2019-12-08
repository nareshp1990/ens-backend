package com.ens;

import com.ens.config.content.ContentHostProperties;
import com.ens.config.content.FileStorageProperties;
import com.ens.config.content.NginxProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EntityScan("com.ens.domain.entity")
@EnableConfigurationProperties({
		FileStorageProperties.class,
		NginxProperties.class,
		ContentHostProperties.class
})
@EnableJpaRepositories(basePackages = "com.ens.repo")
@EnableJpaAuditing
@SpringBootApplication
public class EnsApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnsApplication.class, args);
	}

}

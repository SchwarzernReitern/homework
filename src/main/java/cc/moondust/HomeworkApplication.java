package cc.moondust;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(locations = {"classpath:spring/spring-core.xml"})
public class HomeworkApplication {
	public static void main(String[] args) {
		SpringApplication.run(HomeworkApplication.class, args);
	}
}

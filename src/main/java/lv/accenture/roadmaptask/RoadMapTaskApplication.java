package lv.accenture.roadmaptask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class RoadMapTaskApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(RoadMapTaskApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(RoadMapTaskApplication.class, args);
	}

}

package webpub;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HalloWeltApplication {

	static final Logger log = LoggerFactory.getLogger(HalloWeltApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(HalloWeltApplication.class, args);
		log.info("Hallo, Welt!");
	}

}

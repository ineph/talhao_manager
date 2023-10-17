package slf.talhao_manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TalhaoManagerSpringApplication {

	public static void main(String[] args) {
		System.out.println(System.getenv("DB_URL"));
		System.out.println(System.getenv("DB_PASS"));
		System.out.println(System.getenv("DB_USER"));
		SpringApplication.run(TalhaoManagerSpringApplication.class, args);
	}

}

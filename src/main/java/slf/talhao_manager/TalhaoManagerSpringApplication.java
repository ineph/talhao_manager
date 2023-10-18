package slf.talhao_manager;

import com.bedatadriven.jackson.datatype.jts.JtsModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TalhaoManagerSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(TalhaoManagerSpringApplication.class, args);
	}

//	@Bean
//	public JtsModule jtsModule() {
//		return new JtsModule();
//	}

}

package {{ group_id }}.{{ app_package }}.schema;

import com.stano.annotations.Generated;
import com.stano.spring_boot_application.MspSpringApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Generated("spring-boot")
@SpringBootApplication
public class SchemaMigrationLauncher {
  public static void main(String[] args) {
    var applicationContext = MspSpringApplication.run(SchemaMigrationLauncher.class, args);
    SpringApplication.exit(applicationContext);
  }
}

package {{ group_id }}.{{ app_package }}.domain;

import com.stano.domain_jpa.EnableJpa;
import com.stano.schema.installer.schemacontext.DefaultSchemaContext;
import com.stano.schema.installer.schemacontext.SchemaContext;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.TestPropertySource;

@SpringBootConfiguration
@EnableJpa
@TestPropertySource(properties = "spring.main.allow-bean-definition-overriding=true")
class TestDomainApplication {
  @Bean
  SchemaContext schemaContext() {
    return new DefaultSchemaContext(
        TestDomainApplication.class.getClassLoader().getResource("db/schema.xml"));
  }
}

package {{ group_id }}.{{ app_package }}.domain.person;

import com.stano.domain_jpa.id.EntityId;
import java.util.UUID;

public class PersonId extends EntityId {
  public PersonId(UUID value) {
    super(value);
  }
}

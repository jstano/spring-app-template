package {{ group_id }}.{{ app_package }}.application_contracts.person;

import java.time.LocalDate;
import java.util.UUID;

public record PersonResponse(UUID id, String name, LocalDate birthDate, String address) {}

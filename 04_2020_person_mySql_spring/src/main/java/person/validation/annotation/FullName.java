package person.validation.annotation;

import javax.validation.Payload;

public @interface FullName {
    int value();

    String message() default"{tel.ran.04_2020_person.validation.person.full_name}";

    Class<?> [] groups()default {};
    Class<? extends Payload>[] payload()default {};
}

package udesc.dsw.droneseta.exception;

import jakarta.validation.ConstraintViolation;
import java.util.Set;

public class ValidatorException extends Exception {
    private final Set<? extends ConstraintViolation<?>> violations;

    public ValidatorException(Set<? extends ConstraintViolation<?>> violations) {
        this.violations = violations;
    }

    public Set<? extends ConstraintViolation<?>> getViolations() {
        return violations;
    }
}

package udesc.dsw.droneseta.exception;

import jakarta.validation.ConstraintViolation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.dao.DataIntegrityViolationException;

import javax.management.InstanceNotFoundException;
import java.nio.file.AccessDeniedException;
import java.util.Set;
import java.util.stream.Collectors;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    public static class MessagesCollector {

        private final String errorMessage;

        MessagesCollector(Set<? extends ConstraintViolation<?>> violations) {
            this.errorMessage = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(", "));
        }

        MessagesCollector(String errorMessage) {
            this.errorMessage = errorMessage;
        }

        public String getErrorMessage(){
            return errorMessage;
        }

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ValidatorException.class})
    public ResponseEntity<?> handleValidatorException(ValidatorException ex) {
        MessagesCollector messageCollector = new MessagesCollector(ex.getViolations());
        return new ResponseEntity<>(messageCollector, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({DataIntegrityViolationException.class, IllegalArgumentException.class})
    public ResponseEntity<?> handleException(Exception ex) {
        MessagesCollector messageCollector = new MessagesCollector(ex.getMessage());
        return new ResponseEntity<>(messageCollector, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<?> handleAcessViolation(AccessDeniedException ex){
        MessagesCollector messagesCollector = new MessagesCollector(ex.getMessage());
        return new ResponseEntity<>(messagesCollector, HttpStatus.UNAUTHORIZED);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({InstanceNotFoundException.class})
    public ResponseEntity<?> handleNotFoundException(Exception ex){
        MessagesCollector messagesCollector = new MessagesCollector(ex.getMessage());
        return new ResponseEntity<>(messagesCollector, HttpStatus.NOT_FOUND);
    }
}

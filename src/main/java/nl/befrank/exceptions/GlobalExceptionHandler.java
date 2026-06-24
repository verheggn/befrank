package nl.befrank.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ProblemDetail handleNotFound(
            EntityNotFoundException ex) {


        ProblemDetail problem =
                ProblemDetail.forStatus(HttpStatus.NOT_FOUND);

        problem.setTitle("Niet gevonden");
        problem.setDetail(ex.getMessage());

        return problem;
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleUnexpectedException(Exception ex) {

        ProblemDetail problem =
                ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        problem.setTitle("Internal Server Error");
        problem.setDetail("Er is een onverwachte fout opgetreden");

        return problem;
    }
}

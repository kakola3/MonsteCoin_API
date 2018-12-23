package monstercoin.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomerRestExceptionHandler
{
    // Add an exception handler for CustomerNotFoundException
    @ExceptionHandler
    public ResponseEntity<CustomerErrorResponse> handlerException(CustomerNotFoundException exc){
        // create CustomerErrorResponse
        CustomerErrorResponse error = new CustomerErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                exc.getMessage(),
                System.currentTimeMillis());

        // response ResponseEntity

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // Add another exception handler ... to catch any exception (catch all)
    @ExceptionHandler
    public ResponseEntity<CustomerErrorResponse> handlerException(Exception exc){
        // create CustomerErrorResponse
        CustomerErrorResponse error = new CustomerErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                exc.getMessage(),
                System.currentTimeMillis());

        // response ResponseEntity

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


}

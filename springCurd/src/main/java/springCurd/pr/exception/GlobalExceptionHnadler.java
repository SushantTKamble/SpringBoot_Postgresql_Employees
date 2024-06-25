package springCurd.pr.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHnadler {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> responseNotFoundException(ResourceNotFoundException ex, WebRequest reuest){
		ErrorDetails errorDetails = new ErrorDetails(new Date(),ex.getMessage(),reuest.getDescription(false));
		return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globalExceptionHandler(Exception ex, WebRequest reuest){
		ErrorDetails errorDetails = new ErrorDetails(new Date(),ex.getMessage(),reuest.getDescription(false));
		return new ResponseEntity<>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
}

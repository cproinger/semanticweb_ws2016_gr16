package at.ac.tuwien.ifs.tulid.group16.web;

import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DefaultControllerAdvice {
	@ResponseBody
	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	VndErrors defaultError(Exception ex) {
		return new VndErrors("error", ex.getClass() + "" + ex.getMessage());
	}
	
	@ResponseBody
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	VndErrors notFoundError(Exception ex) {
		return new VndErrors("error", ex.getClass() + "" + ex.getMessage());
	}
}

package jp.co.practice.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;

//@ControllerAdvice
@Slf4j
public class CustomExceptionHandler {

	@ExceptionHandler
	public String handleException(Throwable e){
		e.printStackTrace();
		return "error/error.html";
	}
}

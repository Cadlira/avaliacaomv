package lira.leo.mv.userrs.web.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import lira.leo.mv.userrs.web.exception.RestError.TIPO_ERROR;

/**
 * Classe responsável por tratar os erros do do controller 
 * @author leonardo.lira
 *
 */
@ControllerAdvice
public class ExceptionControllerAdvice {

	
	/**
	 * Trata os erros de validação
	 * @param request
	 * @param response
	 * @param e
	 * @return
	 */
	@ExceptionHandler(ValidationException.class)
	@ResponseBody
	public RestError exceptionValidation(HttpServletRequest request, HttpServletResponse response,
			ValidationException e) {
		RestError restValidatorError = new RestError(request, response, e.getBindingResult());
		restValidatorError.setStatusHttp(HttpStatus.PRECONDITION_FAILED.value());
		response.setStatus(HttpStatus.PRECONDITION_FAILED.value());
		restValidatorError.setMensagemStatusHttp(HttpStatus.PRECONDITION_FAILED.getReasonPhrase());
		return restValidatorError;
	}
	
	/**
	 * 
	 * Trata os erros caso tente acessar um HttpMethod não permitido
	 * @param request
	 * @param response
	 * @param e
	 * @return
	 */
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseBody
	public RestError methodNotSupported(HttpServletRequest request, HttpServletResponse response,
			HttpRequestMethodNotSupportedException e) {
		RestError restWebError = new RestError(request, response, TIPO_ERROR.WEB);
		restWebError.setStatusHttp(HttpStatus.NOT_FOUND.value());
		response.setStatus(HttpStatus.NOT_FOUND.value());
		restWebError.setMensagemStatusHttp(HttpStatus.NOT_FOUND.getReasonPhrase());
		return restWebError;
	}
	
	/**
	 * Trata as exceções gerais do controller
	 * @param request
	 * @param response
	 * @param e
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public RestError exception(HttpServletRequest request, HttpServletResponse response, Exception e) {
		e.printStackTrace();
		RestError restError = new RestError(request, response, null, e);
		restError.setStatusHttp(HttpStatus.INTERNAL_SERVER_ERROR.value());
		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		restError.setMensagemStatusHttp(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());	
		return restError;
	}
}

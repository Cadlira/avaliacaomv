package lira.leo.mv.userrs.web.exception;

import org.springframework.validation.BindingResult;

/**
 * Exceção lançada quando há erro de validação
 * @author leonardo.lira
 *
 */
public class ValidationException extends RuntimeException {

	private static final long serialVersionUID = 3536194260643432063L;

	private BindingResult bindingResult;

	public ValidationException(BindingResult result) {
		this.bindingResult = result;
	}

	public BindingResult getBindingResult() {
		return bindingResult;
	}

	public void setBindingResult(BindingResult bindingResult) {
		this.bindingResult = bindingResult;
	}

}

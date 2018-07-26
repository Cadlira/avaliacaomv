package lira.leo.mv.userrs.web.validator;

import java.util.Objects;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMethod;

import lira.leo.mv.userrs.entity.User;
import lira.leo.mv.userrs.service.UserService;

/**
 * Classe responsável pela validação do User vindo do controller
 * 
 * @author leonardo.lira
 *
 */
@Component
public class UserValidator implements Validator {

	@Autowired
	private UserService service;

	private RequestMethod metodo;

	private enum RegexEnum {
		DIGIT("\\d+"), EMAIL_VALID("^([a-z0-9_.+-])+\\@(([a-z0-9-])+\\.)+([a-z0-9]{2,4})+$");

		private final String regex;

		RegexEnum(String regex) {
			this.regex = regex;
		}

		public String getRegex() {
			return regex;
		}
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;

		if (user.getId() <= 0 && !isInsert()) {
			errors.reject(User.class.getSimpleName().toLowerCase(),
					User.class.getSimpleName().toLowerCase() + ".atualizarInexistente");
		} else if (user.getId() > 0 && !isUpdate()) {
			errors.reject(User.class.getSimpleName().toLowerCase(),
					User.class.getSimpleName().toLowerCase() + ".inserirExistente");
		} else {
			if (!objectIsNull(user, errors)) {
				if (user.getId() > 0 && Objects.isNull(service.findById(user.getId()))) {
					objectNotExist(user, errors);
				}
			}

			isEmptyOrNull("username", user.getUsername(), errors);

			if (!isEmptyOrNull(user.getEmail())) {
				isEmailInvalid("email", user.getEmail(), errors);
			}
		}

	}

	private boolean isEmptyOrNull(String valor) {
		if (Objects.isNull(valor) || valor.trim().isEmpty()) {
			return true;
		}
		return false;
	}

	private boolean objectIsNull(User user, Errors errors) {
		if (Objects.isNull(user)) {
			errors.reject(User.class.getSimpleName().toLowerCase() + ".objetoVazio",
					"� necess�rio preencher as informa��es");
			return true;
		}
		return false;
	}

	private boolean objectNotExist(User user, Errors errors) {
		if (Objects.nonNull(user)) {
			errors.reject(User.class.getSimpleName().toLowerCase() + ".objetoNaoExiste",
					"O Usuario n�o existe na base de dados.");
			return true;
		}
		return false;
	}

	private boolean isEmptyOrNull(String nomeCampo, String valor, Errors errors) {
		if (Objects.isNull(valor) || valor.trim().isEmpty()) {
			errors.rejectValue(nomeCampo, User.class.getSimpleName().toLowerCase() + "." + nomeCampo + ".vazio",
					"O " + nomeCampo + " deve ser informado");
			return true;
		}
		return false;
	}

	private boolean isEmailInvalid(String nomeCampo, String valor, Errors errors) {
		if (!isEmailValid(valor)) {
			errors.rejectValue(nomeCampo, User.class.getSimpleName().toLowerCase() + "." + nomeCampo + ".emailInvalido",
					"O " + nomeCampo + " deve ter o seguir o padr�o de email e ter apenas caract�res min�sculos.");
			return true;
		}
		return false;
	}

	private boolean isEmailValid(final String field) {
		if (Objects.nonNull(field)) {
			Pattern pattern = Pattern.compile(RegexEnum.EMAIL_VALID.getRegex());
			return pattern.matcher(field).matches();
		} else {
			return false;
		}
	}

	public RequestMethod getMetodo() {
		return metodo;
	}

	public void setMetodo(RequestMethod metodo) {
		this.metodo = metodo;
	}

	public boolean isInsert() {
		return RequestMethod.POST == getMetodo();
	}

	public boolean isUpdate() {
		return RequestMethod.PUT == getMetodo();
	}

}

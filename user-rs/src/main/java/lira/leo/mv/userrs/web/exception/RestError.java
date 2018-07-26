package lira.leo.mv.userrs.web.exception;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Classe responsável por mapear os errors rest
 * @author leonardo.lira
 *
 */
@JsonPropertyOrder({ "statusHttp", "mensagemStatusHttp", "metodoHttp", "urlEntrada", "parametros", "dataErro",
		"urlErro", "mensagemErro", "excecao", "errors" })
@JsonInclude(Include.NON_NULL)
public class RestError implements Serializable {

	private static final long serialVersionUID = 5856542669856536856L;
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public enum TIPO_ERROR {
		GENERICO, VALIDACAO, WEB;
	}

	private List<ValidatorError> errors = new ArrayList<ValidatorError>();
	private int statusHttp;
	private String mensagemStatusHttp;
	private String mensagemErro;
	private String metodoHttp;
	private String urlEntrada;
	private String urlErro;
	private String parametros;
	private String dataErro;
	@JsonIgnore
	private Throwable excecao;
	private TIPO_ERROR tipoError = TIPO_ERROR.GENERICO;

	public TIPO_ERROR getTipoError() {
		return tipoError;
	}

	public void setTipoError(TIPO_ERROR tipoError) {
		this.tipoError = tipoError;
	}

	public RestError() {
	}

	public RestError(HttpStatus status, String mensagem, Throwable excecao) {
		this.setStatusHttp(status.value());
		this.setMensagemStatusHttp(status.getReasonPhrase());
		this.setMensagemErro(mensagem);
		this.setDataErro(new Date());
		this.setExcecao(excecao);
	}

	public RestError(HttpServletRequest request, HttpServletResponse response, TIPO_ERROR tipoErro) {

		this(request, response, "", null);

		this.setTipoError(tipoErro);

		String mensagem = (String) request.getAttribute("javax.servlet.error.message");
		if (Objects.nonNull(mensagem)) {
			this.setMensagemErro(mensagem);
		}
	}

	public RestError(HttpServletRequest request, HttpServletResponse response, String mensagem, Throwable excecao) {
		this.statusHttp = response.getStatus();
		HttpStatus status = HttpStatus.valueOf(this.statusHttp);
		if (Objects.nonNull(status)) {
			this.mensagemStatusHttp = status.getReasonPhrase();
		} else {
			this.mensagemStatusHttp = "";
		}

		if (Objects.nonNull(mensagem)) {
			this.mensagemErro = mensagem;
		} else if (Objects.nonNull(excecao)) {
			this.mensagemErro = excecao.toString();
		} else {
			this.mensagemErro = "";
		}
		this.dataErro = dateFormat.format(new Date());
		this.excecao = excecao;
		this.urlErro = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getRequestURI();
		this.urlEntrada = (String) request.getAttribute("javax.servlet.forward.request_uri");
		if (Objects.isNull(this.urlEntrada)) {
			this.urlEntrada = this.urlErro;
		} else {
			this.urlEntrada = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ this.urlEntrada;
		}
		this.metodoHttp = request.getMethod();
		this.parametros = request.getQueryString();
	}

	public RestError(HttpServletRequest request, HttpServletResponse response, BindingResult result) {
		this(request, response, null, null);
		this.setTipoError(TIPO_ERROR.VALIDACAO);
		tratarErros(result);
	}

	private void tratarErros(BindingResult result) {
		for (ObjectError erro : result.getAllErrors()) {

			if (erro instanceof FieldError) {
				this.errors.add(new ValidatorError(erro.getObjectName(), ((FieldError) erro).getField(), erro.getCode(),
						erro.getDefaultMessage()));
			} else {
				this.errors.add(new ValidatorError(erro.getObjectName(), erro.getCode(), erro.getDefaultMessage()));
			}
		}

	}

	public void updateUrls(HttpServletRequest request) {
		this.setUrlErro(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getRequestURI());
		this.setUrlEntrada((String) request.getAttribute("javax.servlet.forward.request_uri"));
		if (Objects.isNull(this.getUrlEntrada())) {
			this.setUrlEntrada(this.getUrlErro());
		} else {
			this.setUrlEntrada(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ getUrlEntrada());
		}
	}

	public int getStatusHttp() {
		return statusHttp;
	}

	public void setStatusHttp(int statusHttp) {
		this.statusHttp = statusHttp;
	}

	public String getMensagemStatusHttp() {
		return mensagemStatusHttp;
	}

	public void setMensagemStatusHttp(String mensagemStatusHttp) {
		this.mensagemStatusHttp = mensagemStatusHttp;
	}

	public String getUrlErro() {
		return urlErro;
	}

	public void setUrlErro(String urlErro) {
		this.urlErro = urlErro;
	}

	public Throwable getExcecao() {
		return excecao;
	}

	public void setExcecao(Throwable excecao) {
		this.excecao = excecao;
	}

	public String getDataErro() {
		return dataErro;
	}

	public void setDataErro(String dataErro) {
		this.dataErro = dataErro;
	}

	public void setDataErro(Date dataErro) {
		this.dataErro = dateFormat.format(dataErro);
	}

	public String getUrlEntrada() {
		return urlEntrada;
	}

	public void setUrlEntrada(String urlEntrada) {
		this.urlEntrada = urlEntrada;
	}

	public String getMetodoHttp() {
		return metodoHttp;
	}

	public void setMetodoHttp(String metodoHttp) {
		this.metodoHttp = metodoHttp;
	}

	public String getParametros() {
		return parametros;
	}

	public void setParametros(String parametros) {
		this.parametros = parametros;
	}

	public String getMensagemErro() {
		return mensagemErro;
	}

	public void setMensagemErro(String mensagemErro) {
		this.mensagemErro = mensagemErro;
	}

	public String toJson() {
		ObjectMapper mapper = new ObjectMapper();
		String retorno = "";
		try {
			retorno = mapper.writeValueAsString(this);
		} catch (Exception e) {
			retorno = toString();
		}
		return retorno;
	}

	public List<ValidatorError> getErrors() {
		return errors;
	}

	public void setErrors(List<ValidatorError> errors) {
		this.errors = errors;
	}

	public static ResponseEntity<RestError> getResponseEntity(HttpServletRequest request, HttpServletResponse response,
			HttpStatus status, String mensagem) {

		RestError restError = new RestError(request, response, TIPO_ERROR.WEB);
		restError.setStatusHttp(status.value());
		restError.setMensagemStatusHttp(status.getReasonPhrase());
		restError.setMensagemErro(mensagem);

		return new ResponseEntity<RestError>(restError, status);
	}

	public static ResponseEntity<RestError> getResponseEntity(HttpServletRequest request, HttpServletResponse response,
			HttpStatus status) {
		return getResponseEntity(request, response, status, "");
	}

}
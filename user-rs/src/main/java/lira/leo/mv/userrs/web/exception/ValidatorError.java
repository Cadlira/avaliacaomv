package lira.leo.mv.userrs.web.exception;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Classe utilizada para mapear erros de validação
 * @author leonardo.lira
 *
 */
@JsonPropertyOrder({ "nomeObjeto", "campo", "codigo", "mensagem" })
@JsonInclude(Include.NON_NULL)
public class ValidatorError implements Serializable {

	private static final long serialVersionUID = -1000119365868318752L;

	private String nomeObjeto;
	private String codigo;
	private String mensagem;
	private String campo;

	public ValidatorError() {
	}

	public ValidatorError(String nomeObjeto, String codigo, String mensagem) {
		super();
		this.nomeObjeto = nomeObjeto;
		this.codigo = codigo;
		this.mensagem = mensagem;
	}

	public ValidatorError(String nomeObjeto, String campo, String codigo, String mensagem) {
		super();
		this.nomeObjeto = nomeObjeto;
		this.codigo = codigo;
		this.mensagem = mensagem;
		this.campo = campo;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getNomeObjeto() {
		return nomeObjeto;
	}

	public void setNomeObjeto(String nomeObjeto) {
		this.nomeObjeto = nomeObjeto;
	}

	public String getCampo() {
		return campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}

}

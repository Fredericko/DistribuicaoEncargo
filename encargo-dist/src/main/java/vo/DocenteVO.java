package vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "docente")
public class DocenteVO implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	private String nome;
	private String senha;
	private double regime_trabalho;
	private String contrato_natureza;
	private Date contrato_encerramento;

	private double horas_ministradas = .0;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public double getRegime_trabalho() {
		return regime_trabalho;
	}

	public void setRegime_trabalho(double regime_trabalho) {
		this.regime_trabalho = regime_trabalho;
	}

	public String getContrato_natureza() {
		return contrato_natureza;
	}

	public void setContrato_natureza(String contrato_natureza) {
		this.contrato_natureza = contrato_natureza;
	}

	public Date getContrato_encerramento() {
		return contrato_encerramento;
	}

	public void setContrato_encerramento(Date contrato_encerramento) {
		this.contrato_encerramento = contrato_encerramento;
	}

}

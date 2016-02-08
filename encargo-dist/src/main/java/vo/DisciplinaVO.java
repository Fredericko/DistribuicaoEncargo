package vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "disciplina")
public class DisciplinaVO implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String codigo;
	@Column(name="nome_disciplina")
	private String nomeDisciplina;
	private String turno;
	@Column(name="nome_total")
	private int chTotal;
	@Column(name="nome_semanal")
	private int chSemanal;
	private String ementa;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNomeDisciplina() {
		return nomeDisciplina;
	}
	public void setNomeDisciplina(String nomeDisciplina) {
		this.nomeDisciplina = nomeDisciplina;
	}
	public String getTurno() {
		return turno;
	}
	public void setTurno(String turno) {
		this.turno = turno;
	}
	public int getChTotal() {
		return chTotal;
	}
	public void setChTotal(int chTotal) {
		this.chTotal = chTotal;
	}
	public int getChSemanal() {
		return chSemanal;
	}
	public void setChSemanal(int chSemanal) {
		this.chSemanal = chSemanal;
	}
	public String getEmenta() {
		return ementa;
	}
	public void setEmenta(String ementa) {
		this.ementa = ementa;
	}
}

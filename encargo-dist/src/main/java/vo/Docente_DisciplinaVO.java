package vo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="docente_disciplina")
public class Docente_DisciplinaVO {
	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "docente_id")
	private DocenteVO docente;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "disciplina_id")
	private DisciplinaVO disciplina;
	private int nivel_interesse;
	private boolean ministrou;
	private int qnt_ministrou;
	private double hrs_preenchidas;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public DocenteVO getDocente() {
		return docente;
	}
	public void setDocente(DocenteVO docente) {
		this.docente = docente;
	}
	public DisciplinaVO getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(DisciplinaVO disciplina) {
		this.disciplina = disciplina;
	}
	public int getNivel_interesse() {
		return nivel_interesse;
	}
	public void setNivel_interesse(int nivel_interesse) {
		this.nivel_interesse = nivel_interesse;
	}
	public boolean isMinistrou() {
		return ministrou;
	}
	public void setMinistrou(boolean ministrou) {
		this.ministrou = ministrou;
	}
	public int getQnt_ministrou() {
		return qnt_ministrou;
	}
	public void setQnt_ministrou(int qnt_ministrou) {
		this.qnt_ministrou = qnt_ministrou;
	}
	public double getCh_semanal() {
		return hrs_preenchidas;
	}
}

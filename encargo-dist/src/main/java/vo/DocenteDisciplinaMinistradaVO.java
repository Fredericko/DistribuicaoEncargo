package vo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "docente_disciplina_ministrada")
public class DocenteDisciplinaMinistradaVO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name = "docente_id")
	private DocenteVO docente;
	@ManyToOne
	@JoinColumn(name = "disciplina_id")
	private DisciplinaVO disciplina;
	private int ano;
	private int semestre;
	
	public DocenteDisciplinaMinistradaVO() {
	}
	
	public DocenteDisciplinaMinistradaVO(DocenteVO docente, DisciplinaVO disciplina) {
		this.docente = docente;
		this.disciplina = disciplina;
	}
	
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

	@Override
	public boolean equals(Object obj) {
		DisciplinaVO other = (DisciplinaVO) obj;
		return this.disciplina.getId() == other.getId();
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public int getSemestre() {
		return semestre;
	}

	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}
}

package vo;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class DocenteDisciplinaID implements Serializable {
	@ManyToOne
	@JoinColumn(name = "docente_id")
	private DocenteVO docente;

	@ManyToOne
	@JoinColumn(name = "disciplina_id")
	private DisciplinaVO disciplina;

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
}

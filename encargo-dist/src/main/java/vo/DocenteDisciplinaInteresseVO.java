package vo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "docente_disciplina_interesse", uniqueConstraints = @UniqueConstraint(columnNames = { "docente_id",
		"disciplina_id" }) )
public class DocenteDisciplinaInteresseVO implements Comparable<DocenteDisciplinaInteresseVO> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name = "docente_id")
	private DocenteVO docente;
	@ManyToOne
	@JoinColumn(name = "disciplina_id")
	private DisciplinaVO disciplina;
	private int ordem;

	public DocenteDisciplinaInteresseVO() {
	}

	public DocenteDisciplinaInteresseVO(DocenteVO docente, DisciplinaVO disciplina) {
		this.docente = docente;
		this.disciplina = disciplina;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrdem() {
		return ordem;
	}

	public void setOrdem(int ordem) {
		this.ordem = ordem;
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
	public int compareTo(DocenteDisciplinaInteresseVO o) {
		return this.ordem - o.ordem;
	}

}

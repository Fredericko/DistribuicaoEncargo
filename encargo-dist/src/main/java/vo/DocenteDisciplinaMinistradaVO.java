package vo;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "docente_disciplina_ministrada")
@AssociationOverrides({
		@AssociationOverride(name = "primaryKey.docente", joinColumns = @JoinColumn(name = "docente_id") ),
		@AssociationOverride(name = "primaryKey.disciplina", joinColumns = @JoinColumn(name = "disciplina_id") ) })
public class DocenteDisciplinaMinistradaVO {
	@EmbeddedId
	private DocenteDisciplinaID primaryKey = new DocenteDisciplinaID();

	private int ordem;

	public int getOrdem() {
		return ordem;
	}

	public void setOrdem(int ordem) {
		this.ordem = ordem;
	}

	public DocenteDisciplinaID getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(DocenteDisciplinaID primaryKey) {
		this.primaryKey = primaryKey;
	}

	@Transient
	public DocenteVO getDocente() {
		return getPrimaryKey().getDocente();
	}

	public void setDocente(DocenteVO docente) {
		getPrimaryKey().setDocente(docente);
	}

	@Transient
	public DisciplinaVO getDisciplina() {
		return getPrimaryKey().getDisciplina();
	}

	public void setDisciplina(DisciplinaVO disciplina) {
		getPrimaryKey().setDisciplina(disciplina);
	}

}

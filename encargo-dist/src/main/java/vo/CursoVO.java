package vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "curso")
public class CursoVO implements Serializable {

	@Id
	private String id;
	private String codigo;
	private String nome;
	private String duracao;
	@ManyToMany
	@JoinColumn(name = "disciplina_id")
	private List<DisciplinaVO> disciplinas;

	public CursoVO() {
		setId(UUID.randomUUID().toString());
		nome = "";
		codigo = "";
		duracao = "";
		disciplinas = new ArrayList<DisciplinaVO>();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void clear() {
		nome = "";
		duracao = "";
		codigo = "";
		disciplinas.clear();
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDuracao() {
		return duracao;
	}

	public void setDuracao(String duracao) {
		this.duracao = duracao;
	}

	public List<DisciplinaVO> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<DisciplinaVO> disciplinas) {
		this.disciplinas = disciplinas;
	}

}

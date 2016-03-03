package vo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "curso")
public class CursoVO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String codigo;
	private String nome;
	private String duracao;
	@ManyToMany
	@JoinColumn(name = "disciplina_id")
	private List<DisciplinaVO> disciplinas;
	@OneToOne
	private DocenteVO coordenador;

	public CursoVO() {
		nome = "";
		codigo = "";
		duracao = "";
		disciplinas = new ArrayList<DisciplinaVO>();
	}

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

	public DocenteVO getCoordenador() {
		return coordenador;
	}

	public void setCoordenador(DocenteVO coordenador) {
		this.coordenador = coordenador;
	}

	public void clear() {
		nome = "";
		duracao = "";
		codigo = "";
		disciplinas.clear();
	}
}

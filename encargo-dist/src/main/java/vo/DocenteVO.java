package vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "docente")
public class DocenteVO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nome;
	private String sobrenome;
	private String senha;
	@Column(name = "regime_trabalho")
	private double regimeTrabalho;
	@Column(name = "contrato_natureza")
	private String contratoNatureza;
	@Column(name = "contrato_encerramento")
	private Date contratoEncerramento;
	@OneToMany(mappedBy = "docente", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<DocenteDisciplinaInteresseVO> docente_disciplina_interesse = new HashSet<DocenteDisciplinaInteresseVO>();
	@OneToMany(mappedBy = "docente", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<DocenteDisciplinaMinistradaVO> docente_disciplina_ministrada = new HashSet<DocenteDisciplinaMinistradaVO>();
	@Transient
	private int contemplacao;
	@Transient
	private double horasMinistradas;
	@Transient
	private List<DisciplinaVO> disciplinasInteresse = new ArrayList<DisciplinaVO>();
	@Transient
	private List<DisciplinaVO> disciplinasMinistradas = new ArrayList<DisciplinaVO>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public double getRegimeTrabalho() {
		return regimeTrabalho;
	}

	public void setRegimeTrabalho(double regimeTrabalho) {
		this.regimeTrabalho = regimeTrabalho;
	}

	public String getContratoNatureza() {
		return contratoNatureza;
	}

	public void setContratoNatureza(String contratoNatureza) {
		this.contratoNatureza = contratoNatureza;
	}

	public Date getContratoEncerramento() {
		return contratoEncerramento;
	}

	public void setContratoEncerramento(Date contratoEncerramento) {
		this.contratoEncerramento = contratoEncerramento;
	}

	public int getContemplacao() {
		return contemplacao;
	}

	public void setContemplacao(int contemplacao) {
		this.contemplacao = contemplacao;
	}

	public Set<DocenteDisciplinaInteresseVO> getDocenteDisciplinasInteresse() {
		return docente_disciplina_interesse;
	}

	public void setDocenteDisciplinaInteresse(Set<DocenteDisciplinaInteresseVO> docente_disciplina_interesse) {
		this.docente_disciplina_interesse = docente_disciplina_interesse;
	}

	public Set<DocenteDisciplinaMinistradaVO> getDocenteDisciplinasMinistradas() {
		return docente_disciplina_ministrada;
	}

	public void setDocenteDisciplinaMinistrada(Set<DocenteDisciplinaMinistradaVO> docente_disciplina_ministrada) {
		this.docente_disciplina_ministrada = docente_disciplina_ministrada;
	}

	public List<DisciplinaVO> getDisciplinasInteresse() {
		disciplinasInteresse.clear();
		for (DocenteDisciplinaInteresseVO disc : docente_disciplina_interesse) {
			disciplinasInteresse.add(disc.getDisciplina());
		}
		ordenarInteresse(docente_disciplina_interesse);
		return disciplinasInteresse;
	}

	public DisciplinaVO getDisciplinaInteresse(int i) {
		disciplinasInteresse.clear();
		for (DocenteDisciplinaInteresseVO disc : docente_disciplina_interesse) {
			disciplinasInteresse.add(disc.getDisciplina());
		}
		ordenarInteresse(docente_disciplina_interesse);
		return disciplinasInteresse.get(i);
	}

	public void setDisciplinasInteresse(List<DisciplinaVO> disciplinas) {
		this.disciplinasInteresse = disciplinas;
		docente_disciplina_interesse.clear();
		int i = 0;
		for (DisciplinaVO disc : this.disciplinasInteresse) {
			DocenteDisciplinaInteresseVO docDisc = new DocenteDisciplinaInteresseVO();
			docDisc.setOrdem(i);
			docDisc.setDocente(this);
			docDisc.setDisciplina(disc);
			docente_disciplina_interesse.add(docDisc);
			i++;
		}
	}

	public List<DisciplinaVO> getDisciplinasMinistradas() {
		if (disciplinasMinistradas.isEmpty()) {
			for (DocenteDisciplinaMinistradaVO disc : docente_disciplina_ministrada) {
				disciplinasMinistradas.add(disc.getDisciplina());
			}
		}
		return disciplinasMinistradas;
	}

	public void setDisciplinasMinistradas(List<DisciplinaVO> disciplinas) {
		this.disciplinasMinistradas = disciplinas;
		docente_disciplina_ministrada.clear();
		int i = 0;
		for (DisciplinaVO disc : this.disciplinasMinistradas) {
			DocenteDisciplinaMinistradaVO docDisc = new DocenteDisciplinaMinistradaVO();
			docDisc.setOrdem(i);
			docDisc.setDocente(this);
			docDisc.setDisciplina(disc);
			docente_disciplina_ministrada.add(docDisc);
			i++;
		}
	}

	public void toDocenteDisciplinasMinistrada() {
		DocenteDisciplinaMinistradaVO docDiscVO;
		for (DisciplinaVO disciplinaVO : disciplinasMinistradas) {
			if (!docente_disciplina_interesse.contains(disciplinaVO)) {
				docDiscVO = new DocenteDisciplinaMinistradaVO();
				docDiscVO.setDocente(this);
				docDiscVO.setDisciplina(disciplinaVO);
				docente_disciplina_ministrada.add(docDiscVO);
			}
		}
	}

	public double getHorasMinistradas() {
		horasMinistradas = 0;
		for (DisciplinaVO disc : getDisciplinasMinistradas()) {
			horasMinistradas += disc.getChSemanal();
		}
		return horasMinistradas;
	}

	private void ordenarInteresse(Set<DocenteDisciplinaInteresseVO> docente_disciplina_interesse) {
		for (DocenteDisciplinaInteresseVO docDiscInt : docente_disciplina_interesse) {
			disciplinasInteresse.set(docDiscInt.getOrdem(), docDiscInt.getDisciplina());
		}
	}
}

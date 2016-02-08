package vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "docente")
public class DocenteVO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    @ManyToMany
    @JoinColumn(name = "disciplina_id")
    @Column(name = "disciplina_ministrida_id")
    private List<DisciplinaVO> disciplinasMinistradas;
    @ManyToMany
    @JoinColumn(name = "disciplina_id")
    @Column(name = "disciplina_interrese_id")
    private List<DisciplinaVO> disciplinasInteresse;
    @Transient
    private int contemplacao;
    @Transient
    private double horasMinistradas;

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

    public List<DisciplinaVO> getDisciplinasMinistradas() {
        return disciplinasMinistradas;
    }

    public void setDisciplinasMinistradas(List<DisciplinaVO> disciplinasMinistradas) {
        this.disciplinasMinistradas = disciplinasMinistradas;
    }

    public List<DisciplinaVO> getDisciplinasInteresse() {
        return disciplinasInteresse;
    }

    public void setDisciplinasInteresse(List<DisciplinaVO> disciplinasInteresse) {
        this.disciplinasInteresse = disciplinasInteresse;
    }

    public int getContemplacao() {
        return contemplacao;
    }

    public void setContemplacao(int contemplacao) {
        this.contemplacao = contemplacao;
    }

    public double getHorasMinistradas() {
        horasMinistradas = 0;
        for (DisciplinaVO disc : disciplinasMinistradas) {
            horasMinistradas += disc.getChSemanal();
        }
        return horasMinistradas;
    }

    public DisciplinaVO getDisciplinasPreferencia(int id) {
        return disciplinasInteresse.get(id);
    }
}

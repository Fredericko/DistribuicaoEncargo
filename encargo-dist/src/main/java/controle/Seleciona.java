package controle;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.ArrayDataModel;

import dao.DisciplinaMinistradaDAO;
import dao.DocenteDAO;
import negocio.Selecao;
import util.DocentesModel;
import vo.DocenteDisciplinaMinistradaVO;
import vo.DocenteVO;

@ManagedBean
@RequestScoped
public class Seleciona {

	private List<DocenteVO> docenteArrayList;
	private int ano, semestre;
	private List<DocenteDisciplinaMinistradaVO> docentesMinistradas;
	
	public void saveOrUpdate(List<DocenteVO> docentes) {
		for (DocenteVO vo : docentes) {
			for (DocenteDisciplinaMinistradaVO docDiscMin : vo.getDocenteDisciplinasMinistradas()) {
				docDiscMin.setAno(ano);
				docDiscMin.setSemestre(semestre);
			}
			DisciplinaMinistradaDAO.getInstance().saveOrUpdate(vo.getDocenteDisciplinasMinistradas());
		}
	}

	public static List<DocenteVO> getAll() {
		return DocenteDAO.getInstance().getAll();
	}

	public ArrayDataModel<DocenteVO> getDocentesModel() {
		return DocentesModel.getDocentesModel();
	}
	
	public void setDocentesModel(ArrayDataModel<DocenteVO> docentesModel) {
		DocentesModel.setDocentesModel(docentesModel);
	}

	public void selecionar() {
		Selecao sel = new Selecao();
		docenteArrayList = DocentesModel.getDocenteArrayList();
		sel.selecionarDisciplinas(docenteArrayList);
		saveOrUpdate(docenteArrayList);
	}

	public int getSemestre() {
		return semestre;
	}

	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public List<DocenteDisciplinaMinistradaVO> gettDocentesMinistradas(int docenteId) {
		this.docentesMinistradas = DisciplinaMinistradaDAO.getInstance().getDisciplinasMinistradasMaisRecente(docenteId);
		return this.docentesMinistradas;
	}

}

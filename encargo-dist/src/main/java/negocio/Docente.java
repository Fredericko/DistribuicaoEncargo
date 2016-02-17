package negocio;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.ArrayDataModel;

import org.primefaces.model.DualListModel;

import dao.DisciplinaInteresseDAO;
import dao.DisciplinaMinistradaDAO;
import dao.DocenteDAO;
import util.Selecao;
import vo.DisciplinaVO;
import vo.DocenteVO;

@ManagedBean
@SessionScoped
public class Docente {

	private DocenteVO docenteNovo = new DocenteVO();
	private ArrayDataModel<DocenteVO> docentes;
	private DualListModel<DisciplinaVO> model;
	private ArrayList<DisciplinaVO> disciplinaArrayList;
	private ArrayList<DocenteVO> docenteArrayList;

	@PostConstruct
	public void init() {
		disciplinaArrayList = (ArrayList<DisciplinaVO>) Disciplina.getAll();
		setModel(new DualListModel<DisciplinaVO>(disciplinaArrayList, docenteNovo.getDisciplinasInteresse()));
	}

	public static void update(DocenteVO vo) {
		DocenteDAO.getInstance().save(vo);
		DisciplinaInteresseDAO.getInstance().inserirDisciplinaInteresse(vo.getDocenteDisciplinasInteresse());
	}

	public static void update(ArrayList<DocenteVO> docentes) {
		for (DocenteVO vo : docentes) {
			DisciplinaMinistradaDAO.getInstance().inserirDisciplinaMinistradas(vo.getDocenteDisciplinaMinistrada());
		}
	}

	public void delete(DocenteVO vo) {
		DocenteDAO.getInstance().delete(vo);
	}

	public static List<DocenteVO> getAll() {
		return DocenteDAO.getInstance().getAll();
	}

	public void addDocente() {
		docenteNovo.setDisciplinasInteresse(getModel().getTarget());
		update(docenteNovo);
		docenteNovo = new DocenteVO();
	}

	public ArrayDataModel<DocenteVO> getDocentes() {
		docenteArrayList = new ArrayList<DocenteVO>(getAll());
		DocenteVO[] disciplinaArray = new DocenteVO[docenteArrayList.size()];
		int i = 0;
		for (DocenteVO dos : docenteArrayList) {
			disciplinaArray[i] = dos;
			i++;
		}
		docentes = new ArrayDataModel<DocenteVO>(disciplinaArray);
		return docentes;
	}

	public void setDocentes(ArrayDataModel<DocenteVO> docentes) {
		this.docentes = docentes;
	}

	public DocenteVO getDocenteNovo() {
		return docenteNovo;
	}

	public void setDocenteNovo(DocenteVO docenteNovo) {
		this.docenteNovo = docenteNovo;
	}

	private ArrayDataModel<DocenteVO> docentesModel;

	public ArrayDataModel<DocenteVO> getDocentesModel() {
		docenteArrayList = new ArrayList<DocenteVO>(Docente.getAll());
		DocenteVO[] disciplinaArray = new DocenteVO[docenteArrayList.size()];
		int i = 0;
		for (DocenteVO dos : docenteArrayList) {
			disciplinaArray[i] = dos;
			i++;
		}
		docentesModel = new ArrayDataModel<DocenteVO>(disciplinaArray);
		return docentesModel;
	}

	public void setDocentesModel(ArrayDataModel<DocenteVO> docentesModel) {
		this.docentesModel = docentesModel;
	}

	public void selecionar() {
		Selecao sel = new Selecao();
		sel.seleciona(docenteArrayList);
	}

	public DualListModel<DisciplinaVO> getModel() {
		return model;
	}

	public void setModel(DualListModel<DisciplinaVO> model) {
		this.model = model;
	}

}

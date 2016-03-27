package controle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.ArrayDataModel;
import javax.faces.model.DataModel;

import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DualListModel;

import dao.CursoDAO;
import dao.DisciplinaInteresseDAO;
import dao.DocenteDAO;
import enums.Cargos;
import util.PickListDisciplinaArray;
import vo.CursoVO;
import vo.DisciplinaVO;
import vo.DocenteVO;

@ManagedBean
@SessionScoped
public class Curso {

	private CursoVO cursoNovo = new CursoVO();
	private static DataModel<CursoVO> cursos;
	private DualListModel<DisciplinaVO> model;
	private List<DocenteVO> listaSelect;

	@PostConstruct
	public void init() {
		PickListDisciplinaArray.setDisciplina();
		model = new DualListModel<DisciplinaVO>(PickListDisciplinaArray.getDisciplinas(), cursoNovo.getDisciplinas());
	}

	public DualListModel<DisciplinaVO> getModel() {
		return this.model;
	}

	public void setModel(DualListModel<DisciplinaVO> model) {
		this.model = model;
	}

	public static void save(CursoVO vo) {
		CursoDAO.getInstance().save(vo);
	}

	public static void update(CursoVO vo) {
		if (vo.getCoordenador() != null) {
			vo.getCoordenador().setCargo(Cargos.COORDENADOR);
		}
		CursoDAO.getInstance().update(vo);
	}

	public void delete(CursoVO vo) {
		if (vo.getCoordenador() != null) {
			vo.getCoordenador().setCargo(Cargos.DOCENTE);
		}
		CursoDAO.getInstance().delete(vo);
	}

	public static List<CursoVO> getAll() {
		return CursoDAO.getInstance().getAll();
	}

	public DataModel<CursoVO> getCursos() {
		ArrayList<CursoVO> cursoArrayList = new ArrayList<>(getAll());
		CursoVO[] cursoArray = new CursoVO[cursoArrayList.size()];
		int i = 0;
		for (CursoVO cur : cursoArrayList) {
			cursoArray[i] = cur;
			i++;
		}
		cursos = new ArrayDataModel<>(cursoArray);
		return cursos;
	}

	public void addCursoNovo(CursoVO cursoNovo) {
		System.out.println("Teste");
		cursoNovo.setDisciplinas(model.getTarget());
		save(cursoNovo);
		if (cursoNovo.getCoordenador() != null) {
			cursoNovo.getCoordenador().setCargo(Cargos.COORDENADOR);
			DocenteDAO.getInstance().save(cursoNovo.getCoordenador());
		}
		cursoNovo.clear();
	}

	public CursoVO getCursoNovo() {
		return cursoNovo;
	}

	public void setCursoNovo(CursoVO cursoNovo) {
		this.cursoNovo = cursoNovo;
	}

	public void getAllDocenteSelectItem() {
		listaSelect = new ArrayList<>();
		listaSelect.addAll(DocenteDAO.getInstance().getAll());
	}

	public List<DocenteVO> getListaSelect() {
		if (listaSelect == null) {
			getAllDocenteSelectItem();
		}
		return listaSelect;
	}

	public List<DisciplinaVO> getDisciplinas(int id){
		return CursoDAO.getInstance().getDisciplinasPorCursoId(id);
	}
	
	public void onRowEdit(RowEditEvent event) {
		update((CursoVO) event.getObject());
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

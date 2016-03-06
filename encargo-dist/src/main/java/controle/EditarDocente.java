package controle;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.ArrayDataModel;

import org.primefaces.model.DualListModel;

import dao.DisciplinaMinistradaDAO;
import dao.DocenteDAO;
import util.DocentesModel;
import util.PickListDisciplinaArray;
import vo.DisciplinaVO;
import vo.DocenteDisciplinaInteresseVO;
import vo.DocenteDisciplinaMinistradaVO;
import vo.DocenteVO;

@ManagedBean
@RequestScoped
public class EditarDocente {
	private DocenteVO docenteNovo = new DocenteVO();
	private DualListModel<DisciplinaVO> model;
	private List<DisciplinaVO> disciplinasInteresse = new ArrayList<DisciplinaVO>();
	private List<DocenteDisciplinaMinistradaVO> docentesMinistradas;
	
	@PostConstruct
	public void init() {
		PickListDisciplinaArray.setDisciplina();
		setModel(new DualListModel<DisciplinaVO>(PickListDisciplinaArray.getDisciplinas(), getDisciplinasInteresseDocenteNovo()));
	}

	public static void saveOrUpdate(DocenteVO vo) {
		DocenteDAO.getInstance().saveOrUpdate(vo);
	}

	public void delete(DocenteVO vo) {
		DocenteDAO.getInstance().delete(vo);
	}

	public static List<DocenteVO> getAll() {
		return DocenteDAO.getInstance().getAll();
	}

	public void addDocente() {
		setDisciplinasInteresse(docenteNovo, model.getTarget());
		saveOrUpdate(docenteNovo);
		docenteNovo = new DocenteVO();
	}
	
	public DocenteVO getDocenteNovo() {
		return docenteNovo;
	}

	public void setDocenteNovo(DocenteVO docenteNovo) {
		this.docenteNovo = docenteNovo;
	}

	public ArrayDataModel<DocenteVO> getDocentesModel() {
		return DocentesModel.getDocentesModel();
	}

	public void setDocentesModel(ArrayDataModel<DocenteVO> docentesModel) {
		DocentesModel.setDocentesModel(docentesModel);
	}

	public DualListModel<DisciplinaVO> getModel() {
		return model;
	}

	public void setModel(DualListModel<DisciplinaVO> model) {
		this.model = model;
	}

	public List<DisciplinaVO> getDisciplinasInteresseDocenteNovo() {
		return disciplinasInteresse;
	}

	public void setDisciplinasInteresse(DocenteVO docente, List<DisciplinaVO> disciplinas) {
		int i = 0;
		for (DisciplinaVO disc : disciplinas) {
			DocenteDisciplinaInteresseVO docDisc = new DocenteDisciplinaInteresseVO();
			docDisc.setOrdem(i);
			docDisc.setDocente(docente);
			docDisc.setDisciplina(disc);
			docente.addDocenteDisciplinaInteresse(docDisc);
			i++;
		}
	}

	public List<DocenteDisciplinaMinistradaVO> gettDocentesMinistradas(int docenteId) {
		this.docentesMinistradas = DisciplinaMinistradaDAO.getInstance().getDisciplinasMinistradasMaisRecente(docenteId);
		return this.docentesMinistradas;
	}
	
}

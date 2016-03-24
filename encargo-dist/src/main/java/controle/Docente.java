package controle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.ArrayDataModel;

import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DualListModel;

import dao.DisciplinaMinistradaDAO;
import dao.DocenteDAO;
import util.DocenteDisciplinaConverter;
import util.DocentesModel;
import util.Encrypter;
import util.PickListDisciplinaArray;
import vo.DisciplinaVO;
import vo.DocenteDisciplinaInteresseVO;
import vo.DocenteDisciplinaMinistradaVO;
import vo.DocenteVO;

@ManagedBean
@RequestScoped
public class Docente {

	private DocenteVO docenteNovo = new DocenteVO();
	private DualListModel<DisciplinaVO> model;
	private Map<DocenteVO, DualListModel<DisciplinaVO>> modelEditMap = new HashMap<DocenteVO, DualListModel<DisciplinaVO>>();
	private List<DisciplinaVO> disciplinasInteresse = new ArrayList<DisciplinaVO>();
	private List<DocenteDisciplinaMinistradaVO> docentesMinistradas;
	private String senha, confirmaSenha;
	
	@PostConstruct
	public void init() {
		for(DocenteVO docente: DocenteDAO.getInstance().getAll()){
			List<DisciplinaVO> lista = PickListDisciplinaArray.getDisciplinas();
			lista.removeAll(DocenteDisciplinaConverter.parseDisciplinasInteresse(docente.getDocenteDisciplinasInteresse()));
			setModelEditMap(docente, new DualListModel<DisciplinaVO>(lista, DocenteDisciplinaConverter.parseDisciplinasInteresse(docente.getDocenteDisciplinasInteresse())));
		}
		setModel(new DualListModel<DisciplinaVO>(PickListDisciplinaArray.getDisciplinas(), getDisciplinasInteresseDocenteNovo()));
	}

	public static void saveOrUpdate(DocenteVO vo) {
		DocenteDAO.getInstance().saveOrUpdate(vo);
	}

	public void update(DocenteVO vo){
		DocenteDAO.getInstance().saveOrUpdate(vo);
	}
	
	public void delete(DocenteVO vo) {
		DocenteDAO.getInstance().delete(vo);
	}

	public static List<DocenteVO> getAll() {
		return DocenteDAO.getInstance().getAll();
	}
	
	public String addDocente() {
		if(senha.equals(confirmaSenha)){
			docenteNovo.setSenha(Encrypter.criptografar(senha));
			setDisciplinasInteresse(docenteNovo, model.getTarget());
			DocenteDAO.getInstance().saveOrUpdate(docenteNovo);
			docenteNovo = new DocenteVO();
		}
		return "";
	}
	
	public DocenteVO getDocenteNovo() {
		return docenteNovo;
	}

	public void setDocenteNovo(DocenteVO docenteNovo) {
		this.docenteNovo = docenteNovo;
	}
	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getConfirmaSenha() {
		return confirmaSenha;
	}

	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
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

	public DualListModel<DisciplinaVO> gettModelEditMap(DocenteVO docente) {
		return modelEditMap.get(docente);
	}

	public void setModelEditMap(DocenteVO docente, DualListModel<DisciplinaVO> modelEdit) {
		this.modelEditMap.put(docente, modelEdit);
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
	
	public void onRowEdit(RowEditEvent event) {
		saveOrUpdate((DocenteVO) event.getObject());
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

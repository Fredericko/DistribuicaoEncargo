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
public class EditarDocente {
	private DualListModel<DisciplinaVO> model = new DualListModel<>();
	private List<DisciplinaVO> disciplinasInteresse = new ArrayList<DisciplinaVO>();
	private List<DocenteDisciplinaMinistradaVO> docentesMinistradas;
	private DocenteVO docente;
	private boolean trocaSenha;
	private String senha, confirmaSenha;

	@PostConstruct
	public void init() {
		docente = (DocenteVO) SessionContext.getInstance().getAttribute("usuario");
		List<DisciplinaVO> lista = PickListDisciplinaArray.getDisciplinas();
		if (docente != null) {
			List<DisciplinaVO> listaTarget = DocenteDisciplinaConverter
					.parseDisciplinasInteresse(docente.getDocenteDisciplinasInteresse());
			if (listaTarget != null && !listaTarget.equals(null)) {
				lista.removeAll(listaTarget);
				model.setTarget(listaTarget);
			}
			model.setSource(lista);
		}
	}

	public void saveOrUpdate() {
		if (isTrocaSenha() && getSenha().equals(getConfirmaSenha())) {
			docente.setSenha(Encrypter.criptografar(getSenha()));
		}
		docente.setDisciplinasInteresse(model.getTarget());
		DocenteDAO.getInstance().saveOrUpdate(docente);
	}

	public static void update(DocenteVO vo) {
		DocenteDAO.getInstance().update(vo);
	}

	public void delete(DocenteVO vo) {
		DocenteDAO.getInstance().delete(vo);
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
		this.docentesMinistradas = DisciplinaMinistradaDAO.getInstance()
				.getDisciplinasMinistradasMaisRecente(docenteId);
		return this.docentesMinistradas;
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

	public boolean isTrocaSenha() {
		return trocaSenha;
	}

	public void setTrocaSenha(boolean trocaSenha) {
		this.trocaSenha = trocaSenha;
	}
}

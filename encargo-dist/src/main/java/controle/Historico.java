package controle;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.ArrayDataModel;

import dao.DisciplinaMinistradaDAO;
import vo.DisciplinaVO;
import vo.DocenteDisciplinaMinistradaVO;

@ManagedBean
@RequestScoped
public class Historico {
	private ArrayDataModel<DocenteDisciplinaMinistradaVO> model;
	private ArrayDataModel<DisciplinaVO> modelNaoSelecionada;
	private DocenteDisciplinaMinistradaVO[] docenteDisciplinaMinistradaArray;
	private DisciplinaVO[] disciplinaArray;

	public ArrayDataModel<DocenteDisciplinaMinistradaVO> getModel() {
		model = new ArrayDataModel<>(getArray(DisciplinaMinistradaDAO.getInstance().getAllOrdenadoPorAnoSemestre()));
		return model;
	}

	public ArrayDataModel<DisciplinaVO> getModelNaoSelecionada() {
		modelNaoSelecionada = new ArrayDataModel<>(
				getArrayNaoMinistrada(DisciplinaMinistradaDAO.getInstance().getDisciplinasNaoSelecionadas()));
		return modelNaoSelecionada;
	}

	public DocenteDisciplinaMinistradaVO[] getArray(List<DocenteDisciplinaMinistradaVO> docDiscMin) {
		docenteDisciplinaMinistradaArray = new DocenteDisciplinaMinistradaVO[docDiscMin.size()];
		int i = 0;
		for (DocenteDisciplinaMinistradaVO docDisc : docDiscMin) {
			docenteDisciplinaMinistradaArray[i] = docDisc;
			i++;
		}
		return docenteDisciplinaMinistradaArray;
	}

	public DisciplinaVO[] getArrayNaoMinistrada(List<DisciplinaVO> disciplinas) {
		disciplinaArray = new DisciplinaVO[disciplinas.size()];
		int i = 0;
		for (DisciplinaVO disc : disciplinas) {
			disciplinaArray[i] = disc;
			i++;
		}
		return disciplinaArray;
	}

	public String getSemestreAtual() {
		Object[] semestreAtual = DisciplinaMinistradaDAO.getInstance().getAnoSemestreAtual();
		if(semestreAtual[0] == null)return null;
		return semestreAtual[0].toString() + "/" + semestreAtual[1].toString();
	}

}

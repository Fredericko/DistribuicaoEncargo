package controle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import dao.DocenteDAO;
import java.util.ArrayList;
import java.util.List;
import javax.faces.model.ArrayDataModel;
import vo.DocenteVO;

@ManagedBean
@SessionScoped
public class EditarDocente {

	private DocenteVO docenteNovo = new DocenteVO();
	private static ArrayDataModel<DocenteVO> docentes;

	public static void update(DocenteVO vo) {
		DocenteDAO.getInstance().save(vo);
	}

	public void delete(DocenteVO vo) {
		DocenteDAO.getInstance().delete(vo);
	}

	public List<DocenteVO> getAll() {
		return DocenteDAO.getInstance().getAll();
	}

	public void addDocente() {
		update(docenteNovo);
		docenteNovo = new DocenteVO();
	}

	public ArrayDataModel<DocenteVO> getDocentes() {
		ArrayList<DocenteVO> docenteArrayList = new ArrayList<DocenteVO>(getAll());
		DocenteVO[] disciplinaArray = new DocenteVO[docenteArrayList.size()];
		int i = 0;
		for (DocenteVO dos : docenteArrayList) {
			disciplinaArray[i] = dos;
			i++;
		}
		docentes = new ArrayDataModel<DocenteVO>(disciplinaArray);
		return docentes;
	}

	public DocenteVO getDocenteNovo() {
		return docenteNovo;
	}

	public void setDocenteNovo(DocenteVO docenteNovo) {
		this.docenteNovo = docenteNovo;
	}

	public static void setDocentes(ArrayDataModel<DocenteVO> docentes) {
		EditarDocente.docentes = docentes;
	}

	
}

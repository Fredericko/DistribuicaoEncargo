package controle;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.ArrayDataModel;
import javax.faces.model.DataModel;

import dao.CursoDAO;
import vo.CursoVO;

@ManagedBean
@SessionScoped
public class EditarCurso {
	private CursoVO cursoNovo = new CursoVO();
	private static DataModel<CursoVO> cursos;
	
	public static void update(CursoVO vo) {
		CursoDAO.getInstance().save(vo);
	}

	public void delete(CursoVO vo) {
		CursoDAO.getInstance().delete(vo);
	}

	public static List<CursoVO> getAll(){
		return CursoDAO.getInstance().getAll();
	}

	public DataModel<CursoVO> getCursos() {
		ArrayList<CursoVO> cursoArrayList =  new ArrayList<CursoVO>(getAll()); 
		CursoVO[] cursoArray = new CursoVO[cursoArrayList.size()];
		int i=0;
		for(CursoVO cur: cursoArrayList){
			cursoArray[i] = cur;
			i++;
		}
		cursos = new ArrayDataModel<CursoVO>(cursoArray);
		return cursos;
	}
	
	public void addCursoNovo(){
		update(cursoNovo);
		cursoNovo = new CursoVO();
	}

	public CursoVO getCursoNovo() {
		return cursoNovo;
	}

	public void setCursoNovo(CursoVO cursoNovo) {
		this.cursoNovo = cursoNovo;
	}
}

package negocio;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.ArrayDataModel;
import javax.faces.model.DataModel;

import dao.DisciplinaDAO;
import vo.DisciplinaVO;

@ManagedBean
@SessionScoped
public class EditarDisciplina {
	
	private DisciplinaVO disciplinaNova = new DisciplinaVO();
	private static DataModel<DisciplinaVO> disciplinas;
	
	public void update(DisciplinaVO vo) {
		DisciplinaDAO.getInstance().save(vo);
	}

	public void delete(DisciplinaVO vo) {
		DisciplinaDAO.getInstance().delete(vo);
	}
	
	public List<DisciplinaVO> getAll(){
		return DisciplinaDAO.getInstance().getAll();
	}
	
	public void addDisciplina(){
		update(disciplinaNova);
		disciplinaNova = new DisciplinaVO();
	}
	
	public DataModel<DisciplinaVO> getDisciplinas() {
		ArrayList<DisciplinaVO> disciplinaArrayList =  new ArrayList<DisciplinaVO>(getAll()); 
		DisciplinaVO[] disciplinaArray = new DisciplinaVO[disciplinaArrayList.size()];
		int i=0;
		for(DisciplinaVO dis: disciplinaArrayList){
			disciplinaArray[i] = dis;
			i++;
		}
		disciplinas = new ArrayDataModel<DisciplinaVO>(disciplinaArray);
		return disciplinas;
	}
	
	public static void setDisciplinas(DataModel<DisciplinaVO> disciplinas) {
		EditarDisciplina.disciplinas = disciplinas;
	}

	public DisciplinaVO getDisciplinaNova() {
		return disciplinaNova;
	}

	public void setDisciplinaNova(DisciplinaVO disciplinaNova) {
		this.disciplinaNova = disciplinaNova;
	}
	
}

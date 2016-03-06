package controle;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.ArrayDataModel;
import javax.faces.model.DataModel;

import org.primefaces.event.RowEditEvent;

import dao.DisciplinaDAO;
import util.PickListDisciplinaArray;
import vo.DisciplinaVO;

@ManagedBean
@SessionScoped
public class Disciplina {
	
	private DisciplinaVO disciplinaNova = new DisciplinaVO();
	private static DataModel<DisciplinaVO> disciplinas;
	
	public void save(DisciplinaVO vo) {
		DisciplinaDAO.getInstance().save(vo);
		PickListDisciplinaArray.setDisciplina();
	}

	public void delete(DisciplinaVO vo) {
		DisciplinaDAO.getInstance().delete(vo);
		PickListDisciplinaArray.setDisciplina();
	}
	
	public static List<DisciplinaVO> getAll(){
		return DisciplinaDAO.getInstance().getAll();
	}
	
	public void addDisciplina(){
		save(disciplinaNova);
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
		Disciplina.disciplinas = disciplinas;
	}

	public DisciplinaVO getDisciplinaNova() {
		return disciplinaNova;
	}

	public void setDisciplinaNova(DisciplinaVO disciplinaNova) {
		this.disciplinaNova = disciplinaNova;
	}
	
	public void update(DisciplinaVO vo){
		DisciplinaDAO.getInstance().update(vo);
	}

	public static List<DisciplinaVO> getDisponiveis() {
		return DisciplinaDAO.getInstance().getDisciplinasDisponiveis();
	}
	
	public void onRowEdit(RowEditEvent event) {
		update((DisciplinaVO) event.getObject());
    }
	
}

package controle;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.ArrayDataModel;
import javax.faces.model.DataModel;

import org.primefaces.model.DualListModel;

import dao.CursoDAO;
import util.PickListDisciplinaArray;
import vo.CursoVO;
import vo.DisciplinaVO;

@ManagedBean
@RequestScoped
public class Curso {

    private CursoVO cursoNovo = new CursoVO();
    private static DataModel<CursoVO> cursos;
    private DualListModel<DisciplinaVO> model;
    
    @PostConstruct
    public void init(){
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
    
    public static void update(CursoVO vo){
    	CursoDAO.getInstance().update(vo);
    }

    public void delete(CursoVO vo) {
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

    public void addCursoNovo() {
        if (!cursoNovo.getNome().isEmpty()||!cursoNovo.getDuracao().isEmpty()) {
            cursoNovo.setDisciplinas(model.getTarget());
            save(cursoNovo);
            cursoNovo.clear();
        }
    }

    public CursoVO getCursoNovo() {
        return cursoNovo;
    }

    public void setCursoNovo(CursoVO cursoNovo) {
        this.cursoNovo = cursoNovo;
    }

}

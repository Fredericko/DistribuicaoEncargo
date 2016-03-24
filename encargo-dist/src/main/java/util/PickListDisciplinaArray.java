package util;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.RequestScoped;

import controle.Disciplina;
import vo.DisciplinaVO;

@RequestScoped
public class PickListDisciplinaArray {
	private static ArrayList<DisciplinaVO> disciplinaArrayList;
	
	public static List<DisciplinaVO> getDisciplinas(){
		setDisciplina();
		return disciplinaArrayList;
	}
	
	public static void setDisciplina(){
		disciplinaArrayList = new ArrayList<>(Disciplina.getDisponiveis());
	}
	
}

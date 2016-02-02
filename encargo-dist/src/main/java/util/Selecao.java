package util;

import java.util.ArrayList;

import vo.DisciplinaVO;
import vo.DocenteVO;

public class Selecao {
	ArrayList<DocenteVO> docentes;
	ArrayList<DisciplinaVO> disciplinas;

	public void seleciona(ArrayList<DocenteVO> docentes, ArrayList<DisciplinaVO> disciplinas) {
		this.docentes = docentes;
		this.disciplinas = disciplinas;
		
		for (DisciplinaVO disc : disciplinas) {
			for (DocenteVO doc : docentes) {
				
			}
		}
	}
}

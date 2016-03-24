package util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import vo.DisciplinaVO;
import vo.DocenteDisciplinaInteresseVO;
import vo.DocenteDisciplinaMinistradaVO;

public class DocenteDisciplinaConverter {
	private static List<DisciplinaVO> disciplinasArray = new ArrayList<>();
	
	public static List<DisciplinaVO> parseDisciplinasInteresse(Set<DocenteDisciplinaInteresseVO> disciplinas){
		disciplinasArray = new ArrayList<>();
		for (DocenteDisciplinaInteresseVO docDis : disciplinas) {
			disciplinasArray.add(docDis.getDisciplina());
		}
		return disciplinasArray;
	}

	public static List<DisciplinaVO> parseDisciplinasMinistrada(Set<DocenteDisciplinaMinistradaVO> disciplinas){
		disciplinasArray = new ArrayList<>();
		for (DocenteDisciplinaMinistradaVO docDis : disciplinas) {
			disciplinasArray.add(docDis.getDisciplina());
		}
		return disciplinasArray;
	}
	
	
	
}

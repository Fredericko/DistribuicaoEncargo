package util;

import java.util.ArrayList;

import javax.faces.model.ArrayDataModel;

import controle.Docente;
import vo.DocenteVO;

public class DocentesModel {
	private static ArrayDataModel<DocenteVO> docentesModel;
	private static ArrayList<DocenteVO> docenteArrayList;

	public static ArrayDataModel<DocenteVO> getDocentesModel() {
		docenteArrayList = new ArrayList<DocenteVO>(Docente.getAll());
		DocenteVO[] disciplinaArray = new DocenteVO[docenteArrayList.size()];
		int i = 0;
		for (DocenteVO dos : docenteArrayList) {
			disciplinaArray[i] = dos;
			i++;
		}
		docentesModel = new ArrayDataModel<DocenteVO>(disciplinaArray);
		return docentesModel;
	}
	
	public static void setDocentesModel(ArrayDataModel<DocenteVO> docentesModel) {
		DocentesModel.docentesModel = docentesModel;
	}

	public static ArrayList<DocenteVO> getDocenteArrayList() {
		return docenteArrayList;
	}
	
}

package negocio;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.annotation.ManagedBean;
import javax.faces.bean.SessionScoped;

import vo.DisciplinaVO;
import vo.DocenteVO;

@ManagedBean
@SessionScoped
public class Selecao {

	private ArrayList<DocenteVO> docentesEfetivos = new ArrayList<>();
	private ArrayList<DocenteVO> docentesSubstitutos = new ArrayList<>();
	private ArrayList<DocenteVO> listaSelecao = new ArrayList<DocenteVO>();
	private Set<DisciplinaVO> disciplinasSelecionadas = new HashSet<DisciplinaVO>();
	private Set<DocenteVO> listaRemoverSelecao = new HashSet<DocenteVO>();
	private DisciplinaVO disc = new DisciplinaVO();

	public void selecionarDisciplinas(ArrayList<DocenteVO> docentes) {
		limparVariaveis();
		for (DocenteVO doc : docentes) {
			doc.getDisciplinasMinistradas().clear();
			doc.getDocenteDisciplinasMinistradas().clear();
			if (doc.getContratoNatureza().equals("Efetivo")) {
				docentesEfetivos.add(doc);
			} else {
				docentesSubstitutos.add(doc);
			}
		}
		seleciona(docentesEfetivos);
		seleciona(docentesSubstitutos);
	}


	private void limparVariaveis() {
		listaSelecao.clear();
		docentesEfetivos.clear();
		docentesSubstitutos.clear();
		listaRemoverSelecao.clear();
		disciplinasSelecionadas.clear();
		disc = new DisciplinaVO();
	}


	private void seleciona(ArrayList<DocenteVO> docentes) {
		DocenteVO maiorLista = docentes.get(0);
		
		for (DocenteVO doc : docentes) {
			if (doc.getDisciplinasInteresse().size() > maiorLista.getDisciplinasInteresse().size()) {
				maiorLista = doc;
			}
		}
		for (int i = 0; i < maiorLista.getDisciplinasInteresse().size(); i++) {
			for (DocenteVO doc : docentes) {
				listaSelecao.clear();
				if (doc.getDisciplinasInteresse().isEmpty()) {
					continue;
				}
				if (!doc.getDisciplinasInteresse().isEmpty() && i < doc.getDisciplinasInteresse().size()) {
					disc = doc.getDisciplinasInteresse().get(i);
				}
				if ((doc.getHorasMinistradas() + disc.getChSemanal()) > doc.getRegimeTrabalho()) {
					continue;
				}
				if (disciplinasSelecionadas.contains(disc)) {
					continue;
				}
				for (DocenteVO doc2 : docentes) {
					if (!listaSelecao.contains(doc2)) {
						if (doc2.getDisciplinasInteresse().size() > 0 && i < doc2.getDisciplinasInteresse().size()) {
							if (doc2.getDisciplinaInteresse(i) == disc) {
								listaSelecao.add(doc2);
							}
						}
					}
				}
				if (listaSelecao.size() == 1) {
					if (!listaSelecao.get(0).getDisciplinasMinistradas().contains(disc)) {
						listaSelecao.get(0).getDisciplinasMinistradas().add(disc);
						listaSelecao.get(0).setContemplacao(doc.getContemplacao() + 1);
						disciplinasSelecionadas.add(disc);
					}
				} else if (listaSelecao.size() > 1) {
					selecionaPorContemplacao();
				}
			}
		}
	}

	private void selecionaPorContemplacao() {
		int igual = 0;
		for (int a = 0; a < listaSelecao.size()-1; a++) {
			if (listaSelecao.get(a).getContemplacao() == listaSelecao.get(a + 1).getContemplacao()) {
				igual++;
			}
			if (listaSelecao.get(a).getContemplacao() > listaSelecao.get(a + 1).getContemplacao()) {
				listaRemoverSelecao.add(listaSelecao.get(a));
			}
			if (listaSelecao.get(a).getContemplacao() < listaSelecao.get(a + 1).getContemplacao()) {
				listaRemoverSelecao.add(listaSelecao.get(a + 1));
			}
		}
		listaSelecao.removeAll(listaRemoverSelecao);
		listaRemoverSelecao.clear();
		if (igual > 0) {
			Random rand = new Random(0);
			for (int i = 0; i < 100; i++) {
				int num = rand.nextInt(listaSelecao.size());
				System.out.println(num);
			}
			int num = rand.nextInt(listaSelecao.size());
			if (!listaSelecao.get(0).getDisciplinasMinistradas().contains(disc)) {
				listaSelecao.get(num).getDisciplinasMinistradas().add(disc);
				listaSelecao.get(num).setContemplacao(listaSelecao.get(num).getContemplacao() + 1);
				disciplinasSelecionadas.add(disc);
			}
		} else {
			if (!listaSelecao.get(0).getDisciplinasMinistradas().contains(disc)) {
				listaSelecao.get(0).getDisciplinasMinistradas().add(disc);
				listaSelecao.get(0).setContemplacao(listaSelecao.get(0).getContemplacao() + 1);
				disciplinasSelecionadas.add(disc);
			}
		}
	}

}

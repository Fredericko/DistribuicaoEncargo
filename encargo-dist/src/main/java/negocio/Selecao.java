package negocio;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import enums.ContratoNatureza;
import vo.DisciplinaVO;
import vo.DocenteVO;

public class Selecao {

	private ArrayList<DocenteVO> docentesEfetivos = new ArrayList<>();
	private ArrayList<DocenteVO> docentesSubstitutos = new ArrayList<>();
	private ArrayList<DocenteVO> listaSelecao = new ArrayList<DocenteVO>();
	private Set<DisciplinaVO> disciplinasSelecionadas = new HashSet<DisciplinaVO>();
	private Set<DocenteVO> listaRemoverSelecao = new HashSet<DocenteVO>();
	private DisciplinaVO disc = new DisciplinaVO();

	public void selecionarDisciplinas(final List<DocenteVO> docentes) {
		limparVariaveis();
		for (DocenteVO doc : docentes) {
			doc.getDocenteDisciplinasMinistradas().clear();
			if (doc.getContratoNatureza().equals(ContratoNatureza.EFETIVO)) {
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


	private void seleciona(List<DocenteVO> docentes) {
		DocenteVO maiorLista = docentes.get(0);
		
		for (DocenteVO doc : docentes) {
			if (doc.getDocenteDisciplinasInteresse().size() > maiorLista.getDocenteDisciplinasInteresse().size()) {
				maiorLista = doc;
			}
		}
		for (int i = 0; i < maiorLista.getDocenteDisciplinasInteresse().size(); i++) {
			for (DocenteVO doc : docentes) {
				listaSelecao.clear();
				if (doc.getDocenteDisciplinasInteresse().isEmpty()) {
					continue;
				}
				if (!doc.getDocenteDisciplinasInteresse().isEmpty() && i < doc.getDocenteDisciplinasInteresse().size()) {
					disc = doc.getDocenteDisciplinaInteresse(i).getDisciplina();
				}
				if ((doc.getHorasMinistradas() + disc.getChSemanal()) > doc.getRegimeTrabalho()) {
					continue;
				}
				if (disciplinasSelecionadas.contains(disc)) {
					continue;
				}
				for (DocenteVO doc2 : docentes) {
					if (!listaSelecao.contains(doc2)) {
						if (doc2.getDocenteDisciplinasInteresse().size() > 0 && i < doc2.getDocenteDisciplinasInteresse().size()) {
							if (doc2.getDocenteDisciplinaInteresse(i).getDisciplina() == disc) {
								listaSelecao.add(doc2);
							}
						}
					}
				}
				if (listaSelecao.size() == 1) {
					if (!listaSelecao.get(0).getDocenteDisciplinasMinistradas().contains(disc)) {
						listaSelecao.get(0).addDisciplinaMinistrada(disc);
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
			Random rand = new Random();
			int num = rand.nextInt(listaSelecao.size());
			if (!listaSelecao.get(0).getDocenteDisciplinasMinistradas().contains(disc)) {
				listaSelecao.get(num).addDisciplinaMinistrada(disc);
				listaSelecao.get(num).setContemplacao(listaSelecao.get(num).getContemplacao() + 1);
				disciplinasSelecionadas.add(disc);
			}
		} else {
			if (!listaSelecao.get(0).getDocenteDisciplinasMinistradas().contains(disc)) {
				listaSelecao.get(0).addDisciplinaMinistrada(disc);
				listaSelecao.get(0).setContemplacao(listaSelecao.get(0).getContemplacao() + 1);
				disciplinasSelecionadas.add(disc);
			}
		}
	}

}

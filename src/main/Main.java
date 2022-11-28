package main;

import dao.FitaDao;
import dao.FitaGenerosDao;
import exceptions.DaoExceptions;
import model.Fita;
import model.FitaGenero;
import model.Genero;

public class Main {

	public static void main(String args[]) {
		/*GeneroDao dao = new GeneroDao();
		Genero g1 = new Genero(null, "Drama");
		
		try {
			g1 = dao.insert(g1);
			System.out.println(g1);
		} catch (DaoExceptions e) {
			System.out.println(e.getMessage());
		}
		
		Genero g2 = new Genero(null, "te");
		try {
			g2 = dao.insert(g2);
		} catch (DaoExceptions e) {
			System.out.println(e.getMessage());
		}
		
		try {
			System.out.println(dao.get(1));
			System.out.println(dao.get(null));
			
			Genero g3 = dao.get(3);
			System.out.println(g3);
			
			g3.setNome("Ficção Científica");
			System.out.println("update = "+dao.update(g3));
			
			if(dao.delete(g3.getId())) {
				System.out.println(g3.getNome()+" DELETADO");
			}
			System.out.println(dao.get(3));
			
		} catch (DaoExceptions e) {
			System.out.println(e.getMessage());
		}*/
		
		FitaDao daoFita = new FitaDao();
		
		Fita f = new Fita();
		f.setAnoLancamento(2022);
		f.setDuracao("01:30");
		f.setNome("Dr Estranho 2");
		f.setSinopse("A Wanda tá doidinha da cabeça e sai destruindo geral em vários universos");
		
		try {
			f = daoFita.insert(f);
			System.out.println(f);
		} catch (DaoExceptions e) {
			System.out.println(e.getMessage());
		}
		
		FitaGenerosDao daoFg = new FitaGenerosDao();
		FitaGenero fg = new FitaGenero(f, new Genero(1));
		try {
			fg = daoFg.insert(fg);
			System.out.println(fg);
		} catch (DaoExceptions e) {
			System.out.println(e.getMessage());
		}
		
		fg = new FitaGenero(f, new Genero(10));
		try {
			fg = daoFg.insert(fg);
			System.out.println(f);
		} catch (DaoExceptions e) {
			System.out.println(e.getMessage());
		}
		
		/*Fita f2 = null;
		try {
			f2 = daoFita.insert(f2);
			System.out.println(f);
		} catch (DaoExceptions e) {
			System.out.println(e.getMessage());
		}
		
		try {
			f2 = daoFita.get(1);
			System.out.println(f2);
			System.out.println(daoFita.get(null));
			
			f2.setSinopse("Um maluco que vai pra o futuro depois pro passado para corrigir o futuro que ele quebrou");
			System.out.println("update = "+daoFita.update(f2));
			
			if(daoFita.delete(f2.getId())) {
				System.out.println(f2.getNome()+" DELETADO");
			}
			System.out.println(daoFita.get(1));
			
		} catch (DaoExceptions e) {
			System.out.println(e.getMessage());
		}*/
	}
}
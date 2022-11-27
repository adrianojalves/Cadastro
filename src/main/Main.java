package main;

import dao.FitasDao;
import exceptions.DaoExceptions;
import model.Fita;

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
		
		FitasDao daoFita = new FitasDao();
		
		Fita f = new Fita();
		f.setAnoLancamento(1985);
		f.setDuracao("01:30");
		f.setNome("De volta para o futuro");
		
		try {
			f = daoFita.insert(f);
			System.out.println(f);
		} catch (DaoExceptions e) {
			System.out.println(e.getMessage());
		}
		
		Fita f2 = null;
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
		}
	}
}
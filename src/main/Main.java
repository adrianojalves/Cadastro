package main;

import dao.GeneroDao;
import exceptions.DaoExceptions;
import model.Genero;

public class Main {

	public static void main(String args[]) {
		GeneroDao dao = new GeneroDao();
		/*Genero g1 = new Genero(null, "Drama");
		
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
		}*/
		
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
		}
	}
}
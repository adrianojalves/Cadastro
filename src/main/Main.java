package main;

import bdutils.ConnectionBD;

public class Main {

	public static void main(String args[]) {
		System.out.println("Testando a conexão");
		
		if(ConnectionBD.getConexao() != null) {
			System.out.println("Banco de dados conectado");
			
			ConnectionBD.close();
		}
		else {
			System.out.println("Erro: "+ConnectionBD.getErro());
		}
	}
}
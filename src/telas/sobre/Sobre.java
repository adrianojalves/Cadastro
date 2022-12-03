package telas.sobre;

import javax.swing.JDialog;
import javax.swing.JLabel;

public class Sobre extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Sobre() {
		super();
		setModal(true);
		
		setSize(550, 100);
		
		setLocationRelativeTo(null);
		
		setTitle("Sobre");
		
		criarTexto();
	}

	private void criarTexto() {
		String msg  = "Sistema Desenvolvido no projeto de extensão UNINASSAU DEZ/2022";
		
		JLabel texto = new JLabel(msg);
		
		add(texto);
	}

}
package telas;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import telas.sobre.Sobre;

public class Principal extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JMenuBar menuBar;
	private JMenu cadastros, ajuda;
	private JMenuItem generos, fitas, sobre;

	public Principal() throws HeadlessException {
		super("Meu Cadastro");
		
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		criarMenu();
	}

	private void criarMenu() {
		generos = new JMenuItem("Gêneros");
		generos.addActionListener(this);
		
		fitas = new JMenuItem("Fitas");
		
		sobre = new JMenuItem("Sobre");
		sobre.addActionListener(this);
		
		cadastros = new JMenu("Cadastros");
		ajuda = new JMenu("Ajuda");
		
		cadastros.add(fitas);
		cadastros.addSeparator();
		cadastros.add(generos);
		
		ajuda.add(sobre);
		
		menuBar = new JMenuBar();
		menuBar.add(cadastros);
		menuBar.add(ajuda);
		
		setJMenuBar(menuBar);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(sobre)) {
			new Sobre().setVisible(true);
		}
		else if(e.getSource().equals(generos)) {
			new TelaListaGeneros().setVisible(true);
		}
	}

	
}
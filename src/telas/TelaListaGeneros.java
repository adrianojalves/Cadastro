package telas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import dao.GeneroDao;
import exceptions.DaoExceptions;
import model.Genero;

public class TelaListaGeneros extends JDialog implements KeyListener, ActionListener, MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton bNovo;
	private JTextField txtNome;
	private List<Genero> listaGeneros;
	private GeneroDao generoDao;
	private JTable tabela;
	private DefaultTableModel modelo;
	private JPanel panelBusca;

	public TelaListaGeneros() throws HeadlessException {
		Dimension dimensao = Toolkit.getDefaultToolkit().getScreenSize();
		
		int width = dimensao.width*70/100;
		int height = dimensao.height*70/100;
		setSize(width, height);
		
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		
		setTitle("Lista de Gêneros");
		
		setLocationRelativeTo(null);
		
		setLayout(null);
		
		setModal(true);
		
		setBackground(Color.DARK_GRAY);
		
		generoDao = new GeneroDao();
		
		setarCamposNaTela();
		
		criarTabela();
		
		carregarGeneros();
	}

	private void setarCamposNaTela() {
		JToolBar topo = new JToolBar();
		topo.setSize(this.getWidth(), 50);
		topo.setBackground(Color.LIGHT_GRAY);
		
		ImageIcon newImage = new ImageIcon("imagens/new.png");
		bNovo = new JButton(newImage);
		bNovo.setBounds(1, 1, 48, 48);
		bNovo.setToolTipText("Novo Gênero");
		topo.add(bNovo);
		
		this.getContentPane().add(topo);
		
		panelBusca = new JPanel();
		panelBusca.setLayout(null);
		panelBusca.setBackground(Color.LIGHT_GRAY);
		panelBusca.setBounds(20, 70, this.getWidth()-40, 60);
		
		JLabel lblNome = new JLabel("Nome: ", SwingConstants.RIGHT);
		lblNome.setForeground(Color.BLACK);
		lblNome.setBounds(5, 15, 100, 30);
		panelBusca.add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setBounds(110, 15, 300, 30);
		txtNome.addKeyListener(this);
		panelBusca.add(txtNome);
		
		this.getContentPane().add(panelBusca);
	}
	
	private void criarTabela() {
		int tableWidth = panelBusca.getWidth();;
		
		modelo = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
		
		tabela = new JTable(modelo);
		tabela.addMouseListener(this);
		tabela.addKeyListener(this);
		
		modelo.addColumn("Código");
		modelo.addColumn("Nome");
		tabela.getColumnModel().getColumn(0)
				.setPreferredWidth(60);
		tabela.getColumnModel().getColumn(1)
				.setPreferredWidth(tableWidth-40);
		
		JScrollPane barraRolagem = new JScrollPane(tabela);
		barraRolagem.setBounds(20, 150, this.getWidth()-40, this.getHeight()-240);
		this.getContentPane().add(barraRolagem);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			if(e.getSource().equals(txtNome)) {
				carregarGeneros();
			}
		}
		else if(e.getKeyCode()==KeyEvent.VK_DELETE) {
			JTable table =(JTable) e.getSource();
			
			if(table.getSelectedRow()!= -1) {
				Genero g = listaGeneros.get(table.getSelectedRow());
				
				int option = JOptionPane
						.showConfirmDialog(table, "Confirma a exclusão do gênero: "+g.getNome(),
								"Confirmação", JOptionPane.YES_NO_OPTION);
				
				if(option==JOptionPane.YES_OPTION) {
					try {
						if(generoDao.delete(g.getId())) {
							JOptionPane.showMessageDialog(table, g.getNome()+" Excluído", "Informação", JOptionPane.INFORMATION_MESSAGE);
							carregarGeneros();
						}
					} catch (DaoExceptions ex) {
						JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}
	}

	
	private void carregarGeneros() {
		try {
			listaGeneros = generoDao.list(txtNome.getText());
			modelo.setNumRows(0);
			
			for (Genero g : listaGeneros) {
				modelo.addRow(new Object[]{g.getId(), g.getNome()});
			}
			
		} catch (DaoExceptions e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(bNovo)) {
			
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		JTable table =(JTable) e.getSource();
        if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {
           Genero g = listaGeneros.get(table.getSelectedRow());
           System.out.println(g);
        }
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}
}
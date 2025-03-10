package tela;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import banco.ProdutoDao;
import dominio.Estoque;
import dominio.Produto;

public class BuscarProduto extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuscarProduto frame = new BuscarProduto();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BuscarProduto() {
		setTitle("Buscar Produto");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 862, 415);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Buscar", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBackground(new Color(192, 192, 192));
		panel.setBounds(47, 30, 235, 298);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Buscar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					buscar();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(57, 223, 85, 21);
		panel.add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(57, 127, 132, 19);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(57, 79, 132, 19);
		panel.add(textField_1);
		
		JLabel lblNewLabel = new JLabel("Nome");
		lblNewLabel.setBounds(57, 56, 45, 13);
		panel.add(lblNewLabel);
		
		JLabel lblPreo = new JLabel("Preço");
		lblPreo.setBounds(57, 108, 45, 13);
		panel.add(lblPreo);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(57, 173, 132, 19);
		panel.add(textField_2);
		
		JLabel lblValidade = new JLabel("Validade");
		lblValidade.setBounds(57, 156, 45, 13);
		panel.add(lblValidade);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Resultado", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setLayout(null);
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(419, 30, 370, 298);
		contentPane.add(panel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 350, 278);
		panel_1.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nome", "Pre\u00E7o", "Validade", "Estoque"
			}
		));
		scrollPane.setViewportView(table);
	}

	protected void buscar() throws ClassNotFoundException, SQLException {
		if((textField.getText() == null || textField.getText().isEmpty()) && (textField_1.getText() == null || textField_1.getText().isEmpty())  && (textField_2.getText() == null || textField_2.getText().isEmpty())) {
			JOptionPane.showMessageDialog(null, "Algum campo precisa está preenchido para buscar.");
			return;
		}
		
		ProdutoDao dao = new ProdutoDao();
		List<Produto> pro = new ArrayList<>();
		
		pro = dao.buscarProduto(textField_1.getText(), textField.getText(),textField_2.getText());
		
		DefaultTableModel modelo = new DefaultTableModel(new String[] {  "Nome ", "Preço","Validade","Estoque" }, 0);
		
		for (int i = 0; i < pro.size(); i++) {
			Produto p = pro.get(i);
			

			modelo.addRow(new String[] { p.getNome(), p.getPreco() ,p.getValidade(),p.getTipodeestoque().getNometipo()});
		}

		table.setModel(modelo);
		
	}

}

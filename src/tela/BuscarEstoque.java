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
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import banco.EstoqueDao;
import dominio.Estoque;

public class BuscarEstoque extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuscarEstoque frame = new BuscarEstoque();
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
	public BuscarEstoque() {
		setTitle("Buscar Estoque");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 817, 463);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Buscar", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBackground(new Color(192, 192, 192));
		panel.setBounds(51, 48, 251, 307);
		contentPane.add(panel);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(61, 99, 127, 19);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(61, 176, 127, 19);
		panel.add(textField_1);
		
		JLabel lblNewLabel = new JLabel("Nome do estoque");
		lblNewLabel.setBounds(61, 79, 127, 13);
		panel.add(lblNewLabel);
		
		JLabel lblLocalDoEstoque = new JLabel("Local do estoque");
		lblLocalDoEstoque.setBounds(61, 153, 127, 13);
		panel.add(lblLocalDoEstoque);
		
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
		btnNewButton.setBounds(61, 234, 85, 21);
		panel.add(btnNewButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Resultado", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setLayout(null);
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(411, 48, 251, 307);
		contentPane.add(panel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 231, 287);
		panel_1.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nome", "Local"
			}
		));
		scrollPane.setViewportView(table);
	}

	protected void buscar() throws ClassNotFoundException, SQLException {
		if((textField.getText() == null || textField.getText().isEmpty()) && (textField_1.getText() == null || textField_1.getText().isEmpty())) {
			JOptionPane.showMessageDialog(null, "Algum campo precisa está preenchido para buscar.");
			return;
		}
		
		EstoqueDao dao = new EstoqueDao();
		List<Estoque> estoques = new ArrayList<>();
		
		estoques = dao.buscarEstoque(textField.getText(), textField_1.getText());
		
		DefaultTableModel modelo = new DefaultTableModel(new String[] {  "Nome ", "Local" }, 0);
		
		for (int i = 0; i < estoques.size(); i++) {
			Estoque e = estoques.get(i);
			

			modelo.addRow(new String[] { e.getNometipo(), e.getLugar() ,});
		}

		table.setModel(modelo);
		
	}
}

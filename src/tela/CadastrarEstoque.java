package tela;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import banco.EstoqueDao;
import dominio.Estoque;

public class CadastrarEstoque extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastrarEstoque frame = new CadastrarEstoque();
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
	public CadastrarEstoque() {
		setTitle("Cadastrar Estoque");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 672, 417);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Cadastrar Tipo de Estoque", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBackground(new Color(192, 192, 192));
		panel.setBounds(71, 48, 507, 279);
		contentPane.add(panel);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(138, 93, 257, 19);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(138, 139, 257, 19);
		panel.add(textField_1);
		
		JLabel lblNewLabel = new JLabel("Nome do estoque");
		lblNewLabel.setBounds(141, 75, 107, 19);
		panel.add(lblNewLabel);
		
		JLabel lblLocalDoEstoque = new JLabel("Local do estoque");
		lblLocalDoEstoque.setBounds(138, 122, 107, 13);
		panel.add(lblLocalDoEstoque);
		
		JButton btnNewButton = new JButton("Cadastrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					cadastrar();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(140, 204, 140, 21);
		panel.add(btnNewButton);
	}

	protected void cadastrar() throws ClassNotFoundException, SQLException {
		Estoque e = new Estoque();
		EstoqueDao dao = new EstoqueDao();
		e.setNometipo(textField.getText());
		e.setLugar(textField_1.getText());
		
		dao.adicionarEstoque(e);
		
	}
}

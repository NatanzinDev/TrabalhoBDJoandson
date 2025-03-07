package banco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import dominio.Estoque;

public class EstoqueDao {
	public void cadastrarEstoque(Estoque e) throws SQLException, ClassNotFoundException {
Connection conexao = FabricaConexao.criarConexao();
		
		String sql = "INSERT INTO estoque (nome,lugar) VALUES (?,?)";
					
		
		PreparedStatement comando = conexao.prepareStatement(sql);
		
		comando.setString(1, e.getNometipo());
		comando.setString(2, e.getLugar());
		
	
		comando.execute();
		
		System.out.println("Fechando ...");
		comando.close();
		conexao.close();
		
		JOptionPane.showMessageDialog(null,"Estoque cadastrado!","Info",JOptionPane.INFORMATION_MESSAGE);
	}

	public void adicionarEstoque(Estoque e) throws ClassNotFoundException, SQLException {
		Connection conexao = FabricaConexao.criarConexao();
		
		String sql = " INSERT INTO  estoque (nome,lugar) VALUES (?,?) ";

		PreparedStatement comando = conexao.prepareStatement(sql);
		comando.setString(1, e.getNometipo());
		comando.setString(2, e.getLugar());
		comando.execute();

		comando.close();
		conexao.close();

		JOptionPane.showMessageDialog(null, "Estoque Cadastrado!");
		
	}
}

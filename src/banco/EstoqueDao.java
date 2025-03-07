package banco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

	public List<Estoque> buscarEstoqueaPeloNome(String nome) throws ClassNotFoundException, SQLException {
		Connection conexao = FabricaConexao.criarConexao();

		String sql = " SELECT * FROM estoque WHERE 1 = 1 ";

		if (nome != null && !nome.isEmpty()) {
			sql += " AND upper(nome) LIKE ? ";

		}

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		int i = 1;

		if (nome != null && !nome.isEmpty()) {
			comando.setString(i++, "%" + nome.toUpperCase() + "%");

		}

		ResultSet resultado = comando.executeQuery();

		List<Estoque> ec = new ArrayList<>();

		while (resultado.next()) {
			Estoque e = new Estoque();
			e.setId(resultado.getInt("idestoque"));
			e.setNometipo(resultado.getString("nome"));
			e.setLugar(resultado.getString("lugar"));

			ec.add(e);
		}

		return ec;
	}

	public List<Estoque> buscarEstoque(String text, String text2) throws ClassNotFoundException, SQLException {
		Connection conexao = FabricaConexao.criarConexao();
		String sql = " SELECT * FROM estoque WHERE 1 = 1 ";

		if (text != null && !text.isEmpty()) {
			sql += " AND nome LIKE ? ";
		}
		
		if(text2 != null && !text2.isEmpty()) {
			sql += "AND lugar LIKE ?";
		}

		

		

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		int i = 1;

		if (text != null && !text.isEmpty()) {
			comando.setString(i, "%" + text.toUpperCase() + "%");
			i++;
		}

		if (text2 != null && !text2.isEmpty()) {
			comando.setString(i, "%" + text2.toUpperCase() + "%");
			i++;
		}

		

		ResultSet resultado = comando.executeQuery();

		List<Estoque> ec = new ArrayList<>();

		while (resultado.next()) {
			Estoque e = new Estoque();
			e.setNometipo(resultado.getString("nome"));
			e.setLugar(resultado.getString("lugar"));
			
			ec.add(e);
		
		}

		return ec;
	}
}

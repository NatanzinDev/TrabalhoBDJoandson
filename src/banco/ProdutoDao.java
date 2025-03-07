package banco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dominio.Estoque;

import dominio.Produto;

public class ProdutoDao {

	public List<Produto> buscarProduto(String text, String text2, String text3) throws ClassNotFoundException, SQLException {
		Connection conexao = FabricaConexao.criarConexao();
		String sql = "SELECT p.nome, p.preco, p.validade,  e.nome AS nomeestoque  FROM produto p JOIN estoque e ON p.idestoque= e.idestoque";

		boolean t = false;

		if (text != null && !text.isEmpty()) {
			sql += " WHERE p.nome LIKE ?";
			t = true;
		}

		if (text2 != null && !text2.isEmpty()) {
			if(t) {
				sql += " AND p.preco LIKE ?";
			}else {
				 sql += "WHERE p.preco LIKE ?";
			}
			
			t = true;
		}
		
		if (text2 != null && !text2.isEmpty()) {
			if(t) {
				sql += " AND p.validade LIKE ?";
			}else {
				 sql += "WHERE p.validade LIKE ?";
			}
			
			t = true;
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
		
		if (text3 != null && !text3.isEmpty()) {
			comando.setString(i, "%" + text3.toUpperCase() + "%");
			i++;
		}

		


		ResultSet resultado = comando.executeQuery();

		List<Produto> pe = new ArrayList<>();

		while (resultado.next()) {
			Estoque e = new Estoque();
			e.setNometipo(resultado.getString("nomeestoque"));
			
			Produto p = new Produto();
			p.setNome(resultado.getString("nome"));
			p.setPreco(resultado.getString("preco"));
			p.setValidade(resultado.getString("validade"));
			p.setTipodeestoque(e);

			pe.add(p);

		}
		
		return pe;
	}
	
}

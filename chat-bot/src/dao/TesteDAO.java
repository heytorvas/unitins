package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.Teste;

public class TesteDAO extends DAO<Teste> {
	private String resposta;
	

	public String getResposta() {
		return resposta;
	}
	public void setResposta(String resposta) {
		this.resposta = resposta;
	}

	@Override
	public List<Teste> findAll() {
		return null;
	}

	@Override
	public boolean create(Teste obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Teste obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Teste findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean sqlAcharArquivo(String message) {
		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			return false;
		}

		PreparedStatement stat = null;

		// **6 keywords
		
		// fazer o tratamento da keywords com if para cada if fazer a busca no banco de
		// dados
		
		// fazer a busca no banco de dados apenas com as informações que o usuario
		// digitou = retornar uma lista com os resultados
		
		// se o usuario digitar uma quantidade de keywords suficientes para retornar um
		// arquivo = retornar o link do arquivo

		String[] frase_array = message.split(" ");

		for (int i = 0; i < frase_array.length; i++) {

			if (frase_array[i].equals("tcc")) {
				// fazer a busca no banco de dados e retornar
				try {
					stat = getConnection().prepareStatement("SELECT * FROM infodocs WHERE tipo = 1");
					ResultSet rs = stat.executeQuery();
					while (rs.next()) {
						
						Teste teste = new Teste();
						teste.setNome(rs.getString("nome"));
						teste.setAno(rs.getString("ano"));
						teste.setOrientador(rs.getString("orientador"));
						teste.setOrientado(rs.getString("orientado"));
						
						System.out.println();
						System.out.println("NOME: " + rs.getString("nome"));
						System.out.println("ANO: " + rs.getString("ano"));
						System.out.println("ORIENTADOR: " + rs.getString("orientador"));
						System.out.println("ORIENTADO: " + rs.getString("orientado"));
						System.out.println();
						
						setResposta("NOME: " + rs.getString("nome") + "\n" + "ANO: " + rs.getString("ano"));
						
						
					}
					return true;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					try {
						stat.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}

		}
		return false;

//		try {
//			stat = getConnection().prepareStatement("SELECT * FROM palavras_chaves WHERE sinonimo_onde = ?");
//			//stat.setString(1, palavra);
//
//			ResultSet rs = stat.executeQuery();
//			if (rs.next()) {
//
//				System.out.println(rs.getString("sinonimo_onde"));
//				// Teste teste = new Teste();;
//				// teste.set(rs.getString("sinonimo_onde"));
//				// adicionar variavel palavra no teste
//				return true;
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				stat.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return false;

	}

}// chave da classe

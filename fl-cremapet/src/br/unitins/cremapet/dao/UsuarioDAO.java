package br.unitins.cremapet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.unitins.cremapet.application.Util;
import br.unitins.cremapet.model.Usuario;
import br.unitins.cremapet.model.Endereco;
import br.unitins.cremapet.model.Perfil;
import br.unitins.cremapet.model.Sexo;
import br.unitins.cremapet.model.Usuario;

public class UsuarioDAO extends DAO<Usuario> {
	
	public UsuarioDAO(Connection conn) {
		super(conn);
	}
	
	public UsuarioDAO() {
		super(null);
	}
	
	public Usuario login(String login, String senha) {
		
		Connection conn = getConnection();
		
		try {
			PreparedStatement stat = conn.prepareStatement(
					"SELECT " +
					"  id, " +
					"  nome, " +
					"  cpf, " +
					"  login, " +
					"  senha, " +
					"  perfil, " +
					"  sexo, " +
					"  salario " +
					"FROM " +
					"  public.usuario " +
					"WHERE login = ? AND senha = ? ");
			
			stat.setString(1, login);
			stat.setString(2, senha);
			
			ResultSet rs = stat.executeQuery();
			
			Usuario usuario = null;
			
			if(rs.next()) {
				usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setCpf(rs.getString("cpf"));
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setPerfil(Perfil.valueOf(rs.getInt("perfil")));
				usuario.setSexo(Sexo.valueOf(rs.getInt("sexo")));
				usuario.setSalario(rs.getDouble("salario"));
			}
			
			return usuario;
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	public void create(Usuario usuario) throws SQLException {
		
		Connection  conn = getConnection();
			
		PreparedStatement stat = conn.prepareStatement(
				"INSERT INTO " +
			    "public.usuario " +
			    " (nome, cpf, login, senha, perfil, sexo, salario) " +
				"VALUES " +
			    " (?, ?, ?, ?, ?, ?, ?) ", Statement.RETURN_GENERATED_KEYS);
		stat.setString(1, usuario.getNome());
		stat.setString(2, usuario.getCpf());
		stat.setString(3, usuario.getLogin());
		stat.setString(4, usuario.getSenha());
		stat.setInt(5, usuario.getPerfil().getValue());
		stat.setInt(6, usuario.getSexo().getValue());
		stat.setDouble(7, usuario.getSalario());
		
		stat.execute();
		
		// obtendo o id gerado pela tabela do banco de dados
		ResultSet rs = stat.getGeneratedKeys();
		rs.next();
		usuario.getEndereco().setId(rs.getInt("id"));
		
		EnderecoDAO dao = new EnderecoDAO(conn);
		dao.create(usuario.getEndereco());
			
	}

	@Override
	public void update(Usuario usuario) throws SQLException {
		Connection  conn = getConnection();
		
		PreparedStatement stat = conn.prepareStatement(
				"UPDATE public.usuario SET " +
			    " nome = ?, " +
			    " cpf = ?, " +
			    " login = ?, " +
			    " senha = ?, " +
			    " perfil = ?, " +
			    " sexo = ?, " +
			    " salario = ? " +
				"WHERE " +
			    " id = ? ");
		stat.setString(1, usuario.getNome());
		stat.setString(2, usuario.getCpf());
		stat.setString(3, usuario.getLogin());
		stat.setString(4, usuario.getSenha());
		stat.setInt(5, usuario.getPerfil().getValue());
		stat.setInt(6, usuario.getSexo().getValue());
		stat.setDouble(7, usuario.getSalario());
		stat.setInt(8, usuario.getId());
			
		stat.execute();
		
		EnderecoDAO dao = new EnderecoDAO(conn);
		dao.update(usuario.getEndereco());
			
	}

	@Override
	public void delete(int id) throws SQLException {

		Connection  conn = getConnection();
		
		// deletando o telefone (pq possui um relacionamento de fk)
		// passando o conn para manter a mesma transacao
		EnderecoDAO dao = new EnderecoDAO(conn);
		
	
		// telefone tem um relecionamento 1 pra 1, ou seja, o id do usuario eh o mesmo do telefone.
		dao.delete(id);
		
		// deletando o usuario
		PreparedStatement stat = conn.prepareStatement(
				"DELETE FROM public.usuario WHERE id = ?");
		stat.setInt(1, id);
		
		stat.execute();
			
	}

	@Override
	public List<Usuario> findAll() {
		Connection conn = getConnection();
		if (conn == null) 
			return null;
		
		try {
			PreparedStatement stat = conn.prepareStatement(
					"SELECT " +
					"  id, " +
					"  nome, " +
					"  cpf, " +
					"  login, " +
					"  senha, " +
					"  perfil, " +
					"  sexo, " +
					"  salario " +
					"FROM " +
					"  public.usuario ");
			ResultSet rs = stat.executeQuery();
			
			List<Usuario> listaUsuario = new ArrayList<Usuario>();
			
			while(rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setCpf(rs.getString("cpf"));
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setPerfil(Perfil.valueOf(rs.getInt("perfil")));
				usuario.setSexo(Sexo.valueOf(rs.getInt("sexo")));
				usuario.setSalario(rs.getDouble("salario"));
				
				listaUsuario.add(usuario);
			}
			
			if (listaUsuario.isEmpty())
				return null;
			return listaUsuario;
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Usuario findId(Integer id) {
		Connection conn = getConnection();
		if (conn == null) 
			return null;
		
		try {
			PreparedStatement stat = conn.prepareStatement(
					"SELECT " +
					"  id, " +
					"  nome, " +
					"  cpf, " +
					"  login, " +
					"  senha, " +
					"  perfil, " +
					"  sexo, " +
					"  salario " +
					"FROM " +
					"  public.usuario " +
					"WHERE id = ? ");
			
			stat.setInt(1, id);
			
			ResultSet rs = stat.executeQuery();
			
			Usuario usuario = null;
			
			if(rs.next()) {
				usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setCpf(rs.getString("cpf"));
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setPerfil(Perfil.valueOf(rs.getInt("perfil")));
				usuario.setSexo(Sexo.valueOf(rs.getInt("sexo")));
				usuario.setSalario(rs.getDouble("salario"));
				
				EnderecoDAO dao = new EnderecoDAO(conn);
				usuario.setEndereco(dao.findById(usuario.getId()));
//				// caso o retorno do telefone seja nulo, instanciar um telefone
				if (usuario.getEndereco() == null)
					usuario.setEndereco(new Endereco());
				
			}
			
			return usuario;
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Usuario> findByNome(String nome) {
		// verificando se tem uma conexao valida
		Connection conn = getConnection();
		if (conn == null) 
			return null;
		
		List<Usuario> listaUsuario = new ArrayList<Usuario>();
		
		PreparedStatement stat = null;
	
		try {
			stat = conn.prepareStatement("SELECT * FROM usuario WHERE nome ILIKE ?");
			stat.setString(1, (nome == null? "%" : "%"+nome+"%"));
			ResultSet rs = stat.executeQuery();
			
			while(rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setLogin(rs.getString("login"));
				usuario.setCpf(rs.getString("cpf"));
				usuario.setSexo(Sexo.valueOf(rs.getInt("sexo")));
				usuario.setPerfil(Perfil.valueOf(rs.getInt("perfil")));
				usuario.setSalario(rs.getDouble("salario"));

				listaUsuario.add(usuario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			Util.addMessageError("Falha ao consultar o Banco de Dados.");
			listaUsuario = null;
		} finally {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listaUsuario;
	}
	
}

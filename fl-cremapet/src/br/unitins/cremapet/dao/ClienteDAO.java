package br.unitins.cremapet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.unitins.cremapet.application.Util;
import br.unitins.cremapet.model.Cliente;
import br.unitins.cremapet.model.Endereco;
import br.unitins.cremapet.model.Perfil;
import br.unitins.cremapet.model.Sexo;
import br.unitins.cremapet.model.Cliente;

public class ClienteDAO extends DAO<Cliente>{
	
	public ClienteDAO(Connection conn) {
		super(conn);
	}
	
	public ClienteDAO() {
		super(null);
	}

	@Override
	public void create(Cliente cliente) throws SQLException {
		
		Connection  conn = getConnection();
			
		PreparedStatement stat = conn.prepareStatement(
				"INSERT INTO " +
			    "public.cliente " +
			    " (nome, cpf, login, sexo) " +
				"VALUES " +
			    " (?, ?, ?, ?) ", Statement.RETURN_GENERATED_KEYS);
		stat.setString(1, cliente.getNome());
		stat.setString(2, cliente.getCpf());
		stat.setString(3, cliente.getLogin());
		stat.setInt(4, cliente.getSexo().getValue());
		
		stat.execute();
		
		// obtendo o id gerado pela tabela do banco de dados
		ResultSet rs = stat.getGeneratedKeys();
		rs.next();
		cliente.getEndereco().setId(rs.getInt("id"));
		
		EnderecoDAO dao = new EnderecoDAO(conn);
		dao.create(cliente.getEndereco());
			
	}

	@Override
	public void update(Cliente cliente) throws SQLException {
		Connection  conn = getConnection();
		
		PreparedStatement stat = conn.prepareStatement(
				"UPDATE public.cliente SET " +
			    " nome = ?, " +
			    " cpf = ?, " +
			    " login = ?, " +
			    " sexo = ? " +
				"WHERE " +
			    " id = ? ");
		stat.setString(1, cliente.getNome());
		stat.setString(2, cliente.getCpf());
		stat.setString(3, cliente.getLogin());
		stat.setInt(4, cliente.getSexo().getValue());
		stat.setInt(5, cliente.getId());
			
		stat.execute();
		
		EnderecoDAO dao = new EnderecoDAO(conn);
		dao.update(cliente.getEndereco());
			
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
				"DELETE FROM public.cliente WHERE id = ?");
		stat.setInt(1, id);
		
		stat.execute();
			
	}

	@Override
	public List<Cliente> findAll() {
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
					"  sexo " +
					"FROM " +
					"  public.cliente ");
			ResultSet rs = stat.executeQuery();
			
			List<Cliente> listaCliente = new ArrayList<Cliente>();
			
			while(rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setId(rs.getInt("id"));
				cliente.setNome(rs.getString("nome"));
				cliente.setCpf(rs.getString("cpf"));
				cliente.setLogin(rs.getString("login"));
				cliente.setSexo(Sexo.valueOf(rs.getInt("sexo")));
				
				listaCliente.add(cliente);
			}
			
			if (listaCliente.isEmpty())
				return null;
			return listaCliente;
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Cliente findId(Integer id) {
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
					"  sexo " +
					"FROM " +
					"  public.cliente " +
					"WHERE id = ? ");
			
			stat.setInt(1, id);
			
			ResultSet rs = stat.executeQuery();
			
			Cliente cliente = null;
			
			if(rs.next()) {
				cliente = new Cliente();
				cliente.setId(rs.getInt("id"));
				cliente.setNome(rs.getString("nome"));
				cliente.setCpf(rs.getString("cpf"));
				cliente.setLogin(rs.getString("login"));
				cliente.setSexo(Sexo.valueOf(rs.getInt("sexo")));
				
				EnderecoDAO dao = new EnderecoDAO(conn);
				cliente.setEndereco(dao.findById(cliente.getId()));
//				// caso o retorno do telefone seja nulo, instanciar um telefone
				if (cliente.getEndereco() == null)
					cliente.setEndereco(new Endereco());
				
			}
			
			return cliente;
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	public List<Cliente> findByNome(String nome) {
		// verificando se tem uma conexao valida
		Connection conn = getConnection();
		if (conn == null) 
			return null;
		
		List<Cliente> listaUsuario = new ArrayList<Cliente>();
		
		PreparedStatement stat = null;
	
		try {
			stat = conn.prepareStatement("SELECT * FROM cliente WHERE nome ILIKE ?");
			stat.setString(1, (nome == null? "%" : "%"+nome+"%"));
			ResultSet rs = stat.executeQuery();
			
			while(rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setId(rs.getInt("id"));
				cliente.setNome(rs.getString("nome"));
				cliente.setLogin(rs.getString("login"));
				cliente.setCpf(rs.getString("cpf"));
				cliente.setSexo(Sexo.valueOf(rs.getInt("sexo")));

				listaUsuario.add(cliente);
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
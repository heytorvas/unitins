package br.unitins.cremapet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.unitins.cremapet.model.Servico;

public class ServicoDAO extends DAO<Servico> {

	public ServicoDAO(Connection conn) {
		super(conn);
	}

	public ServicoDAO() {
		super(null);
	}

	@Override
	public void create(Servico servico) throws SQLException {
		Connection conn = getConnection();

		PreparedStatement stat = conn
				.prepareStatement("INSERT INTO " + "public.servico " + " (descricao, valor) " + "VALUES " + " (?, ?) ");
		stat.setString(1, servico.getDescricao());
		stat.setDouble(2, servico.getValor());

		stat.execute();

	}

	@Override
	public void update(Servico servico) throws SQLException {

		Connection conn = getConnection();

		PreparedStatement stat = conn.prepareStatement(
				"UPDATE public.servico SET " + " descricao = ?, " + " valor = ? " + "WHERE " + " id = ? ");
		stat.setString(1, servico.getDescricao());
		stat.setDouble(2, servico.getValor());
		stat.setInt(3, servico.getId());

		stat.execute();

	}

	@Override
	public void delete(int id) throws SQLException {
		Connection conn = getConnection();

		PreparedStatement stat = conn.prepareStatement("DELETE FROM public.servico WHERE id = ?");
		stat.setInt(1, id);

		stat.execute();
	}

	@Override
	public List<Servico> findAll() {
		Connection conn = getConnection();
		if (conn == null)
			return null;

		try {
			PreparedStatement stat = conn.prepareStatement(
					"SELECT " + "  id, " + "  descricao, " + "  valor " + "FROM " + "  public.servico ");
			ResultSet rs = stat.executeQuery();

			List<Servico> listaServico = new ArrayList<Servico>();

			while (rs.next()) {
				Servico servico = new Servico();
				servico.setId(rs.getInt("id"));
				servico.setDescricao(rs.getString("descricao"));
				servico.setValor(rs.getDouble("valor"));

				listaServico.add(servico);
			}

			if (listaServico.isEmpty())
				return null;
			return listaServico;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Servico findById(Integer id) {
		Connection conn = getConnection();
		if (conn == null) 
			return null;
		
		try {
			PreparedStatement stat = conn.prepareStatement(
					"SELECT " +
					"  id, " +
					"  descricao, " +
					"  valor " +
					"FROM " +
					"  public.servico " +
					"WHERE id = ? ");
			
			stat.setInt(1, id);
			
			ResultSet rs = stat.executeQuery();
			
			Servico servico = null;
			
			if(rs.next()) {
				servico = new Servico();
				servico.setId(rs.getInt("id"));
				servico.setDescricao(rs.getString("descricao"));
				servico.setValor(rs.getDouble("valor"));
			}
			
			return servico;
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Servico> findByNome(String nome) {
		Connection conn = getConnection();
		if (conn == null) 
			return null;
		
		try {
			PreparedStatement stat = conn.prepareStatement(
					"SELECT * FROM servico WHERE descricao ILIKE ?");
			
			stat.setString(1, nome == null ? "%" : "%"+nome+"%");
			ResultSet rs = stat.executeQuery();
			
			List<Servico> listaServico = new ArrayList<Servico>();
			
			while(rs.next()) {
				Servico servico = new Servico();
				servico.setId(rs.getInt("id"));
				servico.setDescricao(rs.getString("descricao"));
				servico.setValor(rs.getDouble("valor"));
				
				listaServico.add(servico);
			}
			
			if (listaServico.isEmpty())
				return null;
			return listaServico;
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}

package br.unitins.cremapet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.unitins.cremapet.model.Endereco;
import br.unitins.cremapet.model.Estado;

public class EnderecoDAO extends DAO<Endereco> {

	public EnderecoDAO(Connection conn) {
		super(conn);
	}

	@Override
	public void create(Endereco endereco) throws SQLException {
		Connection conn = getConnection();

		PreparedStatement stat = conn.prepareStatement("INSERT INTO " + "public.endereco "
				+ " (id, rua, numero, bairro, cidade, estado) " + "VALUES " + " (?, ?, ?, ?, ?, ?) ");

		stat.setInt(1, endereco.getId());
		stat.setString(2, endereco.getRua());
		stat.setString(3, endereco.getNumero());
		stat.setString(4, endereco.getBairro());
		stat.setString(5, endereco.getCidade());
		stat.setInt(6, endereco.getEstados().getValue());

		stat.execute();
		stat.close();
	}

	@Override
	public void update(Endereco endereco) throws SQLException {
	Connection  conn = getConnection();
		
		PreparedStatement stat = conn.prepareStatement(
				"UPDATE public.endereco SET " +
				" rua = ?, " +
			    " numero = ?, " +
			    " bairro = ?, " +
			    " cidade = ?, " +
			    " estado = ? " +
				"WHERE " +
			    " id = ? ");
		stat.setString(1, endereco.getRua());
		stat.setString(2, endereco.getNumero());
		stat.setString(3, endereco.getBairro());
		stat.setString(4, endereco.getCidade());
		stat.setInt(5, endereco.getEstados().getValue());
		stat.setInt(6, endereco.getId());
			
		stat.execute();
		stat.close();
	}

	@Override
	public void delete(int id) throws SQLException {
		Connection conn = getConnection();

		PreparedStatement stat = conn.prepareStatement("DELETE FROM public.endereco WHERE id =  ?");
		stat.setInt(1, id);

		stat.execute();
		stat.close();

	}

	@Override
	public List<Endereco> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public Endereco findById(Integer id) {
		Connection conn = getConnection();

		try {
			PreparedStatement stat = conn.prepareStatement("SELECT " + 
					"  id, " + "  rua, " + "  numero, " + "  bairro, " + "  cidade, " + "  estado "
					+ "FROM " + "  public.endereco " + "WHERE id = ? ");

			stat.setInt(1, id);

			ResultSet rs = stat.executeQuery();

			Endereco endereco = null;

			if (rs.next()) {
				endereco = new Endereco();
				endereco.setId(rs.getInt("id"));
				endereco.setRua(rs.getString("rua"));
				endereco.setNumero(rs.getString("numero"));
				endereco.setBairro(rs.getString("bairro"));
				endereco.setCidade(rs.getString("cidade"));
				endereco.setEstados(Estado.valueOf(rs.getInt("estado")));

			}

			return endereco;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}

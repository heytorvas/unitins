package br.unitins.cremapet.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.unitins.cremapet.application.Util;
import br.unitins.cremapet.model.Cliente;
import br.unitins.cremapet.model.ItemVenda;
import br.unitins.cremapet.model.Pet;
import br.unitins.cremapet.model.Usuario;
import br.unitins.cremapet.model.Venda;

public class VendaDAO extends DAO<Venda> {

	public VendaDAO(Connection conn) {
		super(conn);
	}
	
	public VendaDAO() {
		super(null);
	}

	@Override
	public void create(Venda venda) throws SQLException {

		// verificando se tem uma conexao valida
		Connection  conn = getConnection();

		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement("INSERT INTO venda ( " + "  data, " + "  idcliente," + "  idpet,"
					+ "  idusuario ) " + "VALUES ( " + " ?, " + " ?, " + " ?, " + " ? ) ", Statement.RETURN_GENERATED_KEYS);
			stat.setDate(1, Date.valueOf(LocalDate.now()));
			stat.setInt(2, venda.getCliente().getId());
			stat.setInt(3, venda.getPet().getId());
			stat.setInt(4, venda.getUsuario().getId());

			stat.executeUpdate();
			final ResultSet rs = stat.getGeneratedKeys();
			rs.next();
			final int lastId = rs.getInt("id");

			stat.close();

			for (ItemVenda itemVenda : venda.getListaItemVenda()) {
				stat = conn.prepareStatement("INSERT INTO itemvenda ( " + "  valor, " + "  idvenda,"
						+ "  idservico ) " + "VALUES ( " + " ?, " + " ?, " + " ? ) ");
				stat.setDouble(1, itemVenda.getValor());
				stat.setInt(2, lastId);
				stat.setInt(3, itemVenda.getServico().getId());

				stat.execute();
				stat.close();
			}

			Util.addMessageError("Venda finalizada com sucesso!");
		} catch (SQLException e) {
			Util.addMessageError("Falha ao incluir.");
			e.printStackTrace();
		} finally {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
	
	public List<Venda> findHistorico(Usuario usuario) {
		// verificando se tem uma conexao valida
		Connection conn = getConnection();

		List<Venda> listaVenda = new ArrayList<Venda>();

		PreparedStatement stat = null;

		try {
			stat = conn.prepareStatement("SELECT * FROM venda WHERE idusuario = ?");
			stat.setInt(1, usuario.getId());
			ResultSet rs = stat.executeQuery();
			while (rs.next()) {
				Venda venda = new Venda();
				venda.setId(rs.getInt("id"));
				venda.setData(rs.getDate("data").toLocalDate());
				venda.setCliente(new Cliente());
				venda.getCliente().setId(rs.getInt("idcliente"));
				venda.setUsuario(usuario);
				venda.setPet(new Pet());
				venda.getPet().setId(rs.getInt("idpet"));

				// buscando os itensvenda de cada venda
				ItemVendaDAO dao = new ItemVendaDAO();
				venda.setListaItemVenda(dao.findAll(venda));

				// adicionando na lista de retorno
				listaVenda.add(venda);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			Util.addMessageError("Falha ao consultar o Banco de Dados.");
			listaVenda = null;
		} finally {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listaVenda;
	}

	public List<Venda> findAll(Cliente cliente) {
		// verificando se tem uma conexao valida
		Connection conn = getConnection();

		List<Venda> listaVenda = new ArrayList<Venda>();

		PreparedStatement stat = null;

		try {
			stat = conn.prepareStatement("SELECT * FROM venda WHERE idusuario = ?");
			stat.setInt(1, cliente.getId());
			ResultSet rs = stat.executeQuery();
			while (rs.next()) {
				Venda venda = new Venda();
				venda.setId(rs.getInt("id"));
				venda.setData(rs.getDate("data").toLocalDate());
				venda.setCliente(cliente);
				venda.setUsuario(venda.getUsuario());
				venda.setPet(venda.getPet());

				// buscando os itensvenda de cada venda
				ItemVendaDAO dao = new ItemVendaDAO();
				venda.setListaItemVenda(dao.findAll(venda));

				// adicionando na lista de retorno
				listaVenda.add(venda);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			Util.addMessageError("Falha ao consultar o Banco de Dados.");
			listaVenda = null;
		} finally {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listaVenda;
	}

	@Override
	public void update(Venda entity) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int id) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Venda> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}

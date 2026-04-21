package br.unitins.cremapet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.unitins.cremapet.application.Util;
import br.unitins.cremapet.model.ItemVenda;
import br.unitins.cremapet.model.Servico;
import br.unitins.cremapet.model.Venda;

public class ItemVendaDAO extends DAO<ItemVenda> {

	public ItemVendaDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	public ItemVendaDAO() {
		super(null);
	}

	public List<ItemVenda> findAll(Venda venda) {
		// verificando se tem uma conexao valida
		Connection conn = getConnection();

		List<ItemVenda> listaItemVenda = new ArrayList<ItemVenda>();

		PreparedStatement stat = null;

		try {
			stat = conn.prepareStatement("SELECT  " + 
					" i.id, " + " i.valor, " + " i.idvenda, " + 
					" i.idservico, " + " s.descricao, " +
					" s.valor as valorservico " + 
					"FROM " + " itemvenda i, " + " servico s " + 
					"WHERE " + " i.idservico = s.id AND " + " i.idvenda = ? ");
			stat.setInt(1, venda.getId());

			ResultSet rs = stat.executeQuery();
			while (rs.next()) {
				ItemVenda itemVenda = new ItemVenda();
				itemVenda.setId(rs.getInt("id"));
				itemVenda.setValor(rs.getDouble("valor"));
				itemVenda.setVenda(venda);
				itemVenda.setServico(new Servico());
				itemVenda.getServico().setId(rs.getInt("idservico"));
				itemVenda.getServico().setDescricao(rs.getString("descricao"));
				itemVenda.getServico().setValor(rs.getDouble("valorservico"));

				listaItemVenda.add(itemVenda);

			}
		} catch (SQLException e) {
			e.printStackTrace();
			Util.addMessageError("Falha ao consultar o Banco de Dados.");
			listaItemVenda = null;
		} finally {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return listaItemVenda;

	}

	@Override
	public void create(ItemVenda entity) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(ItemVenda entity) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int id) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<ItemVenda> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}

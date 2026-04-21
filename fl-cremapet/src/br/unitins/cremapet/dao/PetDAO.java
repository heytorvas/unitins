package br.unitins.cremapet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.unitins.cremapet.application.Util;
import br.unitins.cremapet.model.Pet;
import br.unitins.cremapet.model.Pet;
import br.unitins.cremapet.model.Sexo;
import br.unitins.cremapet.model.Pet;

public class PetDAO extends DAO<Pet>{
	
	public PetDAO(Connection conn) {
		super(conn);
	}
	
	public PetDAO() {
		super(null);
	}

	@Override
	public void create(Pet pet) throws SQLException {
		Connection  conn = getConnection();
		
		PreparedStatement stat = conn.prepareStatement(
				"INSERT INTO " +
			    "public.pet " +
			    " (sexo, nome, animal, raca) " +
				"VALUES " +
			    " (?, ?, ?, ?) ");
		stat.setInt(1, pet.getSexo().getValue());
		stat.setString(2, pet.getNome());
		stat.setString(3, pet.getAnimal());
		stat.setString(4, pet.getRaca());
		
		stat.execute();
		
	}

	@Override
	public void update(Pet pet) throws SQLException {
		Connection  conn = getConnection();
		
		PreparedStatement stat = conn.prepareStatement(
				"UPDATE public.pet SET " +
				" sexo = ?, " +
			    " nome = ?, " +
			    " animal = ?, " +
			    " raca = ? " +
				"WHERE " +
			    " id = ? ");
		stat.setInt(1, pet.getSexo().getValue());
		stat.setString(2, pet.getNome());
		stat.setString(3, pet.getAnimal());
		stat.setString(4, pet.getRaca());
		stat.setInt(5, pet.getId());
			
		stat.execute();
		
	}

	@Override
	public void delete(int id) throws SQLException {
		
		Connection  conn = getConnection();
		
		// deletando o usuario
		PreparedStatement stat = conn.prepareStatement(
				"DELETE FROM public.pet WHERE id = ?");
		stat.setInt(1, id);
		
		stat.execute();
		
	}

	@Override
	public List<Pet> findAll() {
		Connection conn = getConnection();
		if (conn == null) 
			return null;
		
		try {
			PreparedStatement stat = conn.prepareStatement(
					"SELECT " +
					"  id, " +
					"  sexo, " +
					"  nome, " +
					"  animal, " +
					"  raca " +
					"FROM " +
					"  public.pet ");
			ResultSet rs = stat.executeQuery();
			
			List<Pet> listaPet = new ArrayList<Pet>();
			
			while(rs.next()) {
				Pet pet = new Pet();
				pet.setId(rs.getInt("id"));
				pet.setSexo(Sexo.valueOf(rs.getInt("sexo")));
				pet.setNome(rs.getString("nome"));
				pet.setAnimal(rs.getString("animal"));
				pet.setRaca(rs.getString("raca"));
				
				listaPet.add(pet);
			}
			
			if (listaPet.isEmpty())
				return null;
			return listaPet;
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public Pet findId(Integer id) {
		Connection conn = getConnection();
		if (conn == null) 
			return null;
		
		try {
			PreparedStatement stat = conn.prepareStatement(
							"SELECT " +
							"  id, " +
							"  sexo, " +
							"  nome, " +
							"  animal, " +
							"  raca " +
							"FROM " +
							"  public.pet " +
							"WHERE id = ? ");
			
			stat.setInt(1, id);
			
			ResultSet rs = stat.executeQuery();
			
			Pet pet = null;
			
			if(rs.next()) {
				pet = new Pet();
				pet.setId(rs.getInt("id"));
				pet.setSexo(Sexo.valueOf(rs.getInt("sexo")));
				pet.setNome(rs.getString("nome"));
				pet.setAnimal(rs.getString("animal"));
				pet.setRaca(rs.getString("raca"));
				
			}
			
			return pet;
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Pet> findByNome(String nome) {
		// verificando se tem uma conexao valida
		Connection conn = getConnection();
		if (conn == null) 
			return null;
		
		List<Pet> listaPet = new ArrayList<Pet>();
		
		PreparedStatement stat = null;
	
		try {
			stat = conn.prepareStatement("SELECT * FROM pet WHERE nome ILIKE ?");
			stat.setString(1, (nome == null? "%" : "%"+nome+"%"));
			ResultSet rs = stat.executeQuery();
			
			while(rs.next()) {
				Pet pet = new Pet();
				pet.setId(rs.getInt("id"));
				pet.setNome(rs.getString("nome"));
				pet.setRaca(rs.getString("raca"));
				pet.setAnimal(rs.getString("animal"));
				pet.setSexo(Sexo.valueOf(rs.getInt("sexo")));

				listaPet.add(pet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			Util.addMessageError("Falha ao consultar o Banco de Dados.");
			listaPet = null;
		} finally {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listaPet;
	}
}

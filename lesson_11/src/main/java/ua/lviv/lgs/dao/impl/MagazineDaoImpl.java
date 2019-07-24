package ua.lviv.lgs.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.lviv.lgs.dao.MagazineDao;
import ua.lviv.lgs.domain.Magazine;
import ua.lviv.lgs.utils.ConnectionUtil;

public class MagazineDaoImpl implements MagazineDao{
	
	private static String READ_ALL = "select * from magazine_catalog";
	private static String CREATE = "insert into magazine_catalog(name, genre, description, price, number_of_pages) values (?,?,?,?,?)";
	private static String READ_BY_ID = "select * from magazine_catalog where id =?";
	private static String UPDATE_BY_ID = "update magazine_catalog set name=?, genre=?, description=?, price=?, number_of_pages=? where id = ?";
	private static String DELETE_BY_ID = "delete from magazine_catalog where id=?";
	
	private static Logger LOGGER = Logger.getLogger(MagazineDaoImpl.class);
	
	private Connection connection;
	private PreparedStatement preparedStatement;

	public MagazineDaoImpl() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		connection = ConnectionUtil.openConnection();
	}

	@Override
	public Magazine create(Magazine magazine) {
		try {
			preparedStatement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, magazine.getName());
			preparedStatement.setString(2, magazine.getGenre());
			preparedStatement.setString(3, magazine.getDescription());
			preparedStatement.setDouble(4, magazine.getPrice());
			preparedStatement.setInt(5, magazine.getNumberOfPages());
			preparedStatement.executeUpdate();
			
			ResultSet rs = preparedStatement.getGeneratedKeys();
			rs.next();
			magazine.setId(rs.getInt(1));
		} catch (SQLException e) {
			LOGGER.error(e);
		}

		return magazine;
	}

	@Override
	public Magazine read(Integer id) {
		Magazine magazine = null;
		try {
			preparedStatement = connection.prepareStatement(READ_BY_ID);
			preparedStatement.setInt(1, id);
			ResultSet result = preparedStatement.executeQuery();
			result.next();

			int magazineId = result.getInt("id");
			String name = result.getString("name");
			String genre = result.getString("genre");
			String description = result.getString("description");
			double price = result.getDouble("price");
			int numberOfPages = result.getInt("number_of_pages");
			
			magazine = new Magazine(magazineId, name, genre, description, price, numberOfPages);

		} catch (SQLException e) {
			LOGGER.error(e);
		}

		return magazine;
	}

	@Override
	public Magazine update(Magazine magazine) {
		try {
			preparedStatement = connection.prepareStatement(UPDATE_BY_ID);
			preparedStatement.setString(1, magazine.getName());
			preparedStatement.setString(2, magazine.getGenre());
			preparedStatement.setString(3, magazine.getDescription());
			preparedStatement.setDouble(4, magazine.getPrice());
			preparedStatement.setInt(5, magazine.getNumberOfPages());
			preparedStatement.setInt(6, magazine.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error(e);
		}
		return magazine;
	}

	@Override
	public void delete(Integer id) {
		try {
			preparedStatement = connection.prepareStatement(DELETE_BY_ID);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error(e);
		}		
	}

	@Override
	public List<Magazine> readAll() {
		List<Magazine> listOfMagazines = new ArrayList<>();
		try {
			preparedStatement = connection.prepareStatement(READ_ALL);
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				int magazineId = result.getInt("id");
				String name = result.getString("name");
				String genre = result.getString("genre");
				String description = result.getString("description");
				double price = result.getDouble("price");
				int numberOfPages = result.getInt("number_of_pages");
				
				listOfMagazines.add(new Magazine(magazineId, name, genre, description, price, numberOfPages));
			}
		} catch (SQLException e) {
			
		}
		
		return listOfMagazines;
	}
	
}

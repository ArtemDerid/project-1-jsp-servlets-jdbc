package ua.lviv.lgs.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ua.lviv.lgs.dao.UserDao;
import ua.lviv.lgs.domain.User;
import ua.lviv.lgs.utils.ConnectionUtil;

public class UserDaoImpl implements UserDao{
	
	private static String READ_ALL = "select * from user";
	private static String CREATE = "insert into user(email, telephone_number, first_name, last_name, role, status) values (?,?,?,?,?,?)";
	private static String READ_BY_ID = "select * from user where id =?";
	private static String UPDATE_BY_ID = "update user set email=?, telephone_number=?, first_name=?, last_name=?, role=?, status=? where id = ?";
	private static String DELETE_BY_ID = "delete from user where id=?";
	
	private Connection connection;
	private PreparedStatement preparedStatement;

	public UserDaoImpl() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		connection = ConnectionUtil.openConnection();
	}

	@Override
	public User create(User user) {
		try {
			preparedStatement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, user.getEmail());
			preparedStatement.setString(2, user.getTelephoneNumber());
			preparedStatement.setString(3, user.getFirstName());
			preparedStatement.setString(4, user.getLastName());
			preparedStatement.setString(5, user.getRole());
			preparedStatement.setString(6, user.getStatus());
			preparedStatement.executeUpdate();
			
			ResultSet rs = preparedStatement.getGeneratedKeys();
			rs.next();
			user.setId(rs.getInt(1));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}

	@Override
	public User read(Integer id) {
		User user = null;
		try {
			preparedStatement = connection.prepareStatement(READ_BY_ID);
			preparedStatement.setInt(1, id);
			ResultSet result = preparedStatement.executeQuery();
			result.next();

			int userId = result.getInt("id");
			String email = result.getString("email");
			String telephoneNumber = result.getString("telephone_number");
			String firstName = result.getString("first_name");
			String lastName = result.getString("last_name");
			String role = result.getString("role");
			String status = result.getString("status");
			
			user = new User(userId, email, telephoneNumber, firstName, lastName, role, status);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}

	@Override
	public User update(User user) {
		try {
			preparedStatement = connection.prepareStatement(UPDATE_BY_ID);
			preparedStatement.setString(1, user.getEmail());
			preparedStatement.setString(2, user.getTelephoneNumber());
			preparedStatement.setString(3, user.getFirstName());
			preparedStatement.setString(4, user.getLastName());
			preparedStatement.setString(5, user.getRole());
			preparedStatement.setString(6, user.getStatus());
			preparedStatement.setInt(7, user.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public void delete(Integer id) {
		try {
			preparedStatement = connection.prepareStatement(DELETE_BY_ID);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<User> readAll() {
		List<User> listOfUsers = new ArrayList<>();
		try {
			preparedStatement = connection.prepareStatement(READ_ALL);
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				int userId = result.getInt("id");
				String email = result.getString("email");
				String telephoneNumber = result.getString("telephone_number");
				String firstName = result.getString("first_name");
				String lastName = result.getString("last_name");
				String role = result.getString("role");
				String status = result.getString("status");
				
				listOfUsers.add(new User(userId, email, telephoneNumber, firstName, lastName, role, status));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listOfUsers;
	}

}

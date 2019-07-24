package ua.lviv.lgs.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ua.lviv.lgs.dao.SubscriptionDao;
import ua.lviv.lgs.domain.Subscription;
import ua.lviv.lgs.utils.ConnectionUtil;

public class SubscriptionDaoImpl implements SubscriptionDao {

	private static String READ_ALL = "select * from subscription";
	private static String CREATE = "insert into subscription(user_id, magazine_catalog_id, release_date) values (?,?,?)";
	private static String READ_BY_ID = "select * from subscription where id =?";
	private static String DELETE_BY_ID = "delete from subscription where id=?";

	private Connection connection;
	private PreparedStatement preparedStatement;

	public SubscriptionDaoImpl()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		connection = ConnectionUtil.openConnection();
	}

	@Override
	public Subscription create(Subscription subscription) {
		try {
			preparedStatement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, subscription.getUserId());
			preparedStatement.setInt(2, subscription.getMagazineId());
			preparedStatement.setDate(3, new Date(subscription.getReleaseDate().getTime()));
			preparedStatement.executeUpdate();

			ResultSet rs = preparedStatement.getGeneratedKeys();
			rs.next();
			subscription.setId(rs.getInt(1));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return subscription;
	}

	@Override
	public Subscription read(Integer id) {
		Subscription subscription = null;
		try {
			preparedStatement = connection.prepareStatement(READ_BY_ID);
			preparedStatement.setInt(1, id);
			ResultSet result = preparedStatement.executeQuery();
			result.next();

			int subscriptionId = result.getInt("id");
			int userId = result.getInt("user_id");
			int magazineCatalogId = result.getInt("magazine_catalog_id");
			java.util.Date releaseDate = result.getDate("release_date");

			subscription = new Subscription(subscriptionId, userId, magazineCatalogId, releaseDate);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return subscription;
	}

	@Override
	public Subscription update(Subscription t) {
		throw new IllegalStateException("There is no update for subscription");
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
	public List<Subscription> readAll() {
		List<Subscription> listOfSubscription = new ArrayList<>();
		try {
			preparedStatement = connection.prepareStatement(READ_ALL);
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				int subscriptionId = result.getInt("id");
				int userId = result.getInt("user_id");
				int magazineCatalogId = result.getInt("magazine_catalog_id");
				java.util.Date releaseDate = result.getDate("release_date");

				listOfSubscription.add(new Subscription(subscriptionId, userId, magazineCatalogId, releaseDate));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listOfSubscription;
	}

}

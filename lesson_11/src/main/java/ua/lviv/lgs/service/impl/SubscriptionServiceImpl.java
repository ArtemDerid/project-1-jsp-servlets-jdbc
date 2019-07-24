package ua.lviv.lgs.service.impl;

import java.sql.SQLException;
import java.util.List;

import ua.lviv.lgs.dao.SubscriptionDao;
import ua.lviv.lgs.dao.impl.SubscriptionDaoImpl;
import ua.lviv.lgs.domain.Subscription;
import ua.lviv.lgs.service.SubscriptionService;

public class SubscriptionServiceImpl implements SubscriptionService {

	private SubscriptionDao subscriptionDao;

	public SubscriptionServiceImpl() {
		try {
			subscriptionDao = new SubscriptionDaoImpl();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Subscription create(Subscription t) {
		return subscriptionDao.create(t);
	}

	@Override
	public Subscription read(Integer id) {
		return subscriptionDao.read(id);
	}

	@Override
	public Subscription update(Subscription t) {
		return subscriptionDao.update(t);
	}

	@Override
	public void delete(Integer id) {
		subscriptionDao.delete(id);
	}

	@Override
	public List<Subscription> readAll() {
		return subscriptionDao.readAll();
	}

}

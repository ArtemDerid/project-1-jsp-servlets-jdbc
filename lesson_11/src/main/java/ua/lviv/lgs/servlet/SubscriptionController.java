package ua.lviv.lgs.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.lviv.lgs.domain.Subscription;
import ua.lviv.lgs.service.SubscriptionService;
import ua.lviv.lgs.service.impl.SubscriptionServiceImpl;


@WebServlet("/subscription")
public class SubscriptionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SubscriptionService subscriptionService = SubscriptionServiceImpl.getSubscriptionService();
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String magazineId = request.getParameter("magazineId");
		
		HttpSession session = request.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		Subscription subscription = new Subscription(userId, Integer.parseInt(magazineId), new Date());
		subscriptionService.create(subscription);
		
		
		response.setContentType("text");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write("Success");
	}

}

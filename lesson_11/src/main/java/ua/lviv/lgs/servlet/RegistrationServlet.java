package ua.lviv.lgs.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.lviv.lgs.domain.User;
import ua.lviv.lgs.domain.UserRole;
import ua.lviv.lgs.domain.UserStatus;
import ua.lviv.lgs.service.UserService;
import ua.lviv.lgs.service.impl.UserServiceImpl;

public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService = UserServiceImpl.getUserService();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String telephoneNumber = request.getParameter("telephoneNumber");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		

		if (!email.isEmpty() && !firstName.isEmpty() && !lastName.isEmpty() && !password.isEmpty()) {
			userService.create(new User(email, password, telephoneNumber, firstName, lastName, UserRole.USER.toString(), UserStatus.REGULAR_USER.toString()));
		}

		request.setAttribute("userEmail", email);
		request.setAttribute("userFirstName", firstName);
		request.getRequestDispatcher("userAccount.jsp").forward(request, response);
	}

}

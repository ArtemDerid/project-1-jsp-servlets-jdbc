package ua.lviv.lgs.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.lviv.lgs.domain.Magazine;
import ua.lviv.lgs.service.MagazineService;
import ua.lviv.lgs.service.impl.MagazineServiceImpl;

@WebServlet("/magazine")
public class MagazineController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MagazineService magazineService = MagazineServiceImpl.getMagazineService();
       
	// to create resource (product)
		@Override
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String name = request.getParameter("name");
			String genre = request.getParameter("genre");
			String description = request.getParameter("description");
			String price = request.getParameter("price");
			String numberOfPages = request.getParameter("numberOfPages");
			
			Magazine magazine  = new Magazine(name, genre, description, getValidatedPrice(price), getValidatedNumberOfPages(numberOfPages));
			magazineService.create(magazine);
			response.setContentType("text");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write("Success");
		}
		
		private double getValidatedPrice(String price) {
			if(price == null || price.isEmpty()) {
				return 0;
			}
			return Double.parseDouble(price);
		}
		
		private int getValidatedNumberOfPages(String numberOfPages) {
			if(numberOfPages == null || numberOfPages.isEmpty()) {
				return 0;
			}
			return Integer.parseInt(numberOfPages);
		}

		// to get resource (product)
		@Override
		protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {

		}

		// to update resource (product)
		@Override
		protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

			super.doPut(req, resp);
		}

		// to delete resource (product)
		@Override
		protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

			super.doDelete(req, resp);
		}


}

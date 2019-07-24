package ua.lviv.lgs.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import ua.lviv.lgs.domain.Magazine;
import ua.lviv.lgs.domain.Subscription;
import ua.lviv.lgs.dto.SubscriptionDto;
import ua.lviv.lgs.service.MagazineService;
import ua.lviv.lgs.service.SubscriptionService;
import ua.lviv.lgs.service.impl.MagazineServiceImpl;
import ua.lviv.lgs.service.impl.SubscriptionServiceImpl;

@WebServlet("/subscriptions")
public class SubscriptionsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private	SubscriptionService subscriptionService = SubscriptionServiceImpl.getSubscriptionService();
	private MagazineService magazineService = MagazineServiceImpl.getMagazineService();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Subscription> subscriptions = subscriptionService.readAll();
		Map<Integer, Magazine> magazineId = magazineService.readAllMap();
		List<SubscriptionDto> listOfSubscriptionDtos = map(subscriptions, magazineId);
		
		String json = new Gson().toJson(listOfSubscriptionDtos);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}
	
	public List<SubscriptionDto> map(List<Subscription> subscriptions, Map<Integer, Magazine> magazineId){
		return subscriptions.stream().map(subscription->{
			SubscriptionDto subscriptionDto = new SubscriptionDto();
			subscriptionDto.subscriptionId = subscription.getId();
			subscriptionDto.releaseDate = subscription.getReleaseDate();
			
			Magazine magazine = magazineId.get(subscription.getMagazineId());
			subscriptionDto.name = magazine.getName();
			subscriptionDto.genre = magazine.getGenre();
			subscriptionDto.description = magazine.getDescription();
			subscriptionDto.price = magazine.getPrice();
			subscriptionDto.numberOfPages = magazine.getNumberOfPages();
			
			return subscriptionDto;
		}).collect(Collectors.toList());
	}

	

}

package ua.lviv.lgs.app;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import ua.lviv.lgs.domain.Magazine;
import ua.lviv.lgs.domain.Subscription;
import ua.lviv.lgs.domain.User;
import ua.lviv.lgs.service.MagazineService;
import ua.lviv.lgs.service.SubscriptionService;
import ua.lviv.lgs.service.UserService;
import ua.lviv.lgs.service.impl.MagazineServiceImpl;
import ua.lviv.lgs.service.impl.SubscriptionServiceImpl;
import ua.lviv.lgs.service.impl.UserServiceImpl;

public class Application {
	
	public static void main(String[] args) {

		//Work with User
		List<User> listOfUsers = new ArrayList<>();

		listOfUsers.add(new User("Petro@i.ua", "0975553322", "Petro", "Mogyla", "visitor", "regular"));
		listOfUsers.add(new User("Semen@i.ua", "0974443322", "Semen", "Pyppka", "subsciber", "gold"));
		listOfUsers.add(new User("Hugo@i.ua", "0973333322", "Hugo", "Larson", "subsciber", "platinum"));
		listOfUsers.add(new User("arsen@i.ua", "0971113322", "Arsen", "Myrozian", "visitor", "regular"));
		listOfUsers.add(new User("panas@i.ua", "0978703322", "Panas", "Faino", "admin", "superb"));
		listOfUsers.add(new User("pylyp@i.ua", "0978903322", "Pylyp", "Maxno", "visitor", "visitor"));
		listOfUsers.add(new User("Sam@i.ua", "0975558976", "Sam", "Larry", "subsciber", "regular"));
		listOfUsers.add(new User("vil@i.ua", "0975553892", "Villey", "Osborn", "subsciber", "gold"));
		listOfUsers.add(new User("Rivnyy@i.ua", "0975438322", "Petro", "Rivnyy", "subsciber", "regular"));

		UserService userService = new UserServiceImpl();
		listOfUsers.forEach(user -> userService.create(user));

		userService.readAll().forEach(System.out::println);
		System.out.println("***********************************************************\n");

		User user = userService.read(4);
		System.out.println(user);
		System.out.println("***********************************************************\n");

		user.setRole("subscriber");
		user.setStatus("gold");
		userService.update(user);
		System.out.println(userService.read(4));
		System.out.println("***********************************************************\n");

		userService.delete(1);
		userService.readAll().forEach(System.out::println);
		System.out.println("***********************************************************\n");

		//Work with magazine
		List<Magazine> listOfMagazines = new ArrayList<>();

		listOfMagazines.add(new Magazine("Shoes", "fassion", "some description", 280, 345));
		listOfMagazines.add(new Magazine("Dress", "fassion", "some description", 340, 545));
		listOfMagazines.add(new Magazine("Pizza", "food", "some other description", 120, 759));
		listOfMagazines.add(new Magazine("Men", "life", "some description", 509, 290));
		listOfMagazines.add(new Magazine("Cars", "technology", "some other description", 490, 295));
		listOfMagazines.add(new Magazine("Style", "fassion", "some other description", 600, 317));
		listOfMagazines.add(new Magazine("Info", "technology", "some description", 34, 49));

		MagazineService magazineService = new MagazineServiceImpl();
		listOfMagazines.forEach(magazine -> magazineService.create(magazine));

		magazineService.readAll().forEach(System.out::println);
		System.out.println("***********************************************************\n");

		Magazine magazine = magazineService.read(2);
		System.out.println(magazine);
		System.out.println("***********************************************************\n");

		magazine.setDescription("bestseller!!!");
		magazineService.update(magazine);
		System.out.println(magazineService.read(2));
		System.out.println("***********************************************************\n");

		magazineService.delete(1);
		magazineService.readAll().forEach(System.out::println);
		System.out.println("***********************************************************\n");
		
		//Work with subscription
		List<Subscription> listOfSubscriptions = new ArrayList<>();

		listOfSubscriptions.add(new Subscription(4, 3, getDate(2019, 7, 1)));
		listOfSubscriptions.add(new Subscription(5, 2, getDate(2019, 5, 13)));
		listOfSubscriptions.add(new Subscription(2, 6, getDate(2019, 1, 10)));
		listOfSubscriptions.add(new Subscription(7, 4, getDate(2019, 4, 11)));
		listOfSubscriptions.add(new Subscription(3, 5, getDate(2019, 3, 21)));
		

		SubscriptionService subscriptionService = new SubscriptionServiceImpl();
		listOfSubscriptions.forEach(subscription -> subscriptionService.create(subscription));

		subscriptionService.readAll().forEach(System.out::println);
		System.out.println("***********************************************************\n");

		Subscription subscription = subscriptionService.read(3);
		System.out.println(subscription);
		System.out.println("***********************************************************\n");

		System.out.println(subscriptionService.read(3));
		System.out.println("***********************************************************\n");

		subscriptionService.delete(1);
		subscriptionService.readAll().forEach(System.out::println);
		System.out.println("***********************************************************\n");
		
	}
	
	public static Date getDate(int year,int month,int date){
	    Calendar cal = Calendar.getInstance();
	    cal.set(Calendar.YEAR, year);
	    cal.set(Calendar.MONTH, month-1);
	    cal.set(Calendar.DAY_OF_MONTH, date);
	    return cal.getTime();
	}

}

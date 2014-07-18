package controllers;

import java.util.List;

import models.Restaurant;
import play.*;
import play.data.Form;
import play.db.ebean.Model;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    private static Object Restaurants;
	public static Result index() {
        return ok(index.render("Hello, World"));
    }
    public static Result addRestaurant(){
    	Restaurant restaurant=Form.form(Restaurant.class).bindFromRequest().get();
    	restaurant.save();
    	return redirect(routes.Application.index());
    }
    /*public static Result getRestaurants()
    {
    	List<Restaurant> list=new Model.Finder(String.class, Restaurant.class).all();
    	return ok(toJson(Restaurants));
    }*/

}

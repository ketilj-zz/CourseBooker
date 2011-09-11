package controllers;

import java.util.List;

import models.User;
import controllers.Secure.Security;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.With;

@With(Secure.class)
public class Admin extends Controller{

	@Before
	static void setConnectedUser(){
		if (Security.isConnected()){
			User user = User.find("byEmail", Security.connected()).first();
			renderArgs.put("user", user.fullName);
		}
	}
	
	public static void index(){
        render();
	}
}

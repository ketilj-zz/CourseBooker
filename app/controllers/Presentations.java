package controllers;

import play.mvc.With;
import controllers.CRUD;
import controllers.Secure;

@With(Secure.class)
public class Presentations extends CRUD{

}

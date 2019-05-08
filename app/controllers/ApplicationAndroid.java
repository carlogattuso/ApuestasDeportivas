package controllers;

import models.Usuario;
import play.mvc.Controller;

import javax.servlet.http.HttpSession;

public class ApplicationAndroid extends Controller {

    public static void login(String username, String pass)
    {
        Usuario u = Usuario.find("byUsernameAndPassword",username,pass).first();
        if (u== null){
            renderText("404");
        }else{
        renderText("200");}
    }
}

package controllers;

import models.Jornada;
import models.Usuario;
import play.mvc.Controller;

public class ApplicationAndroid extends Controller {

    public static void login(String username, String pass)
    {
        Usuario u = Usuario.find("byUsernameAndPassword",username,pass).first();
        if (u== null){
            renderText("404");
        }else{
        renderText("200");}
    }
    public static void getMatchDay(){
        Jornada j = Jornada.find("byNum_jornada",1).first();
        if(j==null)renderText("404");
        else renderJSON(j.partidos);
    }
}

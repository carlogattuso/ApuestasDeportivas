package controllers;

import com.google.gson.JsonObject;
import org.hibernate.engine.jdbc.connections.internal.UserSuppliedConnectionProviderImpl;
import play.*;
import play.mvc.*;

import play.*;
import play.data.validation.*;

import java.util.*;

import java.util.*;

import models.*;

public class Application extends Controller {

    @Before
    static void addUser() {

        Usuario user = connected();

        if(user != null) {
            renderArgs.put("user", user);
        }
    }
    static Usuario connected() {

        if(renderArgs.get("user") != null) {
            return renderArgs.get("user", Usuario.class);
        }
        String username = session.get("user");
        if(username != null) {
            return Usuario.find("byUsername", username).first();
        }
        return null;
    }
    public static void start() {

        if(connected() != null) {
            Usuario c = renderArgs.get("user", Usuario.class);
            renderTemplate("Application/HomePage.html");
        }else {

            //Creación de usuarios
            Usuario u = new Usuario("carlo", "carlo", "Carlo", "Gattuso Garrido", "carlogc46@gmail.com", 20, 45).save();
            Usuario m = new Usuario("mario", "mario", "Mario", "Sanchez Boto", "marioboto98@gmail.com", 21, 45).save();
            Usuario p = new Usuario("hannibal", "hannibal", "Hannibal", "Rabasso Dominguez", "hannibalrd@gmail.com", 20, 45).save();
            Usuario l = new Usuario("pol", "pol", "Pol", "Gascon Tomas", "polgt@gmail.com", 20, 40).save();
            Usuario s = new Usuario("marta", "marta", "Marta", "Delgado Palau", "martadelgadopalau@gmail.com", 21, 45).save();

            Jornada j = new Jornada(1).save();

            //Crear partidos jornada 1
            Partido p1 = new Partido(2019, 5, 15, 13, 0, "Old Trafford", "Manchester United", "Arsenal", 1.5, 3, 2, "null").save();
            Partido p2 = new Partido(2019, 5, 15, 13, 0, "Anfield", "Liverpool", "Chelsea", 1.3, 2.5, 4, "null").save();
            Partido p3 = new Partido(2019, 5, 15, 13, 0, "Tottenham Hotspur Stadium\n", "Tottenham", "Mancherster City", 1.8, 3, 2.4, "null").save();

            j.partidos.add(p1);
            j.partidos.add(p2);
            j.partidos.add(p3);

            j.save();

            //Crear apuestas

            Apuesta a;

            a = new Apuesta(5, "1").save();
            a.usuario = u;
            a.partido = p1;
            a.jornada = j;

            a.save();


            a = new Apuesta(5, "2").save();
            a.usuario = m;
            a.partido = p2;
            a.jornada = j;

            a.save();

            a = new Apuesta(5, "x").save();
            a.usuario = p;
            a.partido = p3;
            a.jornada = j;

            a.save();

            renderTemplate("Application/start.html");
        }
    }
    public static void login(String username, String pass){
        Usuario u = Usuario.find("byUsernameAndPassword",username,pass).first();

        if (u== null) {
            renderText("Usuario incorrecto");
        }
            else {
            session.put("user", u.username);
            renderTemplate("Application/HomePage.html");
        }
        }
    public static void logout() {
        session.clear();
    }
    public static void getInfoSession(){
        renderText("Està connectat "+ session.get("user"));
    }
    public static void getUsername(){
        Usuario user = Usuario.find("byUsername",session.get("user")).first();
        renderJSON(user);
    }
    public static void register(){
        render();
    }
    public static void getJornada(){
        Jornada j = Jornada.find("byNum_jornada",1).first();
        if(j==null)renderText("404");
        else renderJSON(j.partidos);
    }
    public static void Mybets(){
        renderTemplate("Application/Mybets.html");
    }
    public static void getSaldo(){
        Usuario user =  Usuario.find("byUsername",session.get("user")).first();
        renderJSON(user.getSaldo());
    }
    public static void getApuestas(){
        Usuario user =  Usuario.find("byUsername",session.get("user")).first();
        List<Apuesta> apuestas = Apuesta.find ("byUsuario", user).fetch();
        System.out.println(apuestas);
        renderJSON(apuestas);
    }
    public static void register_params(String username, String pass, String name, String surname, String mail, String age) {

        Usuario found = Usuario.find("byUsername",username).first();
        if(found==null) {
            Usuario u = new Usuario(username, pass, name, surname, mail, Integer.parseInt(age), 0).save();
            renderTemplate("Application/HomePage.html");
        }
        else {
            renderTemplate("errors/500.html");
        }
    }


}
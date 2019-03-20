package controllers;

import com.google.gson.JsonObject;
import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller {

    public static void index() {
        render();
    }
    public static void start() {

        //Creación de usuarios
        Usuario u = new Usuario("carlo","carlo","Carlo","Gattuso Garrido","carlogc46@gmail.com",20,45).save();
        Usuario m = new Usuario("mario","mario","Mario","Sanchez Boto","marioboto98@gmail.com",21,45).save();
        Usuario p = new Usuario("hannibal","hannibal","Hannibal","Rabasso Dominguez","hannibalrd@gmail.com",20,45).save();
        Usuario l = new Usuario("pol","pol","Pol","Gascon Tomas","polgt@gmail.com",20,40).save();
        Usuario s = new Usuario("marta","marta","Marta","Delgado Palau","martadelgadopalau@gmail.com",21,45).save();

        Jornada j = new Jornada(1).save();

        //Crear partidos jornada 1
        Partido p1 = new Partido(2019,5,15,13, 0,"Camp Nou","Barcelona","Real Madrid",1.5,3,2,"null").save();
        Partido p2 = new Partido(2019,5,15,13, 0,"Mestalla","Valencia","Alaves",1.3,2.5,4,"null").save();
        Partido p3 = new Partido(2019,5,15,13, 0,"Benito Villamarin","Betis","Sevilla",1.8,3,2.4,"null").save();
        Partido p4 = new Partido(2019,5,15,13, 0,"Anoeta","Real Sociedad","Atletico de Madrid",2,3,1.8,"null").save();
        Partido p5 = new Partido(2019,5,15,13, 0,"Balaidos","Celta de Vigo","Español",1.5,3,2,"null").save();

        j.partidos.add(p1);
        j.partidos.add(p2);
        j.partidos.add(p3);
        j.partidos.add(p4);
        j.partidos.add(p5);

        j.save();

        //Crear apuestas

        Apuesta a;

        a = new Apuesta(5,"1").save();
        a.usuario = u;
        a.partido = p1;
        a.jornada = j;

        a.save();


        a = new Apuesta(5,"2").save();
        a.usuario = m;
        a.partido = p2;
        a.jornada = j;

        a.save();

        a = new Apuesta(5,"x").save();
        a.usuario = p;
        a.partido = p3;
        a.jornada = j;

        a.save();

        a = new Apuesta(10,"1").save();
        a.usuario = l;
        a.partido = p4;
        a.jornada = j;

        a.save();

        Apuesta e = new Apuesta(5,"1").save();
        e.usuario = s;
        e.partido = p5;
        e.jornada = j;

        e.save();

        render();
    }
    public static void login(String username, String pass) {
        Usuario u = Usuario.find("byUsernameAndPassword",username,pass).first();

        if (u== null){
            renderText("Usuario incorrecto");
        }else{
            render();
        }
    }
    public static void register(){
        render();
    }
    public static void register_params(String username, String pass, String name, String surname, String mail, String age) {

        Usuario found = Usuario.find("byUsername",username).first();
        if(found!=null) {
            Usuario u = new Usuario(username, pass, name, surname, mail, Integer.parseInt(age), 0).save();
            renderTemplate("Application/login.html");
        }
        else {
            renderTemplate("errors/500.html");
        }
    }
}
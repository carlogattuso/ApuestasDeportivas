package controllers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jdk.nashorn.internal.runtime.Debug;
import models.*;
import play.mvc.Controller;
import sun.misc.FormattedFloatingDecimal;
import sun.rmi.runtime.Log;

import javax.xml.transform.Result;
import java.io.Console;
import java.io.InputStreamReader;
import java.text.Normalizer;
import java.util.ArrayList;

public class ApplicationAndroid extends Controller {

    public static void login(String username, String pass) {
        Usuario u = Usuario.find("byUsernameAndPassword", username, pass).first();
        if (u == null) {
            renderText("404");
        } else {
            renderText("200");
        }
    }
    public static void getSaldo(){
        Usuario user =  Usuario.find("byUsername",session.get("user")).first();
        renderJSON(user.getSaldo());
    }
    public static void register(String username, String pass) {
        Usuario u = Usuario.find("byUsernameAndPassword", username, pass).first();
        if (u == null) {
            Usuario a = new Usuario (username,pass,"10","10","10@aprobados",22,100);
            a.save();
            renderText("200");
        } else {
            renderText("404");
        }
    }

    public static void getMatchDay() {
        Jornada j = Jornada.find("byNum_jornada", 1).first();
        ArrayList<PartidoTO> partidoTOArrayList = new ArrayList<PartidoTO>();
        if (j == null) renderText("404");
        else {
            for (Partido p : j.partidos) {
                PartidoTO partido = new PartidoTO();
                partido.setLocal(p.getLocal());
                partido.setVisitante(p.getVisitante());
                partido.setCuota_1(p.getCuota_1());
                partido.setCuota_X(p.getCuota_X());
                partido.setCuota_2(p.getCuota_2());
                partidoTOArrayList.add(partido);
            }
            renderJSON(partidoTOArrayList);
        }
    }
    public static void createBet() {
        JsonObject json;
        JsonElement element = new JsonParser().parse(
                new InputStreamReader(request.body)
        );
        json = element.getAsJsonObject();

        double importe = Double.parseDouble(json.get("importe").toString());

        String pronostico = json.get("pronostico").getAsString();

        String username = json.get("username").getAsString();

        String idPartido = json.get("idPartido").getAsString();

        Apuesta apuesta = new Apuesta(importe,pronostico).save();

        Usuario user =  Usuario.find("byUsername",username).first();

        Partido partido = Partido.find("byLocal",idPartido).first();

        Jornada j = Jornada.find("byNum_jornada",1).first();

            apuesta.usuario = user;
            apuesta.partido = partido;
            apuesta.jornada = j;
            apuesta.save();

        System.out.println(importe+","+pronostico+","+username+","+idPartido);

        renderJSON(json);
    }
}


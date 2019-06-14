package controllers;

import com.google.gson.JsonObject;
import jdk.nashorn.internal.runtime.Debug;
import models.*;
import play.mvc.Controller;
import sun.misc.FormattedFloatingDecimal;
import sun.rmi.runtime.Log;

import javax.xml.transform.Result;
import java.io.Console;
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

        String pronostico = json.get("pronostico").toString();

        String username = json.get("username").toString();

        Long idPartido = Long.parseLong(json.get("idPartido").toString());

        System.out.println(importe+","+pronostico+","+username+","+idPartido);

        renderText("Apuesta añadida");
    }
}

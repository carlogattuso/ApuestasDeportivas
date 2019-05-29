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

    public static void createBet(ApuestaAndroid apuestaAndroid) {
        Apuesta a;

        a = new Apuesta(Double.parseDouble(apuestaAndroid.getImporte()), apuestaAndroid.getPronostico()).save();

        Usuario user = Usuario.find("byUsername", session.get("user")).first();

        Partido partido = Partido.findById(apuestaAndroid.getIdPartido());

        Jornada j = Jornada.find("byNum_jornada", 1).first();

        a.usuario = user;
        a.partido = partido;
        a.jornada = j;

        a.save();

        renderText("Apuesta añadida");
    }
}

package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import java.sql.Time;
import java.sql.Date;
import java.util.Calendar;

@Entity
public class Partido extends Model {

    Date fecha;
    Time hora;
    String estadio;
    String local;
    String visitante;
    double cuota_1;
    double cuota_X;
    double cuota_2;
    String resultado;

    public Partido(int año, int mes, int dia, int hora, int minutos, String estadio, String local, String visitante, double cuota_1, double cuota_X, double cuota_2, String resultado) {
        this.estadio = estadio;
        this.local = local;
        this.visitante = visitante;
        this.cuota_1 = cuota_1;
        this.cuota_X = cuota_X;
        this.cuota_2 = cuota_2;
        this.resultado = resultado;
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, año);
        cal.set(Calendar.MONTH, mes - 1);
        cal.set(Calendar.DAY_OF_MONTH, dia);
        fecha = new java.sql.Date(cal.getTimeInMillis());
        this.hora = new Time(hora,minutos,00);
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public String getEstadio() {
        return estadio;
    }

    public void setEstadio(String estadio) {
        this.estadio = estadio;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getVisitante() {
        return visitante;
    }

    public void setVisitante(String visitante) {
        this.visitante = visitante;
    }

    public double getCuota_1() {
        return cuota_1;
    }

    public void setCuota_1(double cuota_1) {
        this.cuota_1 = cuota_1;
    }

    public double getCuota_X() {
        return cuota_X;
    }

    public void setCuota_X(double cuota_X) {
        this.cuota_X = cuota_X;
    }

    public double getCuota_2() {
        return cuota_2;
    }

    public void setCuota_2(double cuota_2) {
        this.cuota_2 = cuota_2;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
}

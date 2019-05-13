package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Apuesta extends Model {

    double importe;
    String pronostico;

    @ManyToOne
    public Usuario usuario;

    @ManyToOne
    public Partido partido;

    @ManyToOne
    public Jornada jornada;

    public Apuesta(double importe, String pronostico) {
        this.importe = importe;
        this.pronostico = pronostico;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public String getPronostico() {
        return pronostico;
    }

    public void setPronostico(String pronostico) {
        this.pronostico = pronostico;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Partido getPartido() {
        return partido;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }

    public Jornada getJornada() {
        return jornada;
    }

    public void setJornada(Jornada jornada) {
        this.jornada = jornada;
    }

    @Override
    public String toString() {
        return "Apuesta{" +
                "importe=" + importe +
                ", pronostico='" + pronostico + '\'' +
                ", usuario=" + usuario +
                ", partido=" + partido +
                ", jornada=" + jornada +
                '}';
    }
}

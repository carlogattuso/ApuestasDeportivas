package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Jornada extends Model {

    int num_jornada;

    @OneToMany
    public List<Partido> partidos = new ArrayList<Partido>();

    public Jornada(int num_jornada) {
        this.num_jornada = num_jornada;
    }

    public int getNum_jornada() {
        return num_jornada;
    }

    public void setNum_jornada(int num_jornada) {
        this.num_jornada = num_jornada;
    }

    public List<Partido> getPartidos() {
        return partidos;
    }

    public void setPartidos(List<Partido> partidos) {
        this.partidos = partidos;
    }
}

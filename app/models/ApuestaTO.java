package models;

public class ApuestaTO {

    private long id;
    private long idPartido;
    private double importe;
    private String pronostico;

    public ApuestaTO(long id, long idPartido, double importe, String pronostico) {
        this.id = id;
        this.idPartido = idPartido;
        this.importe = importe;
        this.pronostico = pronostico;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdPartido() {
        return idPartido;
    }

    public void setIdPartido(long idPartido) {
        this.idPartido = idPartido;
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

    @Override
    public String toString() {
        return "Apuesta{" +
                "importe=" + importe +
                ", pronostico='" + pronostico + '\'' +
                ", id=" + this.id +
                ", idPartido=" + this.idPartido +
                '}';
    }
}


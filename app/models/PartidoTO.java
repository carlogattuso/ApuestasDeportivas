package models;

public class PartidoTO {
    private String local;
    private String visitante;
    private double cuota_1;
    private double cuota_X;
    private double cuota_2;

    public PartidoTO(String local, String visitante, double cuota_1, double cuota_X, double cuota_2) {
        this.local = local;
        this.visitante = visitante;
        this.cuota_1 = cuota_1;
        this.cuota_X = cuota_X;
        this.cuota_2 = cuota_2;
    }

    public PartidoTO() {
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
}

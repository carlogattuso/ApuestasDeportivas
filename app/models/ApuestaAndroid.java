package models;

public class ApuestaAndroid {


    private String username;
    private String importe;
    private int idPartido;
    private String pronostico;

    public ApuestaAndroid() {
    }

    public ApuestaAndroid(String username, String importe, int idPartido, String pronostico) {
        this.username = username;
        this.importe = importe;
        this.idPartido = idPartido;
        this.pronostico = pronostico;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImporte() {
        return importe;
    }

    public void setImporte(String importe) {
        this.importe = importe;
    }

    public int getIdPartido() {
        return idPartido;
    }

    public void setIdPartido(int idPartido) {
        this.idPartido = idPartido;
    }

    public String getPronostico() {
        return pronostico;
    }

    public void setPronostico(String pronostico) {
        this.pronostico = pronostico;
    }
}

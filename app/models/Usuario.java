package models;

import play.data.validation.Required;
import play.db.jpa.Model;

import javax.persistence.Entity;

@Entity
public class Usuario extends Model {

    @Required
    public String username;
    @Required
    public String password;
    String nombre;
    String apellidos;
    String correo;
    int edad;
    double saldo;

    public Usuario(String username, String password, String nombre, String apellidos, String correo, int edad, double saldo) {
        this.username = username;
        this.password = password;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correo = correo;
        this.edad = edad;
        this.saldo = saldo;
    }

    public static Usuario connect(String n, String p){
        Usuario u = Usuario.find("byNomAndPassword",n,p).first();
        return u;
    }

    public void añadirApuesta(Partido partido, double importe, String pronostico){

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}

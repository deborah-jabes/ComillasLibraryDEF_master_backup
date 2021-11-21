package dtc.isw.domain;

import java.util.ArrayList;

public class Usuario {
    String username;
    String password;
    String correo;
    ArrayList<Reserva> sreservados;

    public Usuario(String username, String password, String correo, ArrayList<Reserva> sreservados) {
        this.username = username;
        this.password = password;
        this.correo = correo;
        this.sreservados = sreservados;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public ArrayList<Reserva> getSreservados() {
        return sreservados;
    }

    public void setSreservados(ArrayList<Reserva> sreservados) {
        this.sreservados = sreservados;
    }
}

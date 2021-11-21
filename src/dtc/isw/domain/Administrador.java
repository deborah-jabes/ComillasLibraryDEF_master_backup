package dtc.isw.domain;

import java.util.ArrayList;

public class Administrador extends Usuario{
    int idAdmin;

    public Administrador(String username, String password, String correo, ArrayList<Reserva> sreservados, int idAdmin)
    {
        super(username,password,correo,sreservados);
        this.idAdmin = idAdmin;
    }

    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }
}

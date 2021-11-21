package dtc.isw.domain;

public class Reserva {
    int idreserva;
    String hi; // Hora inicial
    String hf; // Hora final

    public Reserva(int idreserva, String hi, String hf) {
        this.idreserva = idreserva;
        this.hi = hi;
        this.hf = hf;
    }

    public int getIdreserva() {
        return idreserva;
    }

    public void setIdreserva(int idreserva) {
        this.idreserva = idreserva;
    }

    public String getHi() {
        return hi;
    }

    public void setHi(String hi) {
        this.hi = hi;
    }

    public String getHf() {
        return hf;
    }

    public void setHf(String hf) {
        this.hf = hf;
    }
}

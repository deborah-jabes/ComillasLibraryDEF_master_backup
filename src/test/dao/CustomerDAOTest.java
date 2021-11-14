package test.dao;

import dtc.isw.dao.CustomerDAO;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

import java.util.HashMap;


class CustomerDAOTest extends TestCase {
    private static CustomerDAO  dao = null;

    @Test
    public void checkCustomer() {
        String tabla = "listausuarios";
        String username = "Admin"; // Admin en la tabla --> return true
    int columna = 1;

        assertEquals(true, dao.checkCustomer(tabla,username,columna));
    }

    @Test
    public void checkCustomerCond() {
        String tabla = "listausuarios";
        String cond = "username = 'Admin'";
        int columna = 2;
        String password = "2"; // Contrasenya correcta: password (2 es de otro usuario) --> return false

        assertEquals(false, dao.checkCustomerCond(tabla,password,cond,columna));
    }

    @Test
    public void getColumnCond() {
        String tabla = "reservas";
        String condicion = "username = 'Admin'"; // Valor por defecto --> ideal para comprobar la funcion
        int columna = 2;

        HashMap<String,Object> res = dao.getColumnCond(tabla,condicion,columna);

        assertEquals("13:00",res.get("0"));
    }
}
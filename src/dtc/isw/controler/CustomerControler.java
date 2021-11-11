package dtc.isw.controler;

import java.util.ArrayList;
import java.util.HashMap;

import dtc.isw.dao.CustomerDAO;
import dtc.isw.domain.Customer;

public class CustomerControler {

    public void getCustomer(ArrayList<Customer> lista) {
        CustomerDAO.getClientes(lista);
    }

    public boolean checkCustomerCond(String tabla, String user, String cond , int column) {
        boolean b = CustomerDAO.checkCustomerCond(tabla, user, cond, column);
        return b;
    }

    public boolean checkCustomer(String tabla, String user, int column) {
        boolean b = CustomerDAO.checkCustomer(tabla, user, column);
        return b;
    }

    public HashMap<String,Object> getColumnCond(String tabla, String condicion, int column)
    {
        HashMap<String,Object> h = CustomerDAO.getColumnCond(tabla,condicion,column);
        return h;
    }
    public HashMap<String,Object> getColumn(String tabla, int column)
    {
        HashMap<String,Object> h = CustomerDAO.getColumn(tabla,column);
        return h;
    }

    public void updateColumnCond(String tabla, String valor, String condicion)
    {
        CustomerDAO.updateColumnCond(tabla,valor,condicion);
    }

    public void updateColumn(String tabla, String valor)
    {
        CustomerDAO.updateColumn(tabla,valor);
    }

    public void insertColumn(String tabla, String valores)
    {
        CustomerDAO.insertValue(tabla,valores);
    }

    public void deleteValue(String tabla, String cond)
    {
        CustomerDAO.deleteValue(tabla,cond);
    }
}
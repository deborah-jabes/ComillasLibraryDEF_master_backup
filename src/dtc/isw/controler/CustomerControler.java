package dtc.isw.controler;

import java.util.ArrayList;

import dtc.isw.dao.CustomerDAO;
import dtc.isw.domain.Customer;

public class CustomerControler {

    public void getCustomer(ArrayList<Customer> lista) {
        CustomerDAO.getClientes(lista);
    }

    public boolean checkCustomer(String user, int column){
        boolean b = CustomerDAO.checkCustomer(user,column);
        return b;
    }
}
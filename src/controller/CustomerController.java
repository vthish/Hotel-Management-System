package controller;

import dao.CustomerDAO;
import java.util.List;

public class CustomerController {

    private CustomerDAO customerDAO = new CustomerDAO();

    // SAVE
    public void saveCustomer(String name, String phone, String email) {

        if (name == null || name.isEmpty()) {
            System.out.println("Name is required");
            return;
        }

        customerDAO.addCustomer(name, phone, email);
    }

    // GET ALL
    public List<String[]> getCustomers() {
        return customerDAO.getAllCustomers();
    }

    // UPDATE
    public void updateCustomer(String id, String name, String phone, String email) {
        customerDAO.updateCustomer(id, name, phone, email);
    }

    // DELETE
    public void deleteCustomer(String id) {
        customerDAO.deleteCustomer(id);
    }
}
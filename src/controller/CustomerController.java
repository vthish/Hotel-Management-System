package controller;

import dao.CustomerDAO;

public class CustomerController {

    private CustomerDAO customerDAO = new CustomerDAO();

    public void saveCustomer(String name, String phone, String email) {

        if (name == null || name.isEmpty()) {
            System.out.println("Name is required");
            return;
        }

        customerDAO.addCustomer(name, phone, email);
    }
}
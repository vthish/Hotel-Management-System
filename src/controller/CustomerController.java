package controller;

import dao.CustomerDAO;
import java.util.List;
import java.util.regex.Pattern;

public class CustomerController {

    private CustomerDAO customerDAO = new CustomerDAO();

    public String saveCustomer(String name, String phone, String email) {

        if (name == null || name.trim().isEmpty() || phone == null || phone.trim().isEmpty() || email == null || email.trim().isEmpty()) {
            return "All fields are required!";
        }

        if (!isValidPhone(phone)) {
            return "Invalid Phone Number! It must start with '0' and contain exactly 10 digits.";
        }

        if (!isValidEmail(email)) {
            return "Invalid Email Format! Please enter a valid email.";
        }

        customerDAO.addCustomer(name, phone, email);
        return "Customer added successfully!";
    }

    public List<String[]> getCustomers() {
        return customerDAO.getAllCustomers();
    }

    public String updateCustomer(String id, String name, String phone, String email) {

        if (name == null || name.trim().isEmpty() || phone == null || phone.trim().isEmpty() || email == null || email.trim().isEmpty()) {
            return "All fields are required!";
        }

        if (!isValidPhone(phone)) {
            return "Invalid Phone Number! It must start with '0' and contain exactly 10 digits.";
        }

        if (!isValidEmail(email)) {
            return "Invalid Email Format! Please enter a valid email.";
        }

        customerDAO.updateCustomer(id, name, phone, email);
        return "Customer updated successfully!";
    }

    public void deleteCustomer(String id) {
        customerDAO.deleteCustomer(id);
    }

    private boolean isValidPhone(String phone) {
        return Pattern.matches("^0\\d{9}$", phone);
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        return Pattern.matches(emailRegex, email);
    }
}
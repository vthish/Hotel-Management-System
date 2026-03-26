package controller;

import dao.UserDAO;

public class UserController {

    private UserDAO userDAO = new UserDAO();

    public boolean login(String username, String password) {
        return userDAO.login(username, password);
    }
}
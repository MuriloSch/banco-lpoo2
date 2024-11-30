/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

/**
 *
 * @author Murilo Schrickte
 */

import controller.ClienteController;
import DAO.ClienteDAO;
import view.ClienteView;

public class Main {
    public static void main(String[] args) {
        ClienteView view = new ClienteView();
        ClienteDAO dao = new ClienteDAO();
        new ClienteController(view, dao);
        view.setVisible(true);
    }
}

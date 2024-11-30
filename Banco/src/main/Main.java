/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import controller.ClienteController;
import DAO.ClienteDAO;
import view.ClienteView;

/**
 *
 * @author Murilo Schrickte
 */

//Main temporária
public class Main {
    public static void main(String[] args) {
        ClienteView view = new ClienteView();
        ClienteDAO dao = new ClienteDAO();
        new ClienteController(view, dao);
        view.setVisible(true);
    }
}

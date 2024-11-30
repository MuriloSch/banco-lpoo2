package main;

import view.TelaInicialView;
import controller.TelaInicialController;

/**
 *
 * @author Murilo Schrickte and Pietra Minatti
 */

public class Main {
    public static void main(String[] args) {
        TelaInicialView telaInicialView = new TelaInicialView();
        TelaInicialController telaInicialController = new TelaInicialController(telaInicialView);
        telaInicialView.setVisible(true);
    }
}

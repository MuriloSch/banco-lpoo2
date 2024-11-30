package model;

import util.GeradorNumeroConta;

/**
 *
 * @author Murilo Schrickte
 */

public class ContaCorrente extends Conta {
    private double limite;

    public ContaCorrente(Cliente cliente, double saldo, double limite) {
        super(GeradorNumeroConta.gerarNumero(), cliente, saldo);
        this.limite = limite;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    @Override
    public String getTipoConta() {
        return "Conta Corrente";
    }
}
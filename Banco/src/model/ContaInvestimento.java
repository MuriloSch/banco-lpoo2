package model;

import util.GeradorNumeroConta;

/**
 *
 * @author Murilo Schrickte
 */

public class ContaInvestimento extends Conta {
    private double montanteMinimo;
    private double depositoMinimo;

    public ContaInvestimento(Cliente cliente, double saldo, double montanteMinimo, double depositoMinimo) {
        super(GeradorNumeroConta.gerarNumero(), cliente, saldo);
        this.montanteMinimo = montanteMinimo;
        this.depositoMinimo = depositoMinimo;
    }

    public double getMontanteMinimo() {
        return montanteMinimo;
    }

    public void setMontanteMinimo(double montanteMinimo) {
        this.montanteMinimo = montanteMinimo;
    }

    public double getDepositoMinimo() {
        return depositoMinimo;
    }

    public void setDepositoMinimo(double depositoMinimo) {
        this.depositoMinimo = depositoMinimo;
    }

    @Override
    public String getTipoConta() {
        return "Conta Investimento";
    }
}
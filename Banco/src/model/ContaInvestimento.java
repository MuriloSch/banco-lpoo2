package model;

/**
 *
 * @author Murilo Schrickte
 */

public class ContaInvestimento extends Conta {

    private double montanteMinimo;
    private double depositoMinimo;

    public ContaInvestimento(Cliente dono, double saldoInicial, double montanteMinimo, double depositoMinimo) {
        super(dono, saldoInicial);
        this.montanteMinimo = montanteMinimo;
        this.depositoMinimo = depositoMinimo;
    }

    @Override
    public boolean deposita(double valor) {
        if (valor < depositoMinimo) {
            System.out.println("O valor mínimo para depósito não foi atendido.");
            return false;
        }
        return super.deposita(valor); // Chama o método deposita da classe pai (Conta)
    }

    @Override
    public boolean saca(double valor) {
        if (getSaldo() - valor >= montanteMinimo) {
            return super.saca(valor); // Chama o método saca da classe pai (Conta)
        }
        System.out.println("O saldo após o saque não pode ser inferior ao montante mínimo.");
        return false;
    }

    @Override
    public void remunera() {
        double remuneracao = getSaldo() * 0.02; // 2% de remuneração
        deposita(remuneracao); // Aplica a remuneração no saldo
        System.out.println("Remuneração de 2% aplicada.");
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
}
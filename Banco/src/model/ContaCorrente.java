package model;

/**
 *
 * @author Murilo Schrickte
 */

public class ContaCorrente extends Conta {

    private double limite;

    public ContaCorrente(Cliente dono, double saldoInicial, double limite) {
        super(dono, saldoInicial);
        this.limite = limite;
    }

    @Override
    public boolean saca(double valor) {
        if (valor <= 0) {
            System.out.println("Saque inválido! O valor deve ser positivo.");
            return false;
        }
        if (getSaldo() - valor >= -limite) {
            super.saca(valor); // Chama o método saca da classe pai (Conta)
            return true;
        }
        System.out.println("Saque ultrapassa o limite da conta.");
        return false;
    }

    @Override
    public void remunera() {
        double remuneracao = getSaldo() * 0.01; // 1% de remuneração
        deposita(remuneracao); // Aplica a remuneração no saldo
        System.out.println("Remuneração de 1% aplicada.");
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }
}
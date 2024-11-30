package model;

/**
 *
 * @author Murilo Schrickte and Pietra Minatti
 */

public abstract class Conta implements ContaI {
    private int numero;
    private Cliente dono;
    private double saldo;

    public Conta(Cliente dono, double saldoInicial) {
        this.dono = dono;
        this.saldo = saldoInicial;
    }

    @Override
    public boolean deposita(double valor) {
        if (valor <= 0) {
            System.out.println("Depósito inválido! O valor deve ser positivo.");
            return false;
        }
        saldo += valor;
        return true;
    }

    @Override
    public boolean saca(double valor) {
        if (valor <= 0) {
            System.out.println("Saque inválido! O valor deve ser positivo.");
            return false;
        }
        if (saldo >= valor) {
            saldo -= valor;
            return true;
        }
        System.out.println("Saldo insuficiente.");
        return false;
    }

    @Override
    public Cliente getDono() {
        return dono;
    }

    @Override
    public int getNumero() {
        return numero;
    }

    @Override
    public double getSaldo() {
        return saldo;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public abstract void remunera(); // Este método será implementado nas subclasses
}
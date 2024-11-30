/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Comparator;

/**
 *
 * @author Murilo Schrickte
 */

public class Cliente implements Comparable<Cliente> {
    private int id;
    private String nome;
    private String sobrenome;
    private String rg;
    private String cpf;
    private String endereco;

    // Construtor, getters e setters
    public Cliente(int id, String nome, String sobrenome, String rg, String cpf, String endereco) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.rg = rg;
        this.cpf = cpf;
        this.endereco = endereco;
    }

    public Cliente(int aInt, String string, String string0, String string1, String string2, String string3, double aDouble) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // Getters e Setters
    public int getId() { 
        return id; 
    }
    public void setId(int id) {
        this.id = id; 
    }

    public String getNome() {
        return nome; 
    }
    public void setNome(String nome) {
        this.nome = nome; 
    }

    public String getSobrenome() {
        return sobrenome; 
    }
    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome; 
    }

    public String getRg() {
        return rg; 
    }
    public void setRg(String rg) {
        this.rg = rg; 
    }

    public String getCpf() {
        return cpf; 
    }
    public void setCpf(String cpf) {
        this.cpf = cpf; 
    }

    public String getEndereco() {
        return endereco; 
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco; 
    }

       @Override
    public int compareTo(Cliente outro) {
        return this.nome.compareToIgnoreCase(outro.nome);
    }

    // Comparadores estáticos para ordenação
    public static Comparator<Cliente> compararPorNome() {
        return Comparator.comparing(Cliente::getNome, String.CASE_INSENSITIVE_ORDER);
    }

    public static Comparator<Cliente> compararPorSobrenome() {
        return Comparator.comparing(Cliente::getSobrenome, String.CASE_INSENSITIVE_ORDER);
    }

    public static Comparator<Cliente> compararPorRG() {
        return Comparator.comparing(Cliente::getRg, String.CASE_INSENSITIVE_ORDER);
    }

    public static Comparator<Cliente> compararPorCPF() {
        return Comparator.comparing(Cliente::getCpf, String.CASE_INSENSITIVE_ORDER);
    }

    // Comparador Global - Para qualquer campo
    public static Comparator<Cliente> compararPorCampo(String campo) {
        switch (campo) {
            case "Nome" -> {
                return compararPorNome();
            }
            case "Sobrenome" -> {
                return compararPorSobrenome();
            }
            case "RG" -> {
                return compararPorRG();
            }
            case "CPF" -> {
                return compararPorCPF();
            }
            default -> throw new IllegalArgumentException("Campo de ordenação inválido");
        }
    }
}

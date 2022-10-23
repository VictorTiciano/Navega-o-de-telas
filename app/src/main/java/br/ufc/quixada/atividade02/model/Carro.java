package br.ufc.quixada.atividade02.model;

public class Carro {

    static int ids = 0;

    private int id;
    private String modelo;
    private String fabricante;
    private String  cor;

    public Carro(){}

    public Carro(String modelo, String fabricante, String cor) {

        this.id = ids++;

        this.modelo = modelo;
        this.fabricante = fabricante;
        this.cor = cor;
    }

    public int getId() {
        return id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    @Override
    public String toString()  {
        return "Carro: " + modelo + " - " + fabricante + " - " + cor;
    }
}

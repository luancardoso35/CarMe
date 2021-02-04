package model;

import java.util.Base64;

public class Carro {
    private String marca;
    private String modelo;
    private String anoFabricacao;
    private String cor;
    private String quilometragem;
    private String combustivel;
    private String cambio;
    private int assentos;
    private String imageName;
    private String preco;
    private String cidade;


    public Carro(String marca, String modelo, String anoFabricacao, String cor,
                 String cambio, int assentos, String quilometragem, String combustivel, String imageName,
                 String preco, String cidade) {
        this.marca = marca;
        this.modelo = modelo;
        this.anoFabricacao = anoFabricacao;
        this.cor = cor;
        this.quilometragem = quilometragem;
        this.combustivel = combustivel;
        this.cambio = cambio;
        this.assentos = assentos;
        this.imageName = imageName;
        this.preco = preco;
        this.cidade = cidade;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getAnoFabricacao() {
        return anoFabricacao;
    }

    public String getCor() {
        return cor;
    }

    public String getQuilometragem() {
        return quilometragem;
    }

    public String getCombustivel() {
        return combustivel;
    }

    public String getCambio() {
        return cambio;
    }

    public int getAssentos() {
        return assentos;
    }

    public String getImageName() {
        return imageName;
    }

    public String getPreco() {
        return preco;
    }

    public String getCidade() { return cidade; }
}

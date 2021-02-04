package model;

public class Usuario {

    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private String senha;
    private String imageName;

    public Usuario(String nome, String email, String telefone, String senha, String imageName, String cpf) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
        this.imageName = imageName;
        this.cpf = cpf;
    }

    public Usuario(String nome, String email, String telefone,  String imageName, String cpf) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.imageName = imageName;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public String getImageName() { return imageName; }

    public String getCPF() { return cpf; }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}

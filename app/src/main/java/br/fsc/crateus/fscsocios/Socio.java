package br.fsc.crateus.fscsocios;

import java.util.Date;

public class Socio {

    private String idSocio;
    private String nome;
    private String CPF;
    private String RG;
    private String endereco;
    private String nascimento;
    private String urlFoto3x4;
    private String idNucleo;

    public Socio(){

    }

    public Socio(String idSocio, String nome, String CPF, String RG, String endereco, String nascimento, String urlFoto3x4, String idNucleo) {
        this.idSocio = idSocio;
        this.nome = nome;
        this.CPF = CPF;
        this.RG = RG;
        this.endereco = endereco;
        this.nascimento = nascimento;
        this.urlFoto3x4 = urlFoto3x4;
        this.idNucleo = idNucleo;
    }

    @Override
    public String toString() {
        return "Socio{" +
                "idSocio='" + idSocio + '\'' +
                ", nome='" + nome + '\'' +
                ", CPF='" + CPF + '\'' +
                ", RG='" + RG + '\'' +
                ", endereco='" + endereco + '\'' +
                ", nascimento=" + nascimento +
                ", urlFoto3x4='" + urlFoto3x4 + '\'' +
                '}';
    }

    public String getIdNucleo() {
        return idNucleo;
    }

    public void setIdNucleo(String idNucleo) {
        this.idNucleo = idNucleo;
    }

    public String getIdSocio() {
        return idSocio;
    }

    public void setIdSocio(String idSocio) {
        this.idSocio = idSocio;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getRG() {
        return RG;
    }

    public void setRG(String RG) {
        this.RG = RG;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public String getUrlFoto3x4() {
        return urlFoto3x4;
    }

    public void setUrlFoto3x4(String urlFoto3x4) {
        this.urlFoto3x4 = urlFoto3x4;
    }
}

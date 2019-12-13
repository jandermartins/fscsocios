package br.fsc.crateus.fscsocios;

import java.util.List;

public class Nucleo {

    private String nId;
    private String nomeNucleo;
    private List<String> idSocio;
    private String coordenador, coordenador2, tesoureiro, suplente;
    private String ata;

    public Nucleo(){

    }

    public Nucleo(String nId, String nomeNucleo, List<String> idSocio, String coordenador, String coordenador2, String tesoureiro, String suplente, String ata) {
        this.nId = nId;
        this.nomeNucleo = nomeNucleo;
        this.idSocio = idSocio;
        this.coordenador = coordenador;
        this.coordenador2 = coordenador2;
        this.tesoureiro = tesoureiro;
        this.suplente = suplente;
        this.ata = ata;
    }

    @Override
    public String toString() {
        return "Nucleo{" +
                "nId='" + nId + '\'' +
                ", nomeNucleo='" + nomeNucleo + '\'' +
                ", idSocio=" + idSocio +
                ", coordenador='" + coordenador + '\'' +
                ", coordenador2='" + coordenador2 + '\'' +
                ", tesoureiro='" + tesoureiro + '\'' +
                ", suplente='" + suplente + '\'' +
                ", ata='" + ata + '\'' +
                '}';
    }

    public String getnId() {
        return nId;
    }

    public void setnId(String nId) {
        this.nId = nId;
    }

    public String getNomeNucleo() {
        return nomeNucleo;
    }

    public void setNomeNucleo(String nomeNucleo) {
        this.nomeNucleo = nomeNucleo;
    }

    public List<String> getIdSocio() {
        return idSocio;
    }

    public void setIdSocio(List<String> idSocio) {
        this.idSocio = idSocio;
    }

    public String getCoordenador() {
        return coordenador;
    }

    public void setCoordenador(String coordenador) {
        this.coordenador = coordenador;
    }

    public String getCoordenador2() {
        return coordenador2;
    }

    public void setCoordenador2(String coordenador2) {
        this.coordenador2 = coordenador2;
    }

    public String getTesoureiro() {
        return tesoureiro;
    }

    public void setTesoureiro(String tesoureiro) {
        this.tesoureiro = tesoureiro;
    }

    public String getSuplente() {
        return suplente;
    }

    public void setSuplente(String suplente) {
        this.suplente = suplente;
    }

    public String getAta() {
        return ata;
    }

    public void setAta(String ata) {
        this.ata = ata;
    }
}

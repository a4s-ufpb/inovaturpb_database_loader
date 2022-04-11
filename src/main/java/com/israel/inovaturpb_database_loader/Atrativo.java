package com.israel.inovaturpb_database_loader;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "atrativos")
public class Atrativo {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "validador")
    private String validador;

    @Column(name = "nome_Do_Atrativo")
    private String nomeDoAtrativo;

    @Column(name = "endereco_De_Email")
    private String enderecoDeEmail;

    @Column(name = "tipo_Atrativo")
    private String tipo;

    @Column(name = "data_Hora_Cadastro")
    private Date dataHoraCadastro;

    public Atrativo() {
    }

    public Atrativo(String validador, Date dataHoraCadastro, String enderecoDeEmail, String nomeDoAtrativo, String tipo) {
        this.validador = validador;
        this.dataHoraCadastro = dataHoraCadastro;
        this.enderecoDeEmail = enderecoDeEmail;
        this.nomeDoAtrativo = nomeDoAtrativo;
        this.tipo = tipo;

    }

    public String getValidador() {
        return validador;
    }

    public void setValidador(String validador) {
        this.validador = validador;
    }

    public String getNomeDoAtrativo() {
        return nomeDoAtrativo;
    }

    public void setNomeDoAtrativo(String nomeDoAtrativo) {
        this.nomeDoAtrativo = nomeDoAtrativo;
    }

    public String getEnderecoDeEmail() {
        return enderecoDeEmail;
    }

    public void setEnderecoDeEmail(String enderecoDeEmail) {
        this.enderecoDeEmail = enderecoDeEmail;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getDataHoraCadastro() {
        return dataHoraCadastro;
    }

    public void setDataHoraCadastro(Date dataHoraCadastro) {
        this.dataHoraCadastro = dataHoraCadastro;
    }
}

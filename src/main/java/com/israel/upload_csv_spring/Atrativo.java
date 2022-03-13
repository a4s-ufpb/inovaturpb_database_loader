package com.israel.upload_csv_spring;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "atrativos")
public class Atrativo {

	  @Id
	  @Column(name = "id")
	  private long id;

	  @Column(name = "nome_atrativo")
	  private String nome;

	  @Column(name = "email")
	  private String email;

	  @Column(name = "data_hora")
	  private String dataHora;

	  public Atrativo() {

	  }

	public Atrativo(long id, String nome, String email, String dataHora) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.dataHora = dataHora;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDataHora() {
		return dataHora;
	}

	public void setDataHora(String dataHora) {
		this.dataHora = dataHora;
	}
}

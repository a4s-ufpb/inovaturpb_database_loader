package com.israel.upload_csv_spring;

//Classe responsável por definir o formato da mensagem de resposta
//para que sempre que qualquer API ou endpoint receber uma chamada,
//ela retorne a resposta no formato adequado.
public class ResponseMessage {

	private String message;
	private String fileDownloadUri;
	

	  public ResponseMessage(String message, String fileDownloadUri) {
	    this.message = message;
	    this.fileDownloadUri = fileDownloadUri;
	  }

	  public String getMessage() {
	    return message;
	  }

	  public void setMessage(String message) {
	    this.message = message;
	  }

	public String getFileDownloadUri() {
		return fileDownloadUri;
	}

	public void setFileDownloadUri(String fileDownloadUri) {
		this.fileDownloadUri = fileDownloadUri;
	}

}

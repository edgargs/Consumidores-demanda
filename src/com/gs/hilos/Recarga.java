package com.gs.hilos;

public class Recarga {

	public Recarga(String local, String numero) {
		this.local = local;
		this.numero = numero;
	}
	private String local;
	private String numero;
	
	public String toString(){
		return local+"="+numero;
	}
	
	public String getLocal(){
		return this.local;
	}
}

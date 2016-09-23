package com.gs.hilos;

import java.util.Random;

public class Demonio extends Thread{

	
	public void run(){
		
		Monitor monitor = new Monitor();
		monitor.start();
		int acum = 0;
		while(true){
			
			Random rn = new Random();
			int answer = rn.nextInt(5);
			int cant = rn.nextInt(2);
			for(int i=0;i<=cant;i++){
				String valor = (100+ ++acum)+"";
				Monitor.map.add(new Recarga(answer+"",valor));
			}
			try {
				System.out.println("Demonio-espera:"+answer);
				Thread.sleep(answer*1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args){
		Demonio demon = new Demonio();
		demon.start();
	}
}

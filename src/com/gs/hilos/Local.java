package com.gs.hilos;

import java.util.Iterator;

public class Local extends Thread{

	private String local;
	
	public Local(String local) {
		this.local = local;
	}

	@Override
	public void run() {
		boolean detener = false;
		while(!detener){
			//1. Busca peticiones por local
			//int i;
			Recarga recarga = null;
			//for(i=0;i<Monitor.map.size();i++){
			synchronized (Monitor.map) {
			Iterator<Recarga> irecarga = Monitor.map.iterator();
			//try{
				while(irecarga.hasNext()){				
					Recarga pet = irecarga.next();
					if(pet.getLocal().equals(this.local)){
						recarga = pet;
					}				
				}
			//}catch(ConcurrentModificationException e){
			//	System.out.println("Local-"+this.getName()+":"+"Prevenir interrupcion");
			}
			//2. Procesa peticion pendiente
			if(recarga != null){
				if(Monitor.map.remove(recarga)){
					System.out.println("Local-"+this.local+":"+recarga);
					try {
						Thread.sleep(2*1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}else{
			//3. Sino, termina el proceso
				System.out.println("Local-"+this.local+":"+"final");
				detener = true;
			}
		}
	}

}

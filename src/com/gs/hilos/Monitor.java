package com.gs.hilos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class Monitor extends Thread{

	//public static ArrayList<Recarga> map = new ArrayList<>();
	//Queue<Recarga> globalQueue = new ConcurrentLinkedQueue<>();
	static List<Recarga> map = Collections.synchronizedList(new ArrayList<Recarga>());
	ConcurrentHashMap<String,Local> locales = new ConcurrentHashMap<>();
	
	public void run() {
		while(true){
			//1. Busca locales
			synchronized (map) {
	           Iterator<Recarga> i = map.iterator(); // Must be in synchronized block
	           while (i.hasNext()){
	        	   Recarga pet = i.next();
	        	   String local = pet.getLocal();
					//1.1 
					if(!locales.containsKey(local)){
						Local hilo = new Local(local);
						hilo.setName(local);
						locales.put(local, hilo);
						hilo.start();
					}
	           }
	       }
			//2. Verifica
			System.out.println("Monitor-Recargas:"+map);
			
			Iterator<Local> ilocal = locales.values().iterator();
			while(ilocal.hasNext()){
				Local hilo = ilocal.next();
				System.out.println(hilo.getName() +"-"+ hilo.getState());
				if(hilo.getState() == Thread.State.TERMINATED){
					locales.remove(hilo.getName());
					System.out.println("Monitor-Locales: Se elimina>"+hilo.getName());
				}
			}
			System.out.println("Monitor-Locales:"+locales);
			try {
				Thread.sleep(2*1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}

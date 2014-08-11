package br.ufpb.dce.aps.coffeemachine.impl;

import java.util.ArrayList;

import br.ufpb.dce.aps.coffeemachine.CashBox;
import br.ufpb.dce.aps.coffeemachine.Coin;
import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Display;

public class GerenteCoin {
	
	public ComponentsFactory factory;
	private final int drink = 35;
	public Coin[] coins;
	public CashBox cashBox;
	public Display display;
	
	
	public GerenteCoin(ComponentsFactory factory) {
		this.cashBox = factory.getCashBox();
		this.factory = factory;
	}
	
	public GerenteCoin() {
	 this.coins = new Coin[50];	
	}

	
	public int calcTroco(){
		int contCoins = 0;
		for(Coin c : Coin.reverse()){
			for(Coin aux : coins){
				if(aux == c){
					contCoins = contCoins + aux.getValue();
				}
			}
		}
		return contCoins - drink;
	}
	
	public void clear(){
		for (int c = 0; c < this.coins.length; c++) {
			coins[c] = null;
		}
	}
	
	public void devolverMoeda(){
		Coin[] c = Coin.reverse();
		for (int i = 0; i < c.length; i++) {
			for (int x = 0; x < this.coins.length; x++) {
				if (c[i].equals(this.coins[x])) {
					this.cashBox.release(this.coins[x]);
					this.coins[x] = null;
				}
			}
		}
		clear();
		this.factory.getDisplay().info("Insert coins and select a drink!");
	}
	
	public ArrayList <Coin> liberarMoeda(int valor){
		ArrayList<Coin> c = new ArrayList<Coin>();
		for (int i = 0; i < Coin.reverse().length; i++){
			while (Coin.reverse()[i].getValue() <= valor){
				cashBox.release(Coin.reverse()[i]);
				valor = valor - Coin.reverse()[i].getValue();
			}
		}
		return c;
	}
	
	public ArrayList <Coin> moedaReversa(int valor){
		ArrayList<Coin> listAuxiliar = new ArrayList<Coin>();
		for (int c = 0; c < Coin.reverse().length; c++){
			while (Coin.reverse()[c].getValue() <= valor){
				cashBox.count(Coin.reverse()[c]);
				listAuxiliar.add(Coin.reverse()[c]);
				valor = valor - Coin.reverse()[c].getValue();
			}
		}
		return listAuxiliar;
	}
	
}

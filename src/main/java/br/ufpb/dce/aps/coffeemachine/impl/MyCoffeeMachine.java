package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.CoffeeMachine;
import br.ufpb.dce.aps.coffeemachine.Coin;
import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.MockComponentsFactory;

public class MyCoffeeMachine implements CoffeeMachine {

	private ComponentsFactory factory;


	public void init() {
		factory = new MockComponentsFactory();
		
	}
	
	public MyCoffeeMachine(ComponentsFactory factory) {
		this.factory = factory;
		this.factory.getDisplay().info("Insert coins and select a drink!");
	}

	public void insertCoin(Coin coin) {
		int valorTotal = coin.getValue();
		int dolar = valorTotal /100;
	        int decCent = valorTotal % 100;
		this.factory.getDisplay().info ("Total: US$ "+dolar+"." + decCent);
	}
}

	


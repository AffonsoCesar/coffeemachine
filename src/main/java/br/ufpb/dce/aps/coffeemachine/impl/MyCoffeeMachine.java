package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.CoffeeMachine;
import br.ufpb.dce.aps.coffeemachine.Coin;
import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.MockComponentsFactory;

public class MyCoffeeMachine implements CoffeeMachine {

	private ComponentsFactory factory;
    int dolar =0;
    int decCent=0;

	public void init() {
		factory = new MockComponentsFactory();
		
	}
	
	public MyCoffeeMachine(ComponentsFactory factory) {
		this.factory = factory;
		this.factory.getDisplay().info("Insert coins and select a drink!");
	}

	public void insertCoin(Coin coin) {
		dolar = dolar + coin.getValue() / 100;
		decCent = decCent + coin.getValue() % 100;;
		factory.getDisplay().info ("Total: US$ "+dolar+"." + decCent);
	}
	
}


	


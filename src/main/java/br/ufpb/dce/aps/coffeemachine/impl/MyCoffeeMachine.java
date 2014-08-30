package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.CoffeeMachine;
import br.ufpb.dce.aps.coffeemachine.CoffeeMachineException;
import br.ufpb.dce.aps.coffeemachine.Drink;
import br.ufpb.dce.aps.coffeemachine.Coin;

public class MyCoffeeMachine implements CoffeeMachine {

	private ComponentsFactory factory;
	private GerenteCoin gerenciadorC;
	private GerenteMachine gerenciadorM;


	public void insertCoin(Coin coin) {
		gerenciadorC.insertCoin(factory, coin);
	}

	public void cancel() throws CoffeeMachineException {
		gerenciadorC.cancel(factory);
	}
		
	public void select(Drink drink) {
		gerenciadorM.inicioPedido(factory, gerenciadorC, drink);
	}
	
	public void setFactory(ComponentsFactory factory) {
		this.factory = factory;
		gerenciadorC = new GerenteCoin();
		gerenciadorM = new GerenteMachine();
		factory.getDisplay().info("Insert coins or place your badge in the reader. And select a drink!");
	}
		
	public void readBadge(int badgeCode) {
		factory.getDisplay().info("Badge read.");
	}
}
package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.CoffeeMachine;
import br.ufpb.dce.aps.coffeemachine.CoffeeMachineException;
import br.ufpb.dce.aps.coffeemachine.Drink;
import br.ufpb.dce.aps.coffeemachine.Coin;

public class MyCoffeeMachine implements CoffeeMachine {

	private ComponentsFactory factory;
	private GerenteCoin gerenciadorC =  new GerenteCoin(); 
	private GerenteMachine gerenciadorM = new GerenteMachine();


	public void insertCoin(Coin coin) {
		gerenciadorC.insertCoin(factory, coin, gerenciadorM.getModo());
	}

	public void cancel() throws CoffeeMachineException {
		gerenciadorC.cancel(factory);
	}
		
	public void select(Drink drink) {
		gerenciadorM.inicioPedido(factory, gerenciadorC, drink);
	}
	
	public void setFactory(ComponentsFactory factory) {
		this.factory = factory;
		gerenciadorM.iniciarComMoedas(factory);
	}
		
	public void readBadge(int badgeCode) {
		gerenciadorM.iniciarComCracha(factory, gerenciadorC, badgeCode);
	}
}
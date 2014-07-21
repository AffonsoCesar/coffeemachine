package br.ufpb.dce.aps.coffeemachine.impl;

import static org.mockito.Matchers.anyDouble;

import org.mockito.InOrder;

import br.ufpb.dce.aps.coffeemachine.CoffeeMachine;
import br.ufpb.dce.aps.coffeemachine.CoffeeMachineException;
import br.ufpb.dce.aps.coffeemachine.Coin;
import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Drink;
import br.ufpb.dce.aps.coffeemachine.Messages;
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

	public void insertCoin(Coin coin) throws CoffeeMachineException {
		
		try {
			dolar = dolar + coin.getValue() / 100;
			decCent = decCent + coin.getValue() % 100;
			factory.getDisplay().info ("Total: US$ "+dolar+"." + decCent);
		}
		
		catch (NullPointerException e) {
			throw new CoffeeMachineException("A moeda inserida não é válida para esta máquina!");
		}
	}
	
	
	public void cancel() {
		
			if (dolar == 0 && decCent == 0) {
			throw new CoffeeMachineException("Nenhuma Moeda Inserida na Máquina!");
		}
			else  {	
			factory.getDisplay().warn("Cancelling drink. Please, get your coins.");
			factory.getCashBox().release(Coin.halfDollar);
			factory.getCashBox().release(Coin.nickel);
			factory.getCashBox().release(Coin.penny);
			factory.getCashBox().release(Coin.quarter);
			factory.getCashBox().release(Coin.quarter);
			factory.getDisplay().info("Insert coins and select a drink!");
			}
	}

	public void select(Drink drink) {
		
		this.factory.getCupDispenser().contains(1);
		this.factory.getWaterDispenser().contains(anyDouble());
		this.factory.getCoffeePowderDispenser().contains(anyDouble());
		this.factory.getDisplay().info("Mixing ingredients.");
		this.factory.getCoffeePowderDispenser().release(anyDouble());
		this.factory.getWaterDispenser().release(anyDouble());
		this.factory.getDisplay().info("Releasing drink.");
		this.factory.getCupDispenser().release(1);
		this.factory.getDrinkDispenser().release(anyDouble());
		this.factory.getDisplay().info("Please, take your drink.");
		this.factory.getDisplay().info("Insert coins and select a drink!");
	}

}
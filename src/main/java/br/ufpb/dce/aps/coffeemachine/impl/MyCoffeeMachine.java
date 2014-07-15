package br.ufpb.dce.aps.coffeemachine.impl;

import static org.mockito.Mockito.verify;

import org.junit.Before;

import br.ufpb.dce.aps.coffeemachine.CoffeeMachine;
import br.ufpb.dce.aps.coffeemachine.Coin;
import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Display;
import br.ufpb.dce.aps.coffeemachine.MockComponentsFactory;

public class MyCoffeeMachine implements CoffeeMachine {

	private ComponentsFactory factory;
	private Display display;

	@Before
	public void init() {
		factory = new MockComponentsFactory();
		display = factory.getDisplay();
	}
	
	public MyCoffeeMachine(ComponentsFactory factory) {
		factory.getDisplay().info("Insert coins and select a drink!");
	}

	public void insertCoin(Coin dime) {
		factory.getDisplay().info("Total: US$ 0.10");
	}
	
}

	


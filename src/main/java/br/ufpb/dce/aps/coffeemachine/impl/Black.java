package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Drink;

public class Black extends Drinks {
	
	
	public Black (Drink drink, ComponentsFactory factory) {
		this.factory = factory;
		if (drink == Drink.BLACK) {
			this.drink = Drink.BLACK;
		} else {
			this.drink = Drink.BLACK_SUGAR;
		}
	}

	public void release(ComponentsFactory factory) {
		factory.getWaterDispenser().release(100);
		if (drink == Drink.BLACK_SUGAR) {
			factory.getSugarDispenser().release(5);
		}
		factory.getDisplay().info("Releasing drink.");
		factory.getCupDispenser().release(1);
	}
}

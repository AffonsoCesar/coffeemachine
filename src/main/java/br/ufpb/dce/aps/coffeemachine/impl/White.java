package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Drink;

public class White extends Drinks {
	
	
	public White (Drink drink, ComponentsFactory factory) {
		this.factory = factory;
		if (drink == Drink.WHITE) {
			this.drink = Drink.WHITE;
		} else {
			this.drink = Drink.WHITE_SUGAR;
		}
	}

	public void release() {
		factory.getWaterDispenser().release(80);
		factory.getCreamerDispenser().release(20);
		if (drink == Drink.WHITE_SUGAR) {
			factory.getSugarDispenser().release(5);
		}
	}
}
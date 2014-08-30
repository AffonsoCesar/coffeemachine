package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Drink;

public class Bouillon extends Drinks {

	public Bouillon (Drink drink) {
		this.drink = Drink.BOUILLON;
	}

	@Override
	public void release(ComponentsFactory factory) {
		factory.getWaterDispenser().release(100);
		factory.getDisplay().info("Releasing drink.");
		factory.getCupDispenser().release(1);
	}
}

package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.Drink;
import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;

public class GerenteMachine {
	
	private GerenteDrink gerenteDrink = new GerenteDrink();
	
	public void inicioPedido(ComponentsFactory factory, GerenteCoin gerenteCoin, Drink drink) {
		
			gerenteDrink.iniciarDrink(drink,factory);
			
			if(!gerenteCoin.checkCoin(factory, this.gerenteDrink.getValor())){
				return;
			}

			if (!gerenteDrink.checkIngredients(drink, factory)) {
				gerenteCoin.returnCoin(factory, false);
				return;
			}
			
			if (!gerenteDrink.verifySugar(factory)) {
				gerenteCoin.returnCoin(factory, false);
				return;
			}
			
			if(!gerenteCoin.verifyChange(factory, gerenteDrink.getValor())){
				return;
			}

			gerenteDrink.mixIngredients(drink, factory);
			gerenteDrink.release(factory);
			

			if (gerenteCoin.getTotal() >= gerenteDrink.getValor()) {
				gerenteCoin.returnChange(factory, gerenteDrink.getValor());
				}

			factory.getDisplay().info("Insert coins or place your badge in the reader. And select a drink!");
			gerenteCoin.clear();
		}
}

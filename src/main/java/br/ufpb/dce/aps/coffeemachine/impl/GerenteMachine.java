package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.Drink;
import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;

public class GerenteMachine {
	
	private GerenteDrink gerenteDrink = new GerenteDrink();
	private static String type = "";
	
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
			GerenteMachine.setModo(type);
			gerenteCoin.clear();
		}
	public void iniciarComCracha(ComponentsFactory factory, GerenteCoin gerenteCoin, int Cracha) {
				if(gerenteCoin.getTotal()>0){
					factory.getDisplay().warn("You can not read badge after inserting coins");
					return;
				}
				else{
					factory.getDisplay().info("Badge read.");
					GerenteMachine.setModo("Cracha");
				}
			}
	
	public void iniciarComMoedas(ComponentsFactory factory) {
				factory.getDisplay().info("Insert coins or place your badge in the reader. And select a drink!");
				GerenteMachine.setModo("Moedas");
		 	}
			public static void setModo(String newType) {
				type = newType;
			}
			
			public String getModo(){
				return type;
			}
}

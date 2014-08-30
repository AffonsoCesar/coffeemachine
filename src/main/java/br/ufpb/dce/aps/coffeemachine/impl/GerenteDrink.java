package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Drink;

public class GerenteDrink {

	private final int valor = 35;
	private Drinks d;
	
	public void iniciarDrink (Drink drink, ComponentsFactory factory){
		if (drink == Drink.BLACK ||drink == Drink.BLACK_SUGAR){
			d = new Black (drink, factory);
		}
		else{
			d = new White (drink, factory);
		}
	}
	
	public boolean checkIngredients(Drink drink, ComponentsFactory factory) {
		
		if (!factory.getCupDispenser().contains(1)) {
			factory.getDisplay().warn("Out of Cup");
			return false;
		}
		if(d.getDrink() == Drink.BLACK || d.getDrink() == Drink.BLACK_SUGAR){
			if (!factory.getWaterDispenser().contains(100)) {
				factory.getDisplay().warn("Out of Water");
				return false;
			}
		}
		else{
			if(!factory.getWaterDispenser().contains(80)) {
				factory.getDisplay().warn("Out of Water");
				return false;
			}
		}
		if (!factory.getCoffeePowderDispenser().contains(15)) {
			factory.getDisplay().warn("Out of Coffee Powder");
			return false;
		}
		
		else if (d.getDrink() == Drink.WHITE || d.getDrink() == Drink.WHITE_SUGAR){
			if (!factory.getCreamerDispenser().contains(20)){
				factory.getDisplay().warn("Out of Creamer");
				return false;
			}
		}
			return true;
	}
	
	public boolean verifySugar(ComponentsFactory factory){
		if(d.getDrink() == Drink.BLACK_SUGAR || d.getDrink() == Drink.WHITE_SUGAR){
			if (!factory.getSugarDispenser().contains(5)) {
				factory.getDisplay().warn("Out of Sugar");
				return false;
			}
		}
		return true;
	}
	
	public void mixIngredients (ComponentsFactory factory){
		factory.getDisplay().info("Mixing ingredients.");
		factory.getCoffeePowderDispenser().release(15);
	}
	
	public void release(ComponentsFactory factory){
		d.release();
		factory.getDisplay().info("Releasing drink.");
		factory.getCupDispenser().release(1);
		factory.getDrinkDispenser().release(100);
		factory.getDisplay().info("Please, take your drink.");
		}
	
	public double getValor(){
		return this.valor;
	}
}

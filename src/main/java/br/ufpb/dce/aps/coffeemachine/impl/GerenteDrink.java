package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Drink;
import static org.mockito.Matchers.anyDouble;

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
	
	public boolean checkIngredients(ComponentsFactory factory) {
		
		if (!factory.getCupDispenser().contains(1)) {
			factory.getDisplay().warn("Out of Cup");
			return false;
		}
		else if (!factory.getWaterDispenser().contains(anyDouble())) {
			factory.getDisplay().warn("Out of Water");
			return false;
		}
		else if (!factory.getCoffeePowderDispenser().contains(anyDouble())) {
			factory.getDisplay().warn("Out of Coffee Powder");
			return false;
		}
		else if (d.getDrink() == Drink.WHITE || d.getDrink() == Drink.WHITE_SUGAR){
			if (!factory.getCreamerDispenser().contains(anyDouble())){
				return false;
			}
		}
			return true;
	}
	
	public boolean verifySugar(ComponentsFactory factory){
		if(d.getDrink() == Drink.BLACK_SUGAR || d.getDrink() == Drink.WHITE_SUGAR){
			if (!factory.getSugarDispenser().contains(anyDouble())) {
				factory.getDisplay().warn("Out of Sugar");
				return false;
			}
		}
		return true;
	}
	
	public void mixIngredients (ComponentsFactory factory){
		factory.getDisplay().info("Mixing ingredients.");
		factory.getCoffeePowderDispenser().release(anyDouble());
		factory.getWaterDispenser().release(anyDouble());
	}
	
	public void release(ComponentsFactory factory){
		d.release();
		factory.getDisplay().info("Releasing drink.");
		factory.getCupDispenser().release(1);
		factory.getDrinkDispenser().release(1);
		factory.getDisplay().info("Please, take your drink.");
		}
	
	public double getValor(){
		return this.valor;
	}
}

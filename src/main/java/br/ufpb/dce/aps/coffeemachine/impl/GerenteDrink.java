package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Drink;

public class GerenteDrink {

	private double valor = 35;
	private double valorCaldo = 25;
	private Drinks d;
	
	public void iniciarDrink (Drink drink, ComponentsFactory factory){
		if (drink == Drink.BLACK ||drink == Drink.BLACK_SUGAR){
			d = new Black (drink, factory);
		}
		else if (drink == Drink.WHITE ||drink == Drink.WHITE_SUGAR) {
			d = new White (drink, factory);
		}
		else {
			d = new Bouillon(drink);
			valor = valorCaldo;
		}
	}
	public boolean checkIngredients(ComponentsFactory factory, Drink drink, int cup, int water, int powder, int cream, int bouillon) {
		if (cup > 0) {
			if (!factory.getCupDispenser().contains(cup)) {
				factory.getDisplay().warn("Out of Cup");
				return false;
			}
		}
		if (!factory.getWaterDispenser().contains(water)) {
			factory.getDisplay().warn("Out of Water");
			return false;
		}
		if (powder > 0) {
			if (!factory.getCoffeePowderDispenser().contains(powder)) {
				factory.getDisplay().warn("Out of Coffee Powder");
				return false;
			}
		}
		if (d.getDrink() == Drink.WHITE || d.getDrink() == Drink.WHITE_SUGAR) {
			if (!factory.getCreamerDispenser().contains(cream)) {
				factory.getDisplay().warn("Out of Creamer");
				return false;
			}
		}
		if (bouillon > 0) {
			if (!factory.getBouillonDispenser().contains(bouillon)) {
				return false;
			}
		}
		return true;
	}
	
	public boolean checkIngredients(Drink drink, ComponentsFactory factory) {
		if (d.getDrink() == Drink.BLACK || d.getDrink() == Drink.BLACK_SUGAR) {
			return (checkIngredients(factory, drink, 1, 100, 15, 0, 0));

		} 
		else if (d.getDrink() == Drink.WHITE || d.getDrink() == Drink.WHITE_SUGAR) {
			return (checkIngredients(factory, drink, 1, 80, 15, 20, 0));
		}
		else {
				return (checkIngredients(factory, drink, 1, 100, 0, 0, 10));
			}
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
		if (d.getDrink() == Drink.BOUILLON) {
			factory.getBouillonDispenser().release(10);
		} 
		else {
			factory.getCoffeePowderDispenser().release(15);
		}
	}
	
	public void release(ComponentsFactory factory){
		d.release(factory);
		factory.getDrinkDispenser().release(100);
		factory.getDisplay().info("Please, take your drink.");
		}
	
	public double getValor(){
		return this.valor;
	}
	
}

package br.ufpb.dce.aps.coffeemachine.impl;

import java.util.ArrayList;
import br.ufpb.dce.aps.coffeemachine.CoffeeMachineException;
import br.ufpb.dce.aps.coffeemachine.Coin;
import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;

public class GerenteCoin {

	private int dolar;
	private int decCent;
	private ArrayList<Coin> aux = new ArrayList<Coin>();
	private ArrayList<Coin> change = new ArrayList<Coin>();
	private Coin[] rev = Coin.reverse();
	private int total;
	
	public int getTotal() {
		return total;
	}
	
	public void insertCoin(ComponentsFactory factory, Coin coin)throws CoffeeMachineException {
		
		try {
			total += coin.getValue();
			aux.add(coin);
			dolar = total / 100;
			decCent = total % 100;
			factory.getDisplay().info ("Total: US$ "+dolar+"." + decCent);
		} 
		catch (NullPointerException e) {
			throw new CoffeeMachineException("A moeda inserida não é válida para esta máquina!");
		}
	}

	public void cancel(ComponentsFactory factory) throws CoffeeMachineException {
		
		if (total == 0) {
			throw new CoffeeMachineException("Nenhuma Moeda Inserida na Máquina!");
		}
		returnCoin(factory, true);
	}

	public void returnCoin(ComponentsFactory factory, boolean confirm) {
		if (confirm) {
			factory.getDisplay().warn("Cancelling drink. Please, get your coins.");
		}
		
		for (Coin x : this.rev) {
			for (Coin aux : this.aux) {
				if (aux == x) {
					factory.getCashBox().release(aux);
				}
			}
		}
		total = 0;
		clear();
		factory.getDisplay().info("Insert coins and select a drink!");
	}

	public boolean calcTroco(ComponentsFactory factory, double valorDrink) {
		double contCoins = total - valorDrink;
		for (Coin coin : rev) {
			if (coin.getValue() <= contCoins && factory.getCashBox().count(coin) > 0) {
				while (coin.getValue() <= contCoins) {
					contCoins = contCoins - coin.getValue();
					change.add(coin);
				}
			}
		}
		return (contCoins == 0);
	}
	
	public void clear() {
		aux.clear();
		total = 0;
	}

	public boolean checkCoin(ComponentsFactory factory, double valorDrink) {
		if (total < valorDrink || total == 0) {
			factory.getDisplay().warn("Please, insert enought money");
			returnCoin(factory, false);
			return false;
		}
		return true;
	}

	public boolean verifyChange(ComponentsFactory factory, double valorDrink) {
			if (!calcTroco(factory, valorDrink)) {
				factory.getDisplay().warn("I do not have enought change");
				returnCoin(factory, false);
				return false;
			}
		return true;
	}
	
	public void returnChange(ComponentsFactory factory, double valorDrink) {
		for (Coin coin : rev) {
			for (Coin changeCoin : change) {
				if (changeCoin == coin) {
					factory.getCashBox().release(coin);
				}
			}
		}
	}
}

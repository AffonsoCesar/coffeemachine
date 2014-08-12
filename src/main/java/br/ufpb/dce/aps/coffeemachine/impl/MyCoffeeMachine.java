package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.CoffeeMachine;
import br.ufpb.dce.aps.coffeemachine.CoffeeMachineException;
import br.ufpb.dce.aps.coffeemachine.Drink;
import br.ufpb.dce.aps.coffeemachine.Coin;
import br.ufpb.dce.aps.coffeemachine.Dispenser;

public class MyCoffeeMachine implements CoffeeMachine {

	private ComponentsFactory factory;
	private Dispenser cupDispenser;
	private int valor =0, decCent, dolar; 
 	
 	GerenteCoin gerente = new GerenteCoin();
 	
	
 	public Dispenser getCupDispenser() {
		return cupDispenser;
	}
 	
 	public MyCoffeeMachine(ComponentsFactory factory) {
 		this.factory = factory;
 		//this.gerente.coins = new Coin[50]; //ISSO AQUI ESTÁ SEM SER USADO!
 		this.gerente.factory = factory;
		this.cupDispenser = factory.getCupDispenser(); // ATENÇÂO: o setCupDispenser foi trocado por isso
		this.gerente.cashBox = factory.getCashBox();
		this.factory.getDisplay().info("Insert coins and select a drink!");
		
	}

	public void insertCoin(Coin coin) throws CoffeeMachineException {
		
		try {
			this.gerente.coins[++valor] = coin;
			dolar = dolar + coin.getValue() / 100;
			decCent = decCent + coin.getValue() % 100;
			factory.getDisplay().info ("Total: US$ "+dolar+"." + decCent);
		}
		
		catch (NullPointerException e) {
			throw new CoffeeMachineException("A moeda inserida não é válida para esta máquina!");
		}
	}
	
	public void cancel() {
		
		if (dolar == 0 && decCent == 0) {
			throw new CoffeeMachineException("Nenhuma Moeda Inserida na Máquina!");
		}
		factory.getDisplay().warn("Cancelling drink. Please, get your coins.");
		this.gerente.devolverMoeda();
	}

	public void select(Drink drink) {
		
		if(gerente.calcTroco()< 0){
			this.factory.getDisplay().warn("Please, insert enought money");
			this.gerente.devolverMoeda();	
			return;
		}
		
		if(!this.factory.getCupDispenser().contains(1)) {
			this.factory.getDisplay().warn("Out of Cup");
			this.gerente.devolverMoeda();
			return;
		}
		if(!this.factory.getWaterDispenser().contains(0.1)) {
			this.factory.getDisplay().warn("Out of Water");
			this.gerente.devolverMoeda();
			return;
		}
		if(!this.factory.getCoffeePowderDispenser().contains(0.1)) {
			this.factory.getDisplay().warn("Out of Coffee Powder");
			this.gerente.devolverMoeda();
			return;
		}
		
		else {	
			if (drink == Drink.BLACK_SUGAR) {
				if(! factory.getSugarDispenser().contains(0.1)){;
				this.factory.getDisplay().warn("Out of Sugar");
				this.gerente.devolverMoeda();
				return;
				}
			}
			if (drink == Drink.WHITE) {
				this.factory.getCreamerDispenser().contains(0.1);
				
			}
			if (drink == Drink.WHITE_SUGAR) {
				this.factory.getCreamerDispenser().contains(0.1);
				this.factory.getSugarDispenser().contains(0.1);	
			}
			
			if(!this.gerente.semTroco(gerente.calcTroco())){
				this.factory.getDisplay().warn("I do not have enought change"); 
				this.gerente.devolverMoeda();
				return;
			}
			
			this.gerente.moedaReversa(this.gerente.calcTroco());
			
			this.factory.getDisplay().info("Mixing ingredients.");
			this.factory.getCoffeePowderDispenser().release(0.1);
			this.factory.getWaterDispenser().release(0.1);
		
			if (drink == Drink.WHITE_SUGAR) {
				this.factory.getCreamerDispenser().release(0.1);
				this.factory.getSugarDispenser().release(0.1);
			}
			if (drink == Drink.WHITE) {
				this.factory.getCreamerDispenser().release(0.1);
			}
			if (drink == Drink.BLACK_SUGAR) {
				this.factory.getSugarDispenser().release(0.1);
			}
		
			this.factory.getDisplay().info("Releasing drink.");
			this.factory.getCupDispenser().release(1);
			this.factory.getDrinkDispenser().release(0.1);
			this.factory.getDisplay().info("Please, take your drink.");
		
			this.gerente.liberarMoeda(this.gerente.calcTroco()); //Libera a moeda com o troco calculado!
			this.gerente.clear(); //Limpa a lista de moedas
			this.factory.getDisplay().info("Insert coins and select a drink!");
		}
	}
}
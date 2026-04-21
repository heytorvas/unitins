package com.mockito.model;

public class Car {
	
	private Engine engine;
	private FuelTank fuelTank;

	public Car(Engine engine, FuelTank fuelTank) {
		this.engine = engine;
		this.fuelTank = fuelTank;
	}

	public void start() {
		
		if (engine.isRunning())
			throw new IllegalStateException("Motor funcionando");
		
		if (fuelTank.getFuel() == 0)
			throw new IllegalStateException("Sem combustível");

		engine.start();

		if (!engine.isRunning())
			throw new IllegalStateException("Motor ligado mas não funcionando");
	}

	public boolean isRunning() {
		return engine.isRunning();	
	}
}

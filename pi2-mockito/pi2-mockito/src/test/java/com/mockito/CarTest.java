package com.mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import com.mockito.model.*;

public class CarTest {

	private Car car;
	private Engine engine;
	private FuelTank fuelTank;

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Before
	public void setup() {
		engine = Mockito.mock(Engine.class);
		fuelTank = Mockito.mock(FuelTank.class);

		car = new Car(engine, fuelTank);
	}

	@Test
	public void isRunning() {
		when(engine.isRunning()).thenReturn(true);
		//when(engine.isRunning()).thenReturn(false); //ERROR - BOOLEAN CHECK
		assertEquals(true, car.isRunning());

		when(engine.isRunning()).thenReturn(false);
		assertEquals(false, car.isRunning());
	}

	@Test
	public void start() {
		when(engine.isRunning()).thenReturn(false, true);
		when(fuelTank.getFuel()).thenReturn(100);
		//when(fuelTank.getFuel()).thenReturn(0); //ERROR - NO FUEL
		car.start();
	}

	@Test
	public void start_NoFuel() {
		expectedException.expect(IllegalStateException.class);
		expectedException.expectMessage("Sem combustível");
		when(engine.isRunning()).thenReturn(false);
		//when(engine.isRunning()).thenReturn(true); //ERROR - STARTED ENGINE
		when(fuelTank.getFuel()).thenReturn(0);
		car.start();
	}

}

package aserralle.serialization.benchmark.entities;

import aserralle.serialization.benchmark.Entity;
import aserralle.serialization.benchmark.EntityFactory;


public class PrimitiveArrayFactory implements EntityFactory {

	@Override
	public Entity getInstance(int size) {
		final int[] numArray = new int[size];
		for (int i = 0; i < size; i++) {
			numArray[i] = i;
		}
		return new Entity() {
			@Override
			public Object get() {
				return numArray;
			}

			@Override
			public String getIdentifier() {
				return "Primitive Array of " + size + " elements";
			}
		};
	}
}

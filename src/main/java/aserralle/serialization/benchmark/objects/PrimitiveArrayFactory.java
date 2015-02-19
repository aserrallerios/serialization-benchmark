package aserralle.serialization.benchmark.objects;

import aserralle.serialization.benchmark.ObjectToBenchmark;
import aserralle.serialization.benchmark.ObjectFactory;


public class PrimitiveArrayFactory implements ObjectFactory {

	@Override
	public ObjectToBenchmark getInstance(final int size) {
		final int[] numArray = new int[size];
		for (int i = 0; i < size; i++) {
			numArray[i] = i;
		}
		return new ObjectToBenchmark() {
			@Override
			public Object get() {
				return numArray;
			}

			@Override
			public String getIdentifier() {
				return "Primitive Array of " + size + " elements";
			}

			@Override
			public int getSize() {
				return size;
			}
		};
	}
}

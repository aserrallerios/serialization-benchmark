package aserralle.serialization.benchmark.objects;

import java.util.Set;
import java.util.TreeSet;

import aserralle.serialization.benchmark.ObjectToBenchmark;
import aserralle.serialization.benchmark.ObjectFactory;

public class TreeSetFactory implements ObjectFactory {

	@Override
	public ObjectToBenchmark getInstance(final int size) {
		Set<Integer> numSet = new TreeSet<>();
		for (int i = 0; i < size; i++) {
			numSet.add(i);
		}
		return new ObjectToBenchmark() {
			@Override
			public Object get() {
				return numSet;
			}

			@Override
			public String getIdentifier() {
				return "Tree Set of " + size + " elements";
			}

			@Override
			public int getSize() {
				return size;
			}
		};
	}

}

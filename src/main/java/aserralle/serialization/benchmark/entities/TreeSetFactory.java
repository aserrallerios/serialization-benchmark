package aserralle.serialization.benchmark.entities;

import java.util.Set;
import java.util.TreeSet;

import aserralle.serialization.benchmark.Entity;
import aserralle.serialization.benchmark.EntityFactory;

public class TreeSetFactory implements EntityFactory {

	@Override
	public Entity getInstance(int size) {
		Set<Integer> numSet = new TreeSet<>();
		for (int i = 0; i < size; i++) {
			numSet.add(i);
		}
		return new Entity() {
			@Override
			public Object get() {
				return numSet;
			}

			@Override
			public String getIdentifier() {
				return "Tree Set of " + size + " elements";
			}
		};
	}

}

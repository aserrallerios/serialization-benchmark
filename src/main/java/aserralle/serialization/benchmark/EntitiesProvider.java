package aserralle.serialization.benchmark;

import java.util.Arrays;
import java.util.Iterator;

import aserralle.serialization.benchmark.entities.PrimitiveArrayFactory;
import aserralle.serialization.benchmark.entities.TreeSetFactory;

public class EntitiesProvider implements Iterable<Entity> {

	final static Iterable<EntityFactory> serializers = Arrays.asList(new EntityFactory[] {
			new PrimitiveArrayFactory(), new TreeSetFactory() });

	final private int entitySize;

	public EntitiesProvider(int entitySize) {
		this.entitySize = entitySize;
	}

	@Override
	public Iterator<Entity> iterator() {
		final Iterator<EntityFactory> factories = serializers.iterator();
		return new Iterator<Entity>() {
			@Override
			public boolean hasNext() {
				return factories.hasNext();
			}

			@Override
			public Entity next() {
				return factories.next().getInstance(entitySize);
			}
		};
	}
}

package aserralle.serialization.benchmark;

import java.util.Arrays;
import java.util.Iterator;

import aserralle.serialization.benchmark.objects.HashSetFactory;
import aserralle.serialization.benchmark.objects.PrimitiveArrayFactory;
import aserralle.serialization.benchmark.objects.TreeSetFactory;

public class ObjectsProvider implements Iterable<ObjectToBenchmark> {

	final static Iterable<ObjectFactory> serializers = Arrays.asList(new ObjectFactory[] {
			new PrimitiveArrayFactory(), //
			new TreeSetFactory(), //
			new HashSetFactory() //
			});

	final private int objectSize;

	public ObjectsProvider(int size) {
		objectSize = size;
	}

	@Override
	public Iterator<ObjectToBenchmark> iterator() {
		final Iterator<ObjectFactory> factories = serializers.iterator();
		return new Iterator<ObjectToBenchmark>() {
			@Override
			public boolean hasNext() {
				return factories.hasNext();
			}

			@Override
			public ObjectToBenchmark next() {
				return factories.next().getInstance(objectSize);
			}
		};
	}
}

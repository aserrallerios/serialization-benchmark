package aserralle.serialization.benchmark;

import java.util.Arrays;
import java.util.Iterator;

import aserralle.serialization.benchmark.serializers.DefaultSerializer;

public class SerializersProvider implements Iterable<Serializer> {

	final static Iterable<Serializer> serializers = Arrays.asList(new Serializer[] { //
			new DefaultSerializer() //
			});

	@Override
	public Iterator<Serializer> iterator() {
		return serializers.iterator();
	}

}

package aserralle.serialization.benchmark;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Benchmarker {

	private static final Logger LOG = LoggerFactory.getLogger(Benchmarker.class);

	public void startBenchmark(int size, int times) {
		SerializersProvider serializers = new SerializersProvider();
		ObjectsProvider objects = new ObjectsProvider(size);

		serializers.forEach(serializer -> {
			LOG.info("Benchmark for Serializer: \"" + serializer.getIdentifier() + "\"");
			objects.forEach(object -> {
				LOG.info("\tObject: \"" + object.getIdentifier() + "\"");

				LOG.info("\t\tSerialize " + times + " times");
				long time =
						benchmarkInMillis(() -> repeatNTimes(times,
								() -> serializer.serialize(object.get())));
				LOG.info("\t\t\tTook " + time + " milliseconds");

				LOG.info("\t\tDeserialize " + times + " times");
				byte[] byteArray = serializer.serializeToByteArray(object.get());
				time =
						benchmarkInMillis(() -> repeatNTimes(times,
								() -> serializer.deserialize(byteArray)));
				LOG.info("\t\t\tTook " + time + " milliseconds");

				serializer.serializeToByteArray(object.get());
				LOG.info("\t\tSerialized size is " + byteArray.length + " bytes");
			});
		});
	}

	private long benchmarkInMillis(Method method) {
		long startTime = System.nanoTime();
		method.apply();
		long endTime = System.nanoTime();
		return (endTime - startTime) / 1000000;
	}

	private void repeatNTimes(int times, Method method) {
		for (int i = 0; i < times; i++) {
			method.apply();
		}
	}

	@FunctionalInterface
	interface Method {
		void apply();
	}

	public static void main(String[] args) {
		new Benchmarker().startBenchmark(10000, 1000);
	}
}

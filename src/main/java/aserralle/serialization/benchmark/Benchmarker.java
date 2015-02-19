package aserralle.serialization.benchmark;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Benchmarker {

	private static final Logger LOG = LoggerFactory.getLogger(Benchmarker.class);

	public void startBenchmark(int entitySize, int times) {
		SerializersProvider serializers = new SerializersProvider();
		EntitiesProvider entities = new EntitiesProvider(entitySize);

		serializers.forEach(serializer -> {
			LOG.info(" \t Benchmark for Serializer: " + serializer.getIdentifier());
			entities.forEach(entity -> {
				LOG.info(" \t\t Entity: " + entity.getIdentifier() + "; Times: " + times);

				LOG.info(" \t\t\t Serialize:");
				benchmark(() -> repeatNTimes(times, () -> serializer.serialize(entity.get())));

				LOG.info(" \t\t\t Deserialize:");
				byte[] byteArray = serializer.objectToByteArray(entity.get());
				benchmark(() -> repeatNTimes(times, () -> serializer.deserialize(byteArray)));
				serializer.objectToByteArray(entity.get());

				LOG.info(" \t\t\t SerializedSizeInBytes: " + byteArray.length);
			});
		});
	}

	private void benchmark(Method method) {
		long startTime = System.nanoTime();
		method.apply();
		long endTime = System.nanoTime();
		LOG.info(" \t\t\t\t MilisTotalTime: " + (endTime - startTime) / 1000000);
	}

	private void repeatNTimes(int times, Method method) {
		for (int i = 0; i < times; i++) {
			method.apply();
		}
	}

	public static void main(String[] args) {
		new Benchmarker().startBenchmark(1000, 1000);
	}

	@FunctionalInterface
	interface Method {
		void apply();
	}
}

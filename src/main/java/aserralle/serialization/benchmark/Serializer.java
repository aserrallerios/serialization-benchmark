package aserralle.serialization.benchmark;


public interface Serializer extends IdentifiedObject {

	void deserialize(byte[] array);

	void serialize(Object obj);

	byte[] serializeToByteArray(Object obj);
}

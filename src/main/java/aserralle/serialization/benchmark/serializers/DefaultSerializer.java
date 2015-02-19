package aserralle.serialization.benchmark.serializers;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import aserralle.serialization.benchmark.Serializer;

public class DefaultSerializer implements Serializer {

	@Override
	public void deserialize(byte[] array) {
		try {
			ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(array));
			in.readObject();
			in.close();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public void serialize(Object obj) {
		try {
			ObjectOutputStream out = new ObjectOutputStream(new OutputStream() {
				@Override
				public void write(int b) throws IOException {}
			});
			out.writeObject(obj);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public byte[] objectToByteArray(Object obj) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(obj);
			oos.flush();
			oos.close();
			return baos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public String getIdentifier() {
		return "Default JDK serializer";
	}

}

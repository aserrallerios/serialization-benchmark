package aserralle.serialization.benchmark.serializers;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import aserralle.serialization.benchmark.Serializer;

public class DefaultSerializer implements Serializer {

	private OutputStream nullOutputStream = new OutputStream() {
		@Override
		public void write(int b) throws IOException {}
	};

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
			serialize(obj, nullOutputStream);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public byte[] serializeToByteArray(Object obj) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			serialize(obj, baos);
			return baos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	private void serialize(Object obj, OutputStream outputStream) throws IOException {
		ObjectOutputStream out = new ObjectOutputStream(outputStream);
		out.writeObject(obj);
		out.flush();
		out.close();
	}

	@Override
	public String getIdentifier() {
		return "Default JDK serializer";
	}

}

package ex04;

import java.io.*;
import java.util.prefs.*;

class Preference
{
	static private final int pieceLength = Preferences.MAX_VALUE_LENGTH;

	static private byte[] object2Bytes(Object obj) throws IOException {
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		ObjectOutputStream objectOut = new ObjectOutputStream(byteOut);
		objectOut.writeObject(obj);
		return byteOut.toByteArray();
	}

	static private byte[][] breakIntoPieces(byte raw[]) {
		int numPieces = (raw.length + pieceLength - 1) / pieceLength;
		byte pieces[][] = new byte[numPieces][];
		for (int i = 0; i < numPieces; ++i) {
			int startByte = i * pieceLength;
			int endByte = startByte + pieceLength;
			if (endByte > raw.length) endByte = raw.length;
			int length = endByte - startByte;
			pieces[i] = new byte[length];
			System.arraycopy(raw, startByte, pieces[i], 0, length);
		}
		return pieces;
	}

	static private void writePieces(Preferences prefs, String key, byte pieces[][])
	throws BackingStoreException {
		Preferences node = prefs.node(key);
		node.clear();
		for (int i = 0; i < pieces.length; ++i) {
			node.putByteArray("" + i, pieces[i]);
		}
	}

	static private byte[][] readPieces(Preferences prefs, String key)
	throws BackingStoreException {
		Preferences node = prefs.node(key);
		String keys[] = node.keys();
		int numPieces = keys.length;
		byte pieces[][] = new byte[numPieces][];
		for (int i = 0; i < numPieces; ++i) {
			pieces[i] = node.getByteArray("" + i, null);
		}
		return pieces;
	}

	static private byte[] combinePieces(byte pieces[][]) {
		int length = 0;
		for (int i = 0; i < pieces.length; ++i) {
			length += pieces[i].length;
		}
		byte raw[] = new byte[length];
		int cursor = 0;
		for (int i = 0; i < pieces.length; ++i) {
			System.arraycopy(pieces[i], 0, raw, cursor, pieces[i].length);
			cursor += pieces[i].length;
		}
		return raw;
	}

	static private Object byteToObj(byte bytes[])
	throws IOException, ClassNotFoundException {
		ByteArrayInputStream byteIn = new ByteArrayInputStream(bytes);
		ObjectInputStream objIn = new ObjectInputStream(byteIn);
		Object obj = objIn.readObject();
		return obj;
	}

	static public void putObject(Preferences prefs, String key, Object obj)
	throws IOException, BackingStoreException, ClassNotFoundException {
		byte raw[] = object2Bytes(obj);
		byte pieces[][] = breakIntoPieces(raw);
		writePieces(prefs, key, pieces);
	}

	static public Object getObject(Preferences prefs, String key)
	throws IOException, BackingStoreException, ClassNotFoundException {
		byte pieces[][] = readPieces(prefs, key);
		if (pieces.length == 0) {
			return null;
		}
		byte bytes[] = combinePieces(pieces);
		Object obj = byteToObj(bytes);
		return obj;
	}
}

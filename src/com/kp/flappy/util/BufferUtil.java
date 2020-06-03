package com.kp.flappy.util;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class BufferUtil {

	private BufferUtil() {
		
	}
	
	public static ByteBuffer createBytebuffer(byte[] array) {
		ByteBuffer buffer = ByteBuffer.allocateDirect(array.length).order(ByteOrder.nativeOrder());
		buffer.put(array).flip();
		
		return buffer;
	}
	
	public static FloatBuffer createFloatbuffer(float[] array) {
		FloatBuffer buffer = ByteBuffer.allocateDirect(array.length << 2).order(ByteOrder.nativeOrder()).asFloatBuffer();
		buffer.put(array).flip();
		
		return buffer;
	}
	
	public static IntBuffer createIntbuffer(int[] array) {
		IntBuffer buffer = ByteBuffer.allocateDirect(array.length << 2).order(ByteOrder.nativeOrder()).asIntBuffer();
		buffer.put(array).flip();
		
		return buffer;
	}
}

package com.kp.flappy.graphics;

import com.kp.flappy.math.Matrix4f;
import com.kp.flappy.math.Vector3f;
import com.kp.flappy.util.ShaderUtil;
import static org.lwjgl.opengl.GL20.*;

import java.util.HashMap;
import java.util.Map;

public class Shader {

	public static final int VERTEX_ATTRIB = 0;
	public static final int TEXTURE_ATTRIB = 1;
	
	public static Shader BG;
	public static Shader BIRD;
	public static Shader PIPE;
	public static Shader FADE;
	
	private boolean enabled = false;
	private final int id;

	private Map<String, Integer> locationCache = new HashMap<>();

	public Shader(String vertex, String fragment) {
		id = ShaderUtil.load(vertex, fragment);
	}
	
	public static void loadAll() {
		BG = new Shader("shaders/bg.vert", "shaders/bg.frag");
		BIRD = new Shader("shaders/bird.vert", "shaders/bird.frag");
		PIPE = new Shader("shaders/pipes.vert", "shaders/pipes.frag");
		FADE = new Shader("shaders/fade.vert", "shaders/fade.frag");
	}

	public int getUniform(String name) {
		if (locationCache.containsKey(name))
			return locationCache.get(name);

		int result = glGetUniformLocation(id, name);

		if (result == -1)
			System.err.println("Could not find uniform variable " + name);
		else
			locationCache.put(name, result);

		return result;
	}

	public void setUniform1i(String name, int value) {
		if (!enabled) enable();
		
		glUniform1i(getUniform(name), value);
	}

	public void setUniform1f(String name, float value) {
		if (!enabled) enable();
		
		glUniform1f(getUniform(name), value);
	}
	
	public void setUniform2f(String name, float x, float y) {
		if (!enabled) enable();
		
		glUniform2f(getUniform(name), x, y);
	}
	
	public void setUniform3f(String name, Vector3f vector) {
		if (!enabled) enable();
		
		glUniform3f(getUniform(name), vector.x, vector.y, vector.z);
	}
	
	public void setUniformMat4f(String name, Matrix4f matrix) {
		if (!enabled) enable();
		
		glUniformMatrix4fv(getUniform(name), false, matrix.toFloatBuffer());
	}

	public void enable() {
		glUseProgram(id);
	}

	public void disable() {
		glUseProgram(0);
	}
}

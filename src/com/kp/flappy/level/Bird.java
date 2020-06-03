package com.kp.flappy.level;

import static org.lwjgl.glfw.GLFW.*;

import com.kp.flappy.graphics.Shader;
import com.kp.flappy.graphics.Texture;
import com.kp.flappy.graphics.VertexArray;
import com.kp.flappy.input.Input;
import com.kp.flappy.math.Matrix4f;
import com.kp.flappy.math.Vector3f;

public class Bird {

	private float SIZE = 1;
	private VertexArray mesh;
	private Texture texture;

	private Vector3f position = new Vector3f();
	private float rot;
	private float delta = 0;

	public Bird() {
		float[] vertices = new float[] {
				-SIZE / 2, -SIZE / 2, 0.2f,
				-SIZE / 2,  SIZE / 2, 0.2f,
				 SIZE / 2,  SIZE / 2, 0.2f,
				 SIZE / 2, -SIZE / 2, 0.2f
		};

		byte[] indices = new byte[] {
				0, 1, 2,
				2, 3, 0
		};

		float[] tcs = new float[] {
				0, 1,
				0, 0,
				1, 0,
				1, 1
		};

		mesh = new VertexArray(vertices, indices, tcs);
		texture = new Texture("res/bird.png");
	}
	
	public void fall() {
		delta = -0.15f;
	}
	
	public void update() {
		position.y -= delta;
		
		if (Input.isKeyDown(GLFW_KEY_SPACE))
			delta = -0.12f;
		else
			delta += 0.01f;
		
		rot = -delta * 90f;
	}
	
	public void render() {
		Shader.BIRD.enable();
		Shader.BIRD.setUniformMat4f("ml_matrix", Matrix4f.translate(position).multiply(Matrix4f.rotate(rot)));
		texture.bind();
		mesh.render();
		Shader.BIRD.disable();
	}
	
	public float getY() {
		return position.y;
	}
	
	public float getSize() {
		return SIZE;
	}
}

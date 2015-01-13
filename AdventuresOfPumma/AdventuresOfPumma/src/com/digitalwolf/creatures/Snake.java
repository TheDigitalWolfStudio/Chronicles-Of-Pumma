package com.digitalwolf.creatures;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.digitalwolf.world.World;

public class Snake extends Sprite{

	public float MAX_VELOCITY = 0.04f;
    private float BOUNDARY_x1;
    private float BOUNDARY_x2;
	public static final int STILL = 0;
    public static final int WALKING = 1;
    public static final float width = 96/2.6f * World.WORLD_UNIT;
    public static final float height = 60/2.6f * World.WORLD_UNIT;
    
	public int state;
	public float stateTime;
	public boolean facesRight;
	public boolean visible;
	
	public Snake(float x, float y) {
		super(x, y);
		// TODO Auto-generated constructor stub
		state = WALKING;
		stateTime = 0;
		facesRight = true;	
		BOUNDARY_x1 = x-3;
		BOUNDARY_x2 = x+3;
		visible = true;
	}
	
	public void update(float deltaTime) { 
		super.update(deltaTime);
		stateTime+= deltaTime;
		position.add(velocity);
		
		if(state == WALKING)
		walkInBoundaries();
    }
	
	public Rectangle getBounds() {
        return new Rectangle(position.x, position.y, width, height/2);
    }
    
	private void walkInBoundaries() {
		if(position.x <=BOUNDARY_x1){
			velocity.x += MAX_VELOCITY;
			facesRight=true;
			}
			if(position.x >=BOUNDARY_x2){
			velocity.x -= MAX_VELOCITY;
			facesRight=false;		
			}
	}
	
	public float getDistanceFromPoint(float p1,float p2){
		Vector2 v1 = new Vector2(p1,p2);
		return position.dst(v1);
	}

}


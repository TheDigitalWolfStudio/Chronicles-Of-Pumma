package com.digitalwolf.creatures;

import com.badlogic.gdx.math.Rectangle;
import com.digitalwolf.world.World;

public class Pumma extends Sprite{

	public float MAX_VELOCITY = 10f;
	public float JUMP_VELOCITY = 40f;
	public static float DAMPING = 0.87f;
	
	public static final int JUMP = 2;
	public static final int WALK = 1;
    public static final int STILL = 0;
    
    public boolean visible;
//    public static final float width = 22 * World.WORLD_UNIT;
//    public static final float height = 44 * World.WORLD_UNIT;
    
    public static final float width = 18 * World.WORLD_UNIT;
    public static final float height = 36 * World.WORLD_UNIT;
    public boolean hasKey;
    
    private int state;
    public int health;
	public float stateTime;
	
	//This state will be used during the drawing to decided whether to invert horizontally or not
	public boolean facesRight;
	public boolean grounded;
	
	public void setState(int currentState){
		this.state = currentState;
		//stateTime =0;
	}
	
	public int getState(){
		return this.state;
	}
	
	public Pumma(float x, float y) {
		super(x, y);
		state = STILL;
		stateTime = 0;		
		//make this decision in game screen
		facesRight = true;
		grounded = false;
	    visible = true;
	    health =1000;
	    hasKey = false;
	}

	@Override
    public void update(float deltaTime) {
		super.update(deltaTime);
		if(stateTime>=50)
			stateTime =0;
    }
	 
    public Rectangle getBounds(){
    	return new Rectangle(position.x, position.y, width, height);
    }
    
}

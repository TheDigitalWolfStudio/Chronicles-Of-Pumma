package com.digitalwolf.creatures;

import com.badlogic.gdx.math.Rectangle;
import com.digitalwolf.world.World;

public class Dragon extends Sprite{


	public float MAX_VELOCITY = 0.07f;
    private float BOUNDARY_x1;
    private float BOUNDARY_x2;
   
	public static final int STILL = 0;
    public static final int FLYING = 1;
    public static final float width = 40 * World.WORLD_UNIT;
    public static final float height = 40 * World.WORLD_UNIT;
    
	public int state;
	public float stateTime;
	public boolean facesRight;
	public boolean visible;

	public Dragon(float x, float y) {
		super(x, y);
		// TODO Auto-generated constructor stub

		state = FLYING;
		stateTime = 0;
		facesRight = false;
		BOUNDARY_x1 = x-4;
		BOUNDARY_x2 = x+4;
		
		visible = true;
	}
	
	public void update(float deltaTime) { 
		super.update(deltaTime);
		
		stateTime+= deltaTime;
		position.add(velocity);
		if(state == FLYING)
		flyHorizontally();
		
    }

	public Rectangle getBounds() {
        return new Rectangle(position.x, position.y, width, height/2);
    }
    
	private void flyHorizontally() {
		if(position.x <=BOUNDARY_x1){
			velocity.x += MAX_VELOCITY;
			facesRight=true;
			}
			if(position.x >=BOUNDARY_x2){
			velocity.x -= MAX_VELOCITY;
			facesRight=false;		
			}
	}
	
}

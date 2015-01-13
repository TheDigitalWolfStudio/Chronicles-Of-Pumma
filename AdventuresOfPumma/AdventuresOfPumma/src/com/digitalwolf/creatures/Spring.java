package com.digitalwolf.creatures;

import com.digitalwolf.world.World;

public class Spring extends Sprite{

	public static final float width = 60/3.5f * World.WORLD_UNIT;
    public static final float height = 132/4.5f * World.WORLD_UNIT;
    public boolean visible;
    public float stateTime;
    public static final int NORMAL = 0;
    public static final int ACTIVE = 1;
    public int state;
    
	public Spring(float x, float y) {
		super(x, y);
		visible = true;
		stateTime = 0;
		state = NORMAL;
	}

	@Override
    public void update(float deltaTime) {
		super.update(deltaTime);
		stateTime+= deltaTime;
    }
}

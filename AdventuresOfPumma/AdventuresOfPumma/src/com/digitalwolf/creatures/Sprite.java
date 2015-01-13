package com.digitalwolf.creatures;

import com.badlogic.gdx.math.Vector2;
import com.digitalwolf.world.World;
import com.moribitotech.mtx.settings.AppSettings;

public class Sprite {

	public final Vector2 position;
    public final Vector2 velocity;
    
    public Sprite(float x, float y) {
        this.position = new Vector2(x,y);
        this.velocity = new Vector2();
    }
    
    public void update(float deltaTime) { 
	//Don't let the Sprite visit places where there is no map
				if(position.x <= 0){
					position.x= 0;
				}else if(position.x>= World.mapWidth-1){
					position.x= World.mapWidth-1;
				}
				
				
				//This was needed for two way scrolling
		        if(position.y<= -AppSettings.SCREEN_H/3){
		        position.y=-AppSettings.SCREEN_H/3;
		        }else if(position.y>= World.mapHeight){
		        position.y=  World.mapHeight;
		        }		
    }
    
    
    public void setPosition(float x, float y){
    	this.position.x =x;
    	this.position.y =y;
    }
}
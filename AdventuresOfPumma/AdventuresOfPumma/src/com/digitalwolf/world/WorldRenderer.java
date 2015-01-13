package com.digitalwolf.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.digitalwolf.assets.Assets;
import com.digitalwolf.creatures.Dragon;
import com.digitalwolf.creatures.Pumma;
import com.digitalwolf.creatures.Snake;
import com.digitalwolf.creatures.Spring;
import com.digitalwolf.screens.GameScreen;
import com.mtx.scene2dactors.Egg;
import com.mtx.scene2dactors.Gem;

public class WorldRenderer {

	public OrthogonalTiledMapRenderer renderer;
	public SpriteBatch batch;
	private World world;
	
	public WorldRenderer(World world) {
        this.world = world;
        renderer = new OrthogonalTiledMapRenderer(world.map, 1/16f);
        batch = renderer.getSpriteBatch();
    }
	
	public void render(int[] array) {
		if(GameScreen.state == GameScreen.GAME_RUNNING)
		renderer.render(array);
    }
	
	public void renderPlayer(float deltaTime)
	{
		if(GameScreen.state == GameScreen.GAME_RUNNING || GameScreen.state == GameScreen.GAME_LEVEL_END){
			
		// Based on the Player state, get the animation frame
		if(world.pumma.visible){
		TextureRegion frame = null;
		switch (world.pumma.getState())
		{
		case 0:
			frame = Assets.pummaStill.getKeyFrame(world.pumma.stateTime);
			break;

		case 1:
			frame = Assets.pummaWalk.getKeyFrame(world.pumma.stateTime);
			break;
			
		case 2:
			frame = Assets.pummaJump.getKeyFrame(world.pumma.stateTime);
			break;
			
		}

		// draw the Player depending on the current velocity
		// on the x-axis, draw the Player facing either right or left
		
		batch.begin();
		if (world.pumma.facesRight)
		{
			batch.draw(frame, world.pumma.position.x, world.pumma.position.y, Pumma.width, Pumma.height);
		}
		else
		{
			batch.draw(frame, world.pumma.position.x + Pumma.width, world.pumma.position.y, -Pumma.width, Pumma.height);
		}
		batch.end();
	   }
  }
}

	public void renderEggs(float delta) {
		if(GameScreen.state == GameScreen.GAME_RUNNING){
		batch.begin();
		for(Egg thisEgg:world.eggs){
			thisEgg.draw(batch,1.0f);
		}
		batch.end();
		}
	}
	
	public void renderGems(float delta) {
		if(GameScreen.state == GameScreen.GAME_RUNNING){
		batch.begin();
		for(Gem thisGem:world.gems){
			thisGem.draw(batch,1.0f);
		}
		batch.end();
		}
	}
	
	public void renderSnakes(float delta) {
		if(GameScreen.state == GameScreen.GAME_RUNNING){
			TextureRegion frame = null;
			batch.begin();
			for(int i=0; i< world.snakes.size();i++){
				Snake e = world.snakes.get(i);
				
				switch (e.state)
				{
					case 0:
						frame = Assets.snakeStill.getKeyFrame(e.stateTime);
						break;
					case 1:
						frame = Assets.snakeMove.getKeyFrame(e.stateTime);
						break;
				}
				
				if(e.visible){
					if (e.facesRight)
					{
						batch.draw(frame, e.position.x, e.position.y, Snake.width, Snake.height);
					}
					else
					{
						batch.draw(frame, e.position.x +Snake.width, e.position.y, -Snake.width, Snake.height);
					}
				 }
				   
			}
			batch.end();
		}
		
	}

	public void renderKey(float delta) {
		// TODO Auto-generated method stub
		if(GameScreen.state == GameScreen.GAME_RUNNING){
			if(world.key.visible){
			batch.begin();
			world.key.draw(batch, 1.0f);
			batch.end();
			}
			}
	}

	public void renderDragons(float delta) {
		if(GameScreen.state == GameScreen.GAME_RUNNING){
			TextureRegion frame = null;
			batch.begin();
			for(int i=0; i< world.dragons.size();i++){
				Dragon e = world.dragons.get(i);
				
				switch (e.state)
				{
					case 0:
						frame = Assets.dragonStill.getKeyFrame(e.stateTime);
						break;
					case 1:
						frame = Assets.dragonFlying.getKeyFrame(e.stateTime);
						break;
				}
				
				if(e.visible){
					if (e.facesRight)
					{
						batch.draw(frame, e.position.x, e.position.y, Dragon.width, Dragon.height);
					}
					else
					{
						batch.draw(frame, e.position.x +Dragon.width, e.position.y, -Dragon.width, Dragon.height);
					}
				 }
				   
			}
			batch.end();
		}
	}

	public void renderDoor(float delta) {
		// TODO Auto-generated method stub
		if(GameScreen.state == GameScreen.GAME_RUNNING){
			if(world.door.visible){
			batch.begin();
	
			world.door.draw(batch, 1.0f);
			batch.end();
			}
			}
	}

	public void renderSprings(float delta) {
		// TODO Auto-generated method stub
		if(GameScreen.state == GameScreen.GAME_RUNNING){
			TextureRegion frame = null;
			batch.begin();
			for(int i=0; i< world.springs.size();i++){
				Spring e = world.springs.get(i);
				
				switch (e.state)
				{
					case 0:
						frame = Assets.spring2;
						break;
					case 1:
						frame = Assets.springActive.getKeyFrame(e.stateTime);
						break;
				}
				
				if(e.visible)
				batch.draw(frame, e.position.x, e.position.y, Spring.width, Spring.height);
					
				 }
			batch.end();
		}
	}

	
}

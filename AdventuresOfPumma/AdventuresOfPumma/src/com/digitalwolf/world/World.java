package com.digitalwolf.world;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.digitalwolf.assets.Assets;
import com.digitalwolf.creatures.Dragon;
import com.digitalwolf.creatures.Pumma;
import com.digitalwolf.creatures.Snake;
import com.digitalwolf.creatures.Spring;
import com.digitalwolf.gamedata.DoorData;
import com.digitalwolf.gamedata.DragonData;
import com.digitalwolf.gamedata.EggData;
import com.digitalwolf.gamedata.GemData;
import com.digitalwolf.gamedata.KeyData;
import com.digitalwolf.gamedata.PlayerData;
import com.digitalwolf.gamedata.SnakeData;
import com.digitalwolf.gamedata.SpringData;
import com.digitalwolf.screens.GameScreen;
import com.moribitotech.mtx.settings.AppSettings;
import com.mtx.scene2dactors.Door;
import com.mtx.scene2dactors.Egg;
import com.mtx.scene2dactors.Gem;
import com.mtx.scene2dactors.Key;

public class World {

	public interface WorldListener {
        public void fall();
        public void grabgem();
        public void grabegg();
        public void cry();
        public void ow();
        public void success();
    }
	
	public static final float WORLD_WIDTH = 30;
    public static final float WORLD_HEIGHT = 20;
    public static final int WORLD_STATE_RUNNING = 0;
    public static final int WORLD_STATE_NEXT_LEVEL = 1;
    public static final int WORLD_STATE_GAME_OVER = 2;
    public static final float GRAVITY = -2.5f;
    public static final float WORLD_UNIT =1/16f;
    public int state;
    public static int levelID =1;
    
    //The time spent after the world is created (in seconds)
    private float startTime = System.nanoTime();
	public static float SECONDS_TIME = 0;
    
    //VARIABLES FOR LEVEL MAPS AND THEIR PROPERTIES

    public TiledMap map;
    public MapProperties prop;
    public static int mapWidth;
    public static int mapHeight;
    public final WorldListener listener;
    
    //Create the Player here
    public Pumma pumma;
    public Key key;
    public Door door;
    public ArrayList<Dragon> dragons;
    public ArrayList<Snake> snakes;
    public ArrayList<Spring> springs;
    public List<Egg> eggs;
    public List<Gem> gems;

    
    //VARIABLES FOR PLAYER ACHIEVEMENTS/SCORES/HURTS
    public static int score;
    
    public boolean pummaAtdoor;
    public int eggsPummaHave, gemsPummaHave;
    private int numTotalEggs, numTotalGems;
    private float[][] snakePosition, eggPosition, dragonPosition, keyPosition, doorPosition, gemPosition, springPoition;
    
    //Rectangles for Checking Bounds between Player and Level map
    private Pool<Rectangle> rectPool = new Pool<Rectangle>()
			{
				@Override
				protected Rectangle newObject()
				{
					return new Rectangle();
				}
			};
			
	private Array<Rectangle> tiles = new Array<Rectangle>();
			
	 public World(WorldListener listener) {
			 this.listener = listener;
			 this.state = WORLD_STATE_RUNNING;
			 
			 SECONDS_TIME = 0;
			        //TAKE THIS LEVEL ID FROM GAMESCREEN AS A PARAMETER
			        //IF THE GAME IS CONTINUED THEN IT WILL READ THE CURRENTLEVEL FROM JSON/PREFERENCES/DATABASES
			        
			        //IDEA IS SAVE BOOLEAN GAMECONTINUED IF THE CONTINUE BUTTON IS PRESSED AND USE IF O DETERMINE
			        levelID = GameScreen.currentlevel;
			        generateLevelMap(levelID);
			        
			        //score = 0;
			       
			        //CREATE THE PUMMA
			        this.pumma = new Pumma(0,0);
			        pumma.setPosition(PlayerData.getPlayerPosition(levelID)[0][0], PlayerData.getPlayerPosition(levelID)[0][1]);
			        pummaAtdoor = false;
			        
			        //INITIALIZE SNAKES HERE
			        this.snakes = new ArrayList<Snake>();
			        snakePosition = SnakeData.getSnakePosition(levelID);
			        placeSnakes();
			        
			        //INITIALIZE DRAGONS HERE
			        this.dragons = new ArrayList<Dragon>();
			        dragonPosition = DragonData.getDragonPosition(levelID);
			        placeDragons();
			        
			        //PLACE THE KEY HERE
			        keyPosition = KeyData.getKeyPosition(levelID);		        
			        this.key = new Key(Key.width, Key.height, true );
			        placeKey();
			     
			        //PLACE THE DOOR HERE
			        doorPosition = DoorData.getDoorPosition(levelID);
			        this.door = new Door(Door.width, Door.height, true );
			        placeDoor();
			        
			        //PLACE THE EGGS HERE			        
			        eggs = new ArrayList<Egg>();
			        eggPosition = EggData.getEggPosition(levelID);
			        numTotalEggs = EggData.getEggPosition(levelID).length;
			        placeEggs();
			        
			        //PLACE THE GEMS HERE
			        gems = new ArrayList<Gem>();
			        gemPosition = GemData.getGemPosition(levelID);
			        numTotalGems  = GemData.getGemPosition(levelID).length;
			        placeGems();
			        
			        //PLACE THE GEMS HERE
			        springs = new ArrayList<Spring>();
			        springPoition = SpringData.getSpringPosition(levelID);
			        placeSprings();
			        
			        placeDoor();
			        
			        //initialize 
			        gemsPummaHave =0;
			        eggsPummaHave =0;
			        
			      
	}

	private void placeSprings() {
		// TODO Auto-generated method stub
		for(int i=0;i<springPoition.length;i++){
			Spring spring1 = new Spring(springPoition[i][0],springPoition[i][1]);
			springs.add(spring1);
		}	
	}

	private void placeGems() {
		// TODO Auto-generated method stub
		  for(int i=0;i<gemPosition.length;i++){
				Gem gem1 = new Gem(Gem.width, Gem.height, true);
				gem1.setOrigin(0, 0);
				gem1.setPosition(gemPosition[i][0],gemPosition[i][1]);
				gem1.setSize(Gem.width, Gem.height);
				gem1.setTextureRegion(Assets.snake_gem, true);
				gems.add(gem1);
			}	
	}

	private void placeDoor() {
		// TODO Auto-generated method stub
		door.setOrigin(0, 0);
		door.setPosition(doorPosition[0][0],doorPosition[0][1]);
		door.setSize(Door.width, Door.height);
		door.setTextureRegion(Assets.door, true);
	}

	private void placeKey() {
		// TODO Auto-generated method stub		
		key.setOrigin(0, 0);
		key.setPosition(keyPosition[0][0],keyPosition[0][1]);
		key.setSize(Key.width, Key.height);
		key.setTextureRegion(Assets.key, true);
	}

	private void placeDragons() {

		 for(int i=0;i< dragonPosition.length;i++){
				Dragon e = new Dragon(dragonPosition[i][0],dragonPosition[i][1]);
				dragons.add(e);
				e.velocity.x-=e.MAX_VELOCITY;
			}
	}

	private void placeEggs() {
		  for(int i=0;i<eggPosition.length;i++){
				Egg egg1 = new Egg(Egg.width, Egg.height, true);
				egg1.setOrigin(0, 0);
				egg1.setPosition(eggPosition[i][0],eggPosition[i][1]);
				egg1.setSize(Egg.width, Egg.height);
				egg1.setTextureRegion(Assets.egg, true);
				eggs.add(egg1);
			}	
	}

	private void placeSnakes() {
		// TODO Auto-generated method stub
		  for(int i=0;i<snakePosition.length;i++){
				Snake e = new Snake(snakePosition[i][0],snakePosition[i][1]);
				snakes.add(e);
			    e.velocity.x-=e.MAX_VELOCITY;
			}		
	}
	
	private void generateLevelMap(int levelID) {
					// Load the map
		map = new TmxMapLoader().load("data/level/level"+levelID+".tmx");

					prop = map.getProperties();
			        //Get the properties
					mapWidth = prop.get("width", Integer.class);
					mapHeight = prop.get("height", Integer.class);
	  }
	  
	  /**
		 * MOST IMPORTANT METHOD THAT UPDATES THE WHOLE GAME WORLD
		 * JUST THE INPUT EVENTS FOR PLAYER AND GAME PAUSE ARE HANDLED IN THE GAMESCREEN 
		 * UPDATES THE PLAYER AND ALL OTHER GAME OBJECTS
		 * CHECKS FOR COLLISION BETWEEN VARIOUS GAME OBJECTS INCLUDING LAYER TILES
		 * CHECKS FOR THE CONDITION FOR GAME OVER, LEVEL END
		 * **/
		
		public void update(float deltaTime) {
			if (deltaTime == 0) return;
			
			checkPoomaAtDoor();
			updatePumma(deltaTime);
			updateSnakes(deltaTime);
			updateDragons(deltaTime);
			updateKey(deltaTime);
			
			
		    checkCollisions(deltaTime);
		    checkGameOver();
		    
		    if (System.nanoTime() - startTime >= 1000000000) {
				SECONDS_TIME++;
				startTime = System.nanoTime();
			}
		    
		    if(SECONDS_TIME % 5 ==0)
		    	pumma.health--;
		    }
		
		private void updateKey(float deltaTime) {
		// TODO Auto-generated method stub
			if(key.visible){
				key.update(deltaTime);
			}
			else
				key.remove();
	     }

		private void updateDragons(float deltaTime) {
		// TODO Auto-generated method stub
			for(int i=0;i< dragons.size();i++){
				Dragon e = dragons.get(i);
				if(e.visible){
					e.update(deltaTime);
				}
				else{
					dragons.remove(e);
				}
			}
	    }

		
		private void updateSnakes(float deltaTime) {
			for(int i=0;i< snakes.size();i++){
				Snake e = snakes.get(i);
				if(e.visible){
					e.update(deltaTime);
				}
				else{
					snakes.remove(e);
				}
			}
	    }

		private void checkGameOver() {
			//IF THE PLAYER FALLS DOWN
			if (pumma.position.y <= -AppSettings.SCREEN_H/10) {
	            state = WORLD_STATE_GAME_OVER;
	            listener.fall();
	            GameScreen.gameoverinfo ="Pumma Fell Down";
	        }
			
			if (pumma.health <= 0) {
	            state = WORLD_STATE_GAME_OVER;
	            GameScreen.gameoverinfo ="Pumma lost Health";
	            listener.fall();
	        }
			
			//Conditions for Level Completion			
			if(pummaAtdoor && pumma.hasKey  && (eggsPummaHave >= numTotalEggs/2)  && (gemsPummaHave >= numTotalGems/2)){
				state = WORLD_STATE_NEXT_LEVEL;	
			 }
	}

		//UPDATES NECESSARY FOR THE PLAYER LIKE APPLYING GRAVITY, CLAMPING ETC
		private void updatePumma(float deltaTime) {
			 
			if (deltaTime == 0)
				return;
		
			pumma.stateTime+= deltaTime;
			//Updates the Player helping not to cross the map Boundaries
			pumma.update(deltaTime);
		    //Apply gravity if we are falling
			pumma.velocity.add(0, GRAVITY);

		 	//Clamp the velocity to the maximum, x-axis only
		 		if(Math.abs(pumma.velocity.x) > pumma.MAX_VELOCITY) {
		 			pumma.velocity.x = Math.signum(pumma.velocity.x) * pumma.MAX_VELOCITY;
		 		}

		 		// clamp the velocity to 0 if it's < 1, and set the state to Standing
		 		if(Math.abs(pumma.velocity.x) < 1) {
		 			pumma.velocity.x = 0;
		 			if(pumma.grounded) 
		 				pumma.setState(Pumma.STILL);
		 		}
	            
		 		// multiply by delta time so we know how far we go
		 		// in this frame
		 		pumma.velocity.scl(deltaTime);
		    
		}
		
		//CHECK FOR COLLISION REGULARLY IN GAMES
		private void checkCollisions(float deltaTime) {
			
		    checkCollisionsPlayervsMap(deltaTime); 
		    checkCollisionsPlayervsSnakes(deltaTime); 
		    checkCollisionsPlayervsEggs(deltaTime); 
		    checkCollisionsPlayervsGems(deltaTime); 
		    checkCollisionsPlayervsDragons(deltaTime); 
		    
		    if(key.visible)
		    checkCollisionsPlayervsKey(deltaTime); 
		    
		    checkCollisionsPlayervsSpring(deltaTime);
		}
		
		private void checkCollisionsPlayervsSpring(float deltaTime) {
			// TODO Auto-generated method stub
			
			for(int i=0;i<springs.size();i++){
				Spring e = springs.get(i);
				e.update(deltaTime);
				Rectangle springBounds = new Rectangle(e.position.x,e.position.y,Spring.width,Spring.height);
				if(springBounds.overlaps(pumma.getBounds())){
					listener.ow();

					e.state = Spring.ACTIVE;
		            pumma.velocity.y+= pumma.JUMP_VELOCITY/2;
		            if(pumma.velocity.y >=  pumma.JUMP_VELOCITY*1.5f)
		            	pumma.velocity.y =  pumma.JUMP_VELOCITY*1.5f;
					}
					else{
					e.state = Spring.NORMAL;
					}				
					
				}
		}

		private void checkCollisionsPlayervsGems(float deltaTime) {
			// TODO Auto-generated method stub
			for (int j = 0; j<gems.size(); j++) {
                Gem e = (Gem) gems.get(j);
                Rectangle gemBounds = new Rectangle(e.getX(),e.getY(),e.getWidth(),e.getHeight());

     
            
             if(pumma.getBounds().overlaps(gemBounds)){
            	 listener.grabgem();
            	 score+=20;
            	gemsPummaHave++;
                e.visible = false;
                gems.remove(e);
    			}	
             }
		}

		private void checkCollisionsPlayervsKey(float deltaTime) {
			// TODO Auto-generated method stub
			 Rectangle keyBounds = new Rectangle(key.getX(),key.getY(),key.getWidth(),key.getHeight());

		      if(pumma.getBounds().overlaps(keyBounds)){
		    	    listener.success();
	            	key.visible = false;
	                pumma.hasKey = true;
	                score+=500;
	    			}	
		}

		private void checkPoomaAtDoor(){

			Rectangle doorBounds = new Rectangle(door.getX(),door.getY(), door.getWidth(),door.getHeight());
			
			if(pumma.getBounds().overlaps(doorBounds)){
			pummaAtdoor = true;
			if(pumma.hasKey  && (eggsPummaHave >= numTotalEggs/2)  && (gemsPummaHave >= numTotalGems/2)){
			door.setTextureRegion(Assets.transparent, true);
			}
			}
			else{
				pummaAtdoor = false;
			}
		}
		
		private void checkCollisionsPlayervsDragons(float deltaTime) {
			// TODO Auto-generated method stub
			 for (int j = 0; j<dragons.size(); j++) {
	                Dragon e = (Dragon) dragons.get(j);
	                Rectangle dragonBounds = new Rectangle(e.position.x,e.position.y,Dragon.width,Dragon.height);

	           
	             if(pumma.getBounds().overlaps(dragonBounds)){
	                e.visible = false;
	                listener.cry();
	                state = WORLD_STATE_GAME_OVER;
		            GameScreen.gameoverinfo ="Devil Dragon Attacked";
	    			}	
              }
		}

		private void checkCollisionsPlayervsEggs(float deltaTime) {
			// TODO Auto-generated method stub
			 for (int j = 0; j<eggs.size(); j++) {
	                Egg e = (Egg) eggs.get(j);
	                Rectangle eggBounds = new Rectangle(e.getX(),e.getY(),e.getWidth(),e.getHeight());

	               if(pumma.getBounds().overlaps(eggBounds)){
	           
	               //EffectCreator.create_FO(e, 0.5f, null, true);
	               //EffectCreator.create_SC_FO(e, 0.5f, 0.5f, 0.3f, 0.0f, null, true);
	            	listener.grabegg();
	            	score+=100;
	            	eggsPummaHave++;
	            	pumma.health+=100;
	                e.visible = false;
	                eggs.remove(e);
	    			}	
                 }
		}

    	
		private void checkCollisionsPlayervsSnakes(float deltaTime) {
			// TODO Auto-generated method stub
			
			for(int i=0;i<snakes.size();i++){
				Snake e = snakes.get(i);
				Rectangle snakeBounds = e.getBounds();
				if(snakeBounds.overlaps(pumma.getBounds())){
					listener.ow();
					state = WORLD_STATE_GAME_OVER;
		            GameScreen.gameoverinfo ="snake poisoned pumma";
					}
					else{
			
					}				
					
				}
			}

		private void checkCollisionsPlayervsMap(float deltaTime) {
			// perform collision detection & response, on each axis, separately
			// if the koala is moving right, check the tiles to the right of it's
			// right bounding box edge, otherwise check the ones to the left
			
			Rectangle pummaRect = rectPool.obtain();
			pummaRect.set(pumma.position.x , pumma.position.y, Pumma.width, Pumma.height);
			int startX, startY, endX, endY;
			if (pumma.velocity.x > 0)
			{
				startX = endX = (int) (pumma.position.x + Pumma.width+ pumma.velocity.x);
			}
			else
			{
				startX = endX = (int) (pumma.position.x + pumma.velocity.x);
			}
			startY = (int) (pumma.position.y);
			endY = (int) (pumma.position.y + Pumma.height);
			getTiles(startX, startY, endX, endY, tiles);
			pummaRect.x += pumma.velocity.x;
			for (Rectangle tile : tiles)
			{
				if (pummaRect.overlaps(tile))
				{
					pumma.velocity.x = 0;
					break;
				}
			}
			pummaRect.x = pumma.position.x;

			// if the koala is moving upwards, check the tiles to the top of it's
			// top bounding box edge, otherwise check the ones to the bottom
			
			if (pumma.velocity.y > 0)
			{
				startY = endY = (int) (pumma.position.y + Pumma.height + pumma.velocity.y);
			}
			else
			{
				startY = endY = (int) (pumma.position.y + pumma.velocity.y);
			}
			startX = (int) (pumma.position.x);
			endX = (int) (pumma.position.x + 3*Pumma.width/4);
			getTiles(startX, startY, endX, endY, tiles);
			pummaRect.y += pumma.velocity.y;
			
			//Stores the property of the tile the player collides with in the foreground layer
			String type ="";
			
			for (Rectangle tile : tiles)
			{
				if (pummaRect.overlaps(tile))
				{
					
					//Here we are checking for the tile properties that can be set using Tiled
					TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(1);
					try{
						type = layer.getCell((int) tile.x, (int) tile.y).getTile().getProperties().get("type").toString();
						}
						catch(Exception e){
							System.out.print("Tile "+type+" :not found : Exception in Tiles Property :" +e);
							type="unknown";
					    }
					
					if (pumma.velocity.y > 0)
					{
						listener.ow();
						if(("1up".equals(type))){
							
							//TiledMapTileSet tileset = map.getTileSets().getTileSet("worldmaptileset1");
							
							layer.setCell((int) tile.x, (int) tile.y, layer.getCell(22, 5));
							layer.setCell((int) tile.x+1, (int) tile.y, layer.getCell(22, 5));
							layer.setCell((int) tile.x, (int) tile.y+1, layer.getCell(22, 5));
							layer.setCell((int) tile.x+1, (int) tile.y+1, layer.getCell(22, 5));
							
							score+=100;
							}
						
						if(("breakable1".equals(type))){
							layer.setCell((int) tile.x, (int) tile.y, null);
							
							score+=20;
							}
						
						pumma.position.y = (tile.y - Pumma.height);
						
					}
					else
					{	
						//If the player is falling down this tile i.e.Velocity<=0
						//Let us change this tile with other
												
						pumma.position.y = (tile.y + tile.height);
						// if we hit the ground, mark us as grounded so we can jump						
						pumma.grounded = true;
					}
					pumma.velocity.y =0;
					break;
				}
			}
			rectPool.free(pummaRect);

			// unscale the velocity by the inverse delta time and set 
			// the latest position
			
			pumma.position.add(pumma.velocity);
			//koala.getVelocityVector().scl(1 / deltaTime);
			pumma.velocity.scl(1 /deltaTime);
			// Apply damping to the velocity on the x-axis so we don't
			// walk infinitely once a key was pressed
			
			pumma.velocity.x *= Pumma.DAMPING;
		}
		
		//RESAERCH THIS METHOD
				private void getTiles(int startX, int startY, int endX, int endY, Array<Rectangle> tiles)
				{
					TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(1);
					//test = (String) layer.getCell(22, 9).getTile().getProperties().get("tileType");
					
					rectPool.freeAll(tiles);
					tiles.clear();
					for (int y = startY; y <= endY; y++)
					{
						for (int x = startX; x <= endX; x++)
						{
							Cell cell = layer.getCell(x, y);
							if (cell != null)
							{
								Rectangle rect = rectPool.obtain();
								rect.set(x, y, 1, 1);
								tiles.add(rect);
							}
						}
					}
				}
}

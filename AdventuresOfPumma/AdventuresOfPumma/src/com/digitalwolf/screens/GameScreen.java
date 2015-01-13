package com.digitalwolf.screens;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.digitalwolf.assets.Assets;
import com.digitalwolf.creatures.Pumma;
import com.digitalwolf.gamedata.EggData;
import com.digitalwolf.gamedata.GameData;
import com.digitalwolf.gamedata.GemData;
import com.digitalwolf.screenhelpers.GameScreenGameOverMenu;
import com.digitalwolf.screenhelpers.GameScreenGamePauseMenu;
import com.digitalwolf.screenhelpers.GameScreenGameReadyMenu;
import com.digitalwolf.screenhelpers.GameScreenLevelEndMenu;
import com.digitalwolf.world.World;
import com.digitalwolf.world.World.WorldListener;
import com.digitalwolf.world.WorldRenderer;
import com.moribitotech.mtx.AbstractScreen;
import com.moribitotech.mtx.IScreen;
import com.moribitotech.mtx.models.base.EmptyAbstractActorLight;
import com.moribitotech.mtx.settings.AppSettings;

public class GameScreen extends AbstractScreen implements IScreen{

	//THREE ACTORS ONLY FOR DEMONSTRATION
	private BitmapFont gameFont;
	public static int gameOverCounterForAds =0;
	
	//DEFINITION OF SCREEN HELPERS THAT HELP CREATE MENU FOR VARIOUS GAME STATES
	public GameScreenGameReadyMenu gameScreenGameReadyMenu;
	public GameScreenGamePauseMenu gameScreenGamePauseMenu;	
	public GameScreenGameOverMenu gameScreenGameOverMenu;
	public GameScreenLevelEndMenu gameScreenLevelEndMenu;
    EmptyAbstractActorLight healthBar;
	private float buttonSize = 100*AppSettings.getWorldSizeRatio();
	
	private OrthographicCamera camera;
	
	//DEFINING THE VARIOUS STATES OF THE GAME
	 public static final int GAME_READY = 0;    
	 public static final int GAME_RUNNING = 1;
	 public static final int GAME_PAUSED = 2;
	 public static final int GAME_LEVEL_END = 3;
	 public static final int GAME_OVER = 4;
	 public static int state;

	public static int lastScore;
	public static String gameoverinfo;
	public static String scoreString;
	public static int currentlevel =1;
	 
	//CREATE AN INSTANCE OF WORLD AND WORLD RENDERER
	World world;
	WorldListener worldListener;
	WorldRenderer renderer;
		
	 // KEEP A MODE FOR TESTING
	 public static boolean DEBUG_MODE = false;

	public static int creditsPoint;
	 
	 public GameScreen(Game game, String screenName) {
		super(game, screenName);
		
		setUpTheWorld();
		gameFont = new BitmapFont(Gdx.files.internal("data/gameFont.fnt"), Gdx.files.internal("data/gameFont.png"), false);
		
		state = GAME_READY;
		setUpScreenElements();
	    setUpInfoPanel();
		setUpMenu();
		
		
		//CREATE AN ORTHOGRAPHIC CAMERA THAT SHOWS US 30X20 UNITS OF THE WORLD
        //IN THIS FRAMEWORK 1 WORLD UNIT = 16 SCREEN PIXELS
		 worldListener = new WorldListener() {
	        	@Override
				public void fall () {
					Assets.playSound(Assets.fall);
				}
	        	@Override
				public void grabgem () {
					Assets.playSound(Assets.gemgrab);
				}
	        	@Override
				public void grabegg() {
					Assets.playSound(Assets.egggrab);
				}
	        	@Override
				public void cry () {
					Assets.playSound(Assets.cry);
				}
	        	@Override
				public void ow() {
					Assets.playSound(Assets.ow);
				}
	        	@Override
				public void success () {
					Assets.playSound(Assets.success);
				}

	     };
	        
	        
		camera = new OrthographicCamera(AppSettings.SCREEN_W,AppSettings.SCREEN_H);
		camera.setToOrtho(false, 30, 20);
		camera.update();

		//Called once the player completes a Level
		resetGame();			
	}

	public void resetGame() {
		// The game is reset each time the game is over
		lastScore = World.score;
		
		//###########################
		if(!DEBUG_MODE){
		if(state == GAME_READY)
		gameScreenGameReadyMenu.sendInMenu(GameScreen.this);
		}
		
		creditsPoint =0;
	}
	
	public void setUpTheWorld(){
		  world = new World(worldListener);
		  renderer = new WorldRenderer(world);
	}
	
	@Override
	public void setUpScreenElements() {
		// TODO Auto-generated method stub
		setBackgroundTexture(Assets.bg);
		
		if(!DEBUG_MODE){
		gameScreenGameReadyMenu = new GameScreenGameReadyMenu();
		gameScreenGamePauseMenu	= new GameScreenGamePauseMenu();
		gameScreenGameOverMenu = new GameScreenGameOverMenu();
		gameScreenLevelEndMenu = new GameScreenLevelEndMenu();
		}
		
		healthBar = new EmptyAbstractActorLight(500*AppSettings.getWorldPositionXRatio(), 18, true);
		healthBar.setPosition(140*AppSettings.getWorldPositionXRatio(), AppSettings.SCREEN_H - 25*AppSettings.getWorldPositionYRatio());		
		healthBar.setTextureRegion(Assets.transparent, true);
	}

	@Override
	public void setUpInfoPanel() {
		
        }

	@Override
	public void setUpMenu() {
		
		 // SET UP ALL THE SCREEN HELPERS HERE
		if(!DEBUG_MODE){
		gameScreenGameReadyMenu.setUpMenu(this);
		gameScreenGameOverMenu.setUpMenu(this);
		gameScreenGamePauseMenu.setUpMenu(this);
		gameScreenLevelEndMenu.setUpMenu(this);
		}
	}
	
	@Override
	public void render(float delta) {		
		super.render(delta);

		
		/*************VERY IMPORTANT ***********************************************************************/
		//SET THE VIEW OF THE WORLD RENDERER AS PER MY CAMERA DEFINED HERE SO THAT IT CAN MAP TO WORLD UNITS
        renderer.renderer.setView(camera);
		
        //BASE THE HORIZONTAL MOVEMENT OF THE CAMERA ON THE PLAYER MOVEMENT
		camera.position.x = world.pumma.position.x;	
		
        //DON'T LET THE CAMERA SHOW PLACES WHERE THERE IS NO MAP
		if(camera.position.x < World.WORLD_WIDTH/2)
		{
		   camera.position.x= World.WORLD_WIDTH/2;
		}else if(camera.position.x> World.mapWidth - World.WORLD_WIDTH/2){
		   camera.position.x= World.mapWidth - World.WORLD_WIDTH/2;
		}
		
		//UPDATE THE CAMERA TO REFLECT ALL THE CHANGES
		camera.update();
		
		//UPDATE THE GAMESCREEN ACCORDING TO THE CURRENT GAME STATE
				update(delta);
				
		//THE SEQUENCE MATTERS.. IF I DRAW ALL THESE AFTER SWITCH STATES , THE GAME SHALL BE VISIBLE ONLY WHEN THE GAME
		//IS RUNNING AND NOT IN STATES LIKE LEVEL COMPLETED, PAUSE , READY AND GAMEOVER
				
	    //RENDER THE GAME SPRITES & THE LEVEL MAP LAYER BY LAYER		
		int[] prevlayers = new int[] {0, 1};
		    renderer.render(prevlayers);
		    renderer.renderKey(delta);
		    renderer.renderDoor(delta);
		    renderer.renderSprings(delta);
		    renderer.renderGems(delta);
		    renderer.renderEggs(delta);
		    
		    renderer.renderSnakes(delta); 
		    renderer.renderDragons(delta);
	        renderer.renderPlayer(delta);
	        renderer.render(new int[] {2});
	    
	    
		//UPDATE THE GAMESCREEN ACCORDING TO THE CURRENT GAME STATE
	    
		 //THIS METHOD IS CALLED IN RENDER() LOOP SO THAT IT CAN CONTINUOSLY CHECK THE 
		//GAME STATES AND CALL A PARTICULAR RENDERSTATE() TO DRAW THINGS ACCORDINGLY
		/******************************************************************************/
				
		//IMPLEMENT DIFFERENT UPDATE AND PRESENT METHODS FOR VARIOUS GAME STATES
		getStage().getSpriteBatch().begin();
	    switch(state) {
	    
	    case GAME_READY:
	        renderReady();
	        break;
	        
	    case GAME_RUNNING:		    
	    	renderRunning();
	        break;
	        
	    case GAME_PAUSED:
	    	renderPaused();
	        break;
	        
	    case GAME_LEVEL_END:
	    	renderLevelEnd();
	        break;
	        
	    case GAME_OVER:
	    	renderGameOver();
	        break;
	    }	
			
	    if(DEBUG_MODE){
	    	gameFont.draw(getStage().getSpriteBatch(), "DEBUGGING MODE: ON", 300, 50);
        }
	    
	    getStage().getSpriteBatch().end();
	    
		}
		
	private void update(float delta) {
		// TODO Auto-generated method stub
		    
		    switch(state) {
		    case GAME_READY:
		        updateReady();
		        break;
		    case GAME_RUNNING:
		        updateRunning(delta);
		        break;
		    case GAME_PAUSED:
		        updatePaused();
		        break;
		    case GAME_LEVEL_END:
		        updateLevelEnd();
		        break;
		    case GAME_OVER:
		        updateGameOver();
		        break;
		    }
	}
	
	private void updateGameOver() {
		// TODO Auto-generated method stub
		if(DEBUG_MODE){
			
			if (Gdx.input.justTouched()) {  
				 Gdx.app.log("A HIT", "GAME WENT FROM GAMEOVER TO MAINMENU"); 
			    getGame().setScreen(new MainMenuScreen(getGame(),"MainMenuScreen"));
			}  	
		}
	}

	private void updateLevelEnd() {
		// TODO Auto-generated method stub
		if(DEBUG_MODE){
		if (Gdx.input.justTouched()) {  
			 Gdx.app.log("A HIT", "GAME WENT FROM LEVEL TO LEVEL"); 
			 currentlevel++;
			 GameData.addToUnLockedLevel(GameScreen.currentlevel);
			 
		     world = new World(worldListener);
		     renderer = new WorldRenderer(world);
		     World.score = lastScore;
		     state = GAME_READY;
		     resetGame();
		}  
		 }
	}

	private void updatePaused() {
		// TODO Auto-generated method stub
		 if(DEBUG_MODE){
		if (Gdx.input.justTouched()) {  
		    Gdx.app.log("A HIT", "GAME WENT TO PAUSE STATE"); 
            state = GAME_RUNNING;
            return;
		}  
		 }
	}

	private void updateRunning(float delta) {
		// TODO Auto-generated method stub
	
		 lastScore = World.score;
		 currentlevel = World.levelID;
	     scoreString = "" + lastScore;   
	   
		if(world.state == World.WORLD_STATE_GAME_OVER) {
			saveGameStates();
			lastScore = World.score =0;
			if(!DEBUG_MODE){
			gameScreenGameOverMenu.sendInMenu(this);
			}
			state = GAME_OVER;
	    }

		 if(world.state == World.WORLD_STATE_NEXT_LEVEL) {
			 saveGameStates();
			 try{
			 creditsPoint = ((world.eggsPummaHave*50)/EggData.getEggPosition(currentlevel).length +
					 ((world.gemsPummaHave*50)/GemData.getGemPosition(currentlevel).length));
			 }
			 catch(Exception e){
				 creditsPoint = 0; 
			 }
					
			 if(!DEBUG_MODE){
				gameScreenLevelEndMenu.sendInMenu(this);
			 }
				state = GAME_LEVEL_END;    
				world.listener.success();
		    }
			    
		//UPDATE THE PLAYER FOR USER INPUT
		updatePlayerForUserInput(delta);
			
		//UPDATE THE WORLD
		  world.update(delta);
	}

	private void updatePlayerForUserInput(float delta) {
		
		boolean left = false;
		boolean right = false;
		boolean jump = false;
		boolean pause = false;
		
		if (Gdx.app.getType() == ApplicationType.Android || Gdx.app.getType() == ApplicationType.iOS) {

				for (int i = 0; i < 2; i++) {
					int x = (int)(Gdx.input.getX(i) / (float)Gdx.graphics.getWidth() * AppSettings.SCREEN_W);
					int y = (int)(Gdx.input.getY(i) / (float)Gdx.graphics.getWidth() * AppSettings.SCREEN_W);
					if (!Gdx.input.isTouched(i)) continue;
					
					if(y<= AppSettings.SCREEN_H && y>= AppSettings.SCREEN_H -90){
					if (x <=90) {
						
						left |= true;
					}
					if (x > 1.2f*buttonSize && x <= 2.2f*buttonSize) {
						
						right |= true;
					}


					if (x >= AppSettings.SCREEN_W - 90 && x < AppSettings.SCREEN_W) {
						
						jump |= true;
					}
					
					
				}
					
                    if (x >= AppSettings.SCREEN_W -64 && y >= 0 && y <= 70) {
						pause |= true;
					}					
				}
			}
		
		// CHECK USER INPUT AND APPLY TO VELOCITY AND STATES OF THE MAIN PLAYER
				if ((Gdx.input.isKeyPressed(Keys.SPACE) && world.pumma.grounded) || (jump && world.pumma.grounded))
				{
					world.pumma.velocity.y += world.pumma.JUMP_VELOCITY;
					world.pumma.setState(Pumma.JUMP);
					world.pumma.grounded = false;
				}

				
				if (Gdx.input.isKeyPressed(Keys.LEFT) ||left)
				{
					world.pumma.velocity.x = -world.pumma.MAX_VELOCITY;
					if (world.pumma.grounded)
						world.pumma.setState(Pumma.WALK);
					world.pumma.facesRight = false;
				}

				if (Gdx.input.isKeyPressed(Keys.RIGHT) || right)
				{
					world.pumma.velocity.x = world.pumma.MAX_VELOCITY;
					if (world.pumma.grounded)
						world.pumma.setState(Pumma.WALK);
					world.pumma.facesRight = true;
					
				}
				
				if (Gdx.input.isKeyPressed(Keys.P) || pause)
				{
					state = GAME_PAUSED;
					
					if(!DEBUG_MODE){
					gameScreenGamePauseMenu.sendInMenu(this);
					}
				}				
	}
	
	private void updateReady() {
		// TODO Auto-generated method stub
		 if(DEBUG_MODE){
		if (Gdx.input.justTouched()) {  
			 Gdx.app.log("A HIT", "GAME WENT FROM READY TO RUNNING STATE"); 
		     state = GAME_RUNNING;
		     return;
		}  
		 }
	}

	private void renderGameOver() {
		// TODO Auto-generated method stub
		if(DEBUG_MODE){
			gameFont.draw(getStage().getSpriteBatch(), " "+gameoverinfo, AppSettings.SCREEN_W/3, AppSettings.SCREEN_H/2 +80);
			getStage().getSpriteBatch().draw(Assets.logo, AppSettings.SCREEN_W/3, AppSettings.SCREEN_H/2);		
		}
	}

	private void renderLevelEnd() {
		// TODO Auto-generated method stub
		if(DEBUG_MODE){
			gameFont.draw(getStage().getSpriteBatch(), "Level "+currentlevel+" Completed", AppSettings.SCREEN_W/3, AppSettings.SCREEN_H/2 +80);
			getStage().getSpriteBatch().draw(Assets.logo, AppSettings.SCREEN_W/3, AppSettings.SCREEN_H/2);		
		}
	}

	private void renderPaused() {
		// TODO Auto-generated method stub
		
		if(DEBUG_MODE){
			gameFont.draw(getStage().getSpriteBatch(), " TOUCH TO RESUME", AppSettings.SCREEN_W/3, AppSettings.SCREEN_H/2 +80);
			getStage().getSpriteBatch().draw(Assets.pummaIcon, AppSettings.SCREEN_W/3, AppSettings.SCREEN_H/2);		
		}
	}

	private void renderRunning() {
			
		gameFont.setScale(0.6f);
		gameFont.draw(getStage().getSpriteBatch(), "Score :"+World.score, 
		140*AppSettings.getWorldPositionXRatio(), AppSettings.SCREEN_H - 30*AppSettings.getWorldPositionYRatio());
		
		//GEM AND NUMBER OF GEMS COLLECTED
		getStage().getSpriteBatch().draw(Assets.snake_gem, 5, AppSettings.SCREEN_H - 30*AppSettings.getWorldPositionYRatio(), 73/2.5f*AppSettings.getWorldSizeRatio(), 68/2.5f*AppSettings.getWorldSizeRatio());
		gameFont.draw(getStage().getSpriteBatch(), " X "+world.gemsPummaHave, 30*AppSettings.getWorldPositionXRatio(), AppSettings.SCREEN_H - 5);
	    
		//EGGS AND NUMBER OF EGGS COLLECTED
		getStage().getSpriteBatch().draw(Assets.egg, 5, AppSettings.SCREEN_H - 60*AppSettings.getWorldPositionYRatio(), 300/10*AppSettings.getWorldSizeRatio(), 233/10*AppSettings.getWorldSizeRatio());
		gameFont.draw(getStage().getSpriteBatch(), " X "+world.eggsPummaHave, 30*AppSettings.getWorldPositionXRatio(), AppSettings.SCREEN_H - 30);
	    
		//Display the Health Bar here using Scene2D Actor
		healthBar.setWidth((Math.min(460, world.pumma.health/2.2f))*AppSettings.getWorldPositionXRatio());
		healthBar.draw(getStage().getSpriteBatch(), 1.0f);
		
		gameFont.draw(getStage().getSpriteBatch(), Math.min(100, world.pumma.health/10)+" %", healthBar.getX()+1.05f*healthBar.getWidth(), AppSettings.SCREEN_H - 8*AppSettings.getWorldPositionYRatio());
		   
		  //TO BE DRAWN ONLY AFTER ALL GAME ELEMENTS ARE DRAWN TO AVOID OVERRIDE
		  //DRAW THE GAME CONTROL UI ONLY ON ANDROID DEVICES IF THE GAME STATE IS RUNNING
		
				//if ((Gdx.app.getType() == ApplicationType.Android) || (Gdx.app.getType() == ApplicationType.iOS)){
					
					getStage().getSpriteBatch().draw(Assets.left_button, 0f, 0f, 0f, 0f, buttonSize, buttonSize, 1f, 1f, 0f);
					getStage().getSpriteBatch().draw(Assets.right_button, 1.2f*buttonSize, 0f, 0f, 0f, buttonSize, buttonSize, 1f, 1f, 0f);
					getStage().getSpriteBatch().draw(Assets.jump_button, AppSettings.SCREEN_W -buttonSize, 0f, 0, 0, buttonSize, buttonSize, 1, 1, 0);
					
					getStage().getSpriteBatch().draw(Assets.pause_button, AppSettings.SCREEN_W-64,AppSettings.SCREEN_H -64, 0, 0, 64, 64, 1, 1, 0);
					
				//}
	}

	private void renderReady() {
		// TODO Auto-generated method stub
		if(DEBUG_MODE){
			gameFont.draw(getStage().getSpriteBatch(), " Touch to Begin Level "+currentlevel, AppSettings.SCREEN_W/3, AppSettings.SCREEN_H/2 +80);
			getStage().getSpriteBatch().draw(Assets.logo, AppSettings.SCREEN_W/3, AppSettings.SCREEN_H/2);		
		}
	}

	private void saveGameStates() {
		// STATES ARE SAVED IN KEY AND VALUE PAIRS AS PREFERENCES
      
		//GET THE EXISTING HIGHSCORES FROM PREFERENCES
	        int[] scoresfromdb = GameData.getHighScores();
	        
	        //CHECK IF THE CURRENT SCORE IS GREATER THAN THE STORED ONE
	        if(lastScore > scoresfromdb[4])
	            scoreString = "NEW RECORD : " + lastScore;
	        else
	            scoreString = "SCORE : " + lastScore;
	        
	        //ADD THE NEW SCORE TO THE PREFERENCES IN DECREASING ORDER
	        GameData.addScore(lastScore);
			GameData.savePefs();
	}

}

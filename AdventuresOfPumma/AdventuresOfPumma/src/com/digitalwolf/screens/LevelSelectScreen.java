package com.digitalwolf.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.digitalwolf.assets.Assets;
import com.digitalwolf.gamedata.GameData;
import com.moribitotech.mtx.AbstractScreen;
import com.moribitotech.mtx.ButtonGame;
import com.moribitotech.mtx.ButtonLevel;
import com.moribitotech.mtx.MenuCreator;
import com.moribitotech.mtx.models.base.EmptyAbstractActor;
import com.moribitotech.mtx.settings.AppSettings;

public class LevelSelectScreen extends AbstractScreen {

	Table levelsTable1;
	
	private int selectedLevel;
	private String message ="Select a Level to Play";
	private ButtonGame infoButton;
	private float pad = 10*AppSettings.getWorldSizeRatio();
	private float button_size = 90*AppSettings.getWorldSizeRatio();
	 private EmptyAbstractActor testActor;
	 
	public LevelSelectScreen(Game game, String screenName) {
		super(game, screenName);
		 
		setUpScreenElements();
		setUpLevelsScreen();
	}

	public void setUpScreenElements() {
		setBackgroundTexture(Assets.bg);
		setBackButtonActive(true);
	
		testActor = new EmptyAbstractActor(50, 100, true);
		testActor.setPosition(
				100 * AppSettings.getWorldPositionXRatio()
						- testActor.getWidth() / 2.0f,
				60 * AppSettings.getWorldPositionYRatio()
						- testActor.getHeight() / 2.0f);
		getStage().addActor(testActor);		
		testActor.setAnimation(Assets.pummaStill, true, true);
        
		
		infoButton = MenuCreator.createCustomGameButton(Assets.smallFont,
				Assets.tooltip, Assets.tooltip);				
		infoButton.setSize(460*AppSettings.getWorldPositionXRatio(), 60*AppSettings.getWorldPositionYRatio());
		infoButton.setText("Select a Level to Play", true); 
         
		
		//END OF WHAT I WANTEDs
	
		infoButton.setTextPosXY(10*AppSettings.getWorldPositionXRatio(), 50*AppSettings.getWorldPositionYRatio());
		infoButton.setPosition(100*AppSettings.getWorldPositionXRatio(), 100*AppSettings.getWorldPositionYRatio());
		getStage().addActor(infoButton);
	}
	
	private void setUpLevelsScreen() {
		// Create levels table
		// ######################################################################
	    levelsTable1 = MenuCreator.createTable(true, Assets.getSkin());
	    levelsTable1.setSize(AppSettings.SCREEN_W, 2*AppSettings.SCREEN_H/3);
		levelsTable1.setPosition(-999, 0);
		levelsTable1.addAction(Actions.moveTo(0, 0, 0.7f));
		levelsTable1.top().left().pad(pad, pad, pad, pad);
		
		// Add to stage
		// ######################################################################
		getStage().addActor(levelsTable1);
		
		// Add levels buttons
		// Normally get this number from textfiles or database
		// ######################################################################
		//int numberOfLevels = Settings.NUMBER_OF_LEVELS;
		int numberOfLevels = GameData.NUMBER_OF_LEVELS;
		
		// Create buttons with a loop
		for (int i = 1; i <= numberOfLevels; i++){
			//1. Create level button
			final ButtonLevel levelButton = MenuCreator.createCustomLevelButton(Assets.smallFont,Assets.level_button1,Assets.level_button2);
			
			//final int selectedLevel =i;
			//2. Set level number
			levelButton.setLevelNumber(i , Assets.smallFont);
			
			//3. Set lock condition (get from database if it is locked or not and lock it)
			// use if/else here to lock or not
			
			if(!GameData.prefs.getBoolean("level"+i)){
				levelButton.setTextureExternal(Assets.level_locked, true);
				levelButton.setTextureExternalPosXY(60*AppSettings.getWorldPositionXRatio(), 0);
				levelButton.setTextureExternalSize(36*AppSettings.getWorldSizeRatio(), 36*AppSettings.getWorldSizeRatio());
			}
			
			
			if(GameData.prefs.getBoolean("level"+i)){
				levelButton.setLevelStars(Assets.level_star_holder, Assets.level_star, 3, GameData.getStarsEarned()[i]);
				}
			
			//4. Set stars or any other achievements (get from database or text files here)
			// I just made a random number of earned stars 
			
			
			
			
			//5. Add  listener
			//Add button listener to go to a level (gamescreen)
			
			levelButton.addListener(new ActorGestureListener() {
			@Override
				public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
					super.touchUp(event, x, y, pointer, button);
					selectedLevel = levelButton.getLevelNumber();
					
					if(GameData.getLevelInfo()[selectedLevel]){
					message = "You Selected Level "+selectedLevel;
					GameScreen.currentlevel = selectedLevel;
					getGame().setScreen(new GameScreen(getGame(), "GameScreen"));
					}
					else{
						message = "Level "+selectedLevel+" is Locked";
					}
					
					infoButton.setText(message, true);
				}
			});

			//6. Add row after each 5 level button to go down or how many do you need
			if((i-1) % 8 == 0){
				levelsTable1.row();
			}
			
			// Add to table
			levelsTable1.add(levelButton).size(button_size,button_size).pad(pad, pad, pad, pad);
		}		
	}
	
	@Override
	public void keyBackPressed() {
		super.keyBackPressed();
		getGame().setScreen(new MainMenuScreen(getGame(), "MenuScreen"));
	}
	
	@Override
	public void render(float delta) {
		super.render(delta);
		
	}

}

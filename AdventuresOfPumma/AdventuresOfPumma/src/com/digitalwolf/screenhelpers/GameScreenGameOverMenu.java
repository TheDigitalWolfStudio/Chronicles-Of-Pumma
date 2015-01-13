package com.digitalwolf.screenhelpers;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.digitalwolf.assets.Assets;
import com.digitalwolf.screens.GameScreen;
import com.digitalwolf.screens.MainMenuScreen;
import com.moribitotech.mtx.ButtonGame;
import com.moribitotech.mtx.MenuCreator;
import com.moribitotech.mtx.models.base.TableModel;
import com.moribitotech.mtx.settings.AppSettings;

public class GameScreenGameOverMenu extends GameScreenAbstractMenu{

	private TableModel gameOverMenuTable;
	private ButtonGame infoButton,scoreButton;
	
	private ButtonGame okButton, cancelButton;
	
	float buttonWidth = 174 * AppSettings.getWorldSizeRatio();
	float buttonHeight = 74 * AppSettings.getWorldSizeRatio();
	float dipRatioWidth = 80 * AppSettings.getWorldSizeRatio();
	float dipRatioHeight = 80 * AppSettings.getWorldSizeRatio();
	float padding = 10.0f * AppSettings.getWorldSizeRatio();
	
	@Override
	public void setUpMenu(final GameScreen gameScreen) {
		//Set Up means create and add the required Actors/UI Widgets to the Screen
		
		gameOverMenuTable = new TableModel(Assets.transparent,
				AppSettings.SCREEN_W, AppSettings.WORLD_HEIGHT);		
		gameOverMenuTable.setPosition(0, -gameOverMenuTable.getHeight());
		
		gameScreen.getStage().addActor(gameOverMenuTable);
		
		infoButton = MenuCreator.createCustomGameButton(Assets.bigFont,
				Assets.pummaIcon, Assets.pummaIcon, dipRatioWidth/2, dipRatioHeight, true);	
		
		infoButton.setTextPosXY(100*AppSettings.getWorldSizeRatio(), 60*AppSettings.getWorldSizeRatio());
		
		
		scoreButton = MenuCreator.createCustomGameButton(Assets.bigFont,
				Assets.level_star, Assets.timer, dipRatioWidth, dipRatioHeight, true);	
		
		scoreButton.setTextPosXY(100*AppSettings.getWorldSizeRatio(), 60*AppSettings.getWorldSizeRatio());
	 
	    
		okButton = MenuCreator.createCustomGameButton(null,
				Assets.button_continue, Assets.button_continue2);	
		okButton.addListener(new ActorGestureListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				super.touchUp(event, x, y, pointer, button);
	
				sendAwayMenu(gameScreen);
				GameScreen.state = GameScreen.GAME_READY;
				gameScreen.resetGame();
			}
		});
		
		cancelButton = MenuCreator.createCustomGameButton(null,
				Assets.button_mainmenu, Assets.button_mainmenu2);	
		cancelButton.addListener(new ActorGestureListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				super.touchUp(event, x, y, pointer, button);
				gameScreen.resetGame();
				sendAwayMenu(gameScreen);
				gameScreen.getGame().setScreen(new MainMenuScreen(gameScreen.getGame(), "MainMenuScreen"));
				
			
			}
		});
			
		gameOverMenuTable.add(infoButton).left().size(infoButton.getWidth(), infoButton.getHeight()).pad(padding);
		gameOverMenuTable.row();
		
		gameOverMenuTable.add(scoreButton).left().size(scoreButton.getWidth(), scoreButton.getHeight()).pad(padding);
		gameOverMenuTable.row();
		
		gameOverMenuTable.add(cancelButton).size(buttonWidth, buttonHeight).pad(padding).left();		
		gameOverMenuTable.add(okButton).size(buttonWidth, buttonHeight).pad(padding).right();		

	}

	@Override
	public void sendInMenu(GameScreen gameScreen) {
		// TODO Auto-generated method stub
		GameScreen.gameOverCounterForAds++;
		infoButton.setText(""+GameScreen.gameoverinfo, true);
		scoreButton.setText(""+GameScreen.scoreString, true);
		gameOverMenuTable.addAction(Actions.moveTo(0, 0, 0.5f));
	}

	@Override
	public void sendAwayMenu(GameScreen gameScreen) {
		// TODO Auto-generated method stub
		 
		gameOverMenuTable.addAction(Actions.moveTo(0,- gameOverMenuTable.getHeight(), 0.5f));
	}

}

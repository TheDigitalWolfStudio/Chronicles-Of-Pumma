package com.digitalwolf.screenhelpers;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.digitalwolf.assets.Assets;
import com.digitalwolf.gamedata.GameData;
import com.digitalwolf.screens.CongratsScreen;
import com.digitalwolf.screens.GameScreen;
import com.moribitotech.mtx.ButtonGame;
import com.moribitotech.mtx.MenuCreator;
import com.moribitotech.mtx.models.base.TableModel;
import com.moribitotech.mtx.settings.AppSettings;

public class GameScreenLevelEndMenu extends GameScreenAbstractMenu{

	private TableModel levelCompletedMenuTable;
	private ButtonGame infoButton, messageButton, okButton;
	private ButtonGame creditsBar;
	
	float dipRatioWidth = 80;
	float dipRatioHeight = 70;
	float padding = 5.0f * AppSettings.getWorldSizeRatio();
	
	@Override
	public void setUpMenu(final GameScreen gameScreen) {
		//Set Up means create and add the required Actors/UI Widgets to the Screen
		levelCompletedMenuTable = new TableModel(Assets.table_menu,
				640*AppSettings.getWorldPositionXRatio(), 2*AppSettings.WORLD_HEIGHT/2.7f);
		
		levelCompletedMenuTable.setPosition(180*AppSettings.getWorldPositionXRatio(), -levelCompletedMenuTable.getHeight());
				
				gameScreen.getStage().addActor(levelCompletedMenuTable);
				
				infoButton = MenuCreator.createCustomGameButton(Assets.bigFont,
						Assets.nothing, Assets.nothing, dipRatioWidth*5f,dipRatioHeight, true);	
				
				infoButton.setTextPosXY(30*AppSettings.getWorldPositionXRatio(), 60*AppSettings.getWorldPositionYRatio());
				
				messageButton = MenuCreator.createCustomGameButton(Assets.smallFont,
						Assets.nothing, Assets.nothing, dipRatioWidth*5f,dipRatioHeight, true);
				messageButton.setOrigin(messageButton.getWidth()/2, messageButton.getHeight()/2);
				messageButton.setTextPosXY(30*AppSettings.getWorldPositionXRatio(), 60*AppSettings.getWorldPositionYRatio());
				messageButton.setOrigin(0, 0);
				
				creditsBar = MenuCreator.createCustomGameButton(Assets.smallFont,
						Assets.health_bar, Assets.health_bar, dipRatioWidth*5.0f,dipRatioHeight/3, true);	
				creditsBar.setTextPosXY(20*AppSettings.getWorldPositionXRatio(), -10*AppSettings.getWorldPositionYRatio());
				
				okButton = MenuCreator.createCustomGameButton(null,
						Assets.right_button, Assets.right_button, dipRatioWidth, dipRatioWidth, true);	
				okButton.addListener(new ActorGestureListener() {
					@Override
					public void touchUp(InputEvent event, float x, float y,
							int pointer, int button) {
						super.touchUp(event, x, y, pointer, button);
						
						if(GameScreen.currentlevel < GameData.NUMBER_OF_LEVELS){
						GameScreen.currentlevel++;					
						GameData.addToUnLockedLevel(GameScreen.currentlevel);
						sendAwayMenu(gameScreen);
						GameScreen.state = GameScreen.GAME_READY;
						gameScreen.resetGame();
						}
						
						else{
							gameScreen.getGame().setScreen(new CongratsScreen(gameScreen.getGame(), "Congrats Screen"));
						}
						
						
					}
				});
					
				
				levelCompletedMenuTable.add(infoButton).size(infoButton.getWidth(), infoButton.getHeight()).pad(padding);
				levelCompletedMenuTable.row();
				
				levelCompletedMenuTable.add(messageButton).size(messageButton.getWidth(), messageButton.getHeight()).pad(padding);
				levelCompletedMenuTable.row();
						
				levelCompletedMenuTable.add(creditsBar).size(creditsBar.getWidth(), creditsBar.getHeight()).pad(padding);
				levelCompletedMenuTable.row();
				
				levelCompletedMenuTable.add(okButton).size(okButton.getWidth(),okButton.getHeight()).pad(padding);
	}

	@Override
	public void sendInMenu(GameScreen gameScreen) {
		infoButton.setText("Level "+GameScreen.currentlevel+" Completed", true);
		int stars = 0;
		
		if(GameScreen.creditsPoint<70)
			stars = 1;
		if(GameScreen.creditsPoint>=70 && GameScreen.creditsPoint<90)
			stars = 2;
		if(GameScreen.creditsPoint>=90)
			stars = 3;
		
		GameData.addStarsToLevel(GameScreen.currentlevel, Math.min(3, stars));
		messageButton.setText(""+GameScreen.scoreString, true);
		
		creditsBar.setWidth((GameScreen.creditsPoint*4)*AppSettings.getWorldPositionXRatio());		
		creditsBar.setText(GameScreen.creditsPoint+" %", true);
		
		levelCompletedMenuTable.addAction(Actions.moveTo(180*AppSettings.getWorldPositionXRatio(), 100*AppSettings.getWorldPositionYRatio(), 1.5f));
	}

	@Override
	public void sendAwayMenu(GameScreen gameScreen) {
		levelCompletedMenuTable.addAction(Actions.moveTo(180*AppSettings.getWorldPositionXRatio(),- levelCompletedMenuTable.getHeight(), 0.5f));
	}

}

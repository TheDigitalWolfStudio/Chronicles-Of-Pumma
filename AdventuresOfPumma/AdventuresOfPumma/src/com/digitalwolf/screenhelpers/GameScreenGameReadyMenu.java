package com.digitalwolf.screenhelpers;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.digitalwolf.assets.Assets;
import com.digitalwolf.gamedata.EggData;
import com.digitalwolf.gamedata.GemData;
import com.digitalwolf.screens.GameScreen;
import com.digitalwolf.screens.LevelSelectScreen;
import com.moribitotech.mtx.ButtonGame;
import com.moribitotech.mtx.MenuCreator;
import com.moribitotech.mtx.models.base.TableModel;
import com.moribitotech.mtx.settings.AppSettings;


public class GameScreenGameReadyMenu extends GameScreenAbstractMenu{

	private TableModel gameReadyMenuTable;
	private ButtonGame infoButton, numGemsButton,numEggsButton;
	private ButtonGame missionText;
	private ButtonGame  okButton,cancelButton;
	
	@Override
	public void setUpMenu(final GameScreen gameScreen) {
		
		gameReadyMenuTable = new TableModel(Assets.table_menu,
				AppSettings.SCREEN_W/1.3f , AppSettings.WORLD_HEIGHT/1.3f);
		gameReadyMenuTable.setPosition(100*AppSettings.getWorldPositionXRatio(), -AppSettings.SCREEN_H);
		
				infoButton = MenuCreator.createCustomGameButton(Assets.bigFont,
						Assets.transparent, Assets.transparent);	
				infoButton.setTextPosXY(20*AppSettings.getWorldPositionXRatio(), 38*AppSettings.getWorldPositionYRatio());
				
				numGemsButton = MenuCreator.createCustomGameButton(Assets.smallFont,
						Assets.snake_gem, Assets.snake_gem);				
				numGemsButton.setTextPosXY(-100*AppSettings.getWorldPositionXRatio(), 35*AppSettings.getWorldPositionYRatio());
				numGemsButton.setText(""+GemData.getGemPosition(GameScreen.currentlevel).length/2+" x ", true);
				
				missionText = MenuCreator.createCustomGameButton(Assets.smallFont, Assets.transparent, Assets.transparent);				
				missionText.setTextPosXY(20*AppSettings.getWorldPositionXRatio(), 40*AppSettings.getWorldPositionYRatio());
				
				numEggsButton= MenuCreator.createCustomGameButton(Assets.smallFont, Assets.egg, Assets.egg);				
				numEggsButton.setTextPosXY(-90*AppSettings.getWorldPositionXRatio(), 30*AppSettings.getWorldPositionYRatio());
				numEggsButton.setText(""+EggData.getEggPosition(GameScreen.currentlevel).length/2+" x ", true);
				
				cancelButton = MenuCreator.createCustomGameButton(null,
						Assets.left_button, Assets.left_button);	
				cancelButton.addListener(new ActorGestureListener() {
					@Override
					public void touchUp(InputEvent event, float x, float y,
							int pointer, int button) {
						super.touchUp(event, x, y, pointer, button);
						
						gameScreen.getGame().setScreen(new LevelSelectScreen(gameScreen.getGame(), "LevelSelect Screen"));
					}
				});
				
				okButton = MenuCreator.createCustomGameButton(null,
						Assets.right_button, Assets.right_button);	
				okButton.addListener(new ActorGestureListener() {
					@Override
					public void touchUp(InputEvent event, float x, float y,
							int pointer, int button) {
						super.touchUp(event, x, y, pointer, button);						
						sendAwayMenu(gameScreen);
						
						GameScreen.state = GameScreen.GAME_RUNNING;
						gameScreen.resetGame();
						gameScreen.setUpTheWorld();
				
					}
				});
				
				
				float dipRatioWidth = 80 * AppSettings.getWorldSizeRatio();
				float dipRatioHeight = 80 * AppSettings.getWorldSizeRatio();
				float padding = 10.0f * AppSettings.getWorldSizeRatio();
					
				gameReadyMenuTable.defaults().align(Align.bottom);	
				gameReadyMenuTable.add(infoButton).size(3.0f*dipRatioWidth, dipRatioHeight/1.5f).pad(padding).center();
				gameReadyMenuTable.row();
				
				gameReadyMenuTable.add(missionText).size(5.5f*dipRatioWidth, dipRatioHeight/1.5f);
				gameReadyMenuTable.row();
				
				gameReadyMenuTable.add(numGemsButton).size(80/2*AppSettings.getWorldSizeRatio(), 74/2*AppSettings.getWorldSizeRatio()).pad(padding);
				gameReadyMenuTable.row();
				gameReadyMenuTable.add(numEggsButton).size(80/2*AppSettings.getWorldSizeRatio(), 67/2*AppSettings.getWorldSizeRatio());
				gameReadyMenuTable.row();
				
				gameReadyMenuTable.add(cancelButton).align(Align.left).size(dipRatioWidth, dipRatioHeight);
				gameReadyMenuTable.add(okButton).align(Align.right).size(dipRatioWidth, dipRatioHeight);	
				
	}

	@Override
	public void sendInMenu(final GameScreen gameScreen) {
		// TODO Auto-generated method stub
			missionText.setText("Collect at least ..", true);
			
		
		gameScreen.getStage().addActor(gameReadyMenuTable);
		
		infoButton.setText("LEVEL : "+GameScreen.currentlevel, true);
		gameReadyMenuTable.addAction(Actions.moveTo(100*AppSettings.getWorldPositionXRatio(), 60*AppSettings.getWorldPositionYRatio(), 0.5f));
	}

	@Override
	public void sendAwayMenu(final GameScreen gameScreen) {
		// TODO Auto-generated method stub
		gameReadyMenuTable.addAction(Actions.moveTo(100*AppSettings.getWorldPositionXRatio(),- gameReadyMenuTable.getHeight(), 0.5f));
	}

}

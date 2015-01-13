package com.digitalwolf.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.digitalwolf.assets.Assets;
import com.digitalwolf.gamedata.GameData;
import com.moribitotech.mtx.AbstractScreen;
import com.moribitotech.mtx.ButtonGame;
import com.moribitotech.mtx.IScreen;
import com.moribitotech.mtx.MenuCreator;
import com.moribitotech.mtx.models.base.EmptyAbstractActor;
import com.moribitotech.mtx.models.base.TableModel;
import com.moribitotech.mtx.models.base.Text;
import com.moribitotech.mtx.settings.AppSettings;

public class HighScoresScreen extends AbstractScreen implements IScreen{

	TableModel tableMenu;
	float dipRatioWidth = AppSettings.WORLD_WIDTH/2.5f;
	float dipRatioHeight = 50 * AppSettings.getWorldSizeRatio();
	float padding = 2.0f * AppSettings.getWorldSizeRatio();
	ButtonGame btnHome;
	EmptyAbstractActor player;
	
	public HighScoresScreen(Game game, String screenName) {
		super(game, screenName);	
		setUpScreenElements();
		setUpInfoPanel();
		setUpActors();
		setUpMenu();
	}

	@Override
	public void setUpScreenElements() {
		setBackgroundTexture(Assets.bg);
		setBackButtonActive(true);	
	}

	@Override
	public void setUpInfoPanel() {

		// Home Button
		btnHome = MenuCreator.createCustomGameButton(null,
				Assets.left_button, Assets.left_button, 64, 64 ,true);
		
		btnHome.setPosition(AppSettings.SCREEN_W - btnHome.getWidth(), AppSettings.SCREEN_H-btnHome.getHeight());
		btnHome.addListener(new ActorGestureListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				super.touchUp(event, x, y, pointer, button);
				setAwayMenu();
				
				getGame().setScreen(new MainMenuScreen(getGame(), "MainMenu"));
				
			}
		});
		
		getStage().addActor(btnHome);
	}

	private void setUpActors() {
		player = new EmptyAbstractActor(40, 80, true);
		player.setPosition(
				100 * AppSettings.getWorldPositionXRatio()- player.getWidth() / 2.0f,
				100 * AppSettings.getWorldPositionYRatio() - player.getHeight() / 2.0f);
		getStage().addActor(player);
		
		player.setAnimation(Assets.pummaStill, true, true);
		
		ButtonGame text = MenuCreator.createCustomGameButton(Assets.smallFont,
				Assets.tooltip, Assets.tooltip, dipRatioWidth/1.3f, dipRatioHeight*1.2f, true);
		text.setPosition(player.getX(),player.getY()+player.getHeight());
		text.setText("High Scores ", true);
		text.setTextPosXY(30, 50);
		
		getStage().addActor(text);
	}
	
	@Override
	public void setUpMenu() {
		// #03.2 Test
		// Menu table with sliding animation
		// // #######################################
		tableMenu = new TableModel(Assets.table_menu,
				528*AppSettings.getWorldPositionXRatio() , 454*AppSettings.getWorldPositionYRatio());			
		tableMenu.setPosition(AppSettings.SCREEN_W - 1.1f*tableMenu.getWidth(), AppSettings.SCREEN_H + tableMenu.getHeight());
		tableMenu.addAction(Actions.moveTo(AppSettings.SCREEN_W - 1.1f*tableMenu.getWidth(), AppSettings.SCREEN_H - 1.1f*tableMenu.getHeight(), 0.5f));

		for(int i=0; i<5;i++){
			addScoreButton(i);
		}
		getStage().addActor(tableMenu);
	}

	public void setAwayMenu() {
		tableMenu.addAction(Actions.moveTo(AppSettings.SCREEN_W - 1.1f*tableMenu.getWidth(), AppSettings.SCREEN_H + tableMenu.getHeight(), 0.5f));
	}
	
	private void addScoreButton(int i){
		
		Text scoreText = new Text(Assets.smallFont, dipRatioWidth,dipRatioHeight, true);
		
		scoreText.setText((i+1)+") "+"  "+GameData.getHighScores()[i]);
		scoreText.setOrigin(0, 0);
		
		tableMenu.add(scoreText).size(dipRatioWidth, dipRatioHeight).pad(padding);
		tableMenu.row();
		}
	
	@Override
	public void keyBackPressed() {
		super.keyBackPressed();
		getGame().setScreen(
				new MainMenuScreen(getGame(), "MenuScreen"));
	}
}

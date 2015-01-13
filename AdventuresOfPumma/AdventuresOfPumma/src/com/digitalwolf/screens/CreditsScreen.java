package com.digitalwolf.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.digitalwolf.assets.Assets;
import com.moribitotech.mtx.AbstractScreen;
import com.moribitotech.mtx.ButtonGame;
import com.moribitotech.mtx.MenuCreator;
import com.moribitotech.mtx.models.base.EmptyAbstractActor;
import com.moribitotech.mtx.models.base.TableModel;
import com.moribitotech.mtx.settings.AppSettings;

public class CreditsScreen extends AbstractScreen implements Screen{

	 private EmptyAbstractActor testActor;
	 private TableModel tableMenu;
	 
	 public CreditsScreen(Game game, String screenName) {
		super(game, screenName);
		
		// TODO Auto-generated constructor stub
		setUpScreenElements();
		setUpInfoPanel();
		setUpActors();
		setUpMenu();
	}

	private void setUpMenu() {
		// TODO Auto-generated method stub

		
	    testActor = new EmptyAbstractActor(Assets.transparent_credit.getRegionWidth(), 
				Assets.transparent_credit.getRegionHeight(), true);
		testActor.setTextureRegion(Assets.transparent_credit, true);
		
		tableMenu = new TableModel(null,
				AppSettings.WORLD_WIDTH , AppSettings.WORLD_HEIGHT);
		
		tableMenu.defaults().space(100);
		
		tableMenu.add(testActor);
 	
         // put the table inside a scrollpane  
         ScrollPane scrollPane = new ScrollPane(tableMenu);  
         scrollPane.setBounds(0, 0, AppSettings.SCREEN_W, AppSettings.SCREEN_H-80);  
         scrollPane.setScrollingDisabled(true, false);  
         scrollPane.setOverscroll(false, false);  
         scrollPane.invalidate();  
         getStage().addActor(scrollPane);  
	}

	private void setUpActors() {
		// TODO Auto-generated method stub
		
	}

	private void setUpInfoPanel() {
		// Add the Back Button 
		// Back Button
				final ButtonGame btnBack = MenuCreator.createCustomGameButton(null,
						Assets.left_button, Assets.left_button, 64, 64,
						true);
				btnBack.setPosition(AppSettings.WORLD_WIDTH - btnBack.getWidth(),
						AppSettings.WORLD_HEIGHT - btnBack.getHeight());
				btnBack.addListener(new ActorGestureListener() {
					@Override
					public void touchUp(InputEvent event, float x, float y,
							int pointer, int button) {
						super.touchUp(event, x, y, pointer, button);
						tableMenu.addAction(Actions.moveTo(0, -tableMenu.getHeight(), 0.3f));
						getGame().setScreen(new MainMenuScreen(getGame(), "MainMenu"));
					}
				});
				
			getStage().addActor(btnBack);
			}

	private void setUpScreenElements() {
		// SET UP THE BACKGROUND AND MAKE THE BACK BUTTON ACTIVE
		setBackgroundTexture(Assets.bg);
		setBackButtonActive(true);
	}
   
     @Override  
     public void render(float delta) {  
    	 getStage().act(delta);  
         getStage().draw();  
     }

}

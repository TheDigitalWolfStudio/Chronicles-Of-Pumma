package com.digitalwolf.adventuresOfPumma;

/*
 * Chronicles of Pumma is a 2D Open Source game written in LibGDX by Digital Wolf Studio. This game is meant for the
 * purpose of learning. You may use the source code to create your own games.
 */
/*
 * This is the Main class of the Project. AdventuresOfPumma extends the Game class.
 */

import com.badlogic.gdx.Game;
import com.digitalwolf.assets.Assets;
import com.digitalwolf.gamedata.GameData;
import com.digitalwolf.screens.MainMenuScreen;
import com.moribitotech.mtx.SettingsManager;
import com.moribitotech.mtx.settings.AppSettings;
import com.moribitotech.mtx.settings.MtxLogger;

public class AdventuresOfPumma extends Game {
	
	 public AdventuresOfPumma() {
		  //THIS IS NOT IMPLEMENTED TO BE CALLED BY DESKTOP LAUNCHER 
	 }
	 
	@Override
	public void create() {
		
		//Set Up the Application
		AppSettings.setUp();
		
		//If the Application is launched for the first time, create preferences to store game data such as Array of top 5
		//Highscores, timer mode  and sound settings
		
		if(SettingsManager.isFirstLaunch()){
			SettingsManager.setFirstLaunchDone(true);
			MtxLogger.log(true, true, "LAUNCH", "THIS IS FIRST LAUNCH");
			GameData.createPrefs();
			GameData.saveLevelInfo();
			GameData.saveStarsEarnedInfo();
		}
		else{
			MtxLogger.log(true, true, "LAUNCH", "THIS IS NOT FIRST LAUNCH");
			if(GameData.prefs == null)
			GameData.createPrefs();
			
			GameData.addToUnLockedLevel(1);
		}
		
		// Load assets before setting the screen
		// #####################################
		Assets.loadAll();
	
		// Set up the main menu screen
		// #####################################
		setScreen(new MainMenuScreen(this, "MainMenu Screen"));
	}
	
	@Override
	public void resume(){
		Assets.loadAll();
	}

}

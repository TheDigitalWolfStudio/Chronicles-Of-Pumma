package com.digitalwolf.gamedata;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class GameData {

	
	/*
	This class contains static methods needed to save the various game states like Top 5 Scores, Sound and Timer Mode Settings
	 */
	
	 public static int NUMBER_OF_LEVELS =16;
	
	 private static int[] highscores = new int[] { 500, 100, 50, 20, 10 };
	 
	    public static boolean[] levelUnLocked = new boolean[]
	    	{
	    	true, true, false,false,false,false,false,false,false,
	    	false, false,false,false,false,false,false,false
	    	};
	    
	    public static int[] starsEarned = new int[]
		    	{
		    	0, 0, 0,0,0,0,0,0,0,
		    	0, 0,0,0,0,0,0,0
		    	};
	    
	    private static final String PREFS_FILE_NAME = "chroniclesofpumma2";
	    public static Preferences prefs;
	
		public static void createPrefs(){
			 prefs = Gdx.app.getPreferences(PREFS_FILE_NAME);
			 
		}
	    
		public static void savePefs(){
			 for(int i = 0; i < 5; i++) {
		         prefs.putInteger("highscores"+i, highscores[i]);
		     }
			 
			 prefs.flush();
		    }

		
		//THIS METHOD SAVES THE TOP 5 HIGHSCORES IN THE ARRAY highscores[i] UNDER THE KEY highscores1 .. 5
	  
	    public static int[] getHighScores(){
	    	for(int i = 0; i < 5; i++) {
	    		highscores[i] = prefs.getInteger("highscores"+i);
	         }
		    prefs.flush();
	        return highscores;
	    }
	    
	    
	   //THIS METHOD SAVES THE TOP 5 HIGHSCORES IN THE ARRAY highscores[i] UNDER THE KEY highscores1 .. 5
	    public static void saveLevelInfo(){
		 for(int i = 1; i <= NUMBER_OF_LEVELS ; i++) {
	         prefs.putBoolean("level"+i, levelUnLocked[i]);
	     }
		 prefs.flush();
	    }
	    
	    public static boolean[] getLevelInfo(){
	    	for(int i = 1; i <= NUMBER_OF_LEVELS; i++) {
	    		levelUnLocked[i] = prefs.getBoolean("level"+i);
	         }
	    	 prefs.flush();
	        return levelUnLocked;
	    }
	    
	    
	    // THIS METHOD TAKES THE CURRENT SCORE IF THIS IS GREATER THAN THE PREVIOUS ACHIEVEMENTS AND SAVE IT TO THE 
	    //STATIC ARRAY OF highcores[i] IN DECREASING ORDER
	    public static void addScore(int score) {
	        for(int i=0; i < 5; i++) {
	            if(highscores[i] < score) {
	                for(int j= 4; j > i; j--)
	                    highscores[j] = highscores[j-1];
	                highscores[i] = score;
	                break;
	            }
	        }
	        prefs.flush();
	    }
	   

		public static void addToUnLockedLevel(int levelID) {
	    	prefs.putBoolean("level"+levelID, true);
	    	prefs.flush();
	    }
	    
		public static void addStarsToLevel(int levelID, int stars) {
	    	prefs.putInteger("starsEarned"+levelID, stars);
	    	prefs.flush();
	    }
		
	    public static void setSoundEnabled(boolean soundOn){
	    	 prefs.putBoolean("soundEnabled", soundOn);
	         prefs.flush();
	    }
		
	    
	    public static int[] getStarsEarned(){
	    	for(int i = 1; i <= NUMBER_OF_LEVELS; i++) {
	    		starsEarned[i] = prefs.getInteger("starsEarned"+i, 0);
	         }
	    	 prefs.flush();
	        return starsEarned;
	    }
	    
		public static boolean getSoundEnabled() {
			// TODO Auto-generated method stub
			return prefs.getBoolean("soundEnabled",true);
		}

		public static void saveStarsEarnedInfo() {
			// TODO Auto-generated method stub
			 for(int i = 1; i <= NUMBER_OF_LEVELS ; i++) {
		         prefs.putInteger("starsEarned"+i, starsEarned[i]);
		     }
			 prefs.flush();
		}
}

package com.digitalwolf.gamedata;

public class PlayerData {

	private static float[][] playerPosition1 = {
		{2, 4f}
		};
	
	private static float[][] playerPosition2 = {
		{2, 4f}
		
		};
	
	private static float[][] playerPosition3 = {
		{2,4f}
		};
	
	
	private static float[][] playerPosition4 = {
		{2f,4f}
		};
	
	private static float[][] playerPosition5 = {
		{2,4f}
		};	
	
	private static float[][] playerPosition6 = {
		{2f,4f}
		};
	
	
	
	public static float[][] getPlayerPosition(int levelID) {
		switch(levelID){
		
		case 1:
			return playerPosition1;
		
		case 2:
			return playerPosition2;
			
		case 3:
			return playerPosition3;			
		
	    case 4:
		return playerPosition4;			
	    
        case 5:
	    return playerPosition5;	
	    
        case 6:
		    return playerPosition6;	
	}	
		return playerPosition1;
	}
}

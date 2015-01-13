package com.digitalwolf.gamedata;

public class SnakeData {

	private static float[][] snakePosition1 = {
		{12, 14.7f},{28f, 3.7f},{134f, 3.7f}
		};
	
	private static float[][] snakePosition2 = {
		{55,3.7f},{21,3.7f}
		
		};
	
	private static float[][] snakePosition3 = {
		{25,11.7f},
		{14,3.7f},{80f,3.7f},		
		};
	
	
	private static float[][] snakePosition4 = {
		{4.5f,15.7f},
		{72f,8.7f},{26f,3.7f}
		};
	
	private static float[][] snakePosition5 = {
		{78, 3.7f},{108f,3.7f},
		};	
	
	private static float[][] snakePosition6 = {
		{4.5f,8.7f},{17.8f,7.7f},{63.6f,8.7f}
		};
	
	private static float[][] snakePosition7 = {
		{40f,3.7f},{80f,0.7f},{163f,1.7f}
		};
	
	private static float[][] snakePosition8 = {
		{168f,7.7f},{194f,0.7f},{122f,0.7f},{89f,5.7f},
		};
	
	private static float[][] snakePosition9 = {
		{136f,3.7f},{20f,1.7f},{57f,5.7f}
		};
	
	private static float[][] snakePosition10 = {
		{22f,9.7f},{97f,13.7f},{56f,5.7f}
		};
	
	private static float[][] snakePosition11 = {
		{68f,13.7f},{129f,3.7f},
		};
	
	private static float[][] snakePosition12 = {
		{44f,3.7f},{112f,3.7f},{80f,13.7f}
		};
	
	private static float[][] snakePosition13 = {
		{54f,3.7f},{80f,3.7f}
		};
	
	private static float[][] snakePosition14 = {
		{108f,3.7f},{53f,3.7f},{34f,11.7f}
		};
	
	private static float[][] snakePosition15 = {
		{56f,12.7f},{113f,7.7f},{81f,3.7f}
		};
	
	private static float[][] snakePosition16 = {
		{28f,3.7f},{136f,3.7f}
		};
	
	
	public static float[][] getSnakePosition(int levelID) {
		switch(levelID){
		
		case 1:
			return snakePosition1;
		
		case 2:
			return snakePosition2;
			
		case 3:
			return snakePosition3;			
		
	    case 4:
		return snakePosition4;			
	    
        case 5:
	    return snakePosition5;	
	    
        case 6:
		    return snakePosition6;	
		    
        case 7:
		    return snakePosition7;	
		    
        case 8:
		    return snakePosition8;
		    
        case 9:
		    return snakePosition9;
		    
        case 10:
		    return snakePosition10;
		    
        case 11:
		    return snakePosition11;
		    
        case 12:
		    return snakePosition12;
		    
        case 13:
		    return snakePosition13;
		    
        case 14:
		    return snakePosition14;
		    
        case 15:
		    return snakePosition15;
		    
        case 16:
		    return snakePosition16;
	}	
		return snakePosition1;
	}
	

	
}

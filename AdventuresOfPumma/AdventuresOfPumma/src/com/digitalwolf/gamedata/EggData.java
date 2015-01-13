package com.digitalwolf.gamedata;

public class EggData {

	private static float[][] eggPosition1 = {
		{3.0f,15f},{118f, 10f},
		
		};
	
	private static float[][] eggPosition2 = {
		{1, 16f},{21,4},{35,8}
		};
	
	private static float[][] eggPosition3 = {
		{24,4f},{41,17f}
		};
	
	
	private static float[][] eggPosition4 = {
		{2,16f},
		{72f,9f}
		};
	
	private static float[][] eggPosition5 = {
		{21,4f},{92f,10f},{51f,8f},{85f,6f},
		
		};

	
	private static float[][] eggPosition6 = {
		{17.8f,12},{33.7f,2}
		};
	
	private static float[][] eggPosition7 = {
		{29f,4f},{66f,6f},{78f,6f},{104f,1f},{122f,1f},{189f,10f},
		};
	
	private static float[][] eggPosition8 = {
		{122.5f,7f},{83f,1f},{2f,16f},{87f,17f},{198f,16f}
		};
	
	private static float[][] eggPosition9 = {
		{1f,12f},{80f,3f}
		};
	
	private static float[][] eggPosition10 = {
		{2f,10f},{54f,9f}
		};
	
	private static float[][] eggPosition11 = {
		{66.4f,4f},{5.5f,14f}
		};
	
	private static float[][] eggPosition12 = {
		{57f,4f},{97f,14f}
		};
	
	private static float[][] eggPosition13 = {
		{42f,8f},{2f,16f},{79f,8f},{146f,15f}
		};
	
	private static float[][] eggPosition14 = {
		{17f,16f},{76f,13f},{62f,4f},{123f,11f}
		};
	
	private static float[][] eggPosition15 = {
		{112f,12f},{1f,16f},{49f,2f},{71f,10f}
		};
	
	private static float[][] eggPosition16 = {
		{1f,13f},{74f,4f},
		};
	
	//
	public static float[][] getEggPosition(int levelID) {
		switch(levelID){
		
		case 1:
			return eggPosition1;
		
		case 2:
			return eggPosition2;
			
		case 3:
			return eggPosition3;			
		
	    case 4:
		return eggPosition4;			
	    
        case 5:
	    return eggPosition5;
	    
        case 6:
		    return eggPosition6;	
		    
        case 7:
		    return eggPosition7;	
		    
        case 8:
		    return eggPosition8;	
        
        case 9:
		    return eggPosition9;
		    
        case 10:
		    return eggPosition10;	
		    
        case 11:
		    return eggPosition11;	
		    
        case 12:
		    return eggPosition12;	
		    
        case 13:
		    return eggPosition13;	
		    
        case 14:
		    return eggPosition14;	
		    
        case 15:
		    return eggPosition15;	
		    
        case 16:
		    return eggPosition16;	
	}	
		return eggPosition1;
	}


}

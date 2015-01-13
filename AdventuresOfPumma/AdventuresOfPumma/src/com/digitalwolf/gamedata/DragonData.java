package com.digitalwolf.gamedata;

public class DragonData {

	private static float[][] dragonPosition1 = {
		{41,6.6f}, {148f, 16f}
		};
	
	private static float[][] dragonPosition2 = {
		{82,16f},{40f,9f}
		};
	
	private static float[][] dragonPosition3 = {
		{80,16f}, {34f,16f},{53.7f,4.4f}
		};


	private static float[][] dragonPosition4 = {
		{24.5f,11.5f},{72,16f}
		};

    private static float[][] dragonPosition5 = {
    	{37f,16.2f},
			};

	private static float[][] dragonPosition6 = {
				{33.7f,2}
				};

	private static float[][] dragonPosition7 = {
		{46f,15.7f},{183f,12f}
		};
	
	private static float[][] dragonPosition8 = {
		{24f,14f},{125f,11f},{179f,11f},
	};
	
	private static float[][] dragonPosition9 = {
		{17f,16f},{40f,9f},{104f,10f}
	};
	
	private static float[][] dragonPosition10 = {
		{69f,13f},{122f,14f}
	};
	
	
	private static float[][] dragonPosition11 = {
		{96f,9f},{7f,16f},{59f,9f}
	};
	
	private static float[][] dragonPosition12 = {
		{136f,17f},{53f,9.5f}
	};
	
	private static float[][] dragonPosition13 = {
		{31.5f,14.5f},{85f,17f},{135f,12f},{108f,8f}
	};
	
	private static float[][] dragonPosition14 = {
		{13f,7f},{37f,8f},{102f,15f},{77f,9f},{132f,9f}
	};
	
	private static float[][] dragonPosition15 = {
		{17f,10f},{126f,8f},{100f,10f}
	};
	
	private static float[][] dragonPosition16 = {
		{50f,16f},{127f,16f},{102f,11f}
	};
	//
	public static float[][] getDragonPosition(int levelID) {
		// TODO Auto-generated method stub
		switch(levelID){
		case 1:
			return dragonPosition1;
		
		case 2:
			return dragonPosition2;
			
		case 3:
			return dragonPosition3;			
		
	    case 4:
		return dragonPosition4;			
	    
        case 5:
	    return dragonPosition5;	
	    
        case 6:
		    return dragonPosition6;	 
		    
        case 7:
		    return dragonPosition7;	 
		    
        case 8:
		    return dragonPosition8;	 
		    
        case 9:
		    return dragonPosition9;	 
		    
        case 10:
		    return dragonPosition10;	 
		    
        case 11:
		    return dragonPosition11;	 
		    
        case 12:
		    return dragonPosition12;	 
		    
        case 13:
		    return dragonPosition13;	 
		    
        case 14:
		    return dragonPosition14;	 
		    
        case 15:
		    return dragonPosition15;	 
		    
        case 16:
		    return dragonPosition16;	 
	}	
		return dragonPosition1;
	}
}

package com.digitalwolf.gamedata;

public class SpringData {

	private static float[][] springPosition1 = {
	};
	
	private static float[][] springPosition2 = {
		{78f,4f},{14.5f,4f}
		};
	
	private static float[][] springPosition3 = {
		{0.3f,4f},{78f,4f}
		};
	
	private static float[][] springPosition4 = {
		{37.3f,4f},{32f,4f}
		};
	
	private static float[][] springPosition5 = {
		{14f,4f},{97f,4f}
		};
	
	private static float[][] springPosition6 = {
		{75f,4f}
		};
	
	private static float[][] springPosition7 = {
		{62f,1f},
		};
	
	private static float[][] springPosition8 = {
		
		};
	
	private static float[][] springPosition9 = {
		{36f,6f},{48.5f,6f},{140f,4f}
		};
	
	private static float[][] springPosition10 = {
		{33f,2f},{110f,4f}
		};
	
	private static float[][] springPosition11 = {
		{114f,4f},
		};
	
	private static float[][] springPosition12 = {
		{140f,7f},{10f,4f}
		};
	
	private static float[][] springPosition13 = {
		{108f,4f},{38f,4f},{136f,4f}
		};
	
	private static float[][] springPosition14 = {
		{21f,4f}
		};
	
	private static float[][] springPosition15 = {
		{88f,4f}
		};
	
	private static float[][] springPosition16 = {
		{33f,4f},{57.6f,3f}
		};
	
	//ACCESSOR METHOD
	public static float[][] getSpringPosition(int levelID) {
		// TODO Auto-generated method stub
		
		switch(levelID){
		case 1:
			return springPosition1;
		
		case 2:
			return springPosition2;
			
		case 3:
			return springPosition3;			
		
	    case 4:
		return springPosition4;			
	    
        case 5:
	    return springPosition5;	
	    
        case 6:
		    return springPosition6;	
		    
        case 7:
		    return springPosition7;	
		    
        case 8:
		    return springPosition8;	
		    
        case 9:
		    return springPosition9;	
		    
        case 10:
		    return springPosition10;	
		    
        case 11:
		    return springPosition11;	
		    
        case 12:
		    return springPosition12;	
		    
        case 13:
		    return springPosition13;	
		    
        case 14:
		    return springPosition14;	
		    
        case 15:
		    return springPosition15;	
		    
        case 16:
		    return springPosition16;	
        
	}	
		return springPosition1;
	}
}

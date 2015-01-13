package com.digitalwolf.gamedata;

public class KeyData {

	private static float[][] keyPosition1 = {
		{156.5f, 14f}
		};
	
	private static float[][] keyPosition2 = {
		{86,16}
		};
	
	private static float[][] keyPosition3 = {
		{86f,17f}
		};
	
	private static float[][] keyPosition4 = {
		{80,17f}
		};
	
	private static float[][] keyPosition5 = {
		{52,16f}
		};
	
	
	private static float[][] keyPosition6 = {
		{79f,5f}
		};
	
	private static float[][] keyPosition7 = {
		{188f,16f}
		};
	
	private static float[][] keyPosition8 = {
		{186f,1f}
		};
	
	private static float[][] keyPosition9 = {
		{146f,14f}
		};
	
	private static float[][] keyPosition10 = {
		{141.5f,8f}
		};
	
	private static float[][] keyPosition11 = {
		{94f,5f}
		};
	
	private static float[][] keyPosition12 = {
		{146f,16f}
		};
	
	private static float[][] keyPosition13 = {
		{88.5f,15f}
		};
	
	private static float[][] keyPosition14 = {
		{73f,7f}
		};
	
	private static float[][] keyPosition15 = {
		{29f,2f}
		};
	
	private static float[][] keyPosition16 = {
		{62f,10f}
		};
	//
	public static float[][] getKeyPosition(int levelID) {
		// TODO Auto-generated method stub
		switch(levelID){
	case 1:
		return keyPosition1;
	
	case 2:
		return keyPosition2;
		
	case 3:
		return keyPosition3;			
	
    case 4:
	return keyPosition4;			
    
    case 5:
    return keyPosition5;	
    
    case 6:
	    return keyPosition6;	
	    
    case 7:
	    return keyPosition7;	
	    
    case 8:
	    return keyPosition8;	
	    
    case 9:
	    return keyPosition9;
	    
    case 10:
	    return keyPosition10;
	    
    case 11:
	    return keyPosition11;
	    
    case 12:
	    return keyPosition12;
	    
    case 13:
	    return keyPosition13;
	    
    case 14:
	    return keyPosition14;
	    
    case 15:
	    return keyPosition15;
	    
    case 16:
	    return keyPosition16;
}	
	return keyPosition1;
}

}

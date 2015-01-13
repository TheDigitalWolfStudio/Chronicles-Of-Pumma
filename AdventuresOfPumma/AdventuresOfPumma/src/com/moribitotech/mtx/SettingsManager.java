/*******************************************************************************
 * Copyright 2012-Present, MoribitoTech
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.moribitotech.mtx;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.files.FileHandle;
import com.moribitotech.mtx.settings.MtxLogger;

public class SettingsManager {
	// Litte warning fix (NOTHING FOR, FIX THIS LATER)
	static  String currentLineee = "";
	
	// File TYPE
	public enum FileType {
		INTERNAL_FILE,
		LOCAL_FILE,
		EXTERNAL_FILE
	}
	
	// Public values
	public static final String PREFS_FILE_NAME = "MyPreferences";
	public static final Preferences prefs = Gdx.app.getPreferences(PREFS_FILE_NAME);
	public static final String VALUE_NOT_SET = "ValueNotSet";
	public static final String ON = "onSetting";
	public static final String OFF = "offSetting";
	
	// First Launch
	private static final String KEY_FIRST_LAUNCH_DONE = "firstLaunch";
	private static final String FIRST_LAUNCH_DONE = "trueDone";
	
	// General Settings

	private static final String KEY_MUSIC = "musicSetting";
	private static final String KEY_SOUND = "soundEffectSetting";
	private static final String KEY_VIBRATION = "vibrationSetting";
	
	// General Settings  (For public use)
	public static boolean isSoundOn = false;
	public static boolean isMusicOn = false;
	public static boolean isVibrationOn = false;
	
	/**
	 * Read lines from text file. Lines starts from 1 (It will be fixed to 0 soon)
	 * 
	 * @param strFile file to read 
	 * @param lineNumber line number to read, starts from 1
	 * @param fileType the type of file to retrieve file (INTERNAL, LOCAL, EXTERNAL)
	 * */
	public static String readLine(String strFile, int lineNumber, FileType fileType) {
		// Identify file type and get storage location
		FileHandle file = getFile(strFile, fileType);
		BufferedReader reader = new BufferedReader(new InputStreamReader(file.read()));
		String currentLine = null;
		int counter = 0;
		try {
			while ((currentLine = reader.readLine()) != null) {
				counter++;
				if (counter == lineNumber) {
					 Gdx.app.log("SettingLog", "READ LINE: " + currentLine);	
					break;
				}
			}
			reader.close();
		} catch (IOException e) {
			 Gdx.app.log("SettingLog", "CANT READ LINE: File: " + strFile + ", Line Number: " + lineNumber);	
			 e.printStackTrace();
		}

		return currentLine;
	}

	/**
	 * Re-Write an existing line in a text file without effecting other lines
	 * 
	 * @param strFile file to write 
	 * @param lineNumber line number to write, starts from 1
	 * @param newValue the new value to write over existing line
	 * @param fileType the type of file to retrieve file (INTERNAL, LOCAL, EXTERNAL)
	 * */
	public static void writeExistingLine(String strFile, int lineNumber, String newValue, FileType fileType) {
        try {
        	FileHandle file = getFile(strFile, fileType);
            ArrayList<String> lineByLineTextList = getUpdatedTextInfo(strFile, lineNumber, newValue);
            FileWriter fw = new FileWriter(file.file(),false);
            BufferedWriter bw = new BufferedWriter(fw);  
                for(int i = 0; i < lineByLineTextList.size(); i++){
                	if(lineByLineTextList.get(i) != null){
                    	bw.write(lineByLineTextList.get(i));
                    	bw.newLine();
                	}
                }            
                bw.close();
         } catch (IOException e) {
			 Gdx.app.log("SettingLog", "CANT WRITE LINE: File: " + strFile + ", Line Number: " + lineNumber);	
             e.printStackTrace();
         }		  
	}
	
	/**
	 * If a line consist of comma separated values, it returns each value in ArrayList
	 * 
	 * @param strFile file to read 
	 * @param lineNumber line number to read, starts from 1
	 * @param fileType the type of file to retrieve file (INTERNAL, LOCAL, EXTERNAL)
	 * */
	public static ArrayList<String> getValuesSeperatedByCommaInLine(String strFile, int lineNumber, FileType fileType){	
		String lineString = readLine(strFile, lineNumber, fileType);
		ArrayList<String> values = new ArrayList<String>(Arrays.asList(lineString.split(",")));
		return values;
	}
	
	
	private static ArrayList<String> getUpdatedTextInfo(String strFile, int lineNumber, String newValue) {
		ArrayList<String> lineByLineTextList = new ArrayList<String>();
		FileHandle file = Gdx.files.local(strFile);
		BufferedReader reader = new BufferedReader(new InputStreamReader(file.read()));
		String currentLine = null;
		int counter = 0;
		try {
			while ((currentLine = reader.readLine()) != null) {
				counter++;
				if (counter == lineNumber) {
					 Gdx.app.log("SettingLog", "WRITE EXISTING LINE: OLD:" + currentLine + "NEW: " + newValue);	
					lineByLineTextList.add(newValue);
				} else{
					lineByLineTextList.add(currentLine);
				}
			}
			reader.close();
		} catch (IOException e) {
		}
		return lineByLineTextList;
	}
	
	/**
	 * Write new lines in text file. Lines starts from 1 (It will be fixed to 0 soon)
	 * 
	 * @param strFile file to write 
	 * @param lineNumber line number to read, starts from 1
	 * @param fileType the type of file to retrieve file (INTERNAL, LOCAL, EXTERNAL)
	 * */
	public static void writeLine(String strFile, String newValue, FileType fileType) {
        try {
        	FileHandle file = getFile(strFile, fileType);
            FileWriter fw = new FileWriter(file.file(),true);
            BufferedWriter bw = new BufferedWriter(fw);  
            bw.write(newValue);
            bw.newLine();
            bw.close();
         } catch (IOException e) {
			 Gdx.app.log("SettingLog", "CANT WRITE LINE: File: " + strFile);	
             e.printStackTrace();
         }
	}
	
	/**
	 * Get a android preferences, if it is not set it returns def value
	 * */
	public static String getStringPrefValue(String key, String defValue){
		MtxLogger.log(true, true, "MtxSettingManager", "Pref (Key: " + key + "): " +   prefs.getString(key, defValue));
		return prefs.getString(key, defValue);
		
	}
	
	/**
	 * Get a android preferences, if it is not set it returns def value
	 * */
	public static Boolean getBooleanPrefValue(String key, boolean defValue){
		MtxLogger.log(true, true, "MtxSettingManager", "Pref (Key: " + key + "): " + prefs.getBoolean(key, defValue));
		 return prefs.getBoolean(key, defValue);
	}
	
	/**
	 * Set a android preferences
	 * */
	public static void setBooleanPrefValue(String key, boolean value){
		prefs.putBoolean(key, value);
		prefs.flush();
	}
	
	/**
	 * Set a android preferences
	 * */
	public static void setStringPrefValue(String key, String value){
		prefs.putString(key, value);
		prefs.flush();
	}

	/**
	 * Create a file in a LOCAL storage. Good place the store game data in text files
	 * */
	public static void createTextFileInLocalStorage(String fileName){
		// Get local storage
		String localDir = Gdx.files.getLocalStoragePath();
		
		// Create files
		try {
			new FileWriter(localDir + fileName);
			Gdx.app.log("SettingLog", "TXT FILE CREATED: " + fileName);	
		} catch (IOException e) {
			 Gdx.app.log("SettingLog", "CANT CREATE TEXT FILE: File: " + fileName);	
			e.printStackTrace();
		}
	}
	

	/**
	 * Get number of lines in a text file
	 * */
	public static int getNumberOflInesInTextFile(String strFile, FileType fileType){
		FileHandle file = getFile(strFile, fileType);
		BufferedReader reader = new BufferedReader(new InputStreamReader(file.read()));
		String currentLine = null;
		currentLineee = currentLine;
		int counter = 0;
		try {
			while ((currentLine = reader.readLine()) != null) {
				counter++;
			}
			reader.close();
		} catch (IOException e) {
			 e.printStackTrace();
		}
		
		 Gdx.app.log("SettingLog", "NUMBER OF LINES: " + file.name() + ": " + counter);	
		 return counter;	
	}
	

	/**
	 * Set as first launch for the game. It saves to Android Preferences.
	 * 
	 *@param isFirstLaunchDone value to set as first launch
	 * */
	public static void setFirstLaunchDone(boolean isFirstLaunchDone){
		if(isFirstLaunchDone){
			setStringPrefValue(KEY_FIRST_LAUNCH_DONE, FIRST_LAUNCH_DONE);
			Gdx.app.log("SettingLog", "SETTED AS FIRST LAUNCH OF THE GAME");	
		}
		else{
			setStringPrefValue(KEY_FIRST_LAUNCH_DONE, VALUE_NOT_SET);
		}
	}
	
	/**
	 * Check the game if it is first launch or not, it is helpful to create first launch files
	 * which should be created once. It can be checked in every game launch. To set as first launch use
	 * the "setFirstLaunchDone" method
	 * 
	 *@see setFirstLaunchDone(boolean isFirstLaunchDone);
	 * */
	public static boolean isFirstLaunch(){
		if(getStringPrefValue(KEY_FIRST_LAUNCH_DONE, VALUE_NOT_SET).equals(VALUE_NOT_SET)){
			Gdx.app.log("SettingLog", "FIRST LAUNCH FOR THE DEVICE");	
			return true;
		} else if(getStringPrefValue(KEY_FIRST_LAUNCH_DONE, VALUE_NOT_SET).equals(FIRST_LAUNCH_DONE)){
			Gdx.app.log("SettingLog", "NOT FIRST LAUNCH FOR THE DEVICE (PREVIOUSLY SETTED AS FIRST LAUNCH)");	
			return false;
		} else {
			Gdx.app.log("SettingLog", "NOT FIRST LAUNCH (NOTHING SET ABOUTH FIRST LAUNCH)");	
			return false;
		}
	}
	
	/**
	 * Set music
	 * */
	public static void setMusic(boolean isMusicActive){
		if(isMusicActive){
			setStringPrefValue(KEY_MUSIC, ON);
			isMusicOn = true;
		}
		else{
			setStringPrefValue(KEY_MUSIC, OFF);
			isMusicOn = false;
		}
	}
	
	/**
	 * Get if the music is set true or false
	 * 
	 * @return condition of music
	 * */
	public static boolean getMusicCondtionFromPreferences(){
		if(getStringPrefValue(KEY_MUSIC, VALUE_NOT_SET).equals(ON))
			return true;	
		else 
			return false;
	}
	
	
	/**
	 * Set sound
	 * */
	public static void setSound(boolean isSoundActive){
		if(isSoundActive){
			setStringPrefValue(KEY_SOUND, ON);
			isSoundOn = true;
		}else{
			setStringPrefValue(KEY_SOUND, OFF);
			isSoundOn = false;
		}
	}
	
	/**
	 * Get if the sound is set true or false
	 * 
	 * @return condition of sound
	 * */
	public static boolean getSoundCondtionFromPreferences(){
		if(getStringPrefValue(KEY_SOUND, VALUE_NOT_SET).equals(ON))
			return true;	
		else 
			return false;
	}
	
	/**
	 * Set vibration
	 * */
	public static void setVibration(boolean isVibrationActive){
		if(isVibrationActive){
			setStringPrefValue(KEY_VIBRATION, ON);
			isVibrationOn  = true;
		}else{
			setStringPrefValue(KEY_VIBRATION, OFF);	
			isVibrationOn = false;
		}
	}
	
	/**
	 * Get if the vibration is set true or false
	 * 
	 * @return condition of vibration
	 * */
	public static boolean getVibrationCondtionFromPreferences(){
		if(getStringPrefValue(KEY_VIBRATION, VALUE_NOT_SET).equals(ON))
			return true;	
		else 
			return false;
	}
	
	
	 /**
	  * Set general settings. Use this method in every game launch. It gets values from preferences
	  * if they are not set, it sets as false (OFF)
	  * <p>
	  * SETTINGS EFFECTED<br>
	  * Music<br>
	  * Sound<br>
	  * Vibration<br>
	  */
	public static void setGeneralSettings(){		
		// Set general settings
		setMusic(getMusicCondtionFromPreferences());
		setSound(getSoundCondtionFromPreferences());
		setVibration(getVibrationCondtionFromPreferences());
	}
	
	/**
	 * Get file from one of the storages, there are three storages, INTERNAL (Read Only), LOCAL, EXTERNAL (SD CARD)
	 * 
	 * @param strFile file name to retrieve
	 * @param fileType file type for location identification
	 * @return the file
	 * */
	private static FileHandle getFile(String strFile, FileType fileType){
		FileHandle file = null;
		if(fileType == FileType.INTERNAL_FILE){
			try {
				file = Gdx.files.internal(strFile);
			} catch (Exception e) {
				 Gdx.app.log("SettingLog", "!!! FILE IS NOT INTERNAL OR NOT EXIST: " + strFile);	
			}
		} else if(fileType == FileType.LOCAL_FILE){
			try {
				file = Gdx.files.local(strFile);
			} catch (Exception e) {
				 Gdx.app.log("SettingLog", "!!! FILE IS NOT LOCAL OR NOT EXIST: " + strFile);	
			}
		} else if(fileType == FileType.EXTERNAL_FILE){
			try {
				file = Gdx.files.external(strFile);
			} catch (Exception e) {
				 Gdx.app.log("SettingLog", "!!! FILE IS NOT EXTERNAL OR NOT EXIST: " + strFile);	
			}
		}
		return file;
	}

}

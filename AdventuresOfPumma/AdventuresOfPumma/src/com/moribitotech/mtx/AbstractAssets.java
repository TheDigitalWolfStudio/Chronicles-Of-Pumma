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

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public abstract class AbstractAssets {
	private static AssetManager assetManager;
	private static Skin skin;
	
	public static AssetManager getAssetManager() {
		return assetManager;
	}

	public static void setAssetManager(AssetManager assetManager) {
		AbstractAssets.assetManager = assetManager;
	}

	public static Skin getSkin() {
		return skin;
	}

	public static void setSkin(Skin skin) {
		AbstractAssets.skin = skin;
	}

	/**
	 * Play sound effect, it will only play if SettingsManager.isSoundOn is true
	 * 
	 * @param sound to play
	 * @param volume is the volume setting (Range [0.0 - 1.0])
	 * @see SettingsManager.isSoundOn 
	 * 
	 * */
	public static void playSound(Sound sound, float volume){
		if(SettingsManager.isSoundOn){
			sound.play(volume);
		}
	}
	
	/**
	 * Play music, it will only play if SettingsManager.isMusicOn is true
	 * 
	 * @param music to play
	 * @param isLooping to loop or not
	 * @param volume is the volume setting (Range [0.0 - 1.0])
	 * @see SettingsManager.isMusicOn 
	 * 
	 * */
	public static void playMusic(Music music, boolean isLooping, float volume){
		if(SettingsManager.isMusicOn){
			music.setLooping(isLooping);
			music.setVolume(volume);
			music.play();
		}
	}
}

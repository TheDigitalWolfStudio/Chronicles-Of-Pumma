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

package com.moribitotech.mtx.settings;

import com.badlogic.gdx.Gdx;
import com.moribitotech.mtx.AbstractActor;
import com.moribitotech.mtx.AbstractGameManager;
import com.moribitotech.mtx.AbstractScreen;
import com.moribitotech.mtx.AbstractWorldScene2d;
import com.moribitotech.mtx.InputIntent;

public class MtxLogger {
	private static boolean isMasterLoggerActive;

	/**
	 * Set logs that you want to see, or kill all logs with master logger
	 * */
	private static void setLogs() {
		// Master log (To show or not to show any log)
		isMasterLoggerActive = true;

		// Rest of individual logs
		AppSettings.Log_Active = true;
		AbstractScreen.logActive = true;
		AbstractGameManager.logActive = true;
		AbstractWorldScene2d.logActive = true;
		AbstractActor.logActive = true;

		//
		InputIntent.logActive = false;
	}

	/**
	 * Log something
	 * */
	public static void log(boolean objectLoggerActive,
			boolean methodLoggerActive, String tag, String log) {
		// Manual log settings
		setLogs();

		// Log
		if (isMasterLoggerActive && objectLoggerActive && methodLoggerActive) {
			Gdx.app.log(tag, log);
		}
	}
}

package com.digitalwolf.screenhelpers;

import com.digitalwolf.screens.GameScreen;

abstract class GameScreenAbstractMenu {

	abstract void setUpMenu(final GameScreen gameScreen);
	
	abstract void sendInMenu(final GameScreen gameScreen);

	abstract void sendAwayMenu(final GameScreen gameScreen);
}

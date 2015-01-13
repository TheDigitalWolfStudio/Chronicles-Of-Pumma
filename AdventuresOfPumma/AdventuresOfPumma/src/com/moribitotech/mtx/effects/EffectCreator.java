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

package com.moribitotech.mtx.effects;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.moribitotech.mtx.settings.MtxLogger;

public class EffectCreator {
	private static final String logTag = "MtxEffectCreator";
	public static boolean logActive = true;

	//
	// COMMAND SHORTCUTS
	// ################################################################
	//
	// SC - Scale
	// BTN - Back To Normal
	// FI - Fade In
	// FO - Fade Out
	// SHK - Shake

	//
	// SINGLE EFFECTS
	// ################################################################
	/**
	 * Scale effect (SC)
	 * */
	public static void create_SC(Actor actor, float scaleRatioX,
			float scaleRatioY, float duration, final Stage stage,
			final boolean isRemoveActor) {
		if (actor != null) {
			actor.addAction(Actions.sequence(
					Actions.scaleTo(scaleRatioX, scaleRatioY, duration),
					new Action() {
						@Override
						public boolean act(float delta) {
							if (isRemoveActor) {
								removeActor(stage, actor);
								return false;
							} else {

								return true;
							}
						}
					}));
		}
	}

	/**
	 * Fade Out (FO)
	 * */
	public static void create_FO(Actor actor, float duration,
			final Stage stage, final boolean isRemoveActor) {
		if (actor != null) {
			actor.addAction(Actions.sequence(Actions.fadeOut(duration),
					new Action() {
						@Override
						public boolean act(float delta) {
							if (isRemoveActor) {
								removeActor(stage, actor);
								return false;
							} else {
								return true;
							}
						}
					}));
		}
	}

	/**
	 * Shake effect (SHK)
	 * */
	public static void create_SHK(Actor actor, float shakeAngle,
			float originalAngle, float duration, final Stage stage,
			final boolean isRemoveActor) {
		if (actor != null) {
			actor.addAction(Actions.sequence(
					Actions.rotateTo(shakeAngle, duration),
					Actions.rotateTo(-shakeAngle, duration),
					Actions.rotateTo(originalAngle, duration), new Action() {
						@Override
						public boolean act(float delta) {
							if (isRemoveActor) {
								removeActor(stage, actor);
								return false;
							} else {
								return true;
							}
						}
					}));
		}
	}

	//
	// DOUBLE EFFECTS (Sequence - Chain reaction)
	// ################################################################
	/**
	 * Scale effect and Back to normal (SC, BTN)
	 * */
	public static void create_SC_BTN(Actor actor, float scaleRatioX,
			float scaleRatioY, float duration, final Stage stage,
			final boolean isRemoveActor) {
		if (actor != null) {
			actor.addAction(Actions.sequence(
					Actions.scaleTo(scaleRatioX, scaleRatioY, duration),
					Actions.scaleTo(1, 1, duration), new Action() {
						@Override
						public boolean act(float delta) {
							if (isRemoveActor) {
								removeActor(stage, actor);
								return false;
							} else {
								return true;
							}
						}
					}));
		}
	}

	/**
	 * Scale effect, Fade Out (SC, FO)
	 * */
	public static void create_SC_FO(Actor actor, float scaleRatioX,
			float scaleRatioY, float duration, float delayBeforeFadeOut,
			final Stage stage, final boolean isRemoveActor) {
		if (actor != null) {
			actor.addAction(Actions.sequence(
					Actions.scaleTo(scaleRatioX, scaleRatioY, duration),
					Actions.delay(delayBeforeFadeOut),
					Actions.fadeOut(duration), new Action() {
						@Override
						public boolean act(float delta) {
							if (isRemoveActor) {
								removeActor(stage, actor);
								return false;
							} else {
								return true;
							}
						}
					}));
		}
	}

	/**
	 * Scale effect, Shake effect (SC, SHK)
	 * */
	public static void create_SC_SHK(Actor actor, float scaleRatioX,
			float scaleRatioY, float shakeAngle, float originalAngle,
			float duration, final Stage stage, final boolean isRemoveActor) {
		if (actor != null) {
			actor.addAction(Actions.sequence(
					Actions.scaleTo(scaleRatioX, scaleRatioY, duration),
					Actions.rotateTo(shakeAngle, duration),
					Actions.rotateTo(-shakeAngle, duration),
					Actions.rotateTo(originalAngle, duration), new Action() {
						@Override
						public boolean act(float delta) {
							if (isRemoveActor) {
								removeActor(stage, actor);
								return false;
							} else {
								return true;
							}
						}
					}));
		}
	}

	//
	// TRIPLE EFFECTS (Sequence - Chain reaction)
	// ################################################################
	/**
	 * Scale effect, Back To Normal, Fade Out (SC, BTN, FO)
	 * */
	public static void create_SC_BTN_FO(Actor actor, float scaleRatioX,
			float scaleRatioY, float duration, float delayBeforeFadeOut,
			final Stage stage, final boolean isRemoveActor) {
		if (actor != null) {
			actor.addAction(Actions.sequence(
					Actions.scaleTo(scaleRatioX, scaleRatioY, duration),
					Actions.scaleTo(1, 1, duration),
					Actions.delay(delayBeforeFadeOut),
					Actions.fadeOut(duration), new Action() {
						@Override
						public boolean act(float delta) {
							if (isRemoveActor) {
								removeActor(stage, actor);
								return false;
							} else {

								return true;
							}
						}
					}));
		}
	}

	/**
	 * Scale effect, Shake effect, Back To Normal (SC, SHK, BTN)
	 * */
	public static void create_SC_SHK_BTN(Actor actor, float scaleRatioX,
			float scaleRatioY, float shakeAngle, float originalAngle,
			float duration, final Stage stage, final boolean isRemoveActor) {
		if (actor != null) {
			actor.addAction(Actions.sequence(
					Actions.scaleTo(scaleRatioX, scaleRatioY, duration),
					Actions.rotateTo(shakeAngle, duration),
					Actions.rotateTo(-shakeAngle, duration),
					Actions.rotateTo(originalAngle, duration),
					Actions.scaleTo(1, 1, duration), new Action() {
						@Override
						public boolean act(float delta) {
							if (isRemoveActor) {
								removeActor(stage, actor);
								return false;
							} else {

								return true;
							}
						}
					}));
		}
	}

	private static void removeActor(Stage stage, Actor actor) {
		if (stage != null && actor != null) {
			actor.clearActions();
			String actorName = actor.getName();
			if (stage.getRoot().removeActor(actor)) {
				MtxLogger.log(logActive, true, logTag, "Actor removed! (Name: "
						+ actorName + ")");
			} else {
				MtxLogger.log(logActive, true, logTag,
						"Actor not removed! (Name: " + actorName + ")");
			}
		}
	}
}

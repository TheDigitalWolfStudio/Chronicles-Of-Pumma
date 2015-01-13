package com.digitalwolf.assets;

/*
 *This is the Assets class. All the graphics (Textures) and Audio are loaded in this class.  
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.digitalwolf.gamedata.GameData;

public class Assets {

    private final static String FILE_SPRITE_ATLAS = "data/sprite_sheet.sprites";	
	private final static String FILE_UI_SKIN = "skin/uiskin.json";

	public static TextureAtlas spriteAtlas;
	public static Skin skin;

	//Defining the Audio Files here
	public static Music music;
	public static Sound egggrab;
	public static Sound gemgrab;
	public static Sound cry;
	public static Sound ow;
	public static Sound success;
	public static Sound fall;
	
	//Defining the Trexture Regions
	public static Texture likeButton;
	public static TextureRegion rateOnGooglePlay;
	public static Texture star, starHolder;
	public static TextureRegion level_star,level_star_holder;
	public static TextureRegion bg;
	
	public static TextureRegion left_button;
	public static TextureRegion right_button;
	public static TextureRegion jump_button;
	public static TextureRegion level_button1, level_button2,level_locked;
	public static TextureRegion logo;
	
	public static TextureRegion pumma;
	public static TextureRegion snake;
	public static TextureRegion dragon;
	public static TextureRegion pause_button;
	public static TextureRegion transparent_credit;
	public static TextureRegion transparent;
	public static TextureRegion tooltip;
	public static TextureRegion timer;
	
	
	public static TextureRegion button_continue;
	public static TextureRegion button_continue2;
	public static TextureRegion button_credits;
	public static TextureRegion button_credits2;
	public static TextureRegion button_exit;
	public static TextureRegion button_exit2;
	public static TextureRegion button_highscore;
	public static TextureRegion button_highscore2;
	public static TextureRegion button_mainmenu;
	public static TextureRegion button_mainmenu2;
	public static TextureRegion button_on;
	public static TextureRegion button_off;
	public static TextureRegion default_button1;
	public static TextureRegion default_button2;
	public static TextureRegion table_menu;
	public static TextureRegion startgame_button;
	public static TextureRegion startgame_button2;
	public static TextureRegion congrats_screen;
	public static TextureRegion snake_gem;
	public static TextureRegion egg;
	public static TextureRegion key;
	
	public static TextureRegion health_bar;
	public static TextureRegion stone,menubox,snake2;
	
	public static Animation pummaStill;
	public static Animation pummaWalk;
	public static Animation pummaJump;
	
	public static TextureRegion snakeIcon;
	public static Animation snakeStill;
	public static Animation snakeMove;
	
	public static TextureRegion dragonIcon,pummaIcon;
	public static Animation dragonStill;
	public static Animation dragonFlying;
	
    public static TextureRegion spring1;
    public static TextureRegion spring2;
    public static TextureRegion spring3;
    public static TextureRegion door;
    
	public static Animation springActive;
	
	public static BitmapFont smallFont, bigFont;
	public static TextureRegion world2;
	public static TextureRegion nothing;
	

	public static Texture loadTexture(String file) {
		return new Texture(Gdx.files.internal(file));
	}

	
	public static TextureAtlas getSpriteAtlas() {
		if (spriteAtlas == null) {
			spriteAtlas = new TextureAtlas(
					Gdx.files.internal(FILE_SPRITE_ATLAS));
		}
		return spriteAtlas;
	}
	
	public static Skin getSkin() {
		if (skin == null) {
			FileHandle skinFile = Gdx.files.internal(FILE_UI_SKIN);
			skin = new Skin(skinFile);
		}
		return skin;
	}

	public static void loadAll() {
		relaseResources();
		loadImages();
		loadButtons();
		loadFonts();
		loadAnimations();
		loadSoundsAndMusics();
	}

	private static void relaseResources() {
		skin = null;
		spriteAtlas = null;
//		starHolder.dispose();
//		star.dispose();
//		likeButton.dispose();
	}

	public static void loadImages() {
				
		//TEXTURE REGIONS FROM THE SPRITE ATLAS
		likeButton = loadTexture("data/rateButton.png");
		rateOnGooglePlay  = new TextureRegion(likeButton, 0,0, 125, 125);	
		
		star = loadTexture("data/star.png");
		starHolder = loadTexture("data/starHolder.png");
		level_star =  new TextureRegion(star, 0,0, 66, 66);	
		level_star_holder =  new TextureRegion(starHolder, 0,0, 66, 66);	
		
		bg = getSpriteAtlas().findRegion("bg");
		
		tooltip = getSpriteAtlas().findRegion("tooltip");
		timer = getSpriteAtlas().findRegion("timer");
		logo = getSpriteAtlas().findRegion("logo");
		
		pumma = getSpriteAtlas().findRegion("pumma_sheet");
		snake = getSpriteAtlas().findRegion("snake");
		key = getSpriteAtlas().findRegion("key");
		dragon = getSpriteAtlas().findRegion("dragon");
		egg = getSpriteAtlas().findRegion("egg");
		door  = getSpriteAtlas().findRegion("Door");
		
		pause_button = getSpriteAtlas().findRegion("button_pause");
		transparent_credit = getSpriteAtlas().findRegion("transparent_credit");
		transparent = getSpriteAtlas().findRegion("transparent");
		
		//Fair Buttons Created by me at www.cooltext.com
		button_continue = getSpriteAtlas().findRegion("button_continue");
		button_continue2 = getSpriteAtlas().findRegion("button_continue2");
		button_credits = getSpriteAtlas().findRegion("button_credits");
		button_credits2 = getSpriteAtlas().findRegion("button_credits2");
		button_exit = getSpriteAtlas().findRegion("button_exit");
		button_exit2 = getSpriteAtlas().findRegion("button_exit2");
		button_highscore = getSpriteAtlas().findRegion("button_highscore");
		button_highscore2 = getSpriteAtlas().findRegion("button_highscore2");
		button_mainmenu = getSpriteAtlas().findRegion("button_mainmenu");
		button_mainmenu2 = getSpriteAtlas().findRegion("button_mainmenu2");
		button_on = getSpriteAtlas().findRegion("button_on");
		button_off = getSpriteAtlas().findRegion("button_off");
		default_button1 = getSpriteAtlas().findRegion("default_button1");
		default_button2 = getSpriteAtlas().findRegion("default_button2");
		table_menu = getSpriteAtlas().findRegion("table_menu");
		startgame_button = getSpriteAtlas().findRegion("startgame_button");
		startgame_button2 = getSpriteAtlas().findRegion("startgame_button2");
		
		congrats_screen = getSpriteAtlas().findRegion("congrats_screen");
		snake_gem = getSpriteAtlas().findRegion("snake_gem");
		
		health_bar = getSpriteAtlas().findRegion("health_bar");
		stone  = getSpriteAtlas().findRegion("stone");
		
		spring1  = getSpriteAtlas().findRegion("spring1");
		spring2  = getSpriteAtlas().findRegion("spring2");
		spring3  = getSpriteAtlas().findRegion("spring3");
		
		world2 = getSpriteAtlas().findRegion("world2");
		snake2 = getSpriteAtlas().findRegion("snake2");
		menubox = getSpriteAtlas().findRegion("menubox");
		}

	public static void loadButtons() {

		left_button = getSpriteAtlas().findRegion("left_button");
		right_button = getSpriteAtlas().findRegion("right_button");
		jump_button = getSpriteAtlas().findRegion("jump_button");
		level_button1 = getSpriteAtlas().findRegion("level_button1");
		level_button2 = getSpriteAtlas().findRegion("level_button2");
		level_locked = getSpriteAtlas().findRegion("level_locked");
		nothing = getSpriteAtlas().findRegion("nothing");
		
	}

	public static void loadFonts() {
		//
		smallFont = new BitmapFont(Gdx.files.internal("data/smallFont.fnt"),
				Gdx.files.internal("data/smallFont.png"), false);
		
		bigFont = new BitmapFont(Gdx.files.internal("data/bigFont.fnt"),
				Gdx.files.internal("data/bigFont.png"), false);
	}

	public static void loadAnimations() {
		//Loading the Dragon
		
        TextureRegion[][] regions2 = Assets.pumma.split(32, 64);
		
        pummaWalk = new Animation(0.15f, regions2[0][0], regions2[0][1],regions2[1][4], regions2[0][2],
        		regions2[0][3],regions2[0][4], regions2[0][5],regions2[0][6]);
        pummaWalk.setPlayMode(Animation.LOOP_PINGPONG);
		
        pummaStill = new Animation(0.15f,regions2[1][2], regions2[1][3]);
        pummaStill.setPlayMode(Animation.LOOP_PINGPONG);	
        
        pummaJump = new Animation(0.5f,regions2[1][0], regions2[1][1]);
     
        pummaIcon = regions2[1][2];
        
        TextureRegion[][] regions = snake.split(384/4, 60);
		
        snakeStill = new Animation(0.5f,regions[0][0], regions[0][1]);
        snakeStill.setPlayMode(Animation.LOOP_PINGPONG);
        
        snakeMove = new Animation(0.15f,regions[0][0], regions[0][1], regions[0][2], regions[0][3]);
        snakeMove.setPlayMode(Animation.LOOP_PINGPONG);
        
        snakeIcon = regions[0][0];
        
        TextureRegion[][] dragonRegions = dragon.split(384/4, 384/4);
        
        dragonStill = new Animation(0.3f,dragonRegions[0][0], dragonRegions[0][3]);
        dragonStill.setPlayMode(Animation.LOOP_PINGPONG);
        
        dragonFlying = new Animation(0.15f,dragonRegions[0][0], dragonRegions[0][1] ,dragonRegions[0][2], dragonRegions[0][3]);
        dragonFlying.setPlayMode(Animation.LOOP_PINGPONG);
        
        dragonIcon = dragonRegions[0][0];
        
        springActive = new Animation(0.15f,spring3, spring1, spring2);
        springActive.setPlayMode(Animation.LOOP_PINGPONG);
	}

	public static void loadSoundsAndMusics() {
		music = Gdx.audio.newMusic(Gdx.files.internal("data/music.mp3"));
		music.setLooping(true);
		//Start Playing the Music if the sound is enabled
		if (GameData.getSoundEnabled()) Assets.music.play();
				
		egggrab = Gdx.audio.newSound(Gdx.files.internal("data/egggrab.ogg"));
		gemgrab = Gdx.audio.newSound(Gdx.files.internal("data/gemgrab.ogg"));
		cry = Gdx.audio.newSound(Gdx.files.internal("data/cry.ogg"));
		ow = Gdx.audio.newSound(Gdx.files.internal("data/ow.ogg"));
		success = Gdx.audio.newSound(Gdx.files.internal("data/success.ogg"));
		fall = Gdx.audio.newSound(Gdx.files.internal("data/fall.ogg"));

	}
	
	public static void playSound (Sound sound) {
		if (GameData.getSoundEnabled()) sound.play(1);
	}
}

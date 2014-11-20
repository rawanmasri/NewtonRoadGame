package sw.newtonroad.game;

import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import sw.newtonroad.handlers.AppVar;
import sw.newtonroad.manegers.GameState;
import sw.newtonroad.tween.ActorAccessor;

public class Menu extends GameState{
	private Stage stage;
	private Skin skin ;
	//private Table basicTable;
	//private Table menuTable;
	private OrthographicCamera camera;
	private Texture texture;
	//private Sprite sprite;
	private	SpriteBatch batch;
	//private TextureRegion region; 
	private TweenManager tweenManager;
	//StudioTest t;

	public Menu(GameStateManager gsm) {
		super(gsm);
		camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        batch=new SpriteBatch();
	}

	@Override
	public void init() {
		stage=new Stage();
		skin=new Skin(Gdx.files.internal("gameui/menueSkin.jason"),new TextureAtlas("gameui/gameui.pack"));
		Gdx.input.setInputProcessor(stage);
		texture = new Texture(Gdx.files.internal("gamebackground.png"));
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		Label label=new Label("Game Test",skin,"large");
		label.setPosition(Gdx.graphics.getWidth()/12f, Gdx.graphics.getHeight()/1.4f);
		TextButton playButton=new TextButton("PLAY",skin,"menu");//Create Balls
		playButton.setWidth(Gdx.graphics.getWidth()/8f);
		playButton.setHeight(Gdx.graphics.getHeight()/9f);
		playButton.setPosition(Gdx.graphics.getWidth()/2f, Gdx.graphics.getHeight()/2f);
		TextButton settingButton=new TextButton("SETTING",skin,"menu");//Create Boxes
		settingButton.setWidth(Gdx.graphics.getWidth()/8f);
		settingButton.setHeight(Gdx.graphics.getHeight()/9f);
		settingButton.setPosition(Gdx.graphics.getWidth()/2f, Gdx.graphics.getHeight()/2.7f);
		TextButton helpButton=new TextButton("HELP",skin,"menu");//Create Boxes
		helpButton.setWidth(Gdx.graphics.getWidth()/8f);
		helpButton.setHeight(Gdx.graphics.getHeight()/9f);
		helpButton.setPosition(Gdx.graphics.getWidth()/2f, Gdx.graphics.getHeight()/4.2f);
		playButton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				gsm.setState(GameStateManager.LEVELS,0,0);
		
			}
				
			});
		tweenManager =new TweenManager();
		
		stage.addActor(label);
		stage.addActor(playButton);
		stage.addActor(settingButton);
		stage.addActor(helpButton);
		Tween.registerAccessor(Actor.class, new ActorAccessor());
		//heading color animation 
		Timeline.createSequence().beginSequence()
		.push(Tween.to(label, ActorAccessor.RGB, .5f).target(0.545098f,0.270588f,0.0745098f))
		.push(Tween.to(label, ActorAccessor.RGB, .5f).target(0.545098f,0.270588f,0.0745098f))
		.push(Tween.to(label, ActorAccessor.RGB, .5f).target(0.803922f, 0.521569f, 0.247059f))
		.push(Tween.to(label, ActorAccessor.RGB, .5f).target(0.803922f, 0.521569f, 0.247059f))
		.push(Tween.to(label, ActorAccessor.RGB, .5f).target(0.823529f,0.411765f,0.117647f))
		.push(Tween.to(label, ActorAccessor.RGB, .5f).target(0.823529f,0.411765f,0.117647f))
		.push(Tween.to(label, ActorAccessor.RGB, .5f).target( 0.721569f, 0.52549f, 0.0431373f))
		.push(Tween.to(label, ActorAccessor.RGB, .5f).target( 0.721569f, 0.52549f, 0.0431373f))
		.end().repeat(Tween.INFINITY, 0).start(tweenManager);
		//heading and buttons fade-in 
		Timeline.createSequence().beginSequence()
		.push(Tween.set(playButton, ActorAccessor.ALPHA).target(0))
		.push(Tween.set(settingButton, ActorAccessor.ALPHA).target(0))
		.push(Tween.set(helpButton, ActorAccessor.ALPHA).target(0))
		.push(Tween.from(label, ActorAccessor.ALPHA, .25f).target(0))
		.push(Tween.to(playButton, ActorAccessor.ALPHA, .25f).target(1))
		.push(Tween.to(settingButton, ActorAccessor.ALPHA, .25f).target(1))
		.push(Tween.to(helpButton, ActorAccessor.ALPHA, .25f).target(1))
		.end().start(tweenManager);
		//Table fade-in
		//Tween.from(table, ActorAccessor.ALPHA, .5f).target(0).start(tweenManager);
		//Tween.from(table, ActorAccessor.Y, .5f).target(Gdx.graphics.getHeight()/8).start(tweenManager);
	}

	@Override
	public void update(float dt) {
		// Gdx.gl.glClearColor( 1f, 1f, 1f, 1f );
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); 
			tweenManager.update( dt);
			camera.update();
		    batch.setProjectionMatrix(camera.combined);
		    batch.begin();
		    batch.draw(texture,0, 0);
		    batch.end();
		    //tweenManager.update(dt);
		    stage.act(dt);
		    stage.draw();
		
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handlingInput() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	 public void resizee(int width, int height) {
			camera.viewportWidth=width/25;
			camera.viewportHeight=height/25;
			camera.update();
		}

}

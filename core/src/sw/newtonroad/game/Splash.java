package sw.newtonroad.game;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import sw.newtonroad.manegers.GameState;
import sw.newtonroad.tween.SpriteAccessor;

public class Splash extends GameState {
	private SpriteBatch batch;
	private Sprite splash;
	private TweenManager tweenManager;
	private Game game;
	private OrthographicCamera camera;
	private Texture texture;

	public Splash(GameStateManager gsm) {
		super(gsm);
		camera = new OrthographicCamera();
        camera.setToOrtho(false, 600,400);
        batch=new SpriteBatch();
	}

	@Override
	public void init() {
		batch=new SpriteBatch();
		texture = new Texture(Gdx.files.internal("splash.png"));
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		tweenManager=new TweenManager();
		Tween.registerAccessor(Sprite.class, new SpriteAccessor());
		Texture splashTexture=new Texture("Title.png");
		splash=new Sprite(splashTexture);
		splash.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Tween.set(splash, SpriteAccessor.ALPHA).target(0).start(tweenManager);
		Tween.to(splash, SpriteAccessor.ALPHA, 2).target(1).repeatYoyo(1, 2).setCallback(new TweenCallback() {
			
			@Override
			public void onEvent(int arg0, BaseTween<?> arg1) {
				gsm.setState(GameStateManager.MENU,0,0);
				
			}
		}).start(tweenManager);
		
		
	}

	@Override
	public void update(float dt) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); 
		tweenManager.update(dt);
		 batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(texture,0, 0);
		splash.draw(batch);
		batch.end();
		//stage.act(dt);
	    //stage.draw();
		
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
		batch.dispose();
		splash.getTexture().dispose();
		
	}
	public void resizee(int width,int height){
		splash.setSize(width, height);
	}

}

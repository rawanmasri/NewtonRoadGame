package sw.newtonroad.manegers;

import sw.newtonroad.game.GameStateManager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class TestGameScreen extends Game {
	private GameStateManager gsm;
	int w;
	int h;

	@Override
	public void create() {
		gsm=new GameStateManager();
		
	}
	@Override
	public void render(){
		
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.resize(w,h);
		gsm.draw();
	}
	 @Override
	    public void resize(int width, int height) {
		 w=width;
		 h=height;

			gsm.resize(width, height);
		}

}

package sw.newtonroad.game;

import sw.newtonroad.game.Game;
import sw.newtonroad.game.Menu;
import sw.newtonroad.game.Splash;
import sw.newtonroad.manegers.GameState;

public class GameStateManager {
	private GameState gamestate;
	public static final int MENU=0;
	public static final int PLAY=1;
	public static final int SPLASH=2;
	public static final int LEVELS=3;
	int state;
	public GameStateManager(){
		setState(SPLASH,0,0);
		
	}
	public void setState(int state,int value,int lastLevel){
		if(gamestate!=null)gamestate.dispose();
		if (state==SPLASH){
			gamestate=new Splash(this);
		}
		 if (state==MENU){
			
			gamestate=new Menu(this);
		}
		 if (state==LEVELS){
				gamestate=new LevelsScreen(this);
			}
		 if(state==PLAY){
			//System.out.println(value);
			//gamestate=new PlayState(this);
			 gamestate=new Game(this,value,lastLevel);
		}
		this.state=state;
	}
	public void  update(float dt){
		gamestate.update(dt);
		//System.out.println(gamestate);
	}
	public void draw(){
		gamestate.draw();
	}
	public void resize(int width,int height){
		if(state==SPLASH){
			((Splash)gamestate).resizee(width, height);
		}
		if(state==PLAY){
		((Game)gamestate).resizee(width, height);
		}
	}
}

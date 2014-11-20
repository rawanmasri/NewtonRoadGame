package sw.newtonroad.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import sw.newtonroad.game.Game;
import sw.newtonroad.handlers.AppVar;
import sw.newtonroad.manegers.TestGameScreen;
public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title=AppVar.TITLE;
		config.width=AppVar.V_WIDTH*AppVar.SCALE;
		config.height=AppVar.V_HIGHT*AppVar.SCALE;
		new LwjglApplication(new TestGameScreen(), config);
	}
}

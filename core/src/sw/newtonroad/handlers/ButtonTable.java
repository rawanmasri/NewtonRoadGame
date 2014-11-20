package sw.newtonroad.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

public class ButtonTable {
	private Skin skin ;
	private Table table;
	private TextButtonStyle textButtonStyle; 
	//Initialize table
	public ButtonTable(String textureAtlas,int boundX,int boundY,int positionX,int positionY) {
		TextureAtlas atlas=new TextureAtlas(textureAtlas);
		 skin=new Skin(atlas);
		table=new Table(skin);
		table.setBounds(0, 0,boundX , boundY);
		table.setPosition(positionX, positionY);
		table.setBackground((Drawable) new NinePatchDrawable(getNinePatch(("n.png"))));
		textButtonStyle=new TextButtonStyle();
		
	}

	
	 public TextButtonStyle getTextButtonStyle(){
		 return textButtonStyle;
	 }
	public TextButton createButton(String up,String down,int pressedX,int pressedY,BitmapFont font){
		 textButtonStyle.up=skin.getDrawable(up);
		 textButtonStyle.down=skin.getDrawable(down);
		 textButtonStyle.pressedOffsetX=pressedX;
		 textButtonStyle.pressedOffsetY=pressedY;
	     textButtonStyle.font=font;
		TextButton textButton=new TextButton("", textButtonStyle);
		return textButton;
	}
	public Table addButton(TextButton textButton,int width,int height){
		table.add(textButton).width(width).height(height);
		return table;
		
	}
	private NinePatch getNinePatch(String fname) {
		// Get the image
		final Texture t = new Texture(Gdx.files.internal(fname));
		// create a new texture region, otherwise black pixels will show up too, we are simply cropping the image
		// last 4 numbers respresent the length of how much can each corner can draw,
		// for example if your image is 50px and you set the numbers 50, your whole image will be drawn in each corner
		// so what number should be good?, well a little less than half would be nice
		    return new NinePatch( new TextureRegion(t, 1, 1 , t.getWidth() - 2, t.getWidth() - 2), 10, 10, 10, 10);
		}
	
	

}

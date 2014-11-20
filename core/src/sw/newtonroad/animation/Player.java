package sw.newtonroad.animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;

public class Player extends B2DSprite {
	private Texture tex;
	private float playerWidth;
	private float playerHeight;
	private float playerSpeed;
public Player(Body body,String img,float speed,int x,int y,float width,float height,float posX,float posY) {
		super(body);
		this.playerWidth=width;
		this.playerHeight=height;
		this.playerSpeed=speed;
		 tex = new Texture(Gdx.files.internal(img));;
		TextureRegion[] sprites = TextureRegion.split(tex, x, y)[0];
		setAnimation(sprites, 1f / speed,this.playerWidth,this.playerHeight,posX,posY);
		
	}
public float getPlayerWidth() {
	return playerWidth;
}
public void setPlayerWidth(float playerWidth) {
	this.playerWidth = playerWidth;
}
public float getPlayerHeight() {
	return playerHeight;
}
public void setPlayerHeight(float playerHeight) {
	this.playerHeight = playerHeight;
}
public float getPlayerSpeed() {
	return playerSpeed;
}
public void setPlayerSpeed(float playerSpeed) {
	this.playerSpeed = playerSpeed;
}
public void deleteTexture(){
	tex.dispose();
	
}

}

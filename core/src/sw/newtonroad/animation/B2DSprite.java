package sw.newtonroad.animation;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class B2DSprite {
	protected Body body;
	protected Animation animation;
	protected float width;
	protected float height;
	private float w;
	private float h;
	private float posX;
	private float posY;
	public B2DSprite(Body body) {
		this.body = body;
		animation = new Animation();
	}
	
	public void setAnimation(TextureRegion[] reg, float delay,float w,float h,float posX,float posY) {
		animation.setFrames(reg, delay);
		width = reg[0].getRegionWidth();
		height = reg[0].getRegionHeight();
		this.w=w;
		this.h=h;
		this.posX=posX;
		this.posY=posY;
	}
	
	public void update(float dt) {
		animation.update(dt);
	}
	
	
	public void render(SpriteBatch sb) {
		//sb.setProjectionMatrix(new Matrix4());
		sb.begin();
		sb.draw(
			animation.getFrame(),
			body.getPosition().x-posX ,
			body.getPosition().y-posY,w,h
		);
		
		sb.end();
		
	}
	
	public Body getBody() { return body; }
	public Vector2 getPosition() { return body.getPosition(); }
	public float getWidth() { return width; }
	public float getHeight() { return height; }

}

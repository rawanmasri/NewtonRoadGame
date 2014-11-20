package sw.newtonroad.body;

import java.util.ArrayList;




import sw.newtonroad.joint.GameJoint;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public abstract class GameObject {
	protected ArrayList<Vector2> positionArray;
	protected ArrayList<Body> bodyArray;
	protected ArrayList<BasicObject> basicObjectArray;
	protected ArrayList<GameJoint> gameJoint;
	protected float width;
	protected float height;
	public String[] jointInfo;
	protected boolean secondJoint;
	public abstract void setDynamic(Vector2 position);
	public abstract void setStatic(Vector2 position);
	public abstract ArrayList<Vector2> getPosition();
	public abstract void setPosition(Vector2 position);
	public abstract float getDensity();
	public abstract void setDensity(float density);
	public abstract float getFriction();
	public abstract void setFriction(float friction);
	public abstract float getRestitution();
	public abstract void setRestitution(float restitution);
	public abstract ArrayList<Body> getBody();
	public abstract void setWidth(float width);
	public abstract float getWidth();
	public abstract void setHeight(float height);
	public abstract float getHeight();
	public abstract void setRadius(float radius);
	public abstract float getRadius();
	public abstract ArrayList<BasicObject> getBasicObject();
	public boolean isSecondJoint() {
		return secondJoint;
	}
	public void setSecondJoint(boolean secondJoint) {
		this.secondJoint = secondJoint;
	}
	public boolean getSecondJoint() {
		return secondJoint;
	}
	public  void setGameJoint(GameJoint gameJoint){
		this.gameJoint.add(gameJoint);
	}
	public ArrayList<GameJoint> getGameJoint(){
		return gameJoint;
	}
	
	
}

package sw.newtonroad.body;

import java.util.ArrayList;

import sw.newtonroad.animation.Player;
import sw.newtonroad.handlers.AppVar;
import sw.newtonroad.handlers.AppVar.basicBodyType;
import sw.newtonroad.joint.Distance;
import sw.newtonroad.joint.GameJoint;
import sw.newtonroad.joint.Revolute;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;

public class EndObject extends GameObject{
	private BasicObject endObject;
	private World world;
	private AppVar.animationObject type;
	private float animationSpeed;
	private float virtualDencity;
	private Player player;
	public EndObject(World world,AppVar.animationObject type,float x,float y){
		this.world=world;
		this.type=type;
		positionArray=new ArrayList<Vector2>();
		bodyArray=new ArrayList<Body>();
		gameJoint=new ArrayList<GameJoint>();
		basicObjectArray=new ArrayList<BasicObject>();
		virtualDencity=1.52f;
		jointInfo=new String[4];
		secondJoint=false;
		switch (type) {
		case CUTE:
			endObject=new BasicObject(world, BodyType.DynamicBody,basicBodyType.BOX,"test.png",x, y);
			endObject.setWidth(.8f);
			endObject.setHeight(1f);
			player=new Player(endObject.getBody().get(0),"animation/cute.png",2,50, 72,2.5f,2.8f,1f,1.2f);
			break;
		case FALCON:
			endObject=new BasicObject(world, BodyType.DynamicBody,basicBodyType.BOX,"test.png",x, y);
			endObject.setWidth(.8f);
			endObject.setHeight(1f);
			player=new Player(endObject.getBody().get(0),"animation/falcon.png",5,84, 77,2.6f,3f,1.5f,1.2f);
			break;
		case GIRL:
			endObject=new BasicObject(world, BodyType.DynamicBody,basicBodyType.BOX,"test.png",x, y);
			endObject.setWidth(.6f);
			endObject.setHeight(1.5f);
			player=new Player(endObject.getBody().get(0),"animation/girl.png",5,303, 300,2.8f,3.5f,1.5f,1.6f);
			break;
		case TEDDY:
			endObject=new BasicObject(world, BodyType.DynamicBody,basicBodyType.BOX,"test.png",x, y);
			endObject.setWidth(1.2f);
			endObject.setHeight(1.5f);
			player=new Player(endObject.getBody().get(0),"animation/teddy.png",5,82, 82,2.8f,3.5f,1.5f,1.5f);
			break;
		case TREE1:
			endObject=new BasicObject(world, BodyType.DynamicBody,basicBodyType.BOX,"test.png",x, y);
			endObject.setWidth(.7f);
			endObject.setHeight(2.3f);
			player=new Player(endObject.getBody().get(0),"animation/tree1.png",7,101, 142,3.2f,4.5f,1.6f,2.2f);
			break;
		case FIXEDNEWTON:
			endObject=new BasicObject(world, BodyType.DynamicBody,basicBodyType.BOX,"test.png",x, y);
			endObject.setHeight(1.6f);
			player=new Player(endObject.getBody().get(0),"animation/fixednewton.png",0,53, 99,2.2f,3.5f,1f,1.6f);
			break;
		case APPLE:
			endObject=new BasicObject(world, BodyType.DynamicBody,basicBodyType.BALL,"test.png",x, y);
			endObject.setRadius(.9f);
			endObject.setDensity(0);
			player=new Player(endObject.getBody().get(0),"animation/APPLE.png",0,90,95,2f,2f,1.1f,.9f);
			break;
			
		}
		endObject.setDensity(0);
		
	}
	public Player getPlayer(){
		return player;
	}
	@Override
	public void setDynamic(Vector2 position) {
		//endObject.setDynamic(position);
		endObject.changeBodyType(BodyType.StaticBody);
	}

	@Override
	public void setStatic(Vector2 position) {
		endObject.changeBodyType(BodyType.DynamicBody);
		//endObject.setStatic(position);
	}

	@Override
	public ArrayList<Vector2> getPosition() {
		positionArray.clear();
		positionArray.add(endObject.getPosition().get(0));
		return positionArray;	
		}

	@Override
	public void setPosition(Vector2 position) {
		endObject.setPosition(position);
	}

	@Override
	public float getDensity() {
		return virtualDencity;
	}

	@Override
	public void setDensity(float density) {
		this.virtualDencity=density;
	}

	@Override
	public float getFriction() {
		return endObject.getFriction();
	}

	@Override
	public void setFriction(float friction) {
		endObject.setFriction(friction);
	}

	@Override
	public float getRestitution() {
		return endObject.getRestitution();
	}

	@Override
	public void setRestitution(float restitution) {
		endObject.setRestitution(restitution);
	}

	@Override
	public ArrayList<Body> getBody() {
		 bodyArray.clear();
		 bodyArray.add(endObject.getBody().get(0));
		 return bodyArray;
		
	}
	@Override
	public void setWidth(float width) {
		
		switch (type) {
		case CUTE:
		case FALCON:
		case GIRL:
		case TEDDY:
		case TREE1:
			endObject.setWidth(width);
			break;
		default:
			break;
		}
	}

	@Override
	public float getWidth() {
		switch (type) {
		case CUTE:
		case FALCON:
		case GIRL:
		case TEDDY:
		case TREE1:
			return endObject.getWidth();
		default:
			return 0;
		}
	}

	@Override
	public void setHeight(float height) {
		switch (type) {
		case CUTE:
		case FALCON:
		case GIRL:
		case TEDDY:
		case TREE1:
			endObject.setHeight(height);
			break;
		default:
			break;
		}
		
	}

	@Override
	public float getHeight() {
		switch (type) {
		case CUTE:
		case FALCON:
		case GIRL:
		case TEDDY:
		case TREE1:
			return endObject.getHeight();
		default:
			return 0;
		}
	}

	@Override
	public void setRadius(float radius) {
	}

	@Override
	public float getRadius() {
		return 0;
	}

	@Override
	public ArrayList<BasicObject> getBasicObject() {
		basicObjectArray.clear();
		basicObjectArray.add(endObject);
		return basicObjectArray;
	}
	public void setProperties(float density, float friction, float restitution,float width,float height,float animationSpeed){
		this.virtualDencity=density;
		endObject.setFriction(friction);
		endObject.setRestitution(restitution);
	}
	public AppVar.animationObject getType() {
		return type;
	}
	public void setType(AppVar.animationObject type) {
		this.type = type;
	}
	public float getAnimationSpeed() {
		return animationSpeed;
	}
	public void setAnimationSpeed(float animationSpeed) {
		this.animationSpeed = animationSpeed;
	}
	
	

}

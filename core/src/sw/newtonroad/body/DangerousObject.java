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
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.joints.WheelJoint;
public class DangerousObject extends GameObject{
	private BasicObject firstDangerousObject;
	private BasicObject dangerousObjectLeftWheel,dangerousObjectRightWheel;
	private World world;
	private float movementSpeed;
	private AppVar.animationObject type;
	private float animationSpeed;
	private WheelJoint [] wheelJoint;
	
	private float virtualDencity=4.2f;
	private Player player;
	public DangerousObject(World world,AppVar.animationObject type,float x,float y){
		this.world=world;
		this.type=type;
		
		positionArray=new ArrayList<Vector2>();
		bodyArray=new ArrayList<Body>();
		gameJoint=new ArrayList<GameJoint>();
		basicObjectArray=new ArrayList<BasicObject>();
		jointInfo=new String[4];
		secondJoint=false;
		firstDangerousObject=new BasicObject(world, BodyType.DynamicBody,basicBodyType.BOX,"test.png",x, y);
		switch (type) {
		case BLUEMONSTER:
			firstDangerousObject.setHeight(1.3f);
			player=new Player(firstDangerousObject.getBody().get(0),"animation/bluemonster.png",5,190, 240,2.6f,2.6f,1.1f,1.3f);
			break;
		case BOOM4:
			firstDangerousObject.setHeight(1.8f);
			player=new Player(firstDangerousObject.getBody().get(0),"animation/boom4.png",6,317, 212,4f,5f,1.5f,2.4f);
			break;
		case JELLYFISH1:
			firstDangerousObject.setHeight(1.3f);
			firstDangerousObject.setWidth(.6f);
			player=new Player(firstDangerousObject.getBody().get(0),"animation/jellyfish1.png",1,107, 92,2.6f,2.6f,1.1f,1.3f);
			break;
		case BLACKMONSTER:
			firstDangerousObject.setHeight(1.8f);
			firstDangerousObject.setWidth(1.2f);
			player=new Player(firstDangerousObject.getBody().get(0),"animation/blackmonster.png",5,73, 91,3f,4f,1.5f,2.1f);
			
			
			
			break;
		case GREENMONSTER:
			movementSpeed=-5;
			dangerousObjectLeftWheel = new BasicObject(world, BodyType.DynamicBody,basicBodyType.WHEEL,"test.png",x, y);
			dangerousObjectRightWheel = new BasicObject(world, BodyType.DynamicBody,basicBodyType.WHEEL, "test.png",x, y);
			player=new Player(firstDangerousObject.getBody().get(0),"animation/greenmonster.png",5,100, 100,4f,5f,1.5f,2.1f);
			firstDangerousObject.getBody().get(0).setTransform(firstDangerousObject.getBody().get(0).getPosition(), .5f);
			firstDangerousObject.getBody().get(0).setTransform(firstDangerousObject.getBody().get(0).getPosition(), .5f);
			firstDangerousObject.setWidth(.3f);
			firstDangerousObject.setHeight(1.3f);
			dangerousObjectLeftWheel.setRadius(.35f);
			dangerousObjectRightWheel.setRadius(.6f);
			wheelJoint=AppVar.createWheelJoint(world, firstDangerousObject.getBody().get(0), dangerousObjectLeftWheel.getBody().get(0), dangerousObjectRightWheel.getBody().get(0), 3,2f, 7.8f,1500);
			
			break;
		case GREENMONSTER2:
			movementSpeed=5;
			dangerousObjectLeftWheel = new BasicObject(world, BodyType.DynamicBody,basicBodyType.WHEEL,"test.png",x, y);
			dangerousObjectRightWheel = new BasicObject(world, BodyType.DynamicBody,basicBodyType.WHEEL, "test.png",x, y);
			player=new Player(firstDangerousObject.getBody().get(0),"animation/greenmonster2.png",5,100, 100,4f,5f,1.5f,2.1f);
			firstDangerousObject.getBody().get(0).setTransform(firstDangerousObject.getBody().get(0).getPosition(), -.5f);
			firstDangerousObject.getBody().get(0).setTransform(firstDangerousObject.getBody().get(0).getPosition(), -.5f);
			firstDangerousObject.setWidth(.3f);
			firstDangerousObject.setHeight(1.3f);
			dangerousObjectLeftWheel.setRadius(.6f);
			dangerousObjectRightWheel.setRadius(.35f);
			wheelJoint=AppVar.createWheelJoint(world, firstDangerousObject.getBody().get(0), dangerousObjectLeftWheel.getBody().get(0), dangerousObjectRightWheel.getBody().get(0), 3,2f, 7.8f,1500);
			
			break;
		case EVIL:
			movementSpeed=-5;
			dangerousObjectLeftWheel = new BasicObject(world, BodyType.DynamicBody,basicBodyType.WHEEL,"test.png",x, y);
			dangerousObjectRightWheel = new BasicObject(world, BodyType.DynamicBody,basicBodyType.WHEEL, "test.png",x, y);
			player=new Player(firstDangerousObject.getBody().get(0),"animation/evil.png",5,262, 213,4f,5f,2.5f,2.4f);
			firstDangerousObject.getBody().get(0).setTransform(firstDangerousObject.getBody().get(0).getPosition(), -.5f);
			firstDangerousObject.setWidth(.3f);
			firstDangerousObject.setHeight(1.3f);
			dangerousObjectRightWheel.setRadius(.35f);
			dangerousObjectLeftWheel.setRadius(.6f);
			firstDangerousObject.setDensity(0);
			wheelJoint=AppVar.createWheelJoint(world, firstDangerousObject.getBody().get(0), dangerousObjectLeftWheel.getBody().get(0), dangerousObjectRightWheel.getBody().get(0), 3,2f, 7.8f,1500);
			
			break;
		case DOG1:
			movementSpeed=-5;
			dangerousObjectLeftWheel = new BasicObject(world, BodyType.DynamicBody,basicBodyType.WHEEL,"test.png",x, y);
			dangerousObjectRightWheel = new BasicObject(world, BodyType.DynamicBody,basicBodyType.WHEEL, "test.png",x, y);
			player=new Player(firstDangerousObject.getBody().get(0),"animation/dog1.png",5,184, 90,5.5f,5f,1.5f,2.6f);
			firstDangerousObject.getBody().get(0).setTransform(firstDangerousObject.getBody().get(0).getPosition(), .5f);
			firstDangerousObject.getBody().get(0).setTransform(firstDangerousObject.getBody().get(0).getPosition(), .5f);
			firstDangerousObject.setWidth(.3f);
			firstDangerousObject.setHeight(1.3f);
			dangerousObjectLeftWheel.setRadius(.35f);
			dangerousObjectRightWheel.setRadius(.6f);
			wheelJoint=AppVar.createWheelJoint(world, firstDangerousObject.getBody().get(0), dangerousObjectLeftWheel.getBody().get(0), dangerousObjectRightWheel.getBody().get(0), 3,2f, 7.8f,1500);
			
			break;
		case DOG2:
			movementSpeed=5;
			dangerousObjectLeftWheel = new BasicObject(world, BodyType.DynamicBody,basicBodyType.WHEEL,"test.png",x, y);
			dangerousObjectRightWheel = new BasicObject(world, BodyType.DynamicBody,basicBodyType.WHEEL, "test.png",x, y);
			player=new Player(firstDangerousObject.getBody().get(0),"animation/dog2.png",5,184, 90,5.5f,5f,2f,2.6f);
			firstDangerousObject.getBody().get(0).setTransform(firstDangerousObject.getBody().get(0).getPosition(), -.5f);
			firstDangerousObject.getBody().get(0).setTransform(firstDangerousObject.getBody().get(0).getPosition(), -.5f);
			firstDangerousObject.setWidth(.3f);
			firstDangerousObject.setHeight(1.3f);
			dangerousObjectLeftWheel.setRadius(.6f);
			dangerousObjectRightWheel.setRadius(.35f);
			wheelJoint=AppVar.createWheelJoint(world, firstDangerousObject.getBody().get(0), dangerousObjectLeftWheel.getBody().get(0), dangerousObjectRightWheel.getBody().get(0), 3,2f, 7.8f,1500);
			
			break;

		default:
			break;
		}
		firstDangerousObject.setDensity(0);
		
	}
	public Player getPlayer(){
		return player;
	}
	@Override
	public void setDynamic(Vector2 position) {
		switch (type) {
		case BLUEMONSTER:
		case BOOM4:
		case JELLYFISH1:
		case BLACKMONSTER:
			firstDangerousObject.changeBodyType(BodyType.StaticBody);
			break;
		case GREENMONSTER:
		case DOG1:
			dangerousObjectLeftWheel.setDensity(3);
			dangerousObjectRightWheel.setDensity(3);
			firstDangerousObject.setDynamic(position);
			dangerousObjectLeftWheel.setDynamic(dangerousObjectLeftWheel.getPosition().get(0));
			dangerousObjectRightWheel.setDynamic(dangerousObjectRightWheel.getPosition().get(0));
			wheelJoint[0].enableMotor(true);
			wheelJoint[1].enableMotor(true);
			wheelJoint[0].setMotorSpeed(movementSpeed);
			wheelJoint[1].setMotorSpeed(movementSpeed);
			firstDangerousObject.getBody().get(0).setTransform(firstDangerousObject.getBody().get(0).getPosition(), .5f);
		break;
		case GREENMONSTER2:
		case DOG2:
			dangerousObjectLeftWheel.setDensity(3);
			dangerousObjectRightWheel.setDensity(3);
			firstDangerousObject.setDynamic(position);
			dangerousObjectLeftWheel.setDynamic(dangerousObjectLeftWheel.getPosition().get(0));
			dangerousObjectRightWheel.setDynamic(dangerousObjectRightWheel.getPosition().get(0));
			wheelJoint[0].enableMotor(true);
			wheelJoint[1].enableMotor(true);
			wheelJoint[0].setMotorSpeed(movementSpeed);
			wheelJoint[1].setMotorSpeed(movementSpeed);
			firstDangerousObject.getBody().get(0).setTransform(firstDangerousObject.getBody().get(0).getPosition(), -.5f);
			break;
		case EVIL:
			dangerousObjectLeftWheel.setDensity(3);
			dangerousObjectRightWheel.setDensity(3);
			firstDangerousObject.setDynamic(position);
			dangerousObjectLeftWheel.setDynamic(dangerousObjectLeftWheel.getPosition().get(0));
			dangerousObjectRightWheel.setDynamic(dangerousObjectRightWheel.getPosition().get(0));
			wheelJoint[0].enableMotor(true);
			wheelJoint[1].enableMotor(true);
			wheelJoint[0].setMotorSpeed(movementSpeed);
			wheelJoint[1].setMotorSpeed(movementSpeed);
			firstDangerousObject.getBody().get(0).setTransform(firstDangerousObject.getBody().get(0).getPosition(), .5f);
			break;
		default:
			break;
		}
		
	}
	@Override
	public void setStatic(Vector2 position) {
		switch (type) {
		case BLUEMONSTER:
		case BOOM4:
		case JELLYFISH1:
		case BLACKMONSTER:
			firstDangerousObject.changeBodyType(BodyType.DynamicBody);
			break;
		case GREENMONSTER:
		case DOG1:
		
		case EVIL:
			firstDangerousObject.setStatic(position);
			dangerousObjectLeftWheel.setStatic(position);
			dangerousObjectRightWheel.setStatic(position);
			firstDangerousObject.setDensity(0);
			dangerousObjectLeftWheel.setDensity(0);
			dangerousObjectRightWheel.setDensity(0);
			wheelJoint[0].enableMotor(false);
			wheelJoint[1].enableMotor(false);
			firstDangerousObject.getBody().get(0).setTransform(firstDangerousObject.getBody().get(0).getPosition(), .5f);
			break;
		case GREENMONSTER2:
		case DOG2:
			firstDangerousObject.setStatic(position);
			dangerousObjectLeftWheel.setStatic(position);
			dangerousObjectRightWheel.setStatic(position);
			firstDangerousObject.setDensity(0);
			dangerousObjectLeftWheel.setDensity(0);
			dangerousObjectRightWheel.setDensity(0);
			wheelJoint[0].enableMotor(false);
			wheelJoint[1].enableMotor(false);
			firstDangerousObject.getBody().get(0).setTransform(firstDangerousObject.getBody().get(0).getPosition(), -.5f);
			break;
		default:
			break;
		}
		
	}
	@Override
	public ArrayList<Vector2> getPosition() {
		positionArray.clear();
		switch (type) {
		case BLUEMONSTER:
		case BOOM4:
		case JELLYFISH1:
		case BLACKMONSTER:
			positionArray.add(firstDangerousObject.getPosition().get(0));
			break;
		case GREENMONSTER:
		case DOG1:
		case DOG2:
		case GREENMONSTER2:
		case EVIL:
			positionArray.add(firstDangerousObject.getPosition().get(0));
			positionArray.add(dangerousObjectLeftWheel.getPosition().get(0));
			positionArray.add(dangerousObjectRightWheel.getPosition().get(0));
			break;
		default:
			break;
		}

		return positionArray;
	}
	@Override
	public void setPosition(Vector2 position) {
		// TODO Auto-generated method stub
		
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
		switch (type) {
		case BLUEMONSTER:
		case BOOM4:
		case JELLYFISH1:
		case BLACKMONSTER:
			return firstDangerousObject.getFriction();
			
		case GREENMONSTER:
		case DOG1:
		case DOG2:
		case GREENMONSTER2:
		case EVIL:
			
			return dangerousObjectLeftWheel.getFriction();
		default:
			break;
		
		}
		return 0;
	}
	@Override
	public void setFriction(float friction) {
		switch (type) {
		case BLUEMONSTER:
		case BOOM4:
		case JELLYFISH1:
		case BLACKMONSTER:
			firstDangerousObject.setFriction(friction);
			break;
		case GREENMONSTER:
		case DOG1:
		case DOG2:
		case GREENMONSTER2:
		case EVIL:
			
			dangerousObjectLeftWheel.setFriction(friction);
			dangerousObjectRightWheel.setFriction(friction);
			break;
		default:
			break;
		}
		
	}
	@Override
	public float getRestitution() {
		switch (type) {
		case BLUEMONSTER:
		case BOOM4:
		case JELLYFISH1:
		case BLACKMONSTER:
			return firstDangerousObject.getFriction();
			
		case GREENMONSTER:
		case DOG1:
		case DOG2:
		case GREENMONSTER2:
		case EVIL:
			
			return dangerousObjectLeftWheel.getFriction();
			
		default:
			break;
		}
		return 0;
	}
	@Override
	public void setRestitution(float restitution) {
		switch (type) {
		case BLUEMONSTER:
		case BOOM4:
		case JELLYFISH1:
		case BLACKMONSTER:
			firstDangerousObject.setRestitution(restitution);
			break;
		case GREENMONSTER:
		case DOG1:
		case DOG2:
		case GREENMONSTER2:
		case EVIL:
			dangerousObjectLeftWheel.setRestitution(restitution);
			dangerousObjectRightWheel.setRestitution(restitution);
			break;
		default:
			break;
		}
		
	}
	@Override
	public ArrayList<Body> getBody() {
		 bodyArray.clear();
		switch (type) {
		case BLUEMONSTER:
		case BOOM4:
		case JELLYFISH1:
		case BLACKMONSTER:
			bodyArray.add(firstDangerousObject.getBody().get(0));
			break;
		case GREENMONSTER:
		case DOG1:
		case DOG2:
		case GREENMONSTER2:
			bodyArray.add(dangerousObjectLeftWheel.getBody().get(0));
			break;
		case EVIL:
			bodyArray.add(dangerousObjectRightWheel.getBody().get(0));
			break;
		default:
			break;
		}
		 return bodyArray;
	}
	@Override
	public void setWidth(float width) {
		switch (type) {
		case BLUEMONSTER:
		case BOOM4:
		case JELLYFISH1:
		case BLACKMONSTER:
			firstDangerousObject.setWidth(width);
		}
	}
	@Override
	public float getWidth() {
		switch (type) {
		case BLUEMONSTER:
		case BOOM4:
		case JELLYFISH1:
		case BLACKMONSTER:
			return	firstDangerousObject.getWidth();
		}
		return 0;
	}
	@Override
	public void setHeight(float height) {
		switch (type) {
		case BLUEMONSTER:
		case BOOM4:
		case JELLYFISH1:
		case BLACKMONSTER:
			firstDangerousObject.setHeight(height);
		}
		
	}
	@Override
	public float getHeight() {
		switch (type) {
		case BLUEMONSTER:
		case BOOM4:
		case JELLYFISH1:
		case BLACKMONSTER:
			return firstDangerousObject.getHeight();
		}
		return 0;
	}
	@Override
	public void setRadius(float radius) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public float getRadius() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public ArrayList<BasicObject> getBasicObject() {
		basicObjectArray.clear();
		switch (type) {
		case BLUEMONSTER:
		case BOOM4:
		case JELLYFISH1:
		case BLACKMONSTER:
			basicObjectArray.add(firstDangerousObject);
			break;
		case GREENMONSTER:
		case DOG1:
		case DOG2:
		case GREENMONSTER2:
		case EVIL:
			basicObjectArray.add(firstDangerousObject);
			basicObjectArray.add(dangerousObjectLeftWheel);
			basicObjectArray.add(dangerousObjectRightWheel);
			break;
		default:
			break;
		}
		return basicObjectArray;
	}
	public void setProperties(float density,float friction,float restitution,float width,float height,float movementSpeed){
		switch (type) {
		case BLUEMONSTER:
		case BOOM4:
		case JELLYFISH1:
		case BLACKMONSTER:
			this.virtualDencity=density;
			firstDangerousObject.setFriction(friction);
			firstDangerousObject.setRestitution(restitution);
			firstDangerousObject.setWidth(width);
			firstDangerousObject.setHeight(height);
			
			break;
		case GREENMONSTER:
		case DOG1:
		case DOG2:
		case GREENMONSTER2:
		case EVIL:
			this.virtualDencity=density;
			dangerousObjectLeftWheel.setFriction(friction);
			dangerousObjectRightWheel.setFriction(friction);
			dangerousObjectLeftWheel.setRestitution(restitution);
			dangerousObjectRightWheel.setRestitution(restitution);
			this.movementSpeed=movementSpeed;
			break;
		default:
			break;
		}
		
		
		
	}
	public float getMovementSpeed() {
		return movementSpeed;
	}
	public void setMovementSpeed(float movementSpeed) {
		this.movementSpeed = movementSpeed;
	}
	public AppVar.animationObject getType() {
		return type;
	}
	public void setType(AppVar.animationObject type) {
		this.type = type;
	}
	


}

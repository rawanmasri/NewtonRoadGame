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

public class StartObject extends GameObject{
	private BasicObject startObjectShape;
	private BasicObject startObjectLeftWheel,startObjectRightWheel;
	private float movementSpeed;
	private boolean controlMovement;
	private float animationSpeed;
	private WheelJoint [] wheelJoint;
	private World world;
	private AppVar.animationObject type;
	private Player player;
	private float virtualDencity=3.24f;
	private int lifeCount;
	public StartObject(World world,AppVar.animationObject type,float x,float y){
		this.world=world;
		this.type=type;
		positionArray=new ArrayList<Vector2>();
		bodyArray=new ArrayList<Body>();
		gameJoint=new ArrayList<GameJoint>();
		basicObjectArray=new ArrayList<BasicObject>();
		jointInfo=new String[4];
		secondJoint=false;
		setControlMovement(false);
		lifeCount=0;
		switch (type) {
		case BUNNY1:
			startObjectLeftWheel = new BasicObject(world, BodyType.DynamicBody,basicBodyType.WHEEL,"test.png",x, y);
			startObjectRightWheel = new BasicObject(world, BodyType.DynamicBody,basicBodyType.WHEEL, "test.png",x, y);
			startObjectShape=new BasicObject(world, BodyType.DynamicBody,basicBodyType.BOX,"test.png",x, y);
			movementSpeed=-5;
			startObjectShape.setWidth(1);
			startObjectShape.setHeight(.4f);
			startObjectLeftWheel.setRadius(.35f);
			startObjectRightWheel.setRadius(.6f);
			startObjectShape.setDensity(0);
			startObjectShape.getBody().get(0).setTransform(startObjectShape.getBody().get(0).getPosition(), .5f);
			wheelJoint=AppVar.createWheelJoint(world, startObjectShape.getBody().get(0), startObjectLeftWheel.getBody().get(0), startObjectRightWheel.getBody().get(0), 3,.8f, 7f,1500);
			player=new Player(startObjectShape.getBody().get(0),"animation/bunny.png",12,32,32,3f,2.8f,.8f,1.2f);
			break;
		case BUNNY2:
			startObjectLeftWheel = new BasicObject(world, BodyType.DynamicBody,basicBodyType.WHEEL,"test.png",x, y);
			startObjectRightWheel = new BasicObject(world, BodyType.DynamicBody,basicBodyType.WHEEL, "test.png",x, y);
			startObjectShape=new BasicObject(world, BodyType.DynamicBody,basicBodyType.BOX,"test.png",x, y);
			movementSpeed=5;
			startObjectShape.setWidth(1);
			startObjectShape.setHeight(.4f);
			startObjectLeftWheel.setRadius(.6f);
			startObjectRightWheel.setRadius(.35f);
			startObjectShape.setDensity(0);
			startObjectShape.getBody().get(0).setTransform(startObjectShape.getBody().get(0).getPosition(), -.5f);
			wheelJoint=AppVar.createWheelJoint(world, startObjectShape.getBody().get(0), startObjectLeftWheel.getBody().get(0), startObjectRightWheel.getBody().get(0), 3,.8f, 7f,1500);
			player=new Player(startObjectShape.getBody().get(0),"animation/bunny2.png",12,32,32,3f,2.8f,2f,1.2f);
			break;
		case HANDSOMEBOY1:
			startObjectLeftWheel = new BasicObject(world, BodyType.DynamicBody,basicBodyType.WHEEL,"test.png",x, y);
			startObjectRightWheel = new BasicObject(world, BodyType.DynamicBody,basicBodyType.WHEEL, "test.png",x, y);
			startObjectShape=new BasicObject(world, BodyType.DynamicBody,basicBodyType.BOX,"test.png",x, y);
			movementSpeed=-6;
			startObjectShape.setWidth(.3f);
			startObjectShape.setHeight(1.3f);
			startObjectLeftWheel.setRadius(.35f);
			startObjectRightWheel.setRadius(.6f);
			//rightWheel.setStatic();
			startObjectShape.setDensity(0);
			startObjectShape.getBody().get(0).setTransform(startObjectShape.getBody().get(0).getPosition(), .5f);
			//startObjectShape.getBody().get(0).setTransform(startObjectShape.getBody().get(0).getPosition(), -.5f);
			wheelJoint=AppVar.createWheelJoint(world, startObjectShape.getBody().get(0), startObjectLeftWheel.getBody().get(0), startObjectRightWheel.getBody().get(0), 3,2f, 7.8f,1500);
			player=new Player(startObjectShape.getBody().get(0),"animation/handsomeboy.png",7,58, 87,3f,4.5f,1f,2.2f);
			break;
		case HANDSOMEBOY2:
			startObjectLeftWheel = new BasicObject(world, BodyType.DynamicBody,basicBodyType.WHEEL,"test.png",x, y);
			startObjectRightWheel = new BasicObject(world, BodyType.DynamicBody,basicBodyType.WHEEL, "test.png",x, y);
			startObjectShape=new BasicObject(world, BodyType.DynamicBody,basicBodyType.BOX,"test.png",x, y);
			movementSpeed=6;
			startObjectShape.setWidth(.3f);
			startObjectShape.setHeight(1.3f);
			startObjectLeftWheel.setRadius(.6f);
			startObjectRightWheel.setRadius(.35f);
			//rightWheel.setStatic();
			startObjectShape.setDensity(0);
			startObjectShape.getBody().get(0).setTransform(startObjectShape.getBody().get(0).getPosition(), -.5f);
			//startObjectShape.getBody().get(0).setTransform(startObjectShape.getBody().get(0).getPosition(), -.5f);
			wheelJoint=AppVar.createWheelJoint(world, startObjectShape.getBody().get(0), startObjectLeftWheel.getBody().get(0), startObjectRightWheel.getBody().get(0), 3,2f, 7.8f,1500);
			player=new Player(startObjectShape.getBody().get(0),"animation/handsomeboy2.png",7,60, 87,3f,4.5f,1.5f,2.2f);
			break;
		case HERO1:
			startObjectLeftWheel = new BasicObject(world, BodyType.DynamicBody,basicBodyType.WHEEL,"test.png",x, y);
			startObjectRightWheel = new BasicObject(world, BodyType.DynamicBody,basicBodyType.WHEEL, "test.png",x, y);
			startObjectShape=new BasicObject(world, BodyType.DynamicBody,basicBodyType.BOX,"test.png",x, y);
			movementSpeed=-6;
			startObjectShape.setWidth(.3f);
			startObjectShape.setHeight(1f);
			startObjectLeftWheel.setRadius(.35f);
			startObjectRightWheel.setRadius(.6f);
			//rightWheel.setStatic();
			startObjectShape.setDensity(0);
			startObjectShape.getBody().get(0).setTransform(startObjectShape.getBody().get(0).getPosition(), .5f);
			//startObjectShape.getBody().get(0).setTransform(startObjectShape.getBody().get(0).getPosition(), -.5f);
			wheelJoint=AppVar.createWheelJoint(world, startObjectShape.getBody().get(0), startObjectLeftWheel.getBody().get(0), startObjectRightWheel.getBody().get(0), 3,2f, 7.8f,1500);
			player=new Player(startObjectShape.getBody().get(0),"animation/hero.png",7,160, 145,3.7f,4.3f,1f,2.1f);
			break;
		case HERO2:
			startObjectLeftWheel = new BasicObject(world, BodyType.DynamicBody,basicBodyType.WHEEL,"test.png",x, y);
			startObjectRightWheel = new BasicObject(world, BodyType.DynamicBody,basicBodyType.WHEEL, "test.png",x, y);
			startObjectShape=new BasicObject(world, BodyType.DynamicBody,basicBodyType.BOX,"test.png",x, y);
			movementSpeed=6;
			startObjectShape.setWidth(.3f);
			startObjectShape.setHeight(1f);
			startObjectLeftWheel.setRadius(.6f);
			startObjectRightWheel.setRadius(.35f);
			//rightWheel.setStatic();
			startObjectShape.setDensity(0);
			startObjectShape.getBody().get(0).setTransform(startObjectShape.getBody().get(0).getPosition(), -.5f);
			//startObjectShape.getBody().get(0).setTransform(startObjectShape.getBody().get(0).getPosition(), -.5f);
			wheelJoint=AppVar.createWheelJoint(world, startObjectShape.getBody().get(0), startObjectLeftWheel.getBody().get(0), startObjectRightWheel.getBody().get(0), 3,2f, 7.8f,1500);
			player=new Player(startObjectShape.getBody().get(0),"animation/hero2.png",7,160, 145,3.7f,4.3f,1f,2.1f);
			break;
		case MAN1:
			startObjectLeftWheel = new BasicObject(world, BodyType.DynamicBody,basicBodyType.WHEEL,"test.png",x, y);
			startObjectRightWheel = new BasicObject(world, BodyType.DynamicBody,basicBodyType.WHEEL, "test.png",x, y);
			startObjectShape=new BasicObject(world, BodyType.DynamicBody,basicBodyType.BOX,"test.png",x, y);
			movementSpeed=-6;
			startObjectShape.setWidth(.3f);
			startObjectShape.setHeight(1f);
			startObjectLeftWheel.setRadius(.35f);
			startObjectRightWheel.setRadius(.65f);
			//rightWheel.setStatic();
			startObjectShape.setDensity(0);
			startObjectShape.getBody().get(0).setTransform(startObjectShape.getBody().get(0).getPosition(), .5f);
			//startObjectShape.getBody().get(0).setTransform(startObjectShape.getBody().get(0).getPosition(), -.5f);
			wheelJoint=AppVar.createWheelJoint(world, startObjectShape.getBody().get(0), startObjectLeftWheel.getBody().get(0), startObjectRightWheel.getBody().get(0), 3,2f, 7.8f,1500);
			player=new Player(startObjectShape.getBody().get(0),"animation/man.png",4, 99, 156,3.8f,4.1f,1f,2.1f);
			break;
		case MAN2:
			startObjectLeftWheel = new BasicObject(world, BodyType.DynamicBody,basicBodyType.WHEEL,"test.png",x, y);
			startObjectRightWheel = new BasicObject(world, BodyType.DynamicBody,basicBodyType.WHEEL, "test.png",x, y);
			startObjectShape=new BasicObject(world, BodyType.DynamicBody,basicBodyType.BOX,"test.png",x, y);
			movementSpeed=6;
			startObjectShape.setWidth(.3f);
			startObjectShape.setHeight(1f);
			startObjectLeftWheel.setRadius(.65f);
			startObjectRightWheel.setRadius(.35f);
			//rightWheel.setStatic();
			startObjectShape.setDensity(0);
			startObjectShape.getBody().get(0).setTransform(startObjectShape.getBody().get(0).getPosition(), -.5f);
			//startObjectShape.getBody().get(0).setTransform(startObjectShape.getBody().get(0).getPosition(), -.5f);
			wheelJoint=AppVar.createWheelJoint(world, startObjectShape.getBody().get(0), startObjectLeftWheel.getBody().get(0), startObjectRightWheel.getBody().get(0), 3,2f, 7.8f,1500);
			player=new Player(startObjectShape.getBody().get(0),"animation/man2.png",4, 99, 156,3.8f,4.1f,1.8f,2.1f);
			break;
		case MUSHROOM1:
			startObjectLeftWheel = new BasicObject(world, BodyType.DynamicBody,basicBodyType.WHEEL,"test.png",x, y);
			startObjectRightWheel = new BasicObject(world, BodyType.DynamicBody,basicBodyType.WHEEL, "test.png",x, y);
			startObjectShape=new BasicObject(world, BodyType.DynamicBody,basicBodyType.BOX,"test.png",x, y);
			movementSpeed=-6;
			startObjectShape.setWidth(.3f);
			startObjectShape.setHeight(1f);
			startObjectLeftWheel.setRadius(.35f);
			startObjectRightWheel.setRadius(.65f);
			//rightWheel.setStatic();
			startObjectShape.setDensity(0);
			startObjectShape.getBody().get(0).setTransform(startObjectShape.getBody().get(0).getPosition(), .5f);
			//startObjectShape.getBody().get(0).setTransform(startObjectShape.getBody().get(0).getPosition(), -.5f);
			wheelJoint=AppVar.createWheelJoint(world, startObjectShape.getBody().get(0), startObjectLeftWheel.getBody().get(0), startObjectRightWheel.getBody().get(0), 3,2f, 7.8f,1500);
			player=new Player(startObjectShape.getBody().get(0),"animation/mushroom.png",10,188, 240,3.8f,4.5f,1f,2.1f);
			break;
		case MUSHROOM2:
			startObjectLeftWheel = new BasicObject(world, BodyType.DynamicBody,basicBodyType.WHEEL,"test.png",x, y);
			startObjectRightWheel = new BasicObject(world, BodyType.DynamicBody,basicBodyType.WHEEL, "test.png",x, y);
			startObjectShape=new BasicObject(world, BodyType.DynamicBody,basicBodyType.BOX,"test.png",x, y);
			movementSpeed=6;
			startObjectShape.setWidth(.3f);
			startObjectShape.setHeight(1f);
			startObjectLeftWheel.setRadius(.65f);
			startObjectRightWheel.setRadius(.35f);
			//rightWheel.setStatic();
			startObjectShape.setDensity(0);
			startObjectShape.getBody().get(0).setTransform(startObjectShape.getBody().get(0).getPosition(), -.5f);
			//startObjectShape.getBody().get(0).setTransform(startObjectShape.getBody().get(0).getPosition(), -.5f);
			wheelJoint=AppVar.createWheelJoint(world, startObjectShape.getBody().get(0), startObjectLeftWheel.getBody().get(0), startObjectRightWheel.getBody().get(0), 3,2f, 7.8f,1500);
			player=new Player(startObjectShape.getBody().get(0),"animation/mushroom2.png",10,191, 240,3.8f,4.5f,1.9f,2.1f);
			break;
		case NEWTON:
			startObjectLeftWheel = new BasicObject(world, BodyType.DynamicBody,basicBodyType.WHEEL,"test.png",x, y);
			startObjectRightWheel = new BasicObject(world, BodyType.DynamicBody,basicBodyType.WHEEL, "test.png",x, y);
			startObjectShape=new BasicObject(world, BodyType.DynamicBody,basicBodyType.BOX,"test.png",x, y);
			movementSpeed=-6;
			startObjectShape.setWidth(.3f);
			startObjectShape.setHeight(1.8f);
			startObjectLeftWheel.setRadius(.35f);
			startObjectRightWheel.setRadius(.65f);
			//rightWheel.setStatic();
			startObjectShape.setDensity(0);
			startObjectShape.getBody().get(0).setTransform(startObjectShape.getBody().get(0).getPosition(), .5f);
			//startObjectShape.getBody().get(0).setTransform(startObjectShape.getBody().get(0).getPosition(), -.5f);
			wheelJoint=AppVar.createWheelJoint(world, startObjectShape.getBody().get(0), startObjectLeftWheel.getBody().get(0), startObjectRightWheel.getBody().get(0), 3,2f, 7.8f,1500);
			player=new Player(startObjectShape.getBody().get(0),"animation/newton.png",5,150, 106,4.5f,5.6f,.5f,2.5f);
			break;
		case NEWTON2:
			startObjectLeftWheel = new BasicObject(world, BodyType.DynamicBody,basicBodyType.WHEEL,"test.png",x, y);
			startObjectRightWheel = new BasicObject(world, BodyType.DynamicBody,basicBodyType.WHEEL, "test.png",x, y);
			startObjectShape=new BasicObject(world, BodyType.DynamicBody,basicBodyType.BOX,"test.png",x, y);
			movementSpeed=6;
			startObjectShape.setWidth(.3f);
			startObjectShape.setHeight(1.8f);
			startObjectLeftWheel.setRadius(.65f);
			startObjectRightWheel.setRadius(.35f);
			//rightWheel.setStatic();
			startObjectShape.setDensity(0);
			startObjectShape.getBody().get(0).setTransform(startObjectShape.getBody().get(0).getPosition(), -.5f);
			//startObjectShape.getBody().get(0).setTransform(startObjectShape.getBody().get(0).getPosition(), -.5f);
			wheelJoint=AppVar.createWheelJoint(world, startObjectShape.getBody().get(0), startObjectLeftWheel.getBody().get(0), startObjectRightWheel.getBody().get(0), 3,2f, 7.8f,1500);
			player=new Player(startObjectShape.getBody().get(0),"animation/newton2.png",5,150, 106,4.5f,5.6f,1.3f,2.5f);
			break;
		case APPLE:
			startObjectShape=new BasicObject(world, BodyType.DynamicBody,basicBodyType.BALL,"animation/APPLE.png",x, y);
			movementSpeed=0;
			startObjectShape.setRadius(1.1f);
		
			//rightWheel.setStatic();
			startObjectShape.setDensity(.01f);
			//startObjectShape.getBody().get(0).setTransform(startObjectShape.getBody().get(0).getPosition(), -.5f);
			player=new Player(startObjectShape.getBody().get(0),"test.png",0,90,95,2f,2f,1.1f,.9f);
			break;
		case CAT1:
			startObjectLeftWheel = new BasicObject(world, BodyType.DynamicBody,basicBodyType.WHEEL,"test.png",x, y);
			startObjectRightWheel = new BasicObject(world, BodyType.DynamicBody,basicBodyType.WHEEL, "test.png",x, y);
			startObjectShape=new BasicObject(world, BodyType.DynamicBody,basicBodyType.BOX,"test.png",x, y);
			movementSpeed=-5;
			startObjectShape.setWidth(1);
			startObjectShape.setHeight(.4f);
			startObjectLeftWheel.setRadius(.35f);
			startObjectRightWheel.setRadius(.6f);
			startObjectShape.setDensity(0);
			startObjectShape.getBody().get(0).setTransform(startObjectShape.getBody().get(0).getPosition(), .5f);
			wheelJoint=AppVar.createWheelJoint(world, startObjectShape.getBody().get(0), startObjectLeftWheel.getBody().get(0), startObjectRightWheel.getBody().get(0), 3,.8f, 7f,1500);
			player=new Player(startObjectShape.getBody().get(0),"animation/cat2.png",22,128,128,3.6f,3.3f,.8f,1.4f);
			break;
		case CAT2:
			startObjectLeftWheel = new BasicObject(world, BodyType.DynamicBody,basicBodyType.WHEEL,"test.png",x, y);
			startObjectRightWheel = new BasicObject(world, BodyType.DynamicBody,basicBodyType.WHEEL, "test.png",x, y);
			startObjectShape=new BasicObject(world, BodyType.DynamicBody,basicBodyType.BOX,"test.png",x, y);
			movementSpeed=5;
			startObjectShape.setWidth(1);
			startObjectShape.setHeight(.4f);
			startObjectLeftWheel.setRadius(.6f);
			startObjectRightWheel.setRadius(.35f);
			startObjectShape.setDensity(0);
			startObjectShape.getBody().get(0).setTransform(startObjectShape.getBody().get(0).getPosition(), -.5f);
			wheelJoint=AppVar.createWheelJoint(world, startObjectShape.getBody().get(0), startObjectLeftWheel.getBody().get(0), startObjectRightWheel.getBody().get(0), 3,.8f, 7f,1500);
			player=new Player(startObjectShape.getBody().get(0),"animation/cat1.png",22,128,128,3.6f,3.3f,1.3f,1.4f);
			break;
		case DEER1:
			startObjectLeftWheel = new BasicObject(world, BodyType.DynamicBody,basicBodyType.WHEEL,"test.png",x, y);
			startObjectRightWheel = new BasicObject(world, BodyType.DynamicBody,basicBodyType.WHEEL, "test.png",x, y);
			startObjectShape=new BasicObject(world, BodyType.DynamicBody,basicBodyType.BOX,"test.png",x, y);
			movementSpeed=5;
			startObjectShape.setWidth(1.2f);
			startObjectShape.setHeight(.6f);
			startObjectLeftWheel.setRadius(.65f);
			startObjectRightWheel.setRadius(.35f);
			startObjectShape.setDensity(0);
			startObjectShape.getBody().get(0).setTransform(startObjectShape.getBody().get(0).getPosition(), -.5f);
			wheelJoint=AppVar.createWheelJoint(world, startObjectShape.getBody().get(0), startObjectLeftWheel.getBody().get(0), startObjectRightWheel.getBody().get(0), 3.2f,1.2f, 7f,1500);
			player=new Player(startObjectShape.getBody().get(0),"animation/deer1.png",8,279,456,5f,7f,1.8f,2.3f);
			break;
		case DEER2:
			startObjectLeftWheel = new BasicObject(world, BodyType.DynamicBody,basicBodyType.WHEEL,"test.png",x, y);
			startObjectRightWheel = new BasicObject(world, BodyType.DynamicBody,basicBodyType.WHEEL, "test.png",x, y);
			startObjectShape=new BasicObject(world, BodyType.DynamicBody,basicBodyType.BOX,"test.png",x, y);
			movementSpeed=-5;
			startObjectShape.setWidth(1.2f);
			startObjectShape.setHeight(.6f);
			startObjectLeftWheel.setRadius(.34f);
			startObjectRightWheel.setRadius(.65f);
			startObjectShape.setDensity(0);
			startObjectShape.getBody().get(0).setTransform(startObjectShape.getBody().get(0).getPosition(), .5f);
			wheelJoint=AppVar.createWheelJoint(world, startObjectShape.getBody().get(0), startObjectLeftWheel.getBody().get(0), startObjectRightWheel.getBody().get(0), 3,.8f, 7f,1500);
			player=new Player(startObjectShape.getBody().get(0),"animation/deer2.png",8,284,456,5f,7f,1.8f,2.3f);
			break;
		case PIGGY1:
			startObjectLeftWheel = new BasicObject(world, BodyType.DynamicBody,basicBodyType.WHEEL,"test.png",x, y);
			startObjectRightWheel = new BasicObject(world, BodyType.DynamicBody,basicBodyType.WHEEL, "test.png",x, y);
			startObjectShape=new BasicObject(world, BodyType.DynamicBody,basicBodyType.BOX,"test.png",x, y);
			movementSpeed=-5;
			startObjectShape.setWidth(1.2f);
			startObjectShape.setHeight(.6f);
			startObjectLeftWheel.setRadius(.35f);
			startObjectRightWheel.setRadius(.65f);
			startObjectShape.setDensity(0);
			startObjectShape.getBody().get(0).setTransform(startObjectShape.getBody().get(0).getPosition(), .5f);
			wheelJoint=AppVar.createWheelJoint(world, startObjectShape.getBody().get(0), startObjectLeftWheel.getBody().get(0), startObjectRightWheel.getBody().get(0), 3,.8f, 7f,1500);
			player=new Player(startObjectShape.getBody().get(0),"animation/piggy1.png",20,127,100,3.5f,4f,1.8f,1.8f);
			break;
		case PIGGY2:
			startObjectLeftWheel = new BasicObject(world, BodyType.DynamicBody,basicBodyType.WHEEL,"test.png",x, y);
			startObjectRightWheel = new BasicObject(world, BodyType.DynamicBody,basicBodyType.WHEEL, "test.png",x, y);
			startObjectShape=new BasicObject(world, BodyType.DynamicBody,basicBodyType.BOX,"test.png",x, y);
			movementSpeed=5;
			startObjectShape.setWidth(1.2f);
			startObjectShape.setHeight(.6f);
			startObjectLeftWheel.setRadius(.65f);
			startObjectRightWheel.setRadius(.35f);
			startObjectShape.setDensity(0);
			startObjectShape.getBody().get(0).setTransform(startObjectShape.getBody().get(0).getPosition(), -.5f);
			wheelJoint=AppVar.createWheelJoint(world, startObjectShape.getBody().get(0), startObjectLeftWheel.getBody().get(0), startObjectRightWheel.getBody().get(0), 3,.8f, 7f,1500);
			player=new Player(startObjectShape.getBody().get(0),"animation/piggy2.png",20,127,100,3.5f,4f,1.8f,1.8f);
			break;
			
		}
		
	}
	public Player getPlayer(){
		return player;
	}
	@Override
	public void setDynamic(Vector2 position) {
		switch (type) {
		case BUNNY1:
		case HANDSOMEBOY1:
		case HERO1:
		case MAN1:
		case MUSHROOM1:
		case NEWTON:
		case CAT1:
		case DEER2:
		case PIGGY1:
		startObjectLeftWheel.setDensity(3);
		startObjectRightWheel.setDensity(3);
		startObjectShape.setDynamic(position);
		startObjectLeftWheel.setDynamic(startObjectLeftWheel.getPosition().get(0));
		startObjectRightWheel.setDynamic(startObjectRightWheel.getPosition().get(0));
		if(!controlMovement){
		wheelJoint[0].enableMotor(true);
		wheelJoint[1].enableMotor(true);
		wheelJoint[0].setMotorSpeed(movementSpeed);
		wheelJoint[1].setMotorSpeed(movementSpeed);
		}
		startObjectShape.getBody().get(0).setTransform(startObjectShape.getBody().get(0).getPosition(), .5f);
		break;
		case NEWTON2:
		case BUNNY2:
		case HANDSOMEBOY2:
		case HERO2:
		case MAN2:
		case MUSHROOM2:
		case CAT2:
		case DEER1:
		case PIGGY2:
			startObjectLeftWheel.setDensity(3);
			startObjectRightWheel.setDensity(3);
			startObjectShape.setDynamic(position);
			startObjectLeftWheel.setDynamic(startObjectLeftWheel.getPosition().get(0));
			startObjectRightWheel.setDynamic(startObjectRightWheel.getPosition().get(0));
			if(!controlMovement){
			wheelJoint[0].enableMotor(true);
			wheelJoint[1].enableMotor(true);
			wheelJoint[0].setMotorSpeed(movementSpeed);
			wheelJoint[1].setMotorSpeed(movementSpeed);
			}
			startObjectShape.getBody().get(0).setTransform(startObjectShape.getBody().get(0).getPosition(), -.5f);
		break;
		case APPLE:
			startObjectShape.setDynamic(position);
			break;
		}
	}

	@Override
	public void setStatic(Vector2 position) {
		switch (type) {
		case BUNNY1:
		case HANDSOMEBOY1:
		case HERO1:
		case MAN1:
		case MUSHROOM1:
		case NEWTON:
		case CAT1:
		case DEER2:
		case PIGGY1:
		startObjectShape.setStatic(position);
		startObjectLeftWheel.setStatic(position);
		startObjectRightWheel.setStatic(position);
		startObjectShape.setDensity(0);
		startObjectLeftWheel.setDensity(0);
		startObjectRightWheel.setDensity(0);
		wheelJoint[0].enableMotor(false);
		wheelJoint[1].enableMotor(false);
		startObjectShape.getBody().get(0).setTransform(startObjectShape.getBody().get(0).getPosition(), .5f);
		break;
		case NEWTON2:
		case BUNNY2:
		case HANDSOMEBOY2:
		case HERO2:
		case MAN2:
		case MUSHROOM2:
		case CAT2:
		case DEER1:
		case PIGGY2:
			startObjectShape.setStatic(position);
			startObjectLeftWheel.setStatic(position);
			startObjectRightWheel.setStatic(position);
			startObjectShape.setDensity(0);
			startObjectLeftWheel.setDensity(0);
			startObjectRightWheel.setDensity(0);
			wheelJoint[0].enableMotor(false);
			wheelJoint[1].enableMotor(false);
			startObjectShape.getBody().get(0).setTransform(startObjectShape.getBody().get(0).getPosition(), -.5f);
			break;
		case APPLE:
			startObjectShape.setStatic(position);
			break;
		}
	}

	@Override
	public ArrayList<Vector2> getPosition() {
		positionArray.clear();
		switch (type) {
		case BUNNY1:
		case HANDSOMEBOY1:
		case HERO1:
		case MAN1:
		case MUSHROOM1:
		case NEWTON:
		case CAT1:
		case DEER2:
		case PIGGY1:
		case BUNNY2:
		case HANDSOMEBOY2:
		case HERO2:
		case MAN2:
		case MUSHROOM2:
		case NEWTON2:
		case CAT2:
		case DEER1:
		case PIGGY2:
		positionArray.add(startObjectShape.getPosition().get(0));
		positionArray.add(startObjectLeftWheel.getPosition().get(0));
		positionArray.add(startObjectRightWheel.getPosition().get(0));
		break;
		case APPLE:
			positionArray.add(startObjectShape.getPosition().get(0));
			break;
		}
		return positionArray;
	}
	@Override
	public void setPosition(Vector2 position) {
		startObjectShape.setPosition(position);
	}

	@Override
	public float getDensity() {
		switch (type) {
		case BUNNY1:
		case HANDSOMEBOY1:
		case HERO1:
		case MAN1:
		case MUSHROOM1:
		case NEWTON:
		case CAT1:
		case DEER1:
		case PIGGY1:
		case BUNNY2:
		case HANDSOMEBOY2:
		case HERO2:
		case MAN2:
		case MUSHROOM2:
		case NEWTON2:
		case CAT2:
		case DEER2:
		case PIGGY2:
		return virtualDencity;
		case APPLE:
			return startObjectShape.getDensity();
		}
		return 0;
	}

	@Override
	public void setDensity(float density) {
		switch (type) {
		case BUNNY1:
		case HANDSOMEBOY1:
		case HERO1:
		case MAN1:
		case MUSHROOM1:
		case NEWTON:
		case CAT1:
		case DEER1:
		case PIGGY1:
		case BUNNY2:
		case HANDSOMEBOY2:
		case HERO2:
		case MAN2:
		case MUSHROOM2:
		case NEWTON2:
		case CAT2:
		case DEER2:
		case PIGGY2:
			this.virtualDencity=density;
			break;
		case APPLE:
			startObjectShape.setDensity(density);
			break;
		}
	}
	@Override
	public float getFriction() {
		switch (type) {
		case BUNNY1:
		case HANDSOMEBOY1:
		case HERO1:
		case MAN1:
		case MUSHROOM1:
		case NEWTON:
		case CAT1:
		case DEER1:
		case PIGGY1:
		case BUNNY2:
		case HANDSOMEBOY2:
		case HERO2:
		case MAN2:
		case MUSHROOM2:
		case NEWTON2:
		case CAT2:
		case DEER2:
		case PIGGY2:
			return startObjectLeftWheel.getFriction();
		case APPLE:
			return startObjectShape.getFriction();
		}
		return 0;
	}

	@Override
	public void setFriction(float friction) {
		switch (type) {
		case BUNNY1:
		case HANDSOMEBOY1:
		case HERO1:
		case MAN1:
		case MUSHROOM1:
		case BUNNY2:
		case HANDSOMEBOY2:
		case HERO2:
		case MAN2:
		case MUSHROOM2:
		case NEWTON:
		case CAT1:
		case DEER1:
		case PIGGY1:
		case CAT2:
		case DEER2:
		case PIGGY2:
		case NEWTON2:
			startObjectLeftWheel.setFriction(friction);
			startObjectRightWheel.setFriction(friction);
			break;
		case APPLE:
			startObjectShape.setFriction(friction);
			break;
		}
		
	}

	@Override
	public float getRestitution() {
		switch (type) {
		case BUNNY1:
		case HANDSOMEBOY1:
		case HERO1:
		case MAN1:
		case MUSHROOM1:
		case NEWTON:
		case CAT1:
		case DEER1:
		case PIGGY1:
		case BUNNY2:
		case HANDSOMEBOY2:
		case HERO2:
		case MAN2:
		case MUSHROOM2:
		case NEWTON2:
		case CAT2:
		case DEER2:
		case PIGGY2:
			return startObjectLeftWheel.getFriction();
		case APPLE:
			return startObjectShape.getRestitution();
		}
		
		return 0;
	}

	@Override
	public void setRestitution(float restitution) {
		switch (type) {
		case BUNNY1:
		case HANDSOMEBOY1:
		case HERO1:
		case MAN1:
		case MUSHROOM1:
		case NEWTON:
		case CAT1:
		case DEER1:
		case PIGGY1:
		case BUNNY2:
		case HANDSOMEBOY2:
		case HERO2:
		case MAN2:
		case MUSHROOM2:
		case NEWTON2:
		case CAT2:
		case DEER2:
		case PIGGY2:
			startObjectLeftWheel.setRestitution(restitution);
			startObjectRightWheel.setRestitution(restitution);
			break;
		case APPLE:
			startObjectShape.setRestitution(restitution);
			
		}
		
	}

	@Override
	public ArrayList<Body> getBody() {
		 bodyArray.clear();
		 switch (type) {
			case BUNNY1:
			case HANDSOMEBOY1:
			case HERO1:
			case MAN1:
			case MUSHROOM1:
			case NEWTON:
			case CAT1:
			case DEER1:
			case PIGGY1:
			case BUNNY2:
			case HANDSOMEBOY2:
			case HERO2:
			case MAN2:
			case MUSHROOM2:
			case NEWTON2:
			case CAT2:
			case DEER2:
			case PIGGY2:
				bodyArray.add(startObjectRightWheel.getBody().get(0));
				break;
			case APPLE:
				bodyArray.add(startObjectShape.getBody().get(0));
				break;
			}
		 
		 return bodyArray;
	}

	@Override
	public void setWidth(float width) {
		switch (type) {
		case BUNNY1:
		case HANDSOMEBOY1:
		case HERO1:
		case MAN1:
		case MUSHROOM1:
		case NEWTON:
		case CAT1:
		case DEER1:
		case PIGGY1:
		case BUNNY2:
		case HANDSOMEBOY2:
		case HERO2:
		case MAN2:
		case MUSHROOM2:
		case NEWTON2:
		case CAT2:
		case DEER2:
		case PIGGY2:
			break;
		default:
			break;
		}
	}

	@Override
	public float getWidth() {
		return 0;
	}

	@Override
	public void setHeight(float height) {
		
	}

	@Override
	public float getHeight() {
		return 0;
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
		switch (type) {
		case BUNNY1:
		case HANDSOMEBOY1:
		case HERO1:
		case MAN1:
		case MUSHROOM1:
		case NEWTON:
		case CAT1:
		case DEER1:
		case PIGGY1:
		case BUNNY2:
		case HANDSOMEBOY2:
		case HERO2:
		case MAN2:
		case MUSHROOM2:
		case NEWTON2:
		case CAT2:
		case DEER2:
		case PIGGY2:
			basicObjectArray.add(startObjectShape);
			basicObjectArray.add(startObjectLeftWheel);
			basicObjectArray.add(startObjectRightWheel);
			break;
		case APPLE:
			basicObjectArray.add(startObjectShape);
			break;
		}
		
		return basicObjectArray;
	}
	public void setProperties(float density, float friction, float restitution,float width,float height,float movementSpeed,int lifeCount,boolean controlMovement){
		switch (type) {
		case BUNNY1:
		case HANDSOMEBOY1:
		case HERO1:
		case MAN1:
		case MUSHROOM1:
		case NEWTON:
		case CAT1:
		case DEER1:
		case PIGGY1:
		case BUNNY2:
		case HANDSOMEBOY2:
		case HERO2:
		case MAN2:
		case MUSHROOM2:
		case NEWTON2:
		case CAT2:
		case DEER2:
		case PIGGY2:
			this.virtualDencity=density;
			startObjectLeftWheel.setFriction(friction);
			startObjectRightWheel.setFriction(friction);
			startObjectLeftWheel.setRestitution(restitution);
			startObjectRightWheel.setRestitution(restitution);
			this.movementSpeed=movementSpeed;
			setControlMovement(controlMovement);
			break;
		case APPLE:
			startObjectShape.setDensity(density);
			startObjectShape.setFriction(friction);
			startObjectShape.setRestitution(restitution);
			break;
		}
		setLifeCount(lifeCount);
	}
	public WheelJoint[] getWheelJoint() {
		return wheelJoint;
	}
	public void setWheelJoint(WheelJoint[] wheelJoint) {
		this.wheelJoint = wheelJoint;
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
	public float getAnimationSpeed() {
		return animationSpeed;
	}
	public void setAnimationSpeed(float animationSpeed) {
		this.animationSpeed = animationSpeed;
	}
	//raneen
	public int getLifeCount() {
		return lifeCount;
	}
	public void setLifeCount(int lifeCount) {
		this.lifeCount = lifeCount;
	}
	public boolean isControlMovement() {
		return controlMovement;
	}
	public void setControlMovement(boolean controlMovement) {
		this.controlMovement = controlMovement;
	}
}

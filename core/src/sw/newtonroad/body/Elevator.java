package sw.newtonroad.body;

import java.util.ArrayList;

import sw.newtonroad.body.BasicObject;
import sw.newtonroad.handlers.AppVar;
import sw.newtonroad.joint.Distance;
import sw.newtonroad.joint.GameJoint;
import sw.newtonroad.joint.Revolute;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.PrismaticJoint;

public class Elevator extends GameObject {
	private World world;
	private AppVar.elevatorType type;
	private String img;
	private BasicObject elevator;
	private BasicObject basic;
	private float virtualDencity;
	private PrismaticJoint pj;
	private float motorSpeed;
	private float motorForce;
	public Elevator(World world,AppVar.elevatorType type,String img,float x,float y){
		this.world=world;
		this.type=type;
		this.img=img;
		positionArray=new ArrayList<Vector2>();
		bodyArray=new ArrayList<Body>();
		gameJoint=new ArrayList<GameJoint>();
		basicObjectArray=new ArrayList<BasicObject>();
		jointInfo=new String[4];
		secondJoint=false;
		virtualDencity=4.2f;
		motorForce=13;
		motorSpeed=400;
		switch (type) {
		case ELEVATORUP:
			elevator = new BasicObject(world, BodyType.DynamicBody, AppVar.basicBodyType.ELEVATORUP,img,x,y);
			elevator.setDensity(0);
			basic = new BasicObject(world, BodyType.DynamicBody, AppVar.basicBodyType.BOX, "test.png",elevator.getPosition().get(0).x,elevator.getPosition().get(0).y-2);
			basic.getFixtureDef().filter.categoryBits =2;
			basic.getFixtureDef().filter.maskBits=2;
			basic.setWidth(.1f);
			basic.setHeight(4);
			basic.setDensity(1);
			pj=AppVar.createPrismaticJoint(world, elevator.getBody().get(0), basic.getBody().get(0), elevator.getPosition().get(0), new Vector2(0, -1));
			break;
		case ELEVATERDOWN:
			elevator = new BasicObject(world, BodyType.DynamicBody, AppVar.basicBodyType.BOX,img,x,y);
			elevator.setWidth(1.2f);
			elevator.setHeight(1.5f);
			elevator.setDensity(0);
			basic = new BasicObject(world, BodyType.DynamicBody, AppVar.basicBodyType.BOX, "test.png",elevator.getPosition().get(0).x,elevator.getPosition().get(0).y+2);
			basic.getFixtureDef().filter.categoryBits =2;
			basic.getFixtureDef().filter.maskBits=2;
			basic.setWidth(.1f);
			basic.setHeight(4f);
			basic.setDensity(1);
			pj=AppVar.createPrismaticJoint(world, elevator.getBody().get(0), basic.getBody().get(0), elevator.getPosition().get(0), new Vector2(0, 1));
			break;
		case ELEVATORLEFT:
			elevator = new BasicObject(world, BodyType.DynamicBody, AppVar.basicBodyType.BOX,img,x,y);
			elevator.setWidth(1.2f);
			elevator.setHeight(1.5f);
			elevator.setDensity(0);
			basic = new BasicObject(world, BodyType.DynamicBody, AppVar.basicBodyType.BOX, "test.png",elevator.getPosition().get(0).x-2,elevator.getPosition().get(0).y);
			basic.getFixtureDef().filter.categoryBits =2;
			basic.getFixtureDef().filter.maskBits=2;
			basic.setWidth(4f);
			basic.setHeight(.1f);
			basic.setDensity(1);
			pj=AppVar.createPrismaticJoint(world, elevator.getBody().get(0), basic.getBody().get(0), elevator.getPosition().get(0), new Vector2(-1, 0));
			break;
		case ELEVATORRIGHT:
			elevator = new BasicObject(world, BodyType.DynamicBody, AppVar.basicBodyType.BOX,img,x,y);
			elevator.setWidth(1.2f);
			elevator.setHeight(1.5f);
			elevator.setDensity(0);
			basic = new BasicObject(world, BodyType.DynamicBody, AppVar.basicBodyType.BOX, "test.png",elevator.getPosition().get(0).x+2,elevator.getPosition().get(0).y);
			basic.getFixtureDef().filter.categoryBits =2;
			basic.getFixtureDef().filter.maskBits=2;
			basic.setWidth(4f);
			basic.setHeight(.1f);
			basic.setDensity(1);
			pj=AppVar.createPrismaticJoint(world, elevator.getBody().get(0), basic.getBody().get(0), elevator.getPosition().get(0), new Vector2(1, 0));
			break;
		default:
			break;
		}
	}
	@Override
	public void setDynamic(Vector2 position) {
		// TODO Auto-generated method stub
		//elevator.setDynamic(position);
		basic.getBody().get(0).setType(BodyType.StaticBody);
		pj.enableMotor(true);
		pj.setMotorSpeed(motorSpeed);
		pj.setMaxMotorForce(motorForce);
		pj.enableLimit(true);
		elevator.setDensity(1);
		elevator.getBody().get(0).setAngularVelocity(.0001f);
	}

	@Override
	public void setStatic(Vector2 position) {
		// TODO Auto-generated method stub
		basic.getBody().get(0).setType(BodyType.DynamicBody);
		elevator.setStatic(position);
		pj.enableMotor(false);
		pj.enableLimit(false);
		elevator.setDensity(0);
	}

	@Override
	public ArrayList<Vector2> getPosition() {
		// TODO Auto-generated method stub
		positionArray.clear();
		positionArray.add(elevator.getPosition().get(0));
		positionArray.add(basic.getPosition().get(0));
		return positionArray;
	}

	@Override
	public void setPosition(Vector2 position) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float getDensity() {
		// TODO Auto-generated method stub
		return virtualDencity;
	}

	@Override
	public void setDensity(float density) {
		// TODO Auto-generated method stub
		virtualDencity=density;
	}

	@Override
	public float getFriction() {
		// TODO Auto-generated method stub
		return elevator.getFriction();
	}

	@Override
	public void setFriction(float friction) {
		// TODO Auto-generated method stub
		elevator.setFriction(friction);
	}

	@Override
	public float getRestitution() {
		// TODO Auto-generated method stub
		return elevator.getRestitution();
	}

	@Override
	public void setRestitution(float restitution) {
		// TODO Auto-generated method stub
		elevator.setRestitution(restitution);
	}

	@Override
	public ArrayList<Body> getBody() {
		// TODO Auto-generated method stub
		 bodyArray.clear();
		 bodyArray.add(elevator.getBody().get(0));
		 bodyArray.add(basic.getBody().get(0));
		return bodyArray;
	}

	@Override
	public void setWidth(float width) {
		// TODO Auto-generated method stub
		elevator.setWidth(width);
	}

	@Override
	public float getWidth() {
		// TODO Auto-generated method stub
		return elevator.getWidth();
	}

	@Override
	public void setHeight(float height) {
		// TODO Auto-generated method stub
		elevator.setHeight(height);
	}

	@Override
	public float getHeight() {
		// TODO Auto-generated method stub
		return elevator.getHeight();
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
		// TODO Auto-generated method stub
		basicObjectArray.clear();
		basicObjectArray.add(elevator);
		basicObjectArray.add(basic);
		return basicObjectArray;
	}
	public void setProperties(float density,float friction,float restitution,float width,float height,float motorSpeed,float motorForce){
		virtualDencity=density;
		elevator.setFriction(friction);
		elevator.setRestitution(restitution);
		elevator.setWidth(width);
		elevator.setHeight(height);
		this.motorSpeed=motorSpeed;
		this.motorForce=motorForce;
	}

	public World getWorld() {
		return world;
	}
	public void setWorld(World world) {
		this.world = world;
	}
	public AppVar.elevatorType getType() {
		return type;
	}
	public void setType(AppVar.elevatorType type) {
		this.type = type;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public float getMotorSpeed() {
		return motorSpeed;
	}
	public void setMotorSpeed(float motorSpeed) {
		this.motorSpeed = motorSpeed;
	}
	public float getMotorForce() {
		return motorForce;
	}
	public void setMotorForce(float motorForce) {
		this.motorForce = motorForce;
	}
	
}

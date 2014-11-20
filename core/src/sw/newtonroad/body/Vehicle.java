package sw.newtonroad.body;
import java.util.ArrayList;
import sw.newtonroad.handlers.AppVar;
import sw.newtonroad.handlers.AppVar.basicBodyType;
import sw.newtonroad.joint.Distance;
import sw.newtonroad.joint.GameJoint;
import sw.newtonroad.joint.Revolute;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.WheelJoint;
public class Vehicle extends GameObject  {
	private World world;
	private AppVar.vehicleType type;
	private String img;
	private String wheelimg;
	private BasicObject firstShape;
	private BasicObject secondShape;
	private BasicObject thirdShape;
	private BasicObject leftWheel,rightWheel;
	private float motorSpeed;
	private boolean controlMovement;
	private WheelJoint [] wheelJoint;
	//raneen
	private boolean takeSound=false;
	private Music music;
	public Vehicle(World world ,AppVar.vehicleType type,String img,String wheelimg, float positionX, float positionY) {
		this.world = world;
		this.type=type;
		this.img=img;
		this.wheelimg=wheelimg;
		positionArray=new ArrayList<Vector2>();
		bodyArray=new ArrayList<Body>();
		gameJoint=new ArrayList<GameJoint>();
		basicObjectArray=new ArrayList<BasicObject>();
		jointInfo=new String[4];
		secondJoint=false;
		setControlMovement(false);
		//raneen
		takeSound=false;
		music = Gdx.audio.newMusic(Gdx.files.internal("music/car.wav"));
		switch (type) {
		case CAR:
			width=4;//width shape
			height=1.5f;//height shape
			motorSpeed=-50;
			firstShape=new BasicObject(world, BodyType.DynamicBody, basicBodyType.CARSHAPE, img, positionX, positionY);
			leftWheel = new BasicObject(world, BodyType.DynamicBody,basicBodyType.WHEEL,wheelimg ,positionX, positionY);
			rightWheel = new BasicObject(world, BodyType.DynamicBody,basicBodyType.WHEEL, wheelimg,positionX, positionY);
			wheelJoint=AppVar.createWheelJoint(world, firstShape.getBody().get(0), leftWheel.getBody().get(0), rightWheel.getBody().get(0), width+2,height, firstShape.getDensity(),10);
			break;
		case SMALLCAR:
			width=2;//width shape
			height=1.3f;//height shape
			motorSpeed=-200;
			firstShape=new BasicObject(world, BodyType.DynamicBody, basicBodyType.CARSHAPE, img, positionX, positionY);
			leftWheel = new BasicObject(world, BodyType.DynamicBody,basicBodyType.WHEEL, wheelimg,positionX, positionY);
			rightWheel = new BasicObject(world, BodyType.DynamicBody,basicBodyType.WHEEL, wheelimg,positionX, positionY);
			firstShape.setWidth(2f);
			firstShape.setHeight(1.3f);
           // firstShape.setDensity(7);
            leftWheel.setRadius(2.3f/3f);
            rightWheel.setRadius(2.3f/3f);
            leftWheel.setFriction(.5f);
            rightWheel.setFriction(0);
			wheelJoint=AppVar.createWheelJoint(world, firstShape.getBody().get(0), leftWheel.getBody().get(0), rightWheel.getBody().get(0), width+1.9f,height, firstShape.getDensity(),5);
			break;
		case SKATE:
			width=2;//width shape
			height=.7f;//height shape
			motorSpeed=50;
			firstShape=new BasicObject(world, BodyType.DynamicBody, basicBodyType.BOX,img, positionX, positionY);
			firstShape.setProperties(1.5f, .2f, .4f, 0,0,6f,.4f,false,false,1,false); 
			leftWheel = new BasicObject(world, BodyType.DynamicBody,basicBodyType.WHEEL,wheelimg,positionX, positionY);
			rightWheel = new BasicObject(world, BodyType.DynamicBody,basicBodyType.WHEEL, wheelimg,positionX, positionY);
			firstShape.setWidth(2);
			firstShape.setHeight(.7f);
			firstShape.setDensity(2);
			firstShape.setDensity(7);
			leftWheel.setRadius(.7f);
			rightWheel.setRadius(.7f);
		    wheelJoint=AppVar.createWheelJoint(world, firstShape.getBody().get(0), leftWheel.getBody().get(0), rightWheel.getBody().get(0), width+3,height, firstShape.getDensity(),5);
			break;
		case LARGESKATE:
			width=6;//width shape
			height=.8f;//height shape
			motorSpeed=-85;
			firstShape=new BasicObject(world, BodyType.DynamicBody, basicBodyType.BOX, img, positionX, positionY);
			firstShape.setProperties(1.5f, .2f, .4f, 0,0,6f,.4f,false,false,1,false); 
			leftWheel = new BasicObject(world, BodyType.DynamicBody,basicBodyType.WHEEL, wheelimg,positionX, positionY);
			rightWheel = new BasicObject(world, BodyType.DynamicBody,basicBodyType.WHEEL, "image/vehicle/LARGESKATEWHEEL.png",positionX, positionY);
			firstShape.setWidth(6);
			firstShape.setHeight(.8f);
			firstShape.setDensity(2);
			firstShape.setDensity(7);
			leftWheel.setRadius(1.5f);
			rightWheel.setRadius(1.5f);
			  wheelJoint=AppVar.createWheelJoint(world, firstShape.getBody().get(0), leftWheel.getBody().get(0), rightWheel.getBody().get(0), width+3.5f,height+.5f, firstShape.getDensity(),15);
			
			break;
		case SKOTER:
			width=3.1f;//width shape
			height=.4f;//height shape
			motorSpeed=-60;
			firstShape=new BasicObject(world, BodyType.DynamicBody, basicBodyType.BOX, img, positionX, positionY);
			firstShape.setProperties(1.5f, .2f, .4f, 0,0,6f,.4f,false,false,1,false); 
			secondShape = new BasicObject(world, BodyType.DynamicBody, basicBodyType.BOX, img, positionX+3, positionY+2.1f);		
			firstShape.setProperties(1.5f, .2f, .4f, 0,0,6f,.4f,false,false,1,false); 
			leftWheel = new BasicObject(world, BodyType.DynamicBody,basicBodyType.WHEEL, wheelimg,positionX, positionY);
			rightWheel = new BasicObject(world, BodyType.DynamicBody,basicBodyType.WHEEL, wheelimg,positionX, positionY);
			firstShape.setWidth(3.1f);
			firstShape.setHeight(.4f);
			secondShape.setWidth(.35f);
			secondShape.setHeight(2.5f);
			secondShape.getBody().get(0).setTransform(secondShape.getBody().get(0).getPosition(), -41);
			leftWheel.setRadius(.6f);
			rightWheel.setRadius(.6f);
			
		//	firstShape.getBody().get(0).setTransform(firstShape.getBody().get(0).getPosition(), .5f);
		    wheelJoint=AppVar.createWheelJoint(world, firstShape.getBody().get(0), leftWheel.getBody().get(0), rightWheel.getBody().get(0), width+1,height+1, firstShape.getDensity(),5);
		    secondShape.setDensity(0);
		    firstShape.setDensity(0);
		    AppVar.createRevoluteJoint(world, firstShape.getBody().get(0), secondShape.getBody().get(0),
		    		new Vector2(firstShape.getPosition().get(0).x+2.5f,firstShape.getPosition().get(0).y));
		    break;
		
		case BUKET:
			width=4f;//width shape
			height=1.8f;//height shape
			motorSpeed=-30;
			firstShape=new BasicObject(world, BodyType.DynamicBody, basicBodyType.BUKETSTAND, img, positionX, positionY);
			secondShape = new BasicObject(world, BodyType.DynamicBody, basicBodyType.BUKETSHAPE, img, positionX+3.5f, positionY+1.3f);		
			thirdShape = new BasicObject(world, BodyType.DynamicBody, basicBodyType.BUKETSHAPE,img, positionX-3.5f, positionY+1.3f);		
			secondShape.setDensity(0);
			thirdShape.setDensity(0);
			leftWheel = new BasicObject(world, BodyType.DynamicBody,basicBodyType.BUKETWHEEL, wheelimg,positionX, positionY);
			rightWheel = new BasicObject(world, BodyType.DynamicBody,basicBodyType.BUKETWHEEL, wheelimg,positionX, positionY);
		    wheelJoint=AppVar.createWheelJoint(world, firstShape.getBody().get(0), leftWheel.getBody().get(0), rightWheel.getBody().get(0), width,height, firstShape.getDensity(),5);
		    secondShape.setDensity(0);
		    AppVar.createRevoluteJoint(world, firstShape.getBody().get(0), secondShape.getBody().get(0),
		    		new Vector2(firstShape.getPosition().get(0).x+3.5f,firstShape.getPosition().get(0).y));	
		    AppVar.createRevoluteJoint(world, firstShape.getBody().get(0), thirdShape.getBody().get(0),
		    		new Vector2(firstShape.getPosition().get(0).x-3.5f,firstShape.getPosition().get(0).y));	
		    
			break;		
		default:
			break;
		}
	}
	@Override
	public void setDynamic(Vector2 position) {
		switch (type) {
		case CAR:
		case SMALLCAR:
		case SKATE:
		case LARGESKATE:
		firstShape.setDynamic(firstShape.getPosition().get(0));
		leftWheel.setDynamic(leftWheel.getPosition().get(0));
		rightWheel.setDynamic(rightWheel.getPosition().get(0));
		break;
		case SKOTER:
			firstShape.setDynamic(firstShape.getPosition().get(0));
			secondShape.setDynamic(secondShape.getPosition().get(0));
			leftWheel.setDynamic(leftWheel.getPosition().get(0));
			rightWheel.setDynamic(rightWheel.getPosition().get(0));
			break;
		case BUKET:
			firstShape.setDynamic(firstShape.getPosition().get(0));
			secondShape.setDynamic(secondShape.getPosition().get(0));
			secondShape.setDensity(0);
			thirdShape.setDynamic(secondShape.getPosition().get(0));
			thirdShape.setDensity(0);
			leftWheel.setDynamic(leftWheel.getPosition().get(0));
			rightWheel.setDynamic(rightWheel.getPosition().get(0));
			break;
		}
		if(!controlMovement){
		wheelJoint[0].enableMotor(true);
		wheelJoint[1].enableMotor(true);
		wheelJoint[0].setMotorSpeed(motorSpeed);
		wheelJoint[1].setMotorSpeed(motorSpeed);
		}
		//raneen
		if(takeSound==true){music.play();music.setLooping(true);}
		for(int i=0;i<gameJoint.size();i++){
			System.out.println("car set dynamic");
			gameJoint.get(i).setDynamic();
		}
	}
	@Override
	public void setStatic(Vector2 position) {
		switch (type) {
		case CAR:
		case SMALLCAR:
		case SKATE:
		case LARGESKATE:
		firstShape.setStatic(position);
		leftWheel.setStatic(position);
		rightWheel.setStatic(position);
		wheelJoint[0].enableMotor(false);
		wheelJoint[1].enableMotor(false);
		break;
		case SKOTER:
			firstShape.setStatic(position);
			secondShape.setStatic(new Vector2(position.x+4,position.y+2.1f));
			secondShape.getBody().get(0).setTransform(secondShape.getBody().get(0).getPosition(), -41);
			secondShape.setDensity(0);
			firstShape.setDensity(0);
			leftWheel.setStatic(position);
			rightWheel.setStatic(position);
			wheelJoint[0].enableMotor(false);
			wheelJoint[1].enableMotor(false);
			break;
		case BUKET:
			firstShape.setStatic(position);
			secondShape.setStatic();
			thirdShape.setStatic();
			secondShape.setDensity(0);
			thirdShape.setDensity(0);
			leftWheel.setStatic(position);
			rightWheel.setStatic(position);
			wheelJoint[0].enableMotor(false);
			wheelJoint[1].enableMotor(false);
			break;
		}
		//raneen
		if(takeSound==true){music.pause();}
		for(int i=0;i<gameJoint.size();i++){
			System.out.println("car static ");
			gameJoint.get(i).setStatic();
		}
	}
	@Override
	public ArrayList<Vector2> getPosition() {
		positionArray.clear();
		switch (type) {
		case CAR:
		case SMALLCAR:
		case SKATE:
		case LARGESKATE:
		positionArray.add(firstShape.getPosition().get(0));
		positionArray.add(leftWheel.getPosition().get(0));
		positionArray.add(rightWheel.getPosition().get(0));
		break;
		case SKOTER:
			positionArray.add(firstShape.getPosition().get(0));
			positionArray.add(secondShape.getPosition().get(0));
			positionArray.add(leftWheel.getPosition().get(0));
			positionArray.add(rightWheel.getPosition().get(0));
			break;
		case BUKET:
			positionArray.add(firstShape.getPosition().get(0));
			positionArray.add(secondShape.getPosition().get(0));
			positionArray.add(thirdShape.getPosition().get(0));
			positionArray.add(leftWheel.getPosition().get(0));
			positionArray.add(rightWheel.getPosition().get(0));
			break;
		}
		return positionArray;
	}
	@Override
	public void setPosition(Vector2 position) {
		
	}
	@Override
	public float getDensity() {
		switch (type) {
		case CAR:
		case SMALLCAR:
		case SKATE:
		case LARGESKATE:
			return firstShape.getDensity()+leftWheel.getDensity()+rightWheel.getDensity();
		case SKOTER:
			return firstShape.getDensity()+secondShape.getDensity()+leftWheel.getDensity()+rightWheel.getDensity();
		case BUKET:
			return firstShape.getDensity()+secondShape.getDensity()+thirdShape.getDensity()+leftWheel.getDensity()+rightWheel.getDensity();
		default:
			return 0;
			
		
		}
	
	
	}
	@Override
	public void setDensity(float density) {
		switch (type) {
		case CAR:
		case SMALLCAR:
		case SKATE:
		case LARGESKATE:
		firstShape.setDensity(density*(5/7f));
		leftWheel.setDensity(density*(1/7f));
		rightWheel.setDensity(density*(1/7f));
		break;
		case SKOTER:
			firstShape.setDensity(density*(5/7f));
			//hon
			secondShape.setDensity(0);
			leftWheel.setDensity(density*(1/7f));
			rightWheel.setDensity(density*(1/7f));
			break;
		case BUKET:
			firstShape.setDensity(density*(5/7f));
			//hon
			secondShape.setDensity(0);
			thirdShape.setDensity(0);
			leftWheel.setDensity(density*(1/7f));
			rightWheel.setDensity(density*(1/7f));
			break;
		}
	}
	@Override
	public float getFriction() {
		return leftWheel.getFriction();
	}
	@Override
	public void setFriction(float friction) {
		leftWheel.setFriction(friction);
		rightWheel.setFriction(friction);
	}
	@Override
	public float getRestitution() {
		return leftWheel.getRestitution();
	}
	@Override
	public void setRestitution(float restitution) {
		leftWheel.setRestitution(restitution);
		rightWheel.setRestitution(restitution);
	}
	public float getMotorSpeed(){
		return motorSpeed;
	}
	public void setMotorSpeed(float motorSpeed){
		this.motorSpeed=motorSpeed;
	}
	@Override
	public ArrayList<Body> getBody() {
		bodyArray.clear();
		switch (type) {
		case SKOTER:
			bodyArray.add(secondShape.getBody().get(0));
		case CAR:
		case SMALLCAR:
		case SKATE:
		case LARGESKATE:
		bodyArray.add(leftWheel.getBody().get(0));
		bodyArray.add(rightWheel.getBody().get(0));
		break;
		}
		return bodyArray;
	}
	@Override
	public ArrayList<BasicObject> getBasicObject() {
		basicObjectArray.clear();
		switch (type) {
		case CAR: 
		case SMALLCAR:
		case SKATE:
		case LARGESKATE:
			basicObjectArray.add(firstShape);
			basicObjectArray.add(leftWheel);
			basicObjectArray.add(rightWheel);
			break;
		case SKOTER:
			basicObjectArray.add(firstShape);
			basicObjectArray.add(secondShape);
			basicObjectArray.add(leftWheel);
			basicObjectArray.add(rightWheel);
			break;
		case BUKET:
			basicObjectArray.add(firstShape);
			basicObjectArray.add(secondShape);
			basicObjectArray.add(thirdShape);
			basicObjectArray.add(leftWheel);
			basicObjectArray.add(rightWheel);
			break;
		}
		return basicObjectArray;
	}
	public void setProperties(float density, float friction, float restitution,float motorSpeed ,float width,float height,boolean takeSound,boolean controlMovement) {
		switch (type) {
		case CAR:
		case SMALLCAR:
		case SKATE:
		case LARGESKATE:
		firstShape.setDensity(density*(5/7f));
		leftWheel.setDensity(density*(1/7f));
		rightWheel.setDensity(density*(1/7f));
		leftWheel.setFriction(friction);
		rightWheel.setFriction(friction);
		leftWheel.setRestitution(restitution);
		rightWheel.setRestitution(restitution);
		this.motorSpeed=motorSpeed;
		break;
		case SKOTER:
			firstShape.setDensity(density*(5/7f));
			//hon
			secondShape.setDensity(0);
			leftWheel.setDensity(density*(1/7f));
			rightWheel.setDensity(density*(1/7f));
			leftWheel.setFriction(friction);
			rightWheel.setFriction(friction);
			leftWheel.setRestitution(restitution);
			rightWheel.setRestitution(restitution);
			this.motorSpeed=motorSpeed;
			break;
		}
		this.setWidth(width);
		this.setHeight(height);
		//raneen
		setTakeSound(takeSound);
		setControlMovement(controlMovement);
	}
	@Override
	public void setWidth(float width) {
		switch (type) {
		case SKOTER:
			secondShape.setWidth((width/3.1f)*(.35f));
			break;
		}
		
		firstShape.setWidth(width);
		this.width=width;
		
	}
	@Override
	public float getWidth() {
		return width;
	}
	@Override
	public void setHeight(float height) {
		switch (type) {
		case SKOTER:
			secondShape.setHeight((height/.4f)*(2.5f));
			break;
		}
		firstShape.setHeight(height);
		this.height=height;
	}
	@Override
	public float getHeight() {
		return height;
	}
	@Override
	public void setRadius(float radius) {
	}
	@Override
	public float getRadius() {
		return 0;
	}
	public World getWorld() {
		return world;
	}
	public void setWorld(World world) {
		this.world = world;
	}
	public AppVar.vehicleType getType() {
		return type;
	}
	public void setType(AppVar.vehicleType type) {
		this.type = type;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getWheelimg() {
		return wheelimg;
	}
	public void setWheelimg(String wheelimg) {
		this.wheelimg = wheelimg;
	}
	//raneen
		public boolean isTakeSound() {
			return takeSound;
		}
		public void setTakeSound(boolean takeSound) {
			this.takeSound = takeSound;
		}
		public boolean isControlMovement() {
			return controlMovement;
		}
		public void setControlMovement(boolean controlMovement) {
			this.controlMovement = controlMovement;
		}
		public WheelJoint[] getWheelJoint() {
			return wheelJoint;
		}
		public void setWheelJoint(WheelJoint[] wheelJoint) {
			this.wheelJoint = wheelJoint;
		}
}

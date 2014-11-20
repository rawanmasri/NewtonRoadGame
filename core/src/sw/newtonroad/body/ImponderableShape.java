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
import com.badlogic.gdx.physics.box2d.joints.DistanceJoint;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint;

public class ImponderableShape extends GameObject {
	private World world;
	private AppVar.imponderableType type;
	private String img;
	private BasicObject basic;
	private BasicObject center;
	private RevoluteJoint revoluteJoint;
	private float maxMotorTorque;
	private float motorSpeed;
	//raneen
	private boolean takeSound=false;
	private Music music;
	public ImponderableShape(World world,AppVar.imponderableType type,String img,float x,float y){
		this.world = world;
		this.type=type;
		this.img = img;
		positionArray=new ArrayList<Vector2>();
		bodyArray=new ArrayList<Body>();
		gameJoint=new ArrayList<GameJoint>();
		basicObjectArray=new ArrayList<BasicObject>();
		jointInfo=new String[4];
		secondJoint=false;
		//raneen
		takeSound=false;
		music = Gdx.audio.newMusic(Gdx.files.internal("music/impon.wav"));
		switch (type) {
		case LARGEPENDULUM:
			maxMotorTorque=2000;
			motorSpeed=1;
			basic= new BasicObject(world,BodyType.DynamicBody,AppVar.basicBodyType.LARGEPENDULUM,img,x,y);
			center=new BasicObject(world,BodyType.DynamicBody,AppVar.basicBodyType.WHEEL,"image/imponderable/LARGEPENDULUMCIRCLE.png",x,  y+4);
			revoluteJoint=AppVar.createRevoluteJoint(world,basic.getBody().get(0), center.getBody().get(0), new Vector2(x,  y+4));
			break;
		case SMALLPENDULUM:
			maxMotorTorque=12;
			motorSpeed=3;
			basic= new BasicObject(world,BodyType.DynamicBody,AppVar.basicBodyType.SMALLPENDULUM,img,x,y);
			center=new BasicObject(world,BodyType.DynamicBody,AppVar.basicBodyType.WHEEL,"test.png",x,  y+1.7f);
			revoluteJoint=AppVar.createRevoluteJoint(world,basic.getBody().get(0), center.getBody().get(0), new Vector2(x,  y+1.7f));			
			center.setRadius(.2f);
			break;
		case PENDULUM360:
			maxMotorTorque=1000;
			motorSpeed=10;
			basic= new BasicObject(world,BodyType.DynamicBody,AppVar.basicBodyType.PENDULUM360,img,x,y);
			center=new BasicObject(world,BodyType.DynamicBody,AppVar.basicBodyType.WHEEL,"test.png",x,  y+2.3f);
			revoluteJoint=AppVar.createRevoluteJoint(world,basic.getBody().get(0), center.getBody().get(0), new Vector2(x,  y+2.3f));
			center.setRadius(.2f);
			break;
		case LARGEWOODCUPBOARD:
			maxMotorTorque=1000;
			motorSpeed=10;
			basic= new BasicObject(world,BodyType.DynamicBody,AppVar.basicBodyType.BOX,img,x,y);
			basic.setProperties(1.5f, .2f, .4f, 0,0,6f,.4f,false,false,1,false); 
			basic.setStatic(basic.getPosition().get(0));
			center=new BasicObject(world,BodyType.DynamicBody,AppVar.basicBodyType.WHEEL,"test.png",x,  y);
			revoluteJoint=AppVar.createRevoluteJoint(world,basic.getBody().get(0), center.getBody().get(0), new Vector2(x, y));
			basic.setWidth(.4f);
			basic.setHeight(5.5f);
			center.setRadius(.2f);
			basic.setProperties(10, 0f,1f ,1,0);
			break;
		case LARGEIRONCUPBOARD:
			maxMotorTorque=2000;
			motorSpeed=5;
			basic= new BasicObject(world,BodyType.DynamicBody,AppVar.basicBodyType.LARGEIRONCUPBOARD,img,x,y);
			center=new BasicObject(world,BodyType.DynamicBody,AppVar.basicBodyType.WHEEL,"test.png",x,  y+1f);
			revoluteJoint=AppVar.createRevoluteJoint(world,basic.getBody().get(0), center.getBody().get(0), new Vector2(x,  y+1f));
			center.setRadius(.2f);
			break;
		case SMALLWOODCUPBOARD:
			maxMotorTorque=20;
			motorSpeed=2;
			basic= new BasicObject(world,BodyType.DynamicBody,AppVar.basicBodyType.SMALLWOODCUPBOARD,img,x,y);
			center=new BasicObject(world,BodyType.DynamicBody,AppVar.basicBodyType.WHEEL,"test.png",x,  y);
			revoluteJoint=AppVar.createRevoluteJoint(world,basic.getBody().get(0), center.getBody().get(0), new Vector2(x,  y));
			center.setRadius(.2f);
			break;
		case SMALLIRONCUPBOARD:
			maxMotorTorque=10;
			motorSpeed=2;
			basic= new BasicObject(world,BodyType.DynamicBody,AppVar.basicBodyType.SMALLIRONCUPBOARD,img,x,y);
			center=new BasicObject(world,BodyType.DynamicBody,AppVar.basicBodyType.WHEEL,"test.png",x,  y+.2f);
			revoluteJoint=AppVar.createRevoluteJoint(world,basic.getBody().get(0), center.getBody().get(0), new Vector2(x,  y+.2f));
			center.setRadius(.2f);
			break;
		case SWING:
			maxMotorTorque=10;
			motorSpeed=2;
			basic= new BasicObject(world,BodyType.DynamicBody,AppVar.basicBodyType.SWING,"image/imponderable/SWING.png",x,y);
			center=new BasicObject(world,BodyType.DynamicBody,AppVar.basicBodyType.WHEEL,"image/imponderable/CENTERSWING.png",x+2,  y);
			revoluteJoint=AppVar.createRevoluteJoint(world,basic.getBody().get(0), center.getBody().get(0), new Vector2(x+2,  y));
			center.setRadius(.25f);
			break;
		case SWING360:
			maxMotorTorque=2000;
			motorSpeed=2;
			basic= new BasicObject(world,BodyType.DynamicBody,AppVar.basicBodyType.SWING360,"image/imponderable/SWING360.png",x,y);
			center=new BasicObject(world,BodyType.DynamicBody,AppVar.basicBodyType.WHEEL,"image/imponderable/SWING360CINTER.png",x+4,  y);
			revoluteJoint=AppVar.createRevoluteJoint(world,basic.getBody().get(0), center.getBody().get(0), new Vector2(x+4,  y));
			center.setRadius(.3f);
			break;
		case HORIZONTALSWING:
			maxMotorTorque=50;
			motorSpeed=1;
			basic= new BasicObject(world,BodyType.DynamicBody,AppVar.basicBodyType.HORIZONTALSWING,"image/imponderable/HORIZONTALSWING360.png",x,y);
			center=new BasicObject(world,BodyType.DynamicBody,AppVar.basicBodyType.WHEEL,"image/imponderable/CENTERSWING.png",x+5,  y);
			revoluteJoint=AppVar.createRevoluteJoint(world,basic.getBody().get(0), center.getBody().get(0), new Vector2(x+5,  y));
			center.setRadius(.22f);
			break;
		case HORIZONTALSWING360:
			maxMotorTorque=2000;
			motorSpeed=2;
			basic= new BasicObject(world,BodyType.DynamicBody,AppVar.basicBodyType.HORIZONTALSWING360,img,x,y);
			center=new BasicObject(world,BodyType.DynamicBody,AppVar.basicBodyType.WHEEL,"image/imponderable/CENTERHORIZNTAL360.png",x+5,  y);
			revoluteJoint=AppVar.createRevoluteJoint(world,basic.getBody().get(0), center.getBody().get(0), new Vector2(x+5,  y));
			center.setRadius(.2f);
			break;
		case ROUNDCIRCLESLOW:
			maxMotorTorque=5000;
			motorSpeed=1;
			basic= new BasicObject(world,BodyType.DynamicBody,AppVar.basicBodyType.WHEEL,img,x,y);
			center=new BasicObject(world,BodyType.DynamicBody,AppVar.basicBodyType.WHEEL,"image/imponderable/BALANCECENTER.png",x+5,  y);
			revoluteJoint=AppVar.createRevoluteJoint(world,basic.getBody().get(0), center.getBody().get(0), new Vector2(x+5,  y));
			center.setRadius(.3f);
			basic.setProperties(18, .42f, 1, 0,0);
			break;
		case ROUNDCIRCLEFAST:
			maxMotorTorque=9000;
			motorSpeed=10;
			basic= new BasicObject(world,BodyType.DynamicBody,AppVar.basicBodyType.WHEEL,img,x,y);
			center=new BasicObject(world,BodyType.DynamicBody,AppVar.basicBodyType.WHEEL,"image/imponderable/BALANCECENTER.png",x+2,  y);
			revoluteJoint=AppVar.createRevoluteJoint(world,basic.getBody().get(0), center.getBody().get(0), new Vector2(x+2,  y));
			basic.setRadius(.5f);
			center.setRadius(.22f);
			basic.setProperties(12, 1, 1, 0,0);
			break;
		case BALANCE:
			maxMotorTorque=0;
			motorSpeed=0;
			center=new BasicObject(world, BodyType.DynamicBody, basicBodyType.MEDUIMTRIANGLE , "image/triangle/SMALLWOODTRIANGLE.png", x, y-1.5f);
			center.setDensity(0);
			basic= new BasicObject(world, BodyType.DynamicBody, basicBodyType.BALANCEWOOD, "image/imponderable/BALANCEWOOD.png", x, y);
			AppVar.createDistanceJoin(world,basic.getBody().get(0), center.getBody().get(0), basic.getBody().get(0).getWorldCenter(), 
					new Vector2(center.getBody().get(0).getWorldCenter().x, center.getBody().get(0).getWorldCenter().y+2));
			break;
				
		default:
			break;
		}
		setStatic(new Vector2(x, y));
	}
	@Override
	public void setDynamic(Vector2 position) {
		basic.setDynamic(basic.getPosition().get(0));
		center.setDynamic(center.getPosition().get(0));
		center.changeBodyType(BodyType.StaticBody);
		switch (type) {
		
		/*case LARGEPENDULUM:
			revoluteJointDef.maxMotorTorque=2000;
			revoluteJointDef.motorSpeed=1f;
			break;
		case SMALLPENDULUM:
			revoluteJointDef.maxMotorTorque=12;
			revoluteJointDef.motorSpeed=3f;
			break;
		case PENDULUM360:
			revoluteJointDef.maxMotorTorque=1000;
			revoluteJointDef.motorSpeed=10f;
			break;
		case LARGEWOODCUPBOARD:
			revoluteJointDef.maxMotorTorque=1000;
			revoluteJointDef.motorSpeed=10f;
			break;
		case LARGEIRONCUPBOARD:
			revoluteJointDef.maxMotorTorque=2000;
			revoluteJointDef.motorSpeed=5f;
			break;
		case SMALLWOODCUPBOARD:
			revoluteJointDef.maxMotorTorque=20;
			revoluteJointDef.motorSpeed=2f;
			break;
		case SMALLIRONCUPBOARD:
			revoluteJointDef.maxMotorTorque=10;
			revoluteJointDef.motorSpeed=2f;
			break;
		case SWING:
			revoluteJointDef.maxMotorTorque=10;
			revoluteJointDef.motorSpeed=2f;
			break;
		case SWING360:
			revoluteJointDef.maxMotorTorque=2000;
			revoluteJointDef.motorSpeed=2f;
			break;
		case HORIZONTALSWING:
			revoluteJointDef.maxMotorTorque=50;
			revoluteJointDef.motorSpeed=1f;
			break;
		case HORIZONTALSWING360:
			revoluteJointDef.maxMotorTorque=2000;
			revoluteJointDef.motorSpeed=2f;
			break;
		case ROUNDCIRCLESLOW:
			revoluteJointDef.maxMotorTorque=5000;
			revoluteJointDef.motorSpeed=1f;
			break;
		case ROUNDCIRCLEFAST:
			revoluteJointDef.maxMotorTorque=9000;
			revoluteJointDef.motorSpeed=10f;
			break;*/
		case BALANCE:
			basic.setDynamic(position);
			center.getBody().get(0).setType(BodyType.StaticBody);
			break;
		default:
			revoluteJoint.enableMotor(true);
			revoluteJoint.setMaxMotorTorque(maxMotorTorque);
			revoluteJoint.setMotorSpeed(motorSpeed);
			break;
		}
		//raneen
		if(takeSound==true){music.play();music.setLooping(true);}
		for(int i=0;i<gameJoint.size();i++){
			System.out.println("wind mill set dynamic");
			gameJoint.get(i).setDynamic();
		}
	}
	@Override
	public void setStatic(Vector2 position) {
		switch (type) {
		case BALANCE:
			break;
		default:
			revoluteJoint.enableMotor(false);
			revoluteJoint.setMaxMotorTorque(0);
			revoluteJoint.setMotorSpeed(0);
			break;
		}
		center.changeBodyType(BodyType.DynamicBody);
		basic.setStatic( position);
		center.setStatic( center.getPosition().get(0));
		//raneen
		if(takeSound==true){music.pause();}
		for(int i=0;i<gameJoint.size();i++){
			gameJoint.get(i).setStatic();
		}
	}
	@Override
	public ArrayList<Vector2> getPosition() {
		positionArray.clear();
		positionArray.add(basic.getPosition().get(0));
		positionArray.add(center.getPosition().get(0));
		return positionArray;
	}
	@Override
	public void setPosition(Vector2 position) {
	}
	@Override
	public float getDensity() {
		 return basic.getDensity();
	}
	@Override
	public void setDensity(float density) {
		basic.setDensity(density);
	}
	@Override
	public float getFriction() {
		return basic.getFriction();
	}
	@Override
	public void setFriction(float friction) {
		basic.setFriction(friction);
	}
	@Override
	public float getRestitution() {
		return basic.getRestitution();
	}
	@Override
	public void setRestitution(float restitution) {
		basic.setRestitution(restitution);
	}
	public float getmaxMotorTorque(){
		return maxMotorTorque;
	}
	public void setmaxMotorTorque(float maxMotorTorque){
		this.maxMotorTorque=maxMotorTorque;
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
		bodyArray.add(basic.getBody().get(0));
		bodyArray.add(center.getBody().get(0));
		return bodyArray;
	}
	@Override
	public ArrayList<BasicObject> getBasicObject() {
		basicObjectArray.clear();
		basicObjectArray.add(basic);
		basicObjectArray.add(center);
		return basicObjectArray;
	}
	public void setProperties(float density, float friction, float restitution,float maxMotorTorque,float motorSpeed,float width,float height,boolean takeSound ) {
		basic.setDensity(density);
		basic.setFriction(friction);
		basic.setRestitution(restitution);
		this.maxMotorTorque=maxMotorTorque;
	    this.motorSpeed=motorSpeed;
	    basic.setWidth(width);
	    basic.setHeight(height);
	  //raneen
	  	setTakeSound(takeSound);
	}
	@Override
	public void setWidth(float width) {
		basic.setWidth(width);
		
	}
	@Override
	public float getWidth() {
		return basic.getWidth();
		 
	}
	@Override
	public void setHeight(float height) {
		basic.setHeight(height);
		
	}
	@Override
	public float getHeight() {
		return basic.getHeight();
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
	public World getWorld() {
		return world;
	}
	public void setWorld(World world) {
		this.world = world;
	}
	public AppVar.imponderableType getType() {
		return type;
	}
	public void setType(AppVar.imponderableType type) {
		this.type = type;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	//raneen
	public boolean isTakeSound() {
		return takeSound;
	}
	public void setTakeSound(boolean takeSound) {
		this.takeSound = takeSound;
	}
}

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
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.joints.DistanceJoint;
import com.badlogic.gdx.physics.box2d.joints.DistanceJointDef;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;

public class WindMill extends GameObject {
	private BasicObject Windmill1;
	private BasicObject Windmill2;
	private BasicObject Windmill3;
	private BasicObject Windmill4;
	private float virtualDencity;
	private World world; 
	private AppVar.windMillType type;
	private String img;
	private float angularVelocity;
	//raneen
	private boolean takeSound=false;
	private Music music;
	public WindMill(World world,AppVar.windMillType type,float x,float y,String img){
		positionArray=new ArrayList<Vector2>();
		bodyArray=new ArrayList<Body>();
		gameJoint=new ArrayList<GameJoint>();
		basicObjectArray=new ArrayList<BasicObject>();
		jointInfo=new String[4];
		secondJoint=false;
		 this.world = world;
		 this.type=type;
		 this.img=img;
		 virtualDencity = 9.5f;
		//raneen
		takeSound=false;
		music = Gdx.audio.newMusic(Gdx.files.internal("music/windmill.mp3"));
		switch (type) {
		case CIRCLEWINDMILL:
			angularVelocity=-10f;
			Windmill1 = new BasicObject(world, BodyType.DynamicBody,basicBodyType.BALL, img,x, y);
			Windmill1.setRadius(2.2f);
			break;
		case ROSEWINDMILL:
			angularVelocity=-10f;
			Windmill1 = new BasicObject(world, BodyType.DynamicBody,basicBodyType.BALL, img,x, y);
			Windmill1.setRadius(1.6f);
			break;
		case SMALLROSEWINDMILL:
			angularVelocity=-10f;
			Windmill1 = new BasicObject(world, BodyType.DynamicBody,basicBodyType.BALL, img,x, y);
			Windmill1.setRadius(2/2.5f );
			break;
		
		case MINIWINDMILL:
			angularVelocity=-10f;
			Windmill1 = new BasicObject(world,BodyType.DynamicBody,basicBodyType.BOX,img,x,y);
			Windmill1.setProperties(.5f, .1f, .1f,0,0,.3f,2f,false,false,1,false);
			Windmill1.setStatic(Windmill1.getPosition().get(0));
			Windmill2 = new BasicObject(world,BodyType.DynamicBody,basicBodyType.BOX ,img,x,y);
			Windmill2.setProperties(.5f, .1f, .1f,0,0,.3f,2f,false,false,1,false);
			Windmill2.setStatic(Windmill2.getPosition().get(0));
			Windmill1.setWidth(2);
			Windmill2.setWidth(2);
			Windmill1.setHeight(.1f);
			Windmill2.setHeight(.1f);
			break;
		case MINIWINDMILL90:
			angularVelocity=-10f;
			Windmill1 = new BasicObject(world,BodyType.DynamicBody,basicBodyType.BOX,img,x,y);
			Windmill1.setProperties(.5f, .1f, .1f,0,0,.3f,2f,false,false,1,false);
			Windmill1.setStatic(Windmill1.getPosition().get(0));
			Windmill2 = new BasicObject(world,BodyType.DynamicBody,basicBodyType.BOX ,img,x,y);
			Windmill2.setProperties(.5f, .1f, .1f,0,0,.3f,2f,false,false,1,false);
			Windmill2.setStatic(Windmill2.getPosition().get(0));
			Windmill1.setWidth(2.5f);
			Windmill2.setWidth(.3f);
			Windmill1.setHeight(.3f);
			Windmill2.setHeight(2.5f);
			
			break;
		case IRONWINDMILL:
			angularVelocity=0f;
			Windmill1 = new BasicObject(world,BodyType.DynamicBody,basicBodyType.BOX,img,x,y);
			Windmill1.setProperties(.5f, .1f, .1f,0,0,.3f,2f,false,false,1,false);
			Windmill1.setStatic(Windmill1.getPosition().get(0));
			Windmill2 = new BasicObject(world,BodyType.DynamicBody,basicBodyType.BOX ,img,x,y);
			Windmill2.setProperties(.5f, .1f, .1f,0,0,.3f,2f,false,false,1,false);
			Windmill2.setStatic(Windmill2.getPosition().get(0));
			Windmill3 = new BasicObject(world,BodyType.DynamicBody,basicBodyType.BOX ,img,x,y);
			Windmill3.setProperties(.5f, .1f, .1f,0,0,.3f,2f,false,false,1,false);
			Windmill3.setStatic(Windmill3.getPosition().get(0));
			Windmill4 = new BasicObject(world,BodyType.DynamicBody,basicBodyType.BOX ,img,x,y);
			Windmill4.setProperties(.5f, .1f, .1f,0,0,.3f,2f,false,false,1,false);
			Windmill4.setStatic(Windmill4.getPosition().get(0));
			Windmill1.setWidth(2);
			Windmill2.setWidth(2);
			Windmill3.setWidth(2);
			Windmill4.setWidth(2);
			Windmill1.setHeight(.1f);
			Windmill2.setHeight(.1f);
			Windmill3.setHeight(.1f);
			Windmill4.setHeight(.1f);
			break;
		case WOODWINDMILL:
			angularVelocity=8f;
			Windmill1 = new BasicObject(world,BodyType.DynamicBody,basicBodyType.BOX ,img,x,y);
			Windmill1.setProperties(1.5f, .2f, .4f, 0,0,6f,.4f,false,false,1,false); 
			Windmill1.setStatic(Windmill1.getPosition().get(0));
			Windmill2 = new BasicObject(world,BodyType.DynamicBody,basicBodyType.BOX ,img,x,y);
			Windmill2.setProperties(1.5f, .2f, .4f, 0,0,6f,.4f,false,false,1,false);
			Windmill2.setStatic(Windmill2.getPosition().get(0));
			Windmill3 = new BasicObject(world,BodyType.DynamicBody,basicBodyType.BOX ,img,x,y);
			Windmill3.setProperties(1.5f, .2f, .4f, 0,0,6f,.4f,false,false,1,false);
			Windmill3.setStatic(Windmill3.getPosition().get(0));
			Windmill4 = new BasicObject(world,BodyType.DynamicBody,basicBodyType.BOX ,img,x,y);
			Windmill4.setProperties(1.5f, .2f, .4f, 0,0,6f,.4f,false,false,1,false); 
			Windmill4.setStatic(Windmill4.getPosition().get(0));
			Windmill1.setWidth(4f);
			Windmill2.setWidth(4f);
			Windmill3.setWidth(4f);
			Windmill4.setWidth(4f);
			Windmill1.setHeight(.5f);
			Windmill2.setHeight(.5f);
			Windmill3.setHeight(.5f);
			Windmill4.setHeight(.5f);
			break;
		case SMALLIRONMILL:
			angularVelocity=-10f;
			Windmill1 = new BasicObject(world,BodyType.DynamicBody,basicBodyType.BOX,img,x,y);
			Windmill1.setProperties(.5f, .1f, .1f,0,0,.3f,2f,false,false,1,false);
			Windmill1.setStatic(Windmill1.getPosition().get(0));
			Windmill2 = new BasicObject(world,BodyType.DynamicBody,basicBodyType.BOX ,img,x,y);
			Windmill2.setProperties(.5f, .1f, .1f,0,0,.3f,2f,false,false,1,false);
			Windmill2.setStatic(Windmill2.getPosition().get(0));
			Windmill3 = new BasicObject(world,BodyType.DynamicBody,basicBodyType.BOX ,img,x,y);
			Windmill3.setProperties(.5f, .1f, .1f,0,0,.3f,2f,false,false,1,false);
			Windmill3.setStatic(Windmill3.getPosition().get(0));
			Windmill4 = new BasicObject(world,BodyType.DynamicBody,basicBodyType.BOX ,img,x,y);
			Windmill4.setProperties(.5f, .1f, .1f,0,0,.3f,2f,false,false,1,false);
			Windmill4.setStatic(Windmill4.getPosition().get(0));
			Windmill1.setWidth(1f);
			Windmill3.setWidth(1f);
			Windmill2.setWidth(1f);
			Windmill4.setWidth(1f);
			Windmill1.setHeight(.1f);
			Windmill2.setHeight(.1f);
			Windmill3.setHeight(.1f);
			Windmill4.setHeight(.1f);
			
			break;
		case SMALLWOODMILL:
			angularVelocity=8f;
			Windmill1 = new BasicObject(world,BodyType.DynamicBody,basicBodyType.BOX ,img,x,y);
			Windmill1.setProperties(1.5f, .2f, .4f, 0,0,6f,.4f,false,false,1,false); 
			Windmill1.setStatic(Windmill1.getPosition().get(0));
			Windmill2 = new BasicObject(world,BodyType.DynamicBody,basicBodyType.BOX ,img,x,y);
			Windmill2.setProperties(1.5f, .2f, .4f, 0,0,6f,.4f,false,false,1,false);
			Windmill2.setStatic(Windmill2.getPosition().get(0));
			Windmill3 = new BasicObject(world,BodyType.DynamicBody,basicBodyType.BOX ,img,x,y);
			Windmill3.setProperties(1.5f, .2f, .4f, 0,0,6f,.4f,false,false,1,false);
			Windmill3.setStatic(Windmill3.getPosition().get(0));
			Windmill4 = new BasicObject(world,BodyType.DynamicBody,basicBodyType.BOX,img,x,y);
			Windmill4.setProperties(1.5f, .2f, .4f, 0,0,6f,.4f,false,false,1,false); 
			Windmill4.setStatic(Windmill4.getPosition().get(0));
			Windmill1.setWidth(2f);
			Windmill2.setWidth(2f);
			Windmill3.setWidth(2f);
			Windmill4.setWidth(2f);
			Windmill1.setHeight(.3f);
			Windmill2.setHeight(.3f);
			Windmill3.setHeight(.3f);
			Windmill4.setHeight(.3f);
			break;
		case FUNKYWINDMILL:
			angularVelocity=6f;
			Windmill1 = new BasicObject(world,BodyType.DynamicBody,basicBodyType.BOX,"image/imponderable/SWING.png",x,y);
			Windmill1.setProperties(.5f, .1f, .1f,0,0,.3f,2f,false,false,1,false);
			Windmill1.setStatic(Windmill1.getPosition().get(0));
			Windmill2 = new BasicObject(world,BodyType.DynamicBody,basicBodyType.BOX ,"image/imponderable/SWING360.png",x,y);
			Windmill2.setProperties(.5f, .1f, .1f,0,0,.3f,2f,false,false,1,false);
			Windmill2.setStatic(Windmill2.getPosition().get(0));
			Windmill3 = new BasicObject(world,BodyType.DynamicBody,basicBodyType.BOX ,"image/imponderable/HORIZONTALSWING360.png",x,y);
			Windmill3.setProperties(.5f, .1f, .1f,0,0,.3f,2f,false,false,1,false);
			Windmill3.setStatic(Windmill3.getPosition().get(0));
			Windmill4 = new BasicObject(world,BodyType.DynamicBody,basicBodyType.BOX ,"image/imponderable/SWING.png",x,y);
			Windmill4.setProperties(.5f, .1f, .1f,0,0,.3f,2f,false,false,1,false);
			Windmill4.setStatic(Windmill4.getPosition().get(0));
			Windmill1.setWidth(3f);
			Windmill2.setWidth(3f);
			Windmill3.setWidth(3f);
			Windmill4.setWidth(3f);
			Windmill1.setHeight(.6f);
			Windmill2.setHeight(.6f);
			Windmill3.setHeight(.6f);
			Windmill4.setHeight(.6f);
			break;
		case MOVEMENTWHEEL:
			angularVelocity=-15f;
			Windmill1 = new BasicObject(world,BodyType.DynamicBody,basicBodyType.BOX,img,x,y);
			Windmill1.setProperties(.5f, .1f, .1f,0,0,.3f,2f,false,false,1,false);
			Windmill1.setStatic(Windmill1.getPosition().get(0));
			Windmill2 = new BasicObject(world,BodyType.DynamicBody,basicBodyType.BOX ,img,x,y);
			Windmill2.setProperties(.5f, .1f, .1f,0,0,.3f,2f,false,false,1,false);
			Windmill2.setStatic(Windmill2.getPosition().get(0));
			Windmill3 = new BasicObject(world,BodyType.DynamicBody,basicBodyType.BOX ,img,x,y);
			Windmill3.setProperties(.5f, .1f, .1f,0,0,.3f,2f,false,false,1,false);
			Windmill3.setStatic(Windmill3.getPosition().get(0));
			Windmill4 = new BasicObject(world,BodyType.DynamicBody,basicBodyType.BOX ,img,x,y);
			Windmill4.setProperties(.5f, .1f, .1f,0,0,.3f,2f,false,false,1,false);
			Windmill4.setStatic(Windmill4.getPosition().get(0));
			Windmill1.setWidth(2.5f);
			Windmill3.setWidth(2.5f);
			Windmill2.setWidth(2.5f);
			Windmill4.setWidth(2.5f);
			Windmill1.setHeight(1);
			Windmill2.setHeight(1);
			Windmill3.setHeight(1);
			Windmill4.setHeight(1);
			break;
			case SMALLMOVEMENTWHEEL:
				angularVelocity=20;
				Windmill1 = new BasicObject(world,BodyType.DynamicBody,basicBodyType.BOX,img,x,y);
				Windmill1.setProperties(.5f, .1f, .1f,0,0,.3f,2f,false,false,1,false);
				Windmill1.setStatic(Windmill1.getPosition().get(0));
				Windmill2 = new BasicObject(world,BodyType.DynamicBody,basicBodyType.BOX ,img,x,y);
				Windmill2.setProperties(.5f, .1f, .1f,0,0,.3f,2f,false,false,1,false);
				Windmill2.setStatic(Windmill2.getPosition().get(0));
				Windmill3 = new BasicObject(world,BodyType.DynamicBody,basicBodyType.BOX ,img,x,y);
				Windmill3.setProperties(.5f, .1f, .1f,0,0,.3f,2f,false,false,1,false);
				Windmill3.setStatic(Windmill3.getPosition().get(0));
				Windmill4 = new BasicObject(world,BodyType.DynamicBody,basicBodyType.BOX ,img,x,y);
				Windmill4.setProperties(.5f, .1f, .1f,0,0,.3f,2f,false,false,1,false);
				Windmill4.setStatic(Windmill4.getPosition().get(0));
				Windmill1.setWidth(1.5f);
				Windmill3.setWidth(1.5f);
				Windmill2.setWidth(1.5f);
				Windmill4.setWidth(1.5f);
				Windmill1.setHeight(.7f);
				Windmill2.setHeight(.7f);
				Windmill3.setHeight(.7f);
				Windmill4.setHeight(.7f);
				//saveProparity(1,.5f,.1f,1f);iron
				//saveProparity(1,10,.1f,.75f);wood
				break;
			
		case IRONWHEEL:
			//saveProparity(1,0,0f,0f);
			//boxShape.setAsBox(2f,.25f);
			angularVelocity=5f;
			Windmill1 = new BasicObject(world,BodyType.DynamicBody,basicBodyType.BOX ,img,x,y);
			Windmill1.setProperties(1.5f, .2f, .4f, 0,0,6f,.4f,false,false,1,false); 
			Windmill1.setStatic(Windmill1.getPosition().get(0));
			Windmill2 = new BasicObject(world,BodyType.DynamicBody,basicBodyType.BOX ,img,x,y);
			Windmill2.setProperties(1.5f, .2f, .4f, 0,0,6f,.4f,false,false,1,false); 
			Windmill2.setStatic(Windmill2.getPosition().get(0));
			Windmill3 = new BasicObject(world,BodyType.DynamicBody,basicBodyType.BOX ,img,x,y);
			Windmill3.setProperties(1.5f, .2f, .4f, 0,0,6f,.4f,false,false,1,false);
			Windmill3.setStatic(Windmill3.getPosition().get(0));
			Windmill1.setWidth(2);
			Windmill2.setWidth(2);
			Windmill3.setWidth(2);
			Windmill1.setHeight(.25f);
			Windmill2.setHeight(.25f);
			Windmill3.setHeight(.25f);
			Windmill1.setProperties(0, 0, 0, 0,0);
			Windmill2.setProperties(0, 0, 0, 0,0);
			Windmill3.setProperties(0, 0, 0, 0,0);
			break;
		case WOODWHEEL:
			//saveProparity(1,0,1f,1f);
			//boxShape.setAsBox(1.5f,.25f);
			angularVelocity=-10f;
			Windmill1 = new BasicObject(world,BodyType.DynamicBody,basicBodyType.BOX ,img,x,y);
			Windmill1.setProperties(1.5f, .2f, .4f, 0,0,6f,.4f,false,false,1,false); 
			Windmill1.setStatic(Windmill1.getPosition().get(0));
			Windmill2 = new BasicObject(world,BodyType.DynamicBody,basicBodyType.BOX ,img,x,y);
			Windmill2.setProperties(1.5f, .2f, .4f, 0,0,6f,.4f,false,false,1,false);
			Windmill2.setStatic(Windmill2.getPosition().get(0));
			Windmill3 = new BasicObject(world,BodyType.DynamicBody,basicBodyType.BOX ,img,x,y);
			Windmill3.setProperties(1.5f, .2f, .4f, 0,0,6f,.4f,false,false,1,false);
			Windmill3.setStatic(Windmill3.getPosition().get(0));
			Windmill1.setWidth(1.5f);
			Windmill2.setWidth(1.5f);
			Windmill3.setWidth(1.5f);
			Windmill1.setHeight(.25f);
			Windmill2.setHeight(.25f);
			Windmill3.setHeight(.25f);
			Windmill1.setProperties(0, 1, 1, 0,0);
			Windmill2.setProperties(0, 1, 1, 0,0);
			Windmill3.setProperties(0, 1, 1, 0,0);
			break;
		default:
			break;
		}
		
		switch (type) {
		case CIRCLEWINDMILL:
		case ROSEWINDMILL:
		case SMALLROSEWINDMILL:
			Windmill1 .setDensity(0);
			break;
		case MINIWINDMILL90:
			//Windmill2.getBody().get(0).setTransform(Windmill2.getBody().get(0).getPosition(), 30);
			Windmill1 .setDensity(0);
			Windmill2 .setDensity(0);
			AppVar.createRevoluteJoint(world,Windmill1.getBody().get(0), Windmill2.getBody().get(0), Windmill1.getBody().get(0).getWorldCenter());
			break;
		case MINIWINDMILL:
			Windmill2.getBody().get(0).setTransform(Windmill1.getBody().get(0).getPosition(), -40);
			Windmill1 .setDensity(0);
			Windmill2 .setDensity(0);
			AppVar.createRevoluteJoint(world,Windmill1.getBody().get(0), Windmill2.getBody().get(0), Windmill1.getBody().get(0).getWorldCenter());
			break;
			
		//have 4 windmill
		case IRONWINDMILL:
		case WOODWINDMILL:
		case SMALLIRONMILL:
		case SMALLWOODMILL:
		case FUNKYWINDMILL:
		case MOVEMENTWHEEL:
		case SMALLMOVEMENTWHEEL:
			Windmill2.getBody().get(0).setTransform(Windmill1.getBody().get(0).getPosition(), -40);
			Windmill3.getBody().get(0).setTransform(Windmill1.getBody().get(0).getPosition(), 40);
			Windmill4.getBody().get(0).setTransform(Windmill1.getBody().get(0).getPosition(), 80);
			Windmill1 .setDensity(0);
			Windmill2 .setDensity(0);
			Windmill3 .setDensity(0);
			Windmill4 .setDensity(0);
			AppVar.createRevoluteJoint(world,Windmill1.getBody().get(0), Windmill2.getBody().get(0), Windmill3.getBody().get(0).getWorldCenter());
			AppVar.createRevoluteJoint(world,Windmill1.getBody().get(0), Windmill3.getBody().get(0), Windmill2.getBody().get(0).getWorldCenter());
			AppVar.createRevoluteJoint(world,Windmill1.getBody().get(0), Windmill4.getBody().get(0), Windmill2.getBody().get(0).getWorldCenter());
			AppVar.createRevoluteJoint(world,Windmill2.getBody().get(0), Windmill3.getBody().get(0), Windmill4.getBody().get(0).getWorldCenter());
			AppVar.createRevoluteJoint(world,Windmill2.getBody().get(0), Windmill4.getBody().get(0), Windmill3.getBody().get(0).getWorldCenter());
			AppVar.createRevoluteJoint(world,Windmill3.getBody().get(0), Windmill4.getBody().get(0), Windmill1.getBody().get(0).getWorldCenter());
			break;
		case IRONWHEEL:
		case WOODWHEEL:
			Windmill2.getBody().get(0).setTransform(Windmill1.getBody().get(0).getPosition(), 45);
			Windmill3.getBody().get(0).setTransform(Windmill1.getBody().get(0).getPosition(), -45);
			Windmill1 .setDensity(0);
			Windmill2 .setDensity(0);
			Windmill3 .setDensity(0);
			AppVar.createRevoluteJoint(world,Windmill1.getBody().get(0), Windmill2.getBody().get(0), Windmill3.getBody().get(0).getWorldCenter());
			AppVar.createRevoluteJoint(world,Windmill1.getBody().get(0), Windmill3.getBody().get(0), Windmill2.getBody().get(0).getWorldCenter());
			AppVar.createRevoluteJoint(world,Windmill2.getBody().get(0), Windmill3.getBody().get(0), Windmill1.getBody().get(0).getWorldCenter());
			break;	
		
		}
		
	}
	@Override
	public void setDynamic(Vector2 position) {
		switch (type) {
		case CIRCLEWINDMILL:
		case ROSEWINDMILL:
		case SMALLROSEWINDMILL:
			Windmill1.changeBodyType(BodyType.KinematicBody);
			Windmill1.getBody().get(0).setAngularVelocity(angularVelocity);
			break;
		case MINIWINDMILL90:
		case MINIWINDMILL:
			Windmill1.changeBodyType(BodyType.KinematicBody);
			Windmill2.changeBodyType(BodyType.KinematicBody);
			Windmill1.getBody().get(0).setAngularVelocity(angularVelocity);
			Windmill2.getBody().get(0).setAngularVelocity(angularVelocity);
			break;
		case IRONWINDMILL:
		case WOODWINDMILL:
		case SMALLIRONMILL:
		case SMALLWOODMILL:
		case FUNKYWINDMILL:

		Windmill1.changeBodyType(BodyType.KinematicBody);
		Windmill2.changeBodyType(BodyType.KinematicBody);
		Windmill3.changeBodyType(BodyType.KinematicBody);
		Windmill4.changeBodyType(BodyType.KinematicBody);
		Windmill1.getBody().get(0).setAngularVelocity(angularVelocity);
		Windmill2.getBody().get(0).setAngularVelocity(angularVelocity);
		Windmill3.getBody().get(0).setAngularVelocity(angularVelocity);
		Windmill4.getBody().get(0).setAngularVelocity(angularVelocity);
		break;
		case IRONWHEEL:
		case WOODWHEEL:
			Windmill1.setDynamic(Windmill1.getPosition().get(0));
			Windmill2.setDynamic(Windmill2.getPosition().get(0));
			Windmill3.setDynamic(Windmill3.getPosition().get(0));
			Windmill1.getBody().get(0).setAngularVelocity(angularVelocity);
			Windmill2.getBody().get(0).setAngularVelocity(angularVelocity);
			Windmill3.getBody().get(0).setAngularVelocity(angularVelocity);
			break;
		case MOVEMENTWHEEL:
		case SMALLMOVEMENTWHEEL:
			Windmill1.setDynamic(Windmill1.getPosition().get(0));
			Windmill2.setDynamic(Windmill2.getPosition().get(0));
			Windmill3.setDynamic(Windmill3.getPosition().get(0));
			Windmill4.setDynamic(Windmill4.getPosition().get(0));
			Windmill1.getBody().get(0).setAngularVelocity(angularVelocity);
			Windmill2.getBody().get(0).setAngularVelocity(angularVelocity);
			Windmill3.getBody().get(0).setAngularVelocity(angularVelocity);
			Windmill4.getBody().get(0).setAngularVelocity(angularVelocity);
			break;
		}
		//raneen
		if(takeSound==true){music.play();music.setLooping(true);}
		for(int i=0;i<gameJoint.size();i++){
			gameJoint.get(i).setDynamic();
		}
	}
	@Override
	public void setStatic(Vector2 position) {
		switch (type) {
		case CIRCLEWINDMILL:
		case ROSEWINDMILL:
		case SMALLROSEWINDMILL:
			Windmill1.changeBodyType(BodyType.DynamicBody);
			Windmill1.getBody().get(0).setAngularVelocity(0);
			break;
		case MINIWINDMILL90:
		case MINIWINDMILL:
			Windmill1.changeBodyType(BodyType.DynamicBody);
			Windmill2.changeBodyType(BodyType.DynamicBody);
			Windmill1.getBody().get(0).setAngularVelocity(0);
			Windmill2.getBody().get(0).setAngularVelocity(0);
			break;
		case IRONWINDMILL:
		case WOODWINDMILL:
		case SMALLIRONMILL:
		case SMALLWOODMILL:
		case FUNKYWINDMILL:

		Windmill1.changeBodyType(BodyType.DynamicBody);
		Windmill2.changeBodyType(BodyType.DynamicBody);
		Windmill3.changeBodyType(BodyType.DynamicBody);
		Windmill4.changeBodyType(BodyType.DynamicBody);
		Windmill1.getBody().get(0).setAngularVelocity(0);
		Windmill2.getBody().get(0).setAngularVelocity(0);
		Windmill3.getBody().get(0).setAngularVelocity(0);
		Windmill4.getBody().get(0).setAngularVelocity(0);
		break;
		case IRONWHEEL:
		case WOODWHEEL:
			Windmill1.setStatic(position);
			Windmill2.setStatic(position);
			Windmill3.setStatic(position);
			Windmill2.getBody().get(0).setTransform(Windmill1.getBody().get(0).getPosition(), 45);
			Windmill3.getBody().get(0).setTransform(Windmill1.getBody().get(0).getPosition(), -45);
			Windmill1 .setDensity(0);
			Windmill2 .setDensity(0);
			Windmill3 .setDensity(0);
			Windmill1.getBody().get(0).setAngularVelocity(0);
			Windmill2.getBody().get(0).setAngularVelocity(0);
			Windmill3.getBody().get(0).setAngularVelocity(0);
			break;
		case MOVEMENTWHEEL:
		case SMALLMOVEMENTWHEEL:
			Windmill1.setStatic(position);
			Windmill2.setStatic(position);
			Windmill3.setStatic(position);
			Windmill4.setStatic(position);
			Windmill2.getBody().get(0).setTransform(Windmill1.getBody().get(0).getPosition(), -40);
			Windmill3.getBody().get(0).setTransform(Windmill1.getBody().get(0).getPosition(), 40);
			Windmill4.getBody().get(0).setTransform(Windmill1.getBody().get(0).getPosition(), 80);
			Windmill1 .setDensity(0);
			Windmill2 .setDensity(0);
			Windmill3 .setDensity(0);
			Windmill4 .setDensity(0);
			Windmill1.getBody().get(0).setAngularVelocity(0);
			Windmill2.getBody().get(0).setAngularVelocity(0);
			Windmill3.getBody().get(0).setAngularVelocity(0);
			Windmill4.getBody().get(0).setAngularVelocity(0);
			
			break;
		}
		//raneen
		if(takeSound==true){music.pause();}
		for(int i=0;i<gameJoint.size();i++){
			gameJoint.get(i).setStatic();
		}
	}
	@Override
	public ArrayList<Vector2> getPosition() {
		positionArray.clear();
		switch (type) {
		case CIRCLEWINDMILL:
		case ROSEWINDMILL:
		case SMALLROSEWINDMILL:
			positionArray.add(Windmill1.getPosition().get(0));
			break;
		case MINIWINDMILL90:
		case MINIWINDMILL:
			positionArray.add(Windmill1.getPosition().get(0));
			positionArray.add(Windmill2.getPosition().get(0));
			break;
		case IRONWINDMILL:
		case WOODWINDMILL:
		case SMALLIRONMILL:
		case SMALLWOODMILL:
		case FUNKYWINDMILL:
		case MOVEMENTWHEEL:
		case SMALLMOVEMENTWHEEL:
		positionArray.add(Windmill1.getPosition().get(0));
		positionArray.add(Windmill2.getPosition().get(0));
		positionArray.add(Windmill3.getPosition().get(0));
		positionArray.add(Windmill4.getPosition().get(0));
		break;
		case IRONWHEEL:
		case WOODWHEEL:
			positionArray.add(Windmill1.getPosition().get(0));
			positionArray.add(Windmill2.getPosition().get(0));
			positionArray.add(Windmill3.getPosition().get(0));
			break;
		}
		return positionArray;
	}
	@Override
	public void setPosition(Vector2 position) {
		switch (type) {
		case CIRCLEWINDMILL:
		case ROSEWINDMILL:
		case SMALLROSEWINDMILL:
			Windmill1.setPosition(position);
			break;
		case MINIWINDMILL90:
		case MINIWINDMILL:
			Windmill1.setPosition(position);
			Windmill2.setPosition(position);
			break;
		case IRONWINDMILL:
		case WOODWINDMILL:
		case SMALLIRONMILL:
		case SMALLWOODMILL:
		case FUNKYWINDMILL:
		case MOVEMENTWHEEL:
		case SMALLMOVEMENTWHEEL:
			Windmill1.setPosition(position);
			Windmill2.setPosition(position);
			Windmill3.setPosition(position);
			Windmill4.setPosition(position);
			break;
		case IRONWHEEL:
		case WOODWHEEL:
			Windmill1.setPosition(position);
			Windmill2.setPosition(position);
			Windmill3.setPosition(position);
			break;
		}
		
	}
	@Override
	public float getDensity() {
		//return virtualDensity;
		return virtualDencity;
	}
	@Override
	public void setDensity(float density) {
		virtualDencity = density;
	}
	@Override
	public float getFriction() {
		return Windmill1.getFriction();
	}
	@Override
	public void setFriction(float friction) {
		switch (type) {
		case CIRCLEWINDMILL:
		case ROSEWINDMILL:
		case SMALLROSEWINDMILL:
			Windmill1.setFriction(friction);
			break;
		case MINIWINDMILL90:
		case MINIWINDMILL:
			Windmill1.setFriction(friction);
			Windmill2.setFriction(friction);
			break;
		case IRONWINDMILL:
		case WOODWINDMILL:
		case SMALLIRONMILL:
		case SMALLWOODMILL:
		case FUNKYWINDMILL:
		case MOVEMENTWHEEL:
		case SMALLMOVEMENTWHEEL:

		Windmill1.setFriction(friction);
		Windmill2.setFriction(friction);
		Windmill3.setFriction(friction);
		Windmill4.setFriction(friction);
		break;
		case IRONWHEEL:
		case WOODWHEEL:
			Windmill1.setFriction(friction);
			Windmill2.setFriction(friction);
			Windmill3.setFriction(friction);
			
			break;

		}
	}
	@Override
	public float getRestitution() {
		return Windmill1.getRestitution();
	}
	@Override
	public void setRestitution(float restitution) {
		switch (type) {
		case CIRCLEWINDMILL:
		case ROSEWINDMILL:
		case SMALLROSEWINDMILL:
			Windmill1.setRestitution(restitution);
			break;
		case MINIWINDMILL90:
		case MINIWINDMILL:
			Windmill1.setRestitution(restitution);
			Windmill2.setRestitution(restitution);
			break;
		case IRONWINDMILL:
		case WOODWINDMILL:
		case SMALLIRONMILL:
		case SMALLWOODMILL:
		case FUNKYWINDMILL:
		case MOVEMENTWHEEL:
		case SMALLMOVEMENTWHEEL:

		Windmill1.setRestitution(restitution);
		Windmill2.setRestitution(restitution);
		Windmill3.setRestitution(restitution);
		Windmill4.setRestitution(restitution);
		break;
		case IRONWHEEL:
		case WOODWHEEL:
			Windmill1.setRestitution(restitution);
			Windmill2.setRestitution(restitution);
			Windmill3.setRestitution(restitution);
			break;
		}
	}
	@Override
	public ArrayList<Body> getBody(){
		    bodyArray.clear();
			bodyArray.add(Windmill1.getBody().get(0));
			//bodyArray.add(Windmill2.getBody().get(0));
			//bodyArray.add(Windmill3.getBody().get(0));
			//bodyArray.add(Windmill4.getBody().get(0));
			return bodyArray;
	}
	@Override
	public void setWidth(float width) {
		switch (type) {
		
	/*	case CIRCLEWINDMILL:
		case ROSEWINDMILL:
		case SMALLROSEWINDMILL:
			Windmill1.setRadius(width);
			break;*/
			
			
		case MINIWINDMILL90:
		case MINIWINDMILL:
			Windmill1.setWidth(width);
			Windmill2.setWidth(width);
			break;
		
		case IRONWINDMILL:
		case WOODWINDMILL:
		case SMALLIRONMILL:
		case SMALLWOODMILL:
		case FUNKYWINDMILL:
		case MOVEMENTWHEEL:
		case SMALLMOVEMENTWHEEL:

			Windmill1.setWidth(width);
			Windmill2.setWidth(width);
			Windmill3.setWidth(width);
			Windmill4.setWidth(width);
			break;
		case IRONWHEEL:
		case WOODWHEEL:
			Windmill1.setWidth(width);
			Windmill2.setWidth(width);
			Windmill3.setWidth(width);
			break;
		}
	}
	@Override
	public float getWidth() {
		return Windmill1.getWidth();
	}
	@Override
	public void setHeight(float height) {
		switch (type) {
	/*	case CIRCLEWINDMILL:
		case ROSEWINDMILL:
		case SMALLROSEWINDMILL:
			Windmill1.setRadius(height);
			break;*/
		case MINIWINDMILL90:
		case MINIWINDMILL:
			Windmill1.setHeight(height);
			Windmill2.setHeight(height);
			break;
		case IRONWINDMILL:
		case WOODWINDMILL:
		case SMALLIRONMILL:
		case SMALLWOODMILL:
		case FUNKYWINDMILL:
		case MOVEMENTWHEEL:
		case SMALLMOVEMENTWHEEL:

			Windmill1.setHeight(height);
			Windmill2.setHeight(height);
			Windmill3.setHeight(height);
			Windmill4.setHeight(height);
		case IRONWHEEL:
		case WOODWHEEL:
			Windmill1.setHeight(height);
			Windmill2.setHeight(height);
			Windmill3.setHeight(height);
			break;
		}
	}
	@Override
	public float getHeight() {
		return Windmill1.getHeight();
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
		case CIRCLEWINDMILL:
		case ROSEWINDMILL:
		case SMALLROSEWINDMILL:
			basicObjectArray.add(Windmill1);
			break;
		case MINIWINDMILL90:
		case MINIWINDMILL:
			basicObjectArray.add(Windmill1);
			basicObjectArray.add(Windmill2);
			break;
		//have 4 windmill
		case IRONWINDMILL:
		case WOODWINDMILL:
		case SMALLIRONMILL:
		case SMALLWOODMILL:
		case FUNKYWINDMILL:
		case MOVEMENTWHEEL:
		case SMALLMOVEMENTWHEEL:
			basicObjectArray.add(Windmill1);
			basicObjectArray.add(Windmill2);
			basicObjectArray.add(Windmill3);
			basicObjectArray.add(Windmill4);
			break;
		case IRONWHEEL:
		case WOODWHEEL:
			basicObjectArray.add(Windmill1);
			basicObjectArray.add(Windmill2);
			basicObjectArray.add(Windmill3);
			break;
		}
		return basicObjectArray;
	}
	public float getAngularVelocity(){
		return angularVelocity;
	}
	public void setAngularVelocity(float angularVelocity){
		this.angularVelocity=angularVelocity;
	}
	public void setProperties(float density,float friction,float restitution,float angularVelocity,float width,float hieght,boolean takeSound){
		switch (type) {
		case CIRCLEWINDMILL:
		case ROSEWINDMILL:
		case SMALLROSEWINDMILL:
			density=density;
			Windmill1.setFriction(friction);
			Windmill1.setRestitution(restitution);
			this.angularVelocity=angularVelocity;
			Windmill1.setRadius(width);
			break;
		case MINIWINDMILL90:
		case MINIWINDMILL:
			density=density;
			Windmill1.setFriction(friction);
			Windmill2.setFriction(friction);
			Windmill1.setRestitution(restitution);
			Windmill2.setRestitution(restitution);
			this.angularVelocity=angularVelocity;
			
			break;
		//have 4 windmill
		case IRONWINDMILL:
		case WOODWINDMILL:
		case SMALLIRONMILL:
		case SMALLWOODMILL:
		case FUNKYWINDMILL:
		case MOVEMENTWHEEL:
		case SMALLMOVEMENTWHEEL:
			density=density;
			Windmill1.setFriction(friction);
			Windmill2.setFriction(friction);
			Windmill3.setFriction(friction);
			Windmill4.setFriction(friction);
			Windmill1.setRestitution(restitution);
			Windmill2.setRestitution(restitution);
			Windmill3.setRestitution(restitution);
			Windmill4.setRestitution(restitution);
			this.angularVelocity=angularVelocity;
			break;
		case IRONWHEEL:
		case WOODWHEEL:
			density=density;
			Windmill1.setFriction(friction);
			Windmill2.setFriction(friction);
			Windmill3.setFriction(friction);
			Windmill1.setRestitution(restitution);
			Windmill2.setRestitution(restitution);
			Windmill3.setRestitution(restitution);
			this.angularVelocity=angularVelocity;
			break;
		}
		//raneen
		setTakeSound(takeSound);
		setWidth(width);
		setHeight(hieght);
	}
	public World getWorld() {
		return world;
	}
	public void setWorld(World world) {
		this.world = world;
	}
	public AppVar.windMillType getType() {
		return type;
	}
	public void setType(AppVar.windMillType type) {
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


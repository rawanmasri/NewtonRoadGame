package sw.newtonroad.body;

import java.util.ArrayList;

import sw.newtonroad.handlers.AppVar;
import sw.newtonroad.handlers.AppVar.basicBodyType;
import sw.newtonroad.joint.Distance;
import sw.newtonroad.joint.GameJoint;
import sw.newtonroad.joint.Revolute;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;

public class FixedObject extends GameObject {
	private World world;
	private AppVar.fixedType type;
	private String img;
	private BasicObject shape1;
	private BasicObject shape2;
	private BasicObject shape3;
	private BasicObject shape4;
	private float virtualDencity;
	private boolean isStatic = false;
	private boolean isRotate=false;
	private int isCollision=1;
	public  FixedObject(World world,AppVar.fixedType type,String img,float x,float y){
		this.world=world;
		this.type=type;
		this.img=img;
		positionArray=new ArrayList<Vector2>();
		bodyArray=new ArrayList<Body>();
		basicObjectArray=new ArrayList<BasicObject>();
		gameJoint=new ArrayList<GameJoint>();
		jointInfo=new String[4];
		secondJoint=false;
		switch (type) {
		case WHEEL:
			shape1 = new BasicObject(world, BodyType.DynamicBody,basicBodyType.BALLON, img,x, y);
			shape1.setDensity(0);
			break;
		case UPARROW:
			shape1 = new BasicObject(world, BodyType.DynamicBody,basicBodyType.UPARROW, img,x, y);
			break;
		case RIGHTARROW:
			shape1 = new BasicObject(world, BodyType.DynamicBody,basicBodyType.RIGHTARROW, img,x, y);
			break;
		case BALLON:
			shape1 = new BasicObject(world, BodyType.DynamicBody,basicBodyType.BALLON, img,x, y);
			break;
		case CATFACE:
			shape1 = new BasicObject(world, BodyType.DynamicBody,basicBodyType.BALL, img,x, y);
			shape1.setRadius(.5f);
			shape1.setProperties(1.2f, .4f, .7f, 0,0);
			shape1.setStatic(shape1.getPosition().get(0));
			break;
		case CHARE:
			shape1 = new BasicObject(world, BodyType.DynamicBody,basicBodyType.CHARE, img,x, y);
			break;
		case DARKWOOD:
			shape1 = new BasicObject(world, BodyType.DynamicBody,basicBodyType.BOX, img,x, y);
			shape1.setRadius(1);
			shape1.setProperties(1.5f, .58f, .34f,0,0);
			shape1.setStatic(shape1.getPosition().get(0));
			break;
		case STONWALL:
			shape1 = new BasicObject(world, BodyType.DynamicBody,basicBodyType.BOX, img,x, y);
			shape1.setWidth(6f);
			shape1.setHeight(1.4f);
			shape1.setProperties(5f, .75f, 1f,0,0);
			shape1.setStatic(shape1.getPosition().get(0));
			break;
		case METAL:
			shape1 = new BasicObject(world, BodyType.DynamicBody,basicBodyType.BOX, img,x, y);
			shape1.setWidth(6f);
			shape1.setHeight(1.4f);
			shape1.setProperties(5f, .75f, 1f,0,0);
			shape1.setStatic(shape1.getPosition().get(0));
			break;
		case STANDWOOD:
			shape1 = new BasicObject(world, BodyType.DynamicBody,basicBodyType.BOX, img,x, y);
			shape1.setWidth(3);
			shape1.setHeight(.5f);
			shape1.setProperties(1f, .4f, .7f, 0,0);
			shape1.setStatic(shape1.getPosition().get(0));
			break;
		case DICE:
			shape1 = new BasicObject(world, BodyType.DynamicBody,basicBodyType.BOX, img,x, y);
			shape1.setWidth(1f);
			shape1.setHeight(1f);
			shape1.setProperties(.6f, .77f,1f,0,0);
			shape1.setStatic(shape1.getPosition().get(0));
			break;
		case WOODWALL:
			shape1 = new BasicObject(world, BodyType.DynamicBody,basicBodyType.BOX, img,x, y);
			shape1.setWidth(7f);
			shape1.setHeight(2.2f);
			shape1.setProperties(3.6f, .67f,1f,0,0);
			shape1.setStatic(shape1.getPosition().get(0));
			break;
		case STONSTAND:
			shape1 = new BasicObject(world, BodyType.DynamicBody,basicBodyType.STONSTAND, img,x, y);
			shape1.setProperties(5.2f, .67f,1f,0,0);
			shape1.setStatic(shape1.getPosition().get(0));
			break;
		case WOODSHAPE:
			shape1 = new BasicObject(world, BodyType.DynamicBody,basicBodyType.WOODSHAPE, img,x, y);
			break;
		case BIASWOOD:
			shape1 = new BasicObject(world, BodyType.DynamicBody,basicBodyType.BOX, img,x, y);
			shape1.setProperties(5.2f, .67f,1f,0,0);
			shape1.setWidth(2);
			shape1.setHeight(.4f);
			shape1.getBody().get(0).setTransform(shape1.getBody().get(0).getPosition(), -60);
			shape1.setDensity(0);
			break;
		case LEFTSTARE:
			//first stare
			shape1 = new BasicObject(world, BodyType.DynamicBody,basicBodyType.BOX, img,x, y);
			shape1.setWidth(4f);
			shape1.setHeight(.5f);
			shape1.setProperties(.5f, .75f, .59f,0,0);
			shape1.setStatic(shape1.getPosition().get(0));
			//secound stare
			shape2 = new BasicObject(world, BodyType.DynamicBody,basicBodyType.BOX, img,x+1, y+1);
			shape2.setWidth(3f);
			shape2.setHeight(.5f);
			shape2.setProperties(.5f, .75f, .59f,0,0);
			shape2.setStatic(shape2.getPosition().get(0));
			//third stare
			shape3 = new BasicObject(world, BodyType.DynamicBody,basicBodyType.BOX, img,x+2, y+2);
			shape3.setWidth(2f);
			shape3.setHeight(.5f);
			shape3.setProperties(.5f, .75f, .59f,0,0);
			shape3.setStatic(shape3.getPosition().get(0));
			//fourth stare
			shape4 = new BasicObject(world, BodyType.DynamicBody,basicBodyType.BOX, img,x+3, y+3);
			shape4.setWidth(1f);
			shape4.setHeight(.5f);
			shape4.setProperties(.5f, .75f, .59f,0,0);
			shape4.setStatic(shape4.getPosition().get(0));
			shape3.setDensity(0);
			shape1.setDensity(0);
			shape2.setDensity(0);
			shape3.setDensity(0);
			shape4.setDensity(0);
			AppVar.createRevoluteJoint(world, shape1.getBody().get(0), shape2.getBody().get(0), shape1.getBody().get(0).getLocalCenter());
			AppVar.createRevoluteJoint(world, shape2.getBody().get(0), shape3.getBody().get(0), shape2.getBody().get(0).getLocalCenter());
			AppVar.createRevoluteJoint(world, shape3.getBody().get(0), shape4.getBody().get(0), shape3.getBody().get(0).getLocalCenter());
			virtualDencity  = 4;
			break;
		case RIGHTSTARE:
			//first stare
			shape1 = new BasicObject(world, BodyType.DynamicBody,basicBodyType.BOX, img,x, y);
			shape1.setWidth(4f);
			shape1.setHeight(.5f);
			shape1.setProperties(.5f, .75f, .59f,0,0);
			shape1.setStatic(shape1.getPosition().get(0));
			//secound stare
			shape2 = new BasicObject(world, BodyType.DynamicBody,basicBodyType.BOX, img,x-1, y+1);
			shape2.setWidth(3f);
			shape2.setHeight(.5f);
			shape2.setProperties(.5f, .75f, .59f,0,0);
			shape2.setStatic(shape2.getPosition().get(0));
			//third stare
			shape3 = new BasicObject(world, BodyType.DynamicBody,basicBodyType.BOX, img,x-2, y+2);
			shape3.setWidth(2f);
			shape3.setHeight(.5f);
			shape3.setProperties(.5f, .75f, .59f,0,0);
			shape3.setStatic(shape3.getPosition().get(0));
			//fourth stare
			shape4 = new BasicObject(world, BodyType.DynamicBody,basicBodyType.BOX, img,x-3, y+3);
			shape4.setWidth(1f);
			shape4.setHeight(.5f);
			shape4.setProperties(.5f, .75f, .59f,0,0);
			shape4.setStatic(shape4.getPosition().get(0));
			shape3.setDensity(0);
			shape1.setDensity(0);
			shape2.setDensity(0);
			shape3.setDensity(0);
			shape4.setDensity(0);
			AppVar.createRevoluteJoint(world, shape1.getBody().get(0), shape2.getBody().get(0), shape1.getBody().get(0).getLocalCenter());
			AppVar.createRevoluteJoint(world, shape2.getBody().get(0), shape3.getBody().get(0), shape2.getBody().get(0).getLocalCenter());
			AppVar.createRevoluteJoint(world, shape3.getBody().get(0), shape4.getBody().get(0), shape3.getBody().get(0).getLocalCenter());
			virtualDencity  = 4;
			break;
		case MIDLESTARE:
			//first stare
			shape1 = new BasicObject(world, BodyType.DynamicBody,basicBodyType.BOX, img,x, y);
			shape1.setWidth(4f);
			shape1.setHeight(.5f);
			shape1.setProperties(.5f, .75f, .59f,0,0);
			shape1.setStatic(shape1.getPosition().get(0));
			//secound stare
			shape2 = new BasicObject(world, BodyType.DynamicBody,basicBodyType.BOX, img,x, y+1);
			shape2.setWidth(3f);
			shape2.setHeight(.5f);
			shape2.setProperties(.5f, .75f, .59f,0,0);
			shape2.setStatic(shape2.getPosition().get(0));
			//third stare
			shape3 = new BasicObject(world, BodyType.DynamicBody,basicBodyType.BOX, img,x, y+2);
			shape3.setWidth(2f);
			shape3.setHeight(.5f);
			shape3.setProperties(.5f, .75f, .59f,0,0);
			shape3.setStatic(shape3.getPosition().get(0));
			//fourth stare
			shape4 = new BasicObject(world, BodyType.DynamicBody,basicBodyType.BOX, img,x, y+3);
			shape4.setWidth(1f);
			shape4.setHeight(.5f);
			shape4.setProperties(.5f, .75f, .59f,0,0);
			shape4.setStatic(shape4.getPosition().get(0));
			shape3.setDensity(0);
			shape1.setDensity(0);
			shape2.setDensity(0);
			shape3.setDensity(0);
			shape4.setDensity(0);
			AppVar.createRevoluteJoint(world, shape1.getBody().get(0), shape2.getBody().get(0), shape1.getBody().get(0).getLocalCenter());
			AppVar.createRevoluteJoint(world, shape2.getBody().get(0), shape3.getBody().get(0), shape2.getBody().get(0).getLocalCenter());
			AppVar.createRevoluteJoint(world, shape3.getBody().get(0), shape4.getBody().get(0), shape3.getBody().get(0).getLocalCenter());
			virtualDencity  = 4;
			break;
		case BUCKET:
			//first 
			shape1 = new BasicObject(world, BodyType.DynamicBody,basicBodyType.BOX, img,x, y);
			shape1.setWidth(4.3f);
			shape1.setHeight(.45f);
			shape1.setProperties(.5f, .75f, .59f,0,0);
			shape1.setStatic(shape1.getPosition().get(0));
			//secound 
			shape2 = new BasicObject(world, BodyType.DynamicBody,basicBodyType.BOX, img,x-4.7f, y+1.15f);
			shape2.setWidth(.45f);
			shape2.setHeight(1.6f);
			shape2.setProperties(.5f, .75f, .59f,0,0);
			shape2.setStatic(shape2.getPosition().get(0));
			//third 
			shape3 = new BasicObject(world, BodyType.DynamicBody,basicBodyType.BOX, img,x+4.7f, y+1.15f);
			shape3.setWidth(.45f);
			shape3.setHeight(1.6f);
			shape3.setProperties(.5f, .75f, .59f,0,0);
			shape3.setStatic(shape3.getPosition().get(0));
			shape1.setDensity(0);
			shape2.setDensity(0);
			shape3.setDensity(0);
			AppVar.createRevoluteJoint(world, shape1.getBody().get(0), shape2.getBody().get(0), shape1.getBody().get(0).getLocalCenter());
			AppVar.createRevoluteJoint(world, shape2.getBody().get(0), shape3.getBody().get(0), shape2.getBody().get(0).getLocalCenter());
			virtualDencity  = 4;
			break;
		default:
			break;
		}
	}
	@Override
	public void setDynamic(Vector2 position) {
		// TODO Auto-generated method stub
		switch (type) {
		case LEFTSTARE:
		case RIGHTSTARE:
		case MIDLESTARE:
			shape1.getBody().get(0).setType(BodyType.StaticBody);
			shape2.getBody().get(0).setType(BodyType.StaticBody);
			shape3.getBody().get(0).setType(BodyType.StaticBody);
			shape4.getBody().get(0).setType(BodyType.StaticBody);
			shape1.setDynamic(shape1.getPosition().get(0));
			shape2.setDynamic(shape2.getPosition().get(0));
			shape3.setDynamic(shape3.getPosition().get(0));
			shape4.setDynamic(shape4.getPosition().get(0));
			break;
		case BUCKET:
			shape1.getBody().get(0).setType(BodyType.StaticBody);
			shape2.getBody().get(0).setType(BodyType.StaticBody);
			shape3.getBody().get(0).setType(BodyType.StaticBody);
			shape1.setDynamic(shape1.getPosition().get(0));
			shape2.setDynamic(shape2.getPosition().get(0));
			shape3.setDynamic(shape3.getPosition().get(0));
			break;
		case CHARE:
		case BALLON:
		case CATFACE:
		case DARKWOOD:
		case DICE:
		case RIGHTARROW:
		case STANDWOOD:
		case STONSTAND:
		case STONWALL:
		case METAL:
		case UPARROW:
		case WOODSHAPE:
		case WOODWALL:
		case BIASWOOD:
			if(isStatic==true){
				shape1.getBody().get(0).setType(BodyType.StaticBody);
			}
			else{
				shape1.getBody().get(0).setType(BodyType.DynamicBody);
				}
			shape1.setDynamic(position);
			break;
		case WHEEL:
			shape1.changeBodyType(BodyType.KinematicBody);
			shape1.getBody().get(0).setAngularVelocity(-5);
			break;
			
		}
		for(int i=0;i<gameJoint.size();i++){
			System.out.println("fixed joint dynamic");
			gameJoint.get(i).setDynamic();
		}
	}

	@Override
	public void setStatic(Vector2 position) {
		// TODO Auto-generated method stub
		switch (type) {
		case LEFTSTARE:
		case RIGHTSTARE:
		case MIDLESTARE:
			shape1.getBody().get(0).setType(BodyType.DynamicBody);
			shape2.getBody().get(0).setType(BodyType.DynamicBody);
			shape3.getBody().get(0).setType(BodyType.DynamicBody);
			shape4.getBody().get(0).setType(BodyType.DynamicBody);
			shape1.setStatic(shape1.getPosition().get(0));
			shape2.setStatic(shape2.getPosition().get(0));
			shape3.setStatic(shape3.getPosition().get(0));
			shape4.setStatic(shape4.getPosition().get(0));
			shape1.setDensity(0);
			shape2.setDensity(0);
			shape3.setDensity(0);
			shape4.setDensity(0);
			break;
		case BUCKET:
			shape1.getBody().get(0).setType(BodyType.DynamicBody);
			shape2.getBody().get(0).setType(BodyType.DynamicBody);
			shape3.getBody().get(0).setType(BodyType.DynamicBody);
			shape1.setStatic(shape1.getPosition().get(0));
			shape2.setStatic(shape2.getPosition().get(0));
			shape3.setStatic(shape3.getPosition().get(0));
			shape1.setDensity(0);
			shape2.setDensity(0);
			shape3.setDensity(0);
			
			break;
		case CHARE:
		case BALLON:
		case CATFACE:
		case DARKWOOD:
		case DICE:
		case RIGHTARROW:
		case STANDWOOD:
		case STONSTAND:
		case STONWALL:
		case METAL:
		case UPARROW:
		case WOODSHAPE:
		case WOODWALL:
			shape1.getBody().get(0).setType(BodyType.DynamicBody);
			if(isRotate()==true){	
				shape1.setRotate(isRotate());
			}
			else {
				shape1.setRotate(isRotate());
			}
			shape1.setStatic(position);
			break;
		case BIASWOOD:
			shape1.getBody().get(0).setType(BodyType.DynamicBody);
			if(isRotate()==true){	
				shape1.setRotate(isRotate());
			}
			else {
				shape1.setRotate(isRotate());
			}
			shape1.getBody().get(0).setGravityScale(0);
			shape1.setDensity(0);
			break;
		case WHEEL:
			shape1.changeBodyType(BodyType.DynamicBody);
			shape1.getBody().get(0).setAngularVelocity(0);
			break;
		}
		
		for(int i=0;i<gameJoint.size();i++){
			System.out.println("fixed joint static");
			gameJoint.get(i).setStatic();
		}
	}

	@Override
	public ArrayList<Vector2> getPosition() {
		// TODO Auto-generated method stub
		positionArray.clear();
		switch (type) {
		case LEFTSTARE:
		case RIGHTSTARE:
		case MIDLESTARE:
			positionArray.add(shape1.getBody().get(0).getPosition());
			positionArray.add(shape2.getBody().get(0).getPosition());
			positionArray.add(shape3.getBody().get(0).getPosition());
			positionArray.add(shape4.getBody().get(0).getPosition());
			break;
		case BUCKET:
			positionArray.add(shape1.getBody().get(0).getPosition());
			positionArray.add(shape2.getBody().get(0).getPosition());
			positionArray.add(shape3.getBody().get(0).getPosition());
			break;
		case CHARE:
		case BALLON:
		case CATFACE:
		case DARKWOOD:
		case DICE:
		case RIGHTARROW:
		case STANDWOOD:
		case STONSTAND:
		case STONWALL:
		case METAL:
		case UPARROW:
		case WOODSHAPE:
		case WOODWALL:
		case BIASWOOD:
		case WHEEL:
			positionArray.add(shape1.getBody().get(0).getPosition());
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
		// TODO Auto-generated method stub
		switch (type) {
		case LEFTSTARE:
		case RIGHTSTARE:
		case MIDLESTARE:
		case BUCKET:	
			return virtualDencity;
		case CHARE:
		case BALLON:
		case CATFACE:
		case DARKWOOD:
		case DICE:
		case RIGHTARROW:
		case STANDWOOD:
		case STONSTAND:
		case STONWALL:
		case METAL:
		case UPARROW:
		case WOODSHAPE:
		case WOODWALL:
		case BIASWOOD:
		case WHEEL:	
			return shape1.getDensity();
			
		}
		return 0;
	}
		
	@Override
	public void setDensity(float density) {
		// TODO Auto-generated method stub
		switch (type) {
		case LEFTSTARE:
		case RIGHTSTARE:
		case MIDLESTARE:
		case BUCKET:	
			virtualDencity = density;
			break;
		case CHARE:
		case BALLON:
		case CATFACE:
		case DARKWOOD:
		case DICE:
		case RIGHTARROW:
		case STANDWOOD:
		case STONSTAND:
		case STONWALL:
		case METAL:
		case UPARROW:
		case WOODSHAPE:
		case WOODWALL:
		case BIASWOOD:
		case WHEEL:	
			shape1.setDensity(density);
			break;
		}
	}

	@Override
	public float getFriction() {
		// TODO Auto-generated method stub
		return shape1.getFriction();
	}

	@Override
	public void setFriction(float friction) {
		// TODO Auto-generated method stub
		shape1.setFriction(friction);
	}

	@Override
	public float getRestitution() {
		// TODO Auto-generated method stub
		return shape1.getRestitution();
	}

	@Override
	public void setRestitution(float restitution) {
		// TODO Auto-generated method stub
		shape1.setRestitution(restitution);
	}

	@Override
	public ArrayList<Body> getBody() {
		// TODO Auto-generated method stub
		bodyArray.clear();
		bodyArray.add(shape1.getBody().get(0));
		return bodyArray;
	}

	@Override
	public void setWidth(float width) {
		// TODO Auto-generated method stub
		switch (type) {
		case LEFTSTARE:
		case RIGHTSTARE:
		case MIDLESTARE:
			shape1.setWidth(width);
			shape2.setWidth(width-1);
			shape3.setWidth(width-2);
			shape4.setWidth(width-3);
			break;
		case BUCKET:
			shape1.setWidth(width);
			shape2.setWidth(.5f);
			shape3.setWidth(.5f);
			break;
		case CHARE:
		case BALLON:
		case CATFACE:
		case DARKWOOD:
		case DICE:
		case RIGHTARROW:
		case STANDWOOD:
		case STONSTAND:
		case STONWALL:
		case METAL:
		case UPARROW:
		case WOODSHAPE:
		case WOODWALL:
		case BIASWOOD:
		case WHEEL:	
			shape1.setWidth(width);
			break;
		}
	}

	@Override
	public float getWidth() {
		// TODO Auto-generated method stub
		return shape1.getWidth();
	}

	@Override
	public void setHeight(float height) {
		// TODO Auto-generated method stub
		switch (type) {
		case LEFTSTARE:
		case RIGHTSTARE:
		case MIDLESTARE:
			shape1.setWidth(height);
			shape2.setWidth(height+1);
			shape3.setWidth(height+2);
			shape4.setWidth(height+3);
			break;
		case BUCKET:
			shape1.setWidth(.5f);
			shape2.setWidth(height);
			shape3.setWidth(height);
			break;
		case CHARE:
		case BALLON:
		case CATFACE:
		case DARKWOOD:
		case DICE:
		case RIGHTARROW:
		case STANDWOOD:
		case STONSTAND:
		case STONWALL:
		case METAL:
		case UPARROW:
		case WOODSHAPE:
		case WOODWALL:
		case BIASWOOD:
		case WHEEL:	
			shape1.setHeight(height);
			break;
		}
	}

	@Override
	public float getHeight() {
		// TODO Auto-generated method stub
		switch (type) {
		case LEFTSTARE:
		case RIGHTSTARE:
		case MIDLESTARE:
			return shape1.getHeight();
		case BUCKET:
			return shape2.getHeight();
		case CHARE:
		case BALLON:
		case CATFACE:
		case DARKWOOD:
		case DICE:
		case RIGHTARROW:
		case STANDWOOD:
		case STONSTAND:
		case STONWALL:
		case METAL:
		case UPARROW:
		case WOODSHAPE:
		case WOODWALL:
		case BIASWOOD:
		case WHEEL:	
			return shape1.getHeight();
		}
		return 0;
	}

	@Override
	public void setRadius(float radius) {
		// TODO Auto-generated method stub
		shape1.setRadius(radius);
	}

	@Override
	public float getRadius() {
		// TODO Auto-generated method stub
		return shape1.getRadius();
	}
	@Override
	public ArrayList<BasicObject> getBasicObject() {
		// TODO Auto-generated method stub
		basicObjectArray.clear();
		switch (type) {
		case LEFTSTARE:
		case RIGHTSTARE:
		case MIDLESTARE:
			basicObjectArray.add(shape1);
			basicObjectArray.add(shape2);
			basicObjectArray.add(shape3);
			basicObjectArray.add(shape4);
			break;
		case BUCKET:
			basicObjectArray.add(shape1);
			basicObjectArray.add(shape2);
			basicObjectArray.add(shape3);
			break;
		case CHARE:
		case BALLON:
		case CATFACE:
		case DARKWOOD:
		case DICE:
		case RIGHTARROW:
		case STANDWOOD:
		case STONSTAND:
		case STONWALL:
		case METAL:
		case UPARROW:
		case WOODSHAPE:
		case WOODWALL:
		case BIASWOOD:
		case WHEEL:	
			basicObjectArray.add(shape1);
			break;
		}
		return basicObjectArray;
	}
	public void setProperties(float density,float friction,float restitution,float width,float height,boolean isStatic,boolean isRotate,int isColied){
		switch (type) {
		case LEFTSTARE:
		case RIGHTSTARE:
		case MIDLESTARE:
			virtualDencity = density;
			shape1.setFriction(friction);
			shape1.setRestitution(restitution);
			shape1.setWidth(width);
			shape1.setHeight(height);
			setStatic(isStatic);
			setRotate(isRotate);
			setIsCollision(isColied);
			break;
		case BUCKET:
			virtualDencity = density;
			shape1.setFriction(friction);
			shape1.setRestitution(restitution);
			shape1.setWidth(width);
			shape2.setHeight(height);
			shape3.setHeight(height);
			setStatic(isStatic);
			setRotate(isRotate);
			setIsCollision(isColied);
			break;
		case CHARE:
		case BALLON:
		case CATFACE:
		case DARKWOOD:
		case DICE:
		case RIGHTARROW:
		case STANDWOOD:
		case STONSTAND:
		case STONWALL:
		case METAL:
		case UPARROW:
		case WOODSHAPE:
		case WOODWALL:
		case BIASWOOD:
		case WHEEL:	
			shape1.setDensity(density);
			shape1.setFriction(friction);
			shape1.setRestitution(restitution);
			shape1.setWidth(width);
			shape1.setHeight(height);
			setStatic(isStatic);
			setRotate(isRotate);
			setIsCollision(isColied);
			shape1.setIsCollision((short)isColied);
			break;
		}
	}
	public void setProperties(float density,float friction,float restitution,float radius,boolean isStatic,boolean isRotate,int isColied){
		shape1.setDensity(density);
		shape1.setFriction(friction);
		shape1.setRestitution(restitution);
		shape1.setRadius(radius);
		setStatic(isStatic);
		setRotate(isRotate);
		setIsCollision(isColied);
		shape1.setIsCollision((short)isColied);
		shape1.setStatic(shape1.getPosition().get(0));
	}
	public boolean isStatic() {
		return isStatic;
	}
	public void setStatic(boolean isStatic) {
		this.isStatic = isStatic;
	}
	public boolean isRotate() {
		return isRotate;
	}
	public void setRotate(boolean isRotate) {
		this.isRotate = isRotate;
	}
	public int getIsCollision() {
		return isCollision;
	}
	public void setIsCollision(int isCollision) {
		this.isCollision = isCollision;
	}
	public World getWorld() {
		return world;
	}
	public void setWorld(World world) {
		this.world = world;
	}
	public AppVar.fixedType getType() {
		return type;
	}
	public void setType(AppVar.fixedType type) {
		this.type = type;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public BasicObject getShape() {
		return shape1;
	}
	public void setShape(BasicObject shape) {
		this.shape1 = shape;
	}
}

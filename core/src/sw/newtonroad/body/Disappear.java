package sw.newtonroad.body;

import java.util.ArrayList;

import sw.newtonroad.handlers.AppVar;
import sw.newtonroad.handlers.AppVar.basicBodyType;
import sw.newtonroad.joint.GameJoint;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class Disappear extends GameObject {
	private World world;
	private AppVar.disappear type;
	private String img;
	private BasicObject shape1;
	private BasicObject shape2;
	private float virtualDencity;
	public  Disappear(World world,AppVar.disappear type,String img,float x,float y){
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
		case DISAPPEAR:
			shape1 = new BasicObject(world, BodyType.DynamicBody,basicBodyType.BOX, img,x, y);
			shape1.setWidth(1.4f);
			shape1.setHeight(1.3f);
			shape1.setProperties(1.5f, .58f, .34f,0,0);
			shape1.setStatic(shape1.getPosition().get(0));
			shape2 = new BasicObject(world, BodyType.DynamicBody,basicBodyType.BOX, "image/box/BOX2.png",x+5, y);
			shape2.setWidth(1.4f);
			shape2.setHeight(1.3f);
			shape2.setProperties(1.5f, .58f, .34f,0,0);
			shape2.setStatic(shape2.getPosition().get(0));
			shape1.getBody().get(0).setType(BodyType.DynamicBody);
			shape2.getBody().get(0).setType(BodyType.DynamicBody);
			shape1.getBody().get(0).setGravityScale(0);
			shape1.setDensity(0);
			shape2.getBody().get(0).setGravityScale(0);
			shape2.setDensity(0);
			virtualDencity=2.5f;
			break;
		}
	}
	@Override
	public void setDynamic(Vector2 position) {
		// TODO Auto-generated method stub
		switch (type) {
			case DISAPPEAR:
				shape1.getBody().get(0).setType(BodyType.StaticBody);
				shape1.setDynamic(shape1.getPosition().get(0));
				shape2.getBody().get(0).setType(BodyType.StaticBody);
				shape2.setDynamic(shape1.getPosition().get(0));
			break;
		}
//		for(int i=0;i<gameJoint.size();i++){
//			System.out.println("fixed joint dynamic");
//			gameJoint.get(i).setDynamic();
//		}
	}

	@Override
	public void setStatic(Vector2 position) {
		// TODO Auto-generated method stub
		switch (type) {
		case DISAPPEAR:
			shape1.getBody().get(0).setType(BodyType.DynamicBody);
			shape2.getBody().get(0).setType(BodyType.DynamicBody);
			shape1.setDensity(0);
			shape2.setDensity(0);
			shape1.getBody().get(0).setGravityScale(0);
			shape1.setDensity(0);
			shape2.getBody().get(0).setGravityScale(0);
			shape2.setDensity(0);
			break;

		}
//		for(int i=0;i<gameJoint.size();i++){
//			System.out.println("fixed joint static");
//			gameJoint.get(i).setStatic();
//		}
	}

	@Override
	public ArrayList<Vector2> getPosition() {
		// TODO Auto-generated method stub
		positionArray.clear();
		switch (type) {
		case DISAPPEAR:
			positionArray.add(shape1.getBody().get(0).getPosition());
			positionArray.add(shape2.getBody().get(0).getPosition());
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
			case DISAPPEAR:
				return virtualDencity;
		}
		return 0;
	}

	@Override
	public void setDensity(float density) {
		// TODO Auto-generated method stub
		switch (type) {
			case DISAPPEAR:
				virtualDencity = density;
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
	    bodyArray.add(shape2.getBody().get(0));
		return bodyArray;
	}

	@Override
	public void setWidth(float width) {
		// TODO Auto-generated method stub
		switch (type) {
			case DISAPPEAR:
				shape1.setWidth(width);
				shape2.setWidth(width);
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
		case DISAPPEAR:
			shape1.setHeight(height);
			shape2.setHeight(height);
			break;
		}
	}

	@Override
	public float getHeight() {
		// TODO Auto-generated method stub
		return shape1.getHeight();
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
		switch (type) {
		case DISAPPEAR:
			basicObjectArray.add(shape1);
			basicObjectArray.add(shape2);
			break;
		}
		return basicObjectArray;
	}
	public void setProperties(float density,float friction,float restitution,float width,float height){
		switch (type) {
		case DISAPPEAR:
			virtualDencity = density;
			shape1.setFriction(friction);
			shape1.setRestitution(restitution);
			shape1.setWidth(width);
			shape1.setHeight(height);
			shape2.setFriction(friction);
			shape2.setRestitution(restitution);
			shape2.setWidth(width);
			shape2.setHeight(height);
			break;
		}

	}
	public World getWorld() {
		return world;
	}
	public void setWorld(World world) {
		this.world = world;
	}
	public AppVar.disappear getType() {
		return type;
	}
	public void setType(AppVar.disappear type) {
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
	public BasicObject getShape1() {
		return shape1;
	}
	public void setShape1(BasicObject shape1) {
		this.shape1 = shape1;
	}
	public BasicObject getShape2() {
		return shape2;
	}
	public void setShape2(BasicObject shape2) {
		this.shape2 = shape2;
	}
}

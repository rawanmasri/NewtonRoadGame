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

public class Triangle extends GameObject {
	private World world;
	private AppVar.TriangleType type;
	private String img;
	private float angularVelocity;
	private BasicObject shape;
	private boolean isStatic = false;
	private boolean isRotate=false;
	private int isCollision=1;
	public Triangle(World world,AppVar.TriangleType type,String img,float x,float y){
		this.world=world;
		this.type=type;
		this.img=img;
		angularVelocity=0;
		positionArray=new ArrayList<Vector2>();
		bodyArray=new ArrayList<Body>();
		basicObjectArray=new ArrayList<BasicObject>();
		gameJoint=new ArrayList<GameJoint>();
		jointInfo=new String[4];
		secondJoint=false;
		switch (type) {
		case SMALLTRIANGLE:
			shape = new BasicObject(world, BodyType.DynamicBody, basicBodyType.SMALLTRIANGLE, img, x, y);
			break;
		case MEDUIMTRIANGLE:
			shape = new BasicObject(world, BodyType.DynamicBody, basicBodyType.MEDUIMTRIANGLE, img, x, y);
			break;
		case LARGETRIANGLE:
			shape = new BasicObject(world, BodyType.DynamicBody, basicBodyType.LARGETRIANGLE, img, x, y);
			break;
		default:
			break;
		}
	}
	@Override
	public void setDynamic(Vector2 position) {
		if(isStatic==true){
			shape.getBody().get(0).setType(BodyType.StaticBody);
		}
		else{
			shape.getBody().get(0).setType(BodyType.DynamicBody);
			}
		shape.setDynamic();
		for(int i=0;i<gameJoint.size();i++){
			System.out.println("triangle joint dynamic");
			gameJoint.get(i).setDynamic();
		}
	}

	@Override
	public void setStatic(Vector2 position) {
		shape.getBody().get(0).setType(BodyType.DynamicBody);
		if(isRotate()==true){	
			shape.setRotate(isRotate());
		}
		else {
			shape.setRotate(isRotate());
		}
		shape.setStatic(position);
		for(int i=0;i<gameJoint.size();i++){
			System.out.println("triangle joint static");
			gameJoint.get(i).setStatic();
		}
	}

	@Override
	public ArrayList<Vector2> getPosition() {
		// TODO Auto-generated method stub
		positionArray.clear();
		positionArray.add(shape.getBody().get(0).getPosition());
		return positionArray;
	}

	@Override
	public void setPosition(Vector2 position) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float getDensity() {
		// TODO Auto-generated method stub
		return shape.getDensity();
	}

	@Override
	public void setDensity(float density) {
		// TODO Auto-generated method stub
		shape.setDensity(density);
	}

	@Override
	public float getFriction() {
		// TODO Auto-generated method stub
		return shape.getFriction();
	}

	@Override
	public void setFriction(float friction) {
		// TODO Auto-generated method stub
		shape.setFriction(friction);
	}

	@Override
	public float getRestitution() {
		// TODO Auto-generated method stub
		return shape.getRestitution();
	}

	@Override
	public void setRestitution(float restitution) {
		// TODO Auto-generated method stub
		shape.setRestitution(restitution);
	}

	@Override
	public ArrayList<Body> getBody() {
		// TODO Auto-generated method stub
		bodyArray.clear();
		bodyArray.add(shape.getBody().get(0));
		return bodyArray;
	}

	@Override
	public void setWidth(float width) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setHeight(float height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float getHeight() {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		basicObjectArray.clear();
		basicObjectArray.add(shape);
		return basicObjectArray;
	}
	public void setProperties(float density,float friction,float restitution,float angularVelocity,boolean isStatic,boolean isRotate,int isColied){
		this.angularVelocity = angularVelocity;
		shape.setDensity(density);
		shape.setFriction(friction);
		shape.setRestitution(restitution);
		shape.setAngularVelocity(angularVelocity);
		setStatic(isStatic);
		setRotate(isRotate);
		setIsCollision(isColied);
		shape.setIsCollision((short)isColied);
	}
	public World getWorld() {
		return world;
	}
	public void setWorld(World world) {
		this.world = world;
	}
	public AppVar.TriangleType getType() {
		return type;
	}
	public void setType(AppVar.TriangleType type) {
		this.type = type;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public float getAngularVelocity() {
		return angularVelocity;
	}
	public void setAngularVelocity(float angularVelocity) {
		this.angularVelocity = angularVelocity;
		//System.out.println(angularVelocity);
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
}

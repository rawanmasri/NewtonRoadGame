package sw.newtonroad.body;
import java.util.ArrayList;

import sw.newtonroad.handlers.AppVar;
import sw.newtonroad.handlers.AppVar.basicBodyType;
import sw.newtonroad.handlers.AppVar.chainType;
import sw.newtonroad.joint.Distance;
import sw.newtonroad.joint.GameJoint;
import sw.newtonroad.joint.Revolute;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.badlogic.gdx.physics.box2d.joints.RopeJointDef;
public class Chain extends GameObject {
	private World world;
	private AppVar.chainType type;
	private String img;
	private BasicObject[] segments;
	private RevoluteJoint [] joints;
	private BasicObject ropeSegment;
	private	float length=0;
	public Chain (World world,AppVar.chainType type,String img,float x,float y){
		this.world=world;
		this.type=type;
		this.img=img;
		positionArray=new ArrayList<Vector2>();
		bodyArray=new ArrayList<Body>();
		gameJoint=new ArrayList<GameJoint>();
		basicObjectArray=new ArrayList<BasicObject>();
		jointInfo=new String[4];
		secondJoint=false;
		float height=0f;
		switch (type) {
		case METALCHAIN:
			length=10;
			segments=new BasicObject[(int) length];
			joints=new RevoluteJoint[(int) (length-1)];
			height=1;
			for(int i=0;i<segments.length;i++){
				segments[i]=ropeSegment=new BasicObject(world,BodyType.DynamicBody,basicBodyType.METALCHAIN ,img,x,y);
			}
			break;
			case ROPE:
				length=70;
				segments=new BasicObject[(int) length];
				joints=new RevoluteJoint[(int) (length-1)];
				 height=.09f;
				for(int i=0;i<segments.length;i++){
					segments[i]=ropeSegment=new BasicObject(world,BodyType.DynamicBody,basicBodyType.ROPE ,img,x,y);
				}
				
				break;
			case FIXBALLCHAIN:
				length=3;
				segments=new BasicObject[(int) length];
				joints=new RevoluteJoint[(int) (length-1)];
				height=2.5f;
				segments[0]=ropeSegment=new BasicObject(world,BodyType.DynamicBody,basicBodyType.FIXBALLCHAIN ,"image/chain/BALLCHAIN1.png",x,y);
				segments[1]=ropeSegment=new BasicObject(world,BodyType.DynamicBody,basicBodyType.FIXBALLCHAIN ,"image/chain/BALLCHAIN2.png",x,y);
				segments[2]=ropeSegment=new BasicObject(world,BodyType.DynamicBody,basicBodyType.FIXBALLCHAIN,"image/chain/BALLCHAIN3.png",x,y);
				for(int i=0;i<segments.length;i++){
					segments[i].setDensity(0);
				}
				break;
			case FIXEDMETALIC:
				length=10;
				segments=new BasicObject[(int) length];
				joints=new RevoluteJoint[(int) (length-1)];
				height=1;
				for(int i=0;i<segments.length;i++){
					segments[i]=ropeSegment=new BasicObject(world,BodyType.DynamicBody,basicBodyType.METALCHAIN ,img,x,y);
				}
				break;
			case FIXEDROP:
				length=70;
				segments=new BasicObject[(int) length];
				joints=new RevoluteJoint[(int) (length-1)];
				 height=.09f;
				for(int i=0;i<segments.length;i++){
					segments[i]=ropeSegment=new BasicObject(world,BodyType.DynamicBody,basicBodyType.ROPE ,img,x,y);
				}
				break;
		}
		RevoluteJointDef jointDef=new RevoluteJointDef();
		jointDef.localAnchorA.y=-height/2;
		jointDef.localAnchorB.y=height/2;
		for(int i=0;i<joints.length;i++){
			jointDef.bodyA=segments[i].getBody().get(0);
			jointDef.bodyB=segments[i+1].getBody().get(0);
			joints[i]=(RevoluteJoint) world.createJoint(jointDef);			
		}
		RopeJointDef ropeJointDef=new RopeJointDef();
		ropeJointDef.localAnchorA.set(0,-height/2);
		ropeJointDef.localAnchorB.set(0,height/2);
		ropeJointDef.maxLength=height;
		for(int i=0;i<joints.length;i++){
			ropeJointDef.bodyA=segments[i].getBody().get(0);
			ropeJointDef.bodyB=segments[i+1].getBody().get(0);
	         world.createJoint(ropeJointDef);
	         }
	}
	@Override
	public void setDynamic(Vector2 position) {
		switch (type) {
		case FIXEDMETALIC:
		case FIXEDROP:	
			for(int i=0;i<length;i++){
				segments[i].setDynamic(segments[i].getPosition().get(0));
			}
			for(int i=0;i<gameJoint.size();i++){
				gameJoint.get(i).setDynamic();
			}
			segments[0].getBody().get(0).setType(BodyType.StaticBody);
			break;
		case METALCHAIN:
		case ROPE:
		case FIXBALLCHAIN:
			for(int i=0;i<length;i++){
				segments[i].setDynamic(segments[i].getPosition().get(0));
			}
			
			break;
		}
		for(int i=0;i<gameJoint.size();i++){
			System.out.println("wind mill set dynamic");
			gameJoint.get(i).setDynamic();
		}
		
	}
	@Override
	public void setStatic(Vector2 position) {
		switch (type) {
		case FIXEDMETALIC:
		case FIXEDROP:
			for(int i=0;i<length;i++){
				segments[i].setStatic(position);
			}
			if(type.equals(chainType.FIXBALLCHAIN)){
				for(int i=0;i<length;i++){
					segments[i].setDensity(0);
				}
			}
			for(int i=0;i<gameJoint.size();i++){
				System.out.println("wind mill set dynamic");
				gameJoint.get(i).setStatic();
			}
			segments[0].getBody().get(0).setType(BodyType.DynamicBody);
			break;

		case METALCHAIN:
		case ROPE:
		case FIXBALLCHAIN:
			for(int i=0;i<length;i++){
				segments[i].setStatic(position);
			}
			if(type.equals(chainType.FIXBALLCHAIN)){
				for(int i=0;i<length;i++){
					segments[i].setDensity(0);
				}
			}
			
			break;
		}
		for(int i=0;i<gameJoint.size();i++){
			System.out.println("wind mill set dynamic");
			gameJoint.get(i).setStatic();
		}
	}
	@Override
	public ArrayList<Vector2> getPosition() {
		positionArray.clear();
		for(int i=0;i<length;i++){
			positionArray.add(segments[i].getPosition().get(0));
		}
		return positionArray;
	}
	@Override
	public void setPosition(Vector2 position) {
	}
	@Override
	public float getDensity() {
		float desity=0;
		for(int i=0;i<segments.length;i++){
			desity+=segments[i].getDensity();
		}
		return desity;
	}
	@Override
	public void setDensity(float density) {
		for(int i=0;i<segments.length;i++){
			segments[i].setDensity(density/(float)segments.length);
		}
	}
	@Override
	public float getFriction() {
		return segments[0].getFriction();
	}
	@Override
	public void setFriction(float friction) {
		for(int i=0;i<segments.length;i++){
			segments[i].setFriction(friction);
		}
	}
	@Override
	public float getRestitution() {
		return segments[0].getRestitution();
	}
	@Override
	public void setRestitution(float restitution) {
		for(int i=0;i<segments.length;i++){
		segments[i].setRestitution(restitution);
		}
	}
	@Override
	public ArrayList<Body> getBody() {
		bodyArray.clear();
		for(int i=0;i<length;i++){
		 bodyArray.add(segments[i].getBody().get(0));
		}
		return bodyArray;
	}
	@Override
	public ArrayList<BasicObject> getBasicObject() {
		basicObjectArray.clear();
		for(int i=0;i<segments.length;i++){
			basicObjectArray.add(segments[i]);
		}
		return basicObjectArray;
	}
	public void setProperties(float density, float friction, float restitution) {
		for(int i=0;i<segments.length;i++){
			segments[i].setDensity(density/(float)segments.length);
		}
		for(int i=0;i<segments.length;i++){
			segments[i].setFriction(friction);
		}
		for(int i=0;i<segments.length;i++){
			segments[i].setRestitution(restitution);
			}
	}
	@Override
	public void setWidth(float width) {
		
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
	public World getWorld() {
		return world;
	}
	public void setWorld(World world) {
		this.world = world;
	}
	public AppVar.chainType getType() {
		return type;
	}
	public void setType(AppVar.chainType type) {
		this.type = type;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
}

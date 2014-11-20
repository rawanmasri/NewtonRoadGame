package sw.newtonroad.handlers;

import sw.newtonroad.body.BasicObject;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.DistanceJointDef;
import com.badlogic.gdx.physics.box2d.joints.PrismaticJoint;
import com.badlogic.gdx.physics.box2d.joints.PrismaticJointDef;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.badlogic.gdx.physics.box2d.joints.RopeJointDef;
import com.badlogic.gdx.physics.box2d.joints.WheelJoint;
import com.badlogic.gdx.physics.box2d.joints.WheelJointDef;

public class AppVar {
	public static final String TITLE="TestLevel";
	public static final int V_WIDTH=678;
	public static final int V_HIGHT=356;
	public static final int SCALE=2;
	public static final float STEP=1/60f;
	public static final float speed=200;
	public static boolean testPlay=false;
	public static final float BOX_STEP=1/60f;  
	public static final int BOX_VELOCITY_ITERATIONS=8;  
	public static final int BOX_POSITION_ITERATIONS=3;  
	public static final float WORLD_TO_BOX=0.01f;  
	public static final float BOX_WORLD_TO=100f;
	//types of all objects in the game 
	//
	public static enum basicBodyType {
		BALL,
		BOX,LARGEPENDULUM,SMALLPENDULUM,WOODTRIANGLE,IRONTRIANGLE,BASICLARGEPENDULUM,CENTERPTRANSPARENTENDULUM,BASICSMALLPENDULUM
		,PENDULUM360,LARGEWOODCUPBOARD,LARGEIRONCUPBOARD,SMALLWOODCUPBOARD,SMALLIRONCUPBOARD,SWING,SWING360,HORIZONTALSWING,HORIZONTALSWING360,ROUNDCIRCLESLOW,ROUNDCIRCLEFAST
		,BALANCEWOOD,METALCHAIN,ROPE,FIXBALLCHAIN,ROPESTAND1,STAND1,ROCKET,CARSHAPE,WHEEL,LARGECOGCIRCLE,LARGECOGBOX,
		BUKETWHEEL,BUKETSHAPE,BUKETSTAND,
		SMALLTRIANGLE,MEDUIMTRIANGLE,LARGETRIANGLE,BALLON,CHARE,UPARROW,RIGHTARROW,WOODSHAPE,STONSTAND,GUN,BULLET,DRAGON,SHAFT,ELEVATORUP
	}
	public static enum cannonType{
		CANOON,FLAMCANNON,RIGHTCANOON,SCANNON,GUN,DRAGON,CROSS,CLYNDER,CLYNDER2
	}
	
	public static enum imponderableType{
		LARGEPENDULUM,SMALLPENDULUM,PENDULUM360,LARGEWOODCUPBOARD,LARGEIRONCUPBOARD,SMALLWOODCUPBOARD,
		SMALLIRONCUPBOARD,SWING,SWING360,HORIZONTALSWING,HORIZONTALSWING360,ROUNDCIRCLESLOW,ROUNDCIRCLEFAST,BALANCE
	}
	public static enum TriangleType{
		SMALLTRIANGLE,MEDUIMTRIANGLE,LARGETRIANGLE
	}
	public static enum chainType{
		METALCHAIN,ROPE,FIXBALLCHAIN,BALLSTAND,STAND,ROPESTAND,FIXEDMETALIC,FIXEDROP
	}
	public static enum elevatorType{
		ELEVATORUP,ELEVATERDOWN,ELEVATORLEFT,ELEVATORRIGHT
	}
	public static enum fixedType{
		BALLON,CATFACE,CHARE,DARKWOOD,DICE,STONSTAND
		,STONWALL,STANDWOOD,WOODWALL,UPARROW,RIGHTARROW,WOODSHAPE,RIGHTSTARE,LEFTSTARE,MIDLESTARE,BUCKET,METAL,BIASWOOD,WHEEL
	}
	public static enum standType{
		STAND1
	}
	public static enum vehicleType{
		CAR,SMALLCAR,SKATE,LARGESKATE,SKOTER,BUKET
	}
	public static enum windMillType{
		MINIWINDMILL,MINIWINDMILL90,IRONWINDMILL,WOODWINDMILL,SMALLIRONMILL,SMALLWOODMILL,IRONWHEEL,FUNKYWINDMILL,WOODWHEEL,MOVEMENTWHEEL,SMALLMOVEMENTWHEEL,CIRCLEWINDMILL,ROSEWINDMILL,
		SMALLROSEWINDMILL
	}
	public static enum cogType{
		LARGECOG,SMALLCOG
	}
	public static enum animationObject{
		BUNNY1,BUNNY2,MAN1,MAN2,FALCON,BIRD1,BIRD2,BIRD3,BIRD4,STAR1,STAR2,FIRE,ROSE1,BOOM1,BOOM2,BOOM3,BOOM4,COLOR1,COLOR2,JELLYFISH1,JELLYFISH2,BLUE1,BLUE2,TEDDY,FLAME1,FLAME2,BLOOD,BLACKMONSTER,WINDMILL
		,GREENMONSTER,GREENMONSTER2,BLUEMONSTER,GREEN,BAT,TREE1,TREE2,LIGHT1,LIGHT2,FISH,PLUS,SAD,MUSHROOM1,MUSHROOM2,NEWTON,NEWTON2,BURNTNEWTON,SKLTON,FLAYFROG,ROCK1,ROCK2,EVIL,GIRL,PINKLIGHT,CUTE,HERO1,HERO2,HANDSOMEBOY1,HANDSOMEBOY2,FIXEDNEWTON,APPLE,
		DEER1,DEER2,DOG1,DOG2,CAT1,CAT2,PIGGY1,PIGGY2
	}
	public static enum disappear{
		DISAPPEAR
	}
	
	public static  BasicObject[] createRope(World world,int length,int height,basicBodyType type,String image,float possitionX,float possitionY){
		BasicObject[] segments=new BasicObject[length];
		RevoluteJoint [] joints=new RevoluteJoint[length-1];
		for(int i=0;i<segments.length;i++){
			segments[i]=new BasicObject(world,BodyType.DynamicBody,type ,image,possitionX,possitionY-i*2);
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
		return segments;
	}
	public static void createDistanceJoin(World world,Body bodyA,Body bodyB,Vector2 vecA,Vector2 vecB){
		DistanceJointDef dis= new DistanceJointDef();
		dis.length=0;
	    dis.initialize(bodyA,bodyB,vecA,vecB);
	    world.createJoint(dis);
	}
	public static  RevoluteJoint createRevoluteJoint(World world,Body bodyA,Body bodyB,Vector2 vecA){
		RevoluteJointDef rjd=new RevoluteJointDef();
		rjd.initialize(bodyA,bodyB,vecA);
		 RevoluteJoint revoluteJoint=(RevoluteJoint) world.createJoint(rjd);
		return revoluteJoint;
	}
	public static WheelJoint [] createWheelJoint(World world,Body shape,Body leftWheel,Body rightWheel,float width,float height,float density, float maxMotorTorque){
		WheelJoint[] wheelJoint=new WheelJoint[2];
		// left axis
		WheelJointDef axisDef = new WheelJointDef();
		axisDef.bodyA = shape;
		axisDef.bodyB = leftWheel;
		axisDef.localAnchorA.set(-width / 2 * .82f + 1.5f/3.5f, -height / 2 * 1.5f);
	   // axisDef.localAxisA.set(.5f,.5f);
	    axisDef.frequencyHz =density*2;	//need the dencity of chaisses (chaisses.getfixtureDef.density) 
	    axisDef.localAxisA.set(Vector2.Y);
	    axisDef.maxMotorTorque = maxMotorTorque;//10 * 10;
	   
	    WheelJoint leftAxis = (WheelJoint) world.createJoint(axisDef);
		// right axis
		axisDef.bodyB = rightWheel;
		axisDef.localAnchorA.x *= -1;
		 //axisDef.maxMotorTorque = 5;//10 * 10;
		WheelJoint rightAxis = (WheelJoint) world.createJoint(axisDef);
		wheelJoint[0]=leftAxis;
		wheelJoint[1]=rightAxis;
		return wheelJoint;
	}
	public static PrismaticJoint createPrismaticJoint(World world,Body elevoter,Body stand,Vector2 postion,Vector2 axis){
		PrismaticJointDef pjd = new PrismaticJointDef();
		pjd.initialize(elevoter, stand, postion, axis);
		pjd.lowerTranslation = -3;
		pjd.upperTranslation = 7;	
		PrismaticJoint prismaticjoint = (PrismaticJoint)world.createJoint(pjd);
		return prismaticjoint;
	}
}

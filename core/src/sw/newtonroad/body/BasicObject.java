package sw.newtonroad.body;

import java.util.ArrayList;

import sw.newtonroad.handlers.AppVar;
import sw.newtonroad.handlers.AppVar.basicBodyType;
import sw.newtonroad.joint.GameJoint;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class BasicObject extends GameObject {
	private World world;
	private String imagePath;
	private AppVar.basicBodyType type ;
	private  Body body;
	private BodyDef bodyDef;
	private Fixture fixture;
	private FixtureDef fixtureDef;
	private Sprite sp;
	private float [] proparity;
	private PolygonShape  boxShape;
	private CircleShape ballShape;
	private float angularVelocityY;
	private float angularVelocityX;
	private boolean isStatic = false;
	private boolean isRotate=false;
	private int isCollision=1;
	//raneen
	private boolean takeSound=false;
	public static Music music;
	public static Music music2;
	//create ground
	public BasicObject(World world, BodyType bodyType,Vector2 axis1,Vector2 axis2,float positionX, float positionY){
		basicInit( world,bodyType,positionX,positionY);
		ChainShape groundShape=new ChainShape();
		//groundShape.createChain(new Vector2[]{new Vector2(-50,0),new Vector2(50,0)});
		groundShape.createChain(new Vector2[]{axis1,axis2});
		fixtureDef.shape=groundShape;
		body = world.createBody(bodyDef);
		body.createFixture(fixtureDef);
	}
	//create Object
	public BasicObject(World world, BodyType bodyType,AppVar.basicBodyType type,String imagePath,float positionX, float positionY) {
		basicInit(world,bodyType,positionX,positionY);
		this.type=type;
		switch (type) {
		//ball	
		case BALL:
			ballShape.setRadius(1.3f);
			proparity[5]=proparity[6]=1.3f;
			break;
		//box	
		case BOX:
			boxShape.setAsBox(1f,1f);
			proparity[5]=1f;proparity[6]=1f;
			break;
		//cannon
		case GUN:
			boxShape.setAsBox(2.5f,1f);
			proparity[5]=3.4f;proparity[6]=1.4f;
			break;
		case BULLET:
			boxShape.setAsBox(1f,.3f);
			proparity[5]=1.1f;proparity[6]=.7f;
			break;
		case DRAGON:
			boxShape.setAsBox(1f,3f);
			proparity[5]=2.4f;proparity[6]=3.7f;
			break;	
		case SHAFT:
			boxShape.setAsBox(1.3f,.3f);
			proparity[5]=1.4f;proparity[6]=.5f;
			break;	
		case ELEVATORUP:
			boxShape.setAsBox(1f,4.2f);
			proparity[5]=1.4f;proparity[6]=5f;
			break;	
		//triangle
		case SMALLTRIANGLE:
			boxShape.set(new float[] {-.5f,-.5f,0,.5f,0,.5f,.5f,-.5f}); // counterclockwise order
			proparity[5]=1;proparity[6]=1;
			break;
		case MEDUIMTRIANGLE:
			boxShape.set(new float[] {-1.5f,-1.5f,0,1.5f,0,1.5f,1.5f,-1.5f}); 
			proparity[5]=1.5f;proparity[6]=1.5f;
			break;
		case LARGETRIANGLE:
			boxShape.set(new float[] {-2f,-2f,0,2f,0,2f,2f,-2f}); 
			proparity[5]=2f;proparity[6]=2f;
			break;
		//fixedshape
		case BALLON:
			ballShape.setRadius(.8f);
			proparity[5]=proparity[6]=1f;
			break;
		case CHARE:
			boxShape.setAsBox(3.7f,.7f);
			proparity[5]=4;proparity[6]=.9f;
			break;
		case UPARROW:
			boxShape.setAsBox(.5f,1.7f);
			proparity[5]=.8f;proparity[6]=1.7f;
			break;
		case RIGHTARROW:
			boxShape.setAsBox(1.7f,.5f);
			proparity[5]=1.7f;proparity[6]=.8f;
			break;
		case WOODSHAPE:
			boxShape.setAsBox(3.7f,1.7f);
			proparity[5]=4f;proparity[6]=2f;
			break;
		case STONSTAND:
			boxShape.setAsBox(.84f,4.9f);
			proparity[5]=1.2f;proparity[6]=5f;
			break;
		case LARGEPENDULUM:
			boxShape.setAsBox(.25f,4f);
			proparity[5]=.35f;proparity[6]=4f;
			break;
		case SMALLPENDULUM:
			boxShape.setAsBox(.1f,1.2f);
			proparity[5]=.2f;proparity[6]=1.2f;
			break;
		case LARGEIRONCUPBOARD:
			boxShape.setAsBox(.27f,2.7f);
			proparity[5]=.4f;proparity[6]=2.7f;
			break;
		case PENDULUM360:
			boxShape.setAsBox(.2f,4.5f);
			proparity[5]=.4f;proparity[6]=4.5f;
			break;
		case SMALLWOODCUPBOARD:
			boxShape.setAsBox(.26f,1f);
			proparity[5]=.45f;proparity[6]=1f;
			break;
		case SMALLIRONCUPBOARD:
			boxShape.setAsBox(.05f,1.7f);
			proparity[5]=.25f;proparity[6]=1.7f;
			break;
		case SWING:
			boxShape.setAsBox(.35f,2.7f);
			proparity[5]=.35f;proparity[6]=2.7f;
			break;
		case SWING360:
			boxShape.setAsBox(.35f,3f);
			proparity[5]=.35f;proparity[6]=3f;
			break;
		case HORIZONTALSWING:
			boxShape.setAsBox(4f,.45f);
			proparity[5]=4f;proparity[6]=.45f;
			break;
		case HORIZONTALSWING360:
			boxShape.setAsBox(2f,.5f);
			proparity[5]=2f;proparity[6]=.5f;
			break;
		case BALANCEWOOD:
			boxShape.setAsBox(6,.5f);
			proparity[5]=6;proparity[6]=.5f;
			break;
		case METALCHAIN:
			boxShape.setAsBox(.5f/2f,1/2f);
			proparity[5]=.5f/2f;proparity[6]=1/2f;
			break;
		case ROPE:
			ballShape.setRadius(.2f );
			proparity[5]=proparity[6]=.2f;
			break;
		case FIXBALLCHAIN:
			ballShape.setRadius(1f );
			proparity[5]=proparity[6]=1f;
			break;
		case ROCKET:
			boxShape.setAsBox(1f,1.5f);
			proparity[5]=1f;proparity[6]=1.5f;
			break;
			//standType
		case ROPESTAND1:
			boxShape.setAsBox(.5f/2f,1f);
			proparity[5]=.5f/2f;proparity[6]=1f;
			break;
		case STAND1:
			boxShape.setAsBox(4,.3f);
			proparity[5]=4;proparity[6]=.3f;
			break;
		//vehicleType
		case CARSHAPE:
			boxShape.set(new float[] {-4 / 1.5f , -1.5f /2 , 4 / 1.5f, -1.5f / 2, 4 / 2 * .4f, 1.5f / 1.8f, -4 / 2 * .3f, 1.5f / 2 * 1.2f}); // counterclockwise order
			proparity[5]=4;proparity[6]=1.5f;
			break;  
		case WHEEL:
			ballShape.setRadius(2/2.5f );
			proparity[5]=proparity[6]=2/2.5f;
			break;
		case BUKETWHEEL:
			ballShape.setRadius(1.7f/3.5f );
			proparity[5]=proparity[6]=1.7f/3.5f;
			break;
		case BUKETSHAPE:
			boxShape.setAsBox(.3f, 1.5f);
			proparity[5]=.3f;proparity[6]=1.5f;
			break;
		case BUKETSTAND:
			boxShape.setAsBox(4f, .3f);
			proparity[5]=4f;proparity[6]=.3f;
			break;
			
		//cogType
		case LARGECOGCIRCLE:
			ballShape.setRadius(4 );
			proparity[5]=proparity[6]=4;
			break;
		case LARGECOGBOX:
			boxShape.setAsBox(.7f,.7f);
			proparity[5]=.7f;proparity[6]=.7f;
			break;
       
		}			
		if (type==basicBodyType.BOX|| 
	            type==basicBodyType.SMALLTRIANGLE||type==basicBodyType.MEDUIMTRIANGLE||type==basicBodyType.LARGETRIANGLE||
				type==basicBodyType.CHARE||type==basicBodyType.UPARROW||type==basicBodyType.RIGHTARROW||type==basicBodyType.WOODSHAPE||type==basicBodyType.STONSTAND||type==basicBodyType.LARGEPENDULUM||
				type==basicBodyType.SMALLPENDULUM||type==basicBodyType.PENDULUM360||type==basicBodyType.LARGEIRONCUPBOARD||
				type==basicBodyType.SMALLWOODCUPBOARD||type==basicBodyType.SMALLIRONCUPBOARD||type==basicBodyType.SWING||
				type==basicBodyType.SWING360||type==basicBodyType.HORIZONTALSWING||type==basicBodyType.HORIZONTALSWING360||
				type==basicBodyType.BALANCEWOOD||type==basicBodyType.METALCHAIN||type==basicBodyType.ROCKET||
				type==basicBodyType.ROPESTAND1||type==basicBodyType.STAND1||type==basicBodyType.CARSHAPE||
				type==basicBodyType.BUKETSHAPE||type==basicBodyType.BUKETSTAND
				||type==basicBodyType.LARGECOGBOX||type==basicBodyType.GUN||type==basicBodyType.BULLET||type==basicBodyType.DRAGON||type==basicBodyType.SHAFT||type==basicBodyType.ELEVATORUP){
			fixtureDef.shape = boxShape;
		}
		else {
			fixtureDef.shape = ballShape;
		}
		width=proparity[5];
		height=proparity[6];
		this.imagePath=imagePath;
		createBody();
		putProperties();
	}
	private void basicInit( World world,BodyType bodyType,float positionX, float positionY){
	    boxShape=new PolygonShape();
	    ballShape = new CircleShape();
		positionArray=new ArrayList<Vector2>();
		bodyArray=new ArrayList<Body>();
		gameJoint=new ArrayList<GameJoint>();
		jointInfo=new String[4];
		secondJoint=false;
		isStatic=false;
		isRotate=false;
		basicObjectArray=new ArrayList<BasicObject>();
		this.world=world;
		proparity=new float[8];
		bodyDef=new BodyDef();
		bodyDef.type = bodyType;
    	bodyDef.position.set(positionX, positionY);
		fixtureDef=new FixtureDef();
		fixtureDef.density=.0001f;
		angularVelocityY=0;
		angularVelocityX=0;
		setAngularVelocityX(0);
		//raneen
		takeSound=false;
		music = Gdx.audio.newMusic(Gdx.files.internal("music/ball.wav"));
		music2 = Gdx.audio.newMusic(Gdx.files.internal("music/box.wav"));
	}
	private void createBody() {
		body = world.createBody(bodyDef);
		body.setGravityScale(0f);
		sp =  new Sprite(new Texture(imagePath));;
		sp.setSize( proparity[5] * 2,proparity[6]* 2);
		sp.setOrigin(sp.getWidth()/2, sp.getHeight()/2);
		body.setUserData(sp);
		fixture=body.createFixture(fixtureDef);
	}
	private void refreshImage(){
		sp =  new Sprite(new Texture(imagePath));;
		sp.setSize( getWidth() * 2,getHeight()* 2);
		sp.setOrigin(sp.getWidth()/2, sp.getHeight()/2);
		body.setUserData(sp);
		refreshBody();
	}
	private void putProperties(){
		switch (type) {
		//ball
		case BALL:
			saveProparity(1,.01f,0f,.8f);
			break;
		//box
		case BOX:
			saveProparity(1,1.5f,.5f,.01f);
			break;
		//cannon
		case GUN:	
			saveProparity(1,1.5f,.5f,.01f);
			break;	
		case BULLET:	
			saveProparity(1,1.5f,.5f,.01f);
			break;	
		case DRAGON:	
			saveProparity(1,1.5f,.5f,.01f);
			break;	
		case SHAFT:
			saveProparity(1,1.5f,.5f,.01f);
			break;	
		//elovater
		case ELEVATORUP:
			saveProparity(1,1.5f,.5f,.01f);
			break;	
		//triangle	
		case SMALLTRIANGLE:
			saveProparity(1,.5f,.7f,.85f);
			break;
		case MEDUIMTRIANGLE:
			saveProparity(1,1.4f,.45f,.72f);
			break;
		case LARGETRIANGLE:
			saveProparity(1,2.5f,.22f,.45f);
			break;
			//fixedshape
		case BALLON:
			saveProparity(1, .1f, 1, 0);
			break;
		case CHARE:
			saveProparity(1,2.5f, .77f,1f);
			break;
		case RIGHTARROW:
		case UPARROW:
			saveProparity(1,1.5f, .77f,.75f);
		        	break;
		case WOODSHAPE:
			saveProparity(1,1.5f, .77f,.75f);
			break;
		case STONSTAND:
			saveProparity(1, 5.2f, .67f,1f);
			break;
		        //imponderable	
		case LARGEPENDULUM:
			saveProparity(1,60f,.5f,1f);
			break;
		case SMALLPENDULUM:
			saveProparity(1,.5f,.8f,.6f);
			break;
		case PENDULUM360:
			saveProparity(1,.5f,.7f,.3f);
			break;
		case LARGEIRONCUPBOARD:
			saveProparity(1,30f,.2f,1f);
			break;
		case SMALLWOODCUPBOARD:
			saveProparity(1,3f,.84f,.6f);
			break;
		case SMALLIRONCUPBOARD:
			saveProparity(1,7f,.56f,.89f);
			break;
		case SWING:
			saveProparity(1,10f,.23f,1f);
			break;
		case SWING360:
			saveProparity(1,.9f,.88f,.1f);
			break;
		case HORIZONTALSWING:
			saveProparity(1,8f,.36f,.75f);
			break;
		case HORIZONTALSWING360:
			saveProparity(1,4f,.67f,.22f);
			break;
		case BALANCEWOOD:
			saveProparity(1,4f,.1f,.01f);
			break;
		case METALCHAIN:
			saveProparity(1,.5f,1f,1f);
			break;
		case ROPE:
			saveProparity(1,.001f,1f,1f);
			break;
		case FIXBALLCHAIN:
			saveProparity(1,0f,0f,1f);
			break;
		case ROCKET:
			saveProparity(1, 5f, .2f, .4f);
			break;
			//standType
		case ROPESTAND1:
			saveProparity(1,1f,1f,1f);
			break;
		case STAND1:
			saveProparity(1,2,.67f,.01f);
			break;
			
			//vehicleType
		case CARSHAPE:
			saveProparity(1,5,.4f,.3f);
			break;
		case WHEEL:
			saveProparity(1,.5f,1f,.4f);
			break;
			
		case BUKETWHEEL:
			saveProparity(1,.7f,.35f,.3f);
			break;
		case BUKETSHAPE:
		case BUKETSTAND:
			saveProparity(1,1.5f,.35f,.3f);
			break;
			
			//cog    	
		case LARGECOGCIRCLE:
			saveProparity(1,5,.1f,.75f);
			break;
		case LARGECOGBOX:
			saveProparity(1,1,.1f,.75f);
			break;
		default:
			saveProparity(1,10,.1f,.75f);
		        	  break; 	
		}	
	}	
	private void saveProparity(float gravity,float density,float friction,float restitution){
	    proparity[1]=density;
		proparity[2]=friction;
		proparity[3]= restitution;
		angularVelocityY=proparity[4]=0;
		angularVelocityX=proparity[7]=0;
		//angularVelocityX=
	}
	public void changeBodyType(BodyType bodyType){
		body.setType(bodyType);
	}
	private void refreshBody(){
	body.destroyFixture(fixture);
	body.createFixture(fixtureDef);
	}
	@Override
	public void setDynamic(Vector2 position) {
		body.setGravityScale(1f);
		setProperties( proparity[1], proparity[2], proparity[3],proparity[7],proparity[4]);
		if(isStatic==true){body.setType(BodyType.StaticBody);}
		if(isRotate==true){	fixtureDef.density= proparity[1];}
		//raneen
		//if(takeSound==true){music.play();music.setLooping(true);}
		for(int i=0;i<gameJoint.size();i++){
			gameJoint.get(i).setDynamic();
		}
	}
	@Override
	public void setStatic(Vector2 position) {
	//	bodyDef.position.set(new Vector2(0,0));
		body.setTransform( position, 0);
    	body.setGravityScale(0f);
		fixtureDef.density=.0001f;
		fixtureDef.friction=1f;
		fixtureDef.restitution=0.0f;
		body.setAngularVelocity(0);
		body.setLinearVelocity(0, 0);
		
		if(isStatic==true){body.setType(BodyType.DynamicBody);}
		if(isRotate==true){	fixtureDef.density= 0;}
		//raneen
		//if(takeSound==true){music.pause();}
		fixtureDef.filter.categoryBits =1;
		fixtureDef.filter.maskBits=1;
		fixtureDef.filter.categoryBits =(short) isCollision;
		fixtureDef.filter.maskBits=(short) isCollision;
		refreshBody();
		for(int i=0;i<gameJoint.size();i++){
			gameJoint.get(i).setStatic();
		}
	}
	@Override
	public ArrayList<Vector2> getPosition() {
		positionArray.clear();
		positionArray.add(body.getPosition());
		return positionArray;
	}
	@Override
	public void setPosition(Vector2 position) {
		body.setTransform( position, 0);
		refreshBody();
	}
	@Override
	public float getDensity() {
		return proparity[1];
	}
	@Override
	public void setDensity(float density) {
		fixtureDef.density= proparity[1]=(float) density;
		refreshBody();
	}
	@Override
	public float getFriction() {
		return proparity[2];
	}
	@Override
	public void setFriction(float friction) {
		fixtureDef.friction= proparity[2]=friction;
		refreshBody();
	}
	@Override
	public float getRestitution() {
		return proparity[3];
	}
	@Override
	public void setRestitution(float restitution) {
		fixtureDef.restitution= proparity[3]=restitution;
		refreshBody();
	}
	@Override
	public ArrayList<Body> getBody() {
		bodyArray.clear();
		bodyArray.add(body);
		return bodyArray;
	}
	@Override
	public void setWidth(float width) {
		switch (type) {
		case CARSHAPE:
			 boxShape.set(new float[] {-width/ 1.5f , -getHeight()/2f , width / 1.5f, -getHeight()/ 2f, width / 2 * .4f, getHeight() / 1.8f, -width / 2 * .3f, getHeight() / 2 * 1.2f});
			 break;
		default:
			boxShape.setAsBox(width, getHeight());
			break;
		}
		proparity[5]=width;
       fixtureDef.shape=boxShape;
		refreshImage();
	}
	@Override
	public float getWidth() {
		return proparity[5];
	}
	@Override
	public void setHeight(float height) {
		switch (type) {
		case CARSHAPE:
			 boxShape.set(new float[] {-getWidth()/ 1.5f , -height/2f , getWidth() / 1.5f, -height/ 2f, getWidth() / 2 * .4f, height / 1.8f, -getWidth() / 2 * .3f,height / 2 * 1.2f});
			 break;	
		default:
			boxShape.setAsBox(getWidth(), height);
			break;
		}
		proparity[6]=height;
		fixtureDef.shape=boxShape;
		refreshImage();
	}
	@Override
	public float getHeight() {
		return proparity[6];
	}
	@Override
	public void setRadius(float radius) {
		proparity[5]=radius;
		proparity[6]=radius;
		
		ballShape.setRadius(radius);
		fixtureDef.shape=ballShape;
		refreshImage();
	}
	@Override
	public float getRadius() {
		return proparity[5];
	}
	public void setProperties(float density,float friction,float restitution,float angularVelocity1,float angularVelocity2){
		fixtureDef.density= proparity[1]=density;
		fixtureDef.friction=proparity[2]=friction;
		fixtureDef.restitution=proparity[3]= restitution;
		body.applyForceToCenter(angularVelocity1,angularVelocity2, true);this.angularVelocityY=angularVelocity2;proparity[4]=angularVelocity2;
		this.setAngularVelocityX(angularVelocity1);proparity[7]=angularVelocity1;
		refreshBody();
	}
	public void setProperties(float density,float friction,float restitution,float angularVelocity1,float angularVelocity2,boolean isStatic,boolean isRotate,int isColied,boolean takeSound){
		fixtureDef.density= proparity[1]=density;
		fixtureDef.friction=proparity[2]=friction;
		fixtureDef.restitution=proparity[3]= restitution;
		body.applyForceToCenter(angularVelocity1,angularVelocity2, true);this.angularVelocityY=angularVelocity2;proparity[4]=angularVelocity2;
		this.setAngularVelocityX(angularVelocity1);proparity[7]=angularVelocity1;
		setisStatic(isStatic);
		setRotate(isRotate);
		setIsCollision(isColied);
		setTakeSound(takeSound);
		refreshBody();
		
	}
	public void setProperties(float density,float friction,float restitution,float angularVelocity1,float angularVelocity2,float width,float hieght,boolean isStatic,boolean isRotate,int isColied,boolean takeSound){
		fixtureDef.density= proparity[1]=density;
		fixtureDef.friction=proparity[2]=friction;
		fixtureDef.restitution=proparity[3]= restitution;
		body.applyForceToCenter(angularVelocity1,angularVelocity2, true);this.angularVelocityY=angularVelocity2;proparity[4]=angularVelocity2;
		this.setAngularVelocityX(angularVelocity1);proparity[7]=angularVelocity1;
		setWidth(width);
		setHeight(hieght);
		setisStatic(isStatic);
		setRotate(isRotate);
		setIsCollision(isColied);
		setTakeSound(takeSound);
		refreshBody();
	}
	public void setDynamic(){
		body.setGravityScale(1f);
		setProperties( proparity[1], proparity[2], proparity[3],proparity[7],proparity[4]);
	}
	public void setStatic() {
    	body.setGravityScale(0f);
		fixtureDef.density=.0001f;
		fixtureDef.friction=1f;
		fixtureDef.restitution=0.0f;
		refreshBody();
		for(int i=0;i<gameJoint.size();i++){
			gameJoint.get(i).setStatic();
		}
	}
	public AppVar.basicBodyType getType(){
		return type;
	}
	@Override
	public ArrayList<BasicObject> getBasicObject() {
		basicObjectArray.clear();
		basicObjectArray.add(this);
		return basicObjectArray ;
	}
	public Fixture getFixture(){
		return fixture;
	}
	public float getAngularVelocity(){
		return angularVelocityY;
	}
	public void setAngularVelocity(float angularVelocity){
		this.angularVelocityY=angularVelocity;
		proparity[4]=angularVelocity;
	}
	public World getWorld() {
		return world;
	}
	public void setWorld(World world) {
		this.world = world;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public void setType(AppVar.basicBodyType type) {
		this.type = type;
	}
	public FixtureDef getFixtureDef() {
		return fixtureDef;
	}
	public void setFixtureDef(FixtureDef fixtureDef) {
		this.fixtureDef = fixtureDef;
	}
	public boolean isStatic() {
		return isStatic;
	}
	public void setisStatic(boolean isStatic) {
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
	public boolean isTakeSound() {
		return takeSound;
	}
	public void setTakeSound(boolean takeSound) {
		this.takeSound = takeSound;
	}
	public float getAngularVelocityX() {
		return angularVelocityX;
	}
	public void setAngularVelocityX(float angularVelocityX) {
		this.angularVelocityX = angularVelocityX;
	}
}

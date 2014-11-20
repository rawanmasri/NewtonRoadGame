package sw.newtonroad.body;

import java.util.ArrayList;

import sw.newtonroad.body.AnimationObject;
import sw.newtonroad.body.BasicObject;
import sw.newtonroad.animation.Player;
import sw.newtonroad.handlers.AppVar;
import sw.newtonroad.handlers.AppVar.basicBodyType;
import sw.newtonroad.joint.GameJoint;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public class Cannon extends GameObject {
	private World world;
	private AppVar.cannonType type;
	private String img;
	private BasicObject shape;
	private BasicObject stand;
	private BasicObject ball;
	private AnimationObject flame;
	private float virtualDencity;
	private Array<Body> tmpBodies ;
	private ArrayList<BasicObject> ballObject ;
	private ArrayList<AnimationObject> flameObject ;
	private int ballNumber=0;
	//raneen
	private boolean takeSound=false;
	private Music music;
	public Cannon(World world,AppVar.cannonType type,String img,float x,float y){
		this.world=world;
		this.type = type;
		this.img=img;
		ballObject=new ArrayList<BasicObject>();
		positionArray=new ArrayList<Vector2>();
		bodyArray=new ArrayList<Body>();
		basicObjectArray=new ArrayList<BasicObject>();
		flameObject=new ArrayList<AnimationObject>();
		tmpBodies = new Array<Body>();
		gameJoint=new ArrayList<GameJoint>();
		jointInfo=new String[4];
		secondJoint=false;
		ballNumber=30;
		//raneen
		takeSound=false;
		music = Gdx.audio.newMusic(Gdx.files.internal("music/cannon.wav"));
		switch (type) {
		case FLAMCANNON:
		case CANOON:
			shape = new BasicObject(world, BodyType.DynamicBody, basicBodyType.BOX,img,0,2);
			shape.setWidth(4);
			shape.setHeight(.5f);
			shape.getBody().get(0).setTransform(shape.getBody().get(0).getPosition(), -45);	
			stand = new BasicObject(world, BodyType.DynamicBody, basicBodyType.BOX,"image/cannon/CANNONSTAND.png",0,0);
			stand.setWidth(4);
			stand.setHeight(.5f);
			shape.setDensity(0);
			stand.setDensity(0);
			AppVar.createRevoluteJoint(world, shape.getBody().get(0), stand.getBody().get(0),shape.getBody().get(0).getWorldCenter());
			virtualDencity = 9.5f;
			break;
		case RIGHTCANOON:
			shape = new BasicObject(world, BodyType.DynamicBody, basicBodyType.BOX,img,0,2);
			shape.setWidth(4);
			shape.setHeight(.5f);
			shape.getBody().get(0).setTransform(shape.getBody().get(0).getPosition(), 45);	
			stand = new BasicObject(world, BodyType.DynamicBody, basicBodyType.BOX,"image/cannon/CANNONSTAND.png",0,0);
			stand.setWidth(4);
			stand.setHeight(.5f);
			shape.setDensity(0);
			stand.setDensity(0);
			AppVar.createRevoluteJoint(world, shape.getBody().get(0), stand.getBody().get(0),shape.getBody().get(0).getWorldCenter());
			virtualDencity = 9.5f;
			break;
		case SCANNON:
			shape = new BasicObject(world, BodyType.DynamicBody, basicBodyType.BOX,img,0,2);
			shape.setWidth(4);
			shape.setHeight(2f);
			shape.setDensity(0);
			break;
		case GUN:
			shape = new BasicObject(world, BodyType.DynamicBody, basicBodyType.GUN,img,0,2);
			shape.setDensity(0);
			break;
		case DRAGON:
			shape = new BasicObject(world, BodyType.DynamicBody, basicBodyType.DRAGON,img,0,2);
			shape.setDensity(0);
			break;
		case CROSS:
			shape = new BasicObject(world, BodyType.DynamicBody, basicBodyType.DRAGON,img,0,2);
			shape.setWidth(1.3f);
			shape.setHeight(3.3f);
			shape.setDensity(0);
			break;
		case CLYNDER:
			shape = new BasicObject(world, BodyType.DynamicBody, basicBodyType.BOX,img,0,2);
			shape.getBody().get(0).setTransform(shape.getBody().get(0).getPosition(),-62);
			shape.setWidth(.8f);
			shape.setHeight(3.7f);
			shape.setDensity(0);
			break;
		case CLYNDER2:
			shape = new BasicObject(world, BodyType.DynamicBody, basicBodyType.BOX,img,0,2);
			shape.getBody().get(0).setTransform(shape.getBody().get(0).getPosition(), -45);
			shape.setWidth(.8f);
			shape.setHeight(3.7f);
			shape.setDensity(0);
			break;
		default:
			break;
		}
	}
	@Override
	public void setDynamic(Vector2 position) {
		// TODO Auto-generated method stub
		switch (type) {
		case CANOON:
		case FLAMCANNON:
		case RIGHTCANOON:
			stand.getBody().get(0).setType(BodyType.StaticBody);
			shape.getBody().get(0).setType(BodyType.StaticBody);
			break;
		case SCANNON:
		case GUN:
		case DRAGON:
		case CROSS:	
		case CLYNDER:
		case CLYNDER2:
			shape.getBody().get(0).setType(BodyType.StaticBody);
			break;
		default:
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
		// TODO Auto-generated method stub
		switch (type) {
		case CANOON:
		case FLAMCANNON:
		case RIGHTCANOON:
			shape.getBody().get(0).setType(BodyType.DynamicBody);
			stand.getBody().get(0).setType(BodyType.DynamicBody);
			break;
		case SCANNON:
		case GUN:	
		case DRAGON:
		case CROSS:	
		case CLYNDER:
		case CLYNDER2:
			shape.getBody().get(0).setType(BodyType.DynamicBody);
			break;
		default:
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
		switch (type) {
		case CANOON:
		case FLAMCANNON:
		case RIGHTCANOON:
			positionArray.clear();
			positionArray.add(shape.getPosition().get(0));
			positionArray.add(stand.getPosition().get(0));
			break;
		case SCANNON:
		case GUN:
		case DRAGON:
		case CROSS:	
		case CLYNDER:	
		case CLYNDER2:	
			positionArray.clear();
			positionArray.add(shape.getPosition().get(0));
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
		return virtualDencity;
	}

	@Override
	public void setDensity(float density) {
		// TODO Auto-generated method stub
		virtualDencity = density;
	}

	@Override
	public float getFriction() {
		// TODO Auto-generated method stub
				switch (type) {
				case CANOON:
				case FLAMCANNON:
				case RIGHTCANOON:
				case SCANNON:	
				case GUN:
				case DRAGON:
				case CROSS:	
				case CLYNDER:	
				case CLYNDER2:	
					return shape.getFriction();
				default:
					return 0;
				}
	}

	@Override
	public void setFriction(float friction) {
		// TODO Auto-generated method stub
				switch (type) {
				case CANOON:
				case FLAMCANNON:
				case RIGHTCANOON:	
				case SCANNON:	
				case GUN:	
				case DRAGON:	
				case CROSS:	
				case CLYNDER:
				case CLYNDER2:	
					shape.setFriction(friction);
					break;
				default:
					break;
				}
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
				switch (type) {
				case CANOON:
				case FLAMCANNON:
				case RIGHTCANOON:	
					bodyArray.clear();
					bodyArray.add(shape.getBody().get(0));
					bodyArray.add(stand.getBody().get(0));
					break;
				case SCANNON:
				case GUN:
				case DRAGON:	
				case CROSS:	
				case CLYNDER:	
				case CLYNDER2:	
					bodyArray.clear();
					bodyArray.add(shape.getBody().get(0));
					break;
				}
				return bodyArray;
	}

	@Override
	public void setWidth(float width) {
		// TODO Auto-generated method stub
				switch (type) {
				case CANOON:
				case FLAMCANNON:
				case RIGHTCANOON:	
				case SCANNON:	
				case GUN:	
				case DRAGON:	
				case CROSS:	
				case CLYNDER:
				case CLYNDER2:	
					shape.setWidth(width);
					break;
				default:
					break;
				}
		
	}

	@Override
	public float getWidth() {
		// TODO Auto-generated method stub
		return shape.getWidth();
	}

	@Override
	public void setHeight(float height) {
		// TODO Auto-generated method stub
		shape.setHeight(height);
	}

	@Override
	public float getHeight() {
		// TODO Auto-generated method stub
		return shape.getHeight();
	}

	@Override
	public void setRadius(float radius) {
		// TODO Auto-generated method stub
		ball.setRadius(radius);
	}

	@Override
	public float getRadius() {
		// TODO Auto-generated method stub
		return ball.getRadius();
	}

	@Override
	public ArrayList<BasicObject> getBasicObject() {
		// TODO Auto-generated method stub
		switch (type) {
		case SCANNON:
		case GUN:	
		case DRAGON:
		case CROSS:	
		case CLYNDER:	
		case CLYNDER2:	
			basicObjectArray.clear();
			basicObjectArray.add(shape);
			break;
		case CANOON:
		case FLAMCANNON:
		case RIGHTCANOON:
			basicObjectArray.clear();
			basicObjectArray.add(shape);
			basicObjectArray.add(stand);
			break;
		}
		return basicObjectArray;
	}
	public void setProperties(float density,float friction,float restitution,int ballNumber,boolean takeSound){
		virtualDencity = density;
		shape.setFriction(friction);
		shape.setRestitution(restitution);
		this.ballNumber = ballNumber;
		//raneen
		setTakeSound(takeSound);
	}
	public World getWorld() {
		return world;
	}
	public void setWorld(World world) {
		this.world = world;
	}
	public AppVar.cannonType getType() {
		return type;
	}
	public void setType(AppVar.cannonType type) {
		this.type = type;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public void updateFlame(float x,float y,ArrayList<Player> cannonObjectplayer,AppVar.cannonType type){
		int number = 0;
		world.getBodies(tmpBodies);
		for(int i=0;i<flameObject.size();i++){
            if (flameObject.get(i).getBasicObject().get(0).getBody().get(0).getUserData()!=null) {
                number++;
            }
        }
        if (number<ballNumber) {
        	switch (type) {
			case FLAMCANNON:
	        	addFlame(x, y, cannonObjectplayer);
				break;
			case DRAGON:
				addFlameDragon(x, y, cannonObjectplayer);
				break;
			default:
				break;
			}
	   }
	}
	public void updateBall(float x,float y,AppVar.cannonType type){
		int number = 0;
		world.getBodies(tmpBodies);
			for(int i=0;i<ballObject.size();i++){
	            if (ballObject.get(i).getBody().get(0).getUserData()!=null) {
	                number++;
	            }
			}
        if (number<ballNumber) {
        	 if (number<ballNumber) {
             	switch (type) {
             	case CANOON:
             		addBall(x,y);
             		break;
     			case CROSS:	
     				addShaft(x, y);
     				break;
     			case RIGHTCANOON:
     				addBallRight(x, y);
     				break;
     			case SCANNON:
     				addBallSmall(x, y);
     				break;
     			case GUN:
     				addGun(x, y);
     				break;
     			case CLYNDER:
     				addCyl(x, y);
     				break;
     			case CLYNDER2:
     				addCylRight(x, y);
     				break;
     			default:
     				break;
     			}
     	   }
	   }
	}
	public void eraseBall(){
		for (Body body : tmpBodies) {
			for(int i=0;i<ballObject.size();i++){
	    		if(body == ballObject.get(i).getBody().get(0)){
	    			 if (body.getPosition().y<-12) {
	    				ballObject.remove(ballObject.get(i));
	 	                world.destroyBody(body);
	    			 }
	    		}
	    	}
		}
	}
	public void eraseFlame(ArrayList<Player> cannonObjectplayer){
		for (Body body : tmpBodies) {
			for(int i=0;i<flameObject.size();i++){
	    		if(body == flameObject.get(i).getBasicObject().get(0).getBody().get(0)){
	    			 if (body.getPosition().y<-12) {
	    				 flameObject.remove(flameObject.get(i));
	    				 cannonObjectplayer.remove(cannonObjectplayer.get(i));
	 	                world.destroyBody(body);
	    			 }
	    		}
	    	}
		}
	}
	private void addBall(float xx,float yy){
		ball = new BasicObject(world, BodyType.DynamicBody,basicBodyType.BALL, "image/cannon/ball.png",
				xx-2.5f,yy+4);
		ball.setRadius(.5f);
		ball.setProperties(5f,.32f,.84f,0,0);
		ball.getBody().get(0).setGravityScale(1);
		float x =ball.getBody().get(0).getPosition().x ;
		float y = ball.getBody().get(0).getPosition().y; 
		if(x <0 && y>=0 ){
		ball.getBody().get(0).setLinearVelocity(new Vector2(xx-12,
				yy+18));
		}
		else if(x<0 && y<0){
			ball.getBody().get(0).setLinearVelocity(new Vector2(xx-25,
					yy+35));
		}
		if(x >=0 && y>=0 ){
			ball.getBody().get(0).setLinearVelocity(new Vector2(xx-35,
					yy+18));
			}
		else if(x >=0 &&y<0){
			ball.getBody().get(0).setLinearVelocity(new Vector2(xx-35,
					yy+35));
		}
		ballObject.add(ball);
	}
	private void addBallRight(float xx,float yy){
		ball = new BasicObject(world, BodyType.DynamicBody,basicBodyType.BALL, "image/cannon/ball.png",
				xx+2.5f,yy+4);
		ball.setRadius(.5f);
		ball.setProperties(5f,.32f,.84f,0,0);
		ball.getBody().get(0).setGravityScale(1);
		float x =ball.getBody().get(0).getPosition().x ;
		float y = ball.getBody().get(0).getPosition().y; 
		if(x <0 && y>=0 ){
		ball.getBody().get(0).setLinearVelocity(new Vector2(xx+12,
				yy+18));
		}
		else if(x<0 && y<0){
			ball.getBody().get(0).setLinearVelocity(new Vector2(xx+25,
					yy+35));
		}
		if(x >=0 && y>=0 ){
			ball.getBody().get(0).setLinearVelocity(new Vector2(xx+35,
					yy+18));
			}
		else if(x >=0 &&y<0){
			ball.getBody().get(0).setLinearVelocity(new Vector2(xx+35,
					yy+35));
		}
		ballObject.add(ball);
	}
	private void addCylRight(float xx,float yy){
		ball = new BasicObject(world, BodyType.DynamicBody,basicBodyType.BALL, "image/cannon/ball2.png",
				xx+3f,yy+2.2f);
		ball.setRadius(.5f);
		ball.setProperties(5f,.32f,.84f,0,0);
		ball.getBody().get(0).setGravityScale(1);
		float x =ball.getBody().get(0).getPosition().x ;
		float y = ball.getBody().get(0).getPosition().y; 
		if(x <0 && y>=0 ){
		ball.getBody().get(0).setLinearVelocity(new Vector2(xx+12,
				yy+18));
		}
		else if(x<0 && y<0){
			ball.getBody().get(0).setLinearVelocity(new Vector2(xx+25,
					yy+35));
		}
		if(x >=0 && y>=0 ){
			ball.getBody().get(0).setLinearVelocity(new Vector2(xx+35,
					yy+18));
			}
		else if(x >=0 &&y<0){
			ball.getBody().get(0).setLinearVelocity(new Vector2(xx+35,
					yy+35));
		}
		ballObject.add(ball);
	}
	private void addCyl(float xx,float yy){
		ball = new BasicObject(world, BodyType.DynamicBody,basicBodyType.BALL, "image/cannon/ball1.png",
				xx-3f,yy+2.5f);
		ball.setRadius(.5f);
		ball.setProperties(5f,.32f,.84f,0,0);
		ball.getBody().get(0).setGravityScale(1);
		float x =ball.getBody().get(0).getPosition().x ;
		float y = ball.getBody().get(0).getPosition().y; 
		if(x <0 && y>=0 ){
		ball.getBody().get(0).setLinearVelocity(new Vector2(xx-12,
				yy+18));
		}
		else if(x<0 && y<0){
			ball.getBody().get(0).setLinearVelocity(new Vector2(xx-25,
					yy+35));
		}
		if(x >=0 && y>=0 ){
			ball.getBody().get(0).setLinearVelocity(new Vector2(xx-35,
					yy+18));
			}
		else if(x >=0 &&y<0){
			ball.getBody().get(0).setLinearVelocity(new Vector2(xx-35,
					yy+35));
		}
		ballObject.add(ball);
	}
	private void addBallSmall(float xx,float yy){
		ball = new BasicObject(world, BodyType.DynamicBody,basicBodyType.BALL, "image/cannon/BOMB.png",
				xx+4f,yy+2f);
		ball.setRadius(.5f);
		ball.setProperties(5f,.32f,.84f,0,0);
		ball.getBody().get(0).setGravityScale(1);
		float x =ball.getBody().get(0).getPosition().x ;
		float y = ball.getBody().get(0).getPosition().y; 
		if(x <0 && y>=0 ){
		ball.getBody().get(0).setLinearVelocity(new Vector2(xx+12,
				yy+18));
		}
		else if(x<0 && y<0){
			ball.getBody().get(0).setLinearVelocity(new Vector2(xx+25,
					yy+35));
		}
		if(x >=0 && y>=0 ){
			ball.getBody().get(0).setLinearVelocity(new Vector2(xx+35,
					yy+18));
			}
		else if(x >=0 &&y<0){
			ball.getBody().get(0).setLinearVelocity(new Vector2(xx+35,
					yy+35));
		}
		ballObject.add(ball);
	}
	private void addGun(float xx,float yy){
		ball = new BasicObject(world, BodyType.DynamicBody,basicBodyType.BULLET, "image/cannon/bullet.png",
				xx+4f,yy+1f);
		ball.setDensity(5);
		ball.getBody().get(0).setGravityScale(1);
		float x =ball.getBody().get(0).getPosition().x ;
		float y = ball.getBody().get(0).getPosition().y; 
		if(x <0 && y>=0 ){
		ball.getBody().get(0).setLinearVelocity(new Vector2(xx+35,
				0));
		}
		else if(x<0 && y<0){
			ball.getBody().get(0).setLinearVelocity(new Vector2(xx+25,
					0));
		}
		if(x >=0 && y>=0 ){
			ball.getBody().get(0).setLinearVelocity(new Vector2(xx+35,
					0));
			}
		else if(x >=0 &&y<0){
			ball.getBody().get(0).setLinearVelocity(new Vector2(xx+35,
					0));
		}
		ballObject.add(ball);
	}
	private void addShaft(float xx,float yy){
		ball = new BasicObject(world, BodyType.DynamicBody,basicBodyType.SHAFT, "image/cannon/arrow.png",
				xx+2.3f,yy+1.3f);
	//	ball.setProperties(5f,.32f,.84f,0,0);
		ball.getBody().get(0).setTransform(ball.getBody().get(0).getPosition(), 45);	
		ball.getBody().get(0).setGravityScale(1);
		float x =ball.getBody().get(0).getPosition().x ;
		float y = ball.getBody().get(0).getPosition().y; 
		if(x <0 && y>=0 ){
		ball.getBody().get(0).setLinearVelocity(new Vector2(xx+35,
				yy+18));
		}
		else if(x<0 && y<0){
			ball.getBody().get(0).setLinearVelocity(new Vector2(xx+25,
					yy+35));
		}
		if(x >=0 && y>=0 ){
			ball.getBody().get(0).setLinearVelocity(new Vector2(xx+35,
					yy+18));
			}
		else if(x >=0 &&y<0){
			ball.getBody().get(0).setLinearVelocity(new Vector2(xx+35,
					yy+35));
		}
		ballObject.add(ball);
	}
	
	public void addFlame(float xx,float yy,ArrayList<Player> cannonObjectplayer){
		flame = new AnimationObject(world, AppVar.animationObject.BOOM3, 
				xx-2.5f,yy+4);
		flame.getBody().get(0).setGravityScale(1);
		float x =flame.getBody().get(0).getPosition().x ;
		float y = flame.getBody().get(0).getPosition().y; 
		if(x <0 && y>=0  ){
			flame.getBody().get(0).setLinearVelocity(new Vector2(xx-12,
					yy+18));
			}
		else if(x<0 && y<0){
			flame.getBody().get(0).setLinearVelocity(new Vector2(xx-25,
				yy+35));}
		else if(x >=0 && y>=0){
			flame.getBody().get(0).setLinearVelocity(new Vector2(xx-35,
					yy+18));
			}
		else if(x >=0 &&y<0){
			flame.getBody().get(0).setLinearVelocity(new Vector2(xx-35,
					yy+35));
		}
		cannonObjectplayer.add(flame.getPlayer());
		flameObject.add(flame);
	}
	public void addFlameDragon(float xx,float yy,ArrayList<Player> cannonObjectplayer){
		flame = new AnimationObject(world, AppVar.animationObject.BOOM1, 
				xx+1.5f,yy+1);
		flame.getBody().get(0).setGravityScale(1);
		float x =flame.getBody().get(0).getPosition().x ;
		float y = flame.getBody().get(0).getPosition().y; 
		if(x <0 && y>=0  ){
			flame.getBody().get(0).setLinearVelocity(new Vector2(xx-20,
					yy-18));
			}
		else if(x<0 && y<0){
			flame.getBody().get(0).setLinearVelocity(new Vector2(xx-35,
				yy-35));}
		else if(x >=0 && y>=0){
			flame.getBody().get(0).setLinearVelocity(new Vector2(xx-45,
					yy-18));
			}
		else if(x >=0 &&y<0){
			flame.getBody().get(0).setLinearVelocity(new Vector2(xx-45,
					yy-35));
		}
		cannonObjectplayer.add(flame.getPlayer());
		flameObject.add(flame);
	}
	public int getBallNumber() {
		return ballNumber;
	}
	public void setBallNumber(int ballNumber) {
		this.ballNumber = ballNumber;
	}
	//raneen
	public boolean isTakeSound() {
		return takeSound;
	}
	public void setTakeSound(boolean takeSound) {
		this.takeSound = takeSound;
	}
}

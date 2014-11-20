package sw.newtonroad.body;

import java.util.ArrayList;

import sw.newtonroad.animation.Player;
import sw.newtonroad.handlers.AppVar;
import sw.newtonroad.handlers.AppVar.basicBodyType;
import sw.newtonroad.joint.Distance;
import sw.newtonroad.joint.GameJoint;
import sw.newtonroad.joint.Revolute;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class AnimationObject  extends GameObject{
	private BasicObject animationObject;
	private World world;
	private AppVar.animationObject type;
	private Player player;
	private float virtualDencity;
	private float movementSpeed;
	private String img;
	private boolean isStatic = false;
	private int isCollision=1;
	public AnimationObject(World world,AppVar.animationObject type,float x,float y){
			this.world=world;
			this.type=type;
			virtualDencity=2.7f;
			positionArray=new ArrayList<Vector2>();
			bodyArray=new ArrayList<Body>();
			gameJoint=new ArrayList<GameJoint>();
			basicObjectArray=new ArrayList<BasicObject>();
			jointInfo=new String[4];
			secondJoint=false;
			switch (type) {
			case BAT:
				animationObject = new BasicObject(world, BodyType.DynamicBody,basicBodyType.BOX,"test.png",x, y);
				animationObject.setWidth(1.1f);
				animationObject.setHeight(.6f);
				player=new Player(animationObject.getBody().get(0),"animation/bat.png",7,131, 110,2.5f,2.8f,1.2f,1.2f);
				setImg("animation/bat.png");
				break;
			case BIRD1:
				animationObject = new BasicObject(world, BodyType.DynamicBody,basicBodyType.BALL, "test.png",x, y);
				animationObject.setRadius(2/2.5f );
				player=new Player(animationObject.getBody().get(0),"animation/bird1.png",5,106, 75,2.5f,2.8f,1.4f,.9f);
				setImg("animation/bird1.png");
				break;
			case BIRD2:
				animationObject = new BasicObject(world, BodyType.DynamicBody,basicBodyType.BOX,"test.png",x, y);
				animationObject.setWidth(1f);
				animationObject.setHeight(.3f);
				player=new Player(animationObject.getBody().get(0),"animation/bird2.png",10,239, 255,3f,4f,1.1f,2f);
				setImg("animation/bird2.png");
				break;
			case BIRD3:
				animationObject = new BasicObject(world, BodyType.DynamicBody,basicBodyType.BOX,"test.png",x, y);
				animationObject.setWidth(1.6f);
				animationObject.setHeight(.4f);
				animationObject.getBody().get(0).setTransform(animationObject.getBody().get(0).getPosition(), 12);	
				player=new Player(animationObject.getBody().get(0),"animation/bird3.png",10,109, 81,3.7f,4.5f,2f,2f);
				setImg("animation/bird3.png");
				break;
			case BIRD4:
				animationObject = new BasicObject(world, BodyType.DynamicBody,basicBodyType.BALL, "test.png",x, y);
				animationObject.setRadius(2/2.5f );
				player=new Player(animationObject.getBody().get(0),"animation/bird4.png",2,287,312,2.5f,2.8f,1.4f,.9f);
				animationObject.setRadius(.85f);
				setImg("animation/bird4.png");
				break;
			case BLOOD:
				animationObject = new BasicObject(world, BodyType.DynamicBody,basicBodyType.BALL, "test.png",x, y);
				animationObject.setRadius(2/2.5f );
				player=new Player(animationObject.getBody().get(0),"animation/blood.png",5,512, 512,4.3f,4.3f,2.1f,2.3f);
				animationObject.setRadius(.8f);
				setImg("animation/blood.png");
				break;
			case BLUE1:
				animationObject = new BasicObject(world, BodyType.DynamicBody,basicBodyType.BALL, "test.png",x, y);
				animationObject.setRadius(2/2.5f );
				player=new Player(animationObject.getBody().get(0),"animation/blue1.png",5,200, 109,3f,2.5f,1.5f,1.3f);
				animationObject.setRadius(.75f);
				setImg("animation/blue1.png");
				break;
			case BLUE2:
				animationObject = new BasicObject(world, BodyType.DynamicBody,basicBodyType.BALL, "test.png",x, y);
				animationObject.setRadius(2/2.5f );
				player=new Player(animationObject.getBody().get(0),"animation/blue2.png",5,200, 188,4f,3f,1.5f,1.5f);
				setImg("animation/blue2.png");
				break;
			case BOOM1:
				animationObject = new BasicObject(world, BodyType.DynamicBody,basicBodyType.BALL, "test.png",x, y);
				animationObject.setRadius(2/2.5f );
				player=new Player(animationObject.getBody().get(0),"animation/boom1.png",3,123, 109,2.5f,2.8f,1.2f,1.2f);
				setImg("animation/boom1.png");
				break;
			case BOOM2:
				animationObject = new BasicObject(world, BodyType.DynamicBody,basicBodyType.BALL, "test.png",x, y);
				animationObject.setRadius(2/2.5f );
				player=new Player(animationObject.getBody().get(0),"animation/boom2.png",3,128, 145,2.5f,2.8f,1.2f,1.5f);
				setImg("animation/boom2.png");
				break;
			case BOOM3:
				animationObject = new BasicObject(world, BodyType.DynamicBody,basicBodyType.BALL, "test.png",x, y);
				animationObject.setRadius(2/2.5f );
				player=new Player(animationObject.getBody().get(0),"animation/boom3.png",3,192, 177,4.8f,4.5f,1.8f,1.7f);
				//animationObject.setRadius(1.1f);
				animationObject.setRadius(1.6f);
				setImg("animation/boom3.png");
				break;
			case BOOM4:
				animationObject = new BasicObject(world, BodyType.DynamicBody,basicBodyType.BALL, "test.png",x, y);
				animationObject.setRadius(2/2.5f );
				player=new Player(animationObject.getBody().get(0),"animation/boom4.png",6,317, 212,4.5f,4.1f,1.87f,1f);
				animationObject.setRadius(1f);
				setImg("animation/boom4.png");
				break;
			case ROSE1:
				animationObject = new BasicObject(world, BodyType.DynamicBody,basicBodyType.BALL, "test.png",x, y);
				animationObject.setRadius(2/2.5f );
				player=new Player(animationObject.getBody().get(0),"animation/boom.png",7,130, 142,5f,4.7f,2.2f,2.2f);
				animationObject.setRadius(1.1f);
				setImg("animation/boom.png");
				break;
			case ROCK1:
				animationObject = new BasicObject(world, BodyType.DynamicBody,basicBodyType.BALL, "test.png",x, y);
				animationObject.setRadius(2/2.5f );
				player=new Player(animationObject.getBody().get(0),"animation/rock1.png",12,199, 156,2.5f,2.8f,1f,1.2f);
				setImg("animation/rock1.png");
				break;
			case ROCK2:
				animationObject = new BasicObject(world, BodyType.DynamicBody,basicBodyType.BALL, "test.png",x, y);
				animationObject.setRadius(2/2.5f );
				player=new Player(animationObject.getBody().get(0),"animation/rock2.png",5,199, 156,5f,4.7f,2.2f,2.2f);
				animationObject.setRadius(1.1f);
				setImg("animation/rock2.png");
				break;
			case SAD:
				animationObject = new BasicObject(world, BodyType.DynamicBody,basicBodyType.BOX,"test.png",x, y);
				animationObject.setWidth(1f);
				animationObject.setHeight(1.9f);
				player=new Player(animationObject.getBody().get(0),"animation/sad.png",12,470, 470,3.7f,4.5f,1.7f,2.2f);
				setImg("animation/sad.png");
				break;
			case STAR1:
				animationObject = new BasicObject(world, BodyType.DynamicBody,basicBodyType.BALL, "test.png",x, y);
				animationObject.setRadius(.75f);
				player=new Player(animationObject.getBody().get(0),"animation/star1.png",1,187, 229,2.5f,2.8f,1.3f,1.7f);
				setImg("animation/star1.png");
				break;
			case STAR2:
				animationObject = new BasicObject(world, BodyType.DynamicBody,basicBodyType.BALL, "test.png",x, y);
				animationObject.setRadius(1f);
				player=new Player(animationObject.getBody().get(0),"animation/star2.png",1,187,182,7f,6.8f,3.3f,3.7f);
				setImg("animation/star2.png");
				break;
			case TREE2:
				animationObject = new BasicObject(world, BodyType.DynamicBody,basicBodyType.BOX,"test.png",x, y);
				animationObject.setWidth(1f);
				animationObject.setHeight(1.9f);
				player=new Player(animationObject.getBody().get(0),"animation/tree2.png",7,80, 86,3.7f,4.5f,1.7f,2.3f);
				setImg("animation/tree2.png");
				break;
			case WINDMILL:
				animationObject = new BasicObject(world, BodyType.DynamicBody,basicBodyType.BOX,"test.png",x, y);
				animationObject.setWidth(1f);
				animationObject.setHeight(1.9f);
				//player=new Player(animationObject.getBody().get(0),"animation/windmill.png",2,188, 316,3.7f,4.5f,1.7f,2.3f);
				player=new Player(animationObject.getBody().get(0),"animation/windmill.png",5,190, 328,4.3f,6f,2.5f,3f);
				setImg("animation/windmill.png");
				break;
			case BURNTNEWTON:
				animationObject = new BasicObject(world, BodyType.DynamicBody,basicBodyType.BOX,"test.png",x, y);
				animationObject.setWidth(1f);
				animationObject.setHeight(1.9f);
				//player=new Player(animationObject.getBody().get(0),"animation/windmill.png",2,188, 316,3.7f,4.5f,1.7f,2.3f);
				//player=new Player(animationObject.getBody().get(0),"animation/burntnewton.png",1,133, 99,4.3f,6f,2.5f,3f);
				//setImg("animation/burntnewton.png");
				player=new Player(animationObject.getBody().get(0),"animation/burntnewton2.png",1,146, 99,4.3f,5f,1f,2.3f);
				setImg("animation/burntnewton2.png");
				break;
			case SKLTON:
				animationObject = new BasicObject(world, BodyType.DynamicBody,basicBodyType.BOX,"test.png",x, y);
				animationObject.setWidth(1f);
				animationObject.setHeight(1.9f);
				//player=new Player(animationObject.getBody().get(0),"animation/windmill.png",2,188, 316,3.7f,4.5f,1.7f,2.3f);
				//player=new Player(animationObject.getBody().get(0),"animation/burntnewton.png",1,133, 99,4.3f,6f,2.5f,3f);
				//setImg("animation/burntnewton.png");
				player=new Player(animationObject.getBody().get(0),"animation/sklton.png",1,151, 88,4.3f,5f,1f,2.3f);
				setImg("animation/sklton.png");
				break;
			case FLAME1:
				animationObject = new BasicObject(world, BodyType.DynamicBody,basicBodyType.BALL, "test.png",x, y);
				animationObject.setRadius(2/2.5f );
				player=new Player(animationObject.getBody().get(0),"animation/flame1.png",7,512, 512,3.7f,3.3f,2.1f,1.6f);
				animationObject.setRadius(.7f);
				setImg("animation/flame1.png");
				break;
			case FLAME2:
				animationObject = new BasicObject(world, BodyType.DynamicBody,basicBodyType.BALL, "test.png",x, y);
				animationObject.setRadius(2/2.5f );
				player=new Player(animationObject.getBody().get(0),"animation/flame2.png",7,512, 512,3.7f,3.3f,1.7f,1.6f);
				animationObject.setRadius(.7f);
				setImg("animation/flame2.png");
				break;
			case FLAYFROG:
				animationObject = new BasicObject(world, BodyType.DynamicBody,basicBodyType.BALL, "test.png",x, y);
				animationObject.setRadius(2/2.5f );
				player=new Player(animationObject.getBody().get(0),"animation/flayfrog.png",7,108, 83,5.2f,3.6f,1.1f,2f);
				animationObject.setRadius(1f);
				setImg("animation/flayfrog.png");
				break;
			case LIGHT1:
				animationObject = new BasicObject(world, BodyType.DynamicBody,basicBodyType.BALL, "test.png",x, y);
				animationObject.setRadius(2/2.5f );
				player=new Player(animationObject.getBody().get(0),"animation/light1.png",6,188, 143,6f,5.5f,2.5f,2.2f);
				animationObject.setRadius(1f);
				setImg("animation/light1.png");
				break;
			case LIGHT2:
				animationObject = new BasicObject(world, BodyType.DynamicBody,basicBodyType.BALL, "test.png",x, y);
				animationObject.setRadius(2/2.5f );
				player=new Player(animationObject.getBody().get(0),"animation/light2.png",5,190, 143,6f,6f,2.3f,2.2f);
				animationObject.setRadius(1f);
				setImg("animation/light2.png");
				break;
			case PINKLIGHT:
				animationObject = new BasicObject(world, BodyType.DynamicBody,basicBodyType.BALL, "test.png",x, y);
				animationObject.setRadius(2/2.5f );
				player=new Player(animationObject.getBody().get(0),"animation/pinklight.png",5,57, 55,3.5f,3.5f,2f,2f);
				animationObject.setRadius(1f);
				setImg("animation/pinklight.png");
				
				break;
			case  FIRE:
				animationObject = new BasicObject(world, BodyType.DynamicBody,basicBodyType.BOX,"test.png",x, y);
				animationObject.setWidth(1f);
				animationObject.setHeight(1.9f);
				player=new Player(animationObject.getBody().get(0),"animation/fire.png",5,100, 136,3.7f,4.5f,1.7f,2.3f);
				setImg("animation/fire.png");
				break;
			}
			animationObject.setDensity(0);
		}
		public Player getPlayer(){
			return player;
		}
		@Override
		public void setDynamic(Vector2 position) {
			if(isStatic==true){
				animationObject.getBody().get(0).setType(BodyType.StaticBody);
			}
			else{
				animationObject.getBody().get(0).setType(BodyType.DynamicBody);
				}
			animationObject.setDynamic(position);
		}

		@Override
		public void setStatic(Vector2 position) {
			animationObject.getBody().get(0).setType(BodyType.DynamicBody);
			animationObject.setStatic(position);
			animationObject.setDensity(0);
		}

		@Override
		public ArrayList<Vector2> getPosition() {
			positionArray.clear();
			positionArray.add(animationObject.getPosition().get(0));
			return positionArray;	
		}

		@Override
		public void setPosition(Vector2 position) {
			animationObject.setPosition(position);
		}

		@Override
		public float getDensity() {
			return virtualDencity;
		}

		@Override
		public void setDensity(float density) {
			this.virtualDencity=density;
		}

		@Override
		public float getFriction() {
			return animationObject.getFriction();
		}

		@Override
		public void setFriction(float friction) {
			animationObject.setFriction(friction);
		}

		@Override
		public float getRestitution() {
			return animationObject.getRestitution();
		}

		@Override
		public void setRestitution(float restitution) {
			animationObject.setRestitution(restitution);
		}

		@Override
		public ArrayList<Body> getBody() {
			bodyArray.clear();
			 bodyArray.add(animationObject.getBody().get(0));
			 return bodyArray;
		}

		@Override
		public void setWidth(float width) {
			switch (type) {
			case BAT:
			case BIRD2:
			case BIRD3:
			case SAD:
			case TREE2:
			case WINDMILL:
			case BURNTNEWTON:
			case SKLTON:
			
				animationObject.setWidth(width);
				player.setPlayerHeight(width*2);
				break;
			
			}
		}
		//need change
		@Override
		public float getWidth() {
			switch (type) {
			case BAT:
			case BIRD2:
			case BIRD3:
			case SAD:
			case TREE2:
			case WINDMILL:
			case BURNTNEWTON:
			case SKLTON:
			return animationObject.getWidth();
			default:
				return animationObject.getRadius();
			}
		}

		@Override
		public void setHeight(float height) {
			switch (type) {
			case BAT:
			case BIRD2:
			case BIRD3:
			case SAD:
			case TREE2:
			case WINDMILL:
			case BURNTNEWTON:
			case SKLTON:
				animationObject.setHeight(height);
				player.setPlayerWidth(height*2);
				break;
			default:
				break;
			}
			
		}
		//need change
		@Override
		public float getHeight() {
			switch (type) {
			case BAT:
			case BIRD2:
			case BIRD3:
			case SAD:
			case TREE2:
			case WINDMILL:
			case BURNTNEWTON:
			case SKLTON:
				return animationObject.getHeight();
			default:
				return 0;
			}
		}

		@Override
		public void setRadius(float radius) {
			switch (type) {
			case BAT:
			case BIRD2:
			case BIRD3:
			case SAD:
			case TREE2:
			case WINDMILL:
			case BURNTNEWTON:
			case SKLTON:
				break;
			default:
				animationObject.setRadius(radius);
				player.setPlayerWidth(radius*2.5f);
				player.setPlayerHeight(radius*1.5f);
				break;
			}
		}
		@Override
		public float getRadius() {
			switch (type) {
			case BAT:
			case BIRD2:
			case BIRD3:
			case SAD:
			case TREE2:
			case WINDMILL:
			case BURNTNEWTON:
			case SKLTON:
				return 0;
			default:
				return animationObject.getRadius();
			}
		}
		//need change
		public BasicObject getAnimationObject() {
			return animationObject;
		}
		public void setAnimationObject(BasicObject animationObject) {
			this.animationObject = animationObject;
		}

		

		@Override
		public ArrayList<BasicObject> getBasicObject() {
			basicObjectArray.clear();
			basicObjectArray.add(animationObject);
			return basicObjectArray;
		}
		public AppVar.animationObject getType() {
			return type;
		}
		public void setType(AppVar.animationObject type) {
			this.type = type;
		}
		public float getAnimationSpeed() {
			return player.getPlayerSpeed();
		}
		public void setAnimationSpeed(float animationSpeed) {
			player.setPlayerSpeed(animationSpeed);
		}
		public void setPlayer(Player player) {
			this.player = player;
		}
		public float getMovementSpeed() {
			return movementSpeed;
		}
		public void setMovementSpeed(float movementSpeed) {
			this.movementSpeed = movementSpeed;
		}
		public void setProperties(float density,float friction,float restitution,float width,float height,float animationSpeed,int isCollision,boolean isStatic){
			switch (type) {
			case BAT:
			case BIRD2:	
			case BIRD3:
			case SAD:
			case TREE2:	
			case WINDMILL:
			case BURNTNEWTON:
			case SKLTON:
				this.virtualDencity=density;
				animationObject.setFriction(friction);
				animationObject.setRestitution(restitution);
				animationObject.setWidth(width);
				animationObject.setHeight(height);
				setIsCollision(isCollision);
				animationObject.setIsCollision((short)isCollision);
				animationObject.setStatic(animationObject.getPosition().get(0));
				player.setPlayerSpeed(animationSpeed);
				break;
			default:
				this.virtualDencity=density;
				animationObject.setFriction(friction);
				animationObject.setRestitution(restitution);
				animationObject.setRadius(width);
				setIsCollision(isCollision);
				animationObject.setStatic(animationObject.getPosition().get(0));
				animationObject.setIsCollision((short)isCollision);
				player.setPlayerSpeed(animationSpeed);
				break;
			}
			setStatic(isStatic);
			
			
		}
		public String getImg() {
			return img;
		}
		public void setImg(String img) {
			this.img = img;
		}
		public int getIsCollision() {
			return isCollision;
		}
		public void setIsCollision(int isCollision) {
			this.isCollision = isCollision;
		}
		public boolean isStatic() {
			return isStatic;
		}
		public void setStatic(boolean isStatic) {
			this.isStatic = isStatic;
		}
}

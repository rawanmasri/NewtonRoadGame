package sw.newtonroad.game;

import java.security.acl.LastOwnerException;
import java.util.ArrayList;
import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.QueryCallback;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

import sw.newtonroad.animation.Player;
import sw.newtonroad.body.AnimationObject;
import sw.newtonroad.body.BasicObject;
import sw.newtonroad.body.Cannon;
import sw.newtonroad.body.Chain;
import sw.newtonroad.body.DangerousObject;
import sw.newtonroad.body.Disappear;
import sw.newtonroad.body.Elevator;
import sw.newtonroad.body.EndObject;
import sw.newtonroad.body.FixedObject;
import sw.newtonroad.body.GameObject;
import sw.newtonroad.body.ImponderableShape;
import sw.newtonroad.body.StartObject;
import sw.newtonroad.body.Triangle;
import sw.newtonroad.body.Vehicle;
import sw.newtonroad.body.WindMill;
import sw.newtonroad.handlers.AppVar;
import sw.newtonroad.handlers.ButtonTable;
import sw.newtonroad.handlers.InputController;
import sw.newtonroad.handlers.JsonData;
import sw.newtonroad.handlers.AppVar.TriangleType;
import sw.newtonroad.handlers.AppVar.animationObject;
import sw.newtonroad.handlers.AppVar.fixedType;
import sw.newtonroad.handlers.AppVar.imponderableType;
import sw.newtonroad.handlers.AppVar.vehicleType;
import sw.newtonroad.handlers.AppVar.windMillType;
import sw.newtonroad.joint.Distance;
import sw.newtonroad.joint.GameJoint;
import sw.newtonroad.joint.Revolute;
import sw.newtonroad.manegers.GameState;

public class Game extends GameState{
	    //variables for ui 
		private static Stage stage;
		private Skin skin ;
		 private FileHandle file;
		//private ScrollPane scrollPane;
		private Table basicTable;
		private Table leftTable;
		private Table creationTable;
		private Table directionTable;
		private Label life;
		//App Camera
		private OrthographicCamera camera;
		//box2d variables
		private Box2DDebugRenderer debugRenderer;
		private World world;  
		private GameObject tempObject;
		private GameObject tempDelete;
		private BasicObject topGround;
		private BasicObject downGround;
		private BasicObject leftGround;
		private BasicObject rightGround;
		// variables to draw image to Body
		private	SpriteBatch batch;
		private Array<Body> tmpBodies ;
		private Array<Integer> index ;
		//handling ui and input processor methods
		private InputMultiplexer multiplexer ;
		//to detect the object while touch down 
		private MouseJoint mouseJoint;
		private MouseJointDef mouseJointDef;
		private Vector objectPosition; 
		private Vector3 touchDownPosition;
		private Vector2 tmpTouchDownPosition;
		//Objects
		private BasicObject basicObject;
		private Cannon cannon;
		private Chain chain;
		private Elevator elevator;
		private FixedObject fixedshape;
		private ImponderableShape imponderableShape; 
		private Triangle triangle;
		private Vehicle car;
		private WindMill windMill;
		//test objects
		private BasicObject basicObjectTest;
		private Cannon cannonTest;
		private Chain chainTest;
		private Disappear disappearTest;
		private Elevator elevoterTest;
		private FixedObject fixedshapeTest;
		private ImponderableShape imponderableShapeTest; 
		private Triangle triangleTest;
		private Vehicle carTest;
		private WindMill windMillTest;
		private StartObject startObjectTest;
		private EndObject endObjectTest;
		private AnimationObject animObjectTest;
		private DangerousObject dangerousObjectTest;
		private ArrayList<GameObject> gameObjectArray;
		private ArrayList<GameObject> gameObjectArrayTest;
		private ArrayList<Player> cannonObjectplayer;
		private Player startObjectplayer;
		private Player endObjectplayer;
		private ArrayList<Player> animationObjectplayer;
		private ArrayList<Player> dangerousObjectPlayer;
		float ANIMATIONSTEP=1/60f;
		private Revolute revolute;
		private Distance distance;
		private static int value;
		private static int lastValue;
		private LevelDialog levelDialog;
		private static GameStateManager gsm;
		private String gameimg;
		float posx;
		float posy;
		private boolean flag_retry=false;
		private int lifeCount=0;
		private GameOverDialog GameOverDialog;
		private static NextDialog NextDialog;
		private FileHandle levelFile;
		private int lastLeve;
		private TextField jointMaxMotorTorqueTxt;
		private TextField jointMotorSpeed;
		private TextButton jointButton;
		private TextField jointPossition1Txt;
		private TextField jointPossition2Txt;
		private TextButton createDistanceJointButton;
		private TextButton createRevoluteJointButton;
		private TextButton createWheelJointButton;
		private List jointList;
		private boolean getPossition;
		private Table jointTable;
		private BasicObject firstBodyJoint;
		private BasicObject secondBodyJoint;
		private int numberOfFirstBodyJoint;
		private int numberOfSecondBodyJoint;
		private GameObject firstObjectJoint;
		private GameObject secondObjectJoint;
		private Vector2 tempPossition1;
		private Vector2 tempPossition2;
		private boolean toggle;
		private int currentLevelGame;
		private boolean disappearFlag=false;
		private Disappear testDis;
		private List objectList;
		private ScrollPane scrollPane;
		private String []objects;
		private JsonValue  root;
		private String listObjects;
		private String levelDegree;
		private int numberOftry;
		private int numberOfListObject;
		private boolean burnt;
		private boolean done;
	public Game(GameStateManager gsm,int value,int lastLevel) {
		super(gsm);
		//this.value=value;
		//file = Gdx.files.absolute("C:\\Users\\Rawan\\Desktop\\workspaces\\levels\\level"+value+".json");
		file = Gdx.files.external("levels\\level.json");
		currentLevelGame=value;
		posx=0;
		posy=0;
		gameimg="image/background/level"+"0"+".png";
		createObject(true);
		createJoint();
		this.gsm=gsm;
	//	gameimg="image/game/level"+value%7+".png";
		basicTable.setBackground((Drawable) new NinePatchDrawable(getNinePatch((gameimg))));
		this.value=value+1;
		this.lastValue=lastLevel;
		createLayout();
		prepareList();
		
		
	}

	@Override
	public void init() {
		initt();
		handlingEvent();
		handlingCollision();
		
		//for handling body
		mouseJointDef.bodyA=downGround.getBody().get(0);
		mouseJointDef.collideConnected=true;
		mouseJointDef.maxForce=500;
		
		
	}
	//Initialization 
			public void initt(){
				burnt=false;
				done=false;
				levelDegree="easy";
				numberOftry=5;
				AppVar.testPlay=false;
				camera = new OrthographicCamera();
				multiplexer = new InputMultiplexer();
				debugRenderer = new Box2DDebugRenderer();
				world = new World(new Vector2(0, -9.81f), true);
				topGround=new BasicObject(world,BodyType.StaticBody,new Vector2(-50,0),new Vector2(50,0),0,20f);
				downGround=new BasicObject(world,BodyType.StaticBody,new Vector2(-50,0),new Vector2(50,0),0,-14f);
				leftGround=new BasicObject(world,BodyType.StaticBody,new Vector2(0,-50),new Vector2(0,50),-30,0);
				rightGround=new BasicObject(world,BodyType.StaticBody,new Vector2(0,-50),new Vector2(0,50),30,0);
				batch = new SpriteBatch();
				tmpBodies = new Array<Body>();
				index= new Array<Integer>();
				objectPosition=new Vector();
				//levelFile = Gdx.files.absolute("C:\\Users\\Rawan\\Desktop\\workspaces\\levels\\levelLock.txt");
				levelFile = Gdx.files.external("levels\\levelLock.txt");
				String output1=readFromFile();
				String da[] =output1.split(":");
				lastLeve=Integer.parseInt(da[1]);
				stage=new Stage();
				skin=new Skin(Gdx.files.internal("gameui/levelgame.jason"),new TextureAtlas("gameui/levelgamelayout.pack"));
				life=new Label(" ", skin);
				basicTable=new Table(skin);
				leftTable=new Table(skin);
				creationTable =new Table(skin);
				directionTable=new Table(skin);
				basicTable.setFillParent(true);
				basicTable.setBounds(0, 0, stage.getWidth(), stage.getHeight());
				//leftTable.setFillParent(true);
				//leftTable.setBounds(0, 0, stage.getWidth(), stage.getHeight());
				objectList=new List(skin);
			//	objectList.setHeight(height);
				scrollPane=new ScrollPane(objectList,skin);
				mouseJointDef=new MouseJointDef();
				touchDownPosition=new Vector3();
				tmpTouchDownPosition=new Vector2();
				gameObjectArray=new ArrayList<GameObject>();
				animationObjectplayer= new ArrayList<Player>();
		    	dangerousObjectPlayer=new ArrayList<Player>();
		    	cannonObjectplayer=new ArrayList<Player>();
		    	jointList=new List(skin);
				jointList.setItems(new String[]{"Wheel Joint","Revolute Joint","Distance Join"});
				//test array
				jointTable=new Table(skin);
				gameObjectArrayTest=new ArrayList<GameObject>();
				 levelDialog=new LevelDialog("", skin);
				 levelDialog.background( (Drawable) new NinePatchDrawable(getNinePatch(("image/game/daialogback.png"))));
				 GameOverDialog=new GameOverDialog("", skin);
				 NextDialog=new NextDialog("", skin);
					GameOverDialog.background( (Drawable) new NinePatchDrawable(getNinePatch(("image/game/daialogback.png"))));
					NextDialog.background( (Drawable) new NinePatchDrawable(getNinePatch(("image/game/daialogback.png"))));
					tempPossition1=new Vector2();
			    	tempPossition2=new Vector2();
			    	jointPossition1Txt=new TextField("0.0",skin);
					jointPossition2Txt=new TextField("0.0",skin);
			    	toggle=true;
			    	jointMaxMotorTorqueTxt=new TextField("0.0",skin);
			    	jointMotorSpeed=new TextField("0.0",skin);
				 
				
				}
			Drawable createDrawable (Color c) {
			    Pixmap p = new Pixmap(1, 1, Format.RGBA8888);
			    p.setColor(c);
			    p.drawPixel(0, 0);

			    return new SpriteDrawable(new Sprite(new Texture(p)));
			}
			public void handlingEvent(){
				//event handling for the ui
				multiplexer.addProcessor(stage);
				//event handling for the mousejoint
				multiplexer.addProcessor(new InputController(){
					@Override
					public boolean keyDown(int keycode) {
						if(AppVar.testPlay){
						switch(keycode) {
						case Keys.E:
							for(int i=0;i<gameObjectArray.size();i++){
								if(gameObjectArray.get(i) instanceof Vehicle){
									if(((Vehicle)gameObjectArray.get(i)).isControlMovement()){
										((Vehicle)gameObjectArray.get(i)).getWheelJoint()[0].enableMotor(true);
										((Vehicle)gameObjectArray.get(i)).getWheelJoint()[1].enableMotor(true);
										((Vehicle)gameObjectArray.get(i)).getWheelJoint()[0].setMotorSpeed(((Vehicle)gameObjectArray.get(i)).getMotorSpeed());
										((Vehicle)gameObjectArray.get(i)).getWheelJoint()[1].setMotorSpeed(((Vehicle)gameObjectArray.get(i)).getMotorSpeed());
									}
								}
								
							}
							
							break;
						case Keys.W:
							for(int i=0;i<gameObjectArray.size();i++){
								if(gameObjectArray.get(i) instanceof Vehicle){
									if(((Vehicle)gameObjectArray.get(i)).isControlMovement()){
										((Vehicle)gameObjectArray.get(i)).getWheelJoint()[0].enableMotor(true);
										((Vehicle)gameObjectArray.get(i)).getWheelJoint()[1].enableMotor(true);
										((Vehicle)gameObjectArray.get(i)).getWheelJoint()[0].setMotorSpeed(-((Vehicle)gameObjectArray.get(i)).getMotorSpeed());
										((Vehicle)gameObjectArray.get(i)).getWheelJoint()[1].setMotorSpeed(-((Vehicle)gameObjectArray.get(i)).getMotorSpeed());
									}
								}
								
							}
							break;
						case Keys.D:
							for(int i=0;i<gameObjectArray.size();i++){
								if(gameObjectArray.get(i) instanceof StartObject){
									if(((StartObject)gameObjectArray.get(i)).isControlMovement()){
										((StartObject)gameObjectArray.get(i)).getWheelJoint()[0].enableMotor(true);
										((StartObject)gameObjectArray.get(i)).getWheelJoint()[1].enableMotor(true);
										((StartObject)gameObjectArray.get(i)).getWheelJoint()[0].setMotorSpeed(((StartObject)gameObjectArray.get(i)).getMovementSpeed());
										((StartObject)gameObjectArray.get(i)).getWheelJoint()[1].setMotorSpeed(((StartObject)gameObjectArray.get(i)).getMovementSpeed());
									}
								}
								
							}
							break;
						case Keys.S:
							for(int i=0;i<gameObjectArray.size();i++){
								if(gameObjectArray.get(i) instanceof StartObject){
									if(((StartObject)gameObjectArray.get(i)).isControlMovement()){
										((StartObject)gameObjectArray.get(i)).getWheelJoint()[0].enableMotor(true);
										((StartObject)gameObjectArray.get(i)).getWheelJoint()[1].enableMotor(true);
										((StartObject)gameObjectArray.get(i)).getWheelJoint()[0].setMotorSpeed(-((StartObject)gameObjectArray.get(i)).getMovementSpeed());
										((StartObject)gameObjectArray.get(i)).getWheelJoint()[1].setMotorSpeed(-((StartObject)gameObjectArray.get(i)).getMovementSpeed());
									}
								}
								
							}
							
							break;
						case Keys.R:
							for(int i=0;i<gameObjectArray.size();i++){
								if(gameObjectArray.get(i) instanceof StartObject){
									((StartObject)gameObjectArray.get(i)).getBasicObject().get(0).getBody().get(0).applyForceToCenter(0,1000, true);
									((StartObject)gameObjectArray.get(i)).getBasicObject().get(1).getBody().get(0).applyForceToCenter(0, 1000, true);
									((StartObject)gameObjectArray.get(i)).getBasicObject().get(2).getBody().get(0).applyForceToCenter(0, 1000, true);
								}
							}
							//player.getBody().applyForceToCenter(0, 200, true);
							break;
							
							
							
						}
						}
						return true;
					}
					@Override
					public boolean keyUp(int keycode) {
						switch(keycode) {
						case Keys.E:
						case Keys.W:
							for(int i=0;i<gameObjectArray.size();i++){
								if(gameObjectArray.get(i) instanceof Vehicle){
									if(((Vehicle)gameObjectArray.get(i)).isControlMovement()){
										((Vehicle)gameObjectArray.get(i)).getWheelJoint()[0].enableMotor(false);
										((Vehicle)gameObjectArray.get(i)).getWheelJoint()[1].enableMotor(false);
									}
								}
								
							}
							break;
						case Keys.D:
						case Keys.S:
							for(int i=0;i<gameObjectArray.size();i++){
								if(gameObjectArray.get(i) instanceof StartObject){
									if(((StartObject)gameObjectArray.get(i)).isControlMovement()){
										((StartObject)gameObjectArray.get(i)).getWheelJoint()[0].enableMotor(false);
										((StartObject)gameObjectArray.get(i)).getWheelJoint()[1].enableMotor(false);
									}
								}
								
							}
							break;
						}
						return true;
					}

					@Override
					public boolean touchDown(int screenX, int screenY, int pointer, int button) {
						if(!AppVar.testPlay){
						camera.unproject(touchDownPosition.set(screenX,screenY,0));
						world.QueryAABB(queryCallback, touchDownPosition.x, touchDownPosition.y, touchDownPosition.x, touchDownPosition.y);
						}
						return true;
						
					}
					@Override
					public boolean touchUp(int screenX, int screenY, int pointer, int button) {
						if(!AppVar.testPlay){
						if(mouseJoint==null){
							return false;
						}
						world.destroyJoint(mouseJoint);
						mouseJoint=null;
						return true;
						}
						return false;
					}
					@Override
					public boolean touchDragged(int screenX, int screenY, int pointer) {
						if(!AppVar.testPlay){
						if(mouseJoint==null)
							return false;
						camera.unproject(touchDownPosition.set(screenX,screenY, 0));
						mouseJoint.setTarget(tmpTouchDownPosition.set(touchDownPosition.x,touchDownPosition.y));
						return true;
						}
						return false;
					}
				});
					Gdx.input.setInputProcessor(multiplexer);
			}
			public void createLayout(){ 
				 
				if(lifeCount>0){
				life.setText("life:"+" "+lifeCount);
				basicTable.add(life).left();
				basicTable.add("Game Test      ","level").colspan(3).expandX().top().center().row();
				}
				else{
					basicTable.add("Game Test","level").colspan(3).expandX().top().row();
				}
				createCreationTable();
				createDirectionTable();
				leftTable.add(creationTable).uniformX().top().left().padBottom(10);
				leftTable.row();
				leftTable.add(directionTable).uniformX().expandY().top().left();
				basicTable.add(leftTable).uniformX().left().row();
				basicTable.add(" ").expandY();
				stage.addActor(basicTable);
				createDistanceJointButton=new TextButton("",skin,"done");
				createRevoluteJointButton=new TextButton("",skin,"done");
				createWheelJointButton=new TextButton("",skin,"done");
				jointList.addListener(new ClickListener(){
					@Override
					public void clicked(InputEvent event, float x, float y) {
						getPossition=true;
						switch (jointList.getSelected().toString()) {
						case "Wheel Joint":
							createWheelTable();
							//AppVar.createWheelJoint( world,Body shape,Body leftWheel,Body rightWheel,float width,float height,float density)
							break;
						case "Revolute Joint":
							createReveluteTable();
							
							//AppVar.createRevoluteJoint(world,firstBodyJoint.getBody().get(0),secondBodyJoint.getBody().get(0),new Vector2(firstBodyJoint.getPosition().get(0).x,firstBodyJoint.getPosition().get(0).y));
							
							break;
						case "Distance Join":
							createDistanceTable();
							//AppVar.createDistanceJoin(world,firstBodyJoint.getBody().get(0),secondBodyJoint.getBody().get(0),new Vector2(firstBodyJoint.getPosition().get(0).x,firstBodyJoint.getPosition().get(0).y+(firstBodyJoint.getWidth())) ,new Vector2(secondBodyJoint.getPosition().get(0).x,secondBodyJoint.getPosition().get(0).y));
							break;

						default:
							break;
						}
					}
				});
				createDistanceJointButton.addListener(new ClickListener(){
					@Override
					public void clicked(InputEvent event, float x, float y) {
						firstObjectJoint.setGameJoint(new Distance(firstBodyJoint, secondBodyJoint,new Vector2( tempPossition1), new Vector2(tempPossition2),numberOfFirstBodyJoint ,numberOfSecondBodyJoint,world));
						secondObjectJoint.setSecondJoint(true);
						jointPossition1Txt.setText("0.0");
						jointPossition2Txt.setText("0.0");
						stage.clear();
						basicTable.clear();
						if(lifeCount>0){
							life.setText("life:"+" "+lifeCount);
							basicTable.add(life).left();
							basicTable.add("Game Test      ","level").colspan(3).expandX().top().center().row();
							}
							else{
								basicTable.add("Game Test","level").colspan(3).expandX().top().row();
							}
						basicTable.add(leftTable).uniformX().left().row();
						basicTable.add(" ").expandY();
						stage.addActor(basicTable);
						getPossition=false;
						toggle=true;
					}
				});
				createRevoluteJointButton.addListener(new ClickListener(){
					@Override
					public void clicked(InputEvent event, float x, float y) {
						firstObjectJoint.setGameJoint(new Revolute(firstBodyJoint,secondBodyJoint, new Vector2(tempPossition1), new Vector2(tempPossition1),numberOfFirstBodyJoint ,numberOfSecondBodyJoint,world, Float.parseFloat(jointMaxMotorTorqueTxt.getText()), Float.parseFloat(jointMotorSpeed.getText())));
						secondObjectJoint.setSecondJoint(true);
						jointPossition1Txt.setText("0.0");
						jointPossition2Txt.setText("0.0");
						jointMaxMotorTorqueTxt.setText("0.0");
						jointMotorSpeed.setText("0.0");
						stage.clear();
						basicTable.clear();
						if(lifeCount>0){
							life.setText("life:"+" "+lifeCount);
							basicTable.add(life).left();
							basicTable.add("Game Test      ","level").colspan(3).expandX().top().center().row();
							}
							else{
								basicTable.add("Game Test","level").colspan(3).expandX().top().row();
							}						basicTable.add(leftTable).uniformX().left().row();
						basicTable.add(" ").expandY();
						stage.addActor(basicTable);
						getPossition=false;
						toggle=true;
					}
				});
				
				createWheelJointButton.addListener(new ClickListener(){
					@Override
					public void clicked(InputEvent event, float x, float y) {
						stage.clear();
						basicTable.clear();
						if(lifeCount>0){
							life.setText("life:"+" "+lifeCount);
							basicTable.add(life).left();
							basicTable.add("Game Test      ","level").colspan(3).expandX().top().center().row();
							}
							else{
								basicTable.add("Game Test","level").colspan(3).expandX().top().row();
							}						basicTable.add(leftTable).uniformX().left().row();
						basicTable.add(" ").expandY();
						stage.addActor(basicTable);
						getPossition=false;
						toggle=true;
					}
				});
				
			   }  
			public void prepareList(){
				
				objectList.addListener(new ClickListener(){
					@Override
					public void clicked(InputEvent event, float x, float y) {
						System.out.println(objectList.getSelectedIndex());
						System.out.println(objectList.getItems().size);
						if(!AppVar.testPlay&&listObjects.contains("-")){
							String []selected=objectList.getSelected().toString().split("-");
							int indexNumber=index.get(objectList.getSelectedIndex());
							if(selected.length==2){
							switch (selected[1]) {
							case"BasicObject":
								boolean setStatic=false;
								if(root.get(indexNumber).has("setStatic")){setStatic=true;}
								boolean stopRotation=false;
								if(root.get(indexNumber).has("stopRotation")){stopRotation=true;}
								boolean takeSound=false;
								if(root.get(indexNumber).has("takeSound")){takeSound=true;}
								basicObjectTest = new BasicObject(world, BodyType.DynamicBody, AppVar.basicBodyType.valueOf(root.get(indexNumber).getString("appVarType")),
										root.get(indexNumber).getString("img"), -9, -12);
								basicObjectTest.jointInfo[0]=0+"";
								if(root.get(indexNumber).getString("basicType").equals("PolygonShape")){
									//add false
								basicObjectTest.setProperties(root.get(indexNumber).getFloat("density")-1,root.get(indexNumber).getFloat("friction")-1,root.get(indexNumber).getFloat("restitution")-1,root.get(indexNumber).getFloat("velocityX")-1,root.get(indexNumber).getFloat("velocityY")-1,root.get(indexNumber).getFloat("width")-1,root.get(indexNumber).getFloat("height")-1,setStatic,stopRotation,root.get(indexNumber).getInt("collision")-1,takeSound);
								}
								else{
									basicObjectTest.setProperties(root.get(indexNumber).getFloat("density")-1,root.get(indexNumber).getFloat("friction")-1,root.get(indexNumber).getFloat("restitution")-1,root.get(indexNumber).getFloat("velocityX")-1,root.get(indexNumber).getFloat("velocityY")-1,setStatic,stopRotation,root.get(indexNumber).getInt("collision")-1,takeSound);
									((BasicObject) basicObjectTest).setRadius(root.get(indexNumber).getFloat("width")-1);
								}
								basicObjectTest.setStatic(new Vector2(basicObjectTest.getPosition().get(0)));
								gameObjectArray.add(basicObjectTest);
								
								break;
							case"Cannon":
								boolean takeSoundcannon=false;
								if(root.get(indexNumber).has("takeSound")){takeSoundcannon=true;}
								cannonTest = new Cannon(world, AppVar.cannonType.valueOf(root.get(indexNumber).getString("appVarType")), root.get(indexNumber).getString("img"), -2, -10);
								cannonTest.jointInfo[0]=0+"";
								cannonTest.setProperties(root.get(indexNumber).getFloat("density")-1,root.get(indexNumber).getFloat("friction")-1,root.get(indexNumber).getFloat("restitution")-1,root.get(indexNumber).getInt("ballNumber")-1 ,takeSoundcannon);
								cannonTest.setStatic(new Vector2(cannonTest.getPosition().get(0)));
								gameObjectArray.add(cannonTest);
								
							break;
							case"Chain":
								chainTest= new Chain(world,AppVar.chainType.valueOf(root.get(indexNumber).getString("appVarType")),root.get(indexNumber).getString("img"),-7, -12);
								chainTest.jointInfo[0]=0+"";
								chainTest.setProperties(root.get(indexNumber).getFloat("density")-1,root.get(indexNumber).getFloat("friction")-1,root.get(indexNumber).getFloat("restitution")-1);
								chainTest.setStatic(new Vector2(chainTest.getPosition().get(0)));
								gameObjectArray.add(chainTest);
								
								break;
							case"Elevator":
								elevoterTest= new Elevator(world,AppVar.elevatorType.valueOf(root.get(indexNumber).getString("appVarType")),root.get(indexNumber).getString("img"),5,-12);
								elevoterTest.jointInfo[0]=0+"";
								elevoterTest.setProperties(root.get(indexNumber).getFloat("density")-1,root.get(indexNumber).getFloat("friction")-1,root.get(indexNumber).getFloat("restitution")-1,root.get(indexNumber).getFloat("width")-1,root.get(indexNumber).getFloat("height")-1,root.get(indexNumber).getFloat("motorSpeed")-1,root.get(indexNumber).getFloat("motorForce")-1);
								elevoterTest.setStatic(new Vector2(elevoterTest.getPosition().get(0)));
								gameObjectArray.add(elevoterTest);
								break;
							case"Disappear":
								disappearTest= new Disappear(world,AppVar.disappear.valueOf(root.get(indexNumber).getString("appVarType")),root.get(indexNumber).getString("img"),-8,-12);
								disappearTest.jointInfo[0]=0+"";
								disappearTest.setProperties(root.get(indexNumber).getFloat("density")-1,root.get(indexNumber).getFloat("friction")-1,root.get(indexNumber).getFloat("restitution")-1,root.get(indexNumber).getFloat("width")-1,root.get(indexNumber).getFloat("height")-1);
								disappearTest.setStatic(new Vector2(disappearTest.getPosition().get(0)));
								gameObjectArray.add(disappearTest);
								break;
							case"ImponderableShape":
								boolean takeSoundimp=false;
								if(root.get(indexNumber).has("takeSound")){takeSoundimp=true;}
								imponderableShapeTest= new ImponderableShape(world,imponderableType.valueOf(root.get(indexNumber).getString("appVarType")),root.get(indexNumber).getString("img"),-5, -12);
								imponderableShapeTest.jointInfo[0]=0+"";
								imponderableShapeTest.setProperties(root.get(indexNumber).getFloat("density")-1,root.get(indexNumber).getFloat("friction")-1,root.get(indexNumber).getFloat("restitution")-1, root.get(indexNumber).getFloat("motorTorque")-1, root.get(indexNumber).getFloat("motorSpeed")-1,root.get(indexNumber).getFloat("width")-1,root.get(indexNumber).getFloat("height")-1, takeSoundimp);
								imponderableShapeTest.setStatic(new Vector2(imponderableShapeTest.getPosition().get(0)));
								gameObjectArray.add(imponderableShapeTest);
								
								break;
							case"Triangle":
								boolean setStatic2=false;
								if(root.get(indexNumber).has("setStatic")){setStatic2=true;}
								boolean stopRotation2=false;
								if(root.get(indexNumber).has("stopRotation")){stopRotation2=true;}
								triangle = new Triangle(world,TriangleType.valueOf(root.get(indexNumber).getString("appVarType")), root.get(indexNumber).getString("img"),posx,posy);
								triangle.jointInfo[0]=0+"";
								triangle.setProperties(root.get(indexNumber).getFloat("density")-1,root.get(indexNumber).getFloat("friction")-1,root.get(indexNumber).getFloat("restitution")-1,root.get(indexNumber).getFloat("angularVelocity")-1,setStatic2,stopRotation2,root.get(indexNumber).getInt("collision")-1);
								triangle.setStatic(new Vector2(triangle.getPosition().get(0)));
								gameObjectArray.add(triangle);
								break;
							case"Vehicle":
								boolean takeSoundveh=false;
								if(root.get(indexNumber).has("takeSound")){takeSoundveh=true;}
								boolean controlMove=false;
								if(root.get(indexNumber).has("controlMovement")){controlMove=true;}
								carTest = new Vehicle(world,vehicleType.valueOf(root.get(indexNumber).getString("appVarType")),root.get(indexNumber).getString("img") , root.get(indexNumber).getString("wheelImg"), 0,-12);	
								carTest.jointInfo[0]=0+"";
								carTest.setProperties(root.get(indexNumber).getFloat("density")-1,root.get(indexNumber).getFloat("friction")-1,root.get(indexNumber).getFloat("restitution")-1, root.get(indexNumber).getFloat("motorSpeed")-1,root.get(indexNumber).getFloat("width")-1,root.get(indexNumber).getFloat("height")-1,takeSoundveh,controlMove);
								carTest.setStatic(new Vector2(carTest.getPosition().get(0)));
								gameObjectArray.add(carTest);
								
								break;
							case"WindMill":
								boolean takeSoundwind=false;
								if(root.get(indexNumber).has("takeSound")){takeSoundwind=true;}
								windMillTest = new WindMill(world, windMillType.valueOf(root.get(indexNumber).getString("appVarType")), 3, -12,root.get(indexNumber).getString("img"));
								windMillTest.jointInfo[0]=0+"";
								windMillTest.setProperties(root.get(indexNumber).getFloat("density")-1,root.get(indexNumber).getFloat("friction")-1,root.get(indexNumber).getFloat("restitution")-1,windMillTest.getAngularVelocity(),root.get(indexNumber).getFloat("width")-1,root.get(indexNumber).getFloat("height")-1,takeSoundwind);
								windMillTest.setStatic(new Vector2(windMillTest.getPosition().get(0)));
								gameObjectArray.add(windMillTest);
								break;
								
							}
							}
							//listObjects=objectList.getSelected().toString();
							//objects=listObjects.split(",");
							String replace=" ";
							for(int i=objectList.getSelectedIndex();i>0;i--){
								replace+=" ";
							}
							
							if(levelDegree.equals("easy")){
								if(selected.length==2){
									numberOfListObject--;
								}
						listObjects=listObjects.replace(objectList.getSelected().toString(),replace);
							objects=listObjects.split(",");
							objectList.setItems(objects);
							objectList.setSelectedIndex(listObjects.indexOf(0));
							}
							objectList.setItems(objects);
							stage.clear();
							basicTable.clear();
							if(lifeCount>0){
								life.setText("life:"+" "+lifeCount);
								basicTable.add(life).left();
								basicTable.add("Game Test      ","level").colspan(30).expandX().top().center().row();
								}
								else{
									basicTable.add("Game Test","level").colspan(30).expandX().top().row();
								}
							basicTable.add(leftTable).left();
							basicTable.add(objectList).width(basicTable.getWidth()/6).left().top().row();//
							objectList.setSelectedIndex(listObjects.indexOf(0));
							basicTable.add(" ").expandY();
							if(numberOfListObject==0){
								basicTable.removeActor(objectList);
							}
							stage.addActor(basicTable);
						}
					}
				});
			}
			
			public void createDirectionTable(){
				//ro
				//directionTable.setBackground((Drawable) new NinePatchDrawable(getNinePatch(("b1.png"))));
              TextButton upButton= (TextButton) new TextButton("",skin,"jump");
				TextButton rightButton=new TextButton("",skin,"left");
				TextButton leftButton=new TextButton("",skin,"right");
				directionTable.add(upButton).width(basicTable.getWidth()/25f).height(basicTable.getWidth()/25f).colspan(3).expandX();
				directionTable.row();
				directionTable.add(rightButton).width(basicTable.getWidth()/25f).height(basicTable.getWidth()/25f);
				directionTable.add(leftButton).width(basicTable.getWidth()/25f).height(basicTable.getWidth()/25f);
				
				directionTable.row();
				
				upButton.addListener(new ClickListener(){
					@Override
					public void clicked(InputEvent event, float x, float y) {
						if(AppVar.testPlay){
							for(int i=0;i<gameObjectArray.size();i++){
								if(gameObjectArray.get(i) instanceof StartObject){
									((StartObject)gameObjectArray.get(i)).getBasicObject().get(0).getBody().get(0).applyForceToCenter(70,400, true);
									((StartObject)gameObjectArray.get(i)).getBasicObject().get(1).getBody().get(0).applyForceToCenter(70, 400, true);
									((StartObject)gameObjectArray.get(i)).getBasicObject().get(2).getBody().get(0).applyForceToCenter(70, 400, true);
								}
							}
						}
					}
				});
				rightButton.addListener(new ClickListener(){
					
					@Override
					public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
						if(AppVar.testPlay){
						for(int i=0;i<gameObjectArray.size();i++){
							if(gameObjectArray.get(i) instanceof StartObject){
								if(((StartObject)gameObjectArray.get(i)).isControlMovement()){
									((StartObject)gameObjectArray.get(i)).getWheelJoint()[0].enableMotor(true);
									((StartObject)gameObjectArray.get(i)).getWheelJoint()[1].enableMotor(true);
									((StartObject)gameObjectArray.get(i)).getWheelJoint()[0].setMotorSpeed(-((StartObject)gameObjectArray.get(i)).getMovementSpeed());
									((StartObject)gameObjectArray.get(i)).getWheelJoint()[1].setMotorSpeed(-((StartObject)gameObjectArray.get(i)).getMovementSpeed());
								}
							}
							
							else if(gameObjectArray.get(i) instanceof Vehicle){
									if(((Vehicle)gameObjectArray.get(i)).isControlMovement()){
										((Vehicle)gameObjectArray.get(i)).getWheelJoint()[0].enableMotor(true);
										((Vehicle)gameObjectArray.get(i)).getWheelJoint()[1].enableMotor(true);
										((Vehicle)gameObjectArray.get(i)).getWheelJoint()[0].setMotorSpeed(-((Vehicle)gameObjectArray.get(i)).getMotorSpeed());
										((Vehicle)gameObjectArray.get(i)).getWheelJoint()[1].setMotorSpeed(-((Vehicle)gameObjectArray.get(i)).getMotorSpeed());
									}
								}
						}
						}
						return true;
						
					}
					
					@Override
					public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
						
						for(int i=0;i<gameObjectArray.size();i++){
							if(gameObjectArray.get(i) instanceof StartObject){
								if(((StartObject)gameObjectArray.get(i)).isControlMovement()){
									((StartObject)gameObjectArray.get(i)).getWheelJoint()[0].enableMotor(false);
									((StartObject)gameObjectArray.get(i)).getWheelJoint()[1].enableMotor(false);
								}
							}
							
							else if(gameObjectArray.get(i) instanceof Vehicle){
									if(((Vehicle)gameObjectArray.get(i)).isControlMovement()){
										((Vehicle)gameObjectArray.get(i)).getWheelJoint()[0].enableMotor(false);
										((Vehicle)gameObjectArray.get(i)).getWheelJoint()[1].enableMotor(false);
									}
								}
								
							
							
							
						}
					
					}
					
				});
				leftButton.addListener(new ClickListener(){
					@Override
					public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
						if(AppVar.testPlay){
							for(int i=0;i<gameObjectArray.size();i++){
								if(gameObjectArray.get(i) instanceof StartObject){
									if(((StartObject)gameObjectArray.get(i)).isControlMovement()){
										((StartObject)gameObjectArray.get(i)).getWheelJoint()[0].enableMotor(true);
										((StartObject)gameObjectArray.get(i)).getWheelJoint()[1].enableMotor(true);
										((StartObject)gameObjectArray.get(i)).getWheelJoint()[0].setMotorSpeed(((StartObject)gameObjectArray.get(i)).getMovementSpeed());
										((StartObject)gameObjectArray.get(i)).getWheelJoint()[1].setMotorSpeed(((StartObject)gameObjectArray.get(i)).getMovementSpeed());
									}
								}
								
								else if(gameObjectArray.get(i) instanceof Vehicle){
										if(((Vehicle)gameObjectArray.get(i)).isControlMovement()){
											((Vehicle)gameObjectArray.get(i)).getWheelJoint()[0].enableMotor(true);
											((Vehicle)gameObjectArray.get(i)).getWheelJoint()[1].enableMotor(true);
											((Vehicle)gameObjectArray.get(i)).getWheelJoint()[0].setMotorSpeed(((Vehicle)gameObjectArray.get(i)).getMotorSpeed());
											((Vehicle)gameObjectArray.get(i)).getWheelJoint()[1].setMotorSpeed(((Vehicle)gameObjectArray.get(i)).getMotorSpeed());
										}
									}
							}
							}
						
						
						
						return true;
						}
					@Override
					public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
						for(int i=0;i<gameObjectArray.size();i++){
							if(gameObjectArray.get(i) instanceof StartObject){
								if(((StartObject)gameObjectArray.get(i)).isControlMovement()){
									((StartObject)gameObjectArray.get(i)).getWheelJoint()[0].enableMotor(false);
									((StartObject)gameObjectArray.get(i)).getWheelJoint()[1].enableMotor(false);
								}
							}
							
							else if(gameObjectArray.get(i) instanceof Vehicle){
									if(((Vehicle)gameObjectArray.get(i)).isControlMovement()){
										((Vehicle)gameObjectArray.get(i)).getWheelJoint()[0].enableMotor(false);
										((Vehicle)gameObjectArray.get(i)).getWheelJoint()[1].enableMotor(false);
									}
								}
								
							
							
							
						}
						
					}
				});
				
				
			}
			  // This method create the layout necessary for creation the objects
			public void createCreationTable(){
				//ro
				//creationTable.setBackground((Drawable) new NinePatchDrawable(getNinePatch(("b2.png"))));
				TextButton changeStateButton=new TextButton("",skin,"play");//dynamic/static
				TextButton createjointButton=new TextButton("",skin,"addjoint");//dynamic/static
				TextButton deletejointButton=new TextButton("",skin,"deletejoint");//dynamic/static
				TextButton backButton=new TextButton("",skin,"back");//dynamic/static
				TextButton opjectButton= (TextButton) new TextButton("",skin,"addobject");
				opjectButton.addListener(new ClickListener(){
					@Override
					public void clicked(InputEvent event, float x, float y) {
						objectList.setItems(objects);
						stage.clear();
						basicTable.clear();
						if(lifeCount>0){
							life.setText("life:"+" "+lifeCount);
							basicTable.add(life).left();
							basicTable.add("Game Test      ","level").colspan(30).expandX().top().center().row();
							}
							else{
								basicTable.add("Game Test","level").colspan(30).expandX().top().row();
							}
						basicTable.add(leftTable).left();
						basicTable.add(objectList).width(basicTable.getWidth()/6).left().top().row();//
						basicTable.add(" ").expandY();
						if(numberOfListObject==0){
							basicTable.removeActor(objectList);
						}
						stage.addActor(basicTable);
						
						
						
					}
				});
				
				//dynamic/static
				changeStateButton.addListener(new ClickListener(){
					@Override
					public void clicked(InputEvent event, float x, float y) {
						stage.clear();
						basicTable.clear();
						if(lifeCount>0){
							life.setText("life:"+" "+lifeCount);
							basicTable.add(life).left();
							basicTable.add("Game Test      ","level").colspan(3).expandX().top().center().row();
							}
							else{
								basicTable.add("Game Test","level").colspan(3).expandX().top().row();
							}
						basicTable.add(leftTable).uniformX().left().row();
						basicTable.add(" ").expandY();
						stage.addActor(basicTable);
						if(numberOftry==0&&levelDegree.equals("hard")){
							changeBody(false);
							GameOverDialog.show(stage);
						}
						else{
							
						if(!AppVar.testPlay){
							//Gdx.input.setInputProcessor(null);
							//Gdx.input.setInputProcessor(stage);
							changeBody(true);	
						}
						else{
							handlingEvent();
							changeBody(false);
						}
						AppVar.testPlay=!AppVar.testPlay;
						}
						if(levelDegree.equals("hard")){
							numberOftry--;
						}
					}
				});
				createjointButton.addListener(new ClickListener(){
					@Override
					public void clicked(InputEvent event, float x, float y) {
						jointList.setHeight(basicTable.getHeight()/20);
						jointList.setWidth(basicTable.getWidth()/10);
						jointTable.clear();
						jointTable.add(jointList);
						stage.clear();
						basicTable.clear();
						if(lifeCount>0){
							life.setText("life:"+" "+lifeCount);
							basicTable.add(life).left();
							basicTable.add("Game Test      ","level").colspan(30).expandX().top().center().row();
							}
							else{
								basicTable.add("Game Test","level").colspan(30).expandX().top().row();
							}
						basicTable.add(leftTable).left();
						basicTable.add(jointTable).left().top().row();//
						basicTable.add(" ").expandY();
						stage.addActor(basicTable);
						//jointTable.setPosition(basicTable.getWidth()/4.5f,basicTable.getHeight()/1.14f);
						//stage.addActor(jointTable);
					}
				});
				deletejointButton.addListener(new ClickListener(){
					@Override
					public void clicked(InputEvent event, float x, float y) {
						stage.clear();
						basicTable.clear();
						if(lifeCount>0){
							life.setText("life:"+" "+lifeCount);
							basicTable.add(life).left();
							basicTable.add("Game Test      ","level").colspan(3).expandX().top().center().row();
							}
							else{
								basicTable.add("Game Test","level").colspan(3).expandX().top().row();
							}
						basicTable.add(leftTable).uniformX().left().row();
						basicTable.add(" ").expandY();
						stage.addActor(basicTable);
						if(tempObject!=null){
							if(tempObject instanceof AnimationObject ){
								//delete
								Body body =null;
								for(int i=0;i<gameObjectArray.size();i++){
									if(gameObjectArray.get(i).equals(tempObject)){
										gameObjectArray.remove(tempObject);
										body = tempObject.getBasicObject().get(0).getBody().get(0);
									}}
								//here we delete animation form its array and then delete basic object
								for(int i=0;i<animationObjectplayer.size();i++){
									if(animationObjectplayer.get(i).getBody().equals(body)){
										world.destroyBody(tempObject.getBasicObject().get(0).getBody().get(0));
										animationObjectplayer.get(i).deleteTexture();
										animationObjectplayer.remove(i);
									}
								}
								//create
								animObjectTest=new AnimationObject(world, ((AnimationObject) tempObject).getType(),tempObject.getPosition().get(0).x,tempObject.getPosition().get(0).y);	
								animObjectTest.jointInfo[0]=0+"";
								animObjectTest.setProperties(tempObject.getDensity(),tempObject.getFriction(),tempObject.getRestitution(),tempObject.getWidth(),tempObject.getHeight(),((AnimationObject) tempObject).getAnimationSpeed(),((AnimationObject) tempObject).getIsCollision(),((AnimationObject) tempObject).isStatic());
								animObjectTest.setStatic(new Vector2(animObjectTest.getPosition().get(0)));
								gameObjectArray.add(animObjectTest);
								animationObjectplayer.add(animObjectTest.getPlayer());
								
								
							}
							else if(tempObject instanceof BasicObject){
								//delete
								for(int i=0;i<gameObjectArray.size();i++){
									if(gameObjectArray.get(i).equals(tempObject)){
										gameObjectArray.remove(i);
									}
								}
								for(int i=0;i<tempObject.getBasicObject().size();i++){
								world.destroyBody(tempObject.getBasicObject().get(i).getBody().get(0));
								}
								//create
								basicObjectTest = new BasicObject(world, BodyType.DynamicBody,((BasicObject) tempObject).getType(),((BasicObject) tempObject).getImagePath(), tempObject.getPosition().get(0).x,tempObject.getPosition().get(0).y);	
										
								basicObjectTest.jointInfo[0]=0+"";
								
								if(((BasicObject) tempObject).getFixture().getShape() instanceof PolygonShape){
									//add false
								basicObjectTest.setProperties(tempObject.getDensity(),tempObject.getFriction(),tempObject.getRestitution(),((BasicObject) tempObject).getAngularVelocityX(),((BasicObject) tempObject).getAngularVelocity(),tempObject.getWidth(),tempObject.getHeight(),((BasicObject) tempObject).isStatic(),((BasicObject) tempObject).isRotate(),((BasicObject) tempObject).getIsCollision(),((BasicObject) tempObject).isTakeSound());
								}
								else{
									basicObjectTest.setProperties(tempObject.getDensity(),tempObject.getFriction(),tempObject.getRestitution(),((BasicObject) tempObject).getAngularVelocityX(),((BasicObject) tempObject).getAngularVelocity(),((BasicObject) tempObject).isStatic(),((BasicObject) tempObject).isRotate(),((BasicObject) tempObject).getIsCollision(),((BasicObject) tempObject).isTakeSound());
									((BasicObject) basicObjectTest).setRadius(tempObject.getWidth());
								}
								basicObjectTest.setStatic(new Vector2(basicObjectTest.getPosition().get(0)));
								gameObjectArray.add(basicObjectTest);
								
							}
							else if(tempObject instanceof Cannon){
								//delete
								Body body = null;
								for(int i=0;i<gameObjectArray.size();i++){
									if(gameObjectArray.get(i).equals(tempObject)){
										gameObjectArray.remove(i);
									}
								}
								for(int i=0;i<tempObject.getBasicObject().size();i++){
									//delete animation from cannon array
							    	world.destroyBody(tempObject.getBasicObject().get(i).getBody().get(0));
								}
								for(int jj=0;jj<cannonObjectplayer.size();jj++){
									cannonObjectplayer.get(jj).deleteTexture();
									cannonObjectplayer.remove(jj);
								}
								//create
								cannonTest = new Cannon(world, ((Cannon) tempObject).getType(), ((Cannon) tempObject).getImg(), tempObject.getPosition().get(0).x,tempObject.getPosition().get(0).y);
								cannonTest.jointInfo[0]=0+"";
							
							cannonTest.setProperties(tempObject.getDensity(),tempObject.getFriction(),tempObject.getRestitution(),((Cannon) tempObject).getBallNumber(),((Cannon) tempObject).isTakeSound());
							cannonTest.setStatic(new Vector2(cannonTest.getPosition().get(0)));
							gameObjectArray.add(cannonTest);
							}
							else if(tempObject instanceof Chain){
								//delete
								for(int i=0;i<gameObjectArray.size();i++){
									if(gameObjectArray.get(i).equals(tempObject)){
										gameObjectArray.remove(i);
									}
								}
								for(int i=0;i<tempObject.getBasicObject().size();i++){
									world.destroyBody(tempObject.getBasicObject().get(i).getBody().get(0));
									}
								//create
								chainTest= new Chain(world,((Chain) tempObject).getType(),((Chain) tempObject).getImg(),tempObject.getPosition().get(0).x,tempObject.getPosition().get(0).y);
								chainTest.jointInfo[0]=0+"";	
							
							chainTest.setProperties(tempObject.getDensity(),tempObject.getFriction(),tempObject.getRestitution());
							chainTest.setStatic(new Vector2(chainTest.getPosition().get(0)));
							gameObjectArray.add(chainTest);
							}
							else if(tempObject instanceof DangerousObject){
								//delete
								Body body =null;
								for(int i=0;i<gameObjectArray.size();i++){
									if(gameObjectArray.get(i).equals(tempObject)){
										gameObjectArray.remove(tempObject);
										body = tempObject.getBasicObject().get(0).getBody().get(0);
										for(int j=0;j<tempObject.getBasicObject().size();j++){
											world.destroyBody(tempObject.getBasicObject().get(j).getBody().get(0));
											}
									}}
								//here we delete animation form its array and then delete basic object
								for(int i=0;i<dangerousObjectPlayer.size();i++){
									if(dangerousObjectPlayer.get(i).getBody().equals(body)){
										//world.destroyBody(tempObject.getBasicObject().get(0).getBody().get(0));
										dangerousObjectPlayer.get(i).deleteTexture();
										dangerousObjectPlayer.remove(i);
									}
								}
								//create
								dangerousObjectTest=new DangerousObject(world,((DangerousObject) tempObject).getType(),tempObject.getPosition().get(0).x,tempObject.getPosition().get(0).y);
								dangerousObjectTest.jointInfo[0]=0+"";
							
						dangerousObjectTest.setProperties(tempObject.getDensity(),tempObject.getFriction(),tempObject.getRestitution(),tempObject.getWidth(),tempObject.getHeight(),((DangerousObject) tempObject).getMovementSpeed());
						dangerousObjectTest.setStatic(new Vector2(dangerousObjectTest.getPosition().get(0)));
						dangerousObjectTest.getBody().get(0).setType(BodyType.StaticBody);
							gameObjectArray.add(dangerousObjectTest);
							dangerousObjectPlayer.add(dangerousObjectTest.getPlayer());
								
							}
							else if(tempObject instanceof Elevator){
								for(int i=0;i<gameObjectArray.size();i++){
									if(gameObjectArray.get(i).equals(tempObject)){
										gameObjectArray.remove(i);
									}
								}
								for(int i=0;i<tempObject.getBasicObject().size();i++){
								world.destroyBody(tempObject.getBasicObject().get(i).getBody().get(0));
								}
								elevoterTest= new Elevator(world,((Elevator) tempObject).getType(),((Elevator) tempObject).getImg(),tempObject.getPosition().get(0).x,tempObject.getPosition().get(0).y);
								elevoterTest.jointInfo[0]=0+"";
							
							elevoterTest.setProperties(tempObject.getDensity(),tempObject.getFriction(),tempObject.getRestitution(),tempObject.getWidth(),tempObject.getHeight(),((Elevator) tempObject).getMotorSpeed(),((Elevator) tempObject).getMotorForce());
							elevoterTest.setStatic(new Vector2(elevoterTest.getPosition().get(0)));
							gameObjectArray.add(elevoterTest);
								
							}
							else if(tempObject instanceof EndObject){
								//delete
								for(int i=0;i<gameObjectArray.size();i++){
									if(gameObjectArray.get(i).equals(endObjectTest)){
										gameObjectArray.remove(endObjectTest);
										world.destroyBody(endObjectTest.getBody().get(0));
										endObjectplayer.deleteTexture();
										endObjectplayer=null;
									}
								}
								//create
								endObjectTest=new EndObject(world,((EndObject) tempObject).getType(),tempObject.getPosition().get(0).x,tempObject.getPosition().get(0).y);
								endObjectTest.jointInfo[0]=0+"";
							
							endObjectTest.setProperties(tempObject.getDensity(),tempObject.getFriction(),tempObject.getRestitution(),tempObject.getWidth(),tempObject.getHeight(),((EndObject) tempObject).getAnimationSpeed());
							endObjectTest.setStatic(new Vector2(endObjectTest.getPosition().get(0)));
							endObjectTest.getBody().get(0).setType(BodyType.StaticBody);
							gameObjectArray.add(endObjectTest);
							endObjectplayer=endObjectTest.getPlayer();
								
								

							}
							else if(tempObject instanceof FixedObject){
								//delete
								for(int i=0;i<gameObjectArray.size();i++){
									if(gameObjectArray.get(i).equals(tempObject)){
										gameObjectArray.remove(i);
									}
								}
								for(int i=0;i<tempObject.getBasicObject().size();i++){
								world.destroyBody(tempObject.getBasicObject().get(i).getBody().get(0));
								}
								//create
								fixedshapeTest = new FixedObject(world,((FixedObject) tempObject).getType(),((FixedObject) tempObject).getImg(),tempObject.getPosition().get(0).x,tempObject.getPosition().get(0).y);
								fixedshapeTest.jointInfo[0]=0+"";
						
							if(tempObject.getBasicObject().get(0).getFixtureDef().shape instanceof PolygonShape){
								fixedshapeTest.setProperties(tempObject.getDensity(),tempObject.getFriction(),tempObject.getRestitution(),tempObject.getWidth(),tempObject.getHeight(),((FixedObject) tempObject).isStatic(),((FixedObject) tempObject).isRotate(),((FixedObject) tempObject).getIsCollision());

							}
								else{
									fixedshapeTest.setProperties(tempObject.getDensity(),tempObject.getFriction(),tempObject.getRestitution(),tempObject.getWidth(),((FixedObject) tempObject).isStatic(),((FixedObject) tempObject).isRotate(),((FixedObject) tempObject).getIsCollision());
									//((fixedshapeTest) basicObjectTest).setRadius(root.get(i).getFloat("width")-1);
								}
							fixedshapeTest.setStatic(new Vector2(fixedshapeTest.getPosition().get(0)));
							fixedshapeTest.getBody().get(0).setType(BodyType.StaticBody);
							gameObjectArray.add(fixedshapeTest);
							}
							else if(tempObject instanceof ImponderableShape){
								//delete
								for(int i=0;i<gameObjectArray.size();i++){
									if(gameObjectArray.get(i).equals(tempObject)){
										gameObjectArray.remove(i);
										
									}
								}
								for(int i=0;i<tempObject.getBasicObject().size();i++){
									world.destroyBody(tempObject.getBasicObject().get(i).getBody().get(0));
									}
								//create
								imponderableShapeTest= new ImponderableShape(world,((ImponderableShape) tempObject).getType(),((ImponderableShape) tempObject).getImg(),tempObject.getPosition().get(0).x,tempObject.getPosition().get(0).y);
								imponderableShapeTest.jointInfo[0]=0+"";
							
							imponderableShapeTest.setProperties(tempObject.getDensity(),tempObject.getFriction(),tempObject.getRestitution(), ((ImponderableShape) tempObject).getmaxMotorTorque(), ((ImponderableShape) tempObject).getMotorSpeed(),tempObject.getWidth(),tempObject.getHeight(),((ImponderableShape) tempObject).isTakeSound());
							imponderableShapeTest.setStatic(new Vector2(imponderableShapeTest.getPosition().get(0)));
							gameObjectArray.add(imponderableShapeTest);
							}
							else if(tempObject instanceof StartObject){
								//delete
								for(int i=0;i<gameObjectArray.size();i++){
									if(gameObjectArray.get(i).equals(startObjectTest)){
										gameObjectArray.remove(startObjectTest);
										for(int j=0;j<startObjectTest.getBasicObject().size();j++){
											world.destroyBody(startObjectTest.getBasicObject().get(j).getBody().get(0));
											}
										startObjectplayer.deleteTexture();
										startObjectplayer=null;
									}
								}
								//create
								startObjectTest=new StartObject(world,((StartObject) tempObject).getType(),tempObject.getPosition().get(0).x,tempObject.getPosition().get(0).y);
								startObjectTest.jointInfo[0]=0+"";
							
							startObjectTest.setProperties(tempObject.getDensity(),tempObject.getFriction(),tempObject.getRestitution(),tempObject.getWidth(),tempObject.getHeight(),((StartObject) tempObject).getMovementSpeed(),((StartObject) tempObject).getLifeCount(),((StartObject) tempObject).isControlMovement());
							startObjectTest.getBody().get(0).setType(BodyType.StaticBody);
							gameObjectArray.add(startObjectTest);
							startObjectplayer=startObjectTest.getPlayer();
							}
							else if(tempObject instanceof Triangle){
								//delete
								for(int i=0;i<gameObjectArray.size();i++){
									if(gameObjectArray.get(i).equals(tempObject)){
										gameObjectArray.remove(i);
									}
								}
								for(int i=0;i<tempObject.getBasicObject().size();i++){
								world.destroyBody(tempObject.getBasicObject().get(i).getBody().get(0));
								}
								//create
								triangle = new Triangle(world,((Triangle) tempObject).getType(),((Triangle) tempObject).getImg(),tempObject.getPosition().get(0).x,tempObject.getPosition().get(0).y);
								triangle.jointInfo[0]=0+"";
							
							triangle.setProperties(tempObject.getDensity(),tempObject.getFriction(),tempObject.getRestitution(),((Triangle) tempObject).getAngularVelocity(),((Triangle) tempObject).isStatic(),((Triangle) tempObject).isRotate(),((Triangle) tempObject).getIsCollision());
							triangle.setStatic(new Vector2(triangle.getPosition().get(0)));
							gameObjectArray.add(triangle);
								
							}
							else if(tempObject instanceof Vehicle){
								for(int i=0;i<gameObjectArray.size();i++){
									if(gameObjectArray.get(i).equals(tempObject)){
										gameObjectArray.remove(i);
									}
								}
								for(int i=0;i<tempObject.getBasicObject().size();i++){
									world.destroyBody(tempObject.getBasicObject().get(i).getBody().get(0));
									}
								carTest = new Vehicle(world,((Vehicle) tempObject).getType(),((Vehicle) tempObject).getImg(),((Vehicle) tempObject).getWheelimg(),tempObject.getPosition().get(0).x,tempObject.getPosition().get(0).y);
								carTest.jointInfo[0]=0+"";
							
							carTest.setProperties(tempObject.getDensity(),tempObject.getFriction(),tempObject.getRestitution(),((Vehicle) tempObject).getMotorSpeed(),tempObject.getWidth(),tempObject.getHeight(),((Vehicle)tempObject).isTakeSound(),((Vehicle)tempObject).isControlMovement());
							carTest.setStatic(new Vector2(carTest.getPosition().get(0)));
							gameObjectArray.add(carTest);
									
							}
							else if(tempObject instanceof WindMill){
								//delete
								for(int i=0;i<gameObjectArray.size();i++){
									if(gameObjectArray.get(i).equals(tempObject)){
										gameObjectArray.remove(i);
									}
								}
								for(int i=0;i<tempObject.getBasicObject().size();i++){
									world.destroyBody(tempObject.getBasicObject().get(i).getBody().get(0));
									}
								//create
								windMillTest = new WindMill(world,((WindMill) tempObject).getType(),tempObject.getPosition().get(0).x,tempObject.getPosition().get(0).y,((WindMill) tempObject).getImg());
								windMillTest.jointInfo[0]=0+"";
								windMillTest.setProperties(tempObject.getDensity(),tempObject.getFriction(),tempObject.getRestitution(),((WindMill) tempObject).getAngularVelocity(),tempObject.getWidth(),tempObject.getHeight(),((WindMill)tempObject).isTakeSound());
								windMillTest.setStatic(new Vector2(windMillTest.getPosition().get(0)));
								gameObjectArray.add(windMillTest);
									
								
							}
							
							
						}
					}
				});
				backButton.addListener(new ClickListener(){
					@Override
					public void clicked(InputEvent event, float x, float y) {
						gsm.setState(GameStateManager.LEVELS,0,0);
					}
				});
				creationTable.add(opjectButton).width(basicTable.getWidth()/30f).height(basicTable.getWidth()/30f).pad(2);
				creationTable.add(changeStateButton).width(basicTable.getWidth()/30f).height(basicTable.getWidth()/30f).pad(2);
				creationTable.row();
				creationTable.add(createjointButton).width(basicTable.getWidth()/30f).height(basicTable.getWidth()/30f).pad(2);
				creationTable.add(deletejointButton).width(basicTable.getWidth()/30f).height(basicTable.getWidth()/30f).pad(2);
				creationTable.row();
				creationTable.add(backButton).width(basicTable.getWidth()/30f).height(basicTable.getWidth()/30f).colspan(3).pad(2);
			
			}	
			
				QueryCallback queryCallback=new QueryCallback() {
					@Override
					public boolean reportFixture(Fixture fixture) {
						if(!fixture.testPoint(touchDownPosition.x, touchDownPosition.y))
							return false;
						mouseJointDef.bodyB=fixture.getBody();
						mouseJointDef.target.set(touchDownPosition.x, touchDownPosition.y);
						mouseJoint=(MouseJoint) world.createJoint(mouseJointDef);
						if(getPossition){
							if(toggle){//Position of the first object 
								tempPossition1.x=touchDownPosition.x;
								tempPossition1.y=touchDownPosition.y;
								jointPossition1Txt.setText("X: "+touchDownPosition.x+" Y: "+touchDownPosition.y);
							toggle =false;
							}
							else{//Position of the second object 
								tempPossition2.x=touchDownPosition.x;
								tempPossition2.y=touchDownPosition.y;
								jointPossition2Txt.setText("X: "+touchDownPosition.x+" Y: "+touchDownPosition.y);
								toggle=true;
							}
						}
						if(!AppVar.testPlay){
							for(int i=0;i<gameObjectArray.size();i++){
								for(int y=0;y<gameObjectArray.get(i).getPosition().size();y++){
									if(fixture.getBody().getPosition().equals(gameObjectArray.get(i).getPosition().get(y))){
										tempObject=gameObjectArray.get(i);//the selected object 
									}
								}
							} 
						}
						return false;
					}
				};
				 public void changeBody(boolean play){
				    	//make body dynamic
				    	if(play){
				    		handlingCollision();
				    		objectPosition.clear();
				    		if(startObjectTest!=null){startObjectTest.getBody().get(0).setType(BodyType.DynamicBody);}
				    		for(int i=0;i<gameObjectArray.size();i++){
				    			objectPosition.add(new Vector2(gameObjectArray.get(i).getPosition().get(0)));
				    			if(gameObjectArray.get(i)instanceof FixedObject){
				    				if(!((FixedObject)gameObjectArray.get(i)).isStatic()){
				    					gameObjectArray.get(i).getBody().get(0).setType(BodyType.DynamicBody);
				    				}
				    				
				    			}
				    			if(gameObjectArray.get(i)instanceof DangerousObject){dangerousObjectTest.getBody().get(0).setType(BodyType.DynamicBody);}
				    			
				    			gameObjectArray.get(i).setDynamic(gameObjectArray.get(i).getPosition().get(0));
				    		
				    		}
				    	}
				    	//make body static 
				    	else{
				    		if(startObjectTest!=null){startObjectTest.getBody().get(0).setType(BodyType.StaticBody);}
				    	
				    		world.setContactListener(null);
				    		for(int i=0;i<gameObjectArray.size();i++){
				    			gameObjectArray.get(i).setStatic((Vector2) objectPosition.get(i));
				    			if(gameObjectArray.get(i)instanceof FixedObject){
				    					gameObjectArray.get(i).getBody().get(0).setType(BodyType.StaticBody);
				    			}
				    			if(gameObjectArray.get(i)instanceof DangerousObject){dangerousObjectTest.getBody().get(0).setType(BodyType.StaticBody);}
				    			if(endObjectTest!=null){endObjectTest.getBody().get(0).setType(BodyType.StaticBody);}
				    			
				    		}
				    		
				    	}
					} 

	@Override
	public void update(float dt) {
		 //clear screen
		  Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); 
		  //draw box2d world
	      world.step(AppVar.BOX_STEP,AppVar.BOX_VELOCITY_ITERATIONS,AppVar.BOX_POSITION_ITERATIONS);
		  camera.update();
		  //raneen
		  debugRenderer.render(world, camera.combined);  
		  stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		  stage.draw();
		  if(!AppVar.testPlay){
		      for(int i=0;i<gameObjectArray.size();i++){
		    	  for(int y=0;y<gameObjectArray.get(i).getBody().size();y++){
		    	  gameObjectArray.get(i).getBody().get(y).setLinearVelocity(new Vector2(0,.0001f));
		    	  //raneen
		    	  if(gameObjectArray.get(i) instanceof Cannon){
		    		  if(((Cannon) gameObjectArray.get(i)).getType()==AppVar.cannonType.FLAMCANNON||((Cannon) gameObjectArray.get(i)).getType()==AppVar.cannonType.DRAGON){
			    		  ((Cannon) gameObjectArray.get(i)). eraseFlame(cannonObjectplayer);
			    		  for(int ii=0;ii<cannonObjectplayer.size();ii++){
							  cannonObjectplayer.get(ii).update(ANIMATIONSTEP);
							  cannonObjectplayer.get(ii).render(batch);	
					      }
			    	  }
		    		  else if(((Cannon) gameObjectArray.get(i)).getType()==AppVar.cannonType.CANOON||((Cannon) gameObjectArray.get(i)).getType()==AppVar.cannonType.RIGHTCANOON
		    				  ||((Cannon) gameObjectArray.get(i)).getType()==AppVar.cannonType.SCANNON||((Cannon) gameObjectArray.get(i)).getType()==AppVar.cannonType.GUN||((Cannon) gameObjectArray.get(i)).getType()==AppVar.cannonType.CROSS
		    				  ||((Cannon) gameObjectArray.get(i)).getType()==AppVar.cannonType.CLYNDER ||((Cannon) gameObjectArray.get(i)).getType()==AppVar.cannonType.CLYNDER2){
		    			  ((Cannon) gameObjectArray.get(i)).eraseBall();
		    		  }
			    	 
			      }
		    	  }
		       } 
		    //for cannon
		      for(int ii=0;ii<cannonObjectplayer.size();ii++){
				  cannonObjectplayer.get(ii).update(ANIMATIONSTEP);
				  cannonObjectplayer.get(ii).render(batch);	
		      }
        }
	      else{
	    	 // world .setContactListener(null);//deactivate collision listener in play state
	    	  
	    	  for(int i=0;i<gameObjectArray.size();i++){
	    		 
		    		  if(gameObjectArray.get(i) instanceof BasicObject){
		    			  gameObjectArray.get(i).getBody().get(0).applyForceToCenter(((BasicObject) gameObjectArray.get(i)).getAngularVelocityX(),((BasicObject) gameObjectArray.get(i)).getAngularVelocity(), true);
		    				  //((BasicObject) gameObjectArray.get(i)).applyForceToCenter(70,400, true);(((BasicObject) gameObjectArray.get(i)).getAngularVelocity());	  
		    		  }
		    		  if(gameObjectArray.get(i) instanceof Cannon){
		    			  if(((Cannon) gameObjectArray.get(i)).getType()==AppVar.cannonType.FLAMCANNON||((Cannon) gameObjectArray.get(i)).getType()==AppVar.cannonType.DRAGON){
		    				  ((Cannon) gameObjectArray.get(i)).updateFlame(((Cannon) gameObjectArray.get(i)).getPosition().get(0).x,((Cannon) gameObjectArray.get(i)).getPosition().get(0).y,cannonObjectplayer,((Cannon) gameObjectArray.get(i)).getType());
		    				  ((Cannon) gameObjectArray.get(i)). eraseFlame( cannonObjectplayer);
		    				  for(int ii=0;ii<cannonObjectplayer.size();ii++){
			    				  cannonObjectplayer.get(ii).update(ANIMATIONSTEP);
			    				  cannonObjectplayer.get(ii).render(batch);	
			    		      }
		    			  }
		    			  else if(((Cannon) gameObjectArray.get(i)).getType()==AppVar.cannonType.CANOON|| ((Cannon) gameObjectArray.get(i)).getType()==AppVar.cannonType.RIGHTCANOON||((Cannon) gameObjectArray.get(i)).getType()==AppVar.cannonType.SCANNON
		    					 || ((Cannon) gameObjectArray.get(i)).getType()==AppVar.cannonType.GUN||((Cannon) gameObjectArray.get(i)).getType()==AppVar.cannonType.CROSS ||
		    					  ((Cannon) gameObjectArray.get(i)).getType()==AppVar.cannonType.CLYNDER||((Cannon) gameObjectArray.get(i)).getType()==AppVar.cannonType.CLYNDER2){
		    				  ((Cannon) gameObjectArray.get(i)).updateBall(((Cannon) gameObjectArray.get(i)).getPosition().get(0).x,((Cannon) gameObjectArray.get(i)).getPosition().get(0).y,((Cannon) gameObjectArray.get(i)).getType());
		    				  ((Cannon) gameObjectArray.get(i)).eraseBall();
		    			  }
		    		  }			
	    	  }
	      }
		  if(done){
			  for(int i=0;i<startObjectTest.getBasicObject().size();i++){
			  startObjectTest.getBasicObject().get(0).changeBodyType(BodyType.StaticBody);
			  animObjectTest=new AnimationObject(world, animationObject.LIGHT1, -2.5f, 6);
				animObjectTest.jointInfo[0]=0+"";
				gameObjectArray.add(animObjectTest);
				animationObjectplayer.add(animObjectTest.getPlayer());
				animObjectTest=new AnimationObject(world, animationObject.LIGHT2, -2.5f, 6);
				animObjectTest.jointInfo[0]=0+"";
				gameObjectArray.add(animObjectTest);
				animationObjectplayer.add(animObjectTest.getPlayer());
				 animObjectTest=new AnimationObject(world, animationObject.LIGHT1, 2.5f, 6);
					animObjectTest.jointInfo[0]=0+"";
					gameObjectArray.add(animObjectTest);
					animationObjectplayer.add(animObjectTest.getPlayer());
					animObjectTest=new AnimationObject(world, animationObject.LIGHT2, 2.5f, 6);
					animObjectTest.jointInfo[0]=0+"";
					gameObjectArray.add(animObjectTest);
					animationObjectplayer.add(animObjectTest.getPlayer());
			  }
			  done=false;
		  }
	      if(startObjectplayer != null){
		      startObjectplayer.update(ANIMATIONSTEP);
		      startObjectplayer.render(batch);	
		    }
	      if(endObjectplayer != null){
	    	  endObjectplayer.update(ANIMATIONSTEP);
	    	  endObjectplayer.render(batch);	
	      }
	      for(int i=0;i<animationObjectplayer.size();i++){
	    	  animationObjectplayer.get(i).update(ANIMATIONSTEP);
	    	  animationObjectplayer.get(i).render(batch);	
	      }
	      for(int i=0;i<dangerousObjectPlayer.size();i++){
	    	  dangerousObjectPlayer.get(i).update(ANIMATIONSTEP);
	    	  dangerousObjectPlayer.get(i).render(batch);	
	      }
	      //raneen
		  batch.setProjectionMatrix(camera.combined);
	      batch.begin();
	      //draw images
		  world.getBodies(tmpBodies);
		 for(Body body : tmpBodies)
	    	if(body.getUserData() != null &&body.getUserData() instanceof Sprite) {
					Sprite sprite = (Sprite) body.getUserData();
					sprite.setPosition(body.getPosition().x - sprite.getWidth() / 2, body.getPosition().y - sprite.getHeight() / 2);
					sprite.setRotation(body.getAngle() * MathUtils.radiansToDegrees);
					sprite.draw(batch);
	    	}
		  batch.end();	
		  if(flag_retry){
			  changeBody(false);
			  AppVar.testPlay=!AppVar.testPlay;
			  flag_retry=false;
		  }
		  if(lifeCount==0&&burnt){
			  for(int i=0;i<startObjectTest.getBasicObject().size();i++){
			  startObjectTest.getBasicObject().get(i).changeBodyType(BodyType.StaticBody);
			  }
			  animObjectTest=new AnimationObject(world, animationObject.BURNTNEWTON, -2f, 6);
				animObjectTest.jointInfo[0]=0+"";
				gameObjectArray.add(animObjectTest);
				animationObjectplayer.add(animObjectTest.getPlayer());
				 animObjectTest=new AnimationObject(world, animationObject.BURNTNEWTON, 1, 7f);
					animObjectTest.jointInfo[0]=0+"";
					gameObjectArray.add(animObjectTest);
					animationObjectplayer.add(animObjectTest.getPlayer());
					 animObjectTest=new AnimationObject(world, animationObject.BURNTNEWTON, 1, 6);
						animObjectTest.jointInfo[0]=0+"";
						gameObjectArray.add(animObjectTest);
						animationObjectplayer.add(animObjectTest.getPlayer());
				burnt=false;
		  }
		  if(disappearFlag){
			  Body b1= startObjectTest.getBasicObject().get(0).getBody().get(0);
			  AppVar.animationObject type = startObjectTest.getType();
		//	  for(int i=0;i<gameObjectArray.size();i++){
		//		  if(gameObjectArray.get(i) instanceof Disappear){
					  if(startObjectTest.getBasicObject().size() ==1){
						  b1.setTransform(testDis.getShape2().getPosition().get(0).x
								  ,testDis.getShape2().getPosition().get(0).y+1,0);
					  }
					  else{
						  switch (type) {
						  case BUNNY1:
							case HANDSOMEBOY1:
							case HERO1:
							case MAN1:
							case MUSHROOM1:
							case NEWTON:
							case CAT1:
							case DEER2:
							case PIGGY1:
								 b1.setTransform(testDis.getShape2().getPosition().get(0).x
										  ,testDis.getShape2().getPosition().get(0).y,.5f); 
							break;
							case NEWTON2:
							case BUNNY2:
							case HANDSOMEBOY2:
							case HERO2:
							case MAN2:
							case MUSHROOM2:
							case CAT2:
							case DEER1:
							case PIGGY2:
								b1.setTransform(testDis.getShape2().getPosition().get(0).x
										  ,testDis.getShape2().getPosition().get(0).y,-.5f); 
								break;
						default:
							break;
						}
						  Body b2= startObjectTest.getBasicObject().get(1).getBody().get(0);
						  Body b3= startObjectTest.getBasicObject().get(2).getBody().get(0);
						  b2.setTransform(testDis.getShape2().getPosition().get(0).x
								  ,testDis.getShape2().getPosition().get(0).y+1,.5f); 
						  b3.setTransform(testDis.getShape2().getPosition().get(0).x
								  ,testDis.getShape2().getPosition().get(0).y+1,.5f); 
					  }
			//	  }
			//  }
		  }
		  disappearFlag=false;
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handlingInput() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
	private NinePatch getNinePatch(String fname) {
		// Get the image
		final Texture t = new Texture(Gdx.files.internal(fname));
		// create a new texture region, otherwise black pixels will show up too, we are simply cropping the image
		// last 4 numbers respresent the length of how much can each corner can draw,
		// for example if your image is 50px and you set the numbers 50, your whole image will be drawn in each corner
		// so what number should be good?, well a little less than half would be nice
		    return new NinePatch( new TextureRegion(t, 1, 1 , t.getWidth() - 2, t.getWidth() - 2), 10, 10, 10, 10);
		}

public void createObject(boolean firstTime){
	 listObjects = "";
	int count=1;
	String jsonText=file.readString();
	String game[]=jsonText.split("///");
	//System.out.println(game[currentLevelGame]);
	  root = new JsonReader().parse(game[currentLevelGame]);
	if(root.size()>0){
	gameimg=root.get(root.size()-1).getString("background");
	levelDegree=root.get(root.size()-2).getString("levelDegree");
	}
	
	for(int i=0;i<root.size();i++){
		if(root.get(i).has("possessionX")){posx=root.get(i).getFloat("possessionX");}
		else{posx=0;}
		if(root.get(i).has("possessionY")){posy=root.get(i).getFloat("possessionY");}
		else{posy=0;}
			
		switch (root.get(i).getString("classType")) {
		case "BasicObject":
			boolean setStatic=false;
			if(root.get(i).has("setStatic")){setStatic=true;}
			boolean stopRotation=false;
			if(root.get(i).has("stopRotation")){stopRotation=true;}
			boolean takeSound=false;
			if(root.get(i).has("takeSound")){takeSound=true;}
			if((root.get(i).has("secondJoint")||root.get(i).has("numberOfJoint"))&&firstTime){
				basicObjectTest = new BasicObject(world, BodyType.DynamicBody, AppVar.basicBodyType.valueOf(root.get(i).getString("appVarType")),
						root.get(i).getString("img"),posx,posy);
				if(root.get(i).has("numberOfJoint")){
					//System.out.println("hh");
					basicObjectTest.jointInfo[0]=(root.get(i).getInt("numberOfJoint"))+"";
					basicObjectTest.jointInfo[1]=root.get(i).getString("typeOfJoint");
					basicObjectTest.jointInfo[2]=root.get(i).getString("parameterOfjoint");
				}
				else{
					basicObjectTest.jointInfo[0]=0+"";
				}
			
			
			if(root.get(i).getString("basicType").equals("PolygonShape")){
				//add false
			basicObjectTest.setProperties(root.get(i).getFloat("density")-1,root.get(i).getFloat("friction")-1,root.get(i).getFloat("restitution")-1,root.get(i).getFloat("velocityX")-1,root.get(i).getFloat("velocityY")-1,root.get(i).getFloat("width")-1,root.get(i).getFloat("height")-1,setStatic,stopRotation,root.get(i).getInt("collision")-1,takeSound);
			}
			else{
				basicObjectTest.setProperties(root.get(i).getFloat("density")-1,root.get(i).getFloat("friction")-1,root.get(i).getFloat("restitution")-1,root.get(i).getFloat("velocityX")-1,root.get(i).getFloat("velocityY")-1,setStatic,stopRotation,root.get(i).getInt("collision")-1,takeSound);
				((BasicObject) basicObjectTest).setRadius(root.get(i).getFloat("width")-1);
			}
			basicObjectTest.setStatic(new Vector2(basicObjectTest.getPosition().get(0)));
			System.out.println(basicObjectTest.getAngularVelocityX());
			System.out.println(basicObjectTest.getAngularVelocity());
			gameObjectArray.add(basicObjectTest);
			}
			else if (firstTime){
				listObjects+=count+"-"+root.get(i).getString("classType")+",";
				index.add(i);
				count++;
			}
			break;
		case"Cannon":
			boolean takeSoundcannon=false;
			if(root.get(i).has("takeSound")){takeSoundcannon=true;}
			if((root.get(i).has("secondJoint")||root.get(i).has("numberOfJoint"))&&firstTime){
			cannonTest = new Cannon(world, AppVar.cannonType.valueOf(root.get(i).getString("appVarType")), root.get(i).getString("img"),posx,posy);
			if(root.get(i).has("numberOfJoint")){
				cannonTest.jointInfo[0]=(root.get(i).getInt("numberOfJoint"))+"";
				cannonTest.jointInfo[1]=root.get(i).getString("typeOfJoint");
				cannonTest.jointInfo[2]=root.get(i).getString("parameterOfjoint");
			}
			else{
				cannonTest.jointInfo[0]=0+"";
			}
			
			cannonTest.setProperties(root.get(i).getFloat("density")-1,root.get(i).getFloat("friction")-1,root.get(i).getFloat("restitution")-1,root.get(i).getInt("ballNumber")-1 ,takeSoundcannon);
			cannonTest.setStatic(new Vector2(cannonTest.getPosition().get(0)));
			gameObjectArray.add(cannonTest);
			}
            else if (firstTime){
            	listObjects+=count+"-"+root.get(i).getString("classType")+",";
            	index.add(i);
            	count++;
			}
			break;
		case"Chain":
			if((root.get(i).has("secondJoint")||root.get(i).has("numberOfJoint"))&&firstTime){
			chainTest= new Chain(world,AppVar.chainType.valueOf(root.get(i).getString("appVarType")),root.get(i).getString("img"),posx,posy);
			if(root.get(i).has("numberOfJoint")){
				chainTest.jointInfo[0]=(root.get(i).getInt("numberOfJoint"))+"";
				chainTest.jointInfo[1]=root.get(i).getString("typeOfJoint");
				chainTest.jointInfo[2]=root.get(i).getString("parameterOfjoint");
			}
			else{
				chainTest.jointInfo[0]=0+"";
			}
			
			chainTest.setProperties(root.get(i).getFloat("density")-1,root.get(i).getFloat("friction")-1,root.get(i).getFloat("restitution")-1);
			chainTest.setStatic(new Vector2(chainTest.getPosition().get(0)));
			gameObjectArray.add(chainTest);
			}
           else if (firstTime){
        	   listObjects+=count+"-"+root.get(i).getString("classType")+",";
        	   index.add(i);
        	   count++;
			}
			break;
		case"Disappear":
			if((root.get(i).has("secondJoint")||root.get(i).has("numberOfJoint"))&&firstTime){
			disappearTest= new Disappear(world,AppVar.disappear.valueOf(root.get(i).getString("appVarType")),root.get(i).getString("img"),posx,posy);
			if(root.get(i).has("numberOfJoint")){
				disappearTest.jointInfo[0]=(root.get(i).getInt("numberOfJoint"))+"";
				disappearTest.jointInfo[1]=root.get(i).getString("typeOfJoint");
				disappearTest.jointInfo[2]=root.get(i).getString("parameterOfjoint");
			}
			else{
				disappearTest.jointInfo[0]=0+"";
			}
		
			disappearTest.setProperties(root.get(i).getFloat("density")-1,root.get(i).getFloat("friction")-1,root.get(i).getFloat("restitution")-1,root.get(i).getFloat("width")-1,root.get(i).getFloat("height")-1);
			disappearTest.setStatic(new Vector2(disappearTest.getPosition().get(0)));
			gameObjectArray.add(disappearTest);
			}
			else if (firstTime){
				listObjects+=count+"-"+root.get(i).getString("classType")+",";
				index.add(i);
				count++;
			}
			break;	
		case"Elevator":
			if((root.get(i).has("secondJoint")||root.get(i).has("numberOfJoint"))&&firstTime){
			elevoterTest= new Elevator(world,AppVar.elevatorType.valueOf(root.get(i).getString("appVarType")),root.get(i).getString("img"),posx,posy);
			if(root.get(i).has("numberOfJoint")){
				elevoterTest.jointInfo[0]=(root.get(i).getInt("numberOfJoint"))+"";
				elevoterTest.jointInfo[1]=root.get(i).getString("typeOfJoint");
				elevoterTest.jointInfo[2]=root.get(i).getString("parameterOfjoint");
			}
			else{
				elevoterTest.jointInfo[0]=0+"";
			}
			
			elevoterTest.setProperties(root.get(i).getFloat("density")-1,root.get(i).getFloat("friction")-1,root.get(i).getFloat("restitution")-1,root.get(i).getFloat("width")-1,root.get(i).getFloat("height")-1,root.get(i).getFloat("motorSpeed")-1,root.get(i).getFloat("motorForce")-1);
			elevoterTest.setStatic(new Vector2(elevoterTest.getPosition().get(0)));
			gameObjectArray.add(elevoterTest);
			}
			else if (firstTime){
				listObjects+=count+"-"+root.get(i).getString("classType")+",";
				index.add(i);
				count++;
			}
			break;
		case"FixedObject":
			boolean setStatic1=false;
			if(root.get(i).has("setStatic")){setStatic1=true;}
			boolean stopRotation1=false;
			if(root.get(i).has("stopRotation")){stopRotation1=true;}
			if(root.get(i).has("secondJoint")||root.get(i).has("numberOfJoint")){
			fixedshapeTest = new FixedObject(world,fixedType.valueOf(root.get(i).getString("appVarType")),root.get(i).getString("img"),posx,posy);
			if(root.get(i).has("numberOfJoint")){
				fixedshapeTest.jointInfo[0]=(root.get(i).getInt("numberOfJoint"))+"";
				fixedshapeTest.jointInfo[1]=root.get(i).getString("typeOfJoint");
				fixedshapeTest.jointInfo[2]=root.get(i).getString("parameterOfjoint");
			}
			else{
				fixedshapeTest.jointInfo[0]=0+"";
			}
			}
			else{
				fixedshapeTest = new FixedObject(world,fixedType.valueOf(root.get(i).getString("appVarType")),root.get(i).getString("img"),posx,posy);
				fixedshapeTest.jointInfo[0]=0+"";
			}
			if(root.get(i).getString("basicType").equals("PolygonShape")){
				fixedshapeTest.setProperties(root.get(i).getFloat("density")-1,root.get(i).getFloat("friction")-1,root.get(i).getFloat("restitution")-1,root.get(i).getFloat("width")-1,root.get(i).getFloat("height")-1,setStatic1,stopRotation1,root.get(i).getInt("collision")-1);

			}
				else{
					fixedshapeTest.setProperties(root.get(i).getFloat("density")-1,root.get(i).getFloat("friction")-1,root.get(i).getFloat("restitution")-1,root.get(i).getFloat("width")-1,setStatic1,stopRotation1,root.get(i).getInt("collision")-1);
					//((fixedshapeTest) basicObjectTest).setRadius(root.get(i).getFloat("width")-1);
				}
			fixedshapeTest.setStatic(new Vector2(fixedshapeTest.getPosition().get(0)));
			fixedshapeTest.getBody().get(0).setType(BodyType.StaticBody);
			gameObjectArray.add(fixedshapeTest);
			
		//	System.out.println(root.get(i).getString("classType"));
			
			break;
		case"ImponderableShape":
			boolean takeSoundimp=false;
			if(root.get(i).has("takeSound")){takeSoundimp=true;}
			if((root.get(i).has("secondJoint")||root.get(i).has("numberOfJoint"))&&firstTime){
			imponderableShapeTest= new ImponderableShape(world,imponderableType.valueOf(root.get(i).getString("appVarType")),root.get(i).getString("img"),posx,posy);
			if(root.get(i).has("numberOfJoint")){
				imponderableShapeTest.jointInfo[0]=(root.get(i).getInt("numberOfJoint"))+"";
				imponderableShapeTest.jointInfo[1]=root.get(i).getString("typeOfJoint");
				imponderableShapeTest.jointInfo[2]=root.get(i).getString("parameterOfjoint");
			}
			else{
				imponderableShapeTest.jointInfo[0]=0+"";
			}
			
			imponderableShapeTest.setProperties(root.get(i).getFloat("density")-1,root.get(i).getFloat("friction")-1,root.get(i).getFloat("restitution")-1, root.get(i).getFloat("motorTorque")-1, root.get(i).getFloat("motorSpeed")-1,root.get(i).getFloat("width")-1,root.get(i).getFloat("height")-1, takeSoundimp);
			imponderableShapeTest.setStatic(new Vector2(imponderableShapeTest.getPosition().get(0)));
			gameObjectArray.add(imponderableShapeTest);
			}
			else if (firstTime){
				listObjects+=count+"-"+root.get(i).getString("classType")+",";
				index.add(i);
				count++;
			}
			break;
		case"Triangle":
			boolean setStatic2=false;
			if(root.get(i).has("setStatic")){setStatic2=true;}
			boolean stopRotation2=false;
			if(root.get(i).has("stopRotation")){stopRotation2=true;}
			if((root.get(i).has("secondJoint")||root.get(i).has("numberOfJoint"))&&firstTime){
				System.out.println("yeeeeees");
			triangle = new Triangle(world,TriangleType.valueOf(root.get(i).getString("appVarType")), root.get(i).getString("img"), posx,posy);
			if(root.get(i).has("numberOfJoint")){
				triangle.jointInfo[0]=(root.get(i).getInt("numberOfJoint"))+"";
				triangle.jointInfo[1]=root.get(i).getString("typeOfJoint");
				triangle.jointInfo[2]=root.get(i).getString("parameterOfjoint");
			}
			else{
				triangle.jointInfo[0]=0+"";
			}
			
			triangle.setProperties(root.get(i).getFloat("density")-1,root.get(i).getFloat("friction")-1,root.get(i).getFloat("restitution")-1,root.get(i).getFloat("angularVelocity")-1,setStatic2,stopRotation2,root.get(i).getInt("collision")-1);
			triangle.setStatic(new Vector2(triangle.getPosition().get(0)));
			gameObjectArray.add(triangle);
			}
			else if (firstTime){
				listObjects+=count+"-"+root.get(i).getString("classType")+",";
				index.add(i);
				count++;
			}
			break;
		case"Vehicle":
			boolean takeSoundveh=false;
			if(root.get(i).has("takeSound")){takeSoundveh=true;}
			boolean controlMove=false;
			if(root.get(i).has("controlMovement")){controlMove=true;}
			if((root.get(i).has("secondJoint")||root.get(i).has("numberOfJoint"))&&firstTime){
			carTest = new Vehicle(world,vehicleType.valueOf(root.get(i).getString("appVarType")),root.get(i).getString("img") , root.get(i).getString("wheelImg"), posx,posy);
			if(root.get(i).has("numberOfJoint")){
				carTest.jointInfo[0]=(root.get(i).getInt("numberOfJoint"))+"";
				carTest.jointInfo[1]=root.get(i).getString("typeOfJoint");
				carTest.jointInfo[2]=root.get(i).getString("parameterOfjoint");
			}
			else{
				carTest.jointInfo[0]=0+"";
			}
			
			carTest.setProperties(root.get(i).getFloat("density")-1,root.get(i).getFloat("friction")-1,root.get(i).getFloat("restitution")-1, root.get(i).getFloat("motorSpeed")-1,root.get(i).getFloat("width")-1,root.get(i).getFloat("height")-1,takeSoundveh,controlMove);
			carTest.setStatic(new Vector2(carTest.getPosition().get(0)));
			gameObjectArray.add(carTest);
			}
			else if (firstTime){
				listObjects+=count+"-"+root.get(i).getString("classType")+",";
				index.add(i);
				count++;
			}
			break;
		case"WindMill":
			boolean takeSoundwind=false;
			if(root.get(i).has("takeSound")){takeSoundwind=true;}
			if((root.get(i).has("secondJoint")||root.get(i).has("numberOfJoint"))&&firstTime){
				//System.out.println("you have 2 ");
				windMillTest = new WindMill(world, windMillType.valueOf(root.get(i).getString("appVarType")),posx,posy,root.get(i).getString("img"));
				if(root.get(i).has("numberOfJoint")){
					windMillTest.jointInfo[0]=(root.get(i).getInt("numberOfJoint"))+"";
					windMillTest.jointInfo[1]=root.get(i).getString("typeOfJoint");
					windMillTest.jointInfo[2]=root.get(i).getString("parameterOfjoint");
				}
				else{
					windMillTest.jointInfo[0]=0+"";
					//System.out.println("you have 1 ");
				}
			
			
			windMillTest.setProperties(root.get(i).getFloat("density")-1,root.get(i).getFloat("friction")-1,root.get(i).getFloat("restitution")-1,windMillTest.getAngularVelocity(),root.get(i).getFloat("width")-1,root.get(i).getFloat("height")-1,takeSoundwind);
			windMillTest.setStatic(new Vector2(windMillTest.getPosition().get(0)));
			gameObjectArray.add(windMillTest);
			}
			else if (firstTime){
				listObjects+=count+"-"+root.get(i).getString("classType")+",";
				index.add(i);
				count++;
			}
			
			break;
		case"StartObject":
			boolean startControlMove=false;
			if(root.get(i).has("controlMovement")){startControlMove=true;}
			if(root.get(i).has("secondJoint")||root.get(i).has("numberOfJoint")){
			startObjectTest=new StartObject(world,  animationObject.valueOf(root.get(i).getString("appVarType")), posx,posy);
			if(root.get(i).has("numberOfJoint")){
				startObjectTest.jointInfo[0]=(root.get(i).getInt("numberOfJoint"))+"";
				startObjectTest.jointInfo[1]=root.get(i).getString("typeOfJoint");
				startObjectTest.jointInfo[2]=root.get(i).getString("parameterOfjoint");
			}
			else{
				startObjectTest.jointInfo[0]=0+"";
			}
			}
			else{
				startObjectTest=new StartObject(world,  animationObject.valueOf(root.get(i).getString("appVarType")), posx,posy);
				startObjectTest.jointInfo[0]=0+"";
			}
			startObjectTest.setProperties(root.get(i).getFloat("density")-1,root.get(i).getFloat("friction")-1,root.get(i).getFloat("restitution")-1,root.get(i).getFloat("width")-1,root.get(i).getFloat("height")-1,root.get(i).getFloat("movementSpeed")-1,root.get(i).getInt("lifeCount")-1,startControlMove);
			lifeCount=root.get(i).getInt("lifeCount")-1;
			startObjectTest.getBody().get(0).setType(BodyType.StaticBody);
			gameObjectArray.add(startObjectTest);
			startObjectplayer=startObjectTest.getPlayer();
			//System.out.println(root.get(i).getString("classType"));
			break;
		case"EndObject":
			if(root.get(i).has("secondJoint")||root.get(i).has("numberOfJoint")){
			endObjectTest=new EndObject(world,  animationObject.valueOf(root.get(i).getString("appVarType")),posx,posy);
			if(root.get(i).has("numberOfJoint")){
				endObjectTest.jointInfo[0]=(root.get(i).getInt("numberOfJoint"))+"";
				endObjectTest.jointInfo[1]=root.get(i).getString("typeOfJoint");
				endObjectTest.jointInfo[2]=root.get(i).getString("parameterOfjoint");
			}
			else{
				endObjectTest.jointInfo[0]=0+"";
			}
			}
			else{
				endObjectTest=new EndObject(world,  animationObject.valueOf(root.get(i).getString("appVarType")),posx,posy);
				endObjectTest.jointInfo[0]=0+"";
			}
			endObjectTest.setProperties(root.get(i).getFloat("density")-1,root.get(i).getFloat("friction")-1,root.get(i).getFloat("restitution")-1,root.get(i).getFloat("width")-1,root.get(i).getFloat("height")-1,root.get(i).getFloat("animationSpeed")-1);
			endObjectTest.setStatic(new Vector2(endObjectTest.getPosition().get(0)));
			endObjectTest.getBody().get(0).setType(BodyType.StaticBody);
			gameObjectArray.add(endObjectTest);
			endObjectplayer=endObjectTest.getPlayer();
			//System.out.println(root.get(i).getString("classType"));
			break;
		case "AnimationObject":
			boolean setStatic4=false;
			if(root.get(i).has("setStatic")){setStatic4=true;}
			if(root.get(i).has("secondJoint")||root.get(i).has("numberOfJoint")){
			animObjectTest=new AnimationObject(world, animationObject.valueOf(root.get(i).getString("appVarType")),posx,posy);
			if(root.get(i).has("numberOfJoint")){
				animObjectTest.jointInfo[0]=(root.get(i).getInt("numberOfJoint"))+"";
				animObjectTest.jointInfo[1]=root.get(i).getString("typeOfJoint");
				animObjectTest.jointInfo[2]=root.get(i).getString("parameterOfjoint");
			}
			else{
				animObjectTest.jointInfo[0]=0+"";
			}
			}
			else{
				animObjectTest=new AnimationObject(world, animationObject.valueOf(root.get(i).getString("appVarType")),posx,posy);	
				animObjectTest.jointInfo[0]=0+"";
			}
			animObjectTest.setProperties(root.get(i).getFloat("density")-1,root.get(i).getFloat("friction")-1,root.get(i).getFloat("restitution")-1,root.get(i).getFloat("width")-1,root.get(i).getFloat("height")-1,root.get(i).getFloat("animationSpeed")-1,root.get(i).getInt("collision")-1, setStatic4);
			animObjectTest.setStatic(new Vector2(animObjectTest.getPosition().get(0)));
			gameObjectArray.add(animObjectTest);
			animationObjectplayer.add(animObjectTest.getPlayer());
				
			break;
		case "DangerousObject":
			if(root.get(i).has("secondJoint")||root.get(i).has("numberOfJoint")){
				dangerousObjectTest=new DangerousObject(world, animationObject.valueOf(root.get(i).getString("appVarType")),posx,posy);
				if(root.get(i).has("numberOfJoint")){
					dangerousObjectTest.jointInfo[0]=(root.get(i).getInt("numberOfJoint"))+"";
					dangerousObjectTest.jointInfo[1]=root.get(i).getString("typeOfJoint");
					dangerousObjectTest.jointInfo[2]=root.get(i).getString("parameterOfjoint");
				}
				else{
					dangerousObjectTest.jointInfo[0]=0+"";
				}
				}
				else{
					dangerousObjectTest=new DangerousObject(world, animationObject.valueOf(root.get(i).getString("appVarType")),posx,posy);	
					dangerousObjectTest.jointInfo[0]=0+"";
				}
			dangerousObjectTest.setProperties(root.get(i).getFloat("density")-1,root.get(i).getFloat("friction")-1,root.get(i).getFloat("restitution")-1,root.get(i).getFloat("width")-1,root.get(i).getFloat("height")-1,root.get(i).getFloat("movementSpeed")-1);
			dangerousObjectTest.setStatic(new Vector2(dangerousObjectTest.getPosition().get(0)));
			dangerousObjectTest.getBody().get(0).setType(BodyType.StaticBody);
				gameObjectArray.add(dangerousObjectTest);
				dangerousObjectPlayer.add(dangerousObjectTest.getPlayer());
			break;
		default:
			break;
		}
		
		
	}//rara
	objects=listObjects.split(",");
	numberOfListObject=objects.length;
	System.out.println(numberOfListObject);
}
public void createJoint(){
	for(int i=0;i<gameObjectArray.size();i++){
		//System.out.println("size of the joint"+gameObjectArray.get(i).jointInfo[0]+"  "+gameObjectArray.get(i).toString());
		if(Integer.parseInt(gameObjectArray.get(i).jointInfo[0])>0){
			
			String[] jointType =gameObjectArray.get(i).jointInfo[1].split(",");
			String[] jointparameter=gameObjectArray.get(i).jointInfo[2].split("/");
			//System.out.println("size of properity"+jointparameter.length);
			for(int m=0;m<jointType.length;m++){
				if(jointType[m].equals("Distance")){
					//System.out.println("Distance");
					String[] splitDistance=jointparameter[m].split(":");
					String[] splitDistanceSecondPossition=splitDistance[0].split(",");
					String[] splitDistanceFirstJointPossition=splitDistance[1].split(",");
					String[] splitDistanceSecondJointPossition=splitDistance[2].split(",");
					String[] splitDistanceNumberOfBasic=splitDistance[3].split(",");
					for(int l=0;l<gameObjectArray.size();l++){
						if(gameObjectArray.get(l).getPosition().get(0).x==Float.parseFloat(splitDistanceSecondPossition[0])&&gameObjectArray.get(l).getPosition().get(0).y==Float.parseFloat(splitDistanceSecondPossition[1])){
							distance=new Distance(gameObjectArray.get(i).getBasicObject().get(Integer.parseInt(splitDistanceNumberOfBasic[0])),gameObjectArray.get(l).getBasicObject().get(Integer.parseInt(splitDistanceNumberOfBasic[1])),new Vector2(Float.parseFloat(splitDistanceFirstJointPossition[0]),Float.parseFloat(splitDistanceFirstJointPossition[1])), new Vector2(Float.parseFloat(splitDistanceSecondJointPossition[0]),Float.parseFloat(splitDistanceSecondJointPossition[1])),Integer.parseInt(splitDistanceNumberOfBasic[0]),Integer.parseInt(splitDistanceNumberOfBasic[1]), world);
							gameObjectArray.get(i).setGameJoint(distance);
							gameObjectArray.get(l).setSecondJoint(true);
						//System.out.println(Float.parseFloat(splitDistanceFirstJointPossition[0])+":"+Float.parseFloat(splitDistanceFirstJointPossition[1])+"----second"+ Float.parseFloat(splitDistanceSecondJointPossition[0])+":"+Float.parseFloat(splitDistanceSecondJointPossition[1]));
						}
					}
				
				
				}
				else if(jointType[m].equals("Revolute")){
					//System.out.println("Revolute");
					String[] splitRevolute=jointparameter[m].split(":");
					String[] splitRevoluteSecondPossition=splitRevolute[0].split(",");
					String[] splitRevoluteFirstJointPossition=splitRevolute[1].split(",");
					String[] splitRevoluteSecondJointPossition=splitRevolute[2].split(",");
					String[] splitRevoluteNumberOfBasic=splitRevolute[3].split(",");
					String[] splitRevoluteMotorSpeed=splitRevolute[4].split(",");
					String[] splitRevoluteMotorTorque=splitRevolute[5].split(",");
					for(int k=0;k<gameObjectArray.size();k++){
						if(gameObjectArray.get(k).getPosition().get(0).x==Float.parseFloat(splitRevoluteSecondPossition[0])&&gameObjectArray.get(k).getPosition().get(0).y==Float.parseFloat(splitRevoluteSecondPossition[1])){
							revolute=new Revolute(gameObjectArray.get(i).getBasicObject().get(Integer.parseInt(splitRevoluteNumberOfBasic[0])),gameObjectArray.get(k).getBasicObject().get(Integer.parseInt(splitRevoluteNumberOfBasic[1])), new Vector2(Float.parseFloat(splitRevoluteFirstJointPossition[0]),Float.parseFloat(splitRevoluteFirstJointPossition[1])), new Vector2(Float.parseFloat(splitRevoluteFirstJointPossition[0]),Float.parseFloat(splitRevoluteFirstJointPossition[1])),Integer.parseInt(splitRevoluteNumberOfBasic[0]),Integer.parseInt(splitRevoluteNumberOfBasic[1]),world, Float.parseFloat(splitRevoluteMotorTorque[0]), Float.parseFloat(splitRevoluteMotorSpeed[0]));
							gameObjectArray.get(i).setGameJoint(revolute);
							gameObjectArray.get(k).setSecondJoint(true);
							
						}
						
					}
				}
				else{}
			}
		}
		
	}
	
}

public void handlingCollision(){
	   // Activate collision listener 
	
		world.setContactListener(new ContactListener() {
		@Override
		public void beginContact(Contact contact) {
			if(!AppVar.testPlay){
				for(int i=0;i<gameObjectArray.size();i++){
					for(int y=0;y<gameObjectArray.get(i).getBasicObject().size();y++){
						if(contact.getFixtureA().equals(gameObjectArray.get(i).getBasicObject().get(y).getFixture())){
							firstBodyJoint= gameObjectArray.get(i).getBasicObject().get(y);
							numberOfFirstBodyJoint=y;
							firstObjectJoint=gameObjectArray.get(i);//we will add the joint class to this object 
						}
					}
				} 
				for(int i=0;i<gameObjectArray.size();i++){
					for(int y=0;y<gameObjectArray.get(i).getBasicObject().size();y++){
						if(contact.getFixtureB().equals(gameObjectArray.get(i).getBasicObject().get(y).getFixture())){
							secondBodyJoint= gameObjectArray.get(i).getBasicObject().get(y);
							numberOfSecondBodyJoint=y;
							 secondObjectJoint= gameObjectArray.get(i);
							 }
						 }
					//for BasicObject sounds
					if(gameObjectArray.get(i)instanceof BasicObject){
						if(((BasicObject)gameObjectArray.get(i)).isTakeSound()){
							if(((BasicObject)gameObjectArray.get(i)).getFixture().getShape() instanceof PolygonShape){
								((BasicObject)gameObjectArray.get(i)).music2.pause();
							}
							else{
								((BasicObject)gameObjectArray.get(i)).music.pause();
							}
						}
					}
					 } 
				}
			//for BasicObject sounds
			for(int i=0;i<gameObjectArray.size();i++){
				if(gameObjectArray.get(i)instanceof BasicObject){
					if(contact.getFixtureA().equals(gameObjectArray.get(i).getBasicObject().get(0).getFixture())||
							contact.getFixtureB().equals(gameObjectArray.get(i).getBasicObject().get(0).getFixture())){
						if(((BasicObject)gameObjectArray.get(i)).getFixture().getShape() instanceof PolygonShape){
							if(((BasicObject)gameObjectArray.get(i)).isTakeSound()){
								((BasicObject)gameObjectArray.get(i)).music2.play();
							}
						}
						else{
							if(((BasicObject)gameObjectArray.get(i)).isTakeSound()){
								((BasicObject)gameObjectArray.get(i)).music.play();
							}
						}
					}
				}	
			}	
						
			if(startObjectplayer !=null && endObjectTest !=null){
				for(int i=0;i<gameObjectArray.size();i++){
					if(startObjectTest.getBasicObject().size()==1){
					if(contact.getFixtureA().equals(startObjectTest.getBasicObject().get(0).getFixture())&&contact.getFixtureB().equals(endObjectTest.getBasicObject().get(0).getFixture())||
							contact.getFixtureB().equals(startObjectTest.getBasicObject().get(0).getFixture())&&contact.getFixtureA().equals(endObjectTest.getBasicObject().get(0).getFixture())){
						levelDialog.show(stage);
						done=true;
						if(value>lastLeve){writeToFile(value+"");}
					}
					}
					else{
					 if(contact.getFixtureA().equals(startObjectTest.getBasicObject().get(1).getFixture())&&contact.getFixtureB().equals(endObjectTest.getBasicObject().get(0).getFixture())||
							contact.getFixtureB().equals(startObjectTest.getBasicObject().get(1).getFixture())&&contact.getFixtureA().equals(endObjectTest.getBasicObject().get(0).getFixture())||
							contact.getFixtureA().equals(startObjectTest.getBasicObject().get(2).getFixture())&&contact.getFixtureB().equals(endObjectTest.getBasicObject().get(0).getFixture())||
							contact.getFixtureB().equals(startObjectTest.getBasicObject().get(2).getFixture())&&contact.getFixtureA().equals(endObjectTest.getBasicObject().get(0).getFixture())){
						levelDialog.show(stage);
						done=true;
						if(value>lastLeve){writeToFile(value+"");}
					}
					}
				}
				}
			for(int i=0;i<gameObjectArray.size();i++){
				if(gameObjectArray.get(i) instanceof DangerousObject){
					if(gameObjectArray.get(i).getBasicObject().size()==1){
						if(startObjectTest.getBasicObject().size()==1){
							if(contact.getFixtureA().equals(gameObjectArray.get(i).getBasicObject().get(0).getFixture())
									&&contact.getFixtureB().equals(startObjectTest.getBasicObject().get(0).getFixture())||
									contact.getFixtureB().equals(gameObjectArray.get(i).getBasicObject().get(0).getFixture())
									&&contact.getFixtureA().equals(startObjectTest.getBasicObject().get(0).getFixture())){
								if(lifeCount!=0){
									lifeCount=lifeCount-1;
									life.setText("life:"+" "+lifeCount);
									flag_retry=true;
								}
								if(lifeCount==0){
									burnt=true;

									GameOverDialog.show(stage);
									
								}
							}
						}
						else if(contact.getFixtureA().equals(gameObjectArray.get(i).getBasicObject().get(0).getFixture())&&contact.getFixtureB().equals(startObjectTest.getBasicObject().get(0).getFixture())
								||contact.getFixtureB().equals(gameObjectArray.get(i).getBasicObject().get(0).getFixture())&&contact.getFixtureA().equals(startObjectTest.getBasicObject().get(0).getFixture())
								||contact.getFixtureA().equals(gameObjectArray.get(i).getBasicObject().get(0).getFixture())&&contact.getFixtureB().equals(startObjectTest.getBasicObject().get(1).getFixture())
								||contact.getFixtureB().equals(gameObjectArray.get(i).getBasicObject().get(0).getFixture())&&contact.getFixtureA().equals(startObjectTest.getBasicObject().get(1).getFixture())
								||contact.getFixtureA().equals(gameObjectArray.get(i).getBasicObject().get(0).getFixture())&&contact.getFixtureB().equals(startObjectTest.getBasicObject().get(2).getFixture())
								||contact.getFixtureB().equals(gameObjectArray.get(i).getBasicObject().get(0).getFixture())&&contact.getFixtureA().equals(startObjectTest.getBasicObject().get(2).getFixture())){
								if(lifeCount!=0){
									lifeCount=lifeCount-1;
									life.setText("life:"+" "+lifeCount);
									flag_retry=true;
								}
								if(lifeCount==0){
									burnt=true;
									GameOverDialog.show(stage);
								}
						}
					}
					else if(startObjectTest.getBasicObject().size()==1){
						if(contact.getFixtureA().equals(gameObjectArray.get(i).getBasicObject().get(0).getFixture())&&contact.getFixtureB().equals(startObjectTest.getBasicObject().get(0).getFixture())
						 ||contact.getFixtureB().equals(gameObjectArray.get(i).getBasicObject().get(0).getFixture())&&contact.getFixtureA().equals(startObjectTest.getBasicObject().get(0).getFixture())		
						 ||contact.getFixtureA().equals(gameObjectArray.get(i).getBasicObject().get(1).getFixture())&&contact.getFixtureB().equals(startObjectTest.getBasicObject().get(0).getFixture())
						 ||contact.getFixtureB().equals(gameObjectArray.get(i).getBasicObject().get(1).getFixture())&&contact.getFixtureA().equals(startObjectTest.getBasicObject().get(0).getFixture())
						 ||contact.getFixtureA().equals(gameObjectArray.get(i).getBasicObject().get(2).getFixture())&&contact.getFixtureB().equals(startObjectTest.getBasicObject().get(0).getFixture())
						 ||contact.getFixtureB().equals(gameObjectArray.get(i).getBasicObject().get(2).getFixture())&&contact.getFixtureA().equals(startObjectTest.getBasicObject().get(0).getFixture())){
							if(lifeCount!=0){
								lifeCount=lifeCount-1;
								life.setText("life:"+" "+lifeCount);
								flag_retry=true;
							}
							if(lifeCount==0){
								burnt=true;
								GameOverDialog.show(stage);
							}
						}
					}
					else if(contact.getFixtureA().equals(gameObjectArray.get(i).getBasicObject().get(0).getFixture())&&contact.getFixtureB().equals(startObjectTest.getBasicObject().get(0).getFixture())
							 ||contact.getFixtureB().equals(gameObjectArray.get(i).getBasicObject().get(0).getFixture())&&contact.getFixtureA().equals(startObjectTest.getBasicObject().get(0).getFixture())		
							 ||contact.getFixtureA().equals(gameObjectArray.get(i).getBasicObject().get(1).getFixture())&&contact.getFixtureB().equals(startObjectTest.getBasicObject().get(0).getFixture())
							 ||contact.getFixtureB().equals(gameObjectArray.get(i).getBasicObject().get(1).getFixture())&&contact.getFixtureA().equals(startObjectTest.getBasicObject().get(0).getFixture())
							 ||contact.getFixtureA().equals(gameObjectArray.get(i).getBasicObject().get(2).getFixture())&&contact.getFixtureB().equals(startObjectTest.getBasicObject().get(0).getFixture())
							 ||contact.getFixtureB().equals(gameObjectArray.get(i).getBasicObject().get(2).getFixture())&&contact.getFixtureA().equals(startObjectTest.getBasicObject().get(0).getFixture())
							 ||contact.getFixtureA().equals(gameObjectArray.get(i).getBasicObject().get(0).getFixture())&&contact.getFixtureB().equals(startObjectTest.getBasicObject().get(1).getFixture())
							 ||contact.getFixtureB().equals(gameObjectArray.get(i).getBasicObject().get(0).getFixture())&&contact.getFixtureA().equals(startObjectTest.getBasicObject().get(1).getFixture())		
							 ||contact.getFixtureA().equals(gameObjectArray.get(i).getBasicObject().get(1).getFixture())&&contact.getFixtureB().equals(startObjectTest.getBasicObject().get(1).getFixture())
							 ||contact.getFixtureB().equals(gameObjectArray.get(i).getBasicObject().get(1).getFixture())&&contact.getFixtureA().equals(startObjectTest.getBasicObject().get(1).getFixture())
							 ||contact.getFixtureA().equals(gameObjectArray.get(i).getBasicObject().get(2).getFixture())&&contact.getFixtureB().equals(startObjectTest.getBasicObject().get(1).getFixture())
							 ||contact.getFixtureB().equals(gameObjectArray.get(i).getBasicObject().get(2).getFixture())&&contact.getFixtureA().equals(startObjectTest.getBasicObject().get(1).getFixture())
							 ||contact.getFixtureA().equals(gameObjectArray.get(i).getBasicObject().get(0).getFixture())&&contact.getFixtureB().equals(startObjectTest.getBasicObject().get(2).getFixture())
							 ||contact.getFixtureB().equals(gameObjectArray.get(i).getBasicObject().get(0).getFixture())&&contact.getFixtureA().equals(startObjectTest.getBasicObject().get(2).getFixture())		
							 ||contact.getFixtureA().equals(gameObjectArray.get(i).getBasicObject().get(1).getFixture())&&contact.getFixtureB().equals(startObjectTest.getBasicObject().get(2).getFixture())
							 ||contact.getFixtureB().equals(gameObjectArray.get(i).getBasicObject().get(1).getFixture())&&contact.getFixtureA().equals(startObjectTest.getBasicObject().get(2).getFixture())
							 ||contact.getFixtureA().equals(gameObjectArray.get(i).getBasicObject().get(2).getFixture())&&contact.getFixtureB().equals(startObjectTest.getBasicObject().get(2).getFixture())
							 ||contact.getFixtureB().equals(gameObjectArray.get(i).getBasicObject().get(2).getFixture())&&contact.getFixtureA().equals(startObjectTest.getBasicObject().get(2).getFixture())){
								if(lifeCount!=0){
									lifeCount=lifeCount-1;
									life.setText("life:"+" "+lifeCount);
									flag_retry=true;
								}
								if(lifeCount==0){
									burnt=true;
									GameOverDialog.show(stage);
								}
					}
				}
			}
			for(int i=0;i<gameObjectArray.size();i++){
				if(gameObjectArray.get(i)instanceof Disappear){
					if(contact.getFixtureA().equals(gameObjectArray.get(i).getBasicObject().get(0).getFixture())){
						if(startObjectplayer !=null){
							if(startObjectTest.getBasicObject().size()==1){
								if(contact.getFixtureB().equals(startObjectTest.getBasicObject().get(0).getFixture())){
									testDis=(Disappear) gameObjectArray.get(i);
									disappearFlag=true;
								}		
							}
							else{
								if(contact.getFixtureB().equals(startObjectTest.getBasicObject().get(0).getFixture())||
								   contact.getFixtureB().equals(startObjectTest.getBasicObject().get(1).getFixture())||
								   contact.getFixtureB().equals(startObjectTest.getBasicObject().get(2).getFixture())){
									testDis=(Disappear) gameObjectArray.get(i);
									disappearFlag=true;
								}	
							}
							
						}
					}
					else if(contact.getFixtureB().equals(gameObjectArray.get(i).getBasicObject().get(0).getFixture())){
						if(startObjectplayer !=null){
							if(startObjectTest.getBasicObject().size()==1){
								if(contact.getFixtureA().equals(startObjectTest.getBasicObject().get(0).getFixture())){
									testDis=(Disappear) gameObjectArray.get(i);
									disappearFlag=true;
								}		
							}
							else{
								if(contact.getFixtureA().equals(startObjectTest.getBasicObject().get(0).getFixture())||
								   contact.getFixtureA().equals(startObjectTest.getBasicObject().get(1).getFixture())||
								   contact.getFixtureA().equals(startObjectTest.getBasicObject().get(2).getFixture())){
									testDis=(Disappear) gameObjectArray.get(i);
									disappearFlag=true;
								}	
							}
							
						}
					}
				}
			}
			
			
		}
		@Override
			public void endContact(Contact contact) {
			}
			@Override
			public void preSolve(Contact contact, Manifold oldManifold) {
			}
			@Override
			public void postSolve(Contact contact, ContactImpulse impulse) {
			}
		});
	}
public void resizee(int width, int height) {
	camera.viewportWidth=width/25;
	camera.viewportHeight=height/25;
	camera.update();
}
public static class LevelDialog extends Dialog{

	
		
	public LevelDialog(String title, Skin skin) {
		super(title, skin);
		// TODO Auto-generated constructor stub
	}

	
	{
		//text("Great You Do it !").padBottom(100);
		button("Next","Bye");
		button("Back","Hello");
		
	}
	@Override
	protected void result(Object object){
		if(object.equals("Hello")){
			gsm.setState(GameStateManager.LEVELS,0,0);
		//	AppVar.testPlay=!AppVar.testPlay;
			
		}
		else if(object.equals("Bye")){
			System.out.println(value+",,,,,"+lastValue);
			if(value<=lastValue){
			gsm.setState(GameStateManager.PLAY,value,lastValue);
			}
			else {
				NextDialog.show(stage);
				
			}
		//	AppVar.testPlay=!AppVar.testPlay;
		}
		
	}
}
public static class GameOverDialog extends Dialog{
	
	public GameOverDialog(String title, Skin skin) {
		super(title, skin);
		// TODO Auto-generated constructor stub
	}

	
	{
		button("Retry","Bye");
		button("Back","Hello");
		
	}
	@Override
	protected void result(Object object){
		if(object.equals("Hello")){
			gsm.setState(GameStateManager.LEVELS,0,0);
		//	AppVar.testPlay=!AppVar.testPlay;
			
		}
		else if(object.equals("Bye")){
			gsm.setState(GameStateManager.PLAY,value-1,0);
		//	AppVar.testPlay=!AppVar.testPlay;
		}
		
	}	
}
public static class NextDialog extends Dialog{
	
	public NextDialog(String title, Skin skin) {
		super(title, skin);
		// TODO Auto-generated constructor stub
	}

	
	{
		button("Back","Hello");
		
	}
	@Override
	protected void result(Object object){
		if(object.equals("Hello")){
			gsm.setState(GameStateManager.LEVELS,0,0);
		//	AppVar.testPlay=!AppVar.testPlay;
			
		}
	}	
}
	private void writeToFile(String data){
		 
		try{
		    // Print a line of text
			levelFile.writeString("Level number:"+data,false);
		}
		catch (Exception e) {
		System.out.println("mm");
		}
	}
	//raneen
	private String readFromFile(){
		String output="";
		try
		{
			output=levelFile.readString();
		}
		// Catches any error conditions
		catch (Exception e)
		{
			//file not exist make new one
			output="last Level number:"+1;
			System.out.println(output);
		}
		return output;
	}
	public void createReveluteTable(){
		jointTable.clear();
		jointTable.add(firstBodyJoint.getType()+" point joint:","mido");
		jointTable.add(jointPossition1Txt).row();
		jointTable.add(secondBodyJoint.getType()+" point joint:","mido");
		jointTable.add(jointPossition2Txt).row();
		jointTable.add("Motor torque","mido");
		jointTable.add(jointMaxMotorTorqueTxt).row();
		jointTable.add("Motor speed","mido");
		jointTable.add(jointMotorSpeed).row();
		jointTable.add(createRevoluteJointButton).width(basicTable.getWidth()/30f).height(basicTable.getWidth()/30f);
		stage.clear();
		basicTable.clear();
		if(lifeCount>0){
			life.setText("life:"+" "+lifeCount);
			basicTable.add(life).left();
			basicTable.add("Game Test      ","level").colspan(30).expandX().top().center().row();
			}
			else{
				basicTable.add("Game Test","level").colspan(30).expandX().top().row();
			}
		basicTable.add(leftTable).left();
		basicTable.add(jointTable).width(basicTable.getWidth()/6).left().top().row();//
		basicTable.add(" ").expandY();
		stage.addActor(basicTable);
		
	}
	public void createDistanceTable(){
		jointTable.clear();
		jointTable.add(firstBodyJoint.getType()+" point joint:","mido");
		jointTable.add(jointPossition1Txt).row();
		jointTable.add(secondBodyJoint.getType()+" point joint:","mido");
		jointTable.add(jointPossition2Txt).row();
		jointTable.add(createDistanceJointButton).width(basicTable.getWidth()/30f).height(basicTable.getWidth()/30f);
		stage.clear();
		basicTable.clear();
		if(lifeCount>0){
			life.setText("life:"+" "+lifeCount);
			basicTable.add(life).left();
			basicTable.add("Game Test      ","level").colspan(30).expandX().top().center().row();
			}
			else{
				basicTable.add("Game Test","level").colspan(30).expandX().top().row();
			}
		basicTable.add(leftTable).left();
		basicTable.add(jointTable).width(basicTable.getWidth()/6).left().top().row();//
		basicTable.add(" ").expandY();
		stage.addActor(basicTable);
	}
	public void createWheelTable(){
		stage.clear();
		basicTable.clear();
		if(lifeCount>0){
			life.setText("life:"+" "+lifeCount);
			basicTable.add(life).left();
			basicTable.add("Game Test      ","level").colspan(3).expandX().top().center().row();
			}
			else{
				basicTable.add("Game Test","level").colspan(3).expandX().top().row();
			}
		basicTable.add(leftTable).uniformX().left().row();
		basicTable.add(" ").expandY();
		stage.addActor(basicTable);
		
	}


}

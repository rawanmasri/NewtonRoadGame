package sw.newtonroad.game;

import sw.newtonroad.game.GameStateManager;
import sw.newtonroad.handlers.AppVar;
import sw.newtonroad.handlers.ButtonTable;
import sw.newtonroad.handlers.ConnectServer;
import sw.newtonroad.manegers.GameState;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.Net.HttpRequest;
import com.badlogic.gdx.Net.HttpResponse;
import com.badlogic.gdx.Net.HttpResponseListener;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class LevelsScreen extends GameState{
	private Stage stage;
	private Skin skin ;
	private OrthographicCamera camera;
	private Texture texture;
	private	SpriteBatch batch;
	private TweenManager tweenManager;
	private FileHandle resourceFile;
	private int levelButtonNumber;
	private int startNewLevelnumber;
	private FileHandle levelFile;
	private Table basicTable ;
	private FileHandle file;
	private int lastLevel=0;
	private String response;
	private Table levelTable;
	private TextButton updateButton;
	
	public LevelsScreen(GameStateManager gsm) {
		super(gsm);
		camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        batch=new SpriteBatch();
		
	}
	@Override
	public void init() {
		stage=new Stage();
		skin=new Skin(Gdx.files.internal("gameui/menueSkin.jason"),new TextureAtlas("gameui/gameui.pack"));
		basicTable =new Table(skin);
		tweenManager =new TweenManager();
		//resourceFile = Gdx.files.absolute("C:\\Users\\Rawan\\Desktop\\workspaces\\levels\\resources.txt");
		//levelFile = Gdx.files.absolute("C:\\Users\\Rawan\\Desktop\\workspaces\\levels\\levelLock.txt");
		resourceFile = Gdx.files.external("levels\\resources.txt");
		levelFile = Gdx.files.external("levels\\levelLock.txt");
		 
		  levelTable =new Table(skin);
		 ButtonTable buttonTable=new ButtonTable("gameui/gamelayout.pack",  Gdx.graphics.getWidth(), Gdx.graphics.getHeight(),-AppVar.V_WIDTH+40,30);
		Gdx.input.setInputProcessor(stage);
		texture = new Texture(Gdx.files.internal("image/game/back6.png"));
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		 updateButton=new TextButton("Update Levels ",skin,"level");
		showLevelButtons();
		
		
		
	}
	public void ConnectDropBox(){
		 String requestContent = null;
			HttpRequest  httpRequest = new HttpRequest(Net.HttpMethods.GET);
	   	 httpRequest.setUrl("https://dl.dropboxusercontent.com/s/lwrtob10g1ozbv2/level.json?dl=0");
			 httpRequest.setHeader("Content-Type", "application/json");
	   	    httpRequest.setContent(requestContent);
   	    Gdx.net.sendHttpRequest(httpRequest, new HttpResponseListener() {
   	        public void handleHttpResponse(HttpResponse httpResponse) {
   	            int statusCode = httpResponse.getStatus().getStatusCode();
   	            if(statusCode==200){
   	            	response= httpResponse.getResultAsString();
   	            	file = Gdx.files.external("levels\\level"+".json");
   	             String game[]=response.split("///");
   	          levelButtonNumber=game.length-1;
   	            	file.writeString(response,false);
   	            	resourceFile.writeString("last Level number:"+(levelButtonNumber),false);
   	            	showLevelButtons();
   	            	}
   	            else{
   	            	
   	            	response=null;}
   	            
   	           
   	        }
   	        public void failed(Throwable t) {
   	            System.out.println("HTTP request failed!");
   	        }

   	        @Override
				public void cancelled() {
   	         
					
				}
   	    
   	     

   	    });
   	    
   	 System.out.println(levelButtonNumber);
   	
	}
	@Override
	public void update(float dt) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); 
		tweenManager.update( dt);
		camera.update();
	    batch.setProjectionMatrix(camera.combined);
	    batch.begin();
	    batch.draw(texture,0, 0);
	    batch.end();
	    stage.act(dt);
	    stage.draw();
		
	}
	public void showLevelButtons(){
		
		//basicTable.reset();
		basicTable.clear();
	//	basicTable.remove();
		//levelTable.remove();
		levelTable.clear();
		//levelTable.reset();
		stage.clear();
		basicTable.setFillParent(true);
		 basicTable.setBounds(0, 0, stage.getWidth(), stage.getHeight());
		 basicTable.add("Level Test","level").expandX().colspan(3).spaceBottom(50).row();
		String output=readFromFile();
		if(output==""){}
		else{
		String d[] =output.split(":");
		levelButtonNumber=Integer.parseInt(d[1]);
		//startNewLevelnumber=levelButtonNumber+1;
		System.out.println("here is important"+levelButtonNumber);
		for(int i=0;i<levelButtonNumber;i++){
			System.out.println("hiii");
			TextButton levelButon=new TextButton("Level "+(i+1),skin,"level");
			final int  value=i+1;
			levelTable.add(levelButon).width((int) (Gdx.graphics.getWidth()/12f)).height((int) (Gdx.graphics.getWidth()/20f));
		if((1+i)%3==0){
			levelTable.row();
		}
		
		levelButon.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				//raneen
			    String output1=readFromFile1();
				String da[] =output1.split(":");
				lastLevel=Integer.parseInt(da[1]);
				if(value==1){
					System.out.println(levelButtonNumber);
					gsm.setState(GameStateManager.PLAY,value,levelButtonNumber);
				}
				else if(lastLevel==value){
					System.out.println(levelButtonNumber);
					gsm.setState(GameStateManager.PLAY,value,levelButtonNumber);
					}
				else if (value<lastLevel){
					System.out.println(levelButtonNumber);
					gsm.setState(GameStateManager.PLAY,value,levelButtonNumber);}
				else{System.out.println("level is locked");}
			}
			});
		}
		}
		TextButton backButton=new TextButton("back",skin,"level");
		 backButton.addListener(new ClickListener(){
				@Override
				public void clicked(InputEvent event, float x, float y) {
					
					gsm.setState(GameStateManager.MENU,0,0);
					
					
				}
				});
		 updateButton.addListener(new ClickListener(){
				@Override
				public void clicked(InputEvent event, float x, float y) {
					
					ConnectDropBox();
					
					
				}
				});
		 basicTable.add("");
		basicTable.add(levelTable).expandY().center().top().padTop(100).row();
		basicTable.add(backButton).left().bottom().width((int) (Gdx.graphics.getWidth()/6f)).height((int) (Gdx.graphics.getWidth()/20f));
		 basicTable.add("");
		basicTable.add(updateButton).right().bottom().width((int) (Gdx.graphics.getWidth()/6f)).height((int) (Gdx.graphics.getWidth()/20f));
		
		stage.addActor(basicTable);
		
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
	private String readFromFile(){
		String output="";
		try
		{
			output=resourceFile.readString();
		}
		// Catches any error conditions
		catch (Exception e)
		{
			//file not exist make new one
			output="last Level number:"+0;
		}
		return output;
	}
	private String readFromFile1(){
		String output="";
		try
		{
			output=levelFile.readString();
		}
		// Catches any error conditions
		catch (Exception e)
		{
			//file not exist make new one
			output="last Level number:"+0;
		}
		return output;
	}
	
}

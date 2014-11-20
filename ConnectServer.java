package sw.gameengine.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.Net.HttpRequest;
import com.badlogic.gdx.Net.HttpResponse;
import com.badlogic.gdx.Net.HttpResponseListener;
import com.badlogic.gdx.files.FileHandle;

public class ConnectServer {
	private static String response;
	private static FileHandle file;
	private static int levelNumber=0; 
	public static int ConnectDropBox(){
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
  	            	file.writeString(response,false);
  	            	 String game[]=response.split("///");
  	            	 levelNumber=game.length;
  	            	
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
  	   // while(levelNumber==0){}
  	  return levelNumber;
	}
	

}

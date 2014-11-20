package sw.newtonroad.joint;

import sw.newtonroad.body.BasicObject;
import sw.newtonroad.handlers.AppVar;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class Distance extends GameJoint {
	public Distance(BasicObject firstBody, BasicObject secondBody,
			Vector2 firstPossition, Vector2 secondPossition,int numberOfFirstBodyJoint,int numberOfSecondBodyJoint, World world) {
		super(firstBody, secondBody, firstPossition, secondPossition,numberOfFirstBodyJoint,numberOfSecondBodyJoint, world);
		AppVar.createDistanceJoin(world,firstBody.getBody().get(0),secondBody.getBody().get(0),firstPossition,secondPossition);
	
	}
	public void setDynamic() {
	}
	public void setStatic() {
	}
}

package sw.newtonroad.joint;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import sw.newtonroad.body.BasicObject;

public abstract class GameJoint {
	protected BasicObject firstBody;
	protected BasicObject secondBody;
	protected Vector2 firstPossition;
	protected Vector2 secondPossition;
	protected int numberOfFirstBodyJoint;
	protected int numberOfSecondBodyJoint;
	protected World world;
	public GameJoint(BasicObject firstBody, BasicObject secondBody,
			Vector2 firstPossition, Vector2 secondPossition,int numberOfFirstBodyJoint,int numberOfSecondBodyJoint,World world) {
		this.firstBody = firstBody;
		this.secondBody = secondBody;
		this.firstPossition = firstPossition;
		this.secondPossition = secondPossition;
		this.numberOfFirstBodyJoint=numberOfFirstBodyJoint;
		this.numberOfSecondBodyJoint=numberOfSecondBodyJoint;
		this.world=world;
	}
	public abstract void setDynamic();
	public abstract void setStatic();
	public BasicObject getFirstBody() {
		return firstBody;
	}
	public void setFirstBody(BasicObject firstBody) {
		this.firstBody = firstBody;
	}
	public BasicObject getSecondBody() {
		return secondBody;
	}
	public void setSecondBody(BasicObject secondBody) {
		this.secondBody = secondBody;
	}
	public Vector2 getFirstPossition() {
		return firstPossition;
	}
	public void setFirstPossition(Vector2 firstPossition) {
		this.firstPossition = firstPossition;
	}
	public Vector2 getSecondPossition() {
		return secondPossition;
	}
	public void setSecondPossition(Vector2 secondPossition) {
		this.secondPossition = secondPossition;
	}
	public int getNumberOfFirstBodyJoint() {
		return numberOfFirstBodyJoint;
	}
	public void setNumberOfFirstBodyJoint(int numberOfFirstBodyJoint) {
		this.numberOfFirstBodyJoint = numberOfFirstBodyJoint;
	}
	public int getNumberOfSecondBodyJoint() {
		return numberOfSecondBodyJoint;
	}
	public void setNumberOfSecondBodyJoint(int numberOfSecondBodyJoint) {
		this.numberOfSecondBodyJoint = numberOfSecondBodyJoint;
	}
}

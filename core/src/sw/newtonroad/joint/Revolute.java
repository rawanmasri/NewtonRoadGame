package sw.newtonroad.joint;
import sw.newtonroad.body.BasicObject;
import sw.newtonroad.handlers.AppVar;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint;
public class Revolute extends GameJoint {
	private float maxMotorTorque;
	private float motorSpeed;
	private RevoluteJoint revoluteJoint;
	public Revolute(BasicObject firstBody, BasicObject secondBody,
			Vector2 firstPossition, Vector2 secondPossition,int numberOfFirstBodyJoint,int numberOfSecondBodyJoint,World world,float maxMotorTorque,float motorSpeed){
		super(firstBody, secondBody, firstPossition, secondPossition,numberOfFirstBodyJoint,numberOfSecondBodyJoint,world);
		this.maxMotorTorque=maxMotorTorque;
		this.motorSpeed=motorSpeed;
		revoluteJoint=AppVar.createRevoluteJoint(world, firstBody.getBody().get(0), secondBody.getBody().get(0),firstPossition);
	}
	public float getMaxMotorTorque() {
		return maxMotorTorque;
	}
	public void setMaxMotorTorque(float maxMotorTorque) {
		this.maxMotorTorque = maxMotorTorque;
	}
	public float getMotorSpeed() {
		return motorSpeed;
	}
	public void setMotorSpeed(float motorSpeed) {
		this.motorSpeed = motorSpeed;
	}
	@Override
	public void setDynamic() {
		revoluteJoint.enableMotor(true);
		revoluteJoint.setMaxMotorTorque(maxMotorTorque);
		revoluteJoint.setMotorSpeed(motorSpeed);
	}
	@Override
	public void setStatic() {
	   revoluteJoint.setMotorSpeed(0);
	   revoluteJoint.enableMotor(false);
	   revoluteJoint.setMaxMotorTorque(0);
	}
}

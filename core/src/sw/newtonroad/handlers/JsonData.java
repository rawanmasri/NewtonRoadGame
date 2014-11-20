package sw.newtonroad.handlers;

import sw.newtonroad.handlers.AppVar.basicBodyType;

public class JsonData {
	private String classType;
	private String appVarType;
	private String img;
	private String wheelImg;
	private float density;
	private float friction;
	private float restitution;
	private float angularVelocity;
	private float velocityX;
	private float velocityY;
	private float  width;
	private float height;
	private int ballNumber;
	private float motorSpeed;
	private float motorForce;
	private float motorTorque;
	private float possessionX; 
	private float possessionY; 
	private float  numberOfJoint;
	private String typeOfJoint;
	private String parameterOfjoint;
	private boolean secondJoint;
	private String basicType;
	private float animationSpeed;
	private float movementSpeed;
	private boolean setStatic;
	private boolean stopRotation;
	private int collision;
	private String background;
	private boolean takeSound;
	private boolean controlMovement;
	private int lifeCount;
	private String levelDegree;
	public String getClassType() {
		return classType;
	}
	public float getMotorTorque() {
		return motorTorque;
	}
	public void setMotorTorque(float motorTorque) {
		this.motorTorque = motorTorque;
	}
	public void setClassType(String classType) {
		this.classType = classType;
	}
	public String getAppVarType() {
		return appVarType;
	}
	public void setAppVarType(String appVarType) {
		this.appVarType = appVarType;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getWheelImg() {
		return wheelImg;
	}
	public void setWheelImg(String wheelImg) {
		this.wheelImg = wheelImg;
	}
	public float getDensity() {
		return density;
	}
	public void setDensity(float density) {
		this.density = density;
	}
	public float getFriction() {
		return friction;
	}
	public void setFriction(float friction) {
		this.friction = friction;
	}
	public float getRestitution() {
		return restitution;
	}
	public void setRestitution(float restitution) {
		this.restitution = restitution;
	}
	public float getAngularVelocity() {
		return angularVelocity;
	}
	public void setAngularVelocity(float angularVelocity) {
		this.angularVelocity = angularVelocity;
		//System.out.println(angularVelocity);
	}
	public float getWidth() {
		return width;
	}
	public void setWidth(float width) {
		this.width = width;
	}
	public float getHeight() {
		return height;
	}
	public void setHeight(float height) {
		this.height = height;
	}
	public int getBallNumber() {
		return ballNumber;
	}
	public void setBallNumber(int ballNumber) {
		this.ballNumber = ballNumber;
	}
	public float getMotorSpeed() {
		return motorSpeed;
	}
	public void setMotorSpeed(float motorSpeed) {
		this.motorSpeed = motorSpeed;
	}
	public float getMotorForce() {
		return motorForce;
	}
	public void setMotorForce(float motorForce) {
		this.motorForce = motorForce;
	}
	
	public float getPossessionX() {
		return possessionX;
	}
	public void setPossessionX(float possessionX) {
		this.possessionX = possessionX;
	}
	public float getPossessionY() {
		return possessionY;
	}
	public void setPossessionY(float possessionY) {
		this.possessionY = possessionY;
	}
	public float getNumberOfJoint() {
		return numberOfJoint;
	}
	public void setNumberOfJoint(float numberOfJoint) {
		this.numberOfJoint = numberOfJoint;
	}
	public String getTypeOfJoint() {
		return typeOfJoint;
	}
	public void setTypeOfJoint(String typeOfJoint) {
		this.typeOfJoint = typeOfJoint;
	}
	public String getParameterOfjoint() {
		return parameterOfjoint;
	}
	public void setParameterOfjoint(String parameterOfjoint) {
		this.parameterOfjoint = parameterOfjoint;
	}
	public boolean isSecondJoint() {
		return secondJoint;
	}
	public void setSecondJoint(boolean secondJoint) {
		this.secondJoint = secondJoint;
	}
	public String getBasicType() {
		return basicType;
	}
	public void setBasicType(String basicType) {
		this.basicType = basicType;
	}
	public float getAnimationSpeed() {
		return animationSpeed;
	}
	public void setAnimationSpeed(float animationSpeed) {
		this.animationSpeed = animationSpeed;
	}
	public float getMovementSpeed() {
		return movementSpeed;
	}
	public void setMovementSpeed(float movementSpeed) {
		this.movementSpeed = movementSpeed;
	}
	public boolean isSetStatic() {
		return setStatic;
	}
	public void setSetStatic(boolean setStatic) {
		this.setStatic = setStatic;
	}
	public boolean isStopRotation() {
		return stopRotation;
	}
	public void setStopRotation(boolean stopRotation) {
		this.stopRotation = stopRotation;
	}
	public int getCollision() {
		return collision;
	}
	public void setCollision(int collision) {
		this.collision = collision;
	}
	public String getBackground() {
		return background;
	}
	public void setBackground(String background) {
		this.background = background;
	}
	public boolean istakeSound() {
		return takeSound;
	}
	public int getLifeCount() {
		return lifeCount;
	}
	public void setLifeCount(int lifeCount) {
		this.lifeCount = lifeCount;
	}
	public void setTakeSound(boolean takeSound) {
		this.takeSound = takeSound;
	}
	public boolean isControlMovement() {
		return controlMovement;
	}
	public void setControlMovement(boolean controlMovement) {
		this.controlMovement = controlMovement;
	}
	public String getLevelDegree() {
		return levelDegree;
	}
	public void setLevelDegree(String levelDegree) {
		this.levelDegree = levelDegree;
	}
	public float getVelocityX() {
		return velocityX;
	}
	public void setVelocityX(float velocityX) {
		this.velocityX = velocityX;
	}
	public float getVelocityY() {
		return velocityY;
	}
	public void setVelocityY(float velocityY) {
		this.velocityY = velocityY;
	}
}

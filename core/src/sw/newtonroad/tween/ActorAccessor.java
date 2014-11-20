package sw.newtonroad.tween;

import aurelienribon.tweenengine.TweenAccessor;

import com.badlogic.gdx.scenes.scene2d.Actor;

public class ActorAccessor implements TweenAccessor<Actor>{
	public static final int  Y=0,RGB=1,ALPHA=2;

	@Override
	public int getValues(Actor arg0, int arg1, float[] arg2) {
		switch (arg1) {
		case Y:
			arg2[0]=arg0.getY();
			return 1;
		case RGB:
			arg2[0]=arg0.getColor().r;
			arg2[1]=arg0.getColor().g;
			arg2[2]=arg0.getColor().b;
			return 3;
		case ALPHA:
			arg2[0]=arg0.getColor().a;
			return 1;

		default:
			assert false;
			return -1;
		}
	}

	@Override
	public void setValues(Actor arg0, int arg1, float[] arg2) {
		switch ( arg1) {
		case Y:
			arg0.setY(arg2[0]);
			break;
		case RGB:
			arg0.setColor(arg2[0], arg2[1], arg2[2],arg0.getColor().a);
			break;
		case ALPHA:
			arg0.setColor(arg0.getColor().r, arg0.getColor().g,arg0.getColor().b, arg2[0]);
			break;

		default:
			assert false;
			
		}
		
		
	}

}


package worms.model;

public class Facade implements IFacade {

	@Override
	public Worm createWorm(double x, double y, double direction, double radius,
			String name) {
		Worm worm = new Worm(x, y, direction, radius, name);
		return worm;
	}

	@Override
	public boolean canMove(Worm worm, int nbSteps) {
		if (worm.getMass() >= 0) {
			return true;
		}
		return false;
	}

	@Override
	public void move(Worm worm, int nbSteps) {
		worm.move(nbSteps);
	}

	@Override
	public boolean canTurn(Worm worm, double angle) {
		if (worm.getActionPoint() <= 0) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void turn(Worm worm, double angle) {
		worm.turn(angle);
	}

	@Override
	public void jump(Worm worm) {
			worm.jump();
	}

	@Override
	public double getJumpTime(Worm worm) {
		return worm.jumpTime();
	}

	@Override
	public double[] getJumpStep(Worm worm, double t) {
		return worm.jumpStep(t);
	}

	@Override
	public double getX(Worm worm) {
		return worm.getX();
	}

	@Override
	public double getY(Worm worm) {
		return worm.getY();
	}

	@Override
	public double getOrientation(Worm worm) {
		return worm.getDirection();
	}

	@Override
	public double getRadius(Worm worm) {
		return worm.getRadius();
	}

	@Override
	public void setRadius(Worm worm, double newRadius) {
		if (newRadius < getMinimalRadius(worm)) {
			newRadius = getMinimalRadius(worm);
		} else {
			worm.setRadius(newRadius);
		}
	}

	@Override
	public double getMinimalRadius(Worm worm) {
		return worm.getMinimalRadius();
	}

	@Override
	public int getActionPoints(Worm worm) {
		return worm.getMaximumActionPoint();
	}

	@Override
	public int getMaxActionPoints(Worm worm) {
		return worm.getMaximumActionPoint();
	}

	@Override
	public String getName(Worm worm) {
		return worm.getName();
	}

	@Override
	public void rename(Worm worm, String newName) {
		worm.setName(newName);

	}

	@Override
	public double getMass(Worm worm) {
		return worm.getMass();
	}

}

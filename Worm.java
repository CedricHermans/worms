package worms.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;

	/***
	 * 
	 * @author	Cedric Hermans
	 * 			Informatica
	 *
	 */

public class Worm {

	private double x, y, direction, radius;
	private String name;
	private int actionPoint;

	/***
	 * 
	 * @param	x
	 * 			The position of a worm on the x-axis
	 * @param	y
	 * 			The position of a worm on the y-axis
	 * @param	direction
	 * 			The direction of a worm on the x-axis
	 * @param	radius
	 * 			The radius of a worm on the x-axis
	 * @param 	name
	 * 			The name that is given to a worm
	 */	
	public Worm(double x, double y, double direction, double radius, String name) {
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.radius = radius;
		this.name = name;
		this.actionPoint = (int) getMass();
	}

	/***
	 * Returns the position of a worm on the x-axis.
	 */
	@Basic
	public double getX() {
		return this.x;
	}

	/***
	 * Returns the position of a worm on the y-axis
	 */
	@Basic
	public double getY() {
		return this.y;
	}
	
	/***
	 * Returns the current amount of action points of a worm.
	 */
	@Basic
	public double getActionPoint() {
		return actionPoint;
	}
	
	/***
	 * Returns the current radius of a worm.
	 */
	@Basic
	public double getRadius() {
		return this.radius;
	}
	
	/***
	 * Returns the current direction of a worm.
	 */
	@Basic
	public double getDirection() {
		return this.direction;
	}
	
	/***
	 * Returns the minimal radius that a worm can get.
	 */
	@Basic @Immutable
	public double getMinimalRadius() {
		this.radius = 0.23;
		return radius;
	}
	
	/***
	 * Returns the given name of a worm.
	 */
	@Basic
	public String getName() {
		return this.name;
	}
	
	/***
	 * Returns the mass of a worm. This value won't change during the program.
	 */
	@Basic @Immutable
	public double getMass() {
		double mass = 1062 * (4 / 3 * Math.PI * (Math.pow(getRadius(), 3)));
		return mass;
	}
	
	/***
	 * Returns the maximum amount of action points that a worm can have. 
	 * It won't change during execution of the program.
	 */
	@Basic @Immutable
	public int getMaximumActionPoint() {
		return (int) getMass();
	}
	
	/***
	 * 
	 * @param	x
	 * 			The new value for the position on the x-axis of a worm.
	 * @post	The current value will be overwritten by the new value.
	 */
	public void setX(double x) {
		this.x = x;
	}

	/***
	 * 
	 * @param	y
	 * 			The new value for the position on the y-axis of a worm.
	 * @post	The current value will be overwritten by the new value.
	 */
	public void setY(double y) {
		this.y = y;
	}
	
	/***
	 * 
	 * @param	newName
	 * 			The current value of name will be overwritten by the new given String.
	 */
	public void setName(String newName) {
		this.name = newName;
	}
	
	/***
	 * 
	 * @param	newRadius
	 * 			The current value of radius will be overwritten by the new given value.
	 */
	public void setRadius(double newRadius) {
		this.radius = newRadius;
	}
	
	/***
	 * 
	 * @param	newDirection
	 */
	public void turn(double newDirection) {
		double cost = 60 / Math.abs(newDirection);
		this.actionPoint -= (int)cost;
		this.direction = this.direction + newDirection;
		if (this.direction >= 2 * Math.PI) {
			this.direction /= 2 * Math.PI;
		} else if (this.direction <= 0) {
			this.direction += 2 * Math.PI;
		}
	}

	/***
	 * 
	 * @param	nbSteps
	 * 			
	 */
	public void move(int nbSteps) {
		if (actionPoint == 0) {
			
		} else {
			if (this.direction < Math.PI / 2
					|| this.direction > (3 * Math.PI) / 2) {
				this.x = x + getRadius() * nbSteps;
				actionPoint -= costStep();
			} else {
				this.x = x - getRadius() * nbSteps;
				actionPoint -= costStep();
			}
		}
	}

	/***
	 * Returns the amount of action points that will cost for a worm doing one step.
	 */
	public double costStep() {
		double cos = Math.abs(Math.cos(getDirection()));
		double sin = Math.abs(Math.sin(getDirection() * 4));
		double costStep = cos + sin;
		return (int)costStep;
	}
	
	/***
	 * 
	 */
	public void jump() {
		if (actionPoint == 0 || (getDirection() > Math.PI && getDirection() < (2 * Math.PI))) {

		} else {
			double force = (5 * getActionPoint()) + (getMass() * 9.80665);
			double velocity = (force / getMass()) * 0.5;
			double velocityX = velocity * Math.cos(getDirection());
			this.x += velocityX * jumpTime();
			this.actionPoint = 0;
		}
	}
	
	/***
	 * 
	 * @param	t
	 * 			
	 * @return
	 */
	public double[] jumpStep(double t) {
		if (actionPoint == 0) {
			
		} else {
			double earth = 9.80665;
			double force = (5 * getActionPoint()) + (getMass() * 9.80665);
			double velocity = (force / getMass()) * 0.5;
			double velocityX = velocity * Math.cos(getDirection());
			double velocityY = velocity * Math.sin(getDirection());
			double xT = this.x + (velocityX * t);
			double yT = this.y + (velocityY * t - earth / 2 * t * t);
			double[] array = { xT, yT };
			return array;
		}
		double[] array = { x, y };
		return array;
	}

	/***
	 * 
	 */
	public double jumpTime() {
		double earth = 9.80665;
		double force = (5 * getActionPoint()) + (getMass() * earth);
		double velocity = (force / getMass()) * 0.5;
		double velocity2 = Math.pow(velocity, 2);
		double sin = Math.sin(getDirection() * 2);
		double distance = (velocity2 * sin) / earth;
		double time = distance / (velocity * Math.cos(getDirection()));
		return time;
	}
}

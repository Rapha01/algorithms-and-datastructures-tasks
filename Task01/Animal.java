/**
 * This interface describes the animal for the simulated evolution
 * 
 * @team Sebastian Hattinger, Djordje Nikolic, Tarek Nofal
 */
public class Animal implements AnimalInterface {

	static int energyperfood = 10;
	static int energypermove = 1;
	static int reproduceenergy = 150;

	static int[] directions = new int[6];

	private Board board = null;

	int x;
	int y;
	int direction;
	int energy = 100;
	int id;
	/**
	 * @param gen
	 * 			the genom of an single animal
	 */
	double[] gen = { Math.random(), Math.random(), Math.random(),
			Math.random(), Math.random(), Math.random() };

	Animal() {

	}
	/**
	 * Set the animal's reference to the board
	 * 
	 * @param board
	 *            the board
	 */
	public void setBoard(BoardInterface board) {
		this.board = (Board)board;
		x=(int)Math.floor(Math.random()*this.board.size);
		y=(int)Math.floor(Math.random()*this.board.size);
	}
	/**
	 * Set the animal's ID
	 * 
	 * @param id
	 *            the new ID
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * Retrieve an animal's ID
	 * 
	 * @return the animal's ID
	 */
	public int getId() {
		return id;
	}
	/**
	 * Set the x-coordinate
	 * 
	 * @param x
	 *            the new x-coordinate
	 */
	public void setX(int x) {
		this.x = board.sanitizeCoordinates(x);
	}
	/**
	 * Retrieve the x-coordinate
	 * 
	 * @return the x-coordinate
	 */
	public int getX() {
		return x;
	}
	/**
	 * Set the y-coordinate
	 * 
	 * @param y
	 *            the new y-coordinate
	 */
	public void setY(int y) {
		this.y = board.sanitizeCoordinates(y);
	}
	/**
	 * Retrieve the y-coordinate
	 * 
	 * @return the y-coordinate
	 */
	public int getY() {
		return y;
	}
	/**
	 * Perform one time step of the discrete time simulation.
	 * The animal dies if the energy is under 0 or =0
	 * The animal reproduces if the energy is more than the needed energy for reproducing
	 * The animal eats the number of food which is in his radius
	 * The animal moves if nothing happened before
	 */
	public void tick() {
		int foodcount=0;

		if(energy <= 0){
			die();
			return;
		}
		/**
		 * cheking if any food is in the radius of the animal
		 */
		if(energy > Animal.reproduceenergy ){
			reproduce();
			return;
		}

		if(board.hasFood(x+1,y+1)){
			foodcount++;
			board.removeFood(x+1,y+1);
		}

		if(board.hasFood(x-1,y-1)){ 
			foodcount++;
			board.removeFood(x-1,y-1);
		}

		if(board.hasFood(x+1,y)){
			foodcount++;
			board.removeFood(x+1,y);
		}

		if(board.hasFood(x,y+1)){ 
			foodcount++;
			board.removeFood(x,y+1);
		}

		if(board.hasFood(x,y-1)){ 
			foodcount++;
			board.removeFood(x,y-1);
		}

		if(board.hasFood(x-1,y)){ 
			foodcount++;
			board.removeFood(x-1,y);
		}

		if(board.hasFood(x-1,y+1)){ 
			foodcount++;
			board.removeFood(x-1,y+1);
		}

		if(board.hasFood(x+1,y-1)){ 
			foodcount++;
			board.removeFood(x+1,y-1);
		}
		/**
		 * starts the eat method because the food appeared on one of the animal fields
		 */
		if (foodcount > 0){            		
			eat(foodcount);
		} else {
			move();
		}

	}
	/**
	 * The energy increases depending from the number of food the animal ate 
	 * times the energy gain for food
	 * 
	 * @param foodcount
	 * 		the number of food consumed by the animal
	 * @return energy			
	 */
	public void eat(int foodcount) {
		energy += foodcount * energyperfood;		
	}
	/**
	 * the animal splits into two animals and
	 * each animal gets the half of the origin energy
	 */
	public void reproduce() {
		Animal child = new Animal();

		for (int j = 0; j < 6; j++) {
			child.gen[j] = gen[j];
		}		
		child.gen[(int) Math.floor((Math.random() * 6))] = Math.random();

		child.energy = energy / 2;
		child.x = x;
		child.y = y;
		board.addAnimal(child);

		energy /= 2;

	}
	/**
	 * Removes the current animal.
	 */
	public void die() {
		board.removeAnimal(this.id);
	}
	/**
	 * the animal moves depending from a probability which is defined in the animal's gen
	 */
	public void move() {

		energy -= energypermove;

		double[] prob = new double[6];
		double probsum = 0;
		double gensum = 0;
		double rand = Math.random();
		/**
		 * @param gensum
		 * 		the sum of all gen values
		 */
		for (int i = 0; i < 6; i++)
			gensum += gen[i];
		/**
		 * @param prob[]
		 * 		defines the probatility area for each value from the gen
		 */
		for (int i = 0; i < 6; i++)
			prob[i] = gen[i] / gensum;		
		/**
		 * check in which probability area the random number is
		 * and set direction when found
		 * 
		 * @param probsum
		 * 		extends the limits of the probatility area 
		 */
		for (int i = 0; i < 6; i++) {
			probsum += prob[i];
			if (rand < probsum) {
				direction = i;
				break;
			}
		}
		/**
		 * @param direction
		 * 		defines in which direction the animal will move
		 */
		switch (direction) {
		case 0:
			setX(x-1);
			setY(y-1);
			break;
		case 1:
			setX(y-1);
			break;
		case 2:
			setX(x+1);
			setY(y-1);
			break;
		case 3:
			setX(x-1);
			setY(y+1);
			break;
		case 4:
			setY(y+1);
			break;
		case 5:
			setX(x+1);
			setY(y+1);
			break;
		}
		directions[direction]++;
	}
	/**
	 * Get the animal's gene responsible for its movement
	 * 
	 * @return the current gene of the animal
	 */
	public double[] getGene() {
		return gen;
	}
}

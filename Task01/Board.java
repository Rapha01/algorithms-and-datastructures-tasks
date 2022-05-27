

/**
 *
 * @team Sebastian Hattinger, Djordje Nicolic, 
 */


public class Board implements BoardInterface{
	/**
	 * @param fooddropsperround Amount of food falling on the board each tick
	 */
	static int fooddropsperround=10;
	int numticks = 0;
	int foodcount = 0;
	int numberofanimals = 0;

	public boolean[][] food;

	Animal[] animals;

	int animalbirths = 0;	
	int size = 0;

	/**
	 * Creation of the boolean board, where false = no food and true = food
	 * @param size size of the size*site board
	 */

	public void initialize (int size){
		this.size = size; 
		food = new boolean[size][size];
		animals = new Animal[1000];

	}

	/**
	 * Tickmethod, called by the GUI, calls the animal class tick methods and drops food on the board
	 * 
	 */

	public void tick(){

		numticks++;

		//System.out.println("tick " + numticks + ": food " + foodcount + ", animals=" + getNumberOfAnimals());

		for(int i=0 ; i < numberofanimals; i++ ){
			if(animals[i] != null)
				animals[i].tick();
		}	

		for(int j=0 ; j < Board.fooddropsperround ; j++ ){			
			this.addFood((int)Math.floor(Math.random()*size),(int)Math.floor(Math.random()*size));
		}

		if (numticks % 10 == 0) {
			System.out.print(getNumberOfAnimals()+" anims, ");
			for (int i=0;i<6;i++)
				System.out.print(i+": "+Animal.directions[i] + ", ");
			System.out.println();
		}
	}

	/**
	 * A method checking for food (true) on field i,j
	 * @param sanitizeCoordinates A function which adjusts the Coordintes to connect the Ends of the Square Board. So Animals who step out, enter the other side.
	 */
	public boolean hasFood(int i , int j){
		return this.food[sanitizeCoordinates(i)][sanitizeCoordinates(j)];
	}

	/**
	 * A method for adding food and a field i,j
	 * @param sanitizeCoordinates A function which adjusts the Coordintes to connect the Ends of the Square Board. So Animals who step out, enter the other side.
	 */
	public void addFood(int i, int j){
		foodcount++;
		food[sanitizeCoordinates(i)][sanitizeCoordinates(j)] = true; 
	}

	/**
	 * A method for removing food on field i,j
	 * @param sanitizeCoordinates A function which adjusts the Coordintes to connect the Ends of the Square Board. So Animals who step out, enter the other side.
	 */
	public void removeFood(int i, int j){
		foodcount--;
		food[sanitizeCoordinates(i)][sanitizeCoordinates(j)] = false;
	}

	/**
	 * A Method for returning the number of living animals
	 * 
	 */
	public int getNumberOfAnimals(){
		return numberofanimals;
	}

	/**
	 * A method for returning an Animal of given id to the Animal class (via setBoard method)
	 * 
	 */
	public Animal getAnimal(int id){
		Animal animal = animals[id];
		if (animal.getId() != id)
			System.out.println("getanimal: at "+id+" has id "+animal.getId());

		return animal;
	}

	/**
	 * Adds an Animal, sets its id, and calls method setBoard in animal so each animal can access the board
	 * @param animalbirths a counter which ticks each time an animal is created (addAnimal is called)
	 * @param numberofanimals a counter which ticks each time an animal is created (addAnimal is called)
	 */
	public void addAnimal(Animal animal) {
		animals[numberofanimals] = animal;
		animal.setId(numberofanimals);

		animal.setBoard(this);
		animalbirths++;
		numberofanimals++;
	}

	/**
	 * Destroying an animal by removing it from the animals list
	 * 
	 */
	public void removeAnimal(int id){
		Animal animal = animals[id]; 
		if (animal == null)
			System.out.println("removeanimal at "+id+" was null");

		animals[id] = animals[numberofanimals-1]; // überschreiben
		animals[id].setId(id);
		if (numberofanimals>0)
			numberofanimals--;				
	}

	/**
	 *@param sanitizeCoordinates A function which adjusts the Coordintes to connect the Ends of the Square Board. So Animals who step out, enter the other side.
	 */
	public int sanitizeCoordinates(int a) {
		a = a % size;
		if (a < 0)
			a += size;
		return a;
	}

}






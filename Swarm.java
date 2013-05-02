import java.util.*;
import java.lang.*;

/****************
Swarm is a java class for handling the behavior of a Particle Swarm.
It uses methods supplied by Particle.java to update and manipulate individual
particles in a swarm.
****************/
public class Swarm
{
	public static final double MAX_VELOCITY = 10000; // for any direction/dimension we need to know the proper max#
	
	
	public static final double MAX_INIT_POSITION_SPHERE = 100.0; // for any direction/dimension
	public static final double MIN_INIT_POSITION_SPHERE = 50.0;
	public static final double SPHERE_RANGE = MAX_INIT_POSITION_SPHERE - MIN_INIT_POSITION_SPHERE;
	public static final double MAX_INIT_POSITION_ROSENBROCK = 30.0;
	public static final double MIN_INIT_POSITION_ROSENBROCK = 15.0;
	public static final double ROSENBROCK_RANGE = MAX_INIT_POSITION_ROSENBROCK - MIN_INIT_POSITION_ROSENBROCK;
	public static final double MAX_INIT_POSITION_GRIEWANK = 600.0;
	public static final double MIN_INIT_POSITION_GRIEWANK = 300.0;
	public static final double GRIEWANK_RANGE = MAX_INIT_POSITION_GRIEWANK - MIN_INIT_POSITION_GRIEWANK;
	public static final double MAX_INIT_POSITION_ACKLEY = 32.0;
	public static final double MIN_INIT_POSITION_ACKLEY = 16.0;
	public static final double ACKLEY_RANGE = MAX_INIT_POSITION_ACKLEY - MIN_INIT_POSITION_ACKLEY;
	public static final double MAX_INIT_POSITION_RASTRIGIN = 5.12;
	public static final double MIN_INIT_POSITION_RASTRIGIN = 2.56;
	public static final double RASTRIGIN_RANGE = MAX_INIT_POSITION_RASTRIGIN - MIN_INIT_POSITION_RASTRIGIN;
	
	public static final double DEFAULTS = 0; // inertia, phi, other constants ???#
	public static final double PHI = 2.05;
	public static final double RANDOMTOPOLOGY_PROBABILITY = 0.5;
	
	
	// number of dimensions has no limit (other than hard drive space/allocation restrivtions for arrays)
	private int dimensions;
	private topologyEnum topology;
	private Boolean includeSelf;
	private String influenceStructure;
	private int size; // number of particles in swarm
	private String function;
	private double globalBest;
	private int bestParticle; // index of best particle
	
	
	//holds all of the particles in our swarm
	//Particle
	private ArrayList<Particle> particles;
	
	private double phi1 = PHI, phi2 = PHI;
	
	//just for ease of use in calculating the constriction factor
  	private double phisum = phi1 + phi2;
  	
  	public  double constrictionFactor = 2.0 / (phisum - 2.0 + Math.sqrt(phisum*phisum - 4.0*phisum));
	
	private Random rand = new Random();
	
	// this is the constructor of a swarm, which is created by PSO.java
	public Swarm(topologyEnum topology, String includeSelf, String influenceStructure, 
				int swarmSize, String function, int dimensions) {

        // set the best to a very high (bad) value
		globalBest = 999999;
		
		//initialize swarm variables
		this.dimensions = dimensions;
		this.topology = topology;
		if (includeSelf.equalsIgnoreCase("yes")) this.includeSelf = true;
		else this.includeSelf = false;
		this.influenceStructure = influenceStructure;
		this.size = swarmSize;
		this.function = function;
		
		initSwarm();
	}
	
	//initialize particles with velocity positions neighborhoods.
	public void initSwarm(){
		
		// initialize the particle array
		ArrayList<Particle> newParticles = new ArrayList<Particle>();
		
		for(int i = 0; i < size; i++){
		    // particle constructor takes these arrays as parameters
			int index = i;
			List velocity = new ArrayList<Double>();
			List position = new ArrayList<Double>();
			
			// set position and veocity for each dimension with random values within specified range
			for (int j = 0; j < dimensions; j++){
				if(this.function.equalsIgnoreCase("sphere")){
					position.set(j, rand.nextDouble() * SPHERE_RANGE + MIN_INIT_POSITION_SPHERE);
				}else if(this.function.equalsIgnoreCase("rosenbrock")){
					position.set(j, rand.nextDouble() * ROSENBROCK_RANGE + MIN_INIT_POSITION_ROSENBROCK);
				}else if(this.function.equalsIgnoreCase("griewank")){
					position.set(j, rand.nextDouble() * GRIEWANK_RANGE + MIN_INIT_POSITION_GRIEWANK);
				}else if(this.function.equalsIgnoreCase("ackley")){
					position.set(j, rand.nextDouble() * ACKLEY_RANGE + MIN_INIT_POSITION_ACKLEY);
				}else if(this.function.equalsIgnoreCase("rastrigin")){
					position.set(j, rand.nextDouble() * RASTRIGIN_RANGE + MIN_INIT_POSITION_RASTRIGIN);
				}else {
					System.out.println("invalid function.");
				}
				velocity.set(j, rand.nextDouble() * MAX_VELOCITY);
			}
			
			Particle p = new Particle(index, velocity, position);
			newParticles.set(i,p);
		}
		
		// sets this swarm objects particle array to the new one we just created
		this.particles = newParticles;
		
		//figure out who's in the neighborhood
		for(Particle p: this.particles){
			updateNeighborhood(p);
		}
		// initialize local bests
		updateLocalBests();
	}
	
	// this is where the "meat" of the PSO algorithm lies (we move each particle to a new location)
	//called repeatedly by PSO.java
	public void update(){
		
		// iterate through particles and update velocity and position based on personal and neighborhood bests
		for(int i=0; i<this.particles.size(); i++){
		    //grab the current velocities and save them as old
		    Particle p = (Particle)this.particles.get(i);
		    List oldVel = p.getVel();
			List oldPos = p.getPos();
		    						
			//new velocities will be formed using the acceleration, old velocity and position
			List newVel = new ArrayList<Double>();
			List newPos = new ArrayList<Double>();
			List pBestPos = p.getPBestPos();
			
			//implement fully informed particle swarm, accelerate toward all personal bests with same weight.
			if (this.influenceStructure.equalsIgnoreCase("FIPS")){
				double accTowardEachPBest[][] = new double[particles.size()][this.dimensions];
				double accelerationVector[] = new double[this.dimensions]; // used to aggregate the accelerations toward all the bests
				
				//aggregate acceleration toward each neighbor for a given dimension.
				for(int nbr=0; nbr<particles.size(); nbr++){
					Particle neigh = p.getNeighborhood().get(nbr);
					for (int dim = 0 ; dim < this.dimensions; dim++){
						//determine the amount of accel towards both each neighbor's personal best
						double randPhi2 = rand.nextDouble()*phi2;

						//we want vectors that go from the current position to the next position
						//subtract current position vector from pbestposition vector (fixed)
						accTowardEachPBest[nbr][dim] = randPhi2 * ((double)neigh.getPBestPos().get(dim) - (double)oldPos.get(dim));

						accelerationVector[dim] += accTowardEachPBest[nbr][dim];
					}
				}
				for(int dim=0 ; dim<this.dimensions; dim++){
					newVel.set(dim, (((Double)oldVel.get(dim) + accelerationVector[dim]) * constrictionFactor));
					newPos.set(dim, ((Double)oldPos.get(dim) + (Double)newVel.get(dim)));
					// ^^ this is the right math, but isn't working 
					// because list.get(j) is returning java.lang.Object (requires double)...same as below. what is this even?
					
				}
			}
			//normal swarm - influenced by personal and neighborhood best
			else{
				List nBestPos = p.getNBestPos();
				double accTowardPBest[] = new double[this.dimensions];
				double accTowardNBest[] = new double[this.dimensions];
			
				// loop through dimensions, calculate and update velocity and position 
				for (int j = 0 ; j < this.dimensions; j++){
					//determine the amount of accel towards both the personal and the global best
					double randPhi1 = rand.nextDouble()*phi1;
					double randPhi2 = rand.nextDouble()*phi2;
				
					//we want vectors that go from the current position to the next position
					//subtract current position vector from pbestposition vector (fixed)
					accTowardPBest[j] = randPhi1 * ((double)pBestPos.get(j) - (double)oldPos.get(j));
					accTowardNBest[j] = randPhi2 * ((double)nBestPos.get(j) - (double)oldPos.get(j));
					// this is the right math, but isn't working because list.get(j) is returning java.lang.object?
					
					
					//actually calculate the new velocity and position from it
					newVel.set(j, (((Double)oldVel.get(j) + accTowardPBest[j] + accTowardNBest[j]) * constrictionFactor));
					newPos.set(j, ((Double)oldPos.get(j) + (Double)newVel.get(j)));
				}
			}
			//send the vel/pos back to the Particle class
			p.setVel(newVel);
			p.setPos(newPos);
		}
		
		//evaluate new particle fitness and figure out personal best
		for(Particle p : this.particles){
			List pos = p.getPos();
			double fitness = evaluate(pos);
			if (fitness < p.getPBest()){
				p.setPBest(fitness);
				p.setPBestPos(pos);
			}
			
			//if random topology, update neighborhoods here, good a place as any
			if (this.topology == topologyEnum.random){ //like this right?
				updateNeighborhood(p);
			}
		}
		
		updateLocalBests();
	}
	
	// go through particles, find and update neighborhood bests
	public void updateLocalBests(){
		for(Particle p : this.particles){
			for(Particle neighbor : p.getNeighborhood()){
				List neigh = p.getNeighborhood();
				// search through neighbors for the best fitness
				for(int j=0; j<neigh.size(); j++){
					double neighBest = neigh.get(j).getPBest();
					if (neighBest < p.getNBest()){
						p.setNBest(neighBest);
						p.setNBestPos(neigh.get(j).getPBestPos());
					}
				}	
			}
		}
	}
	
	public double evaluate(List pos){
		double fitness;
		
		if(this.function.equalsIgnoreCase("sphere")){
			for(int i=0 ; i<pos.size() ; i++){
				fitness += pos.get(i) * pos.get(i);
			}
		}else if(this.function.equalsIgnoreCase("rosenbrock")){
			for (int i=0 ; i <pos.size() ; i++) {
				double x = pos.get(i) * pos.get(i); //	x_i^2
				double y = (1 - pos.get(i)); // 			( 1 - x_i )
				y *= y; // 								( 1 - x_i )^2
				double z = pos.get(i+1) - x; // 		( x_{i+1} - x_i^2 )
				z *= z; // 								( x_{i+1} - x_i^2 )^2

				fitness += 100 * z + y; // 						100 ( x_{i+1} - x_i^2 )^2 + ( 1 - x_i )^2 
			}
		}else if(this.function.equalsIgnoreCase("griewank")){
			
		}else if(this.function.equalsIgnoreCase("ackley")){
			
		}else if(this.function.equalsIgnoreCase("rastrigin")){
			
		}else{
			System.out.println("invalid function.");
		}
		
		
		return fitness;
	}
	
	public void updateNeighborhood(Particle p){
        // int enumval = ValueEnum.fromString(this.topology);
		switch(this.topology){
			case gbest:
				// neighborhood is entire swarm
				for (Particle q : this.particles)
				{
					if(p != q)
				    	p.addNeighbor(q);
				}
				break;
			case ring:
				// particles are imagined to be in a ring and the neighbors of each particle are the particles 
				// to the left and right of it
				
				// if (object exists to the left of p in this.particles) -> add it
				// else (object is first particle, so add the last particle in this.particles)
				// if (object exists to the right of p) -> add it
				// else (object is last particle, so add the first particle in this.particles)
				
				for (int i=0; i<this.particles.size(); i++){
					int lastIndex = this.particles.size()-1;
					if(i==0) p.addNeighbor(this.particles.get(lastIndex));
					else p.addNeighbor(this.particles.get(i-1));
					if(i==lastIndex) p.addNeighbor(this.particles.get(0));
					else p.addNeighbor(this.particles.get(i+1));
				}
				
				break; 
			case von_neumann:
				// particles are imagined to be in a grid (that wraps around in both directions) 
				// the neighbors of each particle are the particles above, below, and to the left and right of it
				
				int rowLength = (int)Math.sqrt(this.particles.size());
				int particles2d[][] = new int[rowLength][rowLength];
				int pRow,pCol; // p's row and col
				
				// fill 2d array with index of particle.
				for (int i=0; i<this.particles.size(); i++){
					
					int r,c; // row and column indeces of 2d array
					if((i%rowLength) > 0){
						r = (int) i/rowLength;
						c = i%rowLength;
					}else{
						r = (int)i/rowLength;
						c = 0;
					}
					particles2d[r][c] = i;
					
					if(i == p.getIndex()){
						pRow = r;
						pCol = c;
					}
				}
				
				// go through the 2d array and check if row xor col matches p's, if so, add as neighbor
				try { //for debugging*
					for(int r=0; r<rowLength; r++){
						for(int c=0; c<rowLength; c++){
							if(r == pRow ^ c == pCol){
								int index = particles2d[r][c];
								p.addNeighbor(this.particles.get(index));
							}
						}
					}
					
		        } catch (Exception e) {
		            //catch an error resulting from passing less velocity/position components than expected
		            System.out.println("pRow not set for some odd reason");
		            System.exit(0);
		        }
				
				break;
			case random:
    			// clear neighborhood and add iterate through particles, adding each with chance
				// of RANDOMTOPOLOGY_PROBABILITY (default .5, but modifiable for testing purposes)
				p.clearNeighbors();
				
				for (Particle q : this.particles)
				{
					if(rand.nextDouble() > RANDOMTOPOLOGY_PROBABILITY && p != q)
				    	p.addNeighbor(q);
				}
    			break;
    		default:
    		    // shouldn't ever get here
    		    // default to gbest, neighborhood is entire swarm
				for (Particle q : this.particles)
				{
				    p.addNeighbor(q);
				}
				//System.out.println("defaulted to gbest"); // for debugging#
				//System.exit(0)
    		    break;
				
			//each of these will be impemented separately according to the "famous" topology details
		}
		if(includeSelf)
			p.addNeighbor(p);
	}
	//# means remove before submission if unnecessary #
}
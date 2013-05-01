import java.util.*;

/****************
Swarm is a java class for handling the behavior of a Particle Swarm.
It uses methods supplied by Particle.java to update and manipulate individual
particles in a swarm.
****************/
public class Swarm
{
	public static final double MAX_VELOCITY = 10000; // for any direction/dimension
	public static final double MAX_POSITION = 10000; // for any direction/dimension
	public static final double DEFAULTS = 0; // inertia, phi, other constants
	
	// resistance of a particle to change velocity
	public static final double DEF_INERTIA = 0;
	
	public static final double PHI = 2.05;
	
	// number of dimensions has no limit (other than hard drive space/allocation restrivtions for arrays)
	private int dimensions;
	private topologyEnum topology;
	private Boolean includeSelf;
	private String influenceStructure;
	private int size; // number of particles in swarm
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
	public Swarm(topologyEnum topology, String includeSelf, String influenceStructure, int swarmSize, String function, int dimensions) {

        // set the best to a very high (bad) value
		globalBest = 999999;
		
		//initialize swarm variables
		this.dimensions = dimensions;
		this.topology = topology;
		if (includeSelf.equalsIgnoreCase("yes")) this.includeSelf = true;
		else this.includeSelf = false;
		this.influenceStructure = influenceStructure;
		this.size = size;
        // inertia = DEF_INERTIA;

		// initialize the particle array
		ArrayList<Particle> newParticles = new ArrayList<Particle>();
		
		for(int i = 0; i < swarmSize; i++){
		    // particle constructor takes these arrays as parameters
			List velocity = new ArrayList<Double>();
			List position = new ArrayList<Double>();
			
			// set position and veocity for each dimension with random values
			for (int j = 0; j < dimensions; j++){
                // position.get(j) = rand.nextDouble() * maxPosition[i];
				position.set(j, rand.nextDouble() * MAX_POSITION);
				velocity.set(j, rand.nextDouble() * MAX_VELOCITY);
			}
			
			Particle p = new Particle(velocity, position);
			newParticles.set(i,p);
		}
		
		// sets this swarm objects particle array to the new one we just created
		this.particles = newParticles;
	}
	
	// this is where the "meat" of the PSO algorithm lies (we move each particle to a new location)
	//called repeatedly by PSO.java
	public void update(){
		
		for(int i=0; i<this.particles.size(); i++){
		    //grab the current velocities and save them as old
		    Particle p = (Particle)this.particles.get(i);
		    List oldVel = p.getVel();
			List oldPos = p.getPos();
		    						
			//new velocities will be formed using the acceleration, old velocity and position
			List newVel = new ArrayList<Double>();
			List newPos = new ArrayList<Double>();
			
			double accTowardPBest[] = new double[this.dimensions];
			double accTowardNBest[] = new double[this.dimensions];
			// loop through dimensions, calculate and update velocity and position
			for (int j = 0 ; j < this.dimensions; j++){
				//determine the amount of accel towards both the personal and the global best
				double randPhi1 = rand.nextDouble()*phi1;
				double randPhi2 = rand.nextDouble()*phi2;
				
				//NEED TO FIX THIS!!!
				/*****************
				
				//accTowardPBest[i] = randPhi1 * p.getPBestPos();
				//accTowardNbest[i] = randPhi2 * p.getNBestPos();
				
				
				
				******************/
				
				//actually calculate the new velocity and position from it
				newVel.set(i, (((Double)oldVel.get(i) + accTowardPBest[i] + accTowardNBest[i]) * constrictionFactor));
				newPos.set(i, ((Double)oldPos.get(i) + (Double)newVel.get(i)));
			}
			
			//send the vel/pos back to the Particle class
			p.setVel(newVel);
			p.setPos(newPos);
		}
	}

    //Int
	public void updateLocalBest(Particle p){
		for(Particle neighbor : p.getNeighborhood()){
			//find out which neighbor has the highest value
			//use the position of that neighbor to shift the position of this particle
		}
	}

	//what happens in this function depends on specific implementations of the topologies,
	//which I don't think it's appropriate for us to do in a "sketch."
	//It'll probably involve Particle's .addNeighbor(Particle) and .removeNeighbor(Particle) methods, though.
	public void updateNeighborhood(Particle p){
        // int enumval = ValueEnum.fromString(this.topology);
		switch(this.topology){
			case gbest:
				// neighborhood is entire swarm
				for (Particle q : this.particles)
				{
				    p.addNeighbor(q);
				}
				break;
			case ring:
				// particles are imagined to be in a ring and the neighbors of each particle are the particles to the left and right of it

				// if (object exists to the left of p in this.particles) -> add it
				// else (object is first particle, so add the last particle in this.particles)
				// if (object exists to the right of p) -> add it
				// else (object is last particle, so add the first particle in this.particles)
				
				break; 
			case von_neumann:
				// particles are imagined to be in a grid (that wraps around in both directions) 
				// the neighbors of each particle are the particles above, below, and to the left and right of it
				break;
			case random:
    			//stuff
    			break;
    		default:
    		    // shouldn't ever get here
    		    // default to gbest, neighborhood is entire swarm
    		    break;
				
			//each of these will be impemented separately according to the "famous" topology details
		}
		if(includeSelf)
			p.addNeighbor(p);
	}
	
}
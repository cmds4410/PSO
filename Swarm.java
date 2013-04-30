import java.util.*;

/****************
Swarm is a java class for handling the behavior of a Particle Swarm.
It uses methods supplied by Particle.java to update and manipulate individual
particles in a swarm.
****************/
public class Swarm
{
	public static final double MAX_VELOCITY = ?; // for any direction/dimension
	public static final double //DEFAULTS = ?; // inertia, phi, other constants
	
	// resistance of a particle to change velocity
	public static final double DEF_INERTIA = ??;
	
	public static final double PHI = 2.05;
	
	// number of dimensions has no limit (other than hard drive space/allocation restrivtions for arrays)
	private int dimensions;
	private String topology;
	private Boolean includeSelf;
	private String influenceStructure;
	private int size; // number of particles in swarm
	private double globalBest;
	private int bestParticle; // index of best particle
	
	//holds the mximum velocity (all components) that the particles can reach
	private Array<double> maxVelocity; 
	private Array<double> minVelocity;
	
	//holds the max and min positions which the particle can travel to
	private Array<double> maxVelocity; 
	private Array<double> minVelocity;
	
	//holds all of the particles in our swarm
	private Array<Particle> particles;
	
	private double phi1 = phi2 = PHI;
	
	//just for ease of use in calculating the constriction factor
  	private double phisum = phi1 + phi2;
  	
  	public  double constrictionFactor = 2.0 / (phisum - 2.0 + Math.sqrt(phisum*phisum - 4.0*phisum));
	
	private Random rand = new Random();
	
	// this is the constructor of a swarm, which is created by PSO.java
	public Swarm(String topology, String includeSelf, String influenceStructure, int swarmSize, String function, int dimensions) {

        // set the best to a very high (bad) value
		globalBest = 999999;
		
		//initialize swarm variables
		this.dimensions = dimensions;
		this.topology = topology;
		if (includeSelf.equalsIgnoreCase("yes") this.includeSelf = TRUE;
		else this.includeSelf = FALSE;
		this.influenceStructure = influenceStructure;
		this.size = size;
		inertia = DEF_INERTIA;

		// initialize the particle array
		newParticles = new Array<Particle>;
		
		for(int i = 0; i < swarmSize; i++){
		    // particle constructor takes these arrays as parameters
			Array<double> velocity;
			Array<double> position;
			
			// set position and veocity for each dimension with random values
			for (int j = 0; j < dimensions){
				position[j] = rand.nextDouble() * maxPosition[i];
				velocity[j] = rand.nextDouble() * maxVelocity[i];
			}
			
			newParticles[i] = new Particle(intialV, initialP)
		}
		
		// sets this swarm objects particle array to the new one we just created
		this.particles = newParticles;
	}
	
	// this is where the "meat" of the PSO algorithm lies (we move each particle to a new location)
	//called repeatedly by PSO.java
	public void update(){
		
		for(int i=0; i<this.particles.size; i++){
		    //grab the current velocities and save them as old
		    Array<double> oldVel = particles[i].getVel();
			Array<double> oldPos = particles[i].getPos();
		    						
			//new velocities will be formed using the acceleration, old velocity and position
			newVel = new Array<double>;
			newPos = new Array<double>;
			
			// loop through dimensions, calculate and update velocity and position
			for (int j = 0 ; j < self.dimensions; j++){
				//determine the amount of accel towards both the personal and the global best
				double randPhi1 = rand.nextDouble()*phi1;
				double randPhi2 = rand.nextDouble()*phi2;
				accTowardPBest[i] = randPhi1 * particles[i].getPBestPos();
				accTowardNbest[i] = randPhi2 * particles[i].getNBestPos();
				
				//actually calculate the new velocity and position from it
				newVel[i] = (oldVel[i] + accToPBest[i] + accToNBest[i]) * constrictionFactor;
				newPos[i] = (oldPos[i] + newVel[i]);
			}
			
			//send the vel/pos back to the Particle class
			particles[i].setVel(newVel, newPos);
			particles[i].setPos(newPos);
		}
	}

	public Array<int> updateLocalBest(Particle p){
		for(neighbor:p.getNeighborhood()){
			//find out which neighbor has the highest value
			//use the position of that neighbor to shift the position of this particle
		}
	}

	//what happens in this function depends on specific implementations of the topologies,
	//which I don't think it's appropriate for us to do in a "sketch."
	//It'll probably involve Particle's .addNeighbor(Particle) and .removeNeighbor(Particle) methods, though.
	public void updateNeighborhood(Particle p){
		switch(topology){
			case "van_neumann":
				//stuff
				break;
			case "ring":
				//stuff
				break; 
			case "random":
				/stuff
				break;
				
			//each of these will be impemented separately according to the "famous" topology details
		}
		if(includeSelf)
			p.addNeighbor(p);
	}
	
}
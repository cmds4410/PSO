import java.util.*;


public class Swarm
{
	public static final double MAX_VELOCITY = ?; // for any direction/dimension
	public static final double //DEFAULTS = ?; // inertia, phi, other constants
	public static final double DEF_INERTIA = ??;
	public static final double
	public static final double
	public static final double
	
	public static final 
	public static final 
	
	
	private int dimensions;
	private String topology;
	private Boolean includeSelf;
	private String influenceStructure;
	private int size; // number of particles in swarm
	private double globalBest;
	private int bestParticle; // index of best particle
	
	private double inertia;
	private Array<double> maxPosition;
	private Array<double> minPosition;
	private Array<double> maxVelocity; 
	private Array<double> minVelocity;
	
	private Array<Particle> particles;
	
	private Random rand = new Random();
	
	public Swarm(String topology, String includeSelf, String influenceStructure, int swarmSize, String function, int dimensions) {

		globalBest = 999999;
		
		//initialize swarm variables
		this.dimensions = dimensions;
		this.topology = topology;
		if (includeSelf.equalsIgnoreCase("yes") this.includeSelf = TRUE;
		else this.includeSelf = FALSE;
		this.influenceStructure = influenceStructure;
		this.size = size;
		inertia = DEF_INERTIA;

		
		newParticles = new Array<Particle>;
		
		for(int i = 0; i < swarmSize; i++){
			Array<double> initialV;
			Array<double> initialP;
			
			//set position and veocity for each dimension
			for (int j = 0; j < dimensions){
				double position = rand.nextDouble() * maxPosition[i];
				initialP[i] = position;
				double velocity = rand.nextDouble() * maxVelocity[i];
				initialV[i] = velocity;
			}
			
			newParticles[i] = new Particle(intialV, initialP)
		}
		this.particles = newParticles;
	}
	
	public void update(){
		
		for(int i=0; i<self.size; i++){
			Array<double> currVel = particles[i].getVel();
			Array<double> currPos = particles[i].getPos();
			
			
			double randPhi1 = rand.nextDouble()*ph1;
			
			accToPBest = new Array<double>;
			accToGbest = new Array<double>;
			newVel = new Array<double>;
			newPos = new Array<double>;
			Array<double> oldVel = particles[i].getVel();
			Array<double> oldPos = particles[i].getPos();
			
			// loop through dimensions
			for (int j = 0 ; j < self.dimensions; j++){
				accTowardPBest[i] = randPhi1 * particles[i].getPBestPos();
				accTowardGbest[i] = randPhi2 * particles[i].getNBestPos(); //need getneighborhood best position method
				newVel[i] = (oldVel[i] + accToPBest[i] + accToGBest[i]) * constrictionFactor;
				newPos[i] = (oldPos[i] + newVel[i]);
			}
			
			particles[i].setVel(newVel);
			particles[i].setPos(newPos);
		}
	}
	
}
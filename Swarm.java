import java.util.*;


public class Swarm
{
	public static final MAX_VELOCITY = ?;
	public static final DEFAULT_INERTIA = ?;
	public static final DEFAULT_CONSTRAINTS = ?;
	
	private dimensions;
	private double globalBest;
	private int bestParticle; // index of best particle
	private double inertia;
	private double maxPosition[];
	private double minPosition[];
	private double maxVelocity[]; 
	private double minVelocity[];
	private int size; // number of particles in swarm
	
	private Array<Particle> particles;
	
	private Random rand = new Random();
	
	public Swarm(int swarmSize) {

		globalBest = 999999;
		newParticles = new Particle[swarmSize];
		for(int i = 0; i < swarmSize; i++){
			double[] initialV;
			double[] initialP;
			for (int j = 0; j < dimensions){
				double velocity = rand.nextDouble() * maxVelocity[i];
				initialV[i] = velocity;
				double position = rand.nextDouble() * 
			}
			
			particles[i] = new Particle(intialV, initialP)
		}
		this.particles = newParticles;
	}
	
	public void step(){
		
		for(int i=0; i<self.size; i++){
			particles
			
		}
	}
	
}
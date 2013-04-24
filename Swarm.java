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
			particles[i].
			
		}
	}

	public Array<int> updateLocalBest(Particle p){
		Array<int> weightedPositionSum;
		Array<double> weightedValueSum;
		for(neighbor:p.getNeighborhood()){
			//weight "neighbor"'s position with its value
			//add that to the position sum
			//add its value to the weightedValueSum
		}
		return weightedPositionSum/weightedValueSum;
		//I know you can't just divide Arrays, but bear with me.
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
		}
		if(includeSelf)
			p.addNeighbor(p);
	}
	
}
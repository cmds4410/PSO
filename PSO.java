import java.util.*;
import java.io.*;
import java.lang.*;
import java.text.*;



public class PSO
{
	public static String NEIGHBORHOOD_TOPOLOGY;
	public static String INCLUDE_SELF;
	public static String INFLUENCE_STRUCTURE;
	public static String SWARM_SIZE;
	public static String NUM_ITER;
	public static String FUNCTION;
	public static int DIMENSIONS;
  
	public static void main(String args[]){
		NEIGHBORHOOD_TOPOLOGY = args[0];
		INCLUDE_SELF = args[1];
		INFLUENCE_STRUCTURE = args[2];
		SWARM_SIZE = args[3];
		NUM_ITER = args[4];
		FUNCTION = args[5];
		DIMENSIONS = args[6];

		//time the program's execution
		double startTime = System.CurrentTimeMillis();

		//create and randomize all of the individuals in the swarm, using params specified on command line
		Swarm s = new Swarm (NEIGHBORHOOD_TOPOLOGY, INCLUDE_SELF, INFLUENCE_STRUCTURE, SWARM_SIZE, FUNCTION, DIMENSIONS);

		//actually run the algorithm
		for(i:NUM_ITER){
			s.update();
		}

		double t = (System.CurrentTimeMillis() - startTime)/1000;

		System.out.println("The algorithm is complete. The best value found was " + s.getBestVal());

		System.out.println("Time to completion: " + t + "sec");
	}
}
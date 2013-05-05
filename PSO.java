import java.util.*;
import java.io.*;
import java.lang.*;
import java.text.*;

public class PSO
{
	public static String NEIGHBORHOOD_TOPOLOGY;
	public static String INCLUDE_SELF;
	public static String INFLUENCE_STRUCTURE;
	public static int SWARM_SIZE;
	public static int NUM_ITER;
	public static String FUNCTION;
	public static int DIMENSIONS;
  
	public static void main(String args[]){
	    
	    // check if user entered incorrect args
	    if(args.length != 7)
            {
                System.out.println("Incorrect args! " + args.length);
                System.out.println("Input: NEIGHBORHOOD_TOPOLOGY, INCLUDE_SELF, INFLUENCE_STRUCTURE, SWARM_SIZE, NUM_ITER, FUNCTION, DIMENSIONS");
                System.exit(0);
            }
	    String top = args[0];
		NEIGHBORHOOD_TOPOLOGY = top;
		INCLUDE_SELF = args[1];
		INFLUENCE_STRUCTURE = args[2];
		SWARM_SIZE = Integer.parseInt(args[3]);
		NUM_ITER = Integer.parseInt(args[4]);
		FUNCTION = args[5];
		DIMENSIONS = Integer.parseInt(args[6]);

		//create and randomize all of the individuals in the swarm, using params specified on command line
		Swarm s = new Swarm (NEIGHBORHOOD_TOPOLOGY, INCLUDE_SELF, INFLUENCE_STRUCTURE, SWARM_SIZE, FUNCTION, DIMENSIONS, NUM_ITER);

		//actually run the algorithm
		
		for(int i = 0; i < NUM_ITER; i++){
			s.update();
            System.out.print(s.getBestVal(i)+",");
		}
	}
}
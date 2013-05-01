import java.lang.Math.*;
import java.util.*;

public class Particle
{	
    
    private List velocity; //double
	private List position; //double
    private List pBestPos; //int
	
    private double personalBest;
    private double neighborhoodBest;
    private double currValue;
    
	private Random rand = new Random();

    private List<Particle> myNeighbors;

    // double both
    public Particle(List v, List p) {

        try {
            velocity = v;
            position = p;
        } catch (Exception e) {
            //catch an error resulting from passing less velocity/position components than expected
            System.out.println("Error: Sudden change in dimensionality.");
            System.exit(0);
        }

    }
    
    //setter for the velocities 
    //double
    public void setVel(List velComps)
    {
        try {
            velocity = velComps;

        } catch (Exception e) {
            System.out.println("Error: Sudden change in dimensionality.");
            System.exit(0);
        }
    }
    
    //setter for the positions
    //double
    public void setPos(List posCoords)
      {
          try {
              position = posCoords;

          } catch (Exception e) {
              System.out.println("Error: Sudden change in dimensionality.");
              System.exit(0);
          }
      }
                
    //double
    public List getVel(){
    	return velocity;
    }

    //double
   	public List getPos(){
   		return position;
    }

    //set the personal best position
    //int? then double param
    public void setPBestPos(List a){
        pBestPos = a;
    }

    public List getPBestPos(){
        return pBestPos;
    }

    public void setPBest(double pbest){
    	personalBest = pbest;
    }

    public double getPBest(){
        return personalBest;
    }

    public void addNeighbor(Particle p){
        myNeighbors.add(p);
    }

    public void removeNeighbor(Particle p){
        myNeighbors.remove(p);
    }

    public double getNBest(){
        return neighborhoodBest;
    }

    public void updateNBest(){
        for(Particle p : myNeighbors){
            if(p.getVal() < neighborhoodBest)
                neighborhoodBest = p.getVal();
        }
    }

    //NOTE: "being neighbors," under this logic, does NOT have to be a "two-way street."
    public Boolean isNeighborOf(Particle p){
        /*
        if(List.asList(myNeighbors).contains(p))
            return true;
        else return false;
        */
        return true;
    }

    public List<Particle> getNeighborhood(){
        return myNeighbors;
    }

    public double getVal(){
        return currValue;
    }

    public void setVal(double val){
        currValue = val;
    }
}
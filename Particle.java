import java.Math*;
import java.util.*;

public class Particle
{	
	
    private Array<double> velocity;
	private Array<double> position; 
    private Array<double> pBestPos;
	
    private double personalBest;
    
	private Random rand = new Random();

    private Array<Particle> myNeighbors;

    public Particle(Array<double> v, Array<double> p) {

        try {
            velocity = v;
            position = p;
        } catch (Exception* e) {
            System.out.println("Error: Sudden change in dimensionality.");
            System.exit(0);
        }
        
    }

    public void setVel(Array<double> velComps)
    {
        try {
            velocity = velComps;
        } catch (Exception* e) {
            System.out.println("Error: Sudden change in dimensionality.");
            System.exit(0);
        }
    }

    public void setPos(Array<double> posCoords)
    {	
        try {
            position = posCoords;
        } catch (Exception* e) {
            System.out.println("Error: Sudden change in dimensionality.");
            System.exit(0);
        }
    }

    public Array<double> getVel(){
    	return velocity;
    }

   	public Array<double> getPos(){
   		return position;
    }

    public void setPBest(double pbest){
    	personalBest = pbest;
    }

    public void addNeighbor(Particle p){
        myNeighbors.add(p);
    }

    //NOTE: "being neighbors," under this logic, does NOT have to be a "two-way street."
    public Bool isNeighborOf(Particle p){
        if(Arrays.asList(myNeighbors).contains(p))
            return true;
        else return false;
    }

    public Array<Particle> getNeighborhood(){
        return myNeighbors;
    }
}
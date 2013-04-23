import java.Math*;
import java.util.*;

public class Particle
{	
	
    private Array<double> velocity;
	private Array<double> position; 
    private double personalBest;

	private Array<double> pBestPos;
	private double currentFitness;
    
	private Random rand = new Random();

    public Particle(double v, double p) {

        velocity = v;
        position = p;
        
    }

    public void setVel(Array<double> velComps)
    {
		velocity = velComps;
    }

    public void setPos(Array<double> posCoords)
    {
		position = posCoords;
    }

    public Array<double> getVel(){
    	return velocity;
    }

   	public Array<double> getPos(){
   		return position;
    }

    public setPBest(double pbest){
    	personalBest = pbest;
    }

    
}
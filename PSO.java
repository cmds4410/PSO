import java.util.*
import java.io.*;
import java.lang.*;
import java.text.*;



public class PSO
{

	Array<Particle> population = new Array<Particle>;
	public static int FUNCTION;
	public static String FILENAME;

	public static void main(String args[]){
		FUNCTION = args[0];
		FILENAME = args[1];
		randomizeParticles();
		for(i:population){
			updateVelocity(i);
			updatePosition(i);
			getValueForPosition(i.getPos());
			updatePersonalBest(i);
			updateGlobalBest();
		}
	}

	public void randomizeParticles()
	{

	}

	public void updateVelocity(Particle p)
	{
		p.setVel();
	}

	public void updatePosition(Particle p)
	{
		p.setPos();
	}

	public void updatePersonalBest(Particle p)
	{
		p.setPBest();
	}

	public void updateGlobalBest()
	{

	}

	private double evalParticleWithFunction(Particle p){
		switch(FUNCTION):
		{


		}
	}

}
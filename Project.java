import java.util.Random;

public class Project implements Comparable {
	float x1;
	float x2;
	float x3;
	float x4;

	//	Default Constructor
	Project(){
	}

	//	Parameterized Constructor
	Project(float a,float b,float c,float d){
		x1 = a;
		x2 = b;
		x3 = c;
		x4 = d;
	}

	//	Function to Initialize Random Projects while checking the constraints
	public void initializeProject() {
		Random rn = new Random();
		do
		{x1 = Math.abs(rn.nextInt() % 2);
		x2 = Math.abs(rn.nextInt() % 2);
		x3 = Math.abs(rn.nextInt() % 2);
		x4 = Math.abs(rn.nextInt() % 2);
		}while(!checkConstraints());
	}

	//  Function to display project
	public  void displayProject() {
		System.out.println((int)x1+" "+(int)x2+" "+(int)x3+" "+(int)x4  );
	}

	//  Function to check the fitness of the project
	public float fitness() {
		float fitnessValue=0;
		fitnessValue =   (float) ((0.2* x1 )+(0.3* x2) + (0.5 *x3) + (0.1*x4));
		return fitnessValue;
	}

	// Function to check for the duplicates in the project (not used)
	public boolean checkForDuplicate(Project a , Project b) {
		if(a.x1 == b.x1 && a.x2 == b.x2 && a.x3 == b.x3 && a.x4 == b.x4) {
			return false;
		}else {
			return true;
		}
	}

	//	Function to check the constraints of the Project
	public boolean checkConstraints() {
		boolean validConstraint=false;
		float budgetYear1=0 , budgetYear2=0 , budgetYear3=0;
		budgetYear1 =  (float) ((0.5* x1 )+(1.0* x2) + (1.5 *x3) + (0.1*x4));
		budgetYear2 =  (float) ((0.3* x1 )+(0.8* x2) + (1.5 *x3) + (0.4*x4));
		budgetYear3 =  (float) ((0.2* x1 )+(0.2* x2) + (0.3 *x3) + (0.1*x4));

		if(budgetYear1<=3.1 && budgetYear2 <= 2.5 && budgetYear3 <0.5) {
			validConstraint=true;
		}
		return validConstraint;
	}

	@Override
	public int compareTo(Object o) {
		return  (this.fitness() > ((Project) o).fitness() ? -1 : (this.fitness() == ((Project) o).fitness() ? 0 : 1));
	}

}

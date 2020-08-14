import java.util.*;

/*               Genetic Algorithm
 *  Contains Project Class 
 * 	There are 2 Major Functions : crossoverFunction() , mutationFunction
 * 	The other 2 functions are to check the constraints and check the fitness of the project
 *  1)The Project starts with 4 initial Random Projects(Generations 0) that fulfill the constraints 
 *  and are ordered in Sorted Manner based on the fitness function (Duplicates are allowed).
 *  2)The Projects go for 80 Generations.
 *  3)The probability of Crossover is 75 %.
 *  4)The Probability of Mutation is  10%.
 *  5)The best 4 projects are always considered and are sorted in decreasing manner.
 *  
 */


public class GeneticAlgorithm {
	public static void main(String[] args) {
		int maxIndex=4;
		int generations = 80;
		int iterations = 0;

		Project[] population = new Project[4];
		for(int i =0 ; i<4; i++) {
			Project p = new Project();
			population[i] = p;

		}
		for(int i=0 ; i < 4; i++) {
			population[i].initializeProject();
		}
		Arrays.sort(population);
		System.out.println("Generation 0 is");
		for(int i=0 ; i < 4; i++) {
			population[i].displayProject();
			System.out.println(checkConstraints(population[i]));
			System.out.println(checkFitness(population[i]));
		}
		System.out.println("---------------------------------------------------");

		for(iterations= 1 ; iterations <generations ; iterations++) {
			System.out.println(" ");
			System.out.println("----- Generation "+iterations+" -----");
			crossoverFunction(population);
			mutationFunction(population);
		}

	}

	//...................... Function to PERFORM CROSSFUNCTION .............................
	public static void crossoverFunction(Project[] p) {
		System.out.println("-------- CrossOver ----");
		Random rn = new Random();

		Project[] newChromosomes = new Project[3];
		int index=0;
		for(int i=0;i<3;i++) {
			int prob = rn.nextInt(100-1) + 1;
			Project newProjectAfterCrossOver = new Project();
			System.out.println("Probability of CO happening : " +prob);
			if(prob <75) {
				System.out.println("Performing crossover on "+ i +" and "+ (i+1));
				int crossOverPoint = Math.abs(rn.nextInt() % 3)+1;
				System.out.println("CrossOver Point : "+crossOverPoint);
				if(crossOverPoint==1) {
					newProjectAfterCrossOver.x1= p[i].x1; 
					newProjectAfterCrossOver.x2= p[i+1].x2;
					newProjectAfterCrossOver.x3= p[i+1].x3;
					newProjectAfterCrossOver.x4= p[i+1].x4;
				}else if(crossOverPoint==2)
				{
					newProjectAfterCrossOver.x1= p[i].x1; 
					newProjectAfterCrossOver.x2= p[i].x2;
					newProjectAfterCrossOver.x3= p[i+1].x3;
					newProjectAfterCrossOver.x4= p[i+1].x4;
				}else {
					newProjectAfterCrossOver.x1= p[i].x1; 
					newProjectAfterCrossOver.x2= p[i].x2;
					newProjectAfterCrossOver.x3= p[i].x3;
					newProjectAfterCrossOver.x4= p[i+1].x4;
				}

				if(checkConstraints(newProjectAfterCrossOver)) {
					newChromosomes[index]= newProjectAfterCrossOver;
					System.out.println("New Project Follows Constraint and its fitness value is " +checkFitness(newProjectAfterCrossOver));
					index++;
				}else {
					System.out.println("New Project does not follow the constraint");
				}
			}
		}
		if(index != 0) {
			boolean replace = false;
			for(int j=0 ;  j<index; j++) {
				innerLoop:
					for(int k=0 ; k<4 ; k++) {
						if(checkFitness(newChromosomes[j])>=checkFitness(p[k])) {
							p[k]=newChromosomes[j];
							System.out.println("New Chromosome is "); 
							newChromosomes[j].displayProject();
							replace = true;
							break innerLoop;
						}
					}

			}
			if(replace) {
				System.out.println("New Set Of Chromosomes are :");
				System.out.println(" ");
				for(int i=0 ; i < 4; i++) {
					p[i].displayProject();
					System.out.println(checkConstraints(p[i]));
					System.out.println(checkFitness(p[i]));
				}


			}else {
				System.out.println("Did not replace any chromosome as its fitness were below the current Projects");
			}
		}
	}

	//............ Function to CHECK FITNESS .................................................
	public static float checkFitness(Project p) {
		float fitnessValue=0;
		fitnessValue =   (float) ((0.2* p.x1 )+(0.3* p.x2) + (0.5 *p.x3) + (0.1*p.x4));
		return fitnessValue;
	}
	//............ Function to CHECK CONSTRAINTS ..............................................
	public static boolean checkConstraints(Project p ) {
		boolean validConstraint=false;
		float budgetYear1=0 , budgetYear2=0 , budgetYear3=0;
		budgetYear1 =  (float) ((0.5* p.x1 )+(1.0* p.x2) + (1.5 *p.x3) + (0.1*p.x4));
		budgetYear2 =  (float) ((0.3* p.x1 )+(0.8* p.x2) + (1.5 *p.x3) + (0.4*p.x4));
		budgetYear3 =  (float) ((0.2* p.x1 )+(0.2* p.x2) + (0.3 *p.x3) + (0.1*p.x4));

		if((budgetYear1<=3.1) && (budgetYear2 <= 2.5) && (budgetYear3 <0.5)) {
			validConstraint=true;
		}
		return validConstraint;
	}

	//............. Function to PERFORM MUTATION ..............................................
	public static void mutationFunction(Project[] p) {
		Random rn = new Random();
		boolean change = false;
		Project mutatedProject = new Project();
		System.out.println("-------- Peroforming Mutation ------------");
		for(int i=0;i<4;i++) {
			int prob = rn.nextInt(100-1) + 1;

			System.out.println("Random Probability is :" + prob);
			if(prob<10) {
				change=true;
				//Select a random mutation point
				int mutationPoint = Math.abs(rn.nextInt() % 4)+1;
				//Flip values at the mutation point
				switch(mutationPoint) {
				case 1:
					if(p[i].x1 == 0) {
						mutatedProject.x1=1;
						mutatedProject.x2=p[i].x2;
						mutatedProject.x3=p[i].x3;
						mutatedProject.x4=p[i].x4;

					}else {
						mutatedProject.x1=0;
						mutatedProject.x2=p[i].x2;
						mutatedProject.x3=p[i].x3;
						mutatedProject.x4=p[i].x4;
					}
					break;
				case 2:
					if(p[i].x2 == 0) {
						mutatedProject.x2=1;

						mutatedProject.x1=p[i].x1;
						mutatedProject.x3=p[i].x3;
						mutatedProject.x4=p[i].x4;
					}else {
						mutatedProject.x2=0;
						mutatedProject.x1=p[i].x1;
						mutatedProject.x3=p[i].x3;
						mutatedProject.x4=p[i].x4;
					}
					break;
				case 3:
					if(p[i].x3 == 0) {
						mutatedProject.x3=1;
						mutatedProject.x1=p[i].x1;
						mutatedProject.x2=p[i].x2;
						mutatedProject.x4=p[i].x4;
					}else {
						mutatedProject.x3=0;
						mutatedProject.x1=p[i].x1;
						mutatedProject.x2=p[i].x2;
						mutatedProject.x4=p[i].x4;
					}
					break;
				case 4:
					if(p[i].x4 == 0) {
						mutatedProject.x4=1;
						mutatedProject.x1=p[i].x1;
						mutatedProject.x2=p[i].x2;
						mutatedProject.x3=p[i].x3;
					}else {
						mutatedProject.x4=0;
						mutatedProject.x1=p[i].x1;
						mutatedProject.x2=p[i].x2;
						mutatedProject.x3=p[i].x3;
					}
					break;
				}
				if(change && checkConstraints(mutatedProject)) {
					boolean replace = false;
					for(int k=0 ; k<4 ; k++) {
						if(checkFitness(mutatedProject)>=checkFitness(p[k])) {
							p[k]=mutatedProject;
							System.out.println("New Chromosome is "); 
							mutatedProject.displayProject();
							replace = true;
							break;
						}
					}
					if(replace) {
						System.out.println("New Set Of Chromosomes are :");
						System.out.println(" ");
						for(int j=0 ; j < 4; j++) {
							p[j].displayProject();
							System.out.println(checkConstraints(p[j]));
							System.out.println(checkFitness(p[j]));
						}
					}else {
						System.out.println("Did not replace any chromosome as its fitness were below the current Projects");
					}
				}
			}
		}
		if(!change)
			System.out.println("Did not perform Mutation"); 
	}


}


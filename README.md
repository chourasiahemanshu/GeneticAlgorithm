# GeneticAlgoritm
Assignment for Pattern Recognition displaying an example of how Genetic Algorithm works

Variables: x1, x2, x3 and x4 are binary variables, representing the investment decisions of project 1, 2, 3, and 4. 

Value 1 indicating investment, while a value 0 indicating not investing in the project.

Constraints: 
Projects have some constraints that have to be followed if they are being run for 3 years and it should fulfill the following criteria:
1.	0.5x1 + 1.0x2 + 1.5 x3 + 0.1 x4 <= 3.1
2.	0.3x1 + 0.8x2 + 1.5 x3 + 0.4 x4 <= 2.5
3.	0.2x1 + 0.2x2 + 0.3 x3 + 0.1 x4 <= 0.4

Fitness Function: 
The aim of this problem is to maximize the Fitness Function 

F(x1 , x2, x3, x4) =  0.2x1 + 0.3x2 + 0.5x3 + 0.1x4

For Java
The Code contains 2 files.

GeneticAlgorithm.java and Project.java, GeneticAlgorithm.java is the main code to run 


/*               Genetic Algorithm.java
 *  Contains Project Class 
 * 	There are 2 Major Functions : crossoverFunction() , mutationFunction()
 * 	The other 2 functions are to check the constraints and check the fitness of the project
 *  1)The Project starts with 4 initial Random Projects(Generations 0) that fulfill the constraints 
 *  and are ordered in Sorted Manner based on the fitness function (Duplicates are allowed).
 *  2)The Projects go for 80 Generations.
 *  3)The probability of Crossover is 75 %.
 *  4)The Probability of Mutation is  10%.
 *  5)The best 4 projects are always considered and are sorted in decreasing manner.
 */
 
 
 
 
Crossover code:
In the whole Population, we start by pairing the projects and then randomly checking if crossover would be performed on them or not and gradually moving to the next projects. For crossover a random split point is chosen and the first half of the parent and the second half of the other parent is combined to form the child, Constraints are checked and its fitness value is calculated and if it is more than the previous parents it is included in the current population or else discarded.
Mutation code:
In the whole population, each member based on probability(5%) is checked if he is going to mutate. If yes, a random integer is selected and its value is flipped. Again constraints are checked and its fitness value is calculated and if it is more than the previous parents it is included in the current population or else discarded.
Stopping Criteria:
The whole process is repeated for the 80 generations.


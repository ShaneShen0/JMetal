package shane.JMetal;

import org.uma.jmetal.problem.impl.AbstractIntegerPermutationProblem;
import org.uma.jmetal.solution.PermutationSolution;

import java_cup.internal_error;

public class MultiObjTSPProblem extends AbstractIntegerPermutationProblem {
	private int numberOfCities;
	private int[][] distance;
	private int[][] cost;
	public MultiObjTSPProblem() {
		// TODO Auto-generated constructor stub
		this.numberOfCities = 34;
		this.distance = City.cityDis;
		this.cost = City.cityCost;
		setNumberOfObjectives(2);
		setNumberOfVariables(this.numberOfCities);
	}
	public int getPermutationLength() {
		// TODO Auto-generated method stub
		return numberOfCities;
	}

	public void evaluate(PermutationSolution<Integer> solution) {
		int sumOfDis=0;
		int sumOfCost = 0;
		for(int i =0;i<numberOfCities-1;i++) {
			int from = solution.getVariableValue(i);
			int to = solution.getVariableValue(i+1);
			sumOfDis += distance[from][to];
			sumOfCost += cost[from][to];
		}
		
		int firstCity = solution.getVariableValue(0);
		int lastCity = solution.getVariableValue(numberOfCities-1);
		sumOfDis += distance[firstCity][lastCity];
		sumOfCost += cost[firstCity][lastCity];
		
		solution.setObjective(0, sumOfDis);
		solution.setObjective(1, sumOfCost);
	}

}

package shane.JMetal;

import java.util.List;

import javax.sound.sampled.LineListener;

import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.algorithm.multiobjective.nsgaii.NSGAIIBuilder;
import org.uma.jmetal.operator.CrossoverOperator;
import org.uma.jmetal.operator.MutationOperator;
import org.uma.jmetal.operator.SelectionOperator;
import org.uma.jmetal.operator.impl.crossover.SBXCrossover;
import org.uma.jmetal.operator.impl.mutation.SimpleRandomMutation;
import org.uma.jmetal.operator.impl.selection.BinaryTournamentSelection;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.problem.multiobjective.zdt.ZDT1;
import org.uma.jmetal.solution.DoubleSolution;
import org.uma.jmetal.util.AlgorithmRunner;
import org.uma.jmetal.util.JMetalLogger;
import org.uma.jmetal.util.comparator.RankingAndCrowdingDistanceComparator;

public class ZDT1JMetal {
	public static void main(String[] args) {
		Problem<DoubleSolution> problem = new ZDT1();
		Algorithm<List<DoubleSolution>> algorithm;
		CrossoverOperator<DoubleSolution> crossover;
		MutationOperator<DoubleSolution> mutation;
		SelectionOperator<List<DoubleSolution>, DoubleSolution> selection;
		//SBX 交叉算子
		double crossoverProb = 0.9;
		double crossoverDistributionIndex = 20.0;
		crossover = new SBXCrossover(crossoverProb, crossoverDistributionIndex);
		//变异算子
		double mutationProbality = 1.0 / problem.getNumberOfVariables();
		mutation = new SimpleRandomMutation(mutationProbality);
		//选择算子
		selection = new BinaryTournamentSelection<DoubleSolution>(new RankingAndCrowdingDistanceComparator<DoubleSolution>());
		
		//算法
		algorithm = new NSGAIIBuilder<DoubleSolution>(problem, crossover, mutation)
				.setSelectionOperator(selection).setMaxEvaluations(25000).setPopulationSize(100).build();
		
		AlgorithmRunner algorithmRunner = new AlgorithmRunner.Executor(algorithm).execute();
		
		List<DoubleSolution> population = algorithm.getResult();
		long computingTime = algorithmRunner.getComputingTime();
		JMetalLogger.logger.info("Total execution time: "+ computingTime + "ms");
		
		System.out.println(population.get(0).getVariableValue(0));
		
	}
}

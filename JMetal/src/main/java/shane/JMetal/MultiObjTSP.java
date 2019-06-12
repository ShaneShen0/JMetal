package shane.JMetal;

import java.util.List;

import org.netlib.util.doubleW;
import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.algorithm.multiobjective.nsgaii.NSGAIIBuilder;
import org.uma.jmetal.operator.CrossoverOperator;
import org.uma.jmetal.operator.MutationOperator;
import org.uma.jmetal.operator.SelectionOperator;
import org.uma.jmetal.operator.impl.crossover.PMXCrossover;
import org.uma.jmetal.operator.impl.mutation.PermutationSwapMutation;
import org.uma.jmetal.operator.impl.selection.BinaryTournamentSelection;
import org.uma.jmetal.problem.PermutationProblem;
import org.uma.jmetal.solution.PermutationSolution;
import org.uma.jmetal.util.AlgorithmRunner;
import org.uma.jmetal.util.comparator.RankingAndCrowdingDistanceComparator;
import org.uma.jmetal.util.fileoutput.SolutionListOutput;
import org.uma.jmetal.util.fileoutput.impl.DefaultFileOutputContext;

public class MultiObjTSP {
	public static void main(String[] args) {

		PermutationProblem<PermutationSolution<Integer>> problem;
		Algorithm<List<PermutationSolution<Integer>>> algorithm;
		CrossoverOperator<PermutationSolution<Integer>> crossover;
		MutationOperator<PermutationSolution<Integer>> mutation;
		SelectionOperator<List<PermutationSolution<Integer>>, PermutationSolution<Integer>> selection;
		problem = new MultiObjTSPProblem();
		crossover = new PMXCrossover(0.9);

		double mutationProb = 0.2;
		mutation = new PermutationSwapMutation<Integer>(mutationProb);

		selection = new BinaryTournamentSelection<PermutationSolution<Integer>>(
				new RankingAndCrowdingDistanceComparator<PermutationSolution<Integer>>());

		algorithm = new NSGAIIBuilder<PermutationSolution<Integer>>(problem, crossover, mutation, 100)
				.setSelectionOperator(selection).setMaxEvaluations(10000).build();

		AlgorithmRunner algorithmRunner = new AlgorithmRunner.Executor(algorithm).execute();

		List<PermutationSolution<Integer>> population = algorithm.getResult();
		long computingTime = algorithmRunner.getComputingTime();
		for (int i = 0; i < population.size(); i++) {
			System.out.println(population.get(i));
		}
		new SolutionListOutput(population)
		.setSeparator("\t")
		.setVarFileOutputContext(new DefaultFileOutputContext("VAR.tsv"))
		.setFunFileOutputContext(new DefaultFileOutputContext("FUN.tsv"))
		.print();
	}
}

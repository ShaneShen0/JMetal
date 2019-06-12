package shane.JMetal;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.StyledEditorKit.ForegroundAction;

import org.uma.jmetal.problem.impl.AbstractIntegerDoubleProblem;
import org.uma.jmetal.problem.impl.AbstractIntegerProblem;
import org.uma.jmetal.solution.DoubleSolution;
import org.uma.jmetal.solution.IntegerSolution;
import org.uma.jmetal.solution.PermutationSolution;
import org.uma.jmetal.solution.Solution;

public class SchedulingProblem implements AbstractIntegerPermutationProblem{
	
	public SchedulingProblem(){
		this(34);
	}
	public SchedulingProblem(Integer numberOfIntegerVar) {
		setNumberOfVariables(numberOfIntegerVar);
		setNumberOfObjectives(2);
		setName("SchedulingProblem");
		
		List<Integer> lowerLimit = new ArrayList<Integer>(getNumberOfVariables());
		List<Integer> upperLimit = new ArrayList<Integer>(getNumberOfVariables());
		
	    //设置定义域
	    for (int i = 0; i < getNumberOfVariables(); i++) {
	      lowerLimit.add(1);
	      upperLimit.add(34);
	    }
	    
	    setLowerLimit(lowerLimit);
	    setUpperLimit(upperLimit);
	}
	public void evaluate(IntegerSolution solution) {
		// TODO Auto-generated method stub
	    double[] f = new double[getNumberOfObjectives()];

	
	    int d = this.evalD(solution);
	    int c = this.evalC(solution);


	    solution.setObjective(0, d);
	    solution.setObjective(1, c);
	}

	  private int evalD(IntegerSolution solution) {
	    int d = 0;
	    int from = 0;
	    int to = 0;
	    List<Integer> window = new ArrayList<Integer>();
	    for (int i = 0; i < solution.getNumberOfVariables()-1; i++) {
	      from =  solution.getVariableValue(i);
	      to =  solution.getVariableValue(i+1);
	      if (window.contains(from)) {
			return Integer.MAX_VALUE;
	      }
	      else {
			window.add(from);
			d += City.cityDis[from][to];
	      }
	      
	    }

	    return d;
	  }


	  public int evalC(IntegerSolution solution) {
		    int c = 0;
		    int from = 0;
		    int to = 0;
		    List<Integer> window = new ArrayList<Integer>();
		    for (int i = 0; i < solution.getNumberOfVariables()-1; i++) {
		      from =  solution.getVariableValue(i);
		      to =  solution.getVariableValue(i+1);
		      if (window.contains(from)) {
				return Integer.MAX_VALUE;
		      }
		      else {
				window.add(from);
				c += City.cityCost[from][to];
		      }
		      
		    }

		    return c;
	  }
	public void setObjective(int index, double value) {
		// TODO Auto-generated method stub
		
	}

}

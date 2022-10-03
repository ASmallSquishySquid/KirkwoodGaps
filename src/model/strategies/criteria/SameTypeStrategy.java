package model.strategies.criteria;

import java.util.function.Supplier;

import model.balls.IBall;
import model.visitors.algos.CheckSameTypeBallAlgo;
import provided.utils.loader.IObjectLoader;
import provided.utils.loader.impl.ObjectLoader;

/**
 * A strategy where the criteria is configurable for the same type of ball.
 * 
 * @author Phoebe Scaccia
 */
public class SameTypeStrategy implements ICriteriaStrategy {
	
	/**
	 * The accessor for the criteria name.
	 */
	private Supplier<String> criteriaName;
	
	/**
	 * The IObjectLoader object which loads in criteria strategies.
	 */
	private IObjectLoader<ICriteriaStrategy> criteriaStrategyLoader = new ObjectLoader<ICriteriaStrategy>(
			(attempt, args) -> new ErrorCriteriaStrategy());
	
	/**
	 * Constructor for a new SameTypeStrategy.
	 */
	public SameTypeStrategy() {
		super();
		this.criteriaName = () -> "Collision";
	}
	
	/**
	 * Constructor for a new SameTypeStrategy.
	 * 
	 * @param criteriaName a Supplier for the type of interaction criteria
	 */
	public SameTypeStrategy(Supplier<String> criteriaName) {
		super();
		this.criteriaName = criteriaName;
	}
	
	@Override
	public void init(IBall context) {
		return;
	}

	@Override
	public boolean satisfied(IBall context, IBall target) {
		ICriteriaStrategy providedCriteriaStrategy = criteriaStrategyLoader.loadInstance(this.fixCriteriaName(criteriaName.get()));
		return providedCriteriaStrategy.satisfied(context, target) && context.execute(CheckSameTypeBallAlgo.Singleton, target);
	}
	
	/**
	 * A helper function that adds the criteria package name as a prefix to a class name.
	 *
	 * @param classname : the abbreviated form of an update strategy.
	 * @return the fully-qualified name of the update strategy.
	 */
	private String fixCriteriaName(Object classname) {
		return "model.strategies.criteria." + classname + "Strategy";
	}
}
package model.visitors.algos;

import model.adapters.IBallAlgo2ModelAdapter;
import model.balls.IBall;
import model.strategies.criteria.SameTypeStrategy;
import model.visitors.cmds.ABallAlgoCmd;
import provided.ballworld.extVisitors.IBallHostID;
import provided.logger.ILogger;
import provided.utils.view.ValuesPanel;

/**
 * Configures and installs the SameType criteria strategy.
 * 
 * @author Phoebe Scaccia
 */
public class ConfigSameTypeBallAlgo extends AConfigBallAlgo {
	/**
	 * For serialization.
	 */
	private static final long serialVersionUID = 7182947425193079052L;
	/**
	 * The current collision status of all instances of the installed interact strategy.
	 */
	private String criteriaType = "Collision";
	
	/**
	 * Constructor for a new ConfigSameTypeBallAlgo.
	 * 
	 * @param logger the logger to use
	 * @param algo2ModelAdpt the adapter to the model
	 */
	public ConfigSameTypeBallAlgo(ILogger logger, IBallAlgo2ModelAdapter algo2ModelAdpt) {
		super(logger, algo2ModelAdpt);
		
		this.setDefaultCmd(new ABallAlgoCmd<>() {
			
			/**
			 * For serialization.
			 */
			private static final long serialVersionUID = 8288569975152764528L;

			@Override
			public Void apply(IBallHostID index, IBall host, Void... params) {
				// Inherited AConfigBallAlgo.installInteractStrategy() creates the composite with the existing strategy.           
				installCriteriaStrategy(host, new SameTypeStrategy(()->criteriaType));
				return null;
			}});
		
		algo2ModelAdpt.addConfigComponent("Same Type Interaction", ()->{
			// Panel is instantiated INSIDE of the factory to ensure that is created on the GUI thread!
			ValuesPanel pnlValues =  new ValuesPanel("Input the criteria strategy.", logger);
			pnlValues.addTextInput("Criteria", "Collision", (newVal)->{
				criteriaType = newVal; // No validation being done here.
				return criteriaType;  // Return the current value
			});			
			
			return pnlValues;  // Return the control panel to be displayed
		});
	}

	@Override
	public String toString() {
		return "SameType";
	}
}

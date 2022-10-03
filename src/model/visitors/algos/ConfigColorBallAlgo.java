package model.visitors.algos;

import model.adapters.IBallAlgo2ModelAdapter;
import model.balls.IBall;
import model.strategies.update.ColorStrategy;
import model.visitors.cmds.ABallAlgoCmd;
import provided.ballworld.extVisitors.IBallHostID;
import provided.logger.ILogger;
import provided.utils.view.ValuesPanel;

/**
 * Configures and installs the Color update strategy.
 * 
 * @author Phoebe Scaccia
 */
public class ConfigColorBallAlgo extends AConfigBallAlgo {
	/**
	 * For serialization.
	 */
	private static final long serialVersionUID = -6021712495652362028L;
	/**
	 * Whether the strategy is enabled.
	 */
	private boolean isEnabled = true;

	/**
	 * Constructor for a new ConfigColorBallAlgo.
	 * 
	 * @param name the name of the algo
	 * @param logger the Logger
	 * @param algo2ModelAdpt the adapter to the model
	 */
	public ConfigColorBallAlgo(String name, ILogger logger, IBallAlgo2ModelAdapter algo2ModelAdpt) {
		super(logger, name, algo2ModelAdpt);
		this.setDefaultCmd(new ABallAlgoCmd<Void, Void>() {
			/**
			 * For serialization.
			 */
			private static final long serialVersionUID = 1061108309711647773L;

			@Override
			public Void apply(IBallHostID index, IBall host, Void... params) {
				installUpdateStrategy(host, new ColorStrategy(() -> isEnabled));
				return null;
			}
		});

		algo2ModelAdpt.addConfigComponent("Same Type Interaction", () -> {
			// Panel is instantiated INSIDE of the factory to ensure that is created on the GUI thread!
			ValuesPanel pnlValues = new ValuesPanel("Toggle the color strategy on/off.", logger);
			pnlValues.addBooleanInput("Enable color strategy", "Enable/disable", true, (newVal) -> {
				isEnabled = newVal; // No validation being done here.
				return isEnabled; // Return the current value
			});

			return pnlValues; // Return the control panel to be displayed
		});
	}

}

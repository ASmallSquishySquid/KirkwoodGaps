package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

/**
 * The GUI hosting BallWorld.
 */
public class BallGUI extends JFrame {

	/**
	 * The serial version ID for BallGUI.
	 */
	private static final long serialVersionUID = 6359772238177963596L;
	/**
	 * The content panel.
	 */
	private JPanel contentPane;
	/**
	 * The JPanel where balls are displayed.
	 */
	private final JPanel canvasPnl = new JPanel() {
		/**
		 * Auto-generated serial ID.
		 */
		private static final long serialVersionUID = -6952656931251224807L;

		/**
		* Overridden paintComponent method to paint a ball in the panel.
		* @param g The Graphics object to paint on.
		**/
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			//Painting to adapter
			modelUpdtAdpt.update(g);
		}
	};
	/**
	 * The JPanel where the controls are.
	 */
	private final JPanel controlPnl = new JPanel();
	/**
	 * Button to clear all balls.
	 */
	private final JButton btnClear = new JButton("Clear All");
	/**
	 * Makes a ABall with the strategy of boxType1.
	 */
	private final JButton btnStart = new JButton("Start");
	/**
	 * The view to model control adapter.
	 */
	private IModelControlAdapter modelCtrlAdpt = IModelControlAdapter.MAKE_NULL();
	/**
	 * The view to model update adapter.
	 */
	private IModelUpdateAdapter modelUpdtAdpt = IModelUpdateAdapter.NULL_OBJECT;

	/**
	 * Create the GUI.
	 * @param modelCtrlAdpt : the initialized control adapter.
	 * @param modelUpdtAdpt : the initialized update adapter.
	 */
	public BallGUI(IModelControlAdapter modelCtrlAdpt,
			IModelUpdateAdapter modelUpdtAdpt) {
		this.modelCtrlAdpt = modelCtrlAdpt;
		this.modelUpdtAdpt = modelUpdtAdpt;
		initGUI();
	}

	/**
	 * Initializes the GUI.
	 */
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 500);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		canvasPnl.setBackground(Color.BLACK);
		canvasPnl.setToolTipText("This is where the balls will be shown.");
		contentPane.add(canvasPnl, BorderLayout.CENTER);

		controlPnl.setBackground(Color.BLACK);
		controlPnl.setToolTipText("The panel with the controls.");
		contentPane.add(controlPnl, BorderLayout.WEST);
		GridBagLayout gbl_controlPnl = new GridBagLayout();
		controlPnl.setLayout(gbl_controlPnl);
		GridBagConstraints gbc_btnStart = new GridBagConstraints();
		gbc_btnStart.fill = GridBagConstraints.BOTH;
		gbc_btnStart.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnStart.insets = new Insets(0, 0, 5, 0);
		gbc_btnStart.gridx = 0;
		gbc_btnStart.gridy = 0;
		controlPnl.add(btnStart, gbc_btnStart);
		
		btnStart.setBackground(Color.GREEN);
		btnStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				modelCtrlAdpt.makeBalls();
			}
		});
		btnStart.setToolTipText("Starts the model");
		
				btnClear.setBackground(Color.RED);
				btnClear.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						modelCtrlAdpt.clearBalls();
					}
				});
				btnClear.setToolTipText("Clears all balls in the canvasPnl.");
				GridBagConstraints gbc_btnClear = new GridBagConstraints();
				gbc_btnClear.fill = GridBagConstraints.BOTH;
				gbc_btnClear.anchor = GridBagConstraints.NORTHWEST;
				gbc_btnClear.gridx = 0;
				gbc_btnClear.gridy = 1;
				controlPnl.add(btnClear, gbc_btnClear);
	}

	/**
	 * @return the canvas panel.
	 */
	public Container getCanvas() {
		return this.canvasPnl;
	}

	/**
	 * Repaints the center panel.
	 */
	public void update() {
		canvasPnl.repaint();
	}

	/**
	 * Starts the GUI.
	 */
	public void start() {
		setVisible(true);
	}
}

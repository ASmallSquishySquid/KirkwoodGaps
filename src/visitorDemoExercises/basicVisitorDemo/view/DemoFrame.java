package visitorDemoExercises.basicVisitorDemo.view;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


/**
 * The view for the Visitor Demo system
 * @author swong
 *
 * @param <CBoxItem>  The type of object to put into the JComboBox.
 */
public class DemoFrame<CBoxItem> extends javax.swing.JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8811498700919993686L;
	/**
	 * Dual output display panes
	 */
	private JSplitPane centerSplitPane = new JSplitPane();
	/**
	 * Input TF for the visitor class name
	 */
	private JTextField visitorTF = new JTextField();
	/**
	 * Button to execute the visitor
	 */
	private JButton executeBtn = new JButton();
	/**
	 * Drop list of available hosts
	 */
	private JComboBox<CBoxItem> hostCBx = new JComboBox<CBoxItem>();
	/**
	 * Main control panel
	 */
	private JPanel controlPnl = new JPanel();
	/**
	 * Output text area
	 */
	private JTextArea outputTA = new JTextArea();
	/**
	 * Scroll pane for long text output
	 */
	private JScrollPane textSPane = new JScrollPane();
	/**
	 * Canvas to draw on for graphical outputs
	 */
	private JPanel canvasPnl = new JPanel() {

		private static final long serialVersionUID = -517612402679798317L;

		public void paintComponent(Graphics g){
			super.paintComponent(g);
			model.paint(g);
		}
	};



	/**
	 * Adapter to the model
	 */
	private IModelAdapter<CBoxItem> model;
	private final JTextField paramTF = new JTextField();



	/**
	 * The constructor for the class
	 * @param closeAction  The action to take when the window is closed. 
	 * @param model The adapter interface back out to the model.
	 */
	public DemoFrame(int closeAction, IModelAdapter<CBoxItem> model) {
		super();
		paramTF.setText("comp310");
		paramTF.setToolTipText("Type in something forr an input parameter to be passed to the TxtFieldVisitor1/2 when they are executed.");
		paramTF.setColumns(10);
		this.model = model;
		setDefaultCloseOperation(closeAction);
		initGUI();
	}

	/**
	 * Starts the view.   Populates the JComboBox with values obtained from the model
	 */
	public void start(){
		for(CBoxItem host: model.getHosts()) {
			hostCBx.addItem(host);
		}

		setLocationRelativeTo(null);
		setVisible(true);		
	}

	/** 
	 * Utility method to initialize the system.
	 */
	private void initGUI() {
		setTitle("Visitor Demo");
		try {

			getContentPane().add(centerSplitPane, BorderLayout.CENTER);

			centerSplitPane.add(canvasPnl, JSplitPane.LEFT);
			canvasPnl.setPreferredSize(new java.awt.Dimension(177, 260));

			centerSplitPane.add(textSPane, JSplitPane.RIGHT);
			textSPane.setViewportView(outputTA);
			outputTA.setText("");
			
			getContentPane().add(controlPnl, BorderLayout.NORTH);
			controlPnl.setBackground(new java.awt.Color(230,255,254));
			controlPnl.add(hostCBx);

			
			controlPnl.add(executeBtn);
			executeBtn.setText("Execute!");
			executeBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					String result = model.run(hostCBx.getItemAt(hostCBx.getSelectedIndex()), visitorTF.getText(), paramTF.getText());
					outputTA.append("RESULT = "+result+"\n");
				}
			});


			visitorTF.setColumns(10);
			controlPnl.add(visitorTF);
			visitorTF.setText("Visitor1");
			
			controlPnl.add(paramTF);

			pack();
			this.setSize(632, 337);
		} catch (Exception e) {
			//add your error handling code here
			e.printStackTrace();
		}
	}


}

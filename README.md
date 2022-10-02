# HW06

**Please see the assignment instructions in Canvas.** 

## List ALL Partner Names and NetIDs:
1.  Phoebe Scaccia pys1 
2.  Annita Chang wc45

*Visitor Exercises*: Student-written visitor and accumulator code should be placed in the `model.visitors` package of the associated demo. The supplied model code is already configured to search these packages when dynamically loading visitors and accumulators.

*Simple Extended Visitors Demo*: A demo of a simple extended visitor system is included in the `provided.simpleExtVisitorsDemo` package.  See the README in that package for instructions.  There are no required exercises associated with this demo.  It is  _highly_  recommended that one thoroughly understand how this demo works  _before_  proceeding to the extended visitor portions of this assignment!    

NEVER MODIFY OR ADD ANYTHING TO ANY provided SUB-PACKAGE!!


## Notes to Staff:
All interact strategies must have a criteria strategy to activate.


## Application Notes:
### To run:
Go to Controller.Launch and click the green run button.

### GUI panels:
The Ball Type section sets the type of ball that the next created ball will be. The text box in the Paint Strategy section takes in the short name of the type of paint strategy you'd like to see, which is added to the list using the button below it. The next text box takes in the short name of the type of update strategy you'd like to see, which is added to the list using the button below it. The next text box takes in the short name of the type of criteria strategy you'd like to see, which is added to the list using the button below it. The next text box takes in the short name of the type of interact strategy you'd like to see, which is added to the list using the button below it. The last text box takes in the short name of a configuration algorithm that sets up the whole ball, which is added to the list using the button below it.
The top selector is the primary strategy selector, and the bottom selector provides the second strategy to use for combination. There is also a pair of switcher buttons to the right of the selectors. The top switcher button creates balls that share the same switcher strategy, and the bottom button sets the switcher strategy to the strategy indicated in the top selector. Above the switcher buttons is a label that gives the current strategy that the switcher balls are using. Lastly, on the far left is a button to clear all the balls.

### Type dependent behaviors:
There are five different type-dependent behaviors, as described below.
#### Hunt Predator Interact Strategy:
This interaction strategy causes PredatorBalls to steal mass when it interacts with something.
#### Kill Prey Interact Strategy:
This interaction strategy causes the ball to kill all prey that it interacts with.
#### Color Update Strategy:
This update strategy flashes the color of prey balls red, predators blue, scavengers green, and leaves everything else the same. It can be toggled on or off.
#### Physics Config Algo:
This config algo sets up DefaultBalls to be planets with a gravitation pull, and everything else to be elastically bouncing balls.
#### Same Type Config Algo:
This config algo takes in a provided criteria strategy (provided in the pop-up dialog box), and gives the ball a criteria strategy combining the provided one with the additional check that the other ball is of the same type.
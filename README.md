# HW06

**Please see the assignment instructions in Canvas.** 

## List ALL Partner Names and NetIDs:
1.  Phoebe Scaccia pys1 
2.  Annita Chang wc45

Submission date: October 2nd, 2022 Sunday.

*Visitor Exercises*: Student-written visitor and accumulator code should be placed in the `model.visitors` package of the associated demo. The supplied model code is already configured to search these packages when dynamically loading visitors and accumulators. <br />
1a. 2 visitors that have distinctly different outputs for each host: src/visitorDemonExercises/basicVisitorDemo/model/visitors/BasicVisitor1 and BasicVisitor2 give different string outputs based on hosts. <br />
1b. 2 visitors that show both host-dependent and input parameter-dependent behavior: <br />
src/visitorDemonExercises/basicVisitorDemo/model/visitors/TxtFieldVisitor1 and TxtFieldVisitor2 gives different string outputs based both on the input parameter and hosts types. Enter verbs for TxtFieldVisitor1 and class names at rice for TxtFieldVisitor2.<br />

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

### Ball types:
There are four different ball types, as described below.
#### Default ball
The default ball that is loaded in when a ball type is not provided. Try it out with the physics config strategy!
#### Predator ball
A ball that represents a predator. Try it out with the HuntPredator interact strategy and Collision criteria strategy!
#### Prey ball
A ball that represents prey. Try it out in a field of other balls with the KillPrey interact strategy and Collision criteria strategy!
#### Disco ball
A ball that is ready to party. Try it out with the color update strategy!

### Type dependent behaviors:
There are seven different type-dependent behaviors, as described below.
#### Hunt Predator Interact Strategy:
This interaction strategy causes PredatorBalls to steal mass when it interacts with something. Mass increases by 5% and stops increasing when its radius reaches 75. The balls it stole mass from will disappear when its radius hits 0.
#### Kill Prey Interact Strategy:
This interaction strategy causes the ball to kill all prey that it interacts with.
#### Color Update Strategy:
This update strategy flashes the color of prey balls orange, predators purple, party balls every color, and leaves everything else the same.
#### Color Update Config Algo:
Does the same thing as Color Update Strategy, plus it can be toggled on or off.
#### Physics Config Algo:
This config algo sets up DefaultBalls to be planets with a gravitation pull, and everything else to be elastically bouncing balls.
#### Same Type Criteria Strategy:
This criteria has the ball interact with a ball that it is touching that is also of the same type.
#### Same Type Config Algo:
This config algo takes in a provided criteria strategy (provided in the pop-up dialog box), and gives the ball a criteria strategy combining the provided one with the additional check that the other ball is of the same type.

### Unexpected but correct behavior:
Because we are now returning ABallConfigAlgos when loading in new strategies, strategies with fields now all share the same values in those fields. This is because we are creating the strategies when we first load them in, and their field values are now shared between all the balls with the same strategy instance. This is unexpected but makes sense, as we want all balls with the same configuration algorithms to share the same instance so that the control panels can update them all. The balls sharing all the other strategies is a side effective of this implementation.

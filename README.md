# EventbusDemo
Using Eventbus 


Many of the times we would want to asynchronously modify tabbed fragments after they are rendered. A simpler method of achieving it through RxBus is demonstrated here.

Scenario

1. There are two tabbed fragments.
2. First fragment shows a list of authors and against them is an image which depicts which authors are liked by the user.
3. Second fragment shows only the authors who are liked by the user in the first fragment.
4. Simultaneous updation of the second fragment on first fragment data change using Publisher/Subcriber concept.

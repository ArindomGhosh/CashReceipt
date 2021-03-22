## Kotlin, MVI
In this I have tried to explore the conventional android APIs like viewmodel, livedata, coroutines and hilt, to 
behave like a Stateful UI, where whenever there is change thriugh an API call the affected UI graph is recreated.
It's an attempt to implement idea of State Management using conventional APIs.

I have divided the views inside screens into individual small independent widgets\components and hosted them as states which makes them reusable
and easier to develope new screens supporting multiple screens and also could be leverage for creating a Server driven UI.

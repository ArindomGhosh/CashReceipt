## Kotlin, MVI
In this I have tried to explore the conventional android APIs like:
1) __ViewModel__
2) __Livedata__
3) __Coroutines__
4) __Navigation Componen__
5) __Hilt__

To behave like a Stateful UI, where whenever there is change through an API call the affected UI graph is recreated.
It's an attempt to implement the idea of State Management using conventional APIs.

I have divided the views inside screens into individual small independent **widgets/components** and hosted them as states which makes them reusable
and easier to develop new screens or supporting multiple screen sizes and also could be leverage for creating a Server driven UI.

<img src="https://user-images.githubusercontent.com/17147143/112092860-6a654180-8bbe-11eb-8c53-a6f293cea10f.gif" width="700" height="500">

<img src="https://user-images.githubusercontent.com/17147143/112093972-5de1e880-8bc0-11eb-8afe-ad57b7f70b6d.gif" height="500">

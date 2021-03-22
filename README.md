## Kotlin, MVI
In this I have tried to explore the conventional android APIs like:
1) __ViewModel__
2) __Livedata__
3) __Coroutines__
4) __Hilt__

To behave like a Stateful UI, where whenever there is change thriugh an API call the affected UI graph is recreated.
It's an attempt to implement the idea of State Management using conventional APIs.

I have divided the views inside screens into individual small independent **widgets/components** and hosted them as states which makes them reusable
and easier to develope new screens supporting multiple screens and also could be leverage for creating a Server driven UI.

![](https://user-images.githubusercontent.com/17147143/112033309-4c1e2800-8b63-11eb-98ce-1acf87b1a8bd.png =250x250)

![](https://user-images.githubusercontent.com/17147143/112033717-bdf67180-8b63-11eb-857a-f95602e2ee8f.png)
![](https://user-images.githubusercontent.com/17147143/112033736-c0f16200-8b63-11eb-92f3-6c2df2eb8490.png)

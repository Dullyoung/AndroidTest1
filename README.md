# About this program
origin Url [Click here](https://github.com/cokuscz/Carro-Android-Test-1
)
## Required
   - Kotlin
   - Business logic must not be in View Layer

So I choose kotlin + MVVM to develop it.

# Prepare
 - Third party libs
    - BaseQuickerAdapter  for quick build RV adapter
    - SuperTextView for easy round-rect view and click ripple effect
    - Okhttp for get internet information
    - DataBinding for easy implement MVVM
    - ViewModel for decoupling view and controller
 - Myself libs
    - BaseHttp
# Start
- Use BQA+RV to develop choose county Activity
- Use viewModel to get information from [given json file](https://gist.githubusercontent.com/heinhtetaung92/fbfd371881e6982c71971eedd5732798/raw/00e14e0e5502dbcf1ea9a2cdc44324fd3a5492e7/test.json)
- display the information on CarDetailActivity

# finish at 17:44 2021-09-28
I am not good at Kotlin and MVVM,I often use MVC & Java because my company do like this .
So I don't know how to write standard MVVM ,I think it is very terrible about my BindAdapter code.
It may be better to avoid "do business logic in view layer"  by using MVC + ViewModel for me.


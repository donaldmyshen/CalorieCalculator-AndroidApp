# CalorieCalculator-AndroidApp

Functiont List
----------------------

1. User can enter total calorie amount on start up (Achieved)
    Use a dialog here, in mainAcatibity. 
2. User can add new food item by name (Achieved)
3. User can add new food item by calorie (Achieved)
4. Adding new food items is done in a second activity (Achieved)
    Use a new acativity here, acatuall can be instead by a dialog fragement
5. Calories remaining is updated with each new food item (Achieved)
6. Calorie consumed is updated with each new food item (Achieved)
7. The list of food items displays foods and their respective calories amounts (Achieved)
     Use a list view here and a ArrayList adapter to contain data and view
8. Color change when calorie count becomes negative (Achieved)
    Use a function to judge it 
    Because I add an additional function that can allow users reset the plan calories, when 
the remaining back to positive again, the color wil back to black
9. All inputs are ﬁltered and error messages are displayed accordingly (Achieved)
o Simply use two alert dialog to achieve this function, use a button to maintain 
dialog display order

Optimization 
----------------

1. Greatly improved the UI
    Add background to all activities
    Change the character’s color, size and typeface
    Change the button on the first page to semitransparent
    Change the title bar’s color (Actual can remove it and even change the app to whole screen version)
2. Add additional function that allow user reset the calories plan
    Users may mistake a wrong number or regret when finish setting after start up
3. Add additional function that remove all the food items in the list and reset 
the total calorie and plan to 0 at 5:00 user’s local system time.
4. Suggestion: The second activity can be perfectly instead by a dialog fragment.
    Testing function and Future pplan
    Hope to build another activity that allow users can add sports and calories consume. 
    Bugs free but not work. List it in future plan.

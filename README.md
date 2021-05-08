# Android_Quiz_App

Write a two screen Android application in portrait mode that allows a user to prepare for ‘matching-style’ quizzes. When the program is running, it will display one term and four possible definitions; one of which is the correct one. The user will then select which definition they think matches the term displayed. The program will indicate if the user has made a correct or incorrect selection by displaying a toast message (or other means e.g. colors, etc.) to that affect. 

The first page of the application will present the user with a means of entering their name. The application will use an intent to navigate to the next page and pass this information from the first page of the application to the second page for display purposes. The second page will be where the testing itself takes place. The users score should be maintained and displayed on this page or via an intent on a third page once the testing is complete.

The GUI for this application is open to interpretation by you the developer, however, storyboards must be provided and your design should follow standard Android user interface design patterns and guidelines so that it is easy to use and visually appealing.

Other specifications:

•	You may not ‘hard code’ the hash or lists with quiz data (i.e. terms /definitions).
•	Include exactly ten term/definition pairs for testing and demonstration purposes.
•	Provide validation for the input field (e.g. no empty strings)
•	A definition may only be displayed once during a run of the program, however, its matching term should remain in the pool of terms to be displayed in each question and answer session.
•	The correct answer displayed for selection should not be duplicated in a single session
•	The incorrect answers displayed for selection should not be duplicated in a single session 
•	The correct answer should not always be in the same location from session to session 
•	Provided with the same quiz data, the program should display the definitions in a different order from run to run. (Hint – ‘shuffle’)
•	The program should end if it has run out of definitions to be displayed or if the user wishes to terminate it using the existing android interface or reset option you have provided.
•	The program should use at least one ArrayList to hold collections of items and at least one HashMap to hold  name-value pairs
•	The program should store its quiz data in a delimited text file
•	Use Androids logging mechanism to log error messages via try/catch statements.
•	Efficiency is very important – try not to create any more data structures (lists, hashes, arrays) or make more trips to these than necessary given the requirements. 

SAAD Requirements:
Story boards – one for each page, indicating UI components, layout and navigation.

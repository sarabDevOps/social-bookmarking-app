# social-bookmarking-app

### A Social bookmarking app made in java which allows user to save , upload and leave a commnt on bookmark .

### MVVM pattern is used , (Model, View, ViewModel)

#### Model 

The model is what I like to refer to as the domain object. The model represents the actual data and/or information we are dealing with. An example of a model might be a contact (containing name, phone number, address, etc.) or the characteristics of a live streaming publishing point. The key to remember with the model is that it holds the information, but not behaviors or services that manipulate the information.

#### The View 

The view is what most of us are familiar with and the only thing the end user really interacts with. It is the presentation of the data. The view takes certain liberties to make this data more presentable. For example, a date might be stored on the model as number of seconds since midnight on January 1, 1970 (Unix Time). To the end user, however, it is presented with the month name, date, and year in their local time zone. A view can also have behaviors associated with it, such as accepting user input. The view manages input (key presses, mouse movements, touch gestures, etc.) which ultimately manipulates properties of the model.


#### The ViewModel (Our Controller/Presenter)

The viewmodel is a key piece of the triad because it introduces Presentation Separation, or the concept of keeping the nuances of the view separate from the model. Instead of making the model aware of the user’s view of a date, so that it converts the date to the display format, the model simply holds the data, the view simply holds the formatted date, and the controller acts as the liaison between the two. The controller might take input from the view and place it on the model, or it might interact with a service to retrieve the model, then translate properties and place it on the view.

The viewmodel also exposes methods, commands, and other points that help maintain the state of the view, manipulate the model as the result of actions on the view, and trigger events in the view itself.

#### Prerequisites
Eclipse -  Website [here](https://www.eclipse.org/downloads/)
MYSQL   -  Website [here](https://www.mysql.com/downloads/)
JAVA 8  -  Website [here](https://www.oracle.com/java/technologies/javase-downloads.html)





## Author
          
[Sarabjeet ](https://github.com/sarabDevOps/social-bookmarking-app)

## License

This project is licensed under the [MIT](https://github.com/sarabDevOPs/social-bookmarking-app/blob/master/LICENSE) License 



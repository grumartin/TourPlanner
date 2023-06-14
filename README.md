# SWE2-TourPlanner

## Requirements

* PostgreSQL Database     
  Can be done through docker

* Filled out properties    
  In the application.properties File following parameters should be changed before running the application. </br>
  --->Url: the url of the PostgreSQL-Database should be modified</br>
  --->username: the username for the Database</br>
  --->password: the password for accessing the Database</br>

## Database Setup Docker

1. Startup Docker and go to the command line
2. Enter following command to build and run the container: docker run --name swe2db -e POSTGRES_PASSWORD=swe2pw -p 5432:5432 -d postgres
3. Enter: "docker exec -it swe2db bash" 
4. Enter: "psql -U postgres" so you can enter psql commands
5. Now create a new database: "CREATE database tourplanner;"

You can alternatively change username, password, containername and so on. Just consider changing them in the properties file 
as well.

There's no need to create tables, because Spring Boot automatically creates these through the Entities. 

## 🛠 Build 

Build the application with Maven.

## 🚀 Run

Run with Maven

## Endpoints

All the functional Endpoints are described in the openapi.yaml File.


## 🧾 Protocol

### App Architecture

![Architecture](assets/architecture.png)

### Use Case Diagram
![Use Cases](assets/Use_Case_Diagram_TourPlanner.png)

### Class diagram
![ClassDiagram](assets/class_diagram.png)
### Wireframes

![Wireframe](assets/wireframe_basic_view.jpg)

![Wireframe](assets/wireframe_add_view.jpg)

![Wireframe](assets/wireframe_route_view.jpg)

###	Communication between FXML files
For the communication the Observer Pattern was used, for example when a Tour is selected in the Observable List of Tours,
a FXML-Loader gets notified to load the FXML files of the Tour Information and Tour-Logs.

The same pattern is used for the calculation of the Child-Friendliness and the popularity of a trip. Every time a new Tour-Log gets added
or a Log is deleted, the Observable List of Tour-Logs calls a function to calculate the values notifies the TourInformationController
to update the values.

### Unit Test Design

The Unit Test were designed in a bottom up approach. Which means tests are designed for each layer except the Repositories.
Since the repositories should have already been tested by spring. Thus, tests were implemented for the Controllers and
Services.

The Services are the first layer which gets tested. Therefore, we mocked the repository with mockito. The tests are
structured in a given, when, then manner. Given states the initial data used for the test. When covers the part were
something gets called. Then checks if the correct method got called and if the arguments are right.

The Controller tests build upon the same approach. The only difference here is, that we are using the WebMvcTest library from
spring. This library can perform Http Requests, which are used to test our controllers. This way we can test all of our endpoints
and check the different response contents.

### Lessons learned
The most important lessons learned are, that we now understand how a userinterface should be structured and how it is done in JavaFX.
The usage of the Model View ViewModel pattern was also new to us and now we are capable to take the benefits out of it.
Also we gained some experience with patterns, like using the Observer Pattern for certain UI components.
### Time Tracking

![Tracked Time](assets/time_tracked.png)

### Link to git
#### Server:
https://github.com/grumartin/TourPlanner
#### User Interface:
https://github.com/Warteczek/TourPlannerUI.git
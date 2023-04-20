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

## 🧪 Test



## 🧾 Protocol

### App Architecture


### Unit Test Design


### Unique Feature


### Bonus Feature


### Time Tracking



### Link to git

https://github.com/grumartin/TourPlanner

### Encountered Problems

****
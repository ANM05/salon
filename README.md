## Salon Application
#### By:
Antony Ng'ang'a
#### App Description
Salon App allows a salon owner to add a stylist or a client to the salon database. The owner is able to perform queries and display stylists or clients saved in the database. The owner is also able to assign a client to a specific stylist. Finally the salon owner is able to delete and update client or stylist.
#### Database setup.
Using preferred sql database create the following database and tables. 
CREATE DATABASE hair_salon;
CREATE TABLE stylists (id serial PRIMARY KEY, name vachar, mobile varchar, email varchar);
CREATE TABLE clients (id serial PRIMARY KEY, name varchar, mobile varchar, email varchar, stylist_id int);

create test database:
CREATE DATABASE hair_salon_test WITH TEMPLATE hair_salon;
#### App setup Instructions
Follow the following instructions to run the application:
* clone this repo https:https://github.com/ANM05/salon
* cd into the cloned directory
* Open with preferred java text editor incase you are interested in making some modifications
* Run the application using gradle
#### Technologies used in development
* Postgres database
* Java programming language
* Spark framework plus necessary dependencies e.g VelocityTemplateEngine
* Junit testing framework
* Html
* Bootstrap
#### Contact Details
Incase of any questions or comments you can reach me: @antonynganga05@gmail.com

Copyright (c) 2019 Antony Ng'ang'a

#### License
[MIT](https://choosealicense.com/licenses/mit/)
 


# Solution
### from Jonas Schelde
### Week 4 of the 3. semester 2017 Datamatiker on cphbusiness in Lyngby Denmark

---

## Task One (JPA Mapping Exercise 3)

[PDF with explanations here](https://github.com/scheldejonas/Exercises/blob/master/SP/SP3/JPA_MappingExercise-3.pdf)

## Task One - Part One

[See this package with entities](https://github.com/scheldejonas/Exercises/tree/master/SP/SP3/solution-jpa-mapping-exercise-three/src/main/java/domain)

## Task One - Part Two

[See this class](https://github.com/scheldejonas/Exercises/blob/master/SP/SP3/solution-jpa-mapping-exercise-three/src/main/java/domain/DiscountFixed.java)

## Task One - Part Three

[See this class's commit history](https://github.com/scheldejonas/Exercises/blob/master/SP/SP3/solution-jpa-mapping-exercise-three/src/main/java/domain/DiscountQuantity.java)

## Task One - Part Four

![alt tag](http://schelde.info/wp-content/uploads/2017/02/scheldeinfo_sp-3-task-one-part-4.png)

![alt tag](http://schelde.info/wp-content/uploads/2017/02/scheldeinfo_sp-3-task-one-part-4-1.png)

The D Type Column is descriping what entity type in the model it is.
And the rest of the columns is just layed out to the right with a null.

## Task One - Part Five

[See this class](https://github.com/scheldejonas/Exercises/blob/master/SP/SP3/solution-jpa-mapping-exercise-three/src/main/java/domain/DiscountType.java)

![alt tag](http://schelde.info/wp-content/uploads/2017/02/scheldeinfo_screen-shot-2017-02-24-at-08.51.43.png)

![alt tag](http://schelde.info/wp-content/uploads/2017/02/scheldeinfo_screen-shot-2017-02-24-at-08.51.54.png)

![alt tag](http://schelde.info/wp-content/uploads/2017/02/scheldeinfo_screen-shot-2017-02-24-at-08.52.02.png)

The Entity's is then taken into their own tables, and is seperated with an id for them self. 

---

## Task Two (JPA one)

[PDF for Task Two explained](https://github.com/scheldejonas/Exercises/blob/master/SP/SP3/exam-preparation_JPA1.pdf)

## Task Two - Part One

##### Descripton of how I handled persistence in the last three semesters

We have been handleing it by making the sql code our self in the java data acces objects.

With those objects, we have been handling sql exception and other types of exception to catch when the sql code, we wrote as a string couldn't go through to the database.

More about the persistence, it was our own coding responsibilty to create the tables relational correct into making the objects able to be persisted from java code to database in the right way, so that data would be placed with the 1., 2. and 3. normalform.

The File IO

- Before ORM, there was no cache involved to make the http requests to our servers faster. Therefore each get method for an instance field, was waiting for the actual database response.
- After ORM, there is a live version of the object instance, to just be retrieved by the requests.

Cookies in the browser has only so har been used to connect the username and password to a request, so none of this has so far been used to knowing and using the session ID for anything else

The Pros are you can get security in SQL code writing correct to a higher level. The development speed is increasing a lot to.

The Cons are you can experience problems when using the cache and you need to fine tune the cache for functioning correctly. The worst issue is that the data inconsistency seperated over the different requests from users. I'm talking about cases where users just was sure about changing some data and then the data was not persisted in the need way.

ORM tries then to solve the automation of object models as a diagram model, with List's, to be persistent set into relational tables in the database.

The Basic components in Java JPA is the @Entity, EntityManagerFactory, EntityManager, EntityTransaction, Query

## Task Two - Part Two - Task One

[See this package](https://github.com/scheldejonas/Exercises/tree/master/SP/SP3/solution-jpa-one/src/main/java/domain)

## Task Two - Part Two - Task Two

[See this class for createUser](https://github.com/scheldejonas/Exercises/blob/master/SP/SP3/solution-jpa-one/src/main/java/dao/ProjectUserDaoImpl.java)

[See this class for findUser](https://github.com/scheldejonas/Exercises/blob/master/SP/SP3/solution-jpa-one/src/main/java/dao/ProjectUserDaoImpl.java)

[See this class for getAllUsers](https://github.com/scheldejonas/Exercises/blob/master/SP/SP3/solution-jpa-one/src/main/java/dao/ProjectUserDaoImpl.java)

[See this class for createProject](https://github.com/scheldejonas/Exercises/blob/master/SP/SP3/solution-jpa-one/src/main/java/dao/ProjectDaoImpl.java)

[See this class for assignUserToProject](https://github.com/scheldejonas/Exercises/blob/master/SP/SP3/solution-jpa-one/src/main/java/service/ProjectServiceImpl.java)

[See this class for findProject](https://github.com/scheldejonas/Exercises/blob/master/SP/SP3/solution-jpa-one/src/main/java/dao/ProjectDaoImpl.java)

[See this class for createTaskAndAssignToProject](https://github.com/scheldejonas/Exercises/blob/master/SP/SP3/solution-jpa-one/src/main/java/service/ProjectServiceImpl.java)

---

## Task Three (JPA Two)

[See this PDF for task description](https://github.com/scheldejonas/Exercises/blob/master/SP/SP3/exam-preparation_JPA2.pdf)

## Task Three - Part One

We have been handleing it by making the sql code our self in the java data acces objects.

With those objects, we have been handling sql exception and other types of exception to catch when the sql code, we wrote as a string couldn't go through to the database.

More about the persistence, it was our own coding responsibilty to create the tables relational correct into making the objects able to be persisted from java code to database in the right way, so that data would be placed with the 1., 2. and 3. normalform.

The File IO, have been from code to database immediatly, the has been no layer of cache of the data, to make it way faster to get the data when having a lot of requests.

Cookies in the browser has only so har been used to connect the username and password to a request, so none of this has so far been used to knowing and using the session ID for anything else

The Pros are you can get security in SQL code writing correct to a higher level. The development speed is increasing a lot to.

The Cons are you can experience problems when using the cache and you need to fine tune the cache for functioning correctly. The worst issue is that the data inconsistency seperated over the different requests from users. I'm talking about cases where users just was sure about changing some data and then the data was not persisted in the need way.

ORM tries then to solve the automation of object models as a diagram model, with List's, to be persistent set into relational tables in the database.

With inheritance there can be used three kinds of strategies, SINGLE_TABLE, JOINED, TABLE_PER_CLASS (or called inheritance).

With single_table it created enough columns that are nullable, to make the row pr. instance, to make it work, there is a discriminator column, with the name of the entity, so it is possible to seperate and withdraw directly just a entity from the name of the entity in the same table.

With Joined, it creates tables linked with id's to the main parent entity, and with holds the enities in tables, put has always the link to the parent entity through foreing keys.

With Inheritance (Table_per_class) There is just a table per class.

## Task three - Part Two

[See this package for JPA Annotations on entities](https://github.com/scheldejonas/Exercises/tree/master/SP/SP3/solution-jpa-two/src/main/java/domain)

Tables:

![alt tag](http://schelde.info/wp-content/uploads/2017/02/scheldeinfo_sp3-jpa-2-part-2-273x300.png)

Class's:

![alt tag](http://schelde.info/wp-content/uploads/2017/02/scheldeinfo_sp3-jpa-2-part-2-5.png)

Grade class:

![alt tag](http://schelde.info/wp-content/uploads/2017/02/scheldeinfo_sp3-jpa-2-part-2-1-300x255.png)

Person class: (parent class)

![alt tag](http://schelde.info/wp-content/uploads/2017/02/scheldeinfo_sp3-jpa-2-part-2-2-300x259.png)

Person Student class: (child class)

![alt tag](http://schelde.info/wp-content/uploads/2017/02/scheldeinfo_sp3-jpa-2-part-2-4-300x175.png)

Person Employee class: (child class)

![alt tag](http://schelde.info/wp-content/uploads/2017/02/scheldeinfo_sp3-jpa-2-part-2-3-300x201.png)

##### Part two - Task 2 - Choice of inheritance strategy

I have in this case choosen SINGLE_TABLE inheritance strategy, because there is only 2 and 3 according fields on either employee and student subclass, which makes it needed for a lot of different sub calls with join table sql commands, if not in single table. That makes the getting process more slower.

Also because there is an aggregating class on the Person class. It is about the possibility for a person to be a supervisor of another person. And with just that, the person has an id of who is his supervisor. This also just makes it faster to get the same lines of people fields out by jpa, into the supervised people List.


##### Part two - Task 3 - Small facade

[See this class for dao methods](https://github.com/scheldejonas/Exercises/blob/master/SP/SP3/solution-jpa-two/src/main/java/dao/PersonDaoImpl.java)

##### Part two - Task 3 - Small REST API

[See hits class for facade, which i service in my case of design choice](https://github.com/scheldejonas/Exercises/blob/master/SP/SP3/solution-jpa-two/src/main/java/service/PersonServiceImpl.java)

The small rest API is made ready in the PersonServiceImpl, where it is possible to save and change supervisor of a Student.

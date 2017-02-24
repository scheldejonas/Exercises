# Solution
### from Jonas Schelde
### Week 4 of the 3. semester 2017 Datamatiker on cphbusiness in Lyngby Denmark

---

## Task One

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

## Task Two

[PDF for Task Two explained](https://github.com/scheldejonas/Exercises/blob/master/SP/SP3/exam-preparation_JPA1.pdf)

## Task Two - Part One

##### Descripton of how I handled persistence in the last three semesters

We have been handleing it by making the sql code our self in the java data acces objects.

With those objects, we have been handling sql exception and other types of exception to catch when the sql code, we wrote as a string couldn't go through to the database.

More about the persistence, it was our own coding responsibilty to create the tables relational correct into making the objects able to be persisted from java code to database in the right way, so that data would be placed with the 1., 2. and 3. normalform.

The File IO, have been from code to database immediatly, the has been no layer of cache of the data, to make it way faster to get the data when having a lot of requests.

Cookies in the browser has only so har been used to connect the username and password to a request, so none of this has so far been used to knowing and using the session ID for anything else

The Pros are you can get security in SQL code writing correct to a higher level. The development speed is increasing a lot to.

The Cons are you can experience problems when using the cache and you need to fine tune the cache for functioning correctly. The worst issue is that the data inconsistency seperated over the different requests from users. I'm talking about cases where users just was sure about changing some data and then the data was not persisted in the need way.

ORM tries then to solve the automation of object models as a diagram model, with List's, to be persistent set into relational tables in the database.

The Basic components in Java JPA is the @Entity, EntityManagerFactory, EntityManager, EntityTransaction, Query

## Task Two - Part Two

##### Practical part

[See this class]()

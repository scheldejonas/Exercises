## Exam 2 | React, State, Fetch and MobX

Task description is [here](https://docs.google.com/document/d/1BYq0FfEH-luNuxduJL9cgJ6IIPMHjFvyYWTSAlsjRnk/edit?usp=sharing)

### General part

- Describe the term Single Page Application and why it has become so popular for modern web-applications.

  - It is popular in the big picture to make the site lightning fast when interacting with the site.
  - People are made sure to get the fastest and most interactive experience with the AJAX setup, to both post and get on only the elements they click on.
  - Single Page Applications is also relevant when having a lot of different types of devices that needs to interact with the application.
  - Seen from a developer perspective, it is relevant in a modern would for getting our functional scripting code closer to the users screen, in regards to control on data displayed to the user.

- Explain the “recommended” React way of passing data into Components (at the top, or at the bottom or..)

  - Props is the recommended for passing inital data to the component, when used in render method.
  - So let the data be fetched from the REST api or other places at the very top of the react application component tree, to send them down with props.

- Explain how JavaScript array methods, like filter, map and (reduce) can be used to generate dynamic HTML structures (tables, ul's etc.)

  - JavaScript array, has the methods for iterate over each spot in the array and do different kinds of actions, in regards to filter, map and reduce.

  - Filter is an array method, that takes a callback function with the need to return true or false. The returning of true or false, decides if the object or value in the array spot is being put into the returned new array, after the filter method is iterated.
    To be able to iterate on each value or object in the array, the parameters for the callback function is recieving the datatype in each array spot, for accesing the data iterated.

  - Map is an array method, that takes a callback function for return one variable with the returned text with a line pr. spot or object iterated on in the array.
    Say you wanna loop an array for printing table rows with dynamic filled table cells per object in the array. You go map the array in example like:

  - ```Javascript
    let bookTableContent = tableData.books.map( function (book) {
      return (
        <tr key={book.id}>
        <td className="padding_left_right_normal">{book.title}</td>
        <td className="padding_left_right_normal">{book.info}</td>
        </tr>
      );
    });
    ```

- Explain about the Observer pattern, and where you have used it, both with Java, JavaScript and Mobx.

  - In Java we are using the observer pattern especially in Swing GUI's when having the need for ready the UI, to react to the pushed button from the user. The actions listener is used with the Observer pattern, where code is ready made to react by the buttons in UI pushed. All the observers are being notified when the regarded button is being pushed.
  - In Java, we are also using it for separating UI elements directly with the database level. Therefore we are implementing data fetchers in the UI into an observerable. And in the data acces object implementing it with an Observable action, that notifies all connected observers, when data is ready from the server.
  - In JavaScript, we are especially using the observer pattern to eventListeners, that have call back functions. Here the Browser is notifying all observers through JavaScript, when a use pushes button's, where we have setup eventListeners. onClick methods in JSX Tags and so on in React are observer patterned methods in JavaScript.
  - In MobX, we have the data stores being the data acces objects kind of, what we know it from in Java. Here the data stores are being annotated with @Observable on the array variables, because we then are linking it from the Components on changing data and being able to always accesing the same data object. It is kind of like have the observable books example array in constanst state sync, through out the app.

- Explain how a library/framework like Mobx fits into the React world, and the actual problems it solves. 

  - MobX is a made as a simple State management Library.
  - The issue about react for state is to have dynamicly the newest or correct data available all the time.
  - To do this, MobX are going in and using the observer patterns, by just having the plain JavaScript Class for controlling the array's or variables of data, that needs to be the same on all Components and pages at the same time.
  - So when a change is made to the observable object or array of any data type, the observers get notified with the updated data.

## Practical part

- git clone -b ejected https://github.com/Lars-m/reactMoxExExamex3.git
  - Done
- Type npm install to fetch dependencies and npm start to run, and open the project in your favourite IDE.
  - Done
- In the root of the project, open a new terminal and type  npm run dataserver . This will start a simple REST/JSON server, which you need for this exercise (leave this window open, and let the server run for the rest of the exercise). Test it via a browser: http://localhost:4567/api/persons
  - Done
- Complete the personFactory to fetch data, using fetch, from the REST-api given above.
  - **fetchPeople in personFactory.js**
- Use your updated personFactory and “inject” it into relevant controls so it will be available via props in PersonTableControl
  - **componentWillMount in PersonTable.js**
- Add the necessary code to PersonTable to render a table as sketched here.
  - **render in PersonTable.js**
- Change the code that generates the Table to provide a comma-separated list of all friends, instead of just the number of friends
  - **render in PersonTable.js** (mapping the friends array)
- Change personFactory to use mobX for event-Handling (it must be possible to observe the persons array).
  - **extendObservable in personFactory**
- Add the necessary code to other Components (where required) to observe the persons array.
  - **observer in PersonTable**
- Change the URL in personFactory into this one http://localhost:4567/api/persons_changing / , and use JavaScript’s setInterval function to repeatedly update
  - **updateTablePerThirdSecond in PersonTable**
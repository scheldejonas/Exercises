# React Router

The task description [here](https://github.com/scheldejonas/Exercises/blob/master/EP/ExamPrepRouter.pdf)

### General part

- Describe the term Single Page Application (SPA) and why it’s relevant for modern web-applications.

  - It is relevant in the big picture to make the site lightning fast when interacting with the site.
  - People are made sure to get the fastest and most interactive experience with the AJAX setup, to both post and get on only the elements they click on.
  - Single Page Applications is also relevant when having a lot of different types of devices that needs to interact with the application.
  - Seen from a developer perspective, it is relevant in a modern would for getting our functional scripting code closer to the users screen, in regards to control on data displayed to the user.

- Describe the overall principles used in React to create a SPA.

  - React is a JavaScript Framework for making User Interfaces.
  - React is built with in a declarative rendering base, through JSX code.
  - React has basicly a virtual environment as duplicate to the real environment the user look into. in regards here talking about the DOM.
  - React is only updating the nescassery components needed when changes are made to the virtual DOM in JSX or through state, props, routes clicks and data changes from AJAX in example.
  - Principles followed are not changeable patterns React is designed by:
    - Composition: Components written by different people needs to work well together.
    - Common Abstraction: Not implementing features that can be done in userland, for none used code to lay in that node library
    - Escape Hatches: Staying accessible to a wide range of developers, and being pragmatic about the declarative code solutions in the framework.
    - Stability: In the sense of "nothing changes", it is not directly what we are seing React, but because it is heavily used by Facebook, Twitter and fx. Airbnb.
    - Interoperability: Because facebook already has a ton of different UI Libraries, it is crucial for the transfer to React, that there libraries are interconnecable and can be declared in them
    - Scheduling: Because React is a declarative language, were it is the core that decides when the elements is actually rendered, instead of you controlling it through Plain JavaScript functions. The scheduling of the best order is controlled by React. Therefore also able to upgrade the order into making it easier and better frame responsiveness, when loading or rendering page.
    - Developer Experience: For example React DevTools to Chrome, are being maintained by Facebook, and therefore it is getting into becoming a rearly good develeoper experience.
    - Debugging: It is vitale importent, you have the same mistake as the user, when working with the User Interface versus that backend code, running on one server. React has it's ability to save, state, Components and Props, for you to restart the same complete environment, as the user were in, during the errors. Especially Redux, which is another state management framework, for saving the browse history constantly, to be sent to developer after error.
    - Configuration: Mostly it is designed for not running with function bound to the global scope. Say for example functions like React.configure(options) or React.register(Component) being implemented, would pose a lot of problems. If somebody calls these functions or another global function from a third-party library. If React app embeds another React app and their desired configuraritons are incompatible. This is a problem. Therefore being served with such methods in the global configuration, will not work.
    - Beond the DOM: When also moving on with the React Native, it is gathering developers around the product, instead of based on code platforms. So it makes the possible tools be fleksible enough for working together with the wide enough reach of people to come together on the right solutions for the actual users / customers, into the desired product, and note based on framework-platform.
    - Implementation: Because code is disposable and often changes, it is vital it doesn't introduce internal abstractions unless absolutely necessary. Verbose code that is easy move around, change is preferred to elegant code that is prematurely abstracted and hard to change.
    - Optimized for Tooling: The names of the methods created is more verbose then shortened. Say  ```componentDidMount()```, could be called ```didMount()``` or ```onMount()```. This is to make the reading and interaction from developers highly visible on what is happening. 
    - Dogfooding: This is a term also used at Facebook a lot, It is the story of the Dog food production company seeing the need for taking their enginered food with them home, to their own dogs, and changing it until they are liking it firstly. In the React world, it is all employees of facebook, finding problems about their products and React library issues, and making their addresments higher priority then the community outside Facebook. Because the longivity of React library is not suppose to stop tomorrow. Therefore they believe in spending time on making the products and React framework likeable and highly usable to them self, and from that hoping others int he outside facebook communities agreeing on this feeling.

- Explain the purpose of Client Side Routing in a SPA.

  - The purpose is to make clicking through the app, happen in an instant, instead of waiting for the http request to get the new dynamic HTML template filled page with data on the server.

- Explain the basic “building blocks” in reac-router.

  - First you need the Router with it's history type.
  - After that you are nesten the Components as children, with the jsx line ```{ this.props.children}``` and say the App component as the highest in the structure has this code. Then based on the to attribute on the Link components inserted, the React environment renderes only the nested parts with the new component, on the url pattern in to attribute.

- This exercise provides some “ready to go” start code. Explain what is required to use react-router with a create-react-app project builded from scratch.

  - You first need to install the library ```npm install --save react-router```  When having this you need to implement the Routings in a separatly js file looking example like this:

  - ```javascript
    /**
     * Created by scheldejonas on 02/04/2017.
     */
    // Libraries
    import React from 'react';
    import { Router, Route, browserHistory, IndexRedirect } from 'react-router';

    // Components
    import FrontPage from './templates/FrontPage';
    import Posts from './container/Posts';
    import NotFound from './components/NotFound';

    // Routes
    const routes = (
      <Router history={browserHistory}>
        <Route path="/" component={FrontPage} />
        <Route path="category/:category" component={Archive}>
          <Route path="posts" component={Posts} />
        </Route>
        <Route path="*" component={NotFound} />
      </Router>
    );

    export default routes;
    ```

  - You are starting the router.js file from the index.js, examply like this:

  - ```javascript
    import React from 'react';
    import { render } from 'react-dom';

    // Components
    import routes from './router';

    render(
      routes
      ,document.getElementById('root')
    );
    ```

  - Then when having the Header in example, the router is used with ``<Link>``` component, like this:

  - ```javascript
    /**
     * Created by scheldejonas on 02/04/2017.
     */
    import React, { Component } from 'react';
    import { Link } from 'react-router';

    class Header extends Component {
      render() {
        return (
          <div>
           	<nav>
              <li><Link to="/category/news">News</Link></li>
            </nav>     
            { this.props.children }
          </div>
        );
      };
    };

    export default Header;
    ```

## Practical part

- Investigate index.js and observe how we pass in a facade property via props to the ```RouterComponent```. Add the necessary code to read this in the ```RouterComponent``` , and pass it on further “down” to ```Persons``` component.
  - **index.js in src** (Passing plain javascript class into RouterComponent via props)
- Add the necessary changes to the ```Persons``` Component to render all names in a table as sketched above.
  - **Persons in src** (adding mapping of the rows in table body)
- Add a new Component, ```Details``` (right side of figure below) and the necessary changes so you can navigate to the component via this link: ```persons/details/:id```
  - **Details in src** (Adding filter to persons array, given from props and the path parameter id. Then filtering with true and false returns on the array. then after that rendering the person left in the array on screen.)
- If not already done, change the table, created in step-2, to provide the required link for each person to navigate into the Details control.
  - **Persons in src** (adding Link component to each table row, with the person on /persons/details/:id)
- Create a new Component NewPerson, and the necessary changes to navigate to the component via the "top-menu" as sketched below. Initially you should just add some dummy text in the component to demonstrate that navigation works.
  - **NewPerson in src** (passing the facade into the NewPerson Component)
- Add a Form and a save-Button to the NewPerson control, so new persons can be added to the _persons list (the PersonFacade class include a method; addPerson(..) which you should use for this part).
  - **persondata in src** (pushing the new person as a callback function)
- Only do this, if you have used surge (or similar) before. Publish your solution to a publix URL and demo it from here.
  - http://sp6chris.surge.sh/
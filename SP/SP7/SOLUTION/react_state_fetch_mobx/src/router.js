// Libraries
import React from 'react';
import { Router, Route, browserHistory } from 'react-router';

// Components
import App from './components/App';
import Home from './components/Home';
import PeopleTable from './components/PeopleTable';
import NotFound from './components/NotFound';

const routes = (
    <Router history={browserHistory}>
        <Route component={App}>
            <Route path="/" component={Home} ></Route>
            <Route path="/person-list" component={PeopleTable}/>
            <Route path="*" component={NotFound} />
        </Route>
    </Router>
);

export default routes;


// Old code

// import About from './components/About';
// import Courses from './components/Courses';
// import Teachers from './components/Teachers';
// import NotFound from './components/NotFound';
// import CSS from './components/courses/CSS';
// import HTML from './components/courses/HTML';
// import JavaScript from './components/courses/JavaScript';
// import Featured from './components/Featured';

{/*<Route path="about" component={About} title="About" ></Route>*/}
{/*<Route path="teachers" component={Teachers} ></Route>*/}
{/*<Route path="courses" component={Courses} >*/}
{/*<IndexRedirect to="html" />*/}
{/*<Route path="css" component={CSS} />*/}
{/*<Route path="html" component={HTML} />*/}
{/*<Route path="javascript" components={JavaScript} />*/}
{/*</Route>*/}
{/*<Route path="featured/:topic/:name" component={Featured} />*/}

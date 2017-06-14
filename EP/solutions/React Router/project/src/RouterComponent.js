import React from "react";
import {browserHistory,Route, Router, IndexRoute} from "react-router";
import App from "./App";
import Home from "./Home";
import Persons from "./Persons";
import Details from "./Details";
import NewPerson from './NewPerson';

/**
 * Made by Thomas / Lars
 */
export default class RouterComponent extends React.Component {

  render() {
    const persons = this.props.facade._persons;
    console.log(persons);
    return (
      <div>
        <Router history={browserHistory}>
          <Route path="/" component={App}>
            <IndexRoute component={Home}></IndexRoute>
            <Route path="persons" component={Persons} persons={persons}></Route>
            <Route path="persons/new" component={NewPerson} facade={this.props.facade}></Route>
            <Route path="persons/details/:id" component={Details} persons={persons}></Route>
            <Route path="#" component={Home}></Route>
          </Route>
        </Router>
      </div>
    );
  }
}
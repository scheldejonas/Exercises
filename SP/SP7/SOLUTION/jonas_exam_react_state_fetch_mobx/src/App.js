import React, {Component} from 'react';
import PersonTable from "./PersonTable";
import './App.css';
import '../vendor/materialize/materialize.css';

class App extends Component {

  render() {
    return (
      <div className="">
        <nav>
          <div className="nav-wrapper">
            <a href="#" className="brand-logo center">React, State, Fetch and Mobx</a>
            <ul id="nav-mobile" className="left hide-on-med-and-down">
              <li><a href="/">Person Table</a></li>
            </ul>
          </div>
        </nav>
        <div className="App-intro">
          <PersonTable />
        </div>
      </div>
    );
  }
}

export default App;

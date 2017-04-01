import React, { Component } from 'react';
import NavLink from './NavLink';
import { Link } from 'react-router';

class App extends Component {

    constructor() {
        super();
        this.state = {
            people: [],
            loading: true
        }
    };

    render() {
        return (
            <div>
              <nav className="white" role="navigation">
                <div className="nav-wrapper container">
                  <Link id="logo-container" to="/" className="brand-logo">
                    <i className="material-icons">perm_contact_calendar</i>
                  </Link>
                  <ul className="right hide-on-med-and-down">
                    <li><NavLink to="/person-list">People</NavLink></li>
                  </ul>
                </div>
              </nav>
              <div className="page_container container">
                {
                    this.props.children
                }
              </div>
            </div>
        );
    }
}

export default App;
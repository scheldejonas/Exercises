import React, { Component } from 'react'
import { Link } from "react-router";
import auth from '../authorization/auth'
import { observer } from "mobx-react";

import "..//stores/useStrict";

const App = observer(class App extends Component {

  render() {
    const NavLink = (props) => {
      return (
        <Link {...props} activeClassName="active" />
      );
    };
    const logInStatus = auth.loggedIn ? "Logged in as: " + auth.userName : "";
    return (
      <div>
        <nav>
          <div className="nav-wrapper blue lighten-2">
            <ul id="nav-mobile" className="hide-on-med-and-down">
              <li><Link to="#" className="text-size-custom-1">Exam Project</Link></li>
              <li><NavLink to="/home">Home</NavLink></li>
              {auth.isUser
                && <li><NavLink to="/user">User demo</NavLink></li>
              }
              {auth.isAdmin
                && <li><NavLink to="/admin">Admin demo</NavLink></li>
              }
            </ul>
            <ul className="hide-on-med-and-down right">
              <li className="navbar-text" style={{ color: "steelBlue" }}>{logInStatus}</li>
              <li>
                {auth.loggedIn
                  ? (
                    <NavLink to="/logout"><span className="glyphicon glyphicon-log-in"></span>Logout</NavLink>
                  )
                  : (
                    <NavLink to="/login">
                      <span className="glyphicon glyphicon-log-out"></span>Login</NavLink>
                  )
                }
              </li>
            </ul>
          </div>
        </nav>
        {this.props.children || <p>You are {!auth.loggedIn && 'not'} logged in.</p>}
      </div>
    )
  }
});

export default App;
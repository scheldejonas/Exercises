import React, { Component } from 'react'

import auth from "../authorization/auth";
import { observer } from "mobx-react";
import { observe } from "mobx";

const Login = observer(class Login extends Component {
  constructor() {
    super();
    this.handleSubmit = this.handleSubmit.bind(this)
    this.updateRoutesAfterLogin = this.updateRoutesAfterLogin.bind(this);
    observe(auth, "loggedIn", (data) => this.updateRoutesAfterLogin(data))
  }

  //Refactor this method into auth.js (reuires a way to get the router/location in this class)
  updateRoutesAfterLogin(val) {
    const { location } = this.props

    if (location.state && location.state.nextPathname) {
      this.props.router.replace(location.state.nextPathname)
    } else {
      this.props.router.replace('/')
    }
  }

  handleSubmit(event) {
    event.preventDefault()
    const email = this.refs.username.value
    const pass = this.refs.pass.value
    auth.login(email, pass, (loggedIn) => { })
  }

  render() {
    return (
      <form className="" onSubmit={this.handleSubmit}>
        <div className="row">
          <div className="col s4 offset-s4">
            <h2 className="center">Please sign in</h2>
            <br/>
          </div>
        </div>
        <div className="row">
          <div className="input-field col s4 offset-s4">
            <input id="inputEmail" type="text" ref="username" className="form-control" required autoFocus />
            <label htmlFor="inputEmail" className="sr-only">Email address</label>
          </div>
        </div>
        <div className="row">
          <div className="input-field col s4 offset-s4">
            <input id="inputPassword" type="password" ref="pass" className="form-control" required />
            <label htmlFor="inputPassword" className="sr-only">Password</label>
          </div>
        </div>
        <div className="row">
          <div className="input-field col s4 offset-s4">
            <a onClick={this.handleSubmit} className="waves-effect waves-light btn-large" type="submit">Sign in</a>
            <br/>
            {auth.failedLogin && (<p style={{color: "darkred"}}>{auth.errorMessage}</p> )}
          </div>
        </div>
      </form>
    )
  }
});

export default Login;

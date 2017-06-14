import React, { Component } from 'react';
// import auth from '../authorization/auth';
import { observer } from "mobx-react";

const Home = observer(class Home extends Component {

  render() {
    return (
      <div className="row">
        <div className="col s12">
          <h3>Exam project for AP degree in Computer Science.</h3>
        </div>
      </div>
    )
  }
});

export default Home;



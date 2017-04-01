import React, { Component } from 'react';
import '../vendor/materialize/materialize.css';

class Home extends Component {

  render() {
    return (
        <div className="home center-block">
            <h2>Person Factory</h2>
            <p>This fun directory is a project for the <em>Exam in React</em> on cphbusiness AP degree in Computer Science on 3. semester.</p>
            <p>This is created by <strong>Jonas Schelde</strong>.</p>
        </div>
    );
  }
}

export default Home;
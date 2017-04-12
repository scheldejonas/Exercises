/**
 * Created by scheldejonas on 12/04/2017.
 */
import React, { Component } from 'react';
import { browserHistory } from 'react-router';

class NewPerson extends Component {

  constructor() {
    super();
  }

  addPersonToPersons = (event) => {
    event.preventDefault();
    const newId = this.props.route.facade._persons.length + 1;
    const newName = document.querySelector('#name');
    const newAge = document.querySelector('#age');
    console.log('...Starting to add one person more to the persondata, with new id: ' + newId);
    this.props.route.facade.addPerson({id: newId, name: newName.value, age: newAge.value});
    browserHistory.push('persons');
  };

  render() {
    console.log('...Starting to render New Person component. with facade object: '+ this.props.route.facade);
    return (
      <div>
        <h3>Add New Person</h3>
        <div className="form_new_person_box">
          <form>
            <div>
              <label htmlFor="name">Name</label>
              <br/>
              <input id="name" type="text"></input>
            </div>
            <br/>
            <div>
              <label htmlFor="age">Age</label>
              <br/>
              <input id="age" type="number"></input>
            </div>
            <br/>
            <div>
              <button type="submit" onClick={this.addPersonToPersons}>Save</button>
            </div>
          </form>
        </div>
      </div>
    );
  }
};

export default NewPerson;
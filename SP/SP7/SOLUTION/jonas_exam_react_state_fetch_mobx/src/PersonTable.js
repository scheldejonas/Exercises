import React, { Component } from "react";
import {observer} from 'mobx-react';
import personData from './personFactory';

class PersonTable extends Component {
  constructor(props) {
    super(props);
    console.log('...Creating PersonTable Component.');
    let updateTable = '';
  };



  componentWillMount() {
    console.log('...Starting to get new persons, and waiting for the data to populate with state.');
    personData.fetchPeople();
  }

  updateTablePerSecond = (
    () => {
      personData.fetchChangingPeople();
      this.updateTable =
        setInterval(
          () => {
            personData.fetchChangingPeople();
          }
          ,3000
        );
    }
  );

  stopUpdating = (
    () => {
      clearInterval(this.updateTable);
    }
  );

  render() {
    const personsMappedInRows = personData.persons.map(
      (person, index) => {
        return (
          <tr key={person._id}>
            <td>{person.age}</td>
            <td>{person.name}</td>
            <td>{person.gender}</td>
            <td>{person.email}</td>
            <td>{
                person.friends.map(
                (friend, index) => {
                  return (
                    <span key={friend.id}>
                      {friend.name},&nbsp;
                    </span>
                  )
                }
              )
            }</td>
          </tr>
        );
      }
    );

    return (
      <div className="">
        <div className="row">
          <div className="col s6 right-align">
            <a onClick={this.updateTablePerSecond} className="waves-effect waves-light green lighten-2 btn"><i className="material-icons right">cloud</i>Start updating</a>
          </div>
          <div className="col s6 left-align">
            <a onClick={this.stopUpdating} className="waves-effect waves-light red lighten-2 btn">Stop updating</a>
          </div>
        </div>
        <table className="table">
          <thead>
            <tr>
              <th>Age</th>
              <th>name</th>
              <th>Gender</th>
              <th>Email</th>
              <th>Friends</th>
            </tr>
          </thead>
          <tbody>
            {personsMappedInRows}
          </tbody>
        </table>
      </div>
    );
  }
}
export default observer(PersonTable);
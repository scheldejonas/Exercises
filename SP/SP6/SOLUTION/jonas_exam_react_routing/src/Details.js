/**
 * Created by scheldejonas on 11/04/2017.
 */
import React, { Component } from "react";
import { Link } from 'react-router';

class Details extends Component {

  render() {
    console.log('...Details pages is filtering all people with id: ' + this.props.params.id);
    const personID = this.props.params.id;
    const person = this.props.route.persons.filter((person) => {
        console.log('...Persons being filtered by: ' + person.id + ', and needle like: ' + personID);
        const filterID = person.id;
        if (filterID == personID) {
          console.log('...Found the person to display.');
          return true;
        }
        return false;
      }
    )[0];
    console.log('...Details page is being rendered with this person: ' + person);
    return (
      <div>
        <div className="title_box">
          <h3>{person.name} Details</h3>
        </div>
        <div className="details_box">
          <p>ID: {person.id}</p>
          <p>Name: {person.name}</p>
          <p>Age: {person.age}</p>
          <p><Link to="persons">Go back</Link></p>
        </div>
      </div>
    )
  }
}

export default Details;
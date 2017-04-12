import React, { Component } from "react";
import { Link } from 'react-router';

class Persons extends Component {

  render() {
    const persons = this.props.route.persons;
    console.log(persons);
    const personsComponentRows = persons.map(
      (person, index) => {
        const linkToPerson = `/persons/details/${person.id}`;
        return (
          <tr key={person.id}>
            <td>{person.name}</td>
            <td>
              <Link to={linkToPerson}>{person.id}</Link>
            </td>
          </tr>
        )
      }
    );
    return (
      <div>
        <h3>People Table</h3>
        <table>
          <thead>
          <tr>
            <th>Name</th>
            <th>Details</th>
          </tr>
          </thead>
          <tbody>
          {personsComponentRows}
          </tbody>
        </table>
      </div>
    )
  }
}

export default Persons;


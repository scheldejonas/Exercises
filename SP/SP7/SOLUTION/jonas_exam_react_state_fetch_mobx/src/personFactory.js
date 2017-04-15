import {extendObservable, action} from "mobx";

class personFactory {

  constructor() {
    extendObservable(this, {
      persons: [
        {
          _id: 0
          ,age: 0
          ,name: ''
          ,gender: ''
          ,email: ''
          ,friends: [
            {
              id: 0
              ,name: ''
            }
          ]
        }
      ]
      ,fetchPeople: action(function() {
        fetch('http://localhost:4567/api/persons/')
          .then(
            (response) => {
              console.log('...Receiving response looking like this:');
              console.log(response);
              return response.json();
            }
          )
          .then(
            (responseData) => {
              console.log('...Starting to move data from response into variable in frontend;');
              console.log(responseData);
              this.persons = responseData;
              console.log('...Done fetching and this.persons now looks like:');
              console.log(this.persons);
            }
          )
          .catch(
            (error) => {
              console.log('Error fetching and parsing data', error);
            }
          );
      })
      ,fetchChangingPeople: action(function() {
        fetch('http://localhost:4567/api/persons_changing/')
          .then(
            (response) => {
              console.log('...Receiving response looking like this:');
              console.log(response);
              return response.json();
            }
          )
          .then(
            (responseData) => {
              console.log('...Starting to move data from response into variable in frontend;');
              console.log(responseData);
              this.persons = responseData;
              console.log('...Done fetching and this.persons now looks like:');
              console.log(this.persons);
            }
          )
          .catch(
            (error) => {
              console.log('Error fetching and parsing data', error);
            }
          );
      })
    });
  }
}

let personData = new personFactory();

export default personData;


class PersonFacade {

  constructor() {
    this._persons = [
      { id: 1, name: "Jens", age: 18 }
      , { id: 2, name: "Peter", age: 23 }
      , { id: 3, name: "Hanne", age: 23 }
    ];
  }
  
  get persons() {
    return this._persons;
  }

  addPerson = (person) => {
    this._persons.push(person);
  }

}

export default new PersonFacade();
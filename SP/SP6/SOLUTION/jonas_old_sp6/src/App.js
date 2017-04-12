import React, { Component } from 'react';
import logo from './logo.svg';
import currency from'./currency';
import './App.css';

const cars = [
 { year:1997,registered: 867621600000, make: 'Ford',model: 'E350', description: 'ac,abs, moon', price: 3000 }
 ,{ year:1999,registered: 945212400000, make: 'Chevy', model: 'Venture', description:'None', price: 4900 }
 ,{ year:2000,registered: 953766000000, make: 'Chevy', model: 'Venture', description:'', price: 5000 }
 ,{ year:1996,registered: 844380000000, make: 'Jeep', model: 'GrandCherokee',description: 'Air, moon roof,   loaded',price: 4799 }
 ];

class App extends Component
{
  constructor(props)
  {
    super(props)
    {
      this.state
      {

      };
    }
  }

  render()
  {
    document.title = "Used Cars App";

    const carlines = cars.map
    ( car =>
      {
        const registered = (new Date(car.registered)).toISOString().slice(0, 10);
        return <tr key={car.key}>
          <td>{car.year}</td>
          <td>{registered}</td>
          <td>{car.make}</td>
          <td>{car.model}</td>
          <td>{car.description}</td>
          <td>{currency(car.price)}</td>
        </tr>
      }
    );

    const carsTable =
    <table>
      <thead>
        <tr>
          <td>Year</td>
          <td>Registered</td>
          <td>Make</td>
          <td>Model</td>
          <td>Description</td>
          <td>Price</td>
        </tr>
      </thead>
      <tbody>
        {carlines}
      </tbody>
    </table>;

    return (
      <div className="App">
        <div className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <h2>Used Cars Bingo</h2>
        </div>
        {carsTable}
      </div>
    );
  }
}

export default App;

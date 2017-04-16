import React, { Component } from 'react'
import { observer } from "mobx-react";
import userData from "../stores/userStore";
import footballData from "../stores/footballStore";

const UserPage = observer(
  class UserPage extends Component {

    componentWillMount() {
      /*
     This will fetch data each time you navigate to this route
     Move to constructor, if only required once, or add "logic" to determine when data should be "refetched"
     */
      footballData.getData();
    }

    render() {
      const footballClubsMappedAsListItems = footballData.footballClubs.map(
        (footballClub, index) => {
          return (
            <li key={footballClub.name} className="collection-item">
              <div>
                <span>{footballClub.name}</span>
                <a href={footballClub.url} target="_blank" className="secondary-content">
                  <i className="material-icons">send</i>
                </a>
              </div>
            </li>
          );
        }
      );
      return (
        <div className="row">
          <div className="col s12">
            <h4 style={{ color: "red" }}>{footballData.errorMessage}</h4>
          </div>
          <div className="col s12">
            <ul className="collection with-header">
              <li className="collection-header"><h4>Football Clubs</h4></li>
              {footballClubsMappedAsListItems}
            </ul>
          </div>
        </div>
      )
    }

  }
)
export default UserPage;
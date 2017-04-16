import React, { Component } from 'react'
import {observer} from "mobx-react";
import userData from "../stores/userStore";

const AdminPage = observer(
  class AdminPage extends Component {

    componentWillMount() {
      /*
      This will fetch data each time you navigate to this route
      Move to constructor, if only required once, or add "logic" to determine when data should be "refetched"
      */
      userData.getData();
    }

    render() {
      const allUsersMappedAsTableRows = userData.allusers.map(
        (user, index) => {
          return (
            <tr key={user.mail}>
              <td>{user.name}</td>
              <td>{user.mail}</td>
            </tr>
          );
        }
      );
      return (
        <div className="container">
        <div className="row">
          <div className="col s12">
            <h4 style={{ color: "red" }}>{userData.errorMessage}</h4>
          </div>
          <div className="col s12">
            <table className="striped centered">
              <thead>
                <tr>
                  <th>Name</th>
                  <th>Email</th>
                </tr>
              </thead>
              <tbody>
                {allUsersMappedAsTableRows}
              </tbody>
            </table>
          </div>
        </div>
        </div>
      )
    }

  }
)
export default AdminPage;
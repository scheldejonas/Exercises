
import { observable, action } from "mobx";
import fetchHelper from "./fetchHelpers"
const URL = require("../../package.json").serverURL;


class UserStore {
  @observable allusers = [];
  @observable errorMessage = "";

  @action
  setErrorMessage = (err) => {
    this.errorMessage = err;
  }

  @action
  getData = () => {
    this.errorMessage = "";
    this.messageFromServer = "";
    let errorCode = 200;
    const options = fetchHelper.makeOptions("GET", true);
    fetch(URL + "api/allusers", options)
      .then((res) => {
        if (res.status > 210 || !res.ok) {
          errorCode = res.status;
        }
        return res.json();
      })
      .then(action((res) => {
        if (errorCode !== 200) {
          throw new Error(`${res.error.message} (${res.error.code})`);
        }
        else {
          console.log('...Done fetching from server and recieved message like this:');
          console.log(res);
          this.allusers = res;
        }
      })).catch(err => {
        //This is the only way (I have found) to verify server is not running
        this.setErrorMessage(fetchHelper.addJustErrorMessage(err));
      })
  }
}

let userStore = new UserStore();

//Only for debugging
//window.userStore = userStore;
export default userStore;

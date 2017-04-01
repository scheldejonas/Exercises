import axios from 'axios';

class PersonDao {

    constructor() {
        this.people = [];
    }

    getPeopleFromDatabase = () => {
        axios.get(`http://localhost:4567/api/persons`)
            .then(response => {
                this.people = response.data.data;
            })
            .catch(function (error) {
                console.log('Error fetching and parsing data', error);
            });
        return this.people;
    };

}

export default new PersonDao();
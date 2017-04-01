 /**
 * Created by scheldejonas on 31/03/2017.
 */
 import React from 'react';
 import RowPerson from './RowPerson';
 import PersonDao from '../data/PersonDao';

 const PeopleTable = props => {
     const data = props.people;
     let peopleTableList;
     if (data.length > 0) {
         peopleTableList = data.map(person =>
            <RowPerson key={person.id} age={person.age} name={person.name} gender={person.gender} email={person.email} friends={person.friends} />
         );
     }

     return (
         <div className="row">
             <div className="col l12 m12 s12">
                 <table className="striped">
                     <thead>
                     <tr>
                         <th>Age</th>
                         <th>Name</th>
                         <th>Gender</th>
                         <th>Email</th>
                         <th>Friends</th>
                     </tr>
                     </thead>
                     <tbody>
                        {/*<RowPerson age="20" name="Jonas" gender="male" email="scheldejonas@gmail.com" friends="Peter" />*/}
                        {
                            peopleTableList
                        }
                     </tbody>
                 </table>
             </div>
         </div>
     );
 };

 export default PeopleTable;

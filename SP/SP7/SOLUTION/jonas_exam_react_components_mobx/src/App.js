import React, { Component } from 'react';
import './App.css';
import info from "./dataModel"
import StudentTable from './StudentTable';

class App extends Component {
 
  render() {
    const studentInfo = info.studentsInfo;
    const headerCount = studentInfo.headers.length;
    const studentCount = studentInfo.students.length;
    console.log('...Data is fetched for table with the course count of: ' + headerCount + ', and also student count: ' + studentCount + '.');
    const students = info.studentsInfo.students;
    const courses = info.studentsInfo.headers;
    return (
      <div className="App">
        <div className="App-header">
          <h2>React - Exam Preparation Exercise</h2>
        </div>
        <div className="App-intro">
          <h4>The studentsInfo structure contains two lists:</h4>
          <p>One with all the required headers, whis is a total of ({studentInfo.headers.length})</p>
          <p>One with all the Students, whis is a total of ({studentInfo.students.length})</p>
          <p>Use the empty table below, or move it (you must eventually) into a separate component</p>
          <StudentTable courses={courses} students={students}></StudentTable>
        </div>
      </div>
    );
  }
}

export default App;

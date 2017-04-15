/**
 * Created by scheldejonas on 13/04/2017.
 */
import React, {Component} from 'react';
import {observer} from 'mobx-react';
import info from './dataModel';

@observer
class StudentTable extends Component {

  constructor(props) {
    super(props);
    this.state = {
      courses: []
      ,students: [
        {
          studentId: 0
          ,name: ''
          ,grades: []
        }
      ]
    };
    console.log('...Constructing Student Table Component with courses: ');
    console.log(this.state.courses);
    console.log('...And students:');
    console.log(this.state.students);
  };

  componentWillMount() {
    this.setState(
      {
        courses: info.getCourses()
        ,students: info.getStudents()
      }
    );
    console.log('...I re-rendered because oberservable changed from dataModel');
  }

  render() {
    console.log('...Data is fetched as: ');
    let courses = this.state.courses;
    console.log(courses);
    let students = this.state.students;
    console.log(students);
    let headerRowMapped = this.state.courses.map(function(course, index) {
        return (<th key={course.courseId}>{course.courseName}</th>);
      }
    );
    console.log('...HeaderRowMapped is done preparing and are looking like this:');
    console.log(headerRowMapped);
    let studentCellsMapped = this.state.students.map(
      (student, index) => {
        return (
          <tr key={student.studentId}>
            <td>{student.name}</td>
            {student.grades.map((grade, index) => {
              return <td key={index}>{grade.grade}</td>;
            })}
            <td></td>
          </tr>
        );
      }
    );
    console.log('...StudentCellsMapped is done preparing and are looking like this:');
    console.log(studentCellsMapped);
    return (
      <table className="table">
        <thead>
          <tr>
            <th></th>
              {headerRowMapped}
            <th>Average</th>
          </tr>
        </thead>
        <tbody>
          {studentCellsMapped}
        </tbody>
      </table>
    )
  };
}

export default StudentTable;
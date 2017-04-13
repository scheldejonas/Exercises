/**
 * Created by scheldejonas on 13/04/2017.
 */
import React, {Component} from 'react';

class StudentTable extends Component {

  constructor(props) {
    super(props);
    console.log('...Constructing Student Table Component with courses: ');
    console.log(this.props.courses);
    console.log('...And students:');
    console.log(this.props.students);
    this.state = {
      courses: this.props.courses
      ,students: this.props.students
    };
  };

  render() {
    console.log('...Data Headers is fetched as: ');
    console.log(this.state.headers);
    const headerRowMapped = this.state.courses.map(function(course, index) {
        return (<th key={course.courseId}>{course.courseName}</th>);
      }
    );
    console.log('...HeaderRowMapped is done preparing and are looking like this:');
    console.log(headerRowMapped);
    const studentCellsMapped = this.state.students.map(
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
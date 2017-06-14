import {observable, action} from 'mobx';

class StudentInfoFactory {

  @observable courses = [];
  @observable students = [];

  constructor() {
    this.makeData();
  }

  @action
  getCourses = () => {
    return this.courses;
  };

  @action
  getStudents = () => {
    return this.students;
  };

  makeData = (() => {
    this.courses = [
      { courseId: 1000, courseName: "Basic Programming" },
      { courseId: 1001, courseName: "Advanced Programming" },
      { courseId: 1003, courseName: "DataBase Intro" }
    ];
    this.students.push({ studentId: 100, name: "Peter Hansen", grades: [{ grade: "10" }, { grade: "12" }, {}] });
    this.students.push({ studentId: 101, name: "Jan Olsen", grades: [{ grade: "7" }, { grade: "10" }, {}] });
    this.students.push({ studentId: 102, name: "Gitte Poulsen", grades: [{ grade: "7" }, { grade: "7" }, {}] });
    this.students.push({ studentId: 103, name: "John McDonald", grades: [{ grade: "10" }, {}, { grade: "7" }] });
  }).bind(this);
}

let info = new StudentInfoFactory();

//This is only for debugging purposes
window.info = info;

export default info;

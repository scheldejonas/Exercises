import React from 'react';
import ReactDOM from 'react-dom';
import RouterComponent from './RouterComponent';
import './index.css';

import personFacade from "./persondata";

ReactDOM.render(
  <RouterComponent facade={personFacade} />, document.getElementById('root')
);

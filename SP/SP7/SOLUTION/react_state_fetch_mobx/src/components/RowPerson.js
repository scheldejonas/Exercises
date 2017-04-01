/**
 * Created by scheldejonas on 31/03/2017.
 */
import React from 'react';

const RowPerson = props => {
    return (
        <tr>
            <td>{props.age}</td>
            <td>{props.name}</td>
            <td>{props.gender}</td>
            <td>{props.email}</td>
            <td>{props.friends}</td>
        </tr>
    );
};

export default RowPerson;
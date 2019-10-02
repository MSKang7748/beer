import React, { Component } from 'react';
import PropTypes from 'prop-types';

const Row = (props) => {
  if (props.isHeading) {
    return <th>{props.data}</th>;
  }

  return (
    <tr>
      {props.data.map(item => <td>{item}</td>)}
    </tr>
  );
};

Row.propTypes = {
  data: PropTypes.array.isRequired,
  isHeading: PropTypes.bool
};

export default Row;

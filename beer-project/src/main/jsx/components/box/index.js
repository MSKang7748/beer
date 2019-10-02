import React from 'react';
import PropTypes from 'prop-types';
import cx from 'classnames'; // css classname combine utility

import './box.scss';

// Content box
const Box = (props) => {
  return (
    <section className={cx('Box', props.className)}>
      {props.children}
    </section>
  );
};

Box.propTypes = {
  children: PropTypes.element,
  className: PropTypes.string
};

export default Box;
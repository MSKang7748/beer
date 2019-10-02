import React from 'react';
import PropTypes from 'prop-types';
import { Link } from 'react-router-dom';
import cx from 'classnames'; // css classname combine하는 도구
import _ from 'lodash'; // utility javascript library 

const MenuItem = (props) => {
  const {
    count, // Number of items in the category (optional)
    faClass, // Font awesome class
    link,
    title
  } = props.item;

  return (
    <li className="Menu-item" key={ _.uniqueId() }>
      <Link to={link} className="Menu-item-link">
        { faClass && <i className={ cx('fa', faClass) } /> }
        { title }

        {
          count &&
          <span className="Menu-item-count">
            { count }
          </span>
        }
      </Link>
    </li>
  );
};

MenuItem.propTypes = {
  item: PropTypes.object.isRequired
};

export default MenuItem;

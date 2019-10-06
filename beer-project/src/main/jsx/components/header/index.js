import React from 'react';
import PropTypes from 'prop-types';
import UserInfo from './components/UserInfo';

import './header.scss';

const Header = (props) => {
  return (
    <header className="Header">
      &nbsp;
      <button className="Header-button" href="/">
        Menu
      </button>

      <h1 className="Header-pageTitle">
        {props.title}
      </h1>

      <UserInfo
    //  image="http://loremflickr.com/60/60"
        userName="admin" 
        notifications={[
          {title: 'Added new product'},
          {title: 'Added new product'},
          {title: 'Added new product'}
        ]}
      />
    </header>
  );
};

Header.propTypes = {
  title: PropTypes.string.isRequired
};

export default Header;

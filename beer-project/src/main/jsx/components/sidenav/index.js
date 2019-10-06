import React from 'react';
import PropTypes from 'prop-types';
import cx from 'classnames';
import Menu from '../menu';
import ActionBar from '../actionbar';

import './sidenav.scss';

const SideNav = (props) => {
  const menuItems = [
    {
      title: 'NoticeManager',
      link: '/',
      faClass: 'fa-dashboard'
    },
    {
      title: 'Lectures',
      link: '/lectures',
      faClass: 'fa-users', // Font awesome class
      count: 15 // Notification count
    }
  ];

  return (// classname과 sideNav를 조합.
    <nav className={cx('SideNav', props.customClass)}>
			<span className="SideNav-companyLogo">
        BeerGeek
			</span>

      <Menu 
        items={menuItems}
        caption="Pages" />
      <ActionBar />
    </nav>
  );// 사이드바 밑에 위치한 것 들이 액션바. (actionbar폴더 안에 구현)
};

SideNav.propTypes = {
	customClass: PropTypes.string
};

export default SideNav;
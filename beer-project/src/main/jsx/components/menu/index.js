import React from 'react';
import PropTypes from 'prop-types';
import MenuItem from './components/MenuItem.js'; // 메뉴 속에 있는 것 들을 메뉴아이템이라고 지칭.

import './menu.scss';

const Menu = (props) => {
  const {
    caption,
    items
  } = props;

  return (
    <div>
      {
        caption && 
        <h3 className="Menu-caption">
          {caption}
        </h3>
      }
      <ul className="Menu">
        {items.map(item => <MenuItem key={Math.random()} item={item} />)}
      </ul>
    </div>
  ); // item으로 MenuItem을 만드는 것. 임의의 값으로 key가 생기게 random 사용
};

Menu.propTypes = {
  caption: PropTypes.string,
  items: PropTypes.array.isRequired
};

export default Menu;

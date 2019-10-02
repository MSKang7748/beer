import React from 'react';
import PropTypes from 'prop-types';
import './user-info.scss';

const UserInfo = (props) => {
	const count = props.notifications ? props.notifications.length : false;

  return (
		<div className="UserInfo">
			
				<button className="UserInfo-notifications">
					<i className="fa fa-envelope-o" />
					{ 
						count && 
						<span className="UserInfo-notifications-count">
							{count}
						</span>
					}
				</button>

			<img 
				className="UserInfo-image" 
				src={props.image}
			/>

			<strong className="UserInfo-name">
				{props.userName}
			</strong>
		</div> // img로 이미지 항목들어감
  );
};

UserInfo.propTypes = {
	image: PropTypes.string.isRequired,
  userName: PropTypes.string.isRequired,
  notifications: PropTypes.array
};

export default UserInfo;

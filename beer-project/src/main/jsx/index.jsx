import React from 'react';
import ReactDOM from 'react-dom';

// import {render} from 'react-dom'; //ReactDOM을 줄인 것이 render다.
//import App from './components/App.jsx';

import RouteRoot from './route.jsx';

import './styles/main.scss'; // 스타일(디자인) 지정

ReactDOM.render( 
    <RouteRoot/>,
    document.getElementById("react") // react를 찾아서 rendering 하라. (react는 home.html에 id로 만듦)
)

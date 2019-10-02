import React, { Component } from 'react';
import App from './components/App.jsx';

import { BrowserRouter } from 'react-router-dom'; // BrowserRouter가 있어야 라우팅 처리가 된다.

class RouteRoot extends Component {

    render() {
        return(
            <BrowserRouter>
                <App/>
            </BrowserRouter>
        )
    }
}

export default RouteRoot;
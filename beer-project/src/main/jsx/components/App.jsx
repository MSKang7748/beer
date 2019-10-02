import React, {Component} from 'react'; // react쓰려면 React를 반드시 import 해 줘야 한다.
import { Route } from 'react-router-dom';
import SideNav from './sidenav';
import Header from './header';
import Dashboard from '../containers/dashboard'; // dashboard안의 내용을 모두 import한다.
import Users from '../containers/users';
// import Messages from '../containers/messages';
import Bestlecture from '../containers/bestlecture' ;
import Finance from '../containers/finance';

class App extends Component {

    render() {
        const title = "Beer Dashboard";

        return(            
        <div className="container-fluid">
            <div className="row">
                <SideNav />

            <div className="col-md-10 pull-right">
                        <Header title={title} />
                        <main className="Content">
                            <Route exact path="/" title="Dashboard" component={ Dashboard }/>
                            <Route exact path="/bestlecture" title="Bestlecture" component={ Bestlecture }/>
                            <Route exact path="/users" title="Users"component={ Users }/>
                            <Route exact path="/finance" title="Finance"component={ Finance }/>
                        </main>
                    </div>
                </div>
            </div>
        );
    }
}

export default App;
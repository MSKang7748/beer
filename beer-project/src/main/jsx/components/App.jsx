import React, {Component} from 'react'; // react쓰려면 React를 반드시 import 해 줘야 한다.
import { Route } from 'react-router-dom';
import SideNav from './sidenav';
import Header from '../components/header';
import NoteManager from './notes/NoteManager.jsx';
import LectureManager from './lectures/LectureManager.jsx';

class App extends Component {

    constructor(props) {
        super(props); // 부모 클래스 생성자 지칭 / super를 써야 this를 쓸 수 있다.

        this.state = {
    };
}

    render() {
    
        const title = "Beer Board";
        
        return(
            <div className="container-fluid">
                <div className="row">
                    <SideNav />

                    <div className="col-md-10 pull-right">

                        <Header title={title} />
                        
                        <main className="Content">
                            <Route exact path="/" title="NoteManager" component={ NoteManager }/>
                            <Route exact path="/lectures" title="Lectures" component={ LectureManager }/>
                        </main>
                    </div>
                </div>
            </div>
        ) // NoteManager 안에 실제 내용이 들어간다. // header가 맨 위 디자인 담당. header.js이용
    }
}

export default App;
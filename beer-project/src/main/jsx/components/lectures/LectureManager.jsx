import React, { Component } from 'react'; // {} 없는 것은 default export 하는 것 // {}있으면 export만 받을 수 있다. export default는 못 받음.
import LectureTable from './LectureTable.jsx';

import * as NoteService from '../../services/NoteService.js';

class NoteManager extends Component {

    constructor(props) {
        super(props);

        this.state ={
            lectures: []
        };
    }

    componentDidMount() {
        this.loadLectureList();
    }

    loadLectureList() {
        const promise = NoteService.loadLectureList(); // loadNoteList(NoteService에 있는)에서 promise 반환한다
        promise.then(lectures => { // then은 NoteService의 resolve와 연결됨, resolve가 동작하면 then에서 받아서 나머지를 처리한다.
            this.setState({lectures : lectures}); // notes를 저장
        })
        .catch(err => { // catch는 NoteService의 reject와 연결, reject가 동작하면 catch에서 받아서 나머지를 처리한다.
            console.log(err);
            return;
        })
    }


    render() {
        const { lectures } = this.state; // 내부에서 선언 된 것이므로 state를 쓴다.

        return(
            <div>
                <div align="right" width="300">
                </div>
                <LectureTable lectures = { lectures } />
            </div>
        ); // ControlPanel이 클릭되면 handleAddNoteClick 이벤트 연결. // NoteTable이 목록인데 NoteTable.jsx와 연결되고 notes를 데이터로 보냄.
    }
}

export default NoteManager;

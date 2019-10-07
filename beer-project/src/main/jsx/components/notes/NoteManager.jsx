import React, { Component } from 'react'; // {} 없는 것은 default export 하는 것 // {}있으면 export만 받을 수 있다. export default는 못 받음.
import Modal from 'react-modal';

import ControlPanel from './ControlPanel.jsx';
import AddNoteForm from './AddNoteForm.jsx';
import EditNoteForm from './EditNoteForm.jsx';

import NoteTable from './NoteTable.jsx';

import * as NoteService from '../../services/NoteService.js';

class NoteManager extends Component {

    constructor(props) {
        super(props);

        this.state ={
            notes: [],
            isAddNoteModalOpened : false, // 초기 상태 지정.
            isEditNoteModalOpened : false 
        };
        //eventhandler를 Component에 알려주는 (등록하는) 역할을 한다. // 등록이 되어야 쓸 수 있다.
        this.handleOnAddNoteModalClick = this.handleOnAddNoteModalClick.bind(this);
        this.handleOnCloseAddNoteModal = this.handleOnCloseAddNoteModal.bind(this);
        this.handleOnSaveNote = this.handleOnSaveNote.bind(this);
        this.handleOnUpdateNote = this.handleOnUpdateNote.bind(this);
        this.handleOnDeleteNote = this.handleOnDeleteNote.bind(this);
        this.handleOnEditNoteModalClick = this.handleOnEditNoteModalClick.bind(this);
        this.handleOnCloseEditNoteModal = this.handleOnCloseEditNoteModal.bind(this);
    }

    componentDidMount() {
        this.loadNoteList();
    }

    loadNoteList() {
        const promise = NoteService.loadNoteList(); // loadNoteList(NoteService에 있는)에서 promise 반환한다
        promise.then(notes => { // then은 NoteService의 resolve와 연결됨, resolve가 동작하면 then에서 받아서 나머지를 처리한다.
            this.setState({notes : notes}); // notes를 저장
        })
        .catch(err => { // catch는 NoteService의 reject와 연결, reject가 동작하면 catch에서 받아서 나머지를 처리한다.
            console.log(err);
            return;
        })
    }

    handleOnAddNoteModalClick() {
        this.setState({
            isAddNoteModalOpened: true
        }) // event가 들어오면 true로 바꾼다.
    }

    handleOnCloseEditNoteModal() {
        this.setState({
            isEditNoteModalOpened: false
        }) // event가 들어오면 true로 바꾼다.
    }

    handleOnCloseAddNoteModal() {
        this.setState({
            isAddNoteModalOpened: false
//            isEditNoteModalOpened: false
        }) // event가 들어오면 false로 바꾼다.
    }

    handleOnSaveNote(note) { // 서버에 저장할 때 사용
        this.setState({
            isAddNoteModalOpened: false
        });

        // 서버에 note 저장함.
        // alert(JSON.stringify(note));
        NoteService.addNote(note) // 실행의 순서를 보장하고자 추가한 것.
        .then( result => {
            this.loadNoteList()})
        .catch(err => {
            console.log(err);
        })
    };

     handleOnUpdateNote(note) { // 업데이트 할 때 사용
        this.setState({
            isEditNoteModalOpened: false
        });

        // 서버에 note 저장함.
        // alert(JSON.stringify(note));
        NoteService.updateNote(note) // 실행의 순서를 보장하고자 추가한 것.
        .then( result => {
            this.loadNoteList()})
        .catch(err => {
            console.log(err);
        })
    };
    
    handleOnDeleteNote(noteId) {

        const isOk = confirm(`${noteId}번 게시물을 삭제할까요?`);

        if (isOk) {
            NoteService.deleteNote(noteId)
                .then(() => {
                    // alert('삭제 성공');
                    this.loadNoteList(); //갱신 작업을 위해 바꿈
                })
                .catch((err) => {
                    alert('삭제 실패:' + err);
            });
        }
    }

    handleOnEditNoteModalClick(noteId) {
        // 편집 대상 Note 정보 조회
        const searchedNotes = this.state.notes.filter(note => noteId == note.id) // filter쓰면 함수를 줘야 한다.
        if (!searchedNotes) {
            alert(`${noteId}번 게시글을 찾을 수 없습니다.`);
            return;
        }

        const note = searchedNotes[0];
        //alert(note.title);

        // 편집 Modal 표시

        this.setState({
            isEditNoteModalOpened : true,
            selectedNote : note // isEditNoteModalOpened를 true로 state를 set함.
        })
    }

    render() {
        const { isAddNoteModalOpened, notes , isEditNoteModalOpened, selectedNote } = this.state; // 내부에서 선언 된 것이므로 state를 쓴다.

        return(
            <div>
                <Modal isOpen={  isAddNoteModalOpened }>
                    <AddNoteForm onCloseModal={ this.handleOnCloseAddNoteModal } onSaveNote = {this.handleOnSaveNote}/>
                </Modal>

                <Modal isOpen={ isEditNoteModalOpened }>
                    <EditNoteForm onCloseModal={ this.handleOnCloseEditNoteModal } 
                    selectedNote={ selectedNote } 
                    onSaveNote={ this.handleOnUpdateNote }/>
                </Modal>

                <div align="right" width="150">
                    <ControlPanel onAddNoteModalClick = {this.handleOnAddNoteModalClick}/>
                </div>
                <NoteTable notes = { notes } onDeleteNote={this.handleOnDeleteNote} onEditNoteModalClick = {this.handleOnEditNoteModalClick }/>
            </div>
        ); // ControlPanel이 클릭되면 handleAddNoteClick 이벤트 연결. // NoteTable이 목록인데 NoteTable.jsx와 연결되고 notes를 데이터로 보냄.
    }
}

export default NoteManager;

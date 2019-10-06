import React, { Component } from 'react';

class NoteTable extends Component {

    render() {

        const {notes, onDeleteNote, onEditNoteModalClick } = this.props; // 외부에서 notes 데이터를 받음.

        const noteRows = notes.map(note => { // note(원본데이터)를 => return(새로 만들어질 내용)으로 바꾸겠다.
            return(
                <tr key={ note.id }>
                    <td className="align-middle" style={{width: '80px'}}>
                        <div className="d-flex flex-row">
                            <a data-toggle="tooltip" data-placement="top" title="Edit Note" className="p-2" onClick={() => onEditNoteModalClick(note.id)}>
                                <i className="fa fa-pencil fa-lg text-primary"></i>
                            </a>
                            <a data-toggle="tooltip" data-placement="top" title="Delete Note" className="p-2" onClick={() => onDeleteNote(note.id)}>
                                <i className="fa fa-trash fa-lg text-danger"></i>
                            </a>
                        </div>
                    </td>
                    <td className="align-middle">{ note.title }</td>
                        <td className="align-middle">
                            <span className="d-inline-block text-truncate" style={{maxWidth: '200px'}}>
                                { note.content }
                            </span>
                        </td>
                    <td className="align-middle">{ note.updatedDate }</td>
                </tr>
            ) // 위의 tr이 한 줄에 해당하는 내용
        });

        return( // 이하 div는 column명이 뜨는 table임
            <div>
                <table className="table-bordered table-striped " align="right" width="1200">
                    <thead>
                        <tr>
                            <th></th>
                            <th className="align-middle text-center">제목</th>
                            <th className="align-middle text-center">내용</th>
                            <th className="align-middle text-center">수정일</th>
                        </tr>
                    </thead>
                    <tbody>
                        { noteRows }
                    </tbody>
                </table>
            </div>
        );
    }
}

export default NoteTable;
import React, { Component } from 'react';

class NoteTable extends Component {

    render() {

        const {lectures} = this.props; // 외부에서 notes 데이터를 받음.

        const noteRows = lectures.map(lecture => { // note(원본데이터)를 => return(새로 만들어질 내용)으로 바꾸겠다.
            return(
                <tr key={ lecture.lectureNo }>
                    <td className="align-middle">{ lecture.lectureTitle }</td>
                        <td className="align-middle">
                            <span className="d-inline-block text-truncate" style={{maxWidth: '200px'}}>
                                { lecture.lectureContent }
                            </span>
                        </td>
                    <td className="align-middle">{ lecture.createdDatetime }</td>
                </tr>
            ) // 위의 tr이 한 줄에 해당하는 내용
        });

        return( // 이하 div는 column명이 뜨는 table임
            <div>
                <table className="table-bordered table-striped " align="right" width="1200">
                    <thead>
                        <tr>
                            <th></th>
                            <th className="align-middle text-center">강좌제목</th>
                            <th className="align-middle text-center">강좌분야</th>
                            <th className="align-middle text-center">강좌오픈일</th>
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
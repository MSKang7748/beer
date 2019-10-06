
import * as axios from 'axios';

export const addNote = (note) => {

    return new Promise((resolve, reject) => {
    axios.post("/note-api/add", note)
    .then ((result) => {
        console.log(result.data);
        resolve(result.data);
    })
    .catch((err) => {
        console.log(err);
        reject(err.message);
        });
    });
}

export const loadNoteList = () => {
    return new Promise((resolve, reject) => {

    axios.get("/note-api/list") //note-api/list경로에 get방식으로 들어간다. (NoteController에서 DB와 연결됨)// promise 반환을 한다.
        .then( result => {
            const data = result.data;
            resolve(data); // resolve를 통해 NoteManager.jsx에서 loadNoteList의 promise.then으로 간다.
            return;
        })
        .catch (err => {
            reject(err.message); // reject를 통해 promise.catch로 간다.
            return;
            });
    });
}

export const deleteNote = (noteId) => { // const를 썼으므로 함수가 되어야 한다. / 전달인자는 noteId
// export function deleteNote(noteId)로 써도 된다.

    return new Promise((resolve, reject) => { // 직접 만든 promise
        
        axios.delete(`/note-api/delete/${noteId}`) // 데이터도 같이 보내기 위해 `사용함.
            .then ( () => {
                resolve();
            })
            .catch( err => {
                reject(err.message);
            });
    });
}

export const updateNote = (note) => {

    return new Promise((resolve, reject) => {
    axios.put("/note-api/update", note)
    .then ((result) => {
        console.log(result.data);
        resolve(result.data);
    })
    .catch((err) => {
        console.log(err);
        reject(err.message);
        });
    });
}

export const loadLectureList = () => {
    return new Promise((resolve, reject) => {

    axios.get("/note-api/lecturelist") //note-api/list경로에 get방식으로 들어간다. (NoteController에서 DB와 연결됨)// promise 반환을 한다.
        .then( result => {
            const data = result.data;
            resolve(data); // resolve를 통해 NoteManager.jsx에서 loadNoteList의 promise.then으로 간다.
            return;
        })
        .catch (err => {
            reject(err.message); // reject를 통해 promise.catch로 간다.
            return;
            });
    });
}
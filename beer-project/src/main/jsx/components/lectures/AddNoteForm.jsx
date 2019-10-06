import React, { Component } from 'react';

class AddNoteForm extends Component {

    constructor(props) {
        super(props);

        this.state = {
            title:'',
            content:'',
            tags:''
        } // 초기 값 지정

        this.handleonSubmit = this.handleonSubmit.bind(this);
        this.handleOnTitleChange = this.handleOnTitleChange.bind(this);
        this.handleOnContentChange = this.handleOnContentChange.bind(this);
        this.handleOnTagsChange = this.handleOnTagsChange.bind(this);
    }

    handleonSubmit(e) {

        e.preventDefault(); // form submit 취소(차단)

        //입력 유효성 검사

        //데이터 처리
        const {onSaveNote} = this.props; // props로 부터 받아서 
        onSaveNote(this.state); // 여기로 값을 보내고 onSaveNote로 handleonSubmit이벤트 연결됨.
    }

    handleOnTitleChange(e) {
        this.setState({
            title : e.target.value.trim() // trim은 양쪽 끝공백 제거 기능
        })
    }

    handleOnContentChange(e) {
        this.setState({
            content : e.target.value
        })
    }

    handleOnTagsChange(e) {
        this.setState({
            tags : e.target.value
        })
    }
    render() {
        const{ onCloseModal } = this.props;
        const{ title, content, tags} = this.state;
        return(
            <div className="card card-body">
                <div className="mb-2">
                    <span className="h4 my-auto"><i className="fa fa-file-text-o fa-lg"></i> New Note</span>
                    <a className="float-right ml-auto" onClick = { onCloseModal }>
                        <i className="fa fa-remove fa-2x mr-2 text-danger"></i>
                    </a>
                </div>
				
                <form className="mt-2" onSubmit= {this.handleonSubmit}>
                    <div className="form-group">
                        <label htmlFor="title">Title</label>
                        <input type="text" className="form-control" name="title" onChange={this.handleOnTitleChange} value={title} autoFocus />
                    </div>
                    <div className="form-group">
                        <label htmlFor="content">Content</label>
                        <textarea className="form-control" name="content" rows="3" onChange={this.handleOnContentChange}>{content}</textarea>
                    </div>
                    <div className="form-group">
                        <label htmlFor="tags">Tags</label>
                        <input type="text" className="form-control" name="tags" onChange={this.handleOnTagsChange} value={tags}/>
                    </div>
                    <div className="form-group row">
                        <div className="col-sm-4 col-md-3 col-xl-2 ml-auto">
                            <button type="submit" className="btn btn-success btn-lg btn-block">
                                <i className="fa fa-save mr-2"></i>Save
                            </button>
                        </div>
                        <div className="col-sm-4 col-md-3 col-xl-2">
                            <button className="btn btn-danger btn-lg btn-block mt-2 mt-sm-0" type="button">
                                <i className="fa fa-remove mr-2"></i>Cancel
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        );
    }
}

export default AddNoteForm;
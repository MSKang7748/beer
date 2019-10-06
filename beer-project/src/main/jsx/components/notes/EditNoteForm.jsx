import React, { Component } from 'react';

class EditNoteForm extends Component {

    constructor(props) {
        super(props);

        const { selectedNote } = props;

        this.state = {
            id : selectedNote.id,
            title: selectedNote.title,
            content : selectedNote.content,
            tags : selectedNote.tags
        }

        this.onTitleChange = this.onTitleChange.bind(this);
        this.onContentChange = this.onContentChange.bind(this);
        this.onTagsChange = this.onTagsChange.bind(this);
        this.handleOnClickSaveButton = this.handleOnClickSaveButton.bind(this);
    }

    onTitleChange(event) {
        const title = event.target.value;

        this.setState({
            title: title
        })
    }

    onContentChange(event) {
        const content = event.target.value;

        this.setState({
            content: content
        })
    }

    onTagsChange(event) {
        const tags = event.target.value;

        this.setState({
            tags : tags
        })
    }

    handleOnClickSaveButton(event) {
        event.preventDefault(); // submit 하지 못하게 막음

        //validation check (입력 유효성 검사)

        const { onSaveNote } = this.props;
        const { id, title, content, tags} = this.state;

        onSaveNote({
            id : id,
            title: title,
            content : content,
            tags : tags
        });

    }

    render() {

         const { onCloseModal } = this.props;
         const { id, title, content, tags} = this.state;

        return(
			<div className="card card-body">
                <div className="mb-2">        
                    <span className="h4 my-auto"><i className="fa fa-file-text-o fa-lg"></i> Edit Note</span>
                    <a className="float-right ml-auto" onClick = { onCloseModal }>
                        <i className="fa fa-remove mr-2 fa-2x text-danger"></i>
                    </a>
                </div>

                <form className="mt-2">
                    <div className="form-group">
                        <label htmlFor="title">Title</label>
                        <input type="text" className="form-control" name="title" onChange={ this.onTitleChange } autoFocus value={ title }/>
                    </div>
                    <div className="form-group">
                        <label htmlFor="content">Content</label>
                        <textarea className="form-control" name="content" rows="3" onChange={ this.onContentChange } value={ content }></textarea>
                    </div>
                    <div className="form-group">
                        <label htmlFor="tags">Tags</label>
                        <input type="text" className="form-control" name="tags" value={ tags } onChange={ this.onTagsChange }/>
                    </div>
                    <div className="form-group row">
                        <div className="col-sm-4 col-md-3 col-xl-2 ml-auto">
                            <button type="submit" className="btn btn-success btn-block" onClick={ this.handleOnClickSaveButton}> 
                                <i className="fa fa-save mr-2"></i>Save
                            </button>
                        </div>
                        <div className="col-sm-4 col-md-3 col-xl-2">
                            <button className="btn btn-danger btn-block mt-2 mt-sm-0" type="button" onClick = { onCloseModal }>
                                <i className="fa fa-remove mr-2" ></i>Cancel
                            </button >
                        </div>
                    </div>
                </form>
            </div>
        );
    }
}

export default EditNoteForm;
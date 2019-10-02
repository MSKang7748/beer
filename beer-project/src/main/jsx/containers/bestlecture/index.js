import React, { Component } from 'react';
import PropTypes from 'prop-types';
import Box from '../../components/box';
import DataTable from '../../components/datatable';
import axios from 'axios';

class Bestlecture extends Component {

	constructor(props) {
		super(props);

		this.state = {
			lectures : [[]]
		};
	}
		componentDidMount() {
			axios.get("/dashboard/bestlecture")
			.then(result => {
				this.setState({
					lectures : result.data
				});
			})
			.catch(err => {
				console.log(result.data);
			})
		}


render() {
	return (
		<div>
			<Box>
				<DataTable caption="Best Lectures" data={lectures} />
			</Box>
		</div>
		);
	};
}

export default Bestlecture;

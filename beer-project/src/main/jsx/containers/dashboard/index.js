import React, {Component} from 'react';
import { Chart } from 'react-google-charts'; // yarn add해서 받은 것. / return 내부에서 사용하고 있으므로 import 
// react-google-chart 아니다. (s 필수)
import SummaryBoxRow from './components/SummaryBoxRow.js';
import Box from '../../components/box';
import axios from 'axios';

class Dashboard extends Component {

    constructor(props) {
        super(props);

        this.state = {
            sales : [[]]
        };
    }

        componentDidMount() {
            axios.get("/dashboard/membercount") // 이 경로에서 데이터를 가져온다.
            .then(result => {
                this.setState({
                    sales : result.data
                });
            })
            .catch(err => {
                console.log(err.message);
            })
        }

    render() {

        return(
            <div>
                <SummaryBoxRow orders={111} emails={1} profit={151111}/>
                <div className="row">

                    <div className="col-md-7">
                        <Box className="ChartBox">
                            <h2 className="ChartBox-title">
                            Bussiness
                            </h2>
                            
                            <Chart 
                            chartType="ComboChart" 
                            data={ this.state.sales } 
                            options={{
                                chartArea: {'width': '85%', 'height': '75%'},
                                legend: {'position': 'bottom'},
                                seriesType: 'bars',
                                series: {5: {type: 'line'}},
                                colors: ['#12c5d6', '#40d47e']
                            }}
                            width={"100%"} 
                            height={"300px"} 
                            /> 

                            <div className="Box-settings">
                            <button className="fa fa-wrench"></button>
                            <button className="fa fa-close"></button>
                            <button className="fa fa-chevron-up"></button>
                            </div>
                        </Box>
                        </div>

                        <div className="col-md-5">
                        <Box className="ChartBox">
                            <h2 className="ChartBox-title">
                            Most popular categories
                            </h2>

                            <Chart 
                            options={{ 
                                chartArea: {'width': '100%', 'height': '75%'},
                                legend: {'position': 'bottom'},
                                colors: ['#12c5d6', '#40d47e', '#ec8f6e', '#ea6153', '#f6c7b6']
                            }}
                            chartType="PieChart" 
                            data={getPieChartData()} 
                            width={"100%"} 
                            height={"300px"} 
                            /> 

                            <div className="Box-settings">
                            <button className="fa fa-wrench"></button>
                            <button className="fa fa-close"></button>
                            <button className="fa fa-chevron-up"></button>
                            </div>
                        </Box>
                    </div>
                </div>
                <div className="row">
                    <div className="col-md-4">
                    <Box className="ChartBox">
                        <h2 className="ChartBox-title">
                        Quick settings
                        </h2>
                    </Box>
                    </div>

                    <div className="col-md-8">
                    <Box className="ChartBox">
                        <h2 className="ChartBox-title">
                        Most shipping to
                        </h2>

                        <Chart 
                        chartType="GeoChart" 
                        options={{colors: ['#40d47e']}}
                        data={getGeoChartData()} 
                        width={"100%"} 
                        height={"300px"} 
                        /> 

                        <div className="Box-settings">
                        <button className="fa fa-wrench"></button>
                        <button className="fa fa-close"></button>
                        <button className="fa fa-chevron-up"></button>
                        </div>
                    </Box>
                    </div>
                </div>

            </div>
        )
    }
} // dashboard에서 가장 먼저 실행 되는 것은 index.jsx이고. return안에 있는 내용이 포함됨.
//GeoChart는 지도 이용한 기능.

// These functions are providing only mockup data
    function getAreaChartData() {
        return [
        ['Year', 'Sales', 'Expenses'],
        ['2012', 900, 350],
        ['2013', 1000, 400],
        ['2014', 1170, 460],
        ['2015', 660, 1120],
        ['2016', 1030, 540]
    ];
    }

    function getPieChartData() {
        return [
        ['Goods', 'Amount of sold'],
        ['Cell phones', 210],
        ['TVs', 50],
        ['Fridges', 36],
        ['Laptops', 135],
        ['Vacuum cleaners', 8]
        ];
    }

    function getGeoChartData() { // data를 여기다가 적으면 해당 Chart에 표시 된다.
    return [
        ['Country', 'Popularity'],
        ['Germany', 200],
        ['United States', 300],
        ['Brazil', 400],
        ['Canada', 500],
        ['France', 600],
        ['RU', 700],
        ['China',250],
        ['KR', 400]
    ];
    }

    function getComboChartData() {
    return [
        ['Month', 'Sales', 'Profit'],
        ['2015/10', 953, 335],
        ['2015/11', 860, 401],
        ['2015/12', 425, 100],
        ['2016/01', 1123, 599],
        ['2016/02', 1586, 996]
    ];
    }

export default Dashboard;
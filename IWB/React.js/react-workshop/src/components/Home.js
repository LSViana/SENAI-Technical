import React, { Component } from 'react';
import AppBar from 'material-ui/AppBar';
import Drawer from 'material-ui/Drawer';
import MenuItem from 'material-ui/MenuItem';
import {Card, CardActions, CardHeader, CardText} from 'material-ui/Card';
import FlatButton from 'material-ui/FlatButton';
import './Home.css';
import Header from './Header';
import CardNotice from "./CardNotice";

class Home extends Component {
    constructor(props) {
        super(props);
        this.props = props;
        this.state = {
            "notices": []
        };
    }

    componentWillMount() {
        fetch("http://172.16.7.32:8080/MuralMilGrau/rest/news", {
                            method: "GET",
                            headers: {
                                "Accept": "application/json"
                            }
                        })
                        .then((res) => {
                            res.json()
                            .then((noticesObj) => {
                                this.state.notices = noticesObj;
                            });
                        });
    }

    render() {
        return (
            <div>
                <Header />
               ç <section>
                    {
                        this.state.notices.map((notice, index) => {
                            return (<CardNotice notice={notice} />)
                        })
                    }
                </section>
            </div>
        );
    }
}

export default Home;
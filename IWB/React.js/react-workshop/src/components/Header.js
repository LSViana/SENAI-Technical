import React, { Component } from 'react';
import AppBar from 'material-ui/AppBar';
import Drawer from 'material-ui/Drawer';
import MenuItem from 'material-ui/MenuItem';
import {Card, CardActions, CardHeader, CardText} from 'material-ui/Card';
import FlatButton from 'material-ui/FlatButton';
import CardNotice from "./CardNotice";

class Header extends Component {
    constructor(props) {
        super(props);
        this.props = props;
        this.state = {
            "author": "Duarte",
            "title": "Mil Grau News",
            "open": false
        };
        this.handleToggle = this.handleToggle.bind(this);
    }

    /**
     * Handle to toggle drawer visibility
     */
    handleToggle() {
        this.setState({
            "open": !this.state.open
        });
    }    

    render() {
        return (
            <header>
                <AppBar
                    title="Mural Mil Grau"
                    iconClassNameRight="muidocs-icon-navigation-expand-more"
                    onLeftIconButtonClick={this.handleToggle}
                />
                <h3>Here we are being part of this wonderful workshop by {this.state.author}</h3>
                <Drawer
                    open={this.state.open}
                    onRequestChange={this.handleToggle}
                    docked={false}>
                    <MenuItem>Menu Item</MenuItem>
                    <MenuItem>Menu Item 2</MenuItem>
                </Drawer>
            </header>
        );
    }
}

export default Header;
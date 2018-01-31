import React, { Component } from 'react';
import {Card, CardActions, CardHeader, CardText} from 'material-ui/Card';
import FlatButton from 'material-ui/FlatButton';

class CardNotice extends Component {
    constructor(props) {
        super(props);
        this.state = {
        };
    }

    render() {
        return (
            <Card>
                <CardHeader
                title={ this.props.notice.title }
                subtitle={ this.props.notice.datetime.toLocaleDateString() }
                actAsExpander={true}
                showExpandableButton={true}
                />
                <CardActions>
                <FlatButton label="Show" />
                <FlatButton label="Remove" />
                </CardActions>
                <CardText expandable={true}>
                { this.props.notice.content }
                </CardText>
            </Card>
        );
    }
}

export default CardNotice;
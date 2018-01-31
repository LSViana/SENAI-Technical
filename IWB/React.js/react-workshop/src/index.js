import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
// import App from './App';
import Home from "./components/Home";
import registerServiceWorker from './registerServiceWorker';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';

ReactDOM.render(
    <MuiThemeProvider>
        <Home />
    </MuiThemeProvider>
    , document.getElementById("root"));
// ReactDOM.render(<App />, document.getElementById('root'));

registerServiceWorker();
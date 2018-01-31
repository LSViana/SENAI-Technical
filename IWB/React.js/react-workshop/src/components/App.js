import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';

class App extends Component {
  render() {
    return (
      <div class="App">
        <header class="App-header">
          <img src={logo} class="App-logo" alt="logo" />
          <h1 class="App-title">Welcome to React</h1>
        </header>
        <p class="App-intro">
          To get started, edit <code>src/App.js</code> and save to reload.
        </p>
      </div>
    );
  }
}

export default App;

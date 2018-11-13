import React, { Component } from "react";
import "./App.css";
import Dashboard from "./components/Dashboard";
import Header from "./components/Layout/Header";
import "bootstrap/dist/css/bootstrap.min.css";
import { BrowserRouter as Router, Route, Redirect } from "react-router-dom";
import AddItem from "./components/Project/AddItem";
import { Provider } from "react-redux";
import store from "./store";
import UpdateItem from "./components/Project/UpdateItem";

class App extends Component {
  render() {
    return (
      <Provider store={store}>
        <Router>
          <div className="App">
            <Header />
            <Redirect to="/dashboard" />
            <Route exact path="/dashboard" component={Dashboard} />
            <Route exact path="/addItem" component={AddItem} />
            <Route exact path="/updateItem/:id" component={UpdateItem} />
          </div>
        </Router>
      </Provider>
    );
  }
}

export default App;

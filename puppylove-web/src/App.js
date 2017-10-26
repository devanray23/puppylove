import React from 'react';
import $ from "jquery";
import toastr from 'toastr';
import './App.css';

class App extends React.Component {
    constructor(props) {
      super(props);
      this.deletePuppy = this.deletePuppy.bind(this);
      this.createPuppy = this.createPuppy.bind(this);
      this.state = {
          puppies: [],
      };
   }

  componentDidMount() {
    this.loadPuppiesFromServer();
  }

  // Load puppies from database
  loadPuppiesFromServer() {
      fetch('http://localhost:8080/api/puppies')
      .then((response) => response.json())
      .then((responseData) => {
          this.setState({
              puppies: responseData._embedded.puppies,
          });
      });
  }

        // Delete puppy
  deletePuppy(puppy) {
      fetch (puppy._links.self.href,
      { method: 'DELETE',})
      .then(
          res => this.loadPuppiesFromServer()
      )
      .catch( err => console.error(err))
  }


  createPuppy(puppy) {
      fetch('http://localhost:8080/api/puppies', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(puppy) 
      })
      .then( 
          res => this.loadPuppiesFromServer()
      )
      .catch( err => console.error(err))
  }

  render() {
    return (
       <div>
          <PuppyForm createPuppy={this.createPuppy}/>
          <PuppyTable deletePuppy={this.deletePuppy} puppies={this.state.puppies}/> 
       </div>
    );
  }
}

class PuppyTable extends React.Component {


    render() {
    var puppies = this.props.puppies.map(puppy =>
        <Puppy key={puppy._links.self.href} puppy={puppy} deletePuppy={this.props.deletePuppy}/>
    );

    return (
      <div>
      <table className="table table-striped">
        <thead>
          <tr>
            <th>Name</th><th>Age</th><th>Breed</th><th>Location</th>
          </tr>
        </thead>
        <tbody>{puppies}</tbody>
      </table>
      </div>);
  }
}
class Puppy extends React.Component {

        constructor(props) {
          super(props);
          this.deletePuppy = this.deletePuppy.bind(this);
      }
       deletePuppy() {
          this.props.deletePuppy(this.props.puppy);
      }

      render() {

        return (
          <tr>
              <td>{this.props.puppy.name}</td>
              <td>{this.props.puppy.age}</td>
              <td>{this.props.puppy.breed}</td>
              <td>{this.props.puppy.location}</td>
              <td>
                <button className="btn btn-info" onClick={this.deletePuppy}>Delete</button>
              </td>
          </tr>
        );
      }
    }


    class PuppyForm extends React.Component {
      constructor(props) {
            super(props);
            this.state = {name: '', age: '', breed: '', location: ''};
            this.handleSubmit = this.handleSubmit.bind(this);
            this.handleChange = this.handleChange.bind(this);
          }
           handleChange(event) {
            console.log("NAME: " + event.target.name + " VALUE: " + event.target.value)
            this.setState(
                {[event.target.name]: event.target.value}
            );
        }

      handleSubmit(event) {
          event.preventDefault();
          console.log("name: " + this.state.name);
          var newPuppy = {name: this.state.name, age: this.state.age, breed: this.state.breed, location: this.state.location};
          this.props.createPuppy(newPuppy);
      }

      render() {
        return (
          <div className="panel panel-default">
          <div className="panel-heading">Add Puppy</div>
              <div className="panel-body">
              <form className="form-inline">
                  <div className="col-md-2">
                      <input type="text" placeholder="Name" className="form-control"  
                          name="name" onChange={this.handleChange}/>
                  </div>
                  <div className="col-md-2">
                      <input type="text" placeholder="Age" className="form-control" 
                          name="age" onChange={this.handleChange}/>
                  </div>
                  <div className="col-md-2">
                      <input type="text" placeholder="Breed" className="form-control" 
                          name="breed" onChange={this.handleChange}/>
                  </div>
                  <div className="col-md-2">
                      <input type="text" placeholder="Location" className="form-control" 
                          name="location" onChange={this.handleChange}/>
                  </div>
                  <div className="col-md-2">
                      <button className="btn btn-success" onClick={this.handleSubmit}>Save</button>   
                  </div>
              </form>
              </div>
          </div>
      );
    }
  }


export default App;

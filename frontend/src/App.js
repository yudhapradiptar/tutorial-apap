import React from "react";
import List from "./components/List";
import dummyItems from "./items.json";
import EmptytState from "./components/EmptyState";

export default class App extends React.Component {

  state = {
    showMe:true
  };

  state = {
    backgroundColor: 'white'
  };

  state = {
    favItems: []
  };

  handleItemClick = item => {
    const newItems = [...this.state.favItems];
    const newItem = {...item};
    
    const targetInd = newItems.findIndex(it => it.id === newItem.id);
    if (targetInd < 0) newItems.push(newItem);
    
    this.setState({ favItems: newItems });
  }

  deleteItemClick = item => {
    const newItems = [...this.state.favItems];
    const newItem = {...item};
    
    const targetInd = newItems.findIndex(it => it.id === newItem.id);
    newItems.splice(targetInd, 1);
    
    this.setState({ favItems: newItems });
  }

  toggleHide = () => {
    this.setState({showMe:!this.state.showMe});
  }

  toggleColor = () => {
    console.log(this.state.backgroundColor);
    if(this.state.backgroundColor==='white'){
      this.setState({backgroundColor: 'black'});
    } else {
      this.setState({backgroundColor: 'white'});
    }
  }
  // for class based component, you need to provide render
  // function
  render() {
    
    const { favItems } = this.state;
    const { backgroundColor } = this.state;
    if(this.state.favItems.length===0){
      return (
        <div className="container-fluid" style={{backgroundColor: backgroundColor}}>
          <h1 className="text-center">
            Welcome!
            <small>Class-based</small>
          </h1>
          <button onClick={()=>this.toggleHide()}>Hide Fav</button>
          <button onClick={()=>this.toggleColor()}>Change Color</button>
          <div className="container pt-3">
            <div className="row">
              <div className="col-sm" style={{backgroundColor: backgroundColor}}>
                <List
                  title="Our Menu"
                  items={dummyItems}
                  onItemClick={this.handleItemClick}
                />
              </div>
              <div className = "col-sm">
                {
                  this.state.showMe?
                  null
                  :
                  <EmptytState
                    
                  />
                }
              </div>
            </div>
          </div>
        </div>
      );
    } else {
      return (
        <div className="container-fluid" style={{backgroundColor: backgroundColor}}>
          <h1 className="text-center">
            Welcome!
            <small>Class-based</small>
          </h1>
          <button onClick={()=>this.toggleHide()}>Hide Fav</button>
          <button onClick={()=>this.toggleColor()}>Change Color</button>
          <div className="container pt-3">
            <div className="row">
              <div className="col-sm" style={{backgroundColor: backgroundColor}}>
                <List
                  title="Our Menu"
                  items={dummyItems}
                  onItemClick={this.handleItemClick}
                />
              </div>
              <div className = "col-sm">
                {
                  this.state.showMe?
                  null
                  :
                  <List
                    title = "My Favorite"
                    items = {favItems}
                    onItemClick={this.deleteItemClick}
                  />
                }
              </div>
            </div>
          </div>
        </div>
      );
    }
  }
}
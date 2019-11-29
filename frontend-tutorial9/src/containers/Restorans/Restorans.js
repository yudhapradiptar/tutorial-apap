import React, { Component } from 'react';
import classes from '../../containers/Restorans/Restorans.module.css';
import Restoran from '../../components/Restoran/Restoran'
import Axios from '../../axios-restoran';
import Modal from '../../components/UI/Modal/Modal';
import Button from "../../components/UI/Button/Button";

class Restorans extends Component{
    constructor(props){
        super(props);
        this.state = {
            restorans:[],
            isCreate: false,
            isEdit: false,
            isLoading: true,
            nama: "",
            alamat: "",
            nomorTelepon: "",
            rating: "",
            search: '',
            activePage: 1,
            restoransInPage: []
        }
    }
    

    loadRestorans = async () => {
        const fetchedRestorans = [];
        const response = await Axios.get("/restorans");
        for(let key in response.data) {
            fetchedRestorans.push({
                ...response.data[key]
            });
        }
        this.setState({
            restorans: fetchedRestorans
        })
        this.setState({
            restoransInPage: fetchedRestorans.slice(0,3)
        });
    }

    updateSearch(e) {
        this.setState({search: e.target.value.substr(0, 20)});
    }

    componentDidMount(){
        console.log("componentDidMount()");
        this.loadRestorans()
        this.setState({
            filtered: this.props.restorans
          });
    }

    addRestoranHandler = () => {
        this.setState({ isCreate : true });
        this.setState({ nama : '' });
        this.setState({ alamat: '' });
        this.setState({ nomorTelepon: '' });
        this.setState({ rating: '' });
    }

    canceledHandler = () => {
        this.setState({ isCreate: false, isEdit: false });
    }

    changedHandler = event => {
        const { name, value } = event.target;
        this.setState({ [name]: value});
    };

    submitAddRestoranHandler = event => {
        event.preventDefault();
        this.setState({ isLoading: true});
        this.addRestoran();
        this.canceledHandler();
    }

    async addRestoran() {
        const restoranToAdd = {
            nama: this.state.nama,
            alamat: this.state.alamat,
            nomorTelepon: this.state.nomorTelepon,
            rating: this.state.rating
        };

        await Axios.post("/restoran", restoranToAdd);
        await this.loadRestorans();
    }

    editRestoranHandler(restoran) {
        this.setState({
            isEdit: true,
            idRestoran: restoran.idRestoran,
            nama: restoran.nama,
            nomorTelepon: restoran.nomorTelepon,
            rating: restoran.rating,
            alamat: restoran.alamat
        })
    }

    submitEditRestoranHandler = event => {
        console.log("editing")
        event.preventDefault();
        this.setState({ isLoading: true });
        this.editRestoran();
        this.canceledHandler();
    };

    async editRestoran() {
        const restoranToEdit = {
            idRestoran: this.state.idRestoran,
            nama: this.state.nama,
            alamat: this.state.alamat,
            nomorTelepon: this.state.nomorTelepon,
            rating: this.state.rating
        };

        await Axios.put("/restoran/" + this.state.idRestoran, restoranToEdit);
        await this.loadRestorans();
        this.canceledHandler();
    }

    async deleteRestoranHandler(restoranId){
        await Axios.delete(`/restoran/${restoranId}`);
        await this.loadRestorans();
    }



    
        

    render(){
        let filteredRestorans = this.state.restorans.filter(
            (restoran) => {
                return restoran.nama.toLowerCase().indexOf(this.state
                    .search.toLowerCase()) !== -1;
            }
        );
        return(
            <React.Fragment>
                <Modal show={this.state.isCreate || this.state.isEdit}
                    modalClosed={this.canceledHandler}>
                        {this.renderForm()}
                </Modal>
                <div className={classes.Title}> All Restorans</div>
                <div>
                <input type="text"  
                    value={this.state.search}
                    className={classes.ButtonLayout}
                    onChange={this.updateSearch.bind(this)} placeholder="Search" />
                </div>
                <div className={classes.ButtonLayout}>
                    <button 
                        className={classes.AddRestoranButton}
                        onClick={this.addRestoranHandler}
                    >
                        + Add New Restoran
                    </button>
                </div>
                <div className={classes.Restorans}>
                    {filteredRestorans &&
                        filteredRestorans.map(restoran =>
                            <Restoran
                                key={restoran.idRestoran}
                                nama={restoran.nama}
                                alamat={restoran.alamat}
                                nomorTelepon={restoran.nomorTelepon}
                                edit={() => this.editRestoranHandler(restoran)}
                                delete={() => this.deleteRestoranHandler(restoran.idRestoran)}
                            />
                    )}
                </div>
                <div className={classes.ButtonLayout}>
                    <div className={classes.pagination}>
                        <span>&laquo;</span>
                        <span className={classes.activePage}>1</span>
                        <span>2</span>
                        <span>3</span>
                        <span>4</span>
                    </div>
                </div>
            </React.Fragment>
        )
    }
    renderForm() {
        const { isEdit } = this.state;
        return (
            <form>
                <input
                className={classes.Input}
                name="nama"
                type="text"
                placeholder="Nama"
                value={this.state.nama}
                onChange={this.changedHandler}
                /><br></br>
                <input
                className={classes.Input}
                name="nomorTelepon"
                type="number"
                placeholder="Nomor Telepon"
                value={this.state.nomorTelepon}
                onChange={this.changedHandler}
                /><br></br>
                <input
                className={classes.Input}
                name="alamat"
                type="text"
                placeholder="Alamat"
                value={this.state.alamat}
                onChange={this.changedHandler}
                /><br></br>
                <input
                className={classes.Input}
                name="rating"
                type="number"
                placeholder="Rating"
                value={this.state.rating}
                onChange={this.changedHandler}
                /><br></br>
                <Button btnType="Danger" onClick={this.canceledHandler}>
                    CANCEL
                </Button>
                <Button btnType="Success" onClick={isEdit ? this.submitEditRestoranHandler : this.submitAddRestoranHandler}>
                    SUBMIT
                </Button>

            </form>
        );
    }
}

export default Restorans;
import React, { Component } from "react";

import classes from "./Modal.module.css";
import Backdrop from "../Backdrop/Backdrop";
// import Button from "../Button/Button";

class Modal extends Component {
    shouldComponentUpdate(nextProps, nextState) {
        return (
            nextProps.show !== this.props.show ||
            nextProps.children !== this.props.children 
        );
    }

    render() {
        return (
            <React.Fragment>
                <Backdrop show={this.props.show} onClick={this.props.modalClosed} />
                <div
                    className={classes.Modal}
                    style={{
                        transform: this.props.show ? "translateY(0)" : "translateY(-100vh",
                        opacity: this.props.show ? "1" : "0"
                    }}
                >
                    {this.props.children}
                </div>
            </React.Fragment>
        );
    }

    // renderForm() {
    //     return (
    //         <form>
    //             <input
    //             className={classes.Input}
    //             name="nama"
    //             type="text"
    //             placeholder="Nama"
    //             />
    //             <input
    //             className={classes.Input}
    //             name="nomorTelepon"
    //             type="number"
    //             placeholder="Nomor Telepon"
    //             />
    //             <input
    //             className={classes.Input}
    //             name="alamat"
    //             type="text"
    //             placeholder="Alamat"
    //             />
    //             <input
    //             className={classes.Input}
    //             name="rating"
    //             type="number"
    //             placeholder="Rating"
    //             />
    //             <Button btnType="Danger" onClick={this.canceledHandler}>
    //                 CANCEL
    //             </Button>
    //             <Button btnType="Success" onClick={this.submitAddRestoranHandler}>
    //                 SUBMIT
    //             </Button>

    //         </form>
    //     );
    // }
}

export default Modal;
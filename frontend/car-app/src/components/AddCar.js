import React, { Component } from "react";
import CarDataService from "../services/CarService";

import { TextField, Button, withStyles } from "@material-ui/core"
import { styles } from "../css-common"

class AddCar extends Component {
    constructor(props) {
        super(props);
        this.onChangeName = this.onChangeName.bind(this);
        this.onChangeModel = this.onChangeModel.bind(this);
        this.onChangeBrand = this.onChangeBrand.bind(this);
        this.onChangeDate = this.onChangeDate.bind(this);
        this.saveCar = this.saveCar.bind(this);
        this.newCar = this.newCar.bind(this);

        this.state = {
            id: null,
            name: "",
            model: "",
            brand: "",
            date: "",
        };
    }

    onChangeName(e) {
        this.setState({
            name: e.target.value
        });
    }
    onChangeModel(e) {
        this.setState({
            model: e.target.value
        });
    }    onChangeBrand(e) {
        this.setState({
            brand: e.target.value
        });
    }    onChangeDate(e) {
        this.setState({
            date: e.target.value
        });
    }

    saveCar() {
        var data = {
            name: this.state.name,
            model: this.state.model,
            brand: this.state.brand,
            date: this.state.date,
        };

        CarDataService.create(data)
            .then(response => {
                this.setState({
                    id: response.data.id,
                    name: response.data.name,
                    model: response.data.model,
                    brand: response.data.brand,
                    date: response.data.date,

                    submitted: true
                });
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
    }

    newCar() {
        this.setState({
            id: null,
            name: "",
            model: "",
            brand: "",
            date: "",
        });
    }

    render() {
        const { classes } = this.props

        return (
            <React.Fragment>
                {this.state.submitted ? (
                    <div className={classes.form}>
                        <h4>You submitted successfully!</h4>
                        <Button
                            size="small"
                            color="primary"
                            variant="contained"
                            onClick={this.newCar}>
                            Add
                        </Button>
                    </div>
                                    ) : (
                        <div className={classes.form}>
                            <div className={classes.textField}>
                                <TextField
                                    label="Name"
                                    name="Name"
                                    value={this.state.name}
                                    onChange={this.onChangeName}
                                    required
                                />
                            </div>

                            <div className={classes.textField}>
                                <TextField
                                    label="Model"
                                    name="Model"
                                    value={this.state.model}
                                    onChange={this.onChangeModel}
                                    required
                                />
                            </div>
                            <div className={classes.textField}>
                                <TextField
                                    label="Brand"
                                    name="Brand"
                                    value={this.state.brand}
                                    onChange={this.onChangeBrand}
                                    required
                                />
                            </div>
                            <div className={classes.textField}>
                                <TextField
                                    label="Date"
                                    name="Date"
                                    value={this.state.date}
                                    onChange={this.onChangeDate}
                                    required
                                />
                            </div>

                            <Button
                                size="small"
                                color="primary"
                                variant="contained"
                                onClick={this.saveCar}>
                                Submit
                            </Button>
                        </div>
                    )}
            </React.Fragment>
        );
    }
}

export default withStyles(styles)(AddCar)
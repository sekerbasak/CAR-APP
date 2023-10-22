import React, {Component} from "react";
import CarDataService from "../services/CarService";

import {styles} from "../css-common"
import {Button, TextField, withStyles} from "@material-ui/core";

class Car extends Component {
    constructor(props) {
        super(props);
        this.onChangeName = this.onChangeName.bind(this);
        this.onChangeModel = this.onChangeModel.bind(this);
        this.onChangeBrand = this.onChangeBrand.bind(this);
        this.onChangeDate = this.onChangeDate.bind(this);

        this.getCar = this.getCar.bind(this);
        this.updatePublished = this.updatePublished.bind(this);
        this.updateCar = this.updateCar.bind(this);
        this.deleteCar = this.deleteCar.bind(this);

        this.state = {
            currentCar: {
                id: null,
                name: "",
                model: "",
                brand: "",
                date: "",
                user:""
            },
            message: ""
        };
    }

    componentDidMount() {
        this.getCar(this.props.match.params.id);
    }

    onChangeName(e) {
        const name = e.target.value;
        console.log(name);
        this.setState(function (prevState) {

            return {
                currentCar: {
                    ...prevState.currentCar,
                    name: name
                }
            };
        });
        console.log(name);

    }

    onChangeModel(e) {
        const model = e.target.value;

        this.setState(prevState => ({
            currentCar: {
                ...prevState.currentCar,
                model: model
            }
        }));
    }

    onChangeBrand(e) {
        const brand = e.target.value;

        this.setState(prevState => ({
            currentCar: {
                ...prevState.currentCar,
                brand: brand
            }
        }));
    }

    onChangeDate(e) {
        const date = e.target.value;

        this.setState(prevState => ({
            currentCar: {
                ...prevState.currentCar,
                date: date
            }


        }));

    }

    getCar(id) {
        CarDataService.get(id)
            .then(response => {
                this.setState({
                    currentCar: response.data
                });
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
    }

    updatePublished(status) {
        var data = {
            id: this.state.currentCar.id,
            name: this.state.currentCar.name,
            model: this.state.currentCar.model,
            brand: this.state.currentCar.brand,
            date: this.state.currentCar.date
        };

        CarDataService.update(data)
            .then(response => {
                this.setState(prevState => ({
                    currentCar: {
                        ...prevState.currentCar
                    }
                }));
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
    }

    updateCar() {
        CarDataService.update(
            this.state.currentCar
        )
            .then(response => {
                console.log(response.data);
                this.setState({
                    message: "The car was updated successfully!"
                });
            })
            .catch(e => {
                console.log(e);
            });
    }

    deleteCar() {
        CarDataService.delete(this.state.currentCar.id)
            .then(response => {
                console.log(response.data);
                this.props.history.push('/cars')
            })
            .catch(e => {
                console.log(e);
            });
    }

    render() {
        const {currentCar} = this.state;
        const {classes} = this.props

        return (
            <div>
                {currentCar ? (
                    <div className={classes.form}>
                        <h2>Car</h2>
                        <form>
                            <div>
                                <TextField
                                    className={classes.textField}
                                    label="Name"
                                    name="Name"
                                    value={currentCar.name}
                                    onChange={this.onChangeName}
                                    required
                                />
                            </div>
                            <div>
                                <TextField
                                    className={classes.textField}
                                    label="Model"
                                    name="Model"
                                    value={currentCar.model}
                                    onChange={this.onChangeModel}
                                    required
                                />
                            </div>
                            <div>
                                <TextField
                                    className={classes.textField}
                                    label="Brand"
                                    name="Brand"
                                    value={currentCar.brand}
                                    onChange={this.onChangeBrand}
                                    required
                                />
                            </div>
                            <div>
                                <TextField
                                    className={classes.textField}
                                    label="Date"
                                    name="Date"
                                    value={currentCar.date}
                                    onChange={this.onChangeDate}
                                    required
                                />
                            </div>


                        </form>
                        <div className={classes.buttonWrapper}>

                            <Button
                                className={`${classes.delete} ${classes.button}`}
                                onClick={this.deleteCar}
                            >
                                Delete
                            </Button>

                            <Button
                                type="submit"
                                className={`${classes.update} ${classes.button}`}
                                onClick={this.updateCar}
                            >
                                Update
                            </Button>
                        </div>
                        <p>{this.state.message}</p>
                    </div>
                ) : (
                    <div>
                        <br/>
                        <p>Please click on a Car...</p>
                    </div>
                )}
            </div>
        );
    }
}

export default withStyles(styles)(Car)
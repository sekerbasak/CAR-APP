import React, {Component} from "react";
import CarDataService from "../services/CarService";
import {Link} from "react-router-dom";

import {styles} from "../css-common"
import {TextField, Button, Grid, ListItem, withStyles} from "@material-ui/core";

class CarsList extends Component {
    constructor(props) {
        super(props);
        this.retrieveCars = this.retrieveCars.bind(this);
        this.refreshList = this.refreshList.bind(this);
        this.setActiveCar = this.setActiveCar.bind(this);

        this.state = {
            cars: [],
            currentCar: null,
            currentIndex: -1,
        };
    }

    componentDidMount() {
        this.retrieveCars();
    }


    retrieveCars() {
        CarDataService.getAll()
            .then(response => {
                this.setState({
                    cars: response.data
                });
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
    }

    refreshList() {
        this.retrieveCars();
        this.setState({
            currentCar: null,
            currentIndex: -1
        });
    }

    setActiveCar(car, index) {
        this.setState({
            currentCar: car,
            currentIndex: index
        });
    }





    render() {
        const {classes} = this.props
        const {searchTitle, cars, currentCar, currentIndex} = this.state;

        return (
            <div className={classes.form}>
                <Grid container>

                    <Grid item md={4}>
                        <h2>Cars List</h2>

                        <div className="list-group">
                            {cars &&
                                cars.map((car, index) => (
                                    <ListItem
                                        selected={index === currentIndex}
                                        onClick={() => this.setActiveCar(car, index)}
                                        divider
                                        button
                                        key={index}>
                                        {car.name} {' '}
                                        {car.model} {' '}
                                        {car.brand} {' '}
                                        {car.date} {' '}
                                    </ListItem>
                                ))}
                        </div>

                    </Grid>
                    <Grid item md={8}>
                        {currentCar ? (
                            <div className={classes.car}>
                                <h4>Car About</h4>
                                <div className={classes.detail}>
                                    <label>
                                        <strong>Car Name:</strong>
                                    </label>{" "}
                                    {currentCar.name}
                                </div>
                                <div className={classes.detail}>
                                    <label>
                                        <strong>Brand:</strong>
                                    </label>{" "}
                                    {currentCar.brand}
                                </div>
                                <div className={classes.detail}>
                                    <label>
                                        <strong>Model:</strong>
                                    </label>{" "}
                                    {currentCar.model}
                                </div>
                                <div className={classes.detail}>
                                    <label>
                                        <strong>Date:</strong>
                                    </label>{" "}
                                    {currentCar.date}
                                </div>

                                <Link
                                    to={"/cars/" + currentCar.id}
                                    className={classes.edit}
                                >
                                    Edit
                                </Link>
                            </div>
                        ) : (
                            <div>
                                <br/>
                                <p className={classes.car}>Please click on a Car...</p>
                            </div>
                        )}
                    </Grid>
                </Grid>
            </div>
        );
    }
}

export default withStyles(styles)(CarsList)
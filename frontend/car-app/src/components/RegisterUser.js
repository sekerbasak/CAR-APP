import React, { Component } from "react";
import UserDataService from "../services/UserService";

import { TextField, Button, withStyles } from "@material-ui/core"
import { styles } from "../css-common"
import CarsList from "./CarsList";
import AuthService from "../services/AuthService";

class RegisterUser extends Component {
    constructor(props) {
        super(props);
        this.onChangeUsername = this.onChangeUsername.bind(this);
        this.onChangeFirstname = this.onChangeFirstname.bind(this);
        this.onChangeLastname = this.onChangeLastname.bind(this);
        this.onChangeEmail = this.onChangeEmail.bind(this);
        this.onChangePassword = this.onChangePassword.bind(this);

        this.saveUser = this.saveUser.bind(this);
        this.newUser = this.newUser.bind(this);

        this.state = {
            username: "",
            firstname: "",
            lastname: "",
            email: "",
            password: "",
            role:""
        };
    }

    onChangeUsername(e) {
        this.setState({
            username: e.target.value
        });
    }
    onChangeFirstname(e) {
        this.setState({
            firstname: e.target.value
        });
    }    onChangeLastname(e) {
        this.setState({
            lastname: e.target.value
        });
    }    onChangeEmail(e) {
        this.setState({
            email: e.target.value
        });
    }
    onChangePassword(e) {
        this.setState({
            password: e.target.value
        });
    }

    saveUser() {
        var data = {
            username: this.state.username,
            firstname: this.state.firstname,
            lastname: this.state.lastname,
            email: this.state.email,
            password: this.state.password,
        };

        AuthService.register(data)
            .then(response => {
                this.setState({
                    username: response.data.username,
                    firstname: response.data.name,
                    lastname: response.data.lastname,
                    email: response.data.email,
                    password: response.data.password,

                    submitted: true
                });
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
    }

    newUser() {
        this.setState({
            username: "",
            firstname: "",
            lastname: "",
            email: "",
            password: "",
            role:""
        });
    }

    render() {
        const { classes } = this.props

        return (
            <React.Fragment>
                {this.state.submitted ? (
                    <div className={classes.form}>
                        <h4>Login successfully!</h4>
                        <Button
                            size="small"
                            color="primary"
                            variant="contained"
                            onClick={this.newUser}>
                            Add
                        </Button>
                    </div>
                                    ) : (


                        <div className={classes.form}>
                            <h2>SIGN UP</h2>
                            <div className={classes.textField}>
                                <TextField
                                    label="firstname"
                                    name="firstname"
                                    value={this.state.firstname}
                                    onChange={this.onChangeFirstname}
                                    required
                                />
                            </div>

                            <div className={classes.textField}>
                                <TextField
                                    label="lastname"
                                    name="Model"
                                    value={this.state.lastname}
                                    onChange={this.onChangeLastname}
                                    required
                                />
                            </div>
                            <div className={classes.textField}>
                                <TextField
                                    label="E-mail"
                                    name="eMail"
                                    value={this.state.email}
                                    onChange={this.onChangeEmail}
                                    required
                                />
                            </div>
                            <div className={classes.textField}>
                                <TextField
                                    label="username"
                                    name="username"
                                    value={this.state.username}
                                    onChange={this.onChangeUsername}
                                    required
                                />
                            </div>          <div className={classes.textField}>
                                <TextField
                                    label="Password"
                                    name="password"
                                    value={this.state.password}
                                    onChange={this.onChangePassword}
                                    required
                                />
                            </div>

                            <Button
                                size="small"
                                color="secondary"
                                variant="contained"
                                onClick={this.saveUser}>
                                Sign Up
                            </Button>
                        </div>
                    )}
            </React.Fragment>
        );
    }
}

export default withStyles(styles)(RegisterUser)
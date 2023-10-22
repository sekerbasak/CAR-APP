import React, {Component} from "react";
import UserDataService from "../services/UserService";

import {TextField, Button, withStyles} from "@material-ui/core"
import {styles} from "../css-common"
import LoginService from "../services/AuthService";

class Login extends Component {
    constructor(props) {
        super(props);
        this.onChangeUsername = this.onChangeUsername.bind(this);
        this.onChangePassword = this.onChangePassword.bind(this);

        this.login = this.login.bind(this);
        this.newUser = this.newUser.bind(this);

        this.state = {
            username: "",
            password: ""

        };
    }

    onChangeUsername(e) {
        this.setState({
            username: e.target.value
        });
    }

    onChangePassword(e) {
        this.setState({
            password: e.target.value
        });
    }

    login() {
        var data = {
            username: this.state.username,
            password: this.state.password,
        };

        LoginService.login(data)
            .then(response => {
             /*   this.setState({
                    submitted: true
                });*/
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
    }

    newUser() {
        this.setState({
            username: "",
            password: ""

        });
    }

    render() {
        const {classes} = this.props

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
                        <h2>SIGN IN</h2>
                        <div className={classes.textField}>
                            <TextField
                                label="username"
                                name="username"
                                value={this.state.username}
                                onChange={this.onChangeUsername}
                                required
                            />
                        </div>
                        <div className={classes.textField}>
                            <TextField
                                label="password"
                                name="password"
                                value={this.state.password}
                                onChange={this.onChangePassword}
                                required
                            />
                        </div>

                        <Button
                            size="small"
                            color="warning"
                            variant="contained"
                            onClick={this.login}>
                            Sign In
                        </Button>
                    </div>
                )}
            </React.Fragment>
        );
    }
}

export default withStyles(styles)(Login)
import React, { Component } from 'react'
import './CSS/todo.css'



class Form extends Component {
    constructor(props) {
        super(props)

        this.state = {
            UserName: "",
            Email: "",
            password: "",
            //gender: "",


        }
        this.handleSubmit=this.handleSubmit.bind(this)
    }

    Userhandler = (event) => {
        this.setState({
            UserName: event.target.value
        })
    }
    Emailhandler = (event) => {
        this.setState({
            Email: event.target.value
        })
    }
    passwordhandler = (event) => {
        this.setState({
            password: event.target.value
        })
    }


    handleSubmit = (event) => {
        alert(`${this.state.UserName}   Registered Successfully !!!!`)
        console.log(this.state);
        this.setState({
            UserName: "",
            Email: "",
            password: '',

        })
     event.preventDefault()
        
    }




    render() {
        return (
            <div>

                <form onSubmit={this.handleSubmit}>
                    <h1>User Registration</h1>
                    <label>User Name :</label> <input type="text" value={this.state.UserName} onChange={this.Userhandler} placeholder="User Name..." /><br />
                    <label>Email :</label> <input type="text" value={this.state.Email} onChange={this.Emailhandler} placeholder="Email..." /><br />
                    <label>Password :</label> <input type="password" value={this.state.password} onChange={this.passwordhandler} placeholder="Password..." /><br />
                    <input type="submit" value="Sign up" />
                </form>

            </div>
            
        )
    }
}

export default Form

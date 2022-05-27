import React, { Component } from 'react'
import axios from 'axios'
class AddColumn extends Component {
    constructor(props) {
        super(props)

        this.state = {
            board_id: '',
        }
    }

    changeHandler = e => {
        this.setState({ [e.target.name]: e.target.value })
    }

    submitHandler = e => {
        e.preventDefault()
        console.log(this.state)
        axios
            .post('https://jsonplaceholder.typicode.com/posts', this.state)
            .then(response => {
                console.log(response)
            })
            .catch(error => {
                console.log(error)
            })
    }

    render() {
        const { board_id} = this.state
        return (
            <div>
                <form onSubmit={this.submitHandler}>
                    <div>
                        <input
                            type="text"
                            name="board_id"
                            value={board_id}
                            onChange={this.changeHandler}
                        />
                    </div>
                    <button type="submit">Submit</button>
                </form>
            </div>
        )
    }
}

export default AddColumn
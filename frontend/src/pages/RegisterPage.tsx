import {FormEvent, useState} from "react";
import {registerUser} from "../service/ApiService";
import {useNavigate} from "react-router-dom";


export default function RegisterPage(){

    const [username, setUsername] = useState("")
    const [password, setPassword] = useState("")

    const nav = useNavigate()

    function register(formEvent: FormEvent){
        formEvent.preventDefault()
        registerUser(username, password)
            .then(()=> nav("/"))
    }

    return(
        <div>
            <form onSubmit={register}>
                <div>
                    <input type={"text"} value={username} placeholder={"Username"} onChange={event => setUsername(event.target.value)}/>
                </div>
                <div>
                    <input type={"password"} value={password} placeholder={"Password"} onChange={event => setPassword(event.target.value)}/>
                </div>
                <div>
                    <button type={"submit"}>Register</button>
                </div>
            </form>
        </div>
    )
}
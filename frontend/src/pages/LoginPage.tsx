import {FormEvent, useState} from "react";
import {Link, useNavigate} from "react-router-dom";
import {sendLogin} from "../service/ApiService";


export default function LoginPage(){

    const [username, setUsername] = useState("")
    const [password, setPassword] = useState("")

    const nav = useNavigate()

    function login(formEvent: FormEvent){
        formEvent.preventDefault()
        sendLogin(username, password)
            .then(data => localStorage.setItem("token", data.token))
            .then(()=> nav("/userdetails"))
    }

    return(
        <div>
            <form onSubmit={login}>
                <div>
                    <input type={"text"} value={username} placeholder={"Username"} onChange={event => setUsername(event.target.value)}/>
                </div>
                <div>
                    <input type={"password"} value={password} placeholder={"Password"} onChange={event => setPassword(event.target.value)}/>
                </div>
                <div>
                    <button type={"submit"}>Login</button>
                </div>
            </form>
            <div>
                <Link to={"/register"}>Create Account</Link>
            </div>
        </div>
    )
}
import axios,{AxiosResponse} from "axios";
import {LoginResponse, MyUser} from "./models";


export function sendLogin(loginUsername: string, loginPassword: string){
    return axios.post("/api/login",{
        userName: loginUsername,
        password: loginPassword
    })
        .then((response: AxiosResponse<LoginResponse>) => response.data)
}

export function registerUser(username: string, password: string){
    return axios.post("/api/user", {
        username,
        password
    })

}

export function getUser(){
    return axios.get("/api/user/me", {
        headers: {
            Authorization: "Bearer " + localStorage.getItem("token")
        }
    })
        .then((response: AxiosResponse<MyUser>) => response.data)
}
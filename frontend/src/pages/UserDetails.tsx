import {useEffect, useState} from "react";
import {getUser} from "../service/ApiService";


export default function UserDetails(){

    const [userName, setUserName] = useState("")

    useEffect(()=>{
        getUser()
            .then(data => setUserName(data.username))
    },[])
    return(
        <div>
            {userName}
        </div>
    )
}
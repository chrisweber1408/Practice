import React, { useState, useEffect } from 'react';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import LoginPage from "./pages/LoginPage";
import RegisterPage from "./pages/RegisterPage";
import UserDetails from "./pages/UserDetails";

export default function App() {
    return(
        <BrowserRouter>
            <Routes>
                <Route path={"/"} element={<LoginPage/>}/>
                <Route path={"/register"} element={<RegisterPage/>}/>
                <Route path={"/userdetails"} element={<UserDetails/>}/>
            </Routes>
        </BrowserRouter>
    )

}

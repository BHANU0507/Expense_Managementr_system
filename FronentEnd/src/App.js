import { useEffect, useState } from 'react';
import './App.css';
import Login from './components/login/Login';
import NavigationBar from './components/NavigationBar';
import {Routes,Route} from "react-router-dom"
function App() {
  
  return (
    <div className="App">
        <Routes>
        <Route path="/" element = {<Login/>}></Route>
        <Route path="/dashboard/*" element = {<NavigationBar/>}></Route>
       </Routes>
    </div>
  );
}

export default App;

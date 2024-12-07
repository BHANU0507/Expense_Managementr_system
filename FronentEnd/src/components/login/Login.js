import react from "react";


import { useState } from "react";
import ReactDOM from 'react-dom/client';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import { auth } from "../../service/ApiService";
import Button from "@mui/material/Button";
import CssBaseline from "@mui/material/CssBaseline";
import TextField from "@mui/material/TextField";
import FormControlLabel from "@mui/material/FormControlLabel";
import Checkbox from "@mui/material/Checkbox";
import Link from "@mui/material/Link";
import Paper from "@mui/material/Paper";
import Box from "@mui/material/Box";
import Grid from "@mui/material/Grid";
import Typography from "@mui/material/Typography";
import { Container } from "@mui/material";


function Login() {

    const navigate = useNavigate();


    const [user, setUser] = useState({
        username: "",
        password: ""
    })
    const [error, setError] = useState("");
    
    async function submitHandler(e) {
        e.preventDefault()

        await auth(user)
        
        if (localStorage.getItem("Loginstatus")) {
            console.log("navigate")
            navigate("/dashboard")
        }
        else {
            console.log("error")
            setError("Incorrect Credentials!!");
        }

    }

    function changeHandler(event) {
        const { name, value } = event.target;
        setUser({ ...user, [name]: value })
        console.log(user)
    }

    function changepasswordHandler(event) {
        const { name, value } = event.target;
        setUser({ ...user, [name]: value })
        console.log(user)
    }

    return (

        <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100vh', backgroundColor: '#f5eadb' }}>
            <Container maxWidth="xs">
                <Paper elevation={15} round style={{ padding: '20px', textAlign: 'center' }}>


                    <img src="https://th.bing.com/th?id=OIP.mvzumO18d4Syeg31wW8yRQHaFj&w=288&h=216&c=8&rs=1&qlt=90&o=6&dpr=1.7&pid=3.1&rm=2" style={{ width: "100px", marginTop: "30px", paddingRight: '5px', marginRight: '4px' }}></img>


                    {/* Login form in the middle */}
                    <Container maxWidth="lg" style={{ margin: 'auto' }}>
                        <Box
                            component="form"
                            noValidate
                            onSubmit={submitHandler}
                            sx={{ mt: 1 }}
                        >
                            <TextField
                                margin="normal"
                                required
                                fullWidth
                                id="username"
                                label="User ID"
                                name="username"
                                type="text"
                                value={user.username}
                                onChange={changeHandler}
                            />
                            <TextField
                                margin="normal"
                                required
                                fullWidth
                                name="password"
                                label="Password"
                                type="password"
                                id="password"
                                value={user.password}
                                onChange={changeHandler}
                            />
                            {/* Display error message */}
                            {error && (
                                <Typography color="error" variant="body2" sx={{ mt: 1, mb: 1 }}>
                                    {error}
                                </Typography>
                            )}

                            <Button
                                type="submit"
                                variant="contained"
                                sx={{ mt: 1, mb: 1 }}
                            >
                                Sign In
                            </Button>


                        </Box>


                    </Container>


                </Paper>
            </Container>
        </div>

    )
}

export default Login;



// import React, { useState } from "react";
// import { useNavigate } from "react-router-dom";
// import { Container, Paper, TextField, Button, Typography, Box } from "@mui/material";
// import { auth } from "../../service/ApiService";

// function Login() {
//     const navigate = useNavigate();
//     const [user, setUser] = useState({ username: "", password: "" });
//     const [error, setError] = useState(""); // State to handle error messages

//     async function submitHandler(e) {
//         e.preventDefault();
        
//         try {
//             const response = await auth(user);
//             console.log(response)

//             if (response && response.status === 200) {
//                 localStorage.setItem("Loginstatus", "true");
//                 navigate("/dashboard");
//             } else if (response && response.status === 401) {
//                 setError("Password is incorrect");
//             } else if (response && response.status === 404) {
//                 setError("User not found");
//             } else {
//                 setError("An unexpected error occurred");
//             }
//         } catch (err) {
//             setError("An error occurred. Please try again.");
//         }
//     }

//     function changeHandler(event) {
//         const { name, value } = event.target;
//         setUser({ ...user, [name]: value });
//     }

//     return (
//         <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100vh', backgroundColor: '#f5eadb' }}>
//             <Container maxWidth="xs">
//                 <Paper elevation={15} style={{ padding: '20px', textAlign: 'center' }}>
//                     <img src="https://th.bing.com/th?id=OIP.mvzumO18d4Syeg31wW8yRQHaFj&w=288&h=216&c=8&rs=1&qlt=90&o=6&dpr=1.7&pid=3.1&rm=2" 
//                         alt="logo" style={{ width: "100px", marginTop: "30px", paddingRight: '5px', marginRight: '4px' }} />
                    
//                     {/* Login form */}
//                     <Container maxWidth="lg" style={{ margin: 'auto' }}>
//                         <Box component="form" noValidate onSubmit={submitHandler} sx={{ mt: 1 }}>
//                             <TextField
//                                 margin="normal"
//                                 required
//                                 fullWidth
//                                 id="username"
//                                 label="User ID"
//                                 name="username"
//                                 type="text"
//                                 value={user.username}
//                                 onChange={changeHandler}
//                             />
//                             <TextField
//                                 margin="normal"
//                                 required
//                                 fullWidth
//                                 name="password"
//                                 label="Password"
//                                 type="password"
//                                 id="password"
//                                 value={user.password}
//                                 onChange={changeHandler}
//                             />

                            // {/* Display error message */}
                            // {error && (
                            //     <Typography color="error" variant="body2" sx={{ mt: 1, mb: 1 }}>
                            //         {error}
                            //     </Typography>
                            // )}

//                             <Button
//                                 type="submit"
//                                 variant="contained"
//                                 sx={{ mt: 1, mb: 1 }}
//                             >
//                                 Sign In
//                             </Button>
//                         </Box>
//                     </Container>
//                 </Paper>
//             </Container>
//         </div>
//     );
// }

// export default Login;

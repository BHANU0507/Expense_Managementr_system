import React, { useState, useEffect } from 'react';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import IconButton from '@mui/material/IconButton';
import Typography from '@mui/material/Typography';
import MenuItem from '@mui/material/MenuItem';
import Menu from '@mui/material/Menu';
import MenuIcon from '@mui/icons-material/Menu';
import AccountCircle from '@mui/icons-material/AccountCircle';
import MoreIcon from '@mui/icons-material/MoreVert';
import { Button } from '@mui/material';
import ManagerHome from './ManagerComponents/ManagerHome1';
import Home from './Home';
import PopUp from './AddExpense/PopUp';
import { EmployeeDetails, managerDetails, setMng, setUser } from '../service/ApiService';
import { Route, Routes, Link, useNavigate } from 'react-router-dom';
import ManageAccountsIcon from '@mui/icons-material/ManageAccounts';


export default function NavigationBar() {
  const [anchorEl, setAnchorEl] = React.useState(null);
  const [mobileMoreAnchorEl, setMobileMoreAnchorEl] = React.useState(null);
  const [data, setData] = useState([])
  const [manager, setManager] = useState([])

  const [ismanager, setIsmanager] = useState(false)

  const navigate = useNavigate();

  useEffect(() => {

    EmployeeDetails().then((response) => {
      setData(response.data)
      console.log(data)
    }).catch(error => {
      console.log(error);
    })

    managerDetails().then((response) => {
      setManager(response.data)
      console.log(manager)
    }).catch(error => {
      console.log(error);
    })



    //const isEmpIdInEmployeeData=data.some(employee=>employee.empId===empIdd);
    // setIsmanager(isEmpIdInManagerData);
    //   console.log(ismanager)
  }, [])

  console.log(data.empDOB)
  setMng(data.mgnId)

  const isMenuOpen = Boolean(anchorEl);
  const isMobileMenuOpen = Boolean(mobileMoreAnchorEl);

  const handleProfileMenuOpen = (event) => {
    setAnchorEl(event.currentTarget);
  };

  const handleMobileMenuClose = () => {
    setMobileMoreAnchorEl(null);
  };

  const handleMenuClose = () => {

    localStorage.clear();
    navigate("/");
    setAnchorEl(null);
    handleMobileMenuClose();
  };

  const handleMobileMenuOpen = (event) => {
    setMobileMoreAnchorEl(event.currentTarget);
  };

  const menuId = 'primary-search-account-menu';
  const renderMenu = (
    <Menu
      anchorEl={anchorEl}
      anchorOrigin={{
        vertical: 'bottom',
        horizontal: 'right',
      }}
      id={menuId}
      keepMounted
      transformOrigin={{
        vertical: 'bottom',
        horizontal: 'right',
      }}
      open={isMenuOpen}
      onClose={handleMenuClose}
    >
      
      <MenuItem onClick={handleMenuClose}>Logout</MenuItem>
    </Menu>
  );

  const mobileMenuId = 'primary-search-account-menu-mobile';
  const renderMobileMenu = (
    <Menu
      anchorEl={mobileMoreAnchorEl}
      anchorOrigin={{
        vertical: 'bottom',
        horizontal: 'right',
      }}
      id={mobileMenuId}
      keepMounted
      transformOrigin={{
        vertical: 'bottom',
        horizontal: 'right',
      }}
      open={isMobileMenuOpen}
      onClose={handleMobileMenuClose}
    >

      <MenuItem onClick={handleProfileMenuOpen}>
        <IconButton
          size="large"
          aria-label="account of current user"
          aria-controls="primary-search-account-menu"
          aria-haspopup="true"
          color="inherit"
        >
          <AccountCircle />
        </IconButton>
        <p>{data.empName}</p>
      </MenuItem>
    </Menu>
  );




  return (
    <Box sx={{ flexGrow: 1, color: "red" }}>
      <AppBar position="static" sx={{ bgcolor: '#121c4e', boxShadow: '5px 5px 10px #dad6d1,-5px -5px 10px #ffffff' }}>
        <Toolbar>
          <IconButton
            size="large"
            edge="start"
            color="inherit"
            aria-label="open drawer"
            sx={{ mr: 2 }}
            onClick={() => navigate("/dashboard")}
          >
            <MenuIcon />
          </IconButton>

          <img src="https://th.bing.com/th?id=OIP.mvzumO18d4Syeg31wW8yRQHaFj&w=288&h=216&c=8&rs=1&qlt=90&o=6&dpr=1.7&pid=3.1&rm=2" style={{width:"45px",marginTop:"0px",paddingRight:'3px',marginRight:'4px'}}></img>
          <Typography
            variant="h5"
            noWrap
            component="div"
            sx={{ display: { xs: 'none', sm: 'block', color: '#f5f5f5' } }}
          >
            Expense Management
          </Typography>

          <Box sx={{ flexGrow: 1 }} />

          {manager.some(manager1 => manager1.mgnId === data.empId) ? <IconButton
            size="large"
            aria-label="show more"
            aria-controls={mobileMenuId}
            aria-haspopup="true"
            onClick={() => navigate("/dashboard/manager")}
            color="inherit"
          >
            <ManageAccountsIcon />
          </IconButton> : " "}



          <PopUp data={data} />

          <Box sx={{ display: { xs: 'none', md: 'flex' } }}>
            <IconButton
              size="large"
              edge="end"
              aria-label="account of current user"
              aria-controls={menuId}
              aria-haspopup="true"
              onClick={handleProfileMenuOpen}

              sx={{ borderRadius: 0, ml: 3, color: "#f5f5f5" }}

            >

              <Typography variant="h6"
                noWrap
                component="div"
                sx={{ display: { xs: 'none', sm: 'block', fontStyle: 'italic', fontSize: 'medium' } }}
              >
                {data.empName}
              </Typography>
              <AccountCircle sx={{ fontSize: 35, mx: 1 }} />
            </IconButton>
          </Box>
          <Box sx={{ display: { xs: 'flex', md: 'none' } }}>
            <IconButton
              size="large"
              aria-label="show more"
              aria-controls={mobileMenuId}
              aria-haspopup="true"
              onClick={handleMobileMenuOpen}
              color="inherit"
            >
              <MoreIcon />
            </IconButton>

          </Box>

        </Toolbar>
      </AppBar>
      {renderMobileMenu}
      {renderMenu}

      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/manager" element={<ManagerHome />} />
      </Routes>



    </Box>
  );
}

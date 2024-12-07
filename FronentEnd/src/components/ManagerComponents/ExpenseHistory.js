import React, { useState, useEffect } from "react";
import { Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper, Button, TableSortLabel, TablePagination } from "@mui/material";
import ViewAllPopUp from "./ViewAllPopUp";
import { useNavigate } from 'react-router-dom';
import EmployeeDetails from "./EmployeeDetails";
import EmployeeDetailsPopUp from "./EmployeeDetailsPopUp"
import { getExpenseByManager } from "../../service/ApiService";




const viewAllButtonStyle = {
    float: 'right',
    marginBottom: '12px',
    fontSize: '11px',
    backgroundcolor: '#e7e7e7',
}
function ExpenseHistory() {
    const [isViewAllOpen, setIsViewAllOpen] = useState(false);
    const [isViewAllOpen1, setIsViewAllOpen1] = useState(false);
    const [empid, setEmpid] = useState('');
    const [expenseid, setExpenseid] = useState('');

    const navigate = useNavigate();

    const [data, setData] = useState([])

    useEffect(() => {
        getExpenseByManager().then((response) => {
            setData(response.data)
            console.log(response.data)
        }).catch(error => {
            console.log(error);
        })
    }, [])
    const openViewAll = (empid, expenseid) => {

        setEmpid(empid)
        setExpenseid(expenseid)
        console.log(empid, expenseid)
        setIsViewAllOpen(true);
    };
    const closeViewAll = () => {
        setIsViewAllOpen(false);
    };

    const openViewAll1 = () => {
        setIsViewAllOpen1(true);
    };
    const closeViewAll1 = () => {
        setIsViewAllOpen1(false);
    };



    const [orderBy, setOrderBy] = useState('');
    const [order, setOrder] = useState('asc');

    const handleSort = (property) => (event) => {
        const isAsc = orderBy === property && order === 'asc';
        setOrder(isAsc ? 'desc' : 'asc');
        setOrderBy(property);
    };

    console.log(data);

    const sortedData = data.sort((a, b) => {
        if (order === 'asc') {
            return a[orderBy] < b[orderBy] ? -1 : 1;
        } else {
            return a[orderBy] > b[orderBy] ? -1 : 1;
        }
    });

    console.log(sortedData);



    const [page, setPage] = useState(0);
    const [rowsPerPage, setRowsPerPage] = useState(4); // You can set the number of rows per page as per your preference

    const handleChangePage = (event, newPage) => {
        setPage(newPage);
    };

    const handleChangeRowsPerPage = (event) => {
        setRowsPerPage(+event.target.value);
        setPage(0); // Reset to the first page when changing rows per page
    };

    const startIndex = page * rowsPerPage;
    const endIndex = startIndex + rowsPerPage;
    const paginatedData = sortedData.slice(startIndex, endIndex);

    return (
        <div>
            <div className="row">


                <div className="col" style={{ justifyContent: 'left' }}>
                    <h4>Pending Approvals</h4>
                </div>


                <div className="col" style={viewAllButtonStyle}>
        
                    {/* <Button variant="contained" class="btn btn-light" onClick={openViewAll1} style={{ fontSize: '14px', fontWeight: 'bold', color: 'black' }}>
                        View All
                    </Button> */}
                    <Button variant="contained" class="btn btn-light" onClick={openViewAll} style={{ fontSize: '14px', fontWeight: 'bold', color: 'black' }}>
                        Bulk Approval
                    </Button>
                </div>


            </div>
            <div>
                <TableContainer>
                    <Table>
                        <TableHead>
                            <TableRow>
                                <TableCell style={{ fontSize: '18px', fontWeight: 'bold', backgroundColor: '#cfd7e8' }}>
                                    <TableSortLabel
                                        active={orderBy === 'expenseId'}
                                        direction={orderBy === 'expenseId' ? order : 'asc'}
                                        onClick={handleSort('expenseId')}

                                    >
                                        ID
                                    </TableSortLabel>
                                </TableCell>

                                <TableCell style={{ fontSize: '18px', fontWeight: 'bold', backgroundColor: '#cfd7e8' }}>
                                    <TableSortLabel
                                        active={orderBy === 'empName'}
                                        direction={orderBy === 'empName' ? order : 'asc'}
                                        onClick={handleSort('empName')}
                                    >
                                        Name
                                    </TableSortLabel>
                                </TableCell>


                                <TableCell style={{ fontSize: '18px', fontWeight: 'bold', backgroundColor: '#cfd7e8' }}>
                                    <TableSortLabel
                                        active={orderBy === 'empPosition'}
                                        direction={orderBy === 'empPosition' ? order : 'asc'}
                                        onClick={handleSort('empPosition')}
                                    >
                                        Position
                                    </TableSortLabel>
                                </TableCell>

                                <TableCell style={{ fontSize: '18px', fontWeight: 'bold', backgroundColor: '#cfd7e8' }}>
                                    <TableSortLabel
                                        active={orderBy === 'category'}
                                        direction={orderBy === 'category' ? order : 'asc'}
                                        onClick={handleSort('category')}
                                    >
                                        Category
                                    </TableSortLabel>
                                </TableCell>

                                <TableCell style={{ fontSize: '18px', fontWeight: 'bold', backgroundColor: '#cfd7e8' }}>
                                    <TableSortLabel
                                        active={orderBy === 'amount'}
                                        direction={orderBy === 'amount' ? order : 'asc'}
                                        onClick={handleSort('amount')}
                                    >
                                        Amount
                                    </TableSortLabel>
                                </TableCell>


                                <TableCell style={{ fontSize: '18px', fontWeight: 'bold', backgroundColor: '#cfd7e8' }}>

                                    Status

                                </TableCell>


                                <TableCell style={{ fontSize: '18px', fontWeight: 'bold', backgroundColor: '#cfd7e8' }}>
                                    Recipt
                                </TableCell>


                            </TableRow>
                        </TableHead>



                        <TableBody>
                            {paginatedData.map((row) => (
                                <TableRow key={row.expense_id}>
                                    <TableCell>{row.expenseId}</TableCell>
                                    <TableCell>{row.empName}</TableCell>
                                    <TableCell>{row.empPosition}</TableCell>
                                    <TableCell>{row.category}</TableCell>
                                    <TableCell>{row.amount.toFixed(2)}</TableCell>
                                    <TableCell>{row.status}</TableCell>
                                    <TableCell>
                                        <EmployeeDetailsPopUp open={isViewAllOpen} handleClose={closeViewAll} empdata={row} />
                                    </TableCell>
                                </TableRow>
                            ))}
                        </TableBody>
                    </Table>
                </TableContainer>

                <TablePagination
                    rowsPerPageOptions={[5]}
                    component="div"
                    count={data.length}
                    rowsPerPage={rowsPerPage}
                    page={page}
                    onPageChange={handleChangePage}
                    onRowsPerPageChange={handleChangeRowsPerPage}
                />

                <ViewAllPopUp open={isViewAllOpen1} handleClose={closeViewAll1} />
            </div>
        </div>

    );

}

export default ExpenseHistory;
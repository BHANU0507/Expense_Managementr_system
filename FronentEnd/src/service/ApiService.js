import axios from "axios"
var data
export const setUser = (user) =>{
    data = user;
    console.log(data)
}
var data1
export const setMng = (mng) =>{
    data1 = mng 
    console.log(data1)
}

const currentDate = new Date();
const formateddate = currentDate.toISOString().slice(0,10)  // `${currentDate.getFullYear()}-${currentDate.getMonth()+1}-${currentDate.getDate()}`
console.log(formateddate)

console.log(formateddate)

if(localStorage.getItem('SavedToken') !== null){
    var tok = localStorage.getItem('SavedToken').split(".");
                
                // console.log(JSON.parse(atob(tok[0])));
                var f = JSON.parse(atob(tok[1]));

                console.log(f.sub)
                setUser(f.sub)
}

                


export const auth = async (user) =>{
    await axios.post("http://localhost:8004/auth/login", user)
            .then((response) => {
                let token = response.data;
                localStorage.setItem("SavedToken", 'Bearer ' + token);
                axios.defaults.headers.common['Authorization'] = 'Bearer ' + token;

                var tokens = localStorage.getItem('SavedToken')
                console.log(tokens)

                var tok = localStorage.getItem('SavedToken').split(".");
                
                // console.log(JSON.parse(atob(tok[0])));
                var f = JSON.parse(atob(tok[1]));

                console.log(f.sub)
                setUser(f.sub)

                EmployeeDetails().then((response) => {
                   
                    setMng(response.data.mgnId)
                  }).catch(error =>{
                    console.log(error);
                })

                localStorage.setItem("Loginstatus", true);

            }).catch(error =>{
                console.log(error);
            })

            
            
}


export const pieChart = async () =>{
    return await axios.get(`http://localhost:8001/expense/pieChart?empId=${data}`, { headers: { Authorization:localStorage.getItem('SavedToken') }})
} 

export const lineChart = async () =>{
    return await axios.get(`http://localhost:8001/expense/lineGraph?empId=${data}`, { headers: { Authorization:localStorage.getItem('SavedToken') }})
} 

export const EmployeeDetails = async() =>{
    return await axios.get(`http://localhost:8003/employee/Details/${data}`, { headers: { Authorization:localStorage.getItem('SavedToken') }})
}
export const addExpense = (userdata)=>{
    return  axios.post('http://localhost:8001/expense/addExpensewithfile',userdata, { headers: {'Content-Type': 'multipart/form-data',Authorization:localStorage.getItem('SavedToken') }})
}

export const getExpenseByEmployee = async()=>{
    return await axios.get(`http://localhost:8001/expense/empHistory?empId=${data}`, { headers: { Authorization:localStorage.getItem('SavedToken') }})
}

export const getExpenseByManager = async()=>{
    return await axios.get(`http://localhost:8001/expense/employeesUnderManger?mngId=${data1}`, { headers: { Authorization:localStorage.getItem('SavedToken') }})
}

export const getAllExpenseByManager = async()=>{
    return await axios.get(`http://localhost:8001/expense/expensesUnderManger?mngId=${data1}`, { headers: { Authorization:localStorage.getItem('SavedToken') }})
}

export const pieChartManager = async() =>{
    return await axios.get(`http://localhost:8001/expense/managerpiechart?managerId=${data1}&date=${formateddate}`, { headers: { Authorization:localStorage.getItem('SavedToken') }})
}

export function singleexpenseDetails(empid,expenseid){
    return axios.get(`http://localhost:8001/expense/detailsemployee?empId=${empid}&expenseId=${expenseid}`, { headers: { Authorization:localStorage.getItem('SavedToken') }})
}

export function statusChange(status){
    return axios.post(`http://localhost:8001/expense/status`,status, { headers: { Authorization:localStorage.getItem('SavedToken') }});
}

export const managerDetails=async()=>{
    return await axios.get(`http://localhost:8002/manager/allDetails`, { headers: { Authorization:localStorage.getItem('SavedToken') }});
}

export const CategoryForLineChart = async() =>{
    return await  axios.get(`http://localhost:8001/expense/empcatogerytable?empId=${data}&date=31-10-2024`, { headers: { Authorization:localStorage.getItem('SavedToken') }})
}
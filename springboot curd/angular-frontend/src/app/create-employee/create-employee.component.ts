import { Component } from '@angular/core';

import { Router } from '@angular/router';
import { Employee } from '../model/employee';
import { EmployeeService } from '../services/employee.service';

@Component({
  selector: 'app-create-employee',
  templateUrl: './create-employee.component.html',
  styleUrls: ['./create-employee.component.css']
})
export class CreateEmployeeComponent {
message:any;
  employee: Employee = new Employee();


  constructor(private employeeService: EmployeeService,private router: Router) { }
 
  

  saveEmployee(){
    debugger
    this.employeeService.createEmployee(this.employee).subscribe( data =>{
      console.log(data);
      if(data.id>0){
      this.message='Employee Created Successfully';
       this.router.navigate(['/employees',this.message]);
    }
    },
    error => console.log(error));
  }

 
  
  onSubmit(){
    console.log(this.employee);
    this.saveEmployee();
  }
}

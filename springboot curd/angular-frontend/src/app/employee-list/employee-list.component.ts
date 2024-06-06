import { Component, OnInit } from '@angular/core';

import { ActivatedRoute, Router } from '@angular/router';
import { Employee } from '../model/employee';
import { EmployeeService } from '../services/employee.service';
@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent implements OnInit {

  employees!: Employee[];
message:any;
  constructor(private employeeService: EmployeeService,private route:ActivatedRoute,
    private router: Router) {

      
     }

  ngOnInit(): void {
    this.getEmployees();
     if(this.route.snapshot.params['message']){
   this.message=this.route.snapshot.params['message'];


     }

    
  }

  private getEmployees(){
    debugger
    this.employeeService.getEmployeesList().subscribe(data => {
      debugger
      this.employees = data;
    });
  }

  employeeDetails(id: number){
    this.router.navigate(['employee-details', id]);
  }

  updateEmployee(id: number){
    this.router.navigate(['update-employee', id]);
  }

  deleteEmployee(id: number){
    this.employeeService.deleteEmployee(id).subscribe( data => {
      console.log(data);
      this.getEmployees();
    })
  }
}

import { Component, OnInit, Inject } from '@angular/core';
import { FormControl } from '@angular/forms';
import {MatFormFieldControl} from '@angular/material/form-field';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { HomeComponent } from '../home/home.component';
import { Person } from '../models/person';
import { DataService } from '../services/data.service';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css'],
  providers:[HomeComponent]
})
export class FormComponent implements OnInit {
Age:number;
Name;
  parentAge: any;
  showError=false;
  constructor( public dialogRef: MatDialogRef<HomeComponent>,
    @Inject(MAT_DIALOG_DATA) public data: number,
    private dataService:DataService) { }
// Name=new FormControl('')
  ngOnInit(): void {
  }


addChild(){
 var personInfo={
   name:"",
   age:0,
   parentId:0,
   path:null
 };
  personInfo.name=this.Name;
  personInfo.age=this.Age;
if(this.data==undefined || this.data==0){
  personInfo.parentId=0;
  // this.data.path = this.data.id.toString();
  // personInfo.path = this.data.id.toString();
  // console.log(this.data.path);
  this.dataService.addPersonData(personInfo);
  this.dialogRef.close();
}
else
{
  personInfo.parentId=this.data;
  //check for age
  this.dataService.getById(this.data).subscribe(res=>{
    this.parentAge=res[0].age;
    if(this.parentAge>personInfo.age){
      this.showError=false;
      // this.data.path.concat(this.data.path, "->", this.data.id.toString())
      // console.log(this.data.path)
      this.dataService.addPersonData(personInfo);
      // this.home.setNodeData();
      this.dialogRef.close();
    }
    else{
      this.showError=true;
    }
  })
}
}
}

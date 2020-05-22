import { Component, OnInit ,Injectable, Inject} from '@angular/core';
import { DataService } from '../services/data.service';
import { NestedTreeControl} from '@angular/cdk/tree';
import {MatTreeNestedDataSource} from '@angular/material/tree';
import {BehaviorSubject, of} from 'rxjs';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { FormComponent } from '../form/form.component';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  nestedTreeControl: NestedTreeControl<Person>;
  dataChange: BehaviorSubject<Person[]> = new BehaviorSubject<Person[]>([]);
  data:any;
  dialogRef: MatDialogRef<FormComponent, any>;

  constructor(private dataService: DataService,
    public dialog: MatDialog,
    public nestedDataSource: MatTreeNestedDataSource<Person>
  ){}

  ngOnInit(): void {  
    this.setNodeData();
  }

  setNodeData(): void{
    this.nestedTreeControl = new NestedTreeControl<Person>(this._getChildren);
       this.dataService.getAllData().subscribe(res =>{
        this.data=res;
        this.dataChange.next(this.data);
        this.dataChange.subscribe(res => this.nestedDataSource.data = res); 
      });
  }
  private _getChildren = (node: Person) => { return of(node.children); };
  
  hasNestedChild = (_: number, nodeData: Person) => { if(nodeData.children){return true} else return false };

  openDialog(node) {
     this.dialogRef = this.dialog.open(FormComponent,{
      data:node.id
    });
    this.dialogRef.afterClosed().subscribe(result => {
      // this.setNodeData();
      console.log(`Dialog result: ${result}`);
    });
  }
}

export class Person{
  id: number;
  name: string;
  age: number;
  parentId: number;
  children: Person[];

}
import { Component, OnInit ,Injectable, Inject} from '@angular/core';
import { DataService } from '../services/data.service';
import {SelectionModel} from '@angular/cdk/collections';
import {FlatTreeControl, NestedTreeControl} from '@angular/cdk/tree';
import {MatTreeFlatDataSource, MatTreeFlattener, MatTreeNestedDataSource} from '@angular/material/tree';
import {BehaviorSubject, of} from 'rxjs';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { FormComponent } from '../form/form.component';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  // providers:[NestedTreeControl ]
})
export class HomeComponent implements OnInit {

  nestedTreeControl: NestedTreeControl<Person>;
  // nestedDataSource: MatTreeNestedDataSource<Person>;
  dataChange: BehaviorSubject<Person[]> = new BehaviorSubject<Person[]>([]);
  data:any;

  constructor(private dataService: DataService,
    public dialog: MatDialog,
    public nestedDataSource: MatTreeNestedDataSource<Person>,
    // public  nestedTreeControl: NestedTreeControl<Person>
  )
     {
       this.nestedTreeControl = new NestedTreeControl<Person>(this._getChildren);
      this.dataService.getAllData().subscribe(res =>{
        this.data=res;
      //  nestedDataSource.data = res
        this.dataChange.subscribe(res => nestedDataSource.data = res); 
        this.dataChange.next(this.data);
        console.log("ok");
      });
    }

  ngOnInit(): void {  

  }

  private _getChildren = (node: Person) => { return of(node.children); };
  
  hasNestedChild = (_: number, nodeData: Person) => { if(nodeData.children){return true} else return false };

  openDialog() {
    const dialogRef = this.dialog.open(FormComponent);
    dialogRef.afterClosed().subscribe(result => {
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
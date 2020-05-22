import { Component, OnInit ,Injectable, Inject} from '@angular/core';
import { DataService } from '../services/data.service';
import {SelectionModel} from '@angular/cdk/collections';
import {FlatTreeControl, NestedTreeControl} from '@angular/cdk/tree';
import {MatTreeFlatDataSource, MatTreeFlattener, MatTreeNestedDataSource} from '@angular/material/tree';
import {BehaviorSubject, of} from 'rxjs';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { FormComponent } from '../form/form.component';
import { Person } from '../home/home.component';
import { ActivatedRoute } from '@angular/router';
import * as $ from 'jquery';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  nestedTreeControl: NestedTreeControl<Person>;
  dataChange: BehaviorSubject<Person[]> = new BehaviorSubject<Person[]>([]);
  data:any;
  id: any;
  showError: boolean;

  constructor(private dataService: DataService,
    public dialog: MatDialog,
    public nestedDataSource: MatTreeNestedDataSource<Person>,
    private activatedRoute:ActivatedRoute
  ){}


  ngOnInit(): void {  

    this.setNodeData(this.activatedRoute.snapshot.params.id);
  }

  setNodeData(id): void{
    $("#showError").hide();
    this.nestedTreeControl = new NestedTreeControl<Person>(this._getChildren);
   this.dataService.getById(id).subscribe(res =>{
     this.data=res;
     if(res.length < 1 ){
      $("#showError").show();
     }
     this.showError;
     this.dataChange.subscribe(res => this.nestedDataSource.data = res); 
     this.dataChange.next(this.data);
     console.log("ok");
   });
  }

  private _getChildren = (node: Person) => { return of(node.children); };
  
  hasNestedChild = (_: number, nodeData: Person) => { if(nodeData.children){return true} else return false };

 openDialog(node) {
    const dialogRef = this.dialog.open(FormComponent,{
      data:node
    });
    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
  }

}

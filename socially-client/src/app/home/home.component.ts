import { Component, OnInit ,Injectable, Inject} from '@angular/core';
import { DataService } from '../services/data.service';
import { Person } from '../models/person';
import {SelectionModel} from '@angular/cdk/collections';
import {FlatTreeControl} from '@angular/cdk/tree';
import {MatTreeFlatDataSource, MatTreeFlattener} from '@angular/material/tree';
import {BehaviorSubject} from 'rxjs';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private dataService: DataService
  )
     { }

  data:any;
  ngOnInit(): void {
  
  this.dataService.getAllData().subscribe(res =>{
    this.data=res;
  });   

  }
  
}
import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import {MatFormFieldControl} from '@angular/material/form-field';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent implements OnInit {

  constructor() { }
Name=new FormControl('')
  ngOnInit(): void {
  }

}

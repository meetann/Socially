import { Component } from '@angular/core';
import { Router } from '@angular/router';
import {SearchComponent} from './search/search.component'

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [SearchComponent]
})
export class AppComponent {
  title = 'socially-client';
  parentId:any;
constructor(private route:Router,
  private search: SearchComponent){

}
  onSearch(){
this.route.navigate(["/getChildren",this.parentId]);
// this.search.ngOnInit();
this.search.setNodeData(this.parentId);
  }
}

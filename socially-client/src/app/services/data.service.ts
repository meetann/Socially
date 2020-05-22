import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { Person } from '../models/person';
import {HttpClient} from '@angular/common/http'
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  public refreshNeeded$ = new Subject<void>();

  constructor(private httpClient: HttpClient) {
    
   }

  getAllData(): Observable<any>{

     return this.httpClient.get(`http://localhost:8080/api/person/getAll`);
    }

    getById(id): Observable<any>{
      return this.httpClient.get(`http://localhost:8080/api/person/getById/`+id).pipe(
        tap(() => {
          this.refreshNeeded$.next();
        })
      );
    }

  addPersonData(data){
 this.httpClient.post('http://localhost:8080/api/person/create',data).subscribe(res=>console.log(res)
 )
  }
}

import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Person } from '../models/person';
import {HttpClient} from '@angular/common/http'

@Injectable({
  providedIn: 'root'
})
export class DataService {

  constructor(private httpClient: HttpClient) {
    
   }

  getAllData(): Observable<any>{

     return this.httpClient.get(`http://localhost:8080/api/person/getAll`);
    }
}

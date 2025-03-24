import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PersonService {

  private apiUrl = 'http://localhost:8080/person';

  constructor(private http: HttpClient) { }

  // Tüm kişileri getirir
  getAllPersons() {
    return this.http.get<any[]>(this.apiUrl);
  }

  // Yeni kişi ekler
  savePerson(person: any) {
    return this.http.post(this.apiUrl, person);
  }
}

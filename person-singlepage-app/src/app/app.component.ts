import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { PersonService } from './person.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, FormsModule, HttpClientModule],
  templateUrl: './app.component.html'
})
export class AppComponent implements OnInit {
  
  persons: any[] = [];

  personForm = {
    number: null,
    name: '',
    surname: ''
  };

  constructor(private personService: PersonService) {}

  ngOnInit() {
    this.loadPersons();
  }

  // Tüm kişileri getirir
  loadPersons() {
    this.personService.getAllPersons().subscribe(response => {
      this.persons = response.sort((a,b)=> a.id - b.id);
    });
  }

  // Yeni kişi kaydet
  savePerson() {
    this.personService.savePerson(this.personForm).subscribe(() => {
      this.personForm = { number: null, name: '', surname: '' };
      this.loadPersons(); // listeyi yenile
    });
  }
}

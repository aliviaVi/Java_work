import {Component, OnInit} from '@angular/core';
import {ContactService} from './service/contact.service';
import {Contact} from './model/contact';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [ContactService]
})
export class AppComponent implements OnInit{
  title = 'Contacts';
  contacts: Contact[];

  constructor(public contactService: ContactService) {
  }

  ngOnInit(): void {
    /* this.contactService.getContacts()
      .subscribe(value => this.contacts = value);*/
  }
}

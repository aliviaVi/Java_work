
import {Component, Input, OnInit} from '@angular/core';
import {Contact} from '../model/contact';
import {ContactService} from '../service/contact.service';

@Component({
  selector: 'app-contact-item',
  templateUrl: './contact-item.component.html',
  styleUrls: ['./contact-item.component.css']
})
export class ContactItemComponent implements OnInit {
/*
  @Input()
  contact: Contact;*/

  @Input()
  contacts: Contact[];

  constructor(private contactService: ContactService) {
  }

  ngOnInit(): void {
    /*this.contactService.getContacts()
      .subscribe(value => this.contacts = value);*/
  }

}

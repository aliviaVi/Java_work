import {Component, OnInit} from '@angular/core';
import {Contact} from '../model/contact';
import {ContactService} from '../service/contact.service';

@Component({
  selector: 'app-contact-form',
  templateUrl: './contact-form.component.html',
  styleUrls: ['./contact-form.component.css']
})
export class ContactFormComponent implements OnInit {

  contact: Contact;
  contacts: Contact[];

  isAddingState: boolean;
  private contactService: ContactService;

  constructor(contactService: ContactService) {
    this.contactService = contactService;
  }

  ngOnInit(): void {
    this.isAddingState = true;
    this.contact = new Contact();
    this.contact.id = 1;
  }
  onClickAdd(){
    console.log(this.contact);
    this.contactService.addContact(this.contact)
      .subscribe(value => this.contact = value);
  }

  /*onClickAdd() {
    console.log(this.contact);
    this.contactService.add(this.contact);
  }*/
  clearForm(){
    this.contact = new Contact();
  }


  onClickGetAll() {

    this.contactService.getContacts().subscribe(value => this.contacts = value);
    console.log(this.contacts);
  }
}


import {Injectable} from '@angular/core';
import {Contact} from '../model/contact';
import {Observable, of} from 'rxjs';
import {CONTACTS} from '../model/contacts-mock';
import {HttpClient} from '@angular/common/http';

@Injectable()
export class ContactService {

  private readonly contactsUrl: string;

  constructor(private http: HttpClient) {
    this.contactsUrl = 'http://localhost:8080/contact';
  }

  public getContacts(): Observable<Contact[]>{
    return this.http.get<Contact[]>(this.contactsUrl);
  }

  public addContact(contact: Contact){
    return this.http.post<Contact>(this.contactsUrl, contact);
  }

  add(contact: Contact): void {
    contact.id = CONTACTS.length + 1;
    CONTACTS.push(contact);
  }

  getAll(): Observable<Contact[]> {
    return of(CONTACTS);
  }
}

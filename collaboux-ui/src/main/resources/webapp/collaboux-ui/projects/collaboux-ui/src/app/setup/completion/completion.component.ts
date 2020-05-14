import { Component, OnInit } from '@angular/core';
import {NgSelectComponent} from "@ng-select/ng-select";

@Component({
  selector: 'app-completion',
  templateUrl: './completion.component.html',
  styleUrls: ['./completion.component.scss']
})
export class CompletionComponent implements OnInit {

  public selectedCompanies = ['Uber', 'Microsoft'];
  public companies: any[] = [];
  public loading = false;
  public companiesNames = ['Uber', 'Microsoft', 'Flexigen','Hip hop', "Jazz", "Reggea", "Afro hip hip", "Alte Rock", "Alte Jazz", "RnB"];

  constructor() { }
  ngOnInit() {
    this.companiesNames.forEach((c, i) => {
      this.companies.push({ id: i, name: c, selected:true });
    });
  }

  /**
   * note
   * context bind to this method is an instance of NgSelectComponent
   */

  addTag(){
    return new Promise((resolve) => {
      this.loading = true;
      setTimeout(() => {
        // @ts-ignore
        resolve({ id: this.items.length, name: this.searchTerm, valid: true });
        this.loading = false;
      }, 1000);
    })
  }

}

import { Component, OnInit } from '@angular/core';
import { environment } from '../../../environments/environment';
import {CacheableHttpClientService} from "../../shared/services/cacheable-http-client.service";

@Component({
  selector: 'app-completion',
  templateUrl: './completion.component.html',
  styleUrls: ['./completion.component.scss']
})
export class CompletionComponent implements OnInit {

  public selectedGenres = ['Hip hop', 'Afro beat','Jazz', 'Reggae'];
  public genres: any[] = [];
  public loading = false;


  constructor(private http:CacheableHttpClientService) { }

  ngOnInit() {
    this.http.cacheableGet('genres',`${environment.appUrl}/api/utility/genres`).subscribe((genres:any[])=>{
        this.genres = genres.map((value,index)=>{ return {id:index,value}; });
        this.genres = this.genres.slice(0,40);
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

import {ChangeDetectionStrategy, Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {environment} from '../../../environments/environment';
import {CacheableHttpClientService} from "../../shared/services/cacheable-http-client.service";
import {ModalService} from "../../shared/services/modal.service";
import {IndexComponent} from "../../index/index.component";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {ImageCropperComponent} from "../../modals/image-cropper/image-cropper.component";

@Component({
  selector: 'app-completion',
  templateUrl: './completion.component.html',
  styleUrls: ['./completion.component.scss'],
  changeDetection:ChangeDetectionStrategy.OnPush
})
export class CompletionComponent implements OnInit {

  public selectedGenres = ['Hip hop', 'Afro beat','Jazz', 'Reggae'];
  public genres: any[] = [];
  public loading = false;

  @ViewChild('profileImage')
  private profileImage:ElementRef;

  public profileForm = new FormGroup({
    username: new FormControl('',[Validators.required]),
    nickname: new FormControl('',[Validators.required]),
    email: new FormControl('',[Validators.required, Validators.email]),
    firstname: new FormControl('',[Validators.required]),
    lastname: new FormControl('',[Validators.required]),
    location: new FormControl('',[])
  });

  constructor(private http:CacheableHttpClientService, private modalService:ModalService) { }

  tryUpload($event){
    $event.preventDefault();
    this.profileImage.nativeElement.click();
  }

  handleFileUpload($event){
    $event.preventDefault();
    console.log($event.target.files);
    this.modalService.init(ImageCropperComponent, {}, {});
  }


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

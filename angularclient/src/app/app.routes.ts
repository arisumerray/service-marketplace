import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PostListComponent } from './post-list/post-list.component';
import { PostFormComponent } from './post-form/post-form.component';
import { SignupFormComponent } from './signup-form/signup-form.component';
import { SigninFormComponent } from './signin-form/signin-form.component';
import { HomeComponent } from './home/home.component';
import { MyprofileComponent } from './myprofile/myprofile.component';
import { DocumentsComponent} from './documents/documents.component';
import { PostsComponent } from './posts/posts.component'
import {PostsUslugiComponent} from './posts-uslugi/posts-uslugi.component'
import {DialogsComponent} from './dialogs/dialogs.component'
import {DialogMessagesComponent} from './dialog-messages/dialog-messages.component'
import {ServiceOfferComponent} from './service-offer/service-offer.component'

export const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full'},
  { path: 'home', component: HomeComponent },
  { path: 'addpost', component: PostFormComponent },
  { path: 'auth/signin', component: SigninFormComponent},
  { path: 'auth/signup', component: SignupFormComponent},
  { path: 'users/me', component: MyprofileComponent},
  { path: 'documents', component: DocumentsComponent},
  { path: 'posts', component: PostsComponent},
  { path: 'postssearch', component: PostsUslugiComponent},
  { path: 'dialogs', component: DialogsComponent},
  {path: 'dialogs/2948', component: DialogMessagesComponent},
  {path: 'serviceoffers/incoming', component: ServiceOfferComponent}
];


@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }

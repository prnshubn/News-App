import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { HttpClientModule } from '@angular/common/http';
import { HomeComponent } from './home/home.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BackgroundComponent } from './background/background.component';
import { ResetComponent } from './reset/reset.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { TokenInterceptor } from './token.interceptor';
import { ExceptionComponent } from './exception/exception.component';
import { EditorialsModule } from './editorials/editorials.module';
import { NewsModule } from './news/news.module';

@NgModule({
  declarations: [
    AppComponent,
    RegisterComponent,
    LoginComponent,
    HomeComponent,
    BackgroundComponent,
    ResetComponent,
    ExceptionComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatSnackBarModule,
    EditorialsModule,
  ],
  providers: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    ResetComponent,
    TokenInterceptor,
    BackgroundComponent,
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}

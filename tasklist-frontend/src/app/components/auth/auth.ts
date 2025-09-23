import { Component } from '@angular/core';

@Component({
  selector: 'auth-component',
  standalone: true,
  imports: [],
  templateUrl: './auth.html',
  styleUrl: './auth.scss'
})
export class Auth {
  isLogin: boolean = true;

  switchIsLogin(){
    this.isLogin = !this.isLogin;
  }

}
  
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RegisterDTO } from 'authTypes';
import { AuthService } from '../../services/auth';

@Component({
  selector: 'auth-component',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './auth.html',
  styleUrl: './auth.scss'
})
export class Auth {
  private authService = new AuthService();

  isLogin: boolean = true;
  username: string = '';
  password: string = '';


  switchIsLogin(){
    this.isLogin = !this.isLogin;
  }

  async submitForm(){

    const data: RegisterDTO = {
      username: this.username,
      password: this.password
    }

    const res = await this.authService.register(data);
    console.log(res);

  }

}
  
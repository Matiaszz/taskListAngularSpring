import { Injectable, OnInit } from '@angular/core';
import { RegisterDTO } from 'authTypes';
import { Api } from './api';
import { AxiosInstance } from 'axios';
import { CompleteUserDTO } from 'userTypes';


@Injectable({
  providedIn: 'root'
})
export class AuthService {

    private api = new Api();

    

    async register(data: RegisterDTO){
      try {
        console.log(data);
        const res = await  this.api.post<CompleteUserDTO>('/user', data);
        return res;

      } catch(err){
        console.error(err);
        return;
      }
    }
}

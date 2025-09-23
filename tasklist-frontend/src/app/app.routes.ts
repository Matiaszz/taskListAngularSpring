import { Routes } from '@angular/router';
import { Auth } from './components/auth/auth';
import { App } from './app';

export const routes: Routes = [
    {
    path: "auth",
    component: Auth
    }
];

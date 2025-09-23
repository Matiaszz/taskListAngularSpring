import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Auth } from './components/auth/auth';
import { Navbar } from './components/navbar/navbar';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, Navbar],
  templateUrl: './app.html',
  styleUrl: './app.scss'
})
export class App {
  protected readonly title = signal('tasklist-frontend');
}

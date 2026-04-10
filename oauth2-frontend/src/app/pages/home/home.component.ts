import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  profile() {
    this.router.navigate(['/profile']);
  }
  constructor(private router: Router) { }


  login() {
    this.router.navigate(['/login']);
  }
  title = 'Bienvenido a OAuth2 Frontend';
}

import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiUrl = `${environment.apiBaseUrl}/api/auth`;

  constructor(
    private router: Router,
    private http: HttpClient
  ) {}


  loginWithGoogle(): void {
    window.location.href = 'http://localhost:8080/oauth2/authorization/google';
  }


  saveToken(token: string): void {
    localStorage.setItem('jwt', token);
  }

  
  getToken(): string | null {
    return localStorage.getItem('jwt');
  }

 
  handleTokenFromUrl(): void {

    const params = new URLSearchParams(window.location.search);
    const token = params.get('token');

    if (token) {
      this.saveToken(token);
      this.router.navigate(['/profile'], { replaceUrl: true }); 
    }
  }


  logout(): void {
    localStorage.removeItem('jwt');
    this.router.navigate(['/login']);
  }


  isAuthenticated(): boolean {
    return this.getToken() !== null;
  }


  getProfile(): Observable<any> {
    return this.http.get(`${environment.apiBaseUrl}/api/users/profile`);
  }
}

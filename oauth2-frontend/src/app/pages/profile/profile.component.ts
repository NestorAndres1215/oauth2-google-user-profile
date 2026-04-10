import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
import { CommonModule } from '@angular/common'; // ✅ Importar esto
@Component({
  selector: 'app-profile',
  standalone: true,
    imports: [CommonModule], // ✅ Agregar aquí
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  user: any;

  constructor(private userService: UserService) {}

  ngOnInit(): void {
    this.userService.getProfile().subscribe({
      next: (data) => this.user = data,
      error: (err) => console.error(err)
    });
  }
}

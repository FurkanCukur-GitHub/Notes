import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { UserService } from '../user.service';

@Component({
  selector: 'app-user-add',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './user-add.component.html'
})
export class UserAddComponent {

  userForm = { number: null, name: '', surname: '' };

  constructor(private userService: UserService) {}

  saveUser() {
    this.userService.addUser(this.userForm).subscribe(() => {
      this.userForm = { number: null, name: '', surname: '' };
      window.location.reload();
    });
  }
}

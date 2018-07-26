import { UserService } from './../../services/user.service';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { User } from '../../model/user.model';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(
      private _authService: AuthService,
      private _userService: UserService,
      private _router: Router
  ) { }

  ngOnInit() {
    if (this._authService.checkCredentials()) {
        this._userService.findUserByUsername(this._authService.getUsername()).subscribe(
          user => this._router.navigate(['/users']),
          err =>  {
            if (err === '200') {
                const user = new User();
                user.username = this._authService.getUsername();
                this._userService.saveUserResource(user).subscribe(
                    data => {
                      console.log(JSON.stringify(data));
                      this._router.navigate(['/users']);
                    },
                    erro => {
                      console.log(JSON.stringify(erro));
                      this._authService.logout();
                    }
                );
            }
          }
        );
    }
  }

}

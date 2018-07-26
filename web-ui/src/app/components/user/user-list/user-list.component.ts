import { User } from './../../../model/user.model';
import { UserService } from './../../../services/user.service';
import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../../services/auth.service';
import { ActivatedRoute } from '../../../../../node_modules/@angular/router';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  okMsg: string = null;
  errorMsg: string = null;

  users: User[];

  constructor(
    private _authService: AuthService,
    private _userService: UserService,
    private _route: ActivatedRoute
  ) { }

  ngOnInit() {

    if (this._route.snapshot.params['id']) {
      this._userService.deleteUser(this._route.snapshot.params['id']).subscribe(
        resp => {
            console.log(JSON.stringify(resp));
            this.showSuccessMessage('Usuário removido com sucesso.');
            this.listUser();
        },
        error => {
          console.log(JSON.stringify(error));
          this.showErrorMessage('Ocorreu um erro ao remover o usuário');
          this.listUser();
        }
      );
    } else {
      this.listUser();
    }
  }

  private listUser() {
    this._userService.listUsers().subscribe(
      (data: User[]) => this.users = data,
      error => console.log(JSON.stringify(error))
    );
  }

  private showSuccessMessage(msg: string) {
    this.okMsg = msg;
    setTimeout(() => {
      this.okMsg = null;
    }, 5000);
  }

  private showErrorMessage(msg: string) {
    this.errorMsg = msg;
    setTimeout(() => {
      this.errorMsg = null;
    }, 5000);
  }

}

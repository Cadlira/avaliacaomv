import { Component, OnInit } from '@angular/core';
import { User } from '../../../model/user.model';
import { ActivatedRoute, Router } from '../../../../../node_modules/@angular/router';
import { UserService } from '../../../services/user.service';
import { AuthService } from '../../../services/auth.service';

@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.css']
})
export class UserFormComponent implements OnInit {
  okMsg: string = null;
  errorMsg: string = null;
  user: User = new User();

  constructor(
    private _authService: AuthService,
    private _userService: UserService,
    private _route: ActivatedRoute,
    private _router: Router
  ) { }

  ngOnInit() {
    if (this._route.snapshot.params['id']) {
      this._userService.findUserById(this._route.snapshot.params['id']).subscribe(
        (userResp: User) => {
          console.log(JSON.stringify(userResp));
          this.user.id = userResp.id;
          this.user.name = userResp.name;
          this.user.username = userResp.username;
          this.user.email = userResp.email;
          this.user.password = '';
        },
        error => {
          console.log(JSON.stringify(error));
          this.showErrorMessage('Usuario não existe.');
        }
      );
    }

  }

  save() {
    if (this.user.password !== null && this.user.password.trim().length > 0) {
      this._userService.registerUser(this.user).subscribe(
        resp => {
          console.log(JSON.stringify(resp));
          this.confirmeResourceUser(this.user);
        },
        err => {
          console.log(JSON.stringify(err));
          this.showErrorMessage('Não foi possível alterar a senha do usuário');
          this.confirmeResourceUser(this.user);
        }
      );
    } else {
      this.confirmeResourceUser(this.user);
    }
  }

  private confirmeResourceUser(user: User) {
    if (user.id > 0) {
      this.updateResourceUser(user);
    } else {
      this.saveResourceUser(user);
    }
  }

  private saveResourceUser(user: User) {
    this._userService.saveUserResource(user).subscribe(
      resp => {
        this.showSuccessMessage('Usuário cadastrado com sucesso.');
      },
      err => {
        if (err.statusHttp === 412) {
          this.showErrorMessage(err.errors[0].mensagem);
        } else {
          this.showErrorMessage('Não foi possível cadastrar o usuário, tente novamente mais tarde.');
        }
      }
    );
  }

  private updateResourceUser(user: User) {
    this._userService.updateUserResource(user).subscribe(
      resp => {
        this.showSuccessMessage('Usuário editado com sucesso.');
      },
      err => {
        if (err.statusHttp === 412) {
          this.showErrorMessage(err.errors[0].mensagem);
        } else {
          this.showErrorMessage('Não foi possível atualizar o usuário, tente novamente mais tarde.');
        }
      }
    );
  }

  cancel() {
    this._router.navigate(['/users']);
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

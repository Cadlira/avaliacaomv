import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { Observable } from 'rxjs/Observable';
import { User } from '../model/user.model';
import { catchError } from '../../../node_modules/rxjs/operators';

@Injectable()
export class UserService {

    constructor(
        private _http: HttpClient,
        private _authService: AuthService) { }


    listUsers() {
        return this._http.get<User[]>(this._authService.getApiUrl() + 'userrs/user/', this._authService.getHeaders()).pipe(
            catchError(this._authService.errorHandler)
        );
    }

    deleteUser(id) {
        return this._http.delete<User[]>(this._authService.getApiUrl() + 'userrs/user/' + id, this._authService.getHeaders()).pipe(
            catchError(this._authService.errorHandler)
        );
    }

    findUserByUsername(username) {
        return this.
        _http.get<User[]>(this._authService.getApiUrl() + 'userrs/user/findByUsername/' + username, this._authService.getHeaders())
        .pipe(
            catchError((err) => this._authService.errorHandler(err))
        );
    }

    findUserById(id) {
        return this._http.get<User[]>(this._authService.getApiUrl() + 'userrs/user/' + id, this._authService.getHeaders())
        .pipe(
            catchError((err) => this._authService.errorHandler(err))
        );
    }

    registerUser(user: User) {
        return this._http.post(this._authService.getApiUrl() + 'uaa/rest/register', JSON.stringify(user), this._authService.getHeaders())
        .pipe(
            catchError((err) => this._authService.errorHandler(err))
        );
    }

    saveUserResource(user: User) {
        return this._http.post(this._authService.getApiUrl() + 'userrs/user/', JSON.stringify(user), this._authService.getHeaders())
        .pipe(
            catchError((err) => this._authService.errorHandler(err))
        );
    }

    updateUserResource(user: User) {
        return this._http.put(this._authService.getApiUrl() + 'userrs/user/', JSON.stringify(user), this._authService.getHeaders())
        .pipe(
            catchError((err) => this._authService.errorHandler(err))
        );
    }
}

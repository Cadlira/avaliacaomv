import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Injectable, Inject } from '@angular/core';
import { PlatformLocation } from '@angular/common';
import { LOCAL_STORAGE, WebStorageService } from 'angular-webstorage-service';
import {_throw} from 'rxjs/observable/throw';
import { CookieService } from '../../../node_modules/ngx-cookie-service';

@Injectable()
export class AuthService {

    public clientId = 'avaliacaomv';

    constructor(
        @Inject(LOCAL_STORAGE) private _storage: WebStorageService,
        private _http: HttpClient,
        private _location: PlatformLocation,
        private cookie: CookieService) {
    }

    checkCredentials() {
        return this.cookie.check('access_token');
    }

    retrieveToken(codigo) {
        const parametros = new URLSearchParams();
        parametros.append('grant_type', 'authorization_code');
        parametros.append('client_id', this.clientId);
        parametros.append('redirect_uri', this.getRedirectUrl());
        parametros.append('code', codigo);

        // TODO Esta variável deveria ser colocada como filtro no zuul, se der tempo eu faço
        const authorizationHeader = 'Basic ' + btoa(this.clientId + ':avaliacaomv');
        const contentTypeHeader = 'application/x-www-form-urlencoded; charset=utf-8';

        const headers = new HttpHeaders({ 'Content-type': contentTypeHeader, 'Authorization': authorizationHeader });
        this._http.post(this.getApiUrl() + 'uaa/oauth/token', parametros.toString(), { headers: headers })
            .subscribe(
                data => this.saveToken(data),
                err => this.errorLogin(err)
            );
    }

    errorLogin(err) {
        alert('Código de acesso inválido!');
        window.location.href = this.getRedirectUrl();
    }
    saveToken(token) {
        const expireDate = new Date().getTime() + (1000 * token.expires_in);
        this.cookie.set('access_token', token.access_token, expireDate);
        this.cookie.set('username', token.username, expireDate);
        console.log('Token recuperado: ' + JSON.stringify(token));
        window.location.href = this.getRedirectUrl();
    }

    getHeaders(): any {
        const authorizationHeader = 'Bearer ' + this.cookie.get('access_token');
        const contentTypeHeader = 'application/json; charset=utf-8';

        const headers = new HttpHeaders({ 'Content-type': contentTypeHeader, 'Authorization': authorizationHeader });

        return { headers: headers };

    }

    errorHandler(err: HttpErrorResponse) {
        if (err.status !== 200) {
            if (err.error instanceof ErrorEvent) {
                return _throw('Sem conexão');
            } else {
                if (err.status === 412) {
                    return _throw(err.error);
                }
                console.log(JSON.stringify(err));
            }
            return _throw('Ocorreu um erro.');
        } else {
            console.log(JSON.stringify(err));
            return _throw('200');
        }
    }

    getUsername() {
        return this.cookie.get('username');
    }

    logout() {
       this.cookie.delete('access_token');
       this.cookie.delete('username');
       setTimeout(() => {
          window.location.href = this.getRedirectUrl();
       }, 1000);
    }

    login() {
        const queryParams = '?response_type=code&client_id=' + this.clientId + '&redirect_uri=' + this.getRedirectUrl();
        const urlLogin = this.getApiUrl() + 'uaa/oauth/authorize' + queryParams;
        window.location.href = urlLogin;
    }

    saveApiUrl(url) {
        this._storage.set('API_URL', url);
    }

    getApiUrl() {
        return this._storage.get('API_URL');
    }

    getRedirectUrl() {
        return (this._location as any).location.origin;
    }

}

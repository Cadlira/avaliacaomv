import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { AuthService } from './services/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  public isLogado = false;

  constructor(
    private _authService: AuthService,
    private _http: HttpClient) { }

  ngOnInit() {

    this._http.get('./assets/apiUrl.json').subscribe((api) => {
      if (api['url']) {
        this._authService.saveApiUrl(api['url']);

        this.isLogado = this._authService.checkCredentials();
        const codigoAcesso = this.getUrlParams(window.location.search)['code'];
        if (!this.isLogado && codigoAcesso != null) {
          this._authService.retrieveToken(codigoAcesso);
        } else if (!this.isLogado) {
          this._authService.login();
        }

      }
    });
  }

  private getUrlParams(search) {
    const hashes = search.slice(search.indexOf('?') + 1).split('&');
    const params = {};
    hashes.map(hash => {
      let [key, val] = hash.split('=');
      params[key] = decodeURIComponent(val);
    });

    return params;
  }

}

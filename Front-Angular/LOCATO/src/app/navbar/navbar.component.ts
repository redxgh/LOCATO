import { Component, OnInit } from '@angular/core';
import { SecurityService } from '../services/security.service';
import { KeycloakService } from 'keycloak-angular';
import { initFlowbite } from 'flowbite';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})

export class NavbarComponent implements OnInit {

  constructor(public secService: SecurityService, private keycloak: KeycloakService){}

  logout() {
    this.keycloak.logout(window.location.origin)
  }
  async login() {
    await this.keycloak.login({
      redirectUri: window.location.origin
    });
  }

    ngOnInit(): void {
      initFlowbite();
    }
}

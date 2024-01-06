import {Injectable} from "@angular/core";
import {KeycloakProfile} from "keycloak-js";
import {KeycloakEventType, KeycloakService} from "keycloak-angular";

@Injectable({providedIn : "root"})
export class SecurityService {
  public profile? : KeycloakProfile;
  constructor (public kcService: KeycloakService) {
    this.init();
  }
  init(){
    this.kcService.keycloakEvents$.subscribe({
      next: (e) => {
        if (e.type == KeycloakEventType.OnAuthSuccess) {
          this.kcService.loadUserProfile().then(profile=>{
            this.profile=profile;
          });
        }
      }
    });
  }
public getAuthenticatedUsername(): string | undefined {
    // Renvoie l'ID de l'utilisateur authentifié s'il est disponible
    console.log(this.profile?.username)
    return this.profile?.username;
  }
  public hasRoleIn(roles:string[]):boolean{
    let userRoles = this.kcService.getUserRoles();
    for(let role of roles){
      if (userRoles.includes(role)) return true;
    } return false;
  }

  public isAuthenticated(): boolean {
    return this.kcService.isLoggedIn();  }
}

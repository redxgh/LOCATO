import { Component } from '@angular/core';
import * as Aos from 'aos';

@Component({
  selector: 'app-welcome-page',
  templateUrl: './welcome-page.component.html',
  styleUrls: ['./welcome-page.component.scss']
})
export class WelcomePageComponent {
  ngAfterViewInit() {
    Aos.init();
  }
}

import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-type-post',
  templateUrl: './type-post.component.html',
  styleUrls: ['./type-post.component.scss']
})
export class TypePostComponent {
  constructor(private router: Router) {}
  activeCardIndex: number | null = null;

  setActiveCard(index: number) {
    this.activeCardIndex = index;
    this.selectedCardIndex = index;
  }
  selectedCardIndex: number | null = null;

  goTo() {
    if (this.selectedCardIndex === 0) {
      this.router.navigate(['/renting-ad']);
    } else if (this.selectedCardIndex === 1) {
      this.router.navigate(['roommate-ad']);
    } else {
    }
  }
}

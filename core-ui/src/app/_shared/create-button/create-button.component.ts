import {Component, Input, OnInit} from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-create-button',
  templateUrl: './create-button.component.html',
  styleUrls: ['./create-button.component.scss']
})
export class CreateButtonComponent implements OnInit {
  @Input() private route: string;

  constructor(
    private router: Router
  ) { }

  ngOnInit() {
  }

  onButton() {
    this.router.navigate([this.route]);
  }

}

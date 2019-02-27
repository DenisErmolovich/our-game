import { Component, OnInit } from '@angular/core';
import {ErrorBase} from '../../_models/error-base';
import {BalloonErrorService} from '../../_services/errors/balloon-error.service';

@Component({
  selector: 'app-ballon-error',
  templateUrl: './ballon-error.component.html',
  styleUrls: ['./ballon-error.component.scss']
})
export class BallonErrorComponent implements OnInit {
  public error: ErrorBase;
  public show = false;

  constructor(
    private errorService: BalloonErrorService
  ) { }

  ngOnInit() {
    this.errorService.dataTransferEvent$.subscribe(
      resp => {
        this.error = resp;
        this.showError();
      }
    );
  }

  private showError(): void {
    this.show = true;
    setTimeout(() => this.show = false, 5000);
  }

}

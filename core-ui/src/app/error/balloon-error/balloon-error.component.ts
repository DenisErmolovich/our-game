import {Component, OnDestroy, OnInit} from '@angular/core';
import {ErrorBase} from '../../_models/error-base';
import {BalloonErrorService} from '../../_services/errors/balloon-error.service';
import {Subscription} from 'rxjs';

@Component({
  selector: 'app-balloon-error',
  templateUrl: './balloon-error.component.html',
  styleUrls: ['./balloon-error.component.scss']
})
export class BalloonErrorComponent implements OnInit, OnDestroy {
  public error: ErrorBase;
  public show = false;

  private subscription: Subscription;

  constructor(
    private errorService: BalloonErrorService
  ) { }

  ngOnInit() {
    this.subscription = this.errorService.dataTransferEvent$.subscribe(
      resp => {
        this.error = resp;
        this.showError();
      }
    );
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  private showError(): void {
    this.show = true;
    setTimeout(() => this.show = false, 5000);
  }

}

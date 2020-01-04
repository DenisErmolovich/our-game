import {HttpClient} from '@angular/common/http';

export abstract class BaseHttp {

  protected constructor(
    protected http: HttpClient,
    protected prefix: string
  ) {}
}

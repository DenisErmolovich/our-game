import {environment} from "../../../../environments/environment";
import {HttpClient} from "@angular/common/http";

export abstract class BaseHttp {
  readonly baseUrl = environment.baseUrl;

  protected constructor(protected http: HttpClient) {}
}

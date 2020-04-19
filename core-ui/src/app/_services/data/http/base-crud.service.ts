import {BaseHttp} from './base-http';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {BaseWithId} from '../../../_models/base-with-id';

export abstract class BaseCrudService<T extends BaseWithId> extends BaseHttp {

  protected constructor(http: HttpClient, prefix: string) {
    super(http, prefix);
  }

  save(entity: T): Observable<T> {
    const url = this.prefix;
    return this.http.post<T>(url, entity);
  }

  update(entity: T): Observable<T> {
    const url = this.prefix;
    return this.http.put<T>(url, entity);
  }

  findAll(): Observable<Array<T>> {
    const url = this.prefix;
    return this.http.get<Array<T>>(url);
  }

  findById(id: string): Observable<T> {
    const url = this.prefix + '/' + id;
    return this.http.get<T>(url);
  }

  deleteById(id: string): Observable<void> {
    const url = this.prefix + '/' + id;
    return this.http.delete<void>(url);
  }
}

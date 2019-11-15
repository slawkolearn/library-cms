import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IWorker } from 'app/shared/model/worker.model';

type EntityResponseType = HttpResponse<IWorker>;
type EntityArrayResponseType = HttpResponse<IWorker[]>;

@Injectable({ providedIn: 'root' })
export class WorkerService {
  public resourceUrl = SERVER_API_URL + 'api/workers';

  constructor(protected http: HttpClient) {}

  create(worker: IWorker): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(worker);
    return this.http
      .post<IWorker>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(worker: IWorker): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(worker);
    return this.http
      .put<IWorker>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IWorker>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IWorker[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(worker: IWorker): IWorker {
    const copy: IWorker = Object.assign({}, worker, {
      dateOfBirth: worker.dateOfBirth != null && worker.dateOfBirth.isValid() ? worker.dateOfBirth.format(DATE_FORMAT) : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateOfBirth = res.body.dateOfBirth != null ? moment(res.body.dateOfBirth) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((worker: IWorker) => {
        worker.dateOfBirth = worker.dateOfBirth != null ? moment(worker.dateOfBirth) : null;
      });
    }
    return res;
  }
}

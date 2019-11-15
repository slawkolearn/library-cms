import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IOverdueFeeRate } from 'app/shared/model/overdue-fee-rate.model';

type EntityResponseType = HttpResponse<IOverdueFeeRate>;
type EntityArrayResponseType = HttpResponse<IOverdueFeeRate[]>;

@Injectable({ providedIn: 'root' })
export class OverdueFeeRateService {
  public resourceUrl = SERVER_API_URL + 'api/overdue-fee-rates';

  constructor(protected http: HttpClient) {}

  create(overdueFeeRate: IOverdueFeeRate): Observable<EntityResponseType> {
    return this.http.post<IOverdueFeeRate>(this.resourceUrl, overdueFeeRate, { observe: 'response' });
  }

  update(overdueFeeRate: IOverdueFeeRate): Observable<EntityResponseType> {
    return this.http.put<IOverdueFeeRate>(this.resourceUrl, overdueFeeRate, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IOverdueFeeRate>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IOverdueFeeRate[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}

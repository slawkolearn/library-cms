import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IBookLoan } from 'app/shared/model/book-loan.model';

type EntityResponseType = HttpResponse<IBookLoan>;
type EntityArrayResponseType = HttpResponse<IBookLoan[]>;

@Injectable({ providedIn: 'root' })
export class BookLoanService {
  public resourceUrl = SERVER_API_URL + 'api/book-loans';

  constructor(protected http: HttpClient) {}

  create(bookLoan: IBookLoan): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(bookLoan);
    return this.http
      .post<IBookLoan>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(bookLoan: IBookLoan): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(bookLoan);
    return this.http
      .put<IBookLoan>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IBookLoan>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IBookLoan[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(bookLoan: IBookLoan): IBookLoan {
    const copy: IBookLoan = Object.assign({}, bookLoan, {
      loanStartTimestamp:
        bookLoan.loanStartTimestamp != null && bookLoan.loanStartTimestamp.isValid() ? bookLoan.loanStartTimestamp.toJSON() : null,
      declaredLoanEndTimestamp:
        bookLoan.declaredLoanEndTimestamp != null && bookLoan.declaredLoanEndTimestamp.isValid()
          ? bookLoan.declaredLoanEndTimestamp.toJSON()
          : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.loanStartTimestamp = res.body.loanStartTimestamp != null ? moment(res.body.loanStartTimestamp) : null;
      res.body.declaredLoanEndTimestamp = res.body.declaredLoanEndTimestamp != null ? moment(res.body.declaredLoanEndTimestamp) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((bookLoan: IBookLoan) => {
        bookLoan.loanStartTimestamp = bookLoan.loanStartTimestamp != null ? moment(bookLoan.loanStartTimestamp) : null;
        bookLoan.declaredLoanEndTimestamp = bookLoan.declaredLoanEndTimestamp != null ? moment(bookLoan.declaredLoanEndTimestamp) : null;
      });
    }
    return res;
  }
}

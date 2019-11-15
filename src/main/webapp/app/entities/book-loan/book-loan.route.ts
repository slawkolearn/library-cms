import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { BookLoan } from 'app/shared/model/book-loan.model';
import { BookLoanService } from './book-loan.service';
import { BookLoanComponent } from './book-loan.component';
import { BookLoanDetailComponent } from './book-loan-detail.component';
import { BookLoanUpdateComponent } from './book-loan-update.component';
import { BookLoanDeletePopupComponent } from './book-loan-delete-dialog.component';
import { IBookLoan } from 'app/shared/model/book-loan.model';

@Injectable({ providedIn: 'root' })
export class BookLoanResolve implements Resolve<IBookLoan> {
  constructor(private service: BookLoanService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IBookLoan> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((bookLoan: HttpResponse<BookLoan>) => bookLoan.body));
    }
    return of(new BookLoan());
  }
}

export const bookLoanRoute: Routes = [
  {
    path: '',
    component: BookLoanComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'libraryCmsApp.bookLoan.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: BookLoanDetailComponent,
    resolve: {
      bookLoan: BookLoanResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'libraryCmsApp.bookLoan.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: BookLoanUpdateComponent,
    resolve: {
      bookLoan: BookLoanResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'libraryCmsApp.bookLoan.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: BookLoanUpdateComponent,
    resolve: {
      bookLoan: BookLoanResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'libraryCmsApp.bookLoan.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const bookLoanPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: BookLoanDeletePopupComponent,
    resolve: {
      bookLoan: BookLoanResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'libraryCmsApp.bookLoan.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];

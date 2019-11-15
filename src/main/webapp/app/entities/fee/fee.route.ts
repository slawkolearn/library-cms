import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Fee } from 'app/shared/model/fee.model';
import { FeeService } from './fee.service';
import { FeeComponent } from './fee.component';
import { FeeDetailComponent } from './fee-detail.component';
import { FeeUpdateComponent } from './fee-update.component';
import { FeeDeletePopupComponent } from './fee-delete-dialog.component';
import { IFee } from 'app/shared/model/fee.model';

@Injectable({ providedIn: 'root' })
export class FeeResolve implements Resolve<IFee> {
  constructor(private service: FeeService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IFee> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((fee: HttpResponse<Fee>) => fee.body));
    }
    return of(new Fee());
  }
}

export const feeRoute: Routes = [
  {
    path: '',
    component: FeeComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'libraryCmsApp.fee.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: FeeDetailComponent,
    resolve: {
      fee: FeeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'libraryCmsApp.fee.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: FeeUpdateComponent,
    resolve: {
      fee: FeeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'libraryCmsApp.fee.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: FeeUpdateComponent,
    resolve: {
      fee: FeeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'libraryCmsApp.fee.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const feePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: FeeDeletePopupComponent,
    resolve: {
      fee: FeeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'libraryCmsApp.fee.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];

import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { OverdueFeeRate } from 'app/shared/model/overdue-fee-rate.model';
import { OverdueFeeRateService } from './overdue-fee-rate.service';
import { OverdueFeeRateComponent } from './overdue-fee-rate.component';
import { OverdueFeeRateDetailComponent } from './overdue-fee-rate-detail.component';
import { OverdueFeeRateUpdateComponent } from './overdue-fee-rate-update.component';
import { OverdueFeeRateDeletePopupComponent } from './overdue-fee-rate-delete-dialog.component';
import { IOverdueFeeRate } from 'app/shared/model/overdue-fee-rate.model';

@Injectable({ providedIn: 'root' })
export class OverdueFeeRateResolve implements Resolve<IOverdueFeeRate> {
  constructor(private service: OverdueFeeRateService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IOverdueFeeRate> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((overdueFeeRate: HttpResponse<OverdueFeeRate>) => overdueFeeRate.body));
    }
    return of(new OverdueFeeRate());
  }
}

export const overdueFeeRateRoute: Routes = [
  {
    path: '',
    component: OverdueFeeRateComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'libraryCmsApp.overdueFeeRate.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: OverdueFeeRateDetailComponent,
    resolve: {
      overdueFeeRate: OverdueFeeRateResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'libraryCmsApp.overdueFeeRate.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: OverdueFeeRateUpdateComponent,
    resolve: {
      overdueFeeRate: OverdueFeeRateResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'libraryCmsApp.overdueFeeRate.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: OverdueFeeRateUpdateComponent,
    resolve: {
      overdueFeeRate: OverdueFeeRateResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'libraryCmsApp.overdueFeeRate.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const overdueFeeRatePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: OverdueFeeRateDeletePopupComponent,
    resolve: {
      overdueFeeRate: OverdueFeeRateResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'libraryCmsApp.overdueFeeRate.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];

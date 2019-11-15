import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Rating } from 'app/shared/model/rating.model';
import { RatingService } from './rating.service';
import { RatingComponent } from './rating.component';
import { RatingDetailComponent } from './rating-detail.component';
import { RatingUpdateComponent } from './rating-update.component';
import { RatingDeletePopupComponent } from './rating-delete-dialog.component';
import { IRating } from 'app/shared/model/rating.model';

@Injectable({ providedIn: 'root' })
export class RatingResolve implements Resolve<IRating> {
  constructor(private service: RatingService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IRating> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((rating: HttpResponse<Rating>) => rating.body));
    }
    return of(new Rating());
  }
}

export const ratingRoute: Routes = [
  {
    path: '',
    component: RatingComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'libraryCmsApp.rating.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: RatingDetailComponent,
    resolve: {
      rating: RatingResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'libraryCmsApp.rating.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: RatingUpdateComponent,
    resolve: {
      rating: RatingResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'libraryCmsApp.rating.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: RatingUpdateComponent,
    resolve: {
      rating: RatingResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'libraryCmsApp.rating.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const ratingPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: RatingDeletePopupComponent,
    resolve: {
      rating: RatingResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'libraryCmsApp.rating.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];

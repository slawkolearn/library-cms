import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Worker } from 'app/shared/model/worker.model';
import { WorkerService } from './worker.service';
import { WorkerComponent } from './worker.component';
import { WorkerDetailComponent } from './worker-detail.component';
import { WorkerUpdateComponent } from './worker-update.component';
import { WorkerDeletePopupComponent } from './worker-delete-dialog.component';
import { IWorker } from 'app/shared/model/worker.model';

@Injectable({ providedIn: 'root' })
export class WorkerResolve implements Resolve<IWorker> {
  constructor(private service: WorkerService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IWorker> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((worker: HttpResponse<Worker>) => worker.body));
    }
    return of(new Worker());
  }
}

export const workerRoute: Routes = [
  {
    path: '',
    component: WorkerComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'libraryCmsApp.worker.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: WorkerDetailComponent,
    resolve: {
      worker: WorkerResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'libraryCmsApp.worker.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: WorkerUpdateComponent,
    resolve: {
      worker: WorkerResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'libraryCmsApp.worker.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: WorkerUpdateComponent,
    resolve: {
      worker: WorkerResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'libraryCmsApp.worker.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const workerPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: WorkerDeletePopupComponent,
    resolve: {
      worker: WorkerResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'libraryCmsApp.worker.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];

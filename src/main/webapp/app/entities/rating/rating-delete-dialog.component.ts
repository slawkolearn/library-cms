import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRating } from 'app/shared/model/rating.model';
import { RatingService } from './rating.service';

@Component({
  selector: 'jhi-rating-delete-dialog',
  templateUrl: './rating-delete-dialog.component.html'
})
export class RatingDeleteDialogComponent {
  rating: IRating;

  constructor(protected ratingService: RatingService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.ratingService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'ratingListModification',
        content: 'Deleted an rating'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-rating-delete-popup',
  template: ''
})
export class RatingDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ rating }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(RatingDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.rating = rating;
        this.ngbModalRef.result.then(
          () => {
            this.router.navigate(['/rating', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          () => {
            this.router.navigate(['/rating', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          }
        );
      }, 0);
    });
  }

  ngOnDestroy() {
    this.ngbModalRef = null;
  }
}

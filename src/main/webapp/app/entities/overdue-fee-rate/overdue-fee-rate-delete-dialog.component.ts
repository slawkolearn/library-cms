import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IOverdueFeeRate } from 'app/shared/model/overdue-fee-rate.model';
import { OverdueFeeRateService } from './overdue-fee-rate.service';

@Component({
  selector: 'jhi-overdue-fee-rate-delete-dialog',
  templateUrl: './overdue-fee-rate-delete-dialog.component.html'
})
export class OverdueFeeRateDeleteDialogComponent {
  overdueFeeRate: IOverdueFeeRate;

  constructor(
    protected overdueFeeRateService: OverdueFeeRateService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.overdueFeeRateService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'overdueFeeRateListModification',
        content: 'Deleted an overdueFeeRate'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-overdue-fee-rate-delete-popup',
  template: ''
})
export class OverdueFeeRateDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ overdueFeeRate }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(OverdueFeeRateDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.overdueFeeRate = overdueFeeRate;
        this.ngbModalRef.result.then(
          () => {
            this.router.navigate(['/overdue-fee-rate', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          () => {
            this.router.navigate(['/overdue-fee-rate', { outlets: { popup: null } }]);
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

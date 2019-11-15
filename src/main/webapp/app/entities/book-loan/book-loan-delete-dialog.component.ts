import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBookLoan } from 'app/shared/model/book-loan.model';
import { BookLoanService } from './book-loan.service';

@Component({
  selector: 'jhi-book-loan-delete-dialog',
  templateUrl: './book-loan-delete-dialog.component.html'
})
export class BookLoanDeleteDialogComponent {
  bookLoan: IBookLoan;

  constructor(protected bookLoanService: BookLoanService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.bookLoanService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'bookLoanListModification',
        content: 'Deleted an bookLoan'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-book-loan-delete-popup',
  template: ''
})
export class BookLoanDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ bookLoan }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(BookLoanDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.bookLoan = bookLoan;
        this.ngbModalRef.result.then(
          () => {
            this.router.navigate(['/book-loan', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          () => {
            this.router.navigate(['/book-loan', { outlets: { popup: null } }]);
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

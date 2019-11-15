import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { LibraryCmsTestModule } from '../../../test.module';
import { BookLoanDeleteDialogComponent } from 'app/entities/book-loan/book-loan-delete-dialog.component';
import { BookLoanService } from 'app/entities/book-loan/book-loan.service';

describe('Component Tests', () => {
  describe('BookLoan Management Delete Component', () => {
    let comp: BookLoanDeleteDialogComponent;
    let fixture: ComponentFixture<BookLoanDeleteDialogComponent>;
    let service: BookLoanService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [LibraryCmsTestModule],
        declarations: [BookLoanDeleteDialogComponent]
      })
        .overrideTemplate(BookLoanDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BookLoanDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BookLoanService);
      mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
      mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));
    });
  });
});

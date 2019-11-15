import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { LibraryCmsTestModule } from '../../../test.module';
import { OverdueFeeRateDeleteDialogComponent } from 'app/entities/overdue-fee-rate/overdue-fee-rate-delete-dialog.component';
import { OverdueFeeRateService } from 'app/entities/overdue-fee-rate/overdue-fee-rate.service';

describe('Component Tests', () => {
  describe('OverdueFeeRate Management Delete Component', () => {
    let comp: OverdueFeeRateDeleteDialogComponent;
    let fixture: ComponentFixture<OverdueFeeRateDeleteDialogComponent>;
    let service: OverdueFeeRateService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [LibraryCmsTestModule],
        declarations: [OverdueFeeRateDeleteDialogComponent]
      })
        .overrideTemplate(OverdueFeeRateDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(OverdueFeeRateDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(OverdueFeeRateService);
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

import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { LibraryCmsTestModule } from '../../../test.module';
import { FeeDeleteDialogComponent } from 'app/entities/fee/fee-delete-dialog.component';
import { FeeService } from 'app/entities/fee/fee.service';

describe('Component Tests', () => {
  describe('Fee Management Delete Component', () => {
    let comp: FeeDeleteDialogComponent;
    let fixture: ComponentFixture<FeeDeleteDialogComponent>;
    let service: FeeService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [LibraryCmsTestModule],
        declarations: [FeeDeleteDialogComponent]
      })
        .overrideTemplate(FeeDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(FeeDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(FeeService);
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

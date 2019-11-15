import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { LibraryCmsTestModule } from '../../../test.module';
import { OverdueFeeRateUpdateComponent } from 'app/entities/overdue-fee-rate/overdue-fee-rate-update.component';
import { OverdueFeeRateService } from 'app/entities/overdue-fee-rate/overdue-fee-rate.service';
import { OverdueFeeRate } from 'app/shared/model/overdue-fee-rate.model';

describe('Component Tests', () => {
  describe('OverdueFeeRate Management Update Component', () => {
    let comp: OverdueFeeRateUpdateComponent;
    let fixture: ComponentFixture<OverdueFeeRateUpdateComponent>;
    let service: OverdueFeeRateService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [LibraryCmsTestModule],
        declarations: [OverdueFeeRateUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(OverdueFeeRateUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(OverdueFeeRateUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(OverdueFeeRateService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new OverdueFeeRate(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new OverdueFeeRate();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});

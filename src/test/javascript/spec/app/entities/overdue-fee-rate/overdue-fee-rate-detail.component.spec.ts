import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { LibraryCmsTestModule } from '../../../test.module';
import { OverdueFeeRateDetailComponent } from 'app/entities/overdue-fee-rate/overdue-fee-rate-detail.component';
import { OverdueFeeRate } from 'app/shared/model/overdue-fee-rate.model';

describe('Component Tests', () => {
  describe('OverdueFeeRate Management Detail Component', () => {
    let comp: OverdueFeeRateDetailComponent;
    let fixture: ComponentFixture<OverdueFeeRateDetailComponent>;
    const route = ({ data: of({ overdueFeeRate: new OverdueFeeRate(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [LibraryCmsTestModule],
        declarations: [OverdueFeeRateDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(OverdueFeeRateDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(OverdueFeeRateDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.overdueFeeRate).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});

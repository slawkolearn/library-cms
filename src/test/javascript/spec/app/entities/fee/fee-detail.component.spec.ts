import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { LibraryCmsTestModule } from '../../../test.module';
import { FeeDetailComponent } from 'app/entities/fee/fee-detail.component';
import { Fee } from 'app/shared/model/fee.model';

describe('Component Tests', () => {
  describe('Fee Management Detail Component', () => {
    let comp: FeeDetailComponent;
    let fixture: ComponentFixture<FeeDetailComponent>;
    const route = ({ data: of({ fee: new Fee(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [LibraryCmsTestModule],
        declarations: [FeeDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(FeeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(FeeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.fee).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});

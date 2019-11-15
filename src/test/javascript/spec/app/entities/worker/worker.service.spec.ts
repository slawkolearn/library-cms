import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { WorkerService } from 'app/entities/worker/worker.service';
import { IWorker, Worker } from 'app/shared/model/worker.model';

describe('Service Tests', () => {
  describe('Worker Service', () => {
    let injector: TestBed;
    let service: WorkerService;
    let httpMock: HttpTestingController;
    let elemDefault: IWorker;
    let expectedResult;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(WorkerService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Worker(0, 'AAAAAAA', currentDate, 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dateOfBirth: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );
        service
          .find(123)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: elemDefault });
      });

      it('should create a Worker', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dateOfBirth: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            dateOfBirth: currentDate
          },
          returnedFromService
        );
        service
          .create(new Worker(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a Worker', () => {
        const returnedFromService = Object.assign(
          {
            fullName: 'BBBBBB',
            dateOfBirth: currentDate.format(DATE_FORMAT),
            addressDetails: 'BBBBBB',
            contactDetails: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateOfBirth: currentDate
          },
          returnedFromService
        );
        service
          .update(expected)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should return a list of Worker', () => {
        const returnedFromService = Object.assign(
          {
            fullName: 'BBBBBB',
            dateOfBirth: currentDate.format(DATE_FORMAT),
            addressDetails: 'BBBBBB',
            contactDetails: 'BBBBBB'
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            dateOfBirth: currentDate
          },
          returnedFromService
        );
        service
          .query(expected)
          .pipe(
            take(1),
            map(resp => resp.body)
          )
          .subscribe(body => (expectedResult = body));
        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Worker', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});

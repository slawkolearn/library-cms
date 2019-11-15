import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { BookLoanService } from 'app/entities/book-loan/book-loan.service';
import { IBookLoan, BookLoan } from 'app/shared/model/book-loan.model';

describe('Service Tests', () => {
  describe('BookLoan Service', () => {
    let injector: TestBed;
    let service: BookLoanService;
    let httpMock: HttpTestingController;
    let elemDefault: IBookLoan;
    let expectedResult;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(BookLoanService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new BookLoan(0, currentDate, currentDate);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            loanStartTimestamp: currentDate.format(DATE_TIME_FORMAT),
            declaredLoanEndTimestamp: currentDate.format(DATE_TIME_FORMAT)
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

      it('should create a BookLoan', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            loanStartTimestamp: currentDate.format(DATE_TIME_FORMAT),
            declaredLoanEndTimestamp: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            loanStartTimestamp: currentDate,
            declaredLoanEndTimestamp: currentDate
          },
          returnedFromService
        );
        service
          .create(new BookLoan(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a BookLoan', () => {
        const returnedFromService = Object.assign(
          {
            loanStartTimestamp: currentDate.format(DATE_TIME_FORMAT),
            declaredLoanEndTimestamp: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            loanStartTimestamp: currentDate,
            declaredLoanEndTimestamp: currentDate
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

      it('should return a list of BookLoan', () => {
        const returnedFromService = Object.assign(
          {
            loanStartTimestamp: currentDate.format(DATE_TIME_FORMAT),
            declaredLoanEndTimestamp: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            loanStartTimestamp: currentDate,
            declaredLoanEndTimestamp: currentDate
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

      it('should delete a BookLoan', () => {
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

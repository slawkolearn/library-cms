import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBookLoan } from 'app/shared/model/book-loan.model';

@Component({
  selector: 'jhi-book-loan-detail',
  templateUrl: './book-loan-detail.component.html'
})
export class BookLoanDetailComponent implements OnInit {
  bookLoan: IBookLoan;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ bookLoan }) => {
      this.bookLoan = bookLoan;
    });
  }

  previousState() {
    window.history.back();
  }
}

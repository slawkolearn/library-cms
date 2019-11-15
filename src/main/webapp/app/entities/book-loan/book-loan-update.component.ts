import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { IBookLoan, BookLoan } from 'app/shared/model/book-loan.model';
import { BookLoanService } from './book-loan.service';
import { IBook } from 'app/shared/model/book.model';
import { BookService } from 'app/entities/book/book.service';
import { IClient } from 'app/shared/model/client.model';
import { ClientService } from 'app/entities/client/client.service';

@Component({
  selector: 'jhi-book-loan-update',
  templateUrl: './book-loan-update.component.html'
})
export class BookLoanUpdateComponent implements OnInit {
  isSaving: boolean;

  books: IBook[];

  clients: IClient[];

  editForm = this.fb.group({
    id: [],
    loanStartTimestamp: [null, [Validators.required]],
    declaredLoanEndTimestamp: [],
    bookId: [null, Validators.required],
    clientId: [null, Validators.required]
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected bookLoanService: BookLoanService,
    protected bookService: BookService,
    protected clientService: ClientService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ bookLoan }) => {
      this.updateForm(bookLoan);
    });
    this.bookService
      .query()
      .subscribe((res: HttpResponse<IBook[]>) => (this.books = res.body), (res: HttpErrorResponse) => this.onError(res.message));
    this.clientService
      .query()
      .subscribe((res: HttpResponse<IClient[]>) => (this.clients = res.body), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(bookLoan: IBookLoan) {
    this.editForm.patchValue({
      id: bookLoan.id,
      loanStartTimestamp: bookLoan.loanStartTimestamp != null ? bookLoan.loanStartTimestamp.format(DATE_TIME_FORMAT) : null,
      declaredLoanEndTimestamp:
        bookLoan.declaredLoanEndTimestamp != null ? bookLoan.declaredLoanEndTimestamp.format(DATE_TIME_FORMAT) : null,
      bookId: bookLoan.bookId,
      clientId: bookLoan.clientId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const bookLoan = this.createFromForm();
    if (bookLoan.id !== undefined) {
      this.subscribeToSaveResponse(this.bookLoanService.update(bookLoan));
    } else {
      this.subscribeToSaveResponse(this.bookLoanService.create(bookLoan));
    }
  }

  private createFromForm(): IBookLoan {
    return {
      ...new BookLoan(),
      id: this.editForm.get(['id']).value,
      loanStartTimestamp:
        this.editForm.get(['loanStartTimestamp']).value != null
          ? moment(this.editForm.get(['loanStartTimestamp']).value, DATE_TIME_FORMAT)
          : undefined,
      declaredLoanEndTimestamp:
        this.editForm.get(['declaredLoanEndTimestamp']).value != null
          ? moment(this.editForm.get(['declaredLoanEndTimestamp']).value, DATE_TIME_FORMAT)
          : undefined,
      bookId: this.editForm.get(['bookId']).value,
      clientId: this.editForm.get(['clientId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBookLoan>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackBookById(index: number, item: IBook) {
    return item.id;
  }

  trackClientById(index: number, item: IClient) {
    return item.id;
  }
}

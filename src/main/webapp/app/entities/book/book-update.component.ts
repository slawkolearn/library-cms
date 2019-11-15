import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { IBook, Book } from 'app/shared/model/book.model';
import { BookService } from './book.service';
import { IOverdueFeeRate } from 'app/shared/model/overdue-fee-rate.model';
import { OverdueFeeRateService } from 'app/entities/overdue-fee-rate/overdue-fee-rate.service';
import { IAuthor } from 'app/shared/model/author.model';
import { AuthorService } from 'app/entities/author/author.service';

@Component({
  selector: 'jhi-book-update',
  templateUrl: './book-update.component.html'
})
export class BookUpdateComponent implements OnInit {
  isSaving: boolean;

  overduefeerates: IOverdueFeeRate[];

  authors: IAuthor[];

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required]],
    description: [],
    maxBorrowingDays: [],
    borowwingDayRate: [null, [Validators.required]],
    additionalInfo: [],
    overdueFeeRates: [],
    authorId: [null, Validators.required]
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected bookService: BookService,
    protected overdueFeeRateService: OverdueFeeRateService,
    protected authorService: AuthorService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ book }) => {
      this.updateForm(book);
    });
    this.overdueFeeRateService
      .query()
      .subscribe(
        (res: HttpResponse<IOverdueFeeRate[]>) => (this.overduefeerates = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
    this.authorService
      .query()
      .subscribe((res: HttpResponse<IAuthor[]>) => (this.authors = res.body), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(book: IBook) {
    this.editForm.patchValue({
      id: book.id,
      name: book.name,
      description: book.description,
      maxBorrowingDays: book.maxBorrowingDays,
      borowwingDayRate: book.borowwingDayRate,
      additionalInfo: book.additionalInfo,
      overdueFeeRates: book.overdueFeeRates,
      authorId: book.authorId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const book = this.createFromForm();
    if (book.id !== undefined) {
      this.subscribeToSaveResponse(this.bookService.update(book));
    } else {
      this.subscribeToSaveResponse(this.bookService.create(book));
    }
  }

  private createFromForm(): IBook {
    return {
      ...new Book(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value,
      description: this.editForm.get(['description']).value,
      maxBorrowingDays: this.editForm.get(['maxBorrowingDays']).value,
      borowwingDayRate: this.editForm.get(['borowwingDayRate']).value,
      additionalInfo: this.editForm.get(['additionalInfo']).value,
      overdueFeeRates: this.editForm.get(['overdueFeeRates']).value,
      authorId: this.editForm.get(['authorId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBook>>) {
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

  trackOverdueFeeRateById(index: number, item: IOverdueFeeRate) {
    return item.id;
  }

  trackAuthorById(index: number, item: IAuthor) {
    return item.id;
  }

  getSelected(selectedVals: any[], option: any) {
    if (selectedVals) {
      for (let i = 0; i < selectedVals.length; i++) {
        if (option.id === selectedVals[i].id) {
          return selectedVals[i];
        }
      }
    }
    return option;
  }
}

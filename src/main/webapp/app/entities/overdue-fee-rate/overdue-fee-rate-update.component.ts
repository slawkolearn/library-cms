import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { IOverdueFeeRate, OverdueFeeRate } from 'app/shared/model/overdue-fee-rate.model';
import { OverdueFeeRateService } from './overdue-fee-rate.service';
import { IBook } from 'app/shared/model/book.model';
import { BookService } from 'app/entities/book/book.service';

@Component({
  selector: 'jhi-overdue-fee-rate-update',
  templateUrl: './overdue-fee-rate-update.component.html'
})
export class OverdueFeeRateUpdateComponent implements OnInit {
  isSaving: boolean;

  books: IBook[];

  editForm = this.fb.group({
    id: [],
    afterDays: [null, [Validators.required]],
    dayRate: [null, [Validators.required]]
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected overdueFeeRateService: OverdueFeeRateService,
    protected bookService: BookService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ overdueFeeRate }) => {
      this.updateForm(overdueFeeRate);
    });
    this.bookService
      .query()
      .subscribe((res: HttpResponse<IBook[]>) => (this.books = res.body), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(overdueFeeRate: IOverdueFeeRate) {
    this.editForm.patchValue({
      id: overdueFeeRate.id,
      afterDays: overdueFeeRate.afterDays,
      dayRate: overdueFeeRate.dayRate
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const overdueFeeRate = this.createFromForm();
    if (overdueFeeRate.id !== undefined) {
      this.subscribeToSaveResponse(this.overdueFeeRateService.update(overdueFeeRate));
    } else {
      this.subscribeToSaveResponse(this.overdueFeeRateService.create(overdueFeeRate));
    }
  }

  private createFromForm(): IOverdueFeeRate {
    return {
      ...new OverdueFeeRate(),
      id: this.editForm.get(['id']).value,
      afterDays: this.editForm.get(['afterDays']).value,
      dayRate: this.editForm.get(['dayRate']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOverdueFeeRate>>) {
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

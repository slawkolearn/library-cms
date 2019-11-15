import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { IFee, Fee } from 'app/shared/model/fee.model';
import { FeeService } from './fee.service';
import { IBookLoan } from 'app/shared/model/book-loan.model';
import { BookLoanService } from 'app/entities/book-loan/book-loan.service';

@Component({
  selector: 'jhi-fee-update',
  templateUrl: './fee-update.component.html'
})
export class FeeUpdateComponent implements OnInit {
  isSaving: boolean;

  bookloans: IBookLoan[];

  editForm = this.fb.group({
    id: [],
    fee: [null, [Validators.required]],
    description: [null, [Validators.required]],
    bookId: [null, Validators.required]
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected feeService: FeeService,
    protected bookLoanService: BookLoanService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ fee }) => {
      this.updateForm(fee);
    });
    this.bookLoanService
      .query()
      .subscribe((res: HttpResponse<IBookLoan[]>) => (this.bookloans = res.body), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(fee: IFee) {
    this.editForm.patchValue({
      id: fee.id,
      fee: fee.fee,
      description: fee.description,
      bookId: fee.bookId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const fee = this.createFromForm();
    if (fee.id !== undefined) {
      this.subscribeToSaveResponse(this.feeService.update(fee));
    } else {
      this.subscribeToSaveResponse(this.feeService.create(fee));
    }
  }

  private createFromForm(): IFee {
    return {
      ...new Fee(),
      id: this.editForm.get(['id']).value,
      fee: this.editForm.get(['fee']).value,
      description: this.editForm.get(['description']).value,
      bookId: this.editForm.get(['bookId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFee>>) {
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

  trackBookLoanById(index: number, item: IBookLoan) {
    return item.id;
  }
}

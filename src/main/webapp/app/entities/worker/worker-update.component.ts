import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { IWorker, Worker } from 'app/shared/model/worker.model';
import { WorkerService } from './worker.service';

@Component({
  selector: 'jhi-worker-update',
  templateUrl: './worker-update.component.html'
})
export class WorkerUpdateComponent implements OnInit {
  isSaving: boolean;
  dateOfBirthDp: any;

  editForm = this.fb.group({
    id: [],
    fullName: [null, [Validators.required]],
    dateOfBirth: [null, [Validators.required]],
    addressDetails: [],
    contactDetails: []
  });

  constructor(protected workerService: WorkerService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ worker }) => {
      this.updateForm(worker);
    });
  }

  updateForm(worker: IWorker) {
    this.editForm.patchValue({
      id: worker.id,
      fullName: worker.fullName,
      dateOfBirth: worker.dateOfBirth,
      addressDetails: worker.addressDetails,
      contactDetails: worker.contactDetails
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const worker = this.createFromForm();
    if (worker.id !== undefined) {
      this.subscribeToSaveResponse(this.workerService.update(worker));
    } else {
      this.subscribeToSaveResponse(this.workerService.create(worker));
    }
  }

  private createFromForm(): IWorker {
    return {
      ...new Worker(),
      id: this.editForm.get(['id']).value,
      fullName: this.editForm.get(['fullName']).value,
      dateOfBirth: this.editForm.get(['dateOfBirth']).value,
      addressDetails: this.editForm.get(['addressDetails']).value,
      contactDetails: this.editForm.get(['contactDetails']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IWorker>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}

import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { IClient, Client } from 'app/shared/model/client.model';
import { ClientService } from './client.service';

@Component({
  selector: 'jhi-client-update',
  templateUrl: './client-update.component.html'
})
export class ClientUpdateComponent implements OnInit {
  isSaving: boolean;
  dateOfBirthDp: any;

  editForm = this.fb.group({
    id: [],
    number: [null, [Validators.required]],
    fullName: [null, [Validators.required]],
    dateOfBirth: [null, [Validators.required]],
    addressDetails: [],
    contactDetails: []
  });

  constructor(protected clientService: ClientService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ client }) => {
      this.updateForm(client);
    });
  }

  updateForm(client: IClient) {
    this.editForm.patchValue({
      id: client.id,
      number: client.number,
      fullName: client.fullName,
      dateOfBirth: client.dateOfBirth,
      addressDetails: client.addressDetails,
      contactDetails: client.contactDetails
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const client = this.createFromForm();
    if (client.id !== undefined) {
      this.subscribeToSaveResponse(this.clientService.update(client));
    } else {
      this.subscribeToSaveResponse(this.clientService.create(client));
    }
  }

  private createFromForm(): IClient {
    return {
      ...new Client(),
      id: this.editForm.get(['id']).value,
      number: this.editForm.get(['number']).value,
      fullName: this.editForm.get(['fullName']).value,
      dateOfBirth: this.editForm.get(['dateOfBirth']).value,
      addressDetails: this.editForm.get(['addressDetails']).value,
      contactDetails: this.editForm.get(['contactDetails']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IClient>>) {
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

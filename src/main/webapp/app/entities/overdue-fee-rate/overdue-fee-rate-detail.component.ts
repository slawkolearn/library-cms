import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IOverdueFeeRate } from 'app/shared/model/overdue-fee-rate.model';

@Component({
  selector: 'jhi-overdue-fee-rate-detail',
  templateUrl: './overdue-fee-rate-detail.component.html'
})
export class OverdueFeeRateDetailComponent implements OnInit {
  overdueFeeRate: IOverdueFeeRate;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ overdueFeeRate }) => {
      this.overdueFeeRate = overdueFeeRate;
    });
  }

  previousState() {
    window.history.back();
  }
}

import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { LibraryCmsSharedModule } from 'app/shared/shared.module';
import { OverdueFeeRateComponent } from './overdue-fee-rate.component';
import { OverdueFeeRateDetailComponent } from './overdue-fee-rate-detail.component';
import { OverdueFeeRateUpdateComponent } from './overdue-fee-rate-update.component';
import { OverdueFeeRateDeletePopupComponent, OverdueFeeRateDeleteDialogComponent } from './overdue-fee-rate-delete-dialog.component';
import { overdueFeeRateRoute, overdueFeeRatePopupRoute } from './overdue-fee-rate.route';

const ENTITY_STATES = [...overdueFeeRateRoute, ...overdueFeeRatePopupRoute];

@NgModule({
  imports: [LibraryCmsSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    OverdueFeeRateComponent,
    OverdueFeeRateDetailComponent,
    OverdueFeeRateUpdateComponent,
    OverdueFeeRateDeleteDialogComponent,
    OverdueFeeRateDeletePopupComponent
  ],
  entryComponents: [OverdueFeeRateDeleteDialogComponent]
})
export class LibraryCmsOverdueFeeRateModule {}

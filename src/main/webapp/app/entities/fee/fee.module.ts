import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { LibraryCmsSharedModule } from 'app/shared/shared.module';
import { FeeComponent } from './fee.component';
import { FeeDetailComponent } from './fee-detail.component';
import { FeeUpdateComponent } from './fee-update.component';
import { FeeDeletePopupComponent, FeeDeleteDialogComponent } from './fee-delete-dialog.component';
import { feeRoute, feePopupRoute } from './fee.route';

const ENTITY_STATES = [...feeRoute, ...feePopupRoute];

@NgModule({
  imports: [LibraryCmsSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [FeeComponent, FeeDetailComponent, FeeUpdateComponent, FeeDeleteDialogComponent, FeeDeletePopupComponent],
  entryComponents: [FeeDeleteDialogComponent]
})
export class LibraryCmsFeeModule {}

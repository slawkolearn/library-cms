import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { LibraryCmsSharedModule } from 'app/shared/shared.module';
import { RatingComponent } from './rating.component';
import { RatingDetailComponent } from './rating-detail.component';
import { RatingUpdateComponent } from './rating-update.component';
import { RatingDeletePopupComponent, RatingDeleteDialogComponent } from './rating-delete-dialog.component';
import { ratingRoute, ratingPopupRoute } from './rating.route';

const ENTITY_STATES = [...ratingRoute, ...ratingPopupRoute];

@NgModule({
  imports: [LibraryCmsSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [RatingComponent, RatingDetailComponent, RatingUpdateComponent, RatingDeleteDialogComponent, RatingDeletePopupComponent],
  entryComponents: [RatingDeleteDialogComponent]
})
export class LibraryCmsRatingModule {}

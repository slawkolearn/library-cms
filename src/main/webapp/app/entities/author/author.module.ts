import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { LibraryCmsSharedModule } from 'app/shared/shared.module';
import { AuthorComponent } from './author.component';
import { AuthorDetailComponent } from './author-detail.component';
import { AuthorUpdateComponent } from './author-update.component';
import { AuthorDeletePopupComponent, AuthorDeleteDialogComponent } from './author-delete-dialog.component';
import { authorRoute, authorPopupRoute } from './author.route';

const ENTITY_STATES = [...authorRoute, ...authorPopupRoute];

@NgModule({
  imports: [LibraryCmsSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [AuthorComponent, AuthorDetailComponent, AuthorUpdateComponent, AuthorDeleteDialogComponent, AuthorDeletePopupComponent],
  entryComponents: [AuthorDeleteDialogComponent]
})
export class LibraryCmsAuthorModule {}

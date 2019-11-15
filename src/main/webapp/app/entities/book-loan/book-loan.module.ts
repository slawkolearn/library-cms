import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { LibraryCmsSharedModule } from 'app/shared/shared.module';
import { BookLoanComponent } from './book-loan.component';
import { BookLoanDetailComponent } from './book-loan-detail.component';
import { BookLoanUpdateComponent } from './book-loan-update.component';
import { BookLoanDeletePopupComponent, BookLoanDeleteDialogComponent } from './book-loan-delete-dialog.component';
import { bookLoanRoute, bookLoanPopupRoute } from './book-loan.route';

const ENTITY_STATES = [...bookLoanRoute, ...bookLoanPopupRoute];

@NgModule({
  imports: [LibraryCmsSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    BookLoanComponent,
    BookLoanDetailComponent,
    BookLoanUpdateComponent,
    BookLoanDeleteDialogComponent,
    BookLoanDeletePopupComponent
  ],
  entryComponents: [BookLoanDeleteDialogComponent]
})
export class LibraryCmsBookLoanModule {}

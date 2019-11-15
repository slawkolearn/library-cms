import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'worker',
        loadChildren: () => import('./worker/worker.module').then(m => m.LibraryCmsWorkerModule)
      },
      {
        path: 'client',
        loadChildren: () => import('./client/client.module').then(m => m.LibraryCmsClientModule)
      },
      {
        path: 'book',
        loadChildren: () => import('./book/book.module').then(m => m.LibraryCmsBookModule)
      },
      {
        path: 'author',
        loadChildren: () => import('./author/author.module').then(m => m.LibraryCmsAuthorModule)
      },
      {
        path: 'rating',
        loadChildren: () => import('./rating/rating.module').then(m => m.LibraryCmsRatingModule)
      },
      {
        path: 'book-loan',
        loadChildren: () => import('./book-loan/book-loan.module').then(m => m.LibraryCmsBookLoanModule)
      },
      {
        path: 'overdue-fee-rate',
        loadChildren: () => import('./overdue-fee-rate/overdue-fee-rate.module').then(m => m.LibraryCmsOverdueFeeRateModule)
      },
      {
        path: 'fee',
        loadChildren: () => import('./fee/fee.module').then(m => m.LibraryCmsFeeModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class LibraryCmsEntityModule {}

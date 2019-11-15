import { Moment } from 'moment';
import { IFee } from 'app/shared/model/fee.model';

export interface IBookLoan {
  id?: number;
  loanStartTimestamp?: Moment;
  declaredLoanEndTimestamp?: Moment;
  fees?: IFee[];
  bookId?: number;
  clientId?: number;
}

export class BookLoan implements IBookLoan {
  constructor(
    public id?: number,
    public loanStartTimestamp?: Moment,
    public declaredLoanEndTimestamp?: Moment,
    public fees?: IFee[],
    public bookId?: number,
    public clientId?: number
  ) {}
}

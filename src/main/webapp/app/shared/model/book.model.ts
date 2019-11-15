import { IRating } from 'app/shared/model/rating.model';
import { IBookLoan } from 'app/shared/model/book-loan.model';
import { IOverdueFeeRate } from 'app/shared/model/overdue-fee-rate.model';

export interface IBook {
  id?: number;
  name?: string;
  description?: string;
  maxBorrowingDays?: number;
  borowwingDayRate?: number;
  additionalInfo?: string;
  ratings?: IRating[];
  loans?: IBookLoan[];
  overdueFeeRates?: IOverdueFeeRate[];
  authorId?: number;
}

export class Book implements IBook {
  constructor(
    public id?: number,
    public name?: string,
    public description?: string,
    public maxBorrowingDays?: number,
    public borowwingDayRate?: number,
    public additionalInfo?: string,
    public ratings?: IRating[],
    public loans?: IBookLoan[],
    public overdueFeeRates?: IOverdueFeeRate[],
    public authorId?: number
  ) {}
}

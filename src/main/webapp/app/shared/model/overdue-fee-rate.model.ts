import { IBook } from 'app/shared/model/book.model';

export interface IOverdueFeeRate {
  id?: number;
  afterDays?: number;
  dayRate?: number;
  books?: IBook[];
}

export class OverdueFeeRate implements IOverdueFeeRate {
  constructor(public id?: number, public afterDays?: number, public dayRate?: number, public books?: IBook[]) {}
}

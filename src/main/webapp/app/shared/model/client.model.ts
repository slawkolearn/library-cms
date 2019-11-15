import { Moment } from 'moment';
import { IBookLoan } from 'app/shared/model/book-loan.model';

export interface IClient {
  id?: number;
  number?: string;
  fullName?: string;
  dateOfBirth?: Moment;
  addressDetails?: string;
  contactDetails?: string;
  loans?: IBookLoan[];
}

export class Client implements IClient {
  constructor(
    public id?: number,
    public number?: string,
    public fullName?: string,
    public dateOfBirth?: Moment,
    public addressDetails?: string,
    public contactDetails?: string,
    public loans?: IBookLoan[]
  ) {}
}

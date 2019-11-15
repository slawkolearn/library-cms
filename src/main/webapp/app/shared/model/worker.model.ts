import { Moment } from 'moment';

export interface IWorker {
  id?: number;
  fullName?: string;
  dateOfBirth?: Moment;
  addressDetails?: string;
  contactDetails?: string;
}

export class Worker implements IWorker {
  constructor(
    public id?: number,
    public fullName?: string,
    public dateOfBirth?: Moment,
    public addressDetails?: string,
    public contactDetails?: string
  ) {}
}

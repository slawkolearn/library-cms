export interface IFee {
  id?: number;
  fee?: number;
  description?: string;
  bookId?: number;
}

export class Fee implements IFee {
  constructor(public id?: number, public fee?: number, public description?: string, public bookId?: number) {}
}

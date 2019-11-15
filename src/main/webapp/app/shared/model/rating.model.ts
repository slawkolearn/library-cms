export interface IRating {
  id?: number;
  rating?: number;
  comment?: string;
  bookId?: number;
}

export class Rating implements IRating {
  constructor(public id?: number, public rating?: number, public comment?: string, public bookId?: number) {}
}

import { IBook } from 'app/shared/model/book.model';

export interface IAuthor {
  id?: number;
  name?: string;
  description?: string;
  books?: IBook[];
}

export class Author implements IAuthor {
  constructor(public id?: number, public name?: string, public description?: string, public books?: IBook[]) {}
}

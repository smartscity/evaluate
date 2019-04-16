export const enum Level {
  FIRST = 'FIRST',
  SECOND = 'SECOND',
  THIRD = 'THIRD'
}

export interface ISpeaker {
  id?: number;
  title?: string;
  orgName?: string;
  actor?: string;
  speaker?: string;
  level?: Level;
  pdf?: string;
}

export class Speaker implements ISpeaker {
  constructor(
    public id?: number,
    public title?: string,
    public orgName?: string,
    public actor?: string,
    public speaker?: string,
    public level?: Level,
    public pdf?: string
  ) {}
}

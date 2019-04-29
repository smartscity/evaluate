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
  iconContentType?: string;
  icon?: any;
  pdfContentType?: string;
  pdf?: any;
}

export class Speaker implements ISpeaker {
  constructor(
    public id?: number,
    public title?: string,
    public orgName?: string,
    public actor?: string,
    public speaker?: string,
    public level?: Level,
    public iconContentType?: string,
    public icon?: any,
    public pdfContentType?: string,
    public pdf?: any
  ) {}
}

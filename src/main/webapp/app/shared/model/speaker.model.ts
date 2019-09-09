export const enum Level {
  FIRST = 'FIRST',
  SECOND = 'SECOND',
  THIRD = 'THIRD'
}

export const enum Review {
  UNDO = 'UNDO',
  APPROVAL = 'APPROVAL',
  REFUSE = 'REFUSE'
}

export interface ISpeaker {
  id?: number;
  title?: string;
  orgName?: string;
  actor?: string;
  speaker?: string;
  level?: Level;
  pdfContentType?: string;
  pdf?: any;
  path?: string;
  localPath?: string;
  review?: Review;
  remark?: any;
}

export class Speaker implements ISpeaker {
  constructor(
    public id?: number,
    public title?: string,
    public orgName?: string,
    public actor?: string,
    public speaker?: string,
    public level?: Level,
    public pdfContentType?: string,
    public pdf?: any,
    public path?: string,
    public localPath?: string,
    public review?: Review,
    public remark?: any
  ) {}
}

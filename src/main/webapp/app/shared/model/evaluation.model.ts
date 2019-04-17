import { Moment } from 'moment';
import { IUser } from 'app/core/user/user.model';

export const enum Level {
  FIRST = 'FIRST',
  SECOND = 'SECOND',
  THIRD = 'THIRD'
}

export const enum TaskSource {
  NATIONAL_PLAN = 'NATIONAL_PLAN',
  BUWEI_PLAN = 'BUWEI_PLAN',
  SHENGSHI_PLAN = 'SHENGSHI_PLAN',
  JUNWEI_PLAN = 'JUNWEI_PLAN',
  FUND_SUPPORT = 'FUND_SUPPORT',
  GLOBAL_COOPERATION = 'GLOBAL_COOPERATION',
  OTHER_ORG = 'OTHER_ORG',
  OPTIONAL = 'OPTIONAL',
  OTHER = 'OTHER'
}

export const enum DiscoveryAndInnovation {
  HIGH = 'HIGH',
  MIDDLE = 'MIDDLE',
  GENERAL = 'GENERAL'
}

export const enum AdvancedLevel {
  LEADING = 'LEADING',
  ADVANCED = 'ADVANCED',
  GENERAL = 'GENERAL'
}

export const enum ApplicationAndPromotion {
  EXCELLENCE = 'EXCELLENCE',
  OBVIOUS = 'OBVIOUS',
  GENERAL = 'GENERAL'
}

export interface IEvaluation {
  id?: number;
  speakerId?: number;
  title?: string;
  orgName?: string;
  actor?: string;
  speaker?: string;
  level?: Level;
  taskSource?: TaskSource;
  taskSourceScore?: number;
  discoveryAndInnovation?: DiscoveryAndInnovation;
  discoveryAndInnovationScore?: number;
  advancedLevel?: AdvancedLevel;
  advancedLevelScore?: number;
  applicationAndPromotion?: ApplicationAndPromotion;
  applicationAndPromotionScore?: number;
  paperScore?: number;
  replyScore?: number;
  totalScore?: number;
  avgScore?: number;
  createTime?: Moment;
  updateTime?: Moment;
  evaluator?: string;
  remark?: string;
  user?: IUser;
}

export class Evaluation implements IEvaluation {
  constructor(
    public id?: number,
    public speakerId?: number,
    public title?: string,
    public orgName?: string,
    public actor?: string,
    public speaker?: string,
    public level?: Level,
    public taskSource?: TaskSource,
    public taskSourceScore?: number,
    public discoveryAndInnovation?: DiscoveryAndInnovation,
    public discoveryAndInnovationScore?: number,
    public advancedLevel?: AdvancedLevel,
    public advancedLevelScore?: number,
    public applicationAndPromotion?: ApplicationAndPromotion,
    public applicationAndPromotionScore?: number,
    public paperScore?: number,
    public replyScore?: number,
    public totalScore?: number,
    public avgScore?: number,
    public createTime?: Moment,
    public updateTime?: Moment,
    public evaluator?: string,
    public remark?: string,
    public user?: IUser
  ) {}
}

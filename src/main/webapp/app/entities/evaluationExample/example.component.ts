import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IEvaluation } from 'app/shared/model/evaluation.model';
import { AccountService } from 'app/core';
import { EvaluationExampleService } from './example.service';
import { SERVER_API_URL } from 'app/app.constants';

@Component({
  selector: 'jhi-evaluation',
  templateUrl: './example.component.html',
  styleUrls: ['./example.component.scss']
})
export class ExampleComponent implements OnInit, OnDestroy {
  evaluations: IEvaluation[];
  currentAccount: any;
  eventSubscriber: Subscription;

  taskOptions: any[];
  aboutTips: any[];
  taskSourceScore: number;
  discoveryOptions: any[];
  levelOptions: any[];
  applicationOptions: any[];
  // totalNumber: number;

  constructor(
    protected evaluationService: EvaluationExampleService,
    protected jhiAlertService: JhiAlertService,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {
    this.taskOptions = [
      { label: '国家计划', value: 'NATIONAL_PLAN' },
      { label: '部委计划、省（自治区、市）计划、解放军计划、基金资助', value: 'BUWEI_PLAN' },
      { label: '企事业单位委托、自选、其他', value: 'OTHER_ORG' }
    ];
    this.discoveryOptions = [
      { label: '重大发现和显著创新', value: 'HIGH' },
      { label: '重要发现和明显创新', value: 'MIDDLE' },
      { label: '新发现和一般创新', value: 'GENERAL' }
    ];
    this.levelOptions = [
      { label: '领先水平', value: 'LEADING' },
      { label: '先进水平', value: 'ADVANCED' },
      { label: '一般水平', value: 'GENERAL' }
    ];
    this.applicationOptions = [
      { label: '应用推广及效益显著', value: 'EXCELLENCE' },
      { label: '应用推广及效益明显', value: 'OBVIOUS' },
      { label: '应用推广及效益一般', value: 'GENERAL' }
    ];
  }

  loadAll() {
    this.evaluationService
      .query()
      .pipe(
        filter((res: HttpResponse<IEvaluation[]>) => res.ok),
        map((res: HttpResponse<IEvaluation[]>) => res.body)
      )
      .subscribe(
        (res: IEvaluation[]) => {
          this.evaluations = res;
          this.aboutTips = [];
          const typeMap = {
            taskSource: {
              NATIONAL_PLAN: { min: 4, max: 5, tip: '分值应在4-5分之间' },
              BUWEI_PLAN: { min: 2, max: 3, tip: '分值应在2-3分之间' },
              OTHER_ORG: { min: 1, max: 1, tip: '分值应为1分' },
              null: { min: 0, max: 0, tip: '请选择任务来源' }
            },
            discoveryAndInnovation: {
              HIGH: { min: 21, max: 30, tip: '分值应在21-30分之间' },
              MIDDLE: { min: 11, max: 20, tip: '分值应在11-20分之间' },
              GENERAL: { min: 0, max: 10, tip: '分值应在10分以下' },
              null: { min: 0, max: 0, tip: '请选择主要发现和创新点' }
            },
            advancedLevel: {
              LEADING: { min: 21, max: 30, tip: '分值应在21-30分之间' },
              ADVANCED: { min: 11, max: 20, tip: '分值应在11-20分之间' },
              GENERAL: { min: 0, max: 10, tip: '分值应在10分以下' },
              null: { min: 0, max: 0, tip: '请选择先进程度' }
            },
            applicationAndPromotion: {
              EXCELLENCE: { min: 11, max: 15, tip: '分值应在11-15分之间' },
              OBVIOUS: { min: 6, max: 10, tip: '分值应在6-10分之间' },
              GENERAL: { min: 0, max: 5, tip: '分值应在5分以下' },
              null: { min: 0, max: 0, tip: '请选择应用与推广及其效益' }
            }
          };
          for (let j = 0; j < this.evaluations.length; j++) {
            let taskSourceTip = typeMap.taskSource[this.evaluations[j].taskSource].tip,
              taskSourceMin = typeMap.taskSource[this.evaluations[j].taskSource].min,
              taskSourceMax = typeMap.taskSource[this.evaluations[j].taskSource].max,
              discoveryTip = typeMap.discoveryAndInnovation[this.evaluations[j].discoveryAndInnovation].tip,
              discoveryMin = typeMap.discoveryAndInnovation[this.evaluations[j].discoveryAndInnovation].min,
              discoveryMax = typeMap.discoveryAndInnovation[this.evaluations[j].discoveryAndInnovation].max,
              advancedLevelTip = typeMap.advancedLevel[this.evaluations[j].advancedLevel].tip,
              advancedLevelMin = typeMap.advancedLevel[this.evaluations[j].advancedLevel].min,
              advancedLevelMax = typeMap.advancedLevel[this.evaluations[j].advancedLevel].max,
              applicationTip = typeMap.applicationAndPromotion[this.evaluations[j].applicationAndPromotion].tip,
              applicationMin = typeMap.applicationAndPromotion[this.evaluations[j].applicationAndPromotion].min,
              applicationMax = typeMap.applicationAndPromotion[this.evaluations[j].applicationAndPromotion].max;
            const tempObj = {
              id: j,
              taskSource: {
                tip: taskSourceTip,
                min: taskSourceMin,
                max: taskSourceMax
              },
              discoveryAndInnovation: {
                tip: discoveryTip,
                min: discoveryMin,
                max: discoveryMax
              },
              advancedLevel: {
                tip: advancedLevelTip,
                min: advancedLevelMin,
                max: advancedLevelMax
              },
              applicationAndPromotion: {
                tip: applicationTip,
                min: applicationMin,
                max: applicationMax
              },
              paper: {
                tip: '分值应在5-10分',
                min: 5,
                max: 10
              },
              reply: {
                tip: '分值应在5-10分',
                min: 5,
                max: 10
              }
            };
            this.aboutTips.push(tempObj);
          }
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInEvaluationsExample();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }
  trackId(index: number, item: IEvaluation) {
    return item.id;
  }

  registerChangeInEvaluationsExample() {
    this.eventSubscriber = this.eventManager.subscribe('evaluationListModification', response => this.loadAll());
  }
  getTip(type, val, index) {
    const typeMap = {
      taskSource: {
        NATIONAL_PLAN: { min: 4, max: 5, tip: '分值应在4-5分之间' },
        BUWEI_PLAN: { min: 2, max: 3, tip: '分值应在2-3分之间' },
        OTHER_ORG: { min: 1, max: 1, tip: '分值应为1分' }
      },
      discoveryAndInnovation: {
        HIGH: { min: 21, max: 30, tip: '分值应在21-30分之间' },
        MIDDLE: { min: 11, max: 20, tip: '分值应在11-20分之间' },
        GENERAL: { min: 0, max: 10, tip: '分值应在10分以下' }
      },
      advancedLevel: {
        LEADING: { min: 21, max: 30, tip: '分值应在21-30分之间' },
        ADVANCED: { min: 11, max: 20, tip: '分值应在11-20分之间' },
        GENERAL: { min: 0, max: 10, tip: '分值应在10分以下' }
      },
      applicationAndPromotion: {
        EXCELLENCE: { min: 11, max: 15, tip: '分值应在11-15分之间' },
        OBVIOUS: { min: 6, max: 10, tip: '分值应在6-10分之间' },
        GENERAL: { min: 0, max: 5, tip: '分值应在5分以下' }
      }
    };
    this.aboutTips[index][type].tip = typeMap[type][val].tip;
    this.aboutTips[index][type].min = typeMap[type][val].min;
    this.aboutTips[index][type].max = typeMap[type][val].max;
  }
  changeTip(type, val, index) {
    this.getTip(type, val, index);
    this.evaluations[index][type + 'Score'] = 0;
  }
  getTotal(i) {
    let score1 = this.evaluations[i].taskSourceScore || 0,
      score2 = this.evaluations[i].discoveryAndInnovationScore || 0,
      score3 = this.evaluations[i].applicationAndPromotionScore || 0,
      score4 = this.evaluations[i].advancedLevelScore || 0,
      score5 = this.evaluations[i].paperScore || 0,
      score6 = this.evaluations[i].replyScore || 0;
    this.evaluations[i].totalScore = score1 + score2 + score3 + score4 + score5 + score6;
    this.evaluationService.update(this.evaluations[i]).subscribe(
      (res: HttpResponse<IEvaluation>) => {
        // this.evaluations[i]=res;
        this.aboutTips[i] = this.aboutTips[i];
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
  }
  registerChangeInEvaluations() {
    this.eventSubscriber = this.eventManager.subscribe('evaluationListModification', response => this.loadAll());
  }
  openFile(field) {
    // window.location.href=field;
    window.open(field);
    // window.open(SERVER_API_URL + '/api/download/' + field);
    // return this.dataUtils.openFile(contentType, field);
  }

  protected onError(errorMessage: string) {
    // this.jhiAlertService.error(errorMessage, null, null);
  }
}

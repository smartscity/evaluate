import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
// import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ISpeaker } from 'app/shared/model/speaker.model';
import { AccountService } from 'app/core';
import { SpeakerService } from './example.service';

@Component({
  selector: 'jhi-speaker',
  templateUrl: './example.component.html',
  styleUrls: ['./example.component.scss']
})
export class ExampleComponent implements OnInit, OnDestroy {
  speakers: any[];
  currentAccount: any;
  eventSubscriber: Subscription;
  taskOptions: any[];
  aboutTips: any[];
  taskSourceScore: number;
  discoveryOptions: any[];
  levelOptions: any[];
  applicationOptions: any[];
  totalNumber: number;

  constructor(
    protected speakerService: SpeakerService,
    // protected jhiAlertService: JhiAlertService,
    // protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {
    this.taskOptions = [
      { label: '国家计划', value: 1 },
      { label: '部委计划、省（自治区、市）计划、解放军计划、基金资助', value: 2 },
      { label: '企事业单位委托、自选、其他', value: 3 }
      // { label: '省（自治区、市）计划', value: '3' },
      // { label: '基金资助', value: '4' },
      // { label: '国际合作', value: '5' },
      // { label: '其他企事业单位委托', value: '6' },
      // { label: '自选', value: '7' },
      // { label: '其他', value: '8' }
    ];
    this.discoveryOptions = [
      { label: '重大发现和显著创新', value: 1 },
      { label: '重要发现和明显创新', value: 2 },
      { label: '新发现和一般创新', value: 3 }
    ];
    this.levelOptions = [{ label: '领先水平', value: 1 }, { label: '先进水平', value: 2 }, { label: '一般水平', value: 3 }];
    this.applicationOptions = [
      { label: '应用推广及效益显著', value: 1 },
      { label: '应用推广及效益明显', value: 2 },
      { label: '应用推广及效益一般', value: 3 }
    ];
  }

  loadAll() {
    this.speakers = [
      {
        id: 1,
        title: '中国脑性瘫痪康复指南(2015)',
        orgName: '中国康复医学会儿童康复专业委员会',
        actor: '李晓捷，唐久来',
        speak: '44',
        level: '一等奖',
        pdf: '66',
        taskSource: '',
        taskSourceScore: '',
        discoveryAndInnovation: '',
        discoveryAndInnovationScore: '',
        advancedLevel: '',
        advancedLevelScore: '',
        applicationAndPromotion: '',
        applicationAndPromotionScore: '',
        paper: '',
        paperScore: '',
        reply: '',
        replyScore: '',
        total: ''
      },
      {
        id: 2,
        title: '脑卒中后认知障碍康复评价及关健技术研究',
        orgName: '福建中医学大学',
        actor: '陈立典',
        speak: '44',
        level: '一等奖',
        pdf: '66',
        taskSource: '',
        taskSourceScore: '',
        discoveryAndInnovation: '',
        discoveryAndInnovationScore: '',
        advancedLevel: '',
        advancedLevelScore: '',
        applicationAndPromotion: '',
        applicationAndPromotionScore: '',
        paper: '',
        paperScore: '',
        reply: '',
        replyScore: '',
        total: ''
      },
      {
        id: 3,
        title: '脊柱脊髓损伤治疗、康复及修复的系统研究',
        orgName: '中国康复研究中心，中国人民解放军陆军总医院',
        actor: '洪毅、孙天胜、白金柱',
        speak: '44',
        level: '二等奖',
        pdf: '66',
        taskSource: '',
        taskSourceScore: '',
        discoveryAndInnovation: '',
        discoveryAndInnovationScore: '',
        advancedLevel: '',
        advancedLevelScore: '',
        applicationAndPromotion: '',
        applicationAndPromotionScore: '',
        paper: '',
        paperScore: '',
        reply: '',
        replyScore: '',
        total: ''
      },
      {
        id: 4,
        title: '颈椎病中医康复基础与临床研究',
        orgName: '中国康复研究中心，中国人民解放军陆军总医院',
        actor: '洪毅、孙天胜、白金柱',
        speak: '44',
        level: '二等奖',
        pdf: '66',
        taskSource: '',
        taskSourceScore: '',
        discoveryAndInnovation: '',
        discoveryAndInnovationScore: '',
        advancedLevel: '',
        advancedLevelScore: '',
        applicationAndPromotion: '',
        applicationAndPromotionScore: '',
        paper: '',
        paperScore: '',
        reply: '',
        replyScore: '',
        total: ''
      },
      {
        id: 5,
        title: '人体平衡评定及训练系统',
        orgName: '河南优德医疗设备股份有限公司',
        actor: '陈立春',
        speak: '44',
        level: '三等奖',
        pdf: '66',
        taskSource: '',
        taskSourceScore: '',
        discoveryAndInnovation: '',
        discoveryAndInnovationScore: '',
        advancedLevel: '',
        advancedLevelScore: '',
        applicationAndPromotion: '',
        applicationAndPromotionScore: '',
        paper: '',
        paperScore: '',
        reply: '',
        replyScore: '',
        total: ''
      },
      {
        id: 6,
        title: '小儿脑瘫手术的围麻醉期管理与康复',
        orgName: '四川省八一康复中心',
        actor: '赵泽宇、何霞',
        speak: '44',
        level: '三等奖',
        pdf: '66',
        taskSource: '',
        taskSourceScore: '',
        discoveryAndInnovation: '',
        discoveryAndInnovationScore: '',
        advancedLevel: '',
        advancedLevelScore: '',
        applicationAndPromotion: '',
        applicationAndPromotionScore: '',
        paper: '',
        paperScore: '',
        reply: '',
        replyScore: '',
        total: ''
      }
    ];
    this.aboutTips = [];
    for (let j = 0; j < this.speakers.length; j++) {
      const tempObj = {
        id: j,
        taskSource: {
          tip: '请选择任务来源',
          min: 0,
          max: 0
        },
        discoveryAndInnovation: {
          tip: '请选择主要发现和创新点',
          min: 0,
          max: 0
        },
        advancedLevel: {
          tip: '请选择先进程度',
          min: 0,
          max: 0
        },
        applicationAndPromotion: {
          tip: '请选择应用与推广及其效益',
          min: 0,
          max: 0
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
    // this.speakerService
    //   .query()
    //   .pipe(
    //     filter((res: HttpResponse<ISpeaker[]>) => res.ok),
    //     map((res: HttpResponse<ISpeaker[]>) => res.body)
    //   )
    //   .subscribe(
    //     (res: ISpeaker[]) => {
    //       this.speakers = res;
    //     },
    //     (res: HttpErrorResponse) => this.onError(res.message)
    //   );
  }

  ngOnInit() {
    this.loadAll();
    // this.accountService.identity().then(account => {
    //   this.currentAccount = account;
    // });
    // this.registerChangeInSpeakers();
  }

  ngOnDestroy() {
    //   this.eventManager.destroy(this.eventSubscriber);
  }
  trackId(index: number, item: ISpeaker) {
    return item.id;
  }

  changeTip(type, val, index) {
    const typeMap = {
      taskSource: {
        1: { min: 4, max: 5, tip: '分值应在4-5分之间' },
        2: { min: 2, max: 3, tip: '分值应在2-3分之间' },
        3: { min: 1, max: 1, tip: '分值应为1分' }
      },
      discoveryAndInnovation: {
        1: { min: 21, max: 30, tip: '分值应在21-30分之间' },
        2: { min: 11, max: 20, tip: '分值应在11-20分之间' },
        3: { min: 0, max: 10, tip: '分值应在10分以下' }
      },
      advancedLevel: {
        1: { min: 21, max: 30, tip: '分值应在21-30分之间' },
        2: { min: 11, max: 20, tip: '分值应在11-20分之间' },
        3: { min: 0, max: 10, tip: '分值应在10分以下' }
      },
      applicationAndPromotion: {
        1: { min: 11, max: 15, tip: '分值应在11-15分之间' },
        2: { min: 6, max: 10, tip: '分值应在6-10分之间' },
        3: { min: 0, max: 5, tip: '分值应在5分以下' }
      }
    };
    this.speakers[index][type + 'Score'] = '';
    this.aboutTips[index][type].tip = typeMap[type][val].tip;
    this.aboutTips[index][type].min = typeMap[type][val].min;
    this.aboutTips[index][type].max = typeMap[type][val].max;
  }
  getTotal(i) {
    this.speakers[i].total =
      parseInt(this.speakers[i].taskSourceScore || 0) +
      parseInt(this.speakers[i].discoveryAndInnovationScore || 0) +
      parseInt(this.speakers[i].applicationAndPromotionScore || 0) +
      parseInt(this.speakers[i].advancedLevelScore || 0) +
      parseInt(this.speakers[i].paperScore || 0) +
      parseInt(this.speakers[i].replyScore || 0);
  }
  registerChangeInSpeakers() {
    // this.eventSubscriber = this.eventManager.subscribe('speakerListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    // this.jhiAlertService.error(errorMessage, null, null);
  }
}

import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
// import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ISpeaker } from 'app/shared/model/speaker.model';
import { AccountService } from 'app/core';
import { SpeakerService } from './prize.service';

@Component({
  selector: 'jhi-speaker',
  templateUrl: './prize.component.html',
  styleUrls: ['./prize.component.scss']
})
export class PrizeComponent implements OnInit, OnDestroy {
  prizes: any[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected speakerService: SpeakerService,
    // protected jhiAlertService: JhiAlertService,
    // protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.speakerService
      .query()
      .pipe(
        filter((res: HttpResponse<ISpeaker[]>) => res.ok),
        map((res: HttpResponse<ISpeaker[]>) => res.body)
      )
      .subscribe(
        (res: ISpeaker[]) => {
          this.prizes = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }
  dealData() {
    this.prizes = [
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
        replyScore: ''
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
        replyScore: ''
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
        replyScore: ''
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
        replyScore: ''
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
        replyScore: ''
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
        replyScore: ''
      },
      {
        id: 7,
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
        replyScore: ''
      },
      {
        id: 8,
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
        replyScore: ''
      },
      {
        id: 9,
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
        replyScore: ''
      },
      {
        id: 10,
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
        replyScore: ''
      },
      {
        id: 11,
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
        replyScore: ''
      },
      {
        id: 12,
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
        replyScore: ''
      },
      {
        id: 13,
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
        replyScore: ''
      }
    ];
    const tempObj = [];
    for (let i = 0; i < this.prizes.length; i++) {
      let num = i % 10;
      if (num === i) {
        tempObj[num] = [];
      }
      // debugger
      this.prizes[i].$index = i;
      tempObj[num].push(this.prizes[i]);
    }
    this.prizes = tempObj;
    console.log(tempObj);
  }
  ngOnInit() {
    this.dealData();
    // this.loadAll();
    // this.accountService.identity().then(account => {
    //   this.currentAccount = account;
    // });
    // this.registerChangeInSpeakers();
  }

  ngOnDestroy() {
    // this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: ISpeaker) {
    return item.id;
  }

  registerChangeInSpeakers() {
    // this.eventSubscriber = this.eventManager.subscribe('speakerListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    // this.jhiAlertService.error(errorMessage, null, null);
  }
}
